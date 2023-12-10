package it.unisa.diem.actions.FileActions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import it.unisa.diem.SEGroup9.AlertController;
import it.unisa.diem.actions.Action;


public class MoveFileAction implements Action {
    private File file;
    private String destinationPath;

    // Constructor to initialize the file and destination path when creating an instance of the class
    public MoveFileAction(File file, String destinationPath) {
        this.file = file;
        // Create the full destination path by combining the destination directory and the file name
        this.destinationPath = Paths.get(destinationPath, file.getName()).toString();
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        try {
            // Attempt to move the file to the destination path
            Files.move(file.toPath(), Paths.get(destinationPath));
        } catch (IOException e) {
            if (Files.exists(Paths.get(destinationPath))) {
                AlertController.displayAlertWarning("Warning!",null , "File already moved!");
            } else {
                AlertController.displayAlertWarning("Warning!",null , "An error occurred while moving the file!");
            }
        }
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return "Move the File:" + file.getName() + "\n to:" + destinationPath;
    }
}
