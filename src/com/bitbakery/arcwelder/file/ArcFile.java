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
        return super.getIcon(flags);
    }
}
