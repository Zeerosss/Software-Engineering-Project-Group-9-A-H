package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.ProgramExitStatusTrigger;
import it.unisa.diem.triggers.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ExitStatusTriggerController implements AbstractTriggerController{

    
    private FileChooser fileChooser;
    private File file;
    @FXML
    private Button chooseProgramButton;

    @FXML
    private TextField exitField;

    @FXML
    private Label fileLabel;

    @FXML
    private TextField inputField;

    @FXML
    void chooseFile(ActionEvent event) {
        file = fileChooser.showOpenDialog(App.getStage());
        fileLabel.setText("");
        if (file != null) {
            fileLabel.setText("Chosen file: " + file.getName());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     // Create a FileChooser to allow the user to select a txt file
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("external program (*.exe)", "*.exe");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");

        // Show the file chooser dialog and get the selected file
        file = fileChooser.showOpenDialog(App.getStage());
        // Remove the comment of the next line if you want to print the path of the chosen file to the console
       // System.out.println(file);

        // Update the label with the name of the chosen file if file is not null
        if (file != null) {
            fileLabel.setText("Chosen file: " + file.getName());
        }
    }
    @Override
    public Trigger createTrigger() {
        return new ProgramExitStatusTrigger();
    }
    
}
