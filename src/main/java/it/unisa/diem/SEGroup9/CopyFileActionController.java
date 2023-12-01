package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileChecker;
import it.unisa.diem.actions.FileAction.CopyFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class CopyFileActionController extends FileChecker implements AbstractActionController {
    private File file;
    private File directory;
    
    @FXML
    private Label directoryChoosenId;

    @FXML
    private Label fileChoosenId;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button chooseDirectoryButton;

    //This list contains Path that can be dangerous to work with, so it will be impossible to copy file from or to these directories.


    //method to handle the file choosing process by clicking the button "Choose a File". The method will also check the file lenght to ensure that there are no overlapping texts on the UI.
    @FXML
    private void chooseFile(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose a file to copy");

        file = fileChooser.showOpenDialog(App.getStage());
        if (file != null) {
            String fileName = file.getName();
            if (fileName.length() > 15) {
                fileName = fileName.substring(0, 15)+"...";
            }
            fileChoosenId.setText(fileName);
            }
        }

    //method to handle the directory choosing process by clicking the button "Choose a Directory"
    @FXML
    private void chooseDirectory(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");

        directory = directoryChooser.showDialog(App.getStage());
        if (directory != null) {
                directoryChoosenId.setText(directory.getName());
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Buttons are set on disabled until you confirm the Alert below. If you close the alert without confirming it,
        //the buttons will still be inactive and you need to change action.
        chooseFileButton.setDisable(true);
        chooseDirectoryButton.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Caution: This action works on files.");
        alert.setContentText("Press Confirm to go forward");
        Optional<ButtonType> result = alert.showAndWait();    

        if(result.isPresent() && result.get() == ButtonType.OK){
            chooseFileButton.setDisable(false);
            chooseDirectoryButton.setDisable(false);
        }

    }

    @Override
    public Action createAction(){
        if(isFilled()){
            return new CopyFileAction(file, directory.getPath());
        }else{ 
            return null;
        } 
    }
    @Override
    public boolean isFilled() {
        if (file != null && directory != null && !unavailableDirectory(directory.getPath()) && !unavailableFile(file)) {
                return true;
            }
        return false;
    }
    

}