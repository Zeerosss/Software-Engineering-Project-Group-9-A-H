package it.unisa.diem.SEGroup9;

import java.io.File;
import java.io.IOException;
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
        
        fileChooser.setTitle("Select an external program");

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
        if(isFilled())
            try {
                return new ProgramExitStatusTrigger(file.getPath(),inputField.getText());
            } catch (IOException e) {
                AlertController.displayAlertWarning("Warning!",null,"Error in the external program");
            }
            
        return null;
    }

    public boolean isFilled() {
        if(file==null || inputField.getText().isEmpty()) 
        return(false);
        return(true);
    }
    
}
