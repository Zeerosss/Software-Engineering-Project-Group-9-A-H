package it.unisa.diem.actions.FileActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class CopyFileActionTest {
    
    @Test
    // Test to check if a file is correctly copied and exists in the selected destination Path. After the check, the file is deleted.
    //using createTempDirectory, I can create a temporary directory to be able to run tests on both mac and windows without the needs to specify a OS Path. 
    //In a previous github release I used only a windows compatible test using C:\
    public void copyActionTest() throws IOException {
        File tempFile= File.createTempFile("test", ".txt");
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");

        CopyFileAction copyAction = new CopyFileAction(tempFile, tempDirectory.toString());
        copyAction.startAction();
        
        Path filePath = Paths.get(tempDirectory.toString(), tempFile.getName());
        assertTrue(Files.exists(filePath));

        try {
            Files.delete(tempFile.toPath());
            Files.delete(filePath);
            Files.delete(tempDirectory);
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }

    @Test
    //Test to check if an existing file get overwritten correctly. The file is deleted at the end of the test
    public void copyExistingFileTest() throws IOException{
        File tempFile= File.createTempFile("filetemp", ".txt");
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");

        CopyFileAction copyFileAction = new CopyFileAction(tempFile, tempDirectory.toString());
        copyFileAction.startAction();

        Path destinationPath2=Paths.get(tempDirectory.toString(), tempFile.getName());
        copyFileAction.startAction();

        assertTrue(Files.exists(destinationPath2));
        
        try{
            Files.delete(tempFile.toPath());
            Files.delete(destinationPath2);
            Files.delete(tempDirectory);
            
        }catch(IOException e){
            System.err.println("Error in deleting the temp file");
        }

    }
        @Test
    public void toStringTest() throws IOException{
        File tempFile = File.createTempFile("example",".txt");
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");
        CopyFileAction action=new CopyFileAction(tempFile, tempDirectory.toString());
        String expectedString = "Copy the file: " + tempFile.getName() + "\nInto the directory: " + Paths.get(tempDirectory.toString(),tempFile.getName());
        assertEquals(expectedString, action.toString());

        try{
            Files.delete(tempFile.toPath());
            Files.delete(tempDirectory);
        }catch(IOException e){
            System.err.println("Error in deleting the temp file");
        }
    }

}