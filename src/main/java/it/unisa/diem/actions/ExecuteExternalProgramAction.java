package it.unisa.diem.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExecuteExternalProgramAction implements Action{

    private File file;
    private List<String> parameterList;

    
    public ExecuteExternalProgramAction(File file, List<String> parameterList){
        this.file = file;
        parameterList.add(0, file.getAbsolutePath());
        this.parameterList = parameterList;
        
    }

    @Override
    public void startAction() {
        
        ProcessBuilder pb = new ProcessBuilder(parameterList);
        File f = new File("programResoult");
        pb.redirectOutput(f);
        
        try {
            Process p = pb.start();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException | SecurityException e) {
            try(PrintWriter pw = new PrintWriter(new File("ErrorLog"))){
                pw.append("Error in program execution");
            } catch (FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
        } 
        
    }

    @Override
    public String toString() {
        return "ExecuteExternalProgramAction [file=" + file + "]";
    }

}
