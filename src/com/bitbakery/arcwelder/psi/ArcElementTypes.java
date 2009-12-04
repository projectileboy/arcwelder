package com.bitbakery.arcwelder.psi;

import com.bitbakery.arcwelder.file.ArcFileType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * Defines the various types of PSI elements identified by the ArcParser,
 * which in turn are created by ArcParserDefinition.
 */
public interface ArcElementTypes {
    IFileElementType FILE = new IFileElementType(ArcFileType.ARC_LANGUAGE);
    IElementType DOCSTRING = new ArcElementType("docstring");

    // Specific flavors of expressions
    IElementType FUNCTION_DEFINITION = new ArcElementType("def");
    IElementType MACRO_DEFINITION = new ArcElementType("mac");
    IElementType ASSIGNMENT = new ArcElementType("=");
    IElementType EXPRESSION = new ArcElementType("s-expression");

    // Literals
    IElementType LITERAL = new ArcElementType("literal");
}
