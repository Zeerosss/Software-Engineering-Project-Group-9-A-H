package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileAction.DeleteFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class DeleteFileActionController implements AbstractActionController{
    private File file;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label fileChoosenId;


    //This list contains Path that can be dangerous to work with, so it will be impossible to copy file from or to these directories.
    private  final List<String> sensitiveDirectories = List.of(
    "C:\\Windows",
    "C:\\Program Files",
    "C:\\Program Files (x86)",
    "/System",
    "/Applications",
    "/private");


    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose a file to copy");
        file= fileChooser.showOpenDialog(App.getStage());
        if (file!=null){
            String fileName=file.getName();
            if(fileName.length()>15){
                fileName=fileName.substring(0, 15)+"...";
            }
            fileChoosenId.setText(fileName);
            }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseFileButton.setDisable(true);

        Alert alert= new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Caution: This action is irreversible!");
        alert.setContentText("Press Confirm to continue or change the selected action");

        Optional<ButtonType> result= alert.showAndWait();    
        if(result.isPresent() && result.get()==ButtonType.OK){
            chooseFileButton.setDisable(false);
        }
        
    }

    @Override
    public Action createAction() {
        if(isFilled()){
            return(new DeleteFileAction(file));
        }else{return null;}

    }

    @Override
    public boolean isFilled() {
        if (file != null && !unavailableFile(file)) {
                return true;
            }
        return false;
    }

   //check if the path of the file is acceptable or not by using the stream.anyMatch to see if any item starts with the path choosen.
    private boolean unavailableFile(File file) {
        String filePath = file.getAbsolutePath();
        return sensitiveDirectories.stream().anyMatch(filePath::startsWith);
    
}
    
}

