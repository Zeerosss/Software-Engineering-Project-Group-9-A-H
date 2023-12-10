package it.unisa.diem.triggers.FileSizeExceeds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class FileSizeExceedsTriggerTest {
    
    @Test
    public void testIsValidated() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("file", ".txt");
        
        // Write data to the temporary file 
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        Files.write(tempFile.toPath(), data.getBytes(), StandardOpenOption.APPEND);

        long fileSizeInBytes = tempFile.length();
        System.out.println("The size of the text file is: " + fileSizeInBytes + " bytes");

        FileSizeExceedsTrigger trigger = new FileSizeExceedsTrigger(tempFile.getPath(), 50); //here you can change the max size of the file

        // Validate that the file size exceeds 50 bytes (in general, a specified amount of bytes decided by the user, it can be changed according to every need)
        // This test could even be named testIsNotValidated by providing a byte threshold smaller than the actual file size,
        // which would then result in a failure.
        boolean result = trigger.isValidated();
        assertTrue(result);

        // Delete the temporary file
        try {
            Files.delete(tempFile.toPath());
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }

    @Test
    public void testToString() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("file", ".txt");
        
        // Write data to the temporary file
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        Files.write(tempFile.toPath(), data.getBytes(), StandardOpenOption.APPEND);

        FileSizeExceedsTrigger trigger = new FileSizeExceedsTrigger(tempFile.getName(), 2);

        // Create the expected string representation of the trigger
        String expectedString = "FileSizeTrigger:\n" +
                                "fileName=\n" + tempFile.getName() + "\n" +
                                "sizeFile=\n" + 2.0;

        // Assert that the toString() method produces the expected string
        assertEquals(expectedString, trigger.toString());

        // Delete the temporary file
        try {
            Files.delete(tempFile.toPath());
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }
}
