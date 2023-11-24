package it.unisa.diem.actions.AudioFileAction;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class PlayAudioFileActionTest {  //this test checks for the correct reproduction of the audio file

    @Test
     public void testFileNotFound() {
        String nonExistingFilePath = "path/to/non-existing-audio-file.wav";
        assertThrows(RuntimeException.class, () -> createPlayAudioFileAction(nonExistingFilePath));
    }

    private PlayAudioFileAction createPlayAudioFileAction(String filePath) {
        File file = new File(filePath);
        return new PlayAudioFileAction(file);
    }

}
