package it.unisa.diem.actions.ExecuteProgramAction;

import java.io.File;
import java.io.IOException;

import java.util.List;

import it.unisa.diem.SEGroup9.AlertController;
import it.unisa.diem.actions.Action;

public class ExecuteExternalProgramAction implements Action{

    private File file;
    private List<String> parameterList;
    
    public ExecuteExternalProgramAction(File file, List<String> parameterList){
        this.file = file;
        // Adds the absolute path of the file as the first element in the parameterList list
        parameterList.add(0, file.getAbsolutePath());
        this.parameterList = parameterList;    
    }

    @Override
    public void startAction() {

        ProcessBuilder pb = new ProcessBuilder(parameterList);
        //reference to a file to redirect the output of the external program
        File f = new File("programResult\\" + file.getName() + ".txt");
    
        synchronized (f){
            try {
                pb.redirectOutput(ProcessBuilder.Redirect.appendTo(f));
                Process p = pb.start();
            } catch (IOException | NullPointerException | IndexOutOfBoundsException | SecurityException e) {
                AlertController.displayAlertWarning("Warning!",null , "An error occurred while executing the file!");
                }
            }     
        }       

    @Override
    public String toString() {
        return "Execute this external program:\n" + file;
    }

}
