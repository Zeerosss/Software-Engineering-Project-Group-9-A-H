package it.unisa.diem.actions.FileAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import it.unisa.diem.actions.Action;

public class MoveFileAction implements Action{
    private File file;
    private Path destinationPath;
    public MoveFileAction(File file,String destinationPath){
        this.file=file;
        this.destinationPath=Paths.get(destinationPath,file.getName());
    }

    @Override
    public void startAction(){
        try{Files.move(file.toPath(), destinationPath);
        }catch(IOException e){
            if(Files.exists(destinationPath)){
                System.err.println("File already moved!");
            }else{
            System.err.println("An error occurred while moving the file:"+e.getMessage());
        }}
    }
    
    @Override
    public String toString(){
        return "Move the File:"+file.getName()+ "\n to:"+ destinationPath;
    }
}
