package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileChecker;
import it.unisa.diem.actions.FileActions.CopyFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class CopyFileActionController extends FileChecker implements AbstractActionController {
    private File file;
    private File directory;
    
    @FXML
    private Label choosenFileID;

    @FXML
    private Label choosenDirectoryID;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button chooseDirectoryButton;

    // This method handles the file choosing process by clicking the "Choose a File" button.
    // It also checks the file length to ensure that there are no overlapping texts on the UI.
    @FXML
    private void chooseFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file to copy");

        file = fileChooser.showOpenDialog(App.getStage());
        if (file != null) {
            String fileName = file.getName();
            if (fileName.length() > 20) {
                fileName = fileName.substring(0, 15) + "...";
            }
            choosenFileID.setText(fileName);
        }
    }

    // This method handles the directory choosing process by clicking the "Choose a Directory" button.
    @FXML
    private void chooseDirectory(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");

        directory = directoryChooser.showDialog(App.getStage());
        if (directory != null) {
            choosenDirectoryID.setText(directory.getName());
        }
    }

    // This method is called when the controller is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // This method creates and returns a CopyFileAction based on user input
    @Override
    public Action createAction(){
        if (isFilled()) {
            return new CopyFileAction(file, directory.getPath());
        } else { 
            return null;
        } 
    }

    // This method checks if the necessary fields are filled
    @Override
    public boolean isFilled() {
        if (file != null && directory != null){
            if(!unavailableDirectory(directory.getPath()) && !unavailableFile(file)) {
            return true;
        }else{
            if(unavailableDirectory(directory.getPath())){
                AlertController.displayAlertWarning("Warning!",null , "Directory cannot be selected!");
            }
            if(unavailableFile(file)){
                AlertController.displayAlertWarning("Warning!",null , "File Cannot be copied!");
            }
        }
    }else{
        if(file == null){
            AlertController.displayAlertWarning("Warning!",null , "File not selected");
        }
        if(directory == null){
            AlertController.displayAlertWarning("Warning!",null , "Directory not selected");
        }
    }
        return false;
}
}
