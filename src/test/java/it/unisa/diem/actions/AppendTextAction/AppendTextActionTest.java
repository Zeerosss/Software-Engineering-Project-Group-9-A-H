package it.unisa.diem.actions.AppendTextAction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.Test;

public class AppendTextActionTest {

    // This test verifies that the AppendTextAction appends the given message to the file.
    @Test
    public void testStartAction() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("file", ".txt");

        try {
            AppendTextAction action = new AppendTextAction(tempFile.getPath(), "Hello World!");

            action.startAction();

            // Verify that the message "Hello World!" has been appended to the file
            try (Scanner scanner = new Scanner(tempFile)) {
                String content = scanner.useDelimiter("\\Z").next();
                assertEquals("Hello World!", content);
            } catch (IOException ex) {
                fail("Failed to read file");
            }
        } finally {
            // Close the temporary file and mark it for deletion at the end of the test
            tempFile.deleteOnExit();
        }
    }

    @Test
    public void testStartActionWithExistingContent() throws IOException {
        // Create a temporary file with existing content
        File tempFile = File.createTempFile("file", ".txt");

        try {
            // Write some existing content to the file
            Files.write(tempFile.toPath(), "Existing Content ".getBytes());

            AppendTextAction action = new AppendTextAction(tempFile.getPath(), "Hello World!");

            action.startAction();

            // Verify that the message "Hello World!" has been appended to the existing content
            try (Scanner scanner = new Scanner(tempFile)) {
                String content = scanner.useDelimiter("\\Z").next();
                assertEquals("Existing Content Hello World!", content);
            } catch (IOException ex) {
                fail("Failed to read file");
            }
        } finally {
            // Close the temporary file and mark it for deletion at the end of the test
            tempFile.deleteOnExit();
        }
    }


    // This test verifies the string representation of the AppendTextAction.
    @Test
    public void testToString() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("file", ".txt");

        AppendTextAction trigger = new AppendTextAction(tempFile.getPath(), "test");

        // Create the expected string representation of the action
        String expectedString = "Append this message: " + "test" + "\nTo this file: " + tempFile.getPath();
        assertEquals(expectedString, trigger.toString());

        // Delete the temporary file after the test
        try {
            Files.delete(tempFile.toPath());
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }
}
