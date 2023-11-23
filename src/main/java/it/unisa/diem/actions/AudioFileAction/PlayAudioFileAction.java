package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unisa.diem.actions.Action;

public class PlayAudioFileAction implements Action {

    private String filepath;

    // Runnable to be executed at the end of playback
    private Runnable atEnd;

    public PlayAudioFileAction(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void startAction() {
        // Create a File object from the specified path
        File audioFile = new File(filepath);

        // Check if the file exists and can be read
        if (audioFile.exists() && audioFile.canRead()) {
            try {
                // Obtain an audio input stream from the file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                // Create a Clip object for audio playback
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                // Add a listener to handle the end of playback event
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent evt) {
                        if (evt.getType().equals(LineEvent.Type.STOP)) {
                            // Execute the Runnable at the end of playback
                            Runnable r = getAtEndRunnable();
                            if (r != null) {
                                r.run();
                            }
                        }
                    }
                });
                // Close the audio input stream
                audioStream.close();
            }  catch (LineUnavailableException exc) {
            throw new RuntimeException("Sorry. Cannot play audio files.");
            }  catch (UnsupportedAudioFileException exc) {
            throw new RuntimeException("Unsupported file format for: "+audioFile);
            }  catch (FileNotFoundException exc) {
            throw new RuntimeException("File not found: "+audioFile);
            }  catch (IOException exc) {
            throw new RuntimeException("IOException: "+exc);
           }
            }
        } 
    

    // Private method to get the Runnable to be executed at the end of playback
    private synchronized Runnable getAtEndRunnable() {
        return atEnd;
    }

    // Method to add a listener for the end of playback
    public void addEndListener(Runnable listener) {
        this.atEnd = listener;
    }
}
