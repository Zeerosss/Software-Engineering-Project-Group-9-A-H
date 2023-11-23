package it.unisa.diem.actions.AudioFileAction;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayAudioFileActionTest {  //this test checks for the correct reproduction of the audio file

    @Test
    public void testStartAction() {
        PlayAudioFileAction action = new PlayAudioFileAction("src\\main\\resources\\it\\unisa\\diem\\1171912250123104356.wav");

        // Add a Listener to know when the reproduction is Done
        action.addEndListener(() -> {
            System.out.println("Playback completed!");
            // ...
            synchronized (this) {
                notify(); // Notify the end of the Test
            }
        });

        action.startAction();

        // Wait for the end of the test
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //we can use some Asserts here if necessary !!
    }

    
    @Test
    public void testPlayNonexistentAudioFile() {
        PlayAudioFileAction audioAction = new PlayAudioFileAction("NotAsong.wav");
        audioAction.startAction();
        }


    @Test
    public void testAddEndListener() {
        
    }
    }
