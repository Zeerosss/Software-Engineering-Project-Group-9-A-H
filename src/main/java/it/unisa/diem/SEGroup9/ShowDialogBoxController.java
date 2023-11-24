package it.unisa.diem.SEGroup9;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class ShowDialogBoxController implements AbstractActionController{
    
    @FXML
    private TextField showDialogTextId;

    @Override
    public String getText(){
        return showDialogTextId.getText();
   }

    @Override
    public Action createAction() {
        
            ShowDialogBoxAction action = new ShowDialogBoxAction(showDialogTextId.getText());
            return action;
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDialogTextId.setPromptText("Inserisci il testo qui...");

    }
    

    
}





