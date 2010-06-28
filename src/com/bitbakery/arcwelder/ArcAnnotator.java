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

import com.bitbakery.arcwelder.highlighter.ArcSyntaxHighlighter;
import com.bitbakery.arcwelder.psi.Docstring;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;

/**
 * The ArcAnnotator provides an additional level of parsing, enabling syntax highlighting for docstrings, for example.
 * Placing this within ArcAnnotator greatly simplifies the lexer, which would otherwise have to practically become a
 * parser in order to distinguish docstrings from regular strings as tokens.
 */
public class ArcAnnotator implements Annotator {
    public void annotate(PsiElement psiElement, AnnotationHolder holder) {
        if (psiElement instanceof Docstring) {
            Annotation a = holder.createInfoAnnotation(psiElement, null);
            a.setTextAttributes(ArcSyntaxHighlighter.DOCSTRING);
        }
    }
}
