package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.FileDimensionExceeds.FileSizeExceedsTrigger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class FileSizeExceedsController implements AbstractTriggerController {

    private File selectedFile;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label chosenFileID;

    @FXML
    private Spinner<String> unitSpinner;

    @FXML
    private TextField dimensionTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the Spinner with units of measure
        unitSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList("byte", "kilobyte", "megabyte", "gigabyte")));
    }

    @FXML
    void chooseFile(ActionEvent event) {
        // Open a FileChooser dialog to choose a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");

        // Get the selected file
        File newSelectedFile = fileChooser.showOpenDialog(null);
        if (newSelectedFile != null) {
            // Update the selectedFile and label with the chosen file information
            selectedFile = newSelectedFile;
            chosenFileID.setText("Chosen file: " + selectedFile.getName());
        }
    }

    @Override
    public Trigger createTrigger() {
        // Check if a file is selected
        if (selectedFile != null) {
            // Verify if the selected file still exists before creating the trigger
            if (!selectedFile.exists()) {
                showAlert("The selected file no longer exists. Please choose another file.");
                return null;
            }

            // Get the selected unit and file dimension value
            String selectedUnit = unitSpinner.getValue();
            String valueText = dimensionTextField.getText();

            // Check if the file dimension value is provided
            if (!valueText.isEmpty()) {
                try {
                    // Parse the file dimension value to a double
                    double value = Double.parseDouble(valueText);
                    double maxSize = convertToBytes(value, selectedUnit);

                    // Check if the file dimension is a positive number
                    if (maxSize > 0) {
                        return new FileSizeExceedsTrigger(selectedFile.getAbsolutePath(), maxSize);
                    } else {
                        showAlert("Please enter a positive number for file dimension.");
                    }
                } catch (NumberFormatException e) {
                    showAlert("Please enter a valid number for file dimension. If you are using a decimal number, please use the dot (.) instead of the comma (,)");
                }
            } else {
                showAlert("Please enter a value for file dimension.");
            }
        } else {
            showAlert("Please choose a file in the File dimension Trigger section.");
        }
        return null;
    }

    // Show an alert with the specified message
    private void showAlert(String message) {
        AlertController.displayAlertWarning("Warning!",null , message);
    }

    // Convert the file dimension value to bytes based on the selected unit
    private double convertToBytes(double value, String unit) {
        switch (unit) {
            case "byte":
                return value;
            case "kilobyte":
                return value * 1024;
            case "megabyte":
                return value * 1024 * 1024;
            case "gigabyte":
                return value * 1024 * 1024 * 1024;
            default:
                return value;
        }
    }
}
