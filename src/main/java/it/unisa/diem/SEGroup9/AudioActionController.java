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

    // Original file name (used for preserving the label when the file is not changed)
    private String originalFileName;

    @FXML
    private Button chooseFileButton;

    @FXML
    private AnchorPane fileChooserPane;

    @FXML
    private Label audioFilePathLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the FileChooser, but don't show the dialog immediately
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Audio Files (*.wav, *.mp3)", "*.wav", "*.mp3");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");
    }

    @FXML
    void chooseFile(ActionEvent event) {
        // Save the original file name
        originalFileName = (file != null) ? file.getName() : "";

        File newFile = fileChooser.showOpenDialog(App.getStage());

        // Update the label only if a valid file is chosen
        if (newFile != null && newFile.exists()) {
            file = newFile;
            audioFilePathLabel.setText("Chosen file: " + file.getName());
        }
        // Otherwise, leave the label unchanged
    }

    // Create an Action based on user input
    @Override
    public Action createAction() {
        // Check if the file is selected and valid
        if (isFilled()) {
            try {
                // Create and return a PlayAudioFileAction based on the selected file
                return new PlayAudioFileAction(file.getPath(), new PlayAudioFileJavaFX());
            } catch (FileNotFoundException e) {
                showAlert("Error: File not found.");
                
            }
        }
        return null;
    }

    // Check if the necessary fields are filled
    @Override
    public boolean isFilled() {
        // Check if the file is selected
        if (file == null) {
            showAlert("Please choose an audio file in the Audio Action section.");
            return false;
        }

        // Check if the file exists and is readable
        if (!file.exists()) {
            showAlert("The selected audio file does not exist. Please choose another file.");
            return false;
        }

        if (!file.canRead()) {
            showAlert("The selected audio file is not readable. Please choose another file.");
            return false;
        }

        return true;
    }

    // Show an alert with the specified message
    private void showAlert(String message) {
        AlertController.displayAlertWarning("Warning!",null , message);
    }
}
