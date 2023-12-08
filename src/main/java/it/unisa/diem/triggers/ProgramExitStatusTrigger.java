package it.unisa.diem.triggers;

import java.io.IOException;

import it.unisa.diem.rules.Observer;


/**
 * The ProgramExitStatusTrigger class is a trigger that monitors the exit status of a program.
 * It implements the Trigger interface and the Observer interface.
 */
public class ProgramExitStatusTrigger implements Trigger, Observer {

    private Integer exitStatus = null;
    private ExecProgram execProgram;
    private String path;
    private String args;

    /**
     * Constructor that initializes the path and args fields and creates an instance of the ExecProgram class.
     * @param path The path to the program.
     * @param args The arguments to be passed to the program.
     */
    public ProgramExitStatusTrigger(String path, String args){
        this.path = path;
        this.args = args;
        try {
            execProgram = new ExecProgram(path, args, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the exit status different than or equal to 0.
     * @return true if the exit status is not null, false otherwise.
     */
    @Override
    public boolean isValidated() {
        if(exitStatus!=null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the exit status by calling the getExitStatus() method of the execProgram instance.
     */
    @Override
    public void update() {
        exitStatus = execProgram.getExitStatus();
      
    }

    /**
     * Restarts the program by calling the restart() method of the execProgram instance.
     */
    @Override
    public void startTrigger() {
        try {
            execProgram.restart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return A string representation of the ProgramExitStatusTrigger object.
     */
    @Override
    public String toString() {
        return "Exit Status Trigger\nprogram:" + path + "\nargs:" + args;
    }

    /**
     * Checks if the exit status is not equal to 0.
     * @return true if the exit status is not equal to 0, false otherwise.
     */
    @Override
    public Boolean isFailed() {
        if(exitStatus!=null) {
        if(exitStatus != 0) {
            return true;
        }}
        return false;

        
    }
}