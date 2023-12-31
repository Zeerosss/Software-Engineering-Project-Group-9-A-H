package it.unisa.diem.triggers.ProgramExitStatus;

import java.io.IOException;
import java.util.Arrays;

import it.unisa.diem.rules.Observer;
import it.unisa.diem.triggers.Trigger;


/**
 * The ProgramExitStatusTrigger class is a trigger that monitors the exit status of a program.
 * It implements the Trigger interface and the Observer interface.
 */
public class ProgramExitStatusTrigger implements Trigger, Observer {

    private Integer exitStatus = null;
    private ExecProgram execProgram;
    private String path;
    private String[] args;

    /**
     * Constructor that initializes the path and args fields and creates an instance of the ExecProgram class.
     * @param path The path to the program.
     * @param args The arguments to be passed to the program.
     * @throws IOException
     */
    public ProgramExitStatusTrigger(String path, String args) throws IOException{
        this.path = path;
        this.args = args.split(",");
       
        execProgram = new ExecProgram(path, this.args, this);
        
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
        return "Exit Status Trigger, program:" + path + "\nargs:" + Arrays.asList(args).toString();
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