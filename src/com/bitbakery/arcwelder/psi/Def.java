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

package com.bitbakery.arcwelder.psi;

import com.bitbakery.arcwelder.ArcIcons;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

/**
 * PSI element for 'def' function definitions in Arc
 */
public class Def extends Expression implements PsiNamedElement {
    public Def(ASTNode node) {
        super(node);
    }

    public PsiElement setName(@NonNls String s) throws IncorrectOperationException {
        // TODO
        return null;
    }

    @Override
    public String getName() {
        PsiElement[] children = getChildren();
        if (children.length > 0)
            if (children[0] instanceof SymbolAssignment)
                return children[0].getText();

        return "< missing name >";
    }

    public Icon getIcon(int flags) {
        return ArcIcons.DEF;
    }
}