package it.unisa.diem.SEGroup9;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileAction;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileJavaFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class AudioActionController implements AbstractActionController {
    
    private FileChooser fileChooser;
    private File file;

    @FXML
    private Button changeFileButton;
    
    @FXML
    private AnchorPane fileChooserPane;
    
    @FXML
    private Label audioFilePathLabel;

    // This method is called when the controller is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a FileChooser to allow the user to select an audio file (WAV or MP3)
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Audio Files (*.wav, *.mp3)", "*.wav", "*.mp3");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");

        // Show the file chooser dialog and get the selected file
        file = fileChooser.showOpenDialog(App.getStage());

        if (file != null) {
            audioFilePathLabel.setText("Chosen file: " + file.getName());
        }
    }

    // Create and return a PlayAudioFileAction based on the selected file
    @Override
    public Action createAction() {
        // If the file is selected, create a PlayAudioFileAction with the file path
        if (this.isFilled()) {
            try {
                return new PlayAudioFileAction(file.getPath(), new PlayAudioFileJavaFX());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    // Event handler method for the changeFileButton
    @FXML
    void changeFile(ActionEvent event) {
        // Show the file chooser dialog and update the label with the name of the chosen file
        file = fileChooser.showOpenDialog(App.getStage());
        audioFilePathLabel.setText("");
        if (file != null) {
            audioFilePathLabel.setText("Chosen file: " + file.getName());
        }
    }

    // Check if the file is selected
    @Override
    public boolean isFilled() {
        return file != null;
    }
}
