package it.unisa.diem.triggers.ProgramExitStatus;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unisa.diem.rules.Observable;
import it.unisa.diem.rules.Observer;


/**
 * Represents a program that can be executed and observed for its exit status.
 * Extends the Observable class and implements the Serializable interface.
 */
public class ExecProgram extends Observable implements Serializable {
    private Integer exitStatus = null;
    String path;
    String[] args;

    /**
     * Constructs a new ExecProgram object with the specified path, arguments, and observer.
     * Initializes the path and args fields, and calls the restart method.
     * @param path The path to the program.
     * @param args The command line arguments for the program.
     * @param observer The observer to be notified when the program exits.
     * @throws IOException If an I/O error occurs.
     */
    public ExecProgram(String path, String[] args, Observer observer) throws IOException {
        this.path = path;
        this.args = args;
        restart();
        addObserver(observer);
    }

    /**
     * Returns the exit status of the program.
     * @return The exit status of the program.
     */
    public Integer getExitStatus() {
        return exitStatus;
    }

    /**
     * Restarts the program by creating a new process using the path and args fields.
     * Obtains the exit status of the process and stores it in the exitStatus field.
     * Notifies the observer of the program's exit.
     * @throws IOException If an I/O error occurs.
     */
    public void restart() throws IOException {
        List<String> command = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            command.add(path);
            command.addAll(Arrays.asList(args));}
        else{
             command.add("/bin/bash"); // Use bash to run the command on macOS
            command.add("-c");
            command.add(path + " " + String.join(" ", args));} // Combine path and args into a single command string
         processBuilder.command(command);
         Process process = processBuilder.start();
        new Thread(() -> {
            try {
                exitStatus = process.waitFor();
                notifyObserver();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
