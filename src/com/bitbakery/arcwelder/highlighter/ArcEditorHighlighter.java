package com.bitbakery.arcwelder.highlighter;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LexerEditorHighlighter;

/**
 * Syntax highlighter used by the formatting options screen
 */
public class ArcEditorHighlighter extends LexerEditorHighlighter {

    public ArcEditorHighlighter(EditorColorsScheme scheme) {
        super(new ArcSyntaxHighlighter(), scheme);
    }
}