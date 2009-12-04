package com.bitbakery.arcwelder.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * 
 */
public class ArcFileTypeFactory extends FileTypeFactory{
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(ArcFileType.ARC_FILE_TYPE, ArcFileType.DEFAULT_EXTENSION);
    }
}
