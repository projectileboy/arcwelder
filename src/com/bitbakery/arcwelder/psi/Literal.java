package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for numeric, string and character literals in Arc
 */
public class Literal extends ArcElement {
    public Literal(ASTNode node) {
        super(node);
    }
}