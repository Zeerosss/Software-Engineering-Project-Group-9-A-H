package it.unisa.diem.actions.AppendTextAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import it.unisa.diem.actions.Action;

public class AppendTextAction implements Action{
    public String filePath;
    public String message;

    public AppendTextAction(String filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }
    @Override
    public void startAction() {

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            pw.append(message);
        } catch (IOException ex) {
            System.err.println("Error while adding the string to the file: " + filePath);
            ex.printStackTrace();        }
    }
}
