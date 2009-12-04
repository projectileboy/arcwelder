package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for '=' global variable assignments in Arc
 */
public class Assignment extends Expression {
    public Assignment(ASTNode node) {
        super(node);
        System.out.println("Creating assignment node: " + node.getText());
    }
}