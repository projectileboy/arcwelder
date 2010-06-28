/*
 * Copyright (c) Kurt Christensen, 2010.
 *
 * Licensed under the Artistic License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.bitbakery.arcwelder.structure;

import com.bitbakery.arcwelder.ArcIcons;
import com.bitbakery.arcwelder.highlighter.ArcSyntaxHighlighter;
import com.bitbakery.arcwelder.psi.Assignment;
import com.bitbakery.arcwelder.psi.Def;
import com.bitbakery.arcwelder.psi.Mac;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.util.treeView.smartTree.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.bitbakery.arcwelder.ArcStrings.*;

/**
 * Defines groupers, sorters and filters used within the "structure view" for an Arc file
 */
public class ArcStructureViewModel extends TextEditorBasedStructureViewModel {
    private PsiFile file;

    public ArcStructureViewModel(final PsiFile file) {
        super(file);
        this.file = file;
    }

    @NotNull
    public StructureViewTreeElement getRoot() {
        return new ArcStructureViewElement(file);
    }

    @NotNull
    public Grouper[] getGroupers() {
        return new Grouper[]{new ArcStructureGrouper()};
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    @NotNull
    public Filter[] getFilters() {
        return new Filter[]{
                new ArcStructureFilter(Def.class, ArcIcons.DEF, "Def filter", "display.or.hide.function.def.definitions", "display.function.definitions"),
                new ArcStructureFilter(Mac.class, ArcIcons.MAC, "Mac filter", "display.or.hide.macro.mac.definitions", "display.macro.definitions"),
                new ArcStructureFilter(Assignment.class, ArcIcons.EQ, "Assignment filter", "display.or.hide.assignments", "display.assignments")};
    }

    protected PsiFile getPsiFile() {
        return file;
    }

    @NotNull
    protected Class[] getSuitableClasses() {
        return new Class[]{Def.class, Mac.class, Assignment.class};
    }


    public static class ArcStructureGroup implements Group {
        private Collection<TreeElement> elements = new ArrayList<TreeElement>();

        private String nameKey;
        private Icon icon;
        private TextAttributesKey textAttrKey;

        public ArcStructureGroup(String nameKey, Icon icon, TextAttributesKey textAttrKey) {
            this.nameKey = nameKey;
            this.icon = icon;
            this.textAttrKey = textAttrKey;
        }

        public ItemPresentation getPresentation() {
            return new ItemPresentation() {
                public String getPresentableText() { return str(nameKey); }
                public String getLocationString() { return null; }
                public Icon getIcon(boolean open) { return icon; }
                public TextAttributesKey getTextAttributesKey() { return textAttrKey; }
            };
        }

        public Collection<TreeElement> getChildren() { return elements; }

        protected void add(TreeElement el) { elements.add(el); }
    }


    public static class ArcStructureGrouper implements Grouper {

        // TODO - Note that we could have slightly different grouping mechanisms for top-level nodes vs. child def/mac groups

        @NotNull
        public Collection<Group> group(AbstractTreeNode parent, Collection<TreeElement> children) {
            if (parent.getValue() instanceof ArcStructureViewElement) {
                Collection<Group> groups = new ArrayList<Group>();

                ArcStructureGroup defs = new ArcStructureGroup("function.definitions", ArcIcons.DEF, ArcSyntaxHighlighter.DEF);
                ArcStructureGroup macs = new ArcStructureGroup("macro.definitions", ArcIcons.MAC, ArcSyntaxHighlighter.MAC);
                ArcStructureGroup eqs = new ArcStructureGroup("assignments", ArcIcons.EQ, ArcSyntaxHighlighter.DEF); // TODO - Is the DEF correct here?

                for (TreeElement el : children) {
                    if (el instanceof StructureViewTreeElement) {
                        StructureViewTreeElement svel = (StructureViewTreeElement) el;
                        if (svel.getValue() instanceof Def) {
                            defs.add(el);
                        } else if (svel.getValue() instanceof Mac) {
                            macs.add(el);
                        } else if (svel.getValue() instanceof Assignment) {
                            eqs.add(el);
                        }
                    }
                }

                groups.add(defs);
                groups.add(macs);
                groups.add(eqs);

                return groups;
            }
            return new ArrayList<Group>();
        }

        @NotNull
        public ActionPresentation getPresentation() {
            return new ActionPresentation() {
                public String getText() {
                    return "Group by Type";
                }

                public String getDescription() {
                    return "Group elements by type (def, mac, =)";
                }

                public Icon getIcon() {
                    return ArcIcons.GROUPING;
                }
            };
        }

        @NotNull
        public String getName() {
            return "def/mac/= grouper";
        }
    }
}

