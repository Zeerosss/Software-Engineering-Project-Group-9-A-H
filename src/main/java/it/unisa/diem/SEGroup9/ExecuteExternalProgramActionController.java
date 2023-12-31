package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.ExecuteProgramAction.ExecuteExternalProgramAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class ExecuteExternalProgramActionController implements AbstractActionController{

    private FileChooser fileChooser;
    private File file;
    private List<String> parameterList;

    @FXML
    private Button changeProgramButton;

    @FXML 
    private Button chooseProgramAction;
    
    @FXML
    private Label externalProgramPathLabel;

    @FXML
    private TextField parameterID;

    @FXML
    private Label parametersLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        parameterList = new ArrayList<>();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
    }
    
    @FXML
    private void addParameterAction(ActionEvent event){
        //add parameter in a list and update the label
        if(!parameterID.getText().trim().equals(""))
            parameterList.add(parameterID.getText());
        parameterID.setText("");
        parametersLabel.setText(String.valueOf(parameterList.size()));
    }
    @FXML
    void changeProgramAction(ActionEvent event){
        File newSelectedFile = fileChooser.showOpenDialog(App.getStage());
        // Show the file chooser dialog and update the label with the name of the chosen file
        if (newSelectedFile != null) {
            file = newSelectedFile;
            externalProgramPathLabel.setText("Chosen file: " + file.getName());
        }
    }

    @Override
    public Action createAction() {
        
        if (isFilled()) {
            return new ExecuteExternalProgramAction(file, parameterList);
        } else { 
            return null;
        }

    }

    @Override
    public boolean isFilled() {
        return file != null;
    }

}
