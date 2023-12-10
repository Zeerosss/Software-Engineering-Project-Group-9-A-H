package it.unisa.diem.actions.FileActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import it.unisa.diem.actions.FileActions.MoveFileAction;

//Test to check if a file has left the sourcePath correctly. The file is deleted after the check. 
//The test has been run with Visual Studio Code which has been started with Admin privileges.
//using createTempDirectory, i can create a temporary directory to be able to run tests on both mac and windows without the needs to specify a OS Path.
//In a previous github release i used only a windows compatible test using the destination path "C:/"
public class MoveFileActionTest {
   @Test
    public void fileSourceTest() throws IOException {
    File tempFile=File.createTempFile("test", ".txt");
    Path tempDirectory = Files.createTempDirectory("temporaryDirectory");

    MoveFileAction action=new MoveFileAction(tempFile, tempDirectory.toString());

    Path sourcePath=tempFile.toPath();
    Path destinationPath=Paths.get(tempDirectory.toString(), tempFile.getName());


    try{action.startAction();
    }catch(Exception e){
        System.err.println("Error when moving the file");
    }

    assertFalse(Files.exists(sourcePath));
    


    try{
        Files.delete(tempDirectory);
        Files.delete(destinationPath);
    }catch(IOException e){
        System.err.println("Error when deleting the tempfile");
    }
}

    //Test to check if a moved file has reached the destination Path. The file is deleted after the check. 
    //The test has been runned with Visual Studio Code which has been started with Admin privileges 
    @Test
    public void fileDestinationTest() throws IOException {
    File tempFile=File.createTempFile("test", ".txt");
    Path tempDirectory = Files.createTempDirectory("temporaryDirectory");

    MoveFileAction action=new MoveFileAction(tempFile, tempDirectory.toString());
    Path destinationPath=Paths.get(tempDirectory.toString(), tempFile.getName());
    

    try{action.startAction();
    }catch(Exception e){
        System.err.println("Error when moving the file");
    }

    assertTrue(Files.exists(destinationPath));
    
    try{
        Files.delete(tempFile.toPath());
        Files.delete(tempDirectory);
    }catch(IOException e){
        System.err.println("Error when deleting the tempfile");
    }
}

    @Test
    public void toStringTest() throws IOException{
        File tempFile = File.createTempFile("example",".txt");
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");
        MoveFileAction action=new MoveFileAction(tempFile, tempDirectory.toString());
        String expectedString = "Move the File: " + tempFile.getName() + "\nInto the directory: " + Paths.get(tempDirectory.toString(),tempFile.getName());
        assertEquals(expectedString, action.toString());


        try{
            Files.delete(tempFile.toPath());
            Files.delete(tempDirectory);
        }catch(IOException e){
            System.err.println("Error in deleting the temp file");
        }
    }

}

