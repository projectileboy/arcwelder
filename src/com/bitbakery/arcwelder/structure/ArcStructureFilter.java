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

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.ActionPresentation;
import com.intellij.ide.util.treeView.smartTree.Filter;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static com.bitbakery.arcwelder.ArcStrings.*;

/**
 * Function definition (def) filter for the structure view editor.
 */
public class ArcStructureFilter implements Filter {

    private Class type;
    private Icon icon;
    private String name;
    private String descriptionKey;
    private String textKey;

    public ArcStructureFilter(Class type, Icon icon, String name, String descriptionKey, String textKey) {
        this.type = type;
        this.icon = icon;
        this.name = name;
        this.descriptionKey = descriptionKey;
        this.textKey = textKey;
    }

    public boolean isVisible(TreeElement treeNode) {
        return !(treeNode instanceof StructureViewTreeElement)
                || !type.isInstance(((StructureViewTreeElement) treeNode).getValue());
    }

    public boolean isReverted() { return true; }

    @NotNull
    public ActionPresentation getPresentation() {
        return new ActionPresentation() {
            public String getText() { return str(textKey); }
            public String getDescription() { return str(descriptionKey); }
            public Icon getIcon() { return icon; }
        };
    }

    @NotNull
    public String getName() { return name; }
}