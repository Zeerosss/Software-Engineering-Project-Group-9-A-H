package it.unisa.diem.actions.FileAction;

import it.unisa.diem.SEGroup9.AlertController;
import it.unisa.diem.actions.Action;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileAction implements Action {
    private File file;
    private String destinationPath;

    // Constructor to initialize the file and destination path when creating an instance of the class.
    public CopyFileAction(File file, String destinationPath) {
        this.file = file;
        // Create the full destination path by combining the destination directory and the file name
        this.destinationPath = Paths.get(destinationPath, file.getName()).toString();
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        try {
            // Copy the source file to the destination path, replacing existing files if necessary
            Files.copy(file.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            AlertController.displayAlertWarning("Warning!",null , "An error occurred while copying the file!");
        }
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return ("Copy the file:" + file.getName() + "\n to:" + destinationPath);
    }
}
