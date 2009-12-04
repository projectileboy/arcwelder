package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for 'def' function definitions in Arc
 */
public class Def extends Expression {
    public Def(ASTNode node) {
        super(node);
        System.out.println("Creating def node: " + node.getText());
    }
}