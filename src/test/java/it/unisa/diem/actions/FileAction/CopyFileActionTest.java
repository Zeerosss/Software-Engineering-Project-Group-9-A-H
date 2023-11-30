package it.unisa.diem.actions.FileAction;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Test;


public class CopyFileActionTest {
    
    @Test
    // Test to check if a file is correctly copied and exists in the selected destination Path. After the check, the file is deleted.
    public void copyActionTest() {
       File tempFile = null;
        try {
            tempFile = File.createTempFile("test", ".txt");
        } catch (IOException e) {
            System.err.println("Error in creating the temp file");
        }

        String destinationPath = "C:/"; 

        CopyFileAction copyAction = new CopyFileAction(tempFile, destinationPath);

        copyAction.startAction();

        
        Path copiedFilePath = Paths.get(destinationPath, tempFile.getName());
        assertTrue(Files.exists(copiedFilePath));

        try {
            Files.delete(copiedFilePath);
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }

    @Test
    //Test to check if an existing file get overwritten correctly. The file is deleted at the end of the test
    public void copyExistingFileTest() throws IOException{
        File tempFile= null;
        try{
            tempFile = File.createTempFile("filetemp", ".txt");}
        catch(IOException e){
            System.err.println("Error in creating the temp file");
         }
        String destinationPath = "C:/"; 
        CopyFileAction copyFileAction = new CopyFileAction(tempFile, destinationPath);
        copyFileAction.startAction();

        Path destinationPath2=Paths.get(destinationPath, tempFile.getName());
        copyFileAction.startAction();
        assertTrue(Files.exists(destinationPath2));
        
        try{
            Files.delete(destinationPath2);
        }catch(IOException e){
            System.err.println("Error in deleting the temp file");
        }

    }

}