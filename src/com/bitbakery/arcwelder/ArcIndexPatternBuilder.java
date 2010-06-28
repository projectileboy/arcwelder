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
