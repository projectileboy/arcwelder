package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for 'mac' macro definitions in Arc
 */
public class Mac extends Expression {
    public Mac(ASTNode node) {
        super(node);
        System.out.println("Creating mac node: " + node.getText());
    }
}