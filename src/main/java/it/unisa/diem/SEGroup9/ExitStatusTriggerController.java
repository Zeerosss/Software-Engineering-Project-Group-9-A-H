package it.unisa.diem.SEGroup9;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.ProgramExitStatus.ProgramExitStatusTrigger;
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
    private Label fileLabel;

    @FXML
    private TextField inputField;

    @FXML
    private void chooseFile(ActionEvent event) {
        File newSelectedFile = fileChooser.showOpenDialog(App.getStage());
        if (newSelectedFile != null) {
            file = newSelectedFile;
            fileLabel.setText("Chosen file: " + file.getName());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a FileChooser to allow the user to select an external program
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select an external program");
    }

    @Override
    public Trigger createTrigger() {
        if(isFilled())
            try {
                return new ProgramExitStatusTrigger(file.getPath(),inputField.getText());
            } catch (IOException e) {
                AlertController.displayAlertWarning("Warning!",null,"Error in the external program");
            }
            
        return null;
    }

    public boolean isFilled() {
        return file!=null;
    }
    
}
