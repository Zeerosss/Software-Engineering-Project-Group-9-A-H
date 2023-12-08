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
        File f = new File("programResult\\" + file.getName() + ".txt");
    
        synchronized (f){
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(f));
        
            try {
                Process p = pb.start();
            } catch (IOException | NullPointerException | IndexOutOfBoundsException | SecurityException e) {
                try(PrintWriter pw = new PrintWriter(new File("ErrorLog.txt"))){
                    pw.append("Error in program execution " + file.getAbsolutePath());
                } catch (FileNotFoundException fnfe){
                    fnfe.printStackTrace();
                }
            }     

        }
        
    }

    @Override
    public String toString() {
        return "ExecuteExternalProgramAction [file=" + file + "]";
    }

}
