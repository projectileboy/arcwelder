package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element representing an s-expression in Arc; also acts as the base class for more specialized expressios (such as function definitions)
 */
public class Expression extends ArcElement {
    public Expression(ASTNode node) {
        super(node);
    }
}