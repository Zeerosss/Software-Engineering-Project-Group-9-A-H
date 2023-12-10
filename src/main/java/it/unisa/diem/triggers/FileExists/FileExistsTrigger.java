package it.unisa.diem.triggers.FileExists;

import it.unisa.diem.triggers.Trigger;

import java.nio.file.*;

public class FileExistsTrigger implements Trigger {

    private String directoryPath;
    private String fileName;

    public FileExistsTrigger(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    @Override
    public boolean isValidated() {
        Path filePath = Paths.get(directoryPath, fileName);
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    @Override
    public String toString() {
    return "This file: " + fileName + "\nExists in this Directory: " + directoryPath;
}

}
