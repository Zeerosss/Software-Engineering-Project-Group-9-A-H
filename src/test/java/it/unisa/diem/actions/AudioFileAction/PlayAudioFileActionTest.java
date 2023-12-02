package it.unisa.diem.actions.AudioFileAction;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class PlayAudioFileActionTest {  //this test checks for the correct reproduction of the audio file

    @Test
     public void testFileNotFound() {
        String nonExistingFilePath = "path/to/non-existing-audio-file.wav";
        assertThrows(FileNotFoundException.class, () -> createPlayAudioFileAction(nonExistingFilePath));
    }

    private PlayAudioFileAction createPlayAudioFileAction(String filePath) throws FileNotFoundException {
        return new PlayAudioFileAction(filePath, new PlayAudioFileJavaFX());
    }
}
