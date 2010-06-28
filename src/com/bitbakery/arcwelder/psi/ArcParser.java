/*
 * Copyright (c) Kurt Christensen, 2010.
 *
 * Licensed under the Artistic License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

import static com.bitbakery.arcwelder.lexer.ArcTokenTypes.*;
import static com.bitbakery.arcwelder.psi.ArcElementTypes.*;

/**
 * Parses a stream of Arc tokens into a meaningful tree of PSI elements.
 */
public class ArcParser implements PsiParser {
    private Stack<Marker> markers = new Stack<Marker>();
    private PsiBuilder b;

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder b) {
        this.b = b;

        final PsiBuilder.Marker rootMarker = b.mark();

        try {
            while (!b.eof()) parseNext();
        } catch (EofException e) {
            while (!markers.isEmpty()) {
                markers.pop().marker.drop();
            }
        }

        rootMarker.done(root);
        return b.getTreeBuilt();
    }

    private void parseNext() {
        if (isAt(LEFT_PAREN))
            parseExpression();
        else if (isAt(LEFT_SQUARE))
            parseAnonymous();
        else if (isAt(LITERALS))
            consume(LITERAL);
        else if (isAt(SYMBOL))
            consume(SYMBOL_REFERENCE);
        else
            advance(); // Glide over that which we do not understand... just like real life!
    }

    private void parseExpression() {
        mark(EXPRESSION);
        advance();
        
        if (isAt(DEF))
            parseDefinition(FUNCTION_DEFINITION);
        else if (isAt(FN))
            parseDefinition(FN_DEFINITION);
        else if (isAt(MAC))
            parseDefinition(MACRO_DEFINITION);
        else if (isAt(EQ))
            parseAssignment();

        parseBody(RIGHT_PAREN);
        end();
    }

    private void parseAnonymous() {
        mark(ANONYMOUS_DEFINITION);
        advance();
        parseBody(RIGHT_SQUARE);
        end();
    }

    private void parseBody(IElementType terminator) {
        while (!isAt(terminator)) {
            parseNext();
        }
        advance();
    }

    private void parseAssignment() {
        modify(ASSIGNMENT);
        advance();

        if (isAt(SYMBOL)) {
            consume(SYMBOL_ASSIGNMENT);
        } else if (isAt(LEFT_PAREN)) {
            parseExpression();
        } 
    }

    private void parseDefinition(IElementType type) {
        modify(type);
        advance();

        // Anonymous functions are anonymous. Hence the name.
        if (type != FN_DEFINITION) {
            if (isAt(SYMBOL))
                consume(SYMBOL_ASSIGNMENT);
            else
                b.error("Expected symbol");
        }
        
        parseParameters();
        parseDocstring();
    }

    private void parseParameters() {
        mark(PARAMETERS);
        if (isAt(SYMBOL)) {
            consume(SYMBOL_ASSIGNMENT);
        } else if (isAt(LEFT_PAREN)) {
            advance();
            parseBody(RIGHT_PAREN);
        }
        end();
    }

    // TODO - Note that we could have a macro which defines param names programmatically, so be careful with this one!
    private void parseParameter() {
        if (isAt(SYMBOL)) {
            consume(SYMBOL_ASSIGNMENT);
/*
        } else if (isAt(LEFT_PAREN)) {
            parseOptionalParameter();
*/
        }
    }

    private void parseDocstring() {
        if (isAt(STRING_LITERAL)) {
            mark(DOCSTRING);
            advance();

            // If the string is the *entire* body of a def/mac/fn, then it is *not* a docstring...
            if (isAt(RIGHT_PAREN))
                modify(LITERAL);

            end();
        }
    }

    private void consume(IElementType type) {
        mark(type);
        advance();
        end();
    }

    private boolean isAt(IElementType tokenType) {
        if (b.eof()) throw new EofException();
        return b.getTokenType() == tokenType;
    }

    private boolean isAt(TokenSet tokenSet) {
        if (b.eof()) throw new EofException();
        return tokenSet.contains(b.getTokenType());
    }

    private void mark(IElementType type) { markers.push(new Marker(b.mark(), type)); }
    private void modify(IElementType type) { markers.peek().type = type; }
    private void end() { markers.pop().done(); }
    private void advance() { b.advanceLexer(); }


    private static class EofException extends RuntimeException {
        // Intentionally empty - this is just a goto that bails us out of the parse stack
    }

    private static class Marker {
        PsiBuilder.Marker marker;
        IElementType type;

        Marker(PsiBuilder.Marker marker, IElementType type) {
            this.marker = marker;
            this.type = type;
        }

        void done() {
            marker.done(type);
            System.out.println("" + type);
        }
    }
}
