package com.bitbakery.arcwelder;

import com.bitbakery.arcwelder.file.ArcFile;
import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Serves up icons
 */
public class ArcIconProvider extends IconProvider {
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, int flags) {
        if (psiElement instanceof ArcFile) {
            return ArcIcons.FILE;
        }
        return null;
    }
}
