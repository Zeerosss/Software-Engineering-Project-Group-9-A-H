package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileAction.CopyFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class CopyFileActionController implements AbstractActionController {
    private File file;
    private File directory;
    
    @FXML
    private Label directoryChoosenId;

    @FXML
    private Label fileChoosenId;

    @FXML
    private Button chooseFileButton;
    ;
    @FXML
    private Button chooseDirectoryButton;

    //method to handle the file choosing process by clicking the button "Choose a File"
    @FXML
    private void chooseFile(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose a file to copy");
        
        file= fileChooser.showOpenDialog(App.getStage());
        if (file!=null){
            fileChoosenId.setText(file.getName());
            }

    }

    //method to handle the directory choosing process by clicking the button "Choose a Directory"
    @FXML
    private void chooseDirectory(ActionEvent event){
        DirectoryChooser directoryChooser= new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");
        
        directory= directoryChooser.showDialog(App.getStage());
        if (directory!=null){
            directoryChoosenId.setText(directory.getName());
            }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Buttons are set on disabled untill you confirm the Alert below. If you close the alert without confirming it,
        //the buttons will be inactive anyways.
        chooseFileButton.setDisable(true);
        chooseDirectoryButton.setDisable(true);
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Alert!");
        alert.setHeaderText("Caution: This action works on files.");
        alert.setContentText("Press Confirm to go forward");
        Optional<ButtonType> result= alert.showAndWait();    
        if(result.isPresent() && result.get()==ButtonType.OK){
            chooseFileButton.setDisable(false);
            chooseDirectoryButton.setDisable(false);
        }
        
        //Check to see if both the fields are choosen and show the file name and directory through the use of labels 
        if(directory!=null || file!=null){
            fileChoosenId.setText(file.getName().substring(0,15));
            directoryChoosenId.setText(directory.getName());
        } else{
            fileChoosenId.setText("No file choosen!");
            directoryChoosenId.setText("No directory choosen!");
        }
        


    }

    @Override
    public Action createAction(){
        if(this.isFilled()){
            return (new CopyFileAction(file,directory.getPath()));
        }else{return null;}

    }
    @Override
    public boolean isFilled(){
        if(file!=null && directory !=null){
            return true;
        }else{ return false;}
    }


    
}