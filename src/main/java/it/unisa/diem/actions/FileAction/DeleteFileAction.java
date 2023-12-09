package it.unisa.diem.actions.FileAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.MessageAction.AlertDisplayer;
import it.unisa.diem.actions.MessageAction.AlertJavaFX;

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
                AlertDisplayer alert=new AlertJavaFX();   
                alert.displayAlert("Information","File already deleted", "Press OK to close the window");
            } else {
                AlertDisplayer alert=new AlertJavaFX();   
                alert.displayAlert("Information","An error occurred while deleting the file", "Press OK to close the window");
            }
        }
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return "Delete the file: " + file.getName();
    }
}
