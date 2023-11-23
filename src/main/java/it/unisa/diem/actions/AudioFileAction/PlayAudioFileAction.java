package it.unisa.diem.actions.AudioFileAction;

import java.io.File;
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
    private Runnable atEnd;

    public PlayAudioFileAction(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void startAction() {
        File audioFile = new File(filepath);
        if (audioFile.exists() && audioFile.canRead()) {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                clip.addLineListener(new LineListener() { 
                    @Override
                    public void update(LineEvent evt) {
                        if (evt.getType().equals(LineEvent.Type.STOP)) {  
                            Runnable r = getAtEndRunnable();
                            if (r != null) {
                                r.run();
                            }
                        }
                    }
                });

                audioStream.close();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist or is not readable");
        }
    }

    private synchronized Runnable getAtEndRunnable() {
        return atEnd;
    }

    // Add a Listener for the end of the playback
    public void addEndListener(Runnable listener) {
        this.atEnd = listener;
    }
}
