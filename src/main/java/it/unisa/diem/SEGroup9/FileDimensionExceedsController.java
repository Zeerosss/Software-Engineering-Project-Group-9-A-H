package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.FileDimensionExceeds.FileDimensionExceedsTrigger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class FileDimensionExceedsController implements AbstractTriggerController {

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
        // Inizializza Spinner con le unità di misura
        unitSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList("byte", "kilobyte", "megabyte", "gigabyte")));
    }

    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");

        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            chosenFileID.setText("Chosen file: " + selectedFile.getName());
        }
    }

    @Override
    public Trigger createTrigger() {
        if (selectedFile != null) {
            String selectedUnit = unitSpinner.getValue();
            String valueText = dimensionTextField.getText();

            if (!valueText.isEmpty()) {
                try {
                    double value = Double.parseDouble(valueText);
                    double maxSize = convertToBytes(value, selectedUnit);

                    if (maxSize > 0) {
                        return new FileDimensionExceedsTrigger(selectedFile.getAbsolutePath(), maxSize);
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
            showAlert("Please choose a file.");
        }
        return null;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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
