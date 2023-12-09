package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.FileExists.FileExistsTrigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class FileExistsController implements AbstractTriggerController {

    private File directory;

    @FXML
    private Button chooseDirectoryButton;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private Label chosenDirectoryID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // This method handles the directory choosing process by clicking the "Choose a Directory" button.
    @FXML
    private void chooseDirectory(ActionEvent event) {
        // Open a DirectoryChooser dialog to choose a directory
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");

        // Get the selected directory
        File newSelectedDirectory = directoryChooser.showDialog(App.getStage());
        if (newSelectedDirectory != null) {
            // If the directory is selected, update the value only if the directory is different from the current one
            if (!newSelectedDirectory.equals(directory)) {
                directory = newSelectedDirectory;
                chosenDirectoryID.setText("Chosen Directory: " + directory.getName());
            }
        }
    }

    @Override
    public Trigger createTrigger() {
        // Check if a directory is selected
        if (directory != null) {
            // Verify if the selected directory still exists before creating the trigger
            if (!directory.exists()) {
                showAlert("The selected directory no longer exists. Please choose another directory.");
                return null;
            }

            // Continue with the trigger creation
            if (getFileName() != null && !getFileName().isEmpty()) {
                String directoryPath = directory.getAbsolutePath();
                String fileName = getFileName();
                return new FileExistsTrigger(directoryPath, fileName);
            } else {
                showAlert("Please enter a valid file name in the File exists Trigger section.");
                return null;
            }
        } else {
            showAlert("Please choose a directory in the File exists Trigger section.");
            return null;
        }
    }

    // Show an alert with the specified message
    private void showAlert(String message) {
        AlertController.displayAlertWarning("Warning!",null , message);
    }

    // Get the file name from the text field
    private String getFileName() {
        return fileNameTextField.getText();
    }
}
