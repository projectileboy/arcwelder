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

    IElementType PARAMETERS = new ArcElementType("parameters");
    IElementType SYMBOL_REFERENCE = new ArcElementType("symbol reference");
    IElementType SYMBOL_ASSIGNMENT = new ArcElementType("symbol assignment");

    // Specific flavors of expressions
    IElementType ANONYMOUS_DEFINITION = new ArcElementType("[ ]");
    IElementType FN_DEFINITION = new ArcElementType("fn");
    IElementType FUNCTION_DEFINITION = new ArcElementType("def");
    IElementType MACRO_DEFINITION = new ArcElementType("mac");
    IElementType ASSIGNMENT = new ArcElementType("=");
    IElementType EXPRESSION = new ArcElementType("s-expression");

    // Literals
    IElementType LITERAL = new ArcElementType("literal");
}
