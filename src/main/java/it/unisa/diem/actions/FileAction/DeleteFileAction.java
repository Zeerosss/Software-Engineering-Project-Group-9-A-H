package it.unisa.diem.actions.FileAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import it.unisa.diem.actions.Action;

public class DeleteFileAction implements Action {
    private File file;

    // Constructor to initialize the file when creating an instance of the class
    public DeleteFileAction(File file) {
        this.file = file;
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        try {
            // Attempt to delete the file
            Files.delete(file.toPath());
        } catch (IOException e) {
            if (Files.notExists(file.toPath())) {
                System.err.println("File doesn't exist or has been already deleted!");
            } else {
                System.err.println("Error when deleting the file");
            }
        }
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return "Delete the file: " + file.getName();
    }
}
