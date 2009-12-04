package com.bitbakery.arcwelder;

import com.bitbakery.arcwelder.lexer.ArcTokenTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.bitbakery.arcwelder.lexer.ArcTokenTypes.*;

/**
 * Defines paired braces for Arc code
 */
public class ArcBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(LEFT_PAREN, RIGHT_PAREN, true),
            new BracePair(LEFT_SQUARE, RIGHT_SQUARE, false)
    };

    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType lbraceType, @Nullable final IElementType tokenType) {
        return true;
    }

    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}