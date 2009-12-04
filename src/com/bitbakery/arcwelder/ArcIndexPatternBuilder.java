package com.bitbakery.arcwelder;

import com.bitbakery.arcwelder.file.ArcFileType;
import com.bitbakery.arcwelder.file.ArcFileTypeFactory;
import com.bitbakery.arcwelder.lexer.ArcLexer;
import com.bitbakery.arcwelder.lexer.ArcTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.search.IndexPatternBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * ????
 */
public class ArcIndexPatternBuilder implements IndexPatternBuilder {

    public Lexer getIndexingLexer(PsiFile psiFile) {
        if (ArcFileType.ARC_FILE_TYPE.equals(psiFile.getFileType())) {
            return new ArcLexer();
        }
        return null;
    }

    public TokenSet getCommentTokenSet(PsiFile psiFile) {
        return ArcTokenTypes.COMMENTS;
    }

    public int getCommentStartDelta(IElementType iElementType) {
        return 0;
    }

    public int getCommentEndDelta(IElementType iElementType) {
        return 0;
    }
}
