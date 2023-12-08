package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.FileExists.FileExistsTrigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class FileExistsController implements AbstractTriggerController{

    private File directory;
    
    @FXML
    private Button chooseDirectoryButton;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private Label chosenDirectoryID;

    @FXML
    private Label chosenFileID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {      

    }
    @FXML
    private void chooseFile(ActionEvent event){
        String fileName = fileNameTextField.getText();
        if (!fileName.isEmpty()) {
            if (fileName.length() > 15) {
                fileName = fileName.substring(0, 15) + "...";
            }
            chosenFileID.setText(fileName);
        }
    }

    // This method handles the directory choosing process by clicking the "Choose a Directory" button.
    @FXML
    private void chooseDirectory(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");

        directory = directoryChooser.showDialog(App.getStage());
        if (directory != null) {
            chosenDirectoryID.setText("Chosen Directory: " + directory.getName());
        }
    }
    @Override
    public Trigger createTrigger() {
        if (directory != null && getFileName() != null && !getFileName().isEmpty()) {
            String directoryPath = directory.getAbsolutePath();
            String fileName = getFileName();
            return new FileExistsTrigger(directoryPath, fileName);
        } else {
            // if necessary
            return null;
        }
    }
    
    private String getFileName() {
        return fileNameTextField.getText();
    }
}
