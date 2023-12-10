package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileChecker;
import it.unisa.diem.actions.FileActions.DeleteFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;

public class DeleteFileActionController extends FileChecker implements AbstractActionController {
    private File file;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label choosenFileID;

    // This method handles the file choosing process by clicking the "Choose a File" button.
    // It also checks the file length to ensure that there are no overlapping texts on the UI.
    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file to delete");
        file = fileChooser.showOpenDialog(App.getStage());
        if (file != null) {
            String fileName = file.getName();
            if (fileName.length() > 15) {
                fileName = fileName.substring(0, 15) + "...";
            }
            choosenFileID.setText(fileName);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // This method creates and returns a DeleteFileAction based on user input
    @Override
    public Action createAction() {
        if (isFilled()) {
            return new DeleteFileAction(file);
        } else {
            return null;
        }
    }

    // This method checks if the necessary fields are filled
    @Override
    public boolean isFilled() {
        if (file != null){
            if(!unavailableFile(file)) {
            return true;
        }else{
            AlertController.displayAlertWarning("Warning!",null , "File not available");
        }
        
    }else{
        AlertController.displayAlertWarning("Warning!",null , "File not selected");
    }
    return false;
}
}
