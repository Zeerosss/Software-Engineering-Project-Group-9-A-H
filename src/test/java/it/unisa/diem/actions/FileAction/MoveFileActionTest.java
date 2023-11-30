package it.unisa.diem.actions.FileAction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

// Test to check if a file has left the sourcePath correctly. The file is deleted after the check. 
//The test has been run with Visual Studio Code which has been started with Admin privileges 
public class MoveFileActionTest {
    @Test
    public void fileSourceTest() {
    File tempFile=null;
    try{
        tempFile = File.createTempFile("test", ".txt");
    }catch(IOException e){
        System.err.println("Error in creating the temp file");
    }
    String destinationPathString="C:/";
    MoveFileAction action=new MoveFileAction(tempFile, destinationPathString);

    Path sourcePath=tempFile.toPath();
    Path destinationPath=Paths.get(destinationPathString, tempFile.getName());

    try{action.startAction();
    }catch(Exception e){
        System.err.println("Error when moving the file");
    }

    assertFalse(Files.exists(sourcePath));
    


    try{Files.delete(destinationPath);
    }catch(IOException e){
        System.err.println("Error when deleting the tempfile");
    }
}

    //Test to check if a moved file has reached the destination Path. The file is deleted after the check. 
    //The test has been runned with Visual Studio Code which has been started with Admin privileges 
    @Test
    public void fileDestinationTest() {
    File tempFile=null;
    try{
        tempFile = File.createTempFile("test", ".txt");
    }catch(IOException e){
        System.err.println("Error in creating the temp file");
    }
    String destinationPathString="C:/";
    MoveFileAction action=new MoveFileAction(tempFile, destinationPathString);

    Path destinationPath=Paths.get(destinationPathString, tempFile.getName());

    try{action.startAction();
    }catch(Exception e){
        System.err.println("Error when moving the file");
    }

    assertTrue(Files.exists(destinationPath));
    


    try{Files.delete(destinationPath);
    }catch(IOException e){
        System.err.println("Error when deleting the tempfile");
    }
}




}
