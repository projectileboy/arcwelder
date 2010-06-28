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

import com.bitbakery.arcwelder.psi.ArcElement;
import com.bitbakery.arcwelder.psi.Assignment;
import com.bitbakery.arcwelder.psi.Def;
import com.bitbakery.arcwelder.psi.Mac;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNamedElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a single element within the structure view for an Arc file
 */
public class ArcStructureViewElement implements StructureViewTreeElement {
    private PsiElement element;

    public ArcStructureViewElement(PsiElement element) {
        this.element = element;
    }

    public PsiElement getValue() {
        return element;
    }

    public void navigate(boolean requestFocus) {
        ((NavigationItem) element).navigate(requestFocus);
    }

    public boolean canNavigate() {
        return ((NavigationItem) element).canNavigate();
    }

    public boolean canNavigateToSource() {
        return ((NavigationItem) element).canNavigateToSource();
    }

    public StructureViewTreeElement[] getChildren() {
        final List<ArcElement> childrenElements = new ArrayList<ArcElement>();
        element.acceptChildren(new PsiElementVisitor() {
            public void visitElement(PsiElement element) {
                if (isBrowsableElement(element)) {
                    childrenElements.add((ArcElement) element);
                } else {
                    element.acceptChildren(this);
                }
            }
        });

        StructureViewTreeElement[] children = new StructureViewTreeElement[childrenElements.size()];
        for (int i = 0; i < children.length; i++) {
            children[i] = new ArcStructureViewElement(childrenElements.get(i));
        }

        return children;
    }

    private boolean isBrowsableElement(PsiElement e) {
        return e instanceof Def
                || e instanceof Mac
                || e instanceof Assignment;
    }

    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            public String getPresentableText() {
                return ((PsiNamedElement) element).getName();
            }

            public TextAttributesKey getTextAttributesKey() {
                return null;
            }

            public String getLocationString() {
                return null;
            }

            public Icon getIcon(boolean open) {
                return element.getIcon(Iconable.ICON_FLAG_OPEN);
            }
        };
    }
}