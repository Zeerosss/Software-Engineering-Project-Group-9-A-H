package it.unisa.diem.actions.FileActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class DeleteFileActionTest {
    //Test to delete a common .txt file.
    @Test
    public void deleteFileTest() throws IOException {
        File tempFile = File.createTempFile("text",".txt");
        
        DeleteFileAction action=new DeleteFileAction(tempFile);
        Path sourcePath = tempFile.toPath();

        action.startAction();
        assertTrue(Files.notExists(sourcePath));
    }


    //Test to delete a file with .exe extension.
    @Test
    public void deleteDifferentExstensionTest() throws IOException{
        File tempFile = File.createTempFile("program", ".exe");

        DeleteFileAction action = new DeleteFileAction(tempFile);
        Path sourcePath = tempFile.toPath();

        action.startAction();
        assertTrue(Files.notExists(sourcePath));
        
    }

    //Test to try the remove of a null File-> The test checks whether a NullPointerException is thrown correctly.
    @Test
    public void deleteNullFileTest(){
        File tempFile = null;
        DeleteFileAction action= new DeleteFileAction(tempFile);
        
        assertThrows(NullPointerException.class,action::startAction);

    }

        @Test
    public void toStringTest() throws IOException{
        File tempFile = File.createTempFile("example",".txt");
        DeleteFileAction action=new DeleteFileAction(tempFile);
        String expectedString = "Delete the file:\n" + tempFile.getName();
        assertEquals(expectedString, action.toString());

        try{
            Files.delete(tempFile.toPath());
        }catch(IOException e){
            System.err.println("Error in deleting the temp file");
        }
    }
}