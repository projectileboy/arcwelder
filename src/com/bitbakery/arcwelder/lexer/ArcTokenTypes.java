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

package com.bitbakery.arcwelder.lexer;

import com.bitbakery.arcwelder.psi.ArcElementType;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * Defines the set of Arc tokens, which we expand slightly (e.g., 'if' isn't truly a token in Arc)
 */
public class    ArcTokenTypes {

    public static IElementType CHAR_LITERAL = new ArcElementType("character literal");
    public static IElementType NUMERIC_LITERAL = new ArcElementType("numeric literal");
    public static IElementType STRING_LITERAL = new ArcElementType("string literal");
    public static IElementType TRUE = new ArcElementType("t");
    /* TODO - Anything special for empty lists? They *are* equivalent to nil, y'know... */
    public static IElementType NIL = new ArcElementType("nil");
    public static TokenSet NUMERIC_LITERALS = TokenSet.create(NUMERIC_LITERAL);
    public static TokenSet STRING_LITERALS = TokenSet.create(STRING_LITERAL);
    public static TokenSet BOOLEAN_LITERALS = TokenSet.create(TRUE, NIL);
    public static TokenSet LITERALS = TokenSet.create(STRING_LITERAL, NUMERIC_LITERAL, CHAR_LITERAL, TRUE, NIL);


    public static IElementType LINE_COMMENT = new ArcElementType("line comment");
    public static IElementType MULTILINE_COMMENT = new ArcElementType("multiline comment");
    public static IElementType BLOCK_COMMENT = new ArcElementType("block comment");
    public static TokenSet COMMENTS = TokenSet.create(LINE_COMMENT, MULTILINE_COMMENT, BLOCK_COMMENT);

    public static TokenSet READABLE_TEXT = TokenSet.create(STRING_LITERAL, BLOCK_COMMENT, LINE_COMMENT);


    public static IElementType LEFT_PAREN = new ArcElementType("(");
    public static IElementType RIGHT_PAREN = new ArcElementType(")");
    public static IElementType LEFT_SQUARE = new ArcElementType("[");
    public static IElementType RIGHT_SQUARE = new ArcElementType("]");
    public static TokenSet BRACKETS = TokenSet.create(LEFT_PAREN, RIGHT_PAREN, LEFT_SQUARE, RIGHT_SQUARE);

    /**
     * TODO - We're not handling the underscore correctly. We probably need to do the whole JFlex-state-while-in-square-brackets thingy...  *****
     */
    public static IElementType UNDERSCORE = new ArcElementType("_");



    public static IElementType DEF = new ArcElementType("def");
    public static IElementType MAC = new ArcElementType("mac");

    public static IElementType TILDE = new ArcElementType("~");
    public static IElementType EQ = new ArcElementType("=");
    public static IElementType BACKQUOTE = new ArcElementType("`");
    public static IElementType QUOTE = new ArcElementType("'");
    public static IElementType COMMA = new ArcElementType(",");
    public static IElementType COMMA_AT = new ArcElementType(",@");
    public static IElementType DOT = new ArcElementType(".");
    public static IElementType COMPOSER = new ArcElementType(":");

    public static IElementType QUOTE_KEYWORD = new ArcElementType("quote");
    public static IElementType FN = new ArcElementType("fn");
    public static IElementType IF = new ArcElementType("if");
    public static IElementType DO = new ArcElementType("do");
    public static IElementType LET = new ArcElementType("let");
    public static IElementType WITH = new ArcElementType("with");



    public static IElementType SYMBOL = new ArcElementType("symbol");
    public static TokenSet SYMBOL_FILTER = TokenSet.create(ArcTokenTypes.SYMBOL);


    public static IElementType EOL = new ArcElementType("end of line");
    public static IElementType EOF = new ArcElementType("end of file");
    public static IElementType WHITESPACE = TokenType.WHITE_SPACE;
    public static IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    public static TokenSet WHITESPACE_TOKENS = TokenSet.create(EOL, EOF, WHITESPACE);
    // TODO - Not tokens, but we should know what library functions are available depending on the CL implementation we're using??
    // TODO - We should understand the syntax of common macros, like do, print format synatx, etc.
    // TODO - Should we distinguish between macros and functions that are destructive?


}
