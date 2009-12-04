package com.bitbakery.arcwelder.file;

import com.bitbakery.arcwelder.ArcIcons;
import com.bitbakery.arcwelder.ArcLanguage;
import com.bitbakery.arcwelder.highlighter.ArcEditorHighlighter;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Icons;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Defines the file type for Arc source files
 */
public class ArcFileType extends LanguageFileType {

    public static final ArcFileType ARC_FILE_TYPE = new ArcFileType();
    public static final Language ARC_LANGUAGE = ARC_FILE_TYPE.getLanguage();

    @NonNls
    public static final String DEFAULT_EXTENSION = "arc";

    private ArcFileType() {
        super(new ArcLanguage());
    }

    @NotNull
    @NonNls
    public String getName() {
        return "Arc";
    }

    @NotNull
    public String getDescription() {
        return "Arc files"; // TODO - l10n
    }

    @NotNull
    @NonNls
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return ArcIcons.FILE;
    }

    public boolean isJVMDebuggingSupported() {
        return false;
    }

    public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        return new ArcEditorHighlighter(colors);
    }
}
