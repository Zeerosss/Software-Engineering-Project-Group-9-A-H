package it.unisa.diem.actions.FileAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import it.unisa.diem.actions.Action;

public class DeleteFileAction implements Action {
    private File file;
    
    public DeleteFileAction(File file){
        this.file=file;
    }
    @Override
    public void startAction() {
        try{Files.delete(file.toPath());
        }catch(IOException e){
            if(Files.notExists(file.toPath())){
                System.err.println("File doesn't exists or has been already deleted!");
            }else{
            System.err.println("Error when deleting the file");}
        }
    
}

    @Override
    public String toString(){
        return "Delete the file: "+ file.getName();
    }
}