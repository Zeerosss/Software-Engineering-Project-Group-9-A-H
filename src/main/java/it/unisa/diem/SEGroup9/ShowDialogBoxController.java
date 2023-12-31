package it.unisa.diem.SEGroup9;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.DialogAction.AlertJavaFX;
import it.unisa.diem.actions.DialogAction.ShowDialogBoxAction;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class ShowDialogBoxController implements AbstractActionController{
    
    @FXML
    private TextField showDialogTextId;


    @Override
    public Action createAction() {
        if (isFilled()){
            Action action = new ShowDialogBoxAction(showDialogTextId.getText(),new AlertJavaFX());
            return action;
        }else{
                return null;
            }
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDialogTextId.setPromptText("...");

    }

    @Override
    public boolean isFilled() {
        return(!showDialogTextId.getText().isEmpty());
    }
    

    
}





