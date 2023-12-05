package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

public class PlayAudioFileJavaFX implements PlayAudioFile {

    // Override the StartSound method as required by the PlayAudioFile interface
    @Override
    public void StartSound(String audioFilePath) {
        try {
            // Create a File object representing the audio file using the provided file path
            File audioFile = new File(audioFilePath);

            // Check if the file exists, throw FileNotFoundException if not
            if (!audioFile.exists()) {
                throw new FileNotFoundException("File not found: " + audioFilePath);
            }

            // Create a Media object using the audio file's URI
            Media media = new Media(audioFile.toURI().toString());

            // Create a MediaPlayer object using the Media object
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Play the media using the MediaPlayer
            mediaPlayer.play();

        } catch (FileNotFoundException e) {
            System.err.println("Exception: " + e.getMessage());
        } catch (MediaException e) {
            System.err.println("Exception: " + e.getMessage());
        }   
    }
}
