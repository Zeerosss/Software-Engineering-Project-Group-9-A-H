package it.unisa.diem.triggers.FileSizeExceeds;

import java.io.File;

import it.unisa.diem.triggers.Trigger;

public class FileSizeExceedsTrigger implements Trigger {
    private String filePath;
    private double maxSize; // Maximum size in bytes
    private String unit; // Unit of measure

    public FileSizeExceedsTrigger(String filePath, double maxSize, String unit) {
        this.filePath = filePath;
        this.maxSize = maxSize;
        this.unit = unit;
    }

    @Override
    public boolean isValidated() {
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.length() > maxSize;
    }
    @Override
    public String toString() {
        String unitSuffix = " bytes";
        double displaySize = maxSize;
    
        if (maxSize >= 1024 * 1024 * 1024) {
            displaySize = maxSize / (1024 * 1024 * 1024);
            unitSuffix = " GB";
        } else if (maxSize >= 1024 * 1024) {
            displaySize = maxSize / (1024 * 1024);
            unitSuffix = " MB";
        } else if (maxSize >= 1024) {
            displaySize = maxSize / 1024;
            unitSuffix = " KB";
        }
    
        return "This file: " + filePath + "\nIs bigger in size than: " + displaySize + unitSuffix;
    }
    
}
