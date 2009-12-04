package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * Root PSI element for an Arc source file
 */
public class Root extends ArcElement {
    public Root(ASTNode node) {
        super(node);
    }
}
