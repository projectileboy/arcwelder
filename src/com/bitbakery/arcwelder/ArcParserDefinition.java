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

package com.bitbakery.arcwelder;

import com.bitbakery.arcwelder.psi.*;
import com.bitbakery.arcwelder.lexer.ArcLexer;
import com.bitbakery.arcwelder.file.ArcFile;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import static com.bitbakery.arcwelder.lexer.ArcTokenTypes.*;
import static com.bitbakery.arcwelder.psi.ArcElementTypes.*;

/**
 * Defines the implementation of our Arc file parser. Note that the real parsing guts are in ArcParser.
 */
public class ArcParserDefinition implements ParserDefinition {
    @NotNull
    public Lexer createLexer(Project project) {
        return new ArcLexer();
    }

    public PsiParser createParser(Project project) {
        return new ArcParser();
    }

    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITESPACE_TOKENS;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    public ParserDefinition.SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MUST;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new ArcFile(viewProvider);
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();
        return type == FILE ? new Root(node) :
               type == FUNCTION_DEFINITION ? new Def(node) :
               type == MACRO_DEFINITION ? new Mac(node) :
               type == FN_DEFINITION ? new Fn(node) :
               type == ANONYMOUS_DEFINITION ? new Anon(node) :
               type == ASSIGNMENT ? new Assignment(node) :
               type == DOCSTRING ? new Docstring(node) :
               type == EXPRESSION ? new Expression(node) :
               type == SYMBOL_ASSIGNMENT ? new SymbolAssignment(node) :
               type == SYMBOL_REFERENCE ? new SymbolReference(node) :
               type == PARAMETERS ? new Parameters(node) :
               type == LITERAL ? new Literal(node) :
               new ASTWrapperPsiElement(node);
    }
}
