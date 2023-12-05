package it.unisa.diem.actions.AppendTextAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import it.unisa.diem.actions.Action;

public class AppendTextAction implements Action {
    public String filePath;
    public String message;

    // Constructor to initialize the file path and message when creating an instance of the class
    public AppendTextAction(String filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return "Append a string on a file\n file:" + filePath + ", message=" + message;
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        // Use try-with-resources to automatically close the PrintWriter and handle exceptions
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            pw.append(message);
        } catch (IOException ex) {
            System.err.println("Error while adding the string to the file: " + filePath);
            ex.printStackTrace();
        }
    }
}
