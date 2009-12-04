package com.bitbakery.arcwelder.file;

import com.bitbakery.arcwelder.file.ArcFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * PSI element for an Arc file
 */
public class ArcFile extends PsiFileBase {
    public ArcFile(FileViewProvider viewProvider) {
        super(viewProvider, ArcFileType.ARC_LANGUAGE);
    }

    @NotNull
    public FileType getFileType() {
        return ArcFileType.ARC_FILE_TYPE;
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
