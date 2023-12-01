package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileChecker;
import it.unisa.diem.actions.FileAction.DeleteFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class DeleteFileActionController extends FileChecker implements AbstractActionController{
    private File file;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label fileChoosenId;

    //method to handle the file choosing process by clicking the button "Choose a File". The method will also check the file lenght to ensure that there are no overlapping texts on the UI.
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
    //Disable the button until the Confirm button of the Alert is pressed. The button will be disabled if the alert is closed without pressing Confirm
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
    
}

