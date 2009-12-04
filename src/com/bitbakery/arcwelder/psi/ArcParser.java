package com.bitbakery.arcwelder.psi;

import com.bitbakery.arcwelder.lexer.ArcTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.io.EOFException;

import static com.bitbakery.arcwelder.lexer.ArcTokenTypes.*;
import static com.bitbakery.arcwelder.psi.ArcElementTypes.*;

/**
 * Parses a stream of Arc tokens into a meaningful tree of PSI elements.
 */
public class ArcParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType root, PsiBuilder b) {
        final PsiBuilder.Marker rootMarker = b.mark();

        try {
        while (!b.eof()) {
            parseNext(b);
        }
        } catch (EofException e) {

        }

        rootMarker.done(root);
        return b.getTreeBuilt();
    }

    private void parseNext(PsiBuilder b) {
        if (isAt(b, LEFT_PAREN)) {
            parseExpression(b);
        } else if (isAt(b, LITERALS)) {
            parseLiteral(b);
        } else {
            b.advanceLexer();
        }
    }

    private void parseExpression(PsiBuilder b) {
        IElementType type = EXPRESSION;
        
        PsiBuilder.Marker m = b.mark();
        b.advanceLexer(); // Move past the opening left paren

        if (isAt(b, DEF)) {
            type = FUNCTION_DEFINITION;
            b.advanceLexer();
        } else if (isAt(b, MAC)) {
            type = MACRO_DEFINITION;
            b.advanceLexer();
        } else if (isAt(b, EQ)) {
            // TODO - If the next chunk is an expression and not a symbol, then we're just setting a value in a map or some such...
            type = ASSIGNMENT;
            b.advanceLexer();
        }

        while (!isAt(b, RIGHT_PAREN)) {
            parseNext(b);
        }
        
        b.advanceLexer();
        m.done(type);
    }

    private void parseDef(PsiBuilder b) {
        parseName(b);
        parseParameters(b);
        parseDocstring(b);
        parseBody(b);
    }

    private void parseMac(PsiBuilder b) {
        parseName(b);
        parseParameters(b);
        parseDocstring(b);
        parseBody(b);
    }


     // TODO - We need element types for var defs and var refs - a def or mac name is a var def, as is a parameter, as is a let/with var, as is an = var
    //          Any other sybols are var refs


    private void parseLiteral(PsiBuilder b) {
        PsiBuilder.Marker m = b.mark();
        b.advanceLexer();
        m.done(LITERAL);
    }

    private boolean isAt(PsiBuilder b, IElementType tokenType) {
        return b.getTokenType() == tokenType;
    }

    private boolean isAt(PsiBuilder b, TokenSet tokenSet) {
        return tokenSet.contains(b.getTokenType());
    }


    private static class EofException extends RuntimeException {
        
    }
}
