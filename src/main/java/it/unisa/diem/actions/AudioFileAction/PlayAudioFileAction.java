package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unisa.diem.actions.Action;

public class PlayAudioFileAction implements Action {
    String name;
    private Clip clip;

    // Constructor that takes the path of the audio file
    public PlayAudioFileAction(String pathName) {
        this(new File(pathName));
    }
    // Constructor that takes a File object representing the audio file
    public PlayAudioFileAction(File file) {
        name = file.getName();
        try {
            clip = AudioSystem.getClip(); 
            InputStream is = new FileInputStream(file); 
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(file); 

            // Create an AudioInputStream from the InputStream
            AudioInputStream ais = new AudioInputStream(is, aff.getFormat(), aff.getByteLength()); 
            clip.open(ais); 

            // Add a LineListener to handle events on the Clip
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    // If the event type is STOP, drain the clip and close it
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.drain();
                        clip.close();
                    }
                }
            });
        } catch (LineUnavailableException exc) {
            throw new RuntimeException("Sorry. Cannot play audio files.");
        } catch (UnsupportedAudioFileException exc) {
            throw new RuntimeException("Unsupported file format for: " + file);
        } catch (FileNotFoundException exc) {
            throw new RuntimeException("File not found: " + file);
        } catch (IOException exc) {
            throw new RuntimeException("IOException: " + exc);
        }
    }

    // Start playing the audio when the action is started
    @Override
    public void startAction() {
        clip.start();
    }

    @Override
    public String toString() {
        return ("PLAY:"+name);
    }
    
}
