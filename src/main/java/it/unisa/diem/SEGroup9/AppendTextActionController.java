package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AppendTextAction.AppendTextAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AppendTextActionController implements AbstractActionController {

    private FileChooser fileChooser;
    private File file;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label fileLabel;

    @FXML
    private TextField messageField;

    // Initialization method for the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
    }

    // Event handler method for the chooseFileButton
    @FXML
    void chooseFile(ActionEvent event) {
        // Show the file chooser dialog and update the label with the name of the chosen file
        File newFile = fileChooser.showOpenDialog(App.getStage());
        if (newFile != null) {
            if (!newFile.equals(file)) {
                file = newFile;
                fileLabel.setText("Chosen file: " + file.getName());
            }
        }
    }

    // Create an AppendTextAction based on user input
    @Override
    public Action createAction() {
        if (this.isFilled() && file != null && file.exists()) {
            String message = messageField.getText();
            messageField.clear();
            return new AppendTextAction(file.getPath(), message);
        } else {
            if (file == null) {
                showAlert("Please choose a file in the Append action section.");
            } else if (!file.exists()) {
                showAlert("The selected file no longer exists. Please choose another file.");
            } else if (messageField.getText().isEmpty()) {
                showAlert("Please enter a message in the Append action section.");
            }
            return null;
        }
    }

    // Check if both the file and messageField are filled
    @Override
    public boolean isFilled() {
        return file != null && !messageField.getText().isEmpty();
    }

    // Show an alert with the specified message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
