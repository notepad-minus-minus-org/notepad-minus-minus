package com.nmm.core.model;

import java.io.File;

public class DocumentSession {

    public enum FileType {
        NONE,
        TEXT,
        PDF,
        EPUB
    }

    private File currentFile = null;
    private FileType fileType = FileType.NONE;

    public boolean isFileOpen() {
        return fileType != FileType.NONE;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType == null ? FileType.NONE : fileType;
        if (this.fileType == FileType.NONE) {
            this.currentFile = null;
        }
    }
}
