package it.unisa.diem.actions.AudioFileAction;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import it.unisa.diem.actions.AppendTextAction.AppendTextAction;

public class PlayAudioFileActionTest {  //this test checks for the correct reproduction of the audio file

    @Test
    public void testFileNotFound() {
        String nonExistingFilePath = "path/to/non-existing-audio-file.wav";
        assertThrows(FileNotFoundException.class, () -> createPlayAudioFileAction(nonExistingFilePath));
    }

    private PlayAudioFileAction createPlayAudioFileAction(String filePath) throws FileNotFoundException {
        return new PlayAudioFileAction(filePath, new PlayAudioFileJavaFX());
    }

    @Test
    public void testToString() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("file", ".wav");

        PlayAudioFileAction trigger = new PlayAudioFileAction(tempFile.getPath(), new PlayAudioFile() {

            @Override
            public void StartSound(String audioFilePath) {
                
            }
            
        });

        // Create the expected string representation of the action
        String expectedString = "Play this file:\n" + tempFile.getPath();
        assertEquals(expectedString, trigger.toString());

        // Delete the temporary file after the test
        try {
            Files.delete(tempFile.toPath());
        } catch (IOException e) {
            System.err.println("Error in deleting the temp file");
        }
    }
}
