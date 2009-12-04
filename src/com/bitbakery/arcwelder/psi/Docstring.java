package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for a docstring, which (by convention) is the string literal following
 * the parameter list in a function or macro definition which contains more than
 * one expression in the body. Proper syntax highlighting is applied by ArcAnnotator.
 */
public class Docstring extends ArcElement {
    public Docstring(ASTNode node) {
        super(node);
    }
}