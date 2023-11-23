package it.unisa.diem.actions.AudioFileAction;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

        //we can use some Asserts here if necessary
    }

    
    @Test
    public void testPlayNonexistentAudioFile() {
        PlayAudioFileAction audioAction = new PlayAudioFileAction("src\\main\\resources\\it\\unisa\\diem\\song01.wav");
            audioAction.startAction();
    
            // Redirect System.out for testing purposes
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Start the action
        audioAction.startAction();

        // Add an assertion to check if the appropriate message is printed
        assertTrue(outContent.toString().contains("File does not exist or is not readable"));

        // Reset System.out
        System.setOut(System.out);// Add assertions or checks to verify that appropriate messages or actions are taken
        }
    }
