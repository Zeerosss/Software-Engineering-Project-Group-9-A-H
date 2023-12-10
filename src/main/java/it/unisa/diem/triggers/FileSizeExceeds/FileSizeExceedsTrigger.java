package it.unisa.diem.triggers.FileSizeExceeds;

import java.io.File;

import it.unisa.diem.triggers.Trigger;

public class FileSizeExceedsTrigger implements Trigger{
    private String filePath;
    private double maxSize; // Maximum size in bytes

    public FileSizeExceedsTrigger(String filePath, double maxSize) {
        this.filePath = filePath;
        this.maxSize = maxSize;
    }

    @Override
    public boolean isValidated() {
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.length() > maxSize;
    }

    @Override
    public String toString() {
        return "FileSizeTrigger:\n" + "fileName=\n" + filePath + "\nsizeFile=\n" + maxSize;
    }
}
