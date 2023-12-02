package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

public class PlayAudioFileJavaFX implements PlayAudioFile{

    @Override
    public void StartSound(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);

            // Check if the file exists
            if (!audioFile.exists()) {
                throw new FileNotFoundException("File not found: " + audioFilePath);
            }

            // Media creation
            Media media = new Media(audioFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Playing the media
            mediaPlayer.play();

        } catch (FileNotFoundException e) {
            System.err.println("Exception: " + e.getMessage()); // File not found exception
        } catch (MediaException e) {
            System.err.println("Exception: " + e.getMessage()); // File not supported
        }   
    }
}
