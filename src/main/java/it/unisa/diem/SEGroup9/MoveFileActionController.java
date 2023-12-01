package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.FileAction.MoveFileAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MoveFileActionController implements AbstractActionController{
    private File file;
    private File directory;

    @FXML
    private Button chooseDirectoryButton;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label directoryChoosenId;

    @FXML
    private Label fileChoosenId;
//This list contains Path that can be dangerous to work with, so it will be impossible to move file from or to these directories.
    private  final List<String> sensitiveDirectories = List.of(
    "C:\\Windows",
    "C:\\Program Files",
    "C:\\Program Files (x86)",
    "/System",
    "/Applications",
    "/private");
    //method to handle the directory choosing process by clicking the button "Choose a Directory"
    @FXML
    void chooseDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser= new DirectoryChooser();
        directoryChooser.setTitle("Choose your directory");
        
        directory= directoryChooser.showDialog(App.getStage());
        if (directory!=null){
            directoryChoosenId.setText(directory.getName());
            }
    }
    //method to handle the file choosing process by clicking the button "Choose a File". The method will also check the file lenght to ensure that there are no overlapping texts on the UI.
    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose a file to move");
        
        file= fileChooser.showOpenDialog(App.getStage());
        if (file!=null){
            String fileName=file.getName();
            if(fileName.length()>15){
                fileName=fileName.substring(0, 15)+"...";
            }
            fileChoosenId.setText(fileName);
            }
    
    }
    //Disable the buttons until the Confirm button of the Alert is pressed. The buttons will be disabled if the alert is closed without pressing Confirm
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        


    }
    

    @Override
    public Action createAction() {
        if(isFilled()){
            return new MoveFileAction(file, directory.getPath());
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
    //These two methods use .stream() to convert the List in a item stream and anyMatch to see if any item starts with the directory or the path of the file we want to check
    //If it finds any matches, it return false. This methods are necessary to check if a Path is also present in sensitiveDirectory which contains dangerous paths.
    private boolean unavailableDirectory(String directory) {
        return sensitiveDirectories.stream().anyMatch(directory::startsWith);
    }

    private boolean unavailableFile(File file) {
        String filePath = file.getAbsolutePath();
        return sensitiveDirectories.stream().anyMatch(filePath::startsWith);
    
}
    
}
