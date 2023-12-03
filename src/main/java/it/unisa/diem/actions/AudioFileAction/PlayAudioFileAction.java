package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
import java.io.FileNotFoundException;

import it.unisa.diem.actions.Action;


public class PlayAudioFileAction implements Action {
    private String audioFilePath;
    private PlayAudioFile playSound;


    // Constructor that takes the path of the audio file
    public PlayAudioFileAction(String audioFilePath, PlayAudioFile playSound) throws FileNotFoundException {
        if (!(new File(audioFilePath)).exists()) {
                throw new FileNotFoundException("File not found: " + audioFilePath);
            }
        this.audioFilePath = audioFilePath;
        this.playSound = playSound;
    }
    
    @Override
    public String toString() {
        return "Play this file \nfile:" + audioFilePath;
    }

    @Override
    public void startAction() {
        playSound.StartSound(audioFilePath);
         }
}
