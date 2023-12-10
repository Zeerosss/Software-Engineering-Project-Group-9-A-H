package it.unisa.diem.triggers.FileExists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;

public class FileExistsTriggerTest {

    // Returns true if the file exists and is a regular file
    @Test
    public void testIsValidated() throws IOException {
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");
        File tempFile = File.createTempFile("file", ".txt", tempDirectory.toFile());

        FileExistsTrigger trigger = new FileExistsTrigger(tempDirectory.toString(), tempFile.getName());
        boolean result = trigger.isValidated();
        assertTrue(result);
    }


    // Returns false if the file does not exist or is not a regular file
    @Test
    public void testIsNotValidated() {
        FileExistsTrigger trigger = new FileExistsTrigger("directory/path", "file.txt");
        boolean result = trigger.isValidated();
        assertFalse(result);
    }
    
    //toString testing
    @Test
    public void testToString() throws IOException {
        File tempFile = File.createTempFile("file",".txt");
        Path tempDirectory = Files.createTempDirectory("temporaryDirectory");
        FileExistsTrigger trigger = new FileExistsTrigger(tempDirectory.toString(), tempFile.getName());
        String expectedString = "FileExistsTrigger:\n" + "fileName=\n" + tempFile.getName() + "\nDirectory=\n" + tempDirectory.toString();
        assertEquals(expectedString, trigger.toString());
        try {
            Files.delete(tempFile.toPath());
            Files.delete(tempDirectory);
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }
}
