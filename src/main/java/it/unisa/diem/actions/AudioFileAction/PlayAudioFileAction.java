package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileNotFoundException;

import it.unisa.diem.actions.Action;

public class PlayAudioFileAction implements Action {
    private String audioFilePath;
    private PlayAudioFile playSound;

    // Constructor that takes an audio file path and a PlayAudioFile implementation
    // Throws FileNotFoundException if the specified file does not exist
    public PlayAudioFileAction(String audioFilePath, PlayAudioFile playSound) throws FileNotFoundException {
        if (!(new File(audioFilePath)).exists()) {
            throw new FileNotFoundException("File not found: " + audioFilePath);
        }
        this.audioFilePath = audioFilePath;
        this.playSound = playSound;
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return "Play this file:\n" + audioFilePath;
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        playSound.StartSound(audioFilePath);
    }
}
