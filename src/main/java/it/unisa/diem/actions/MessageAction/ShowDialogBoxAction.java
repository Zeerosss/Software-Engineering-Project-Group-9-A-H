package it.unisa.diem.actions.MessageAction;

import it.unisa.diem.actions.Action;
import javafx.scene.control.Alert;

public class ShowDialogBoxAction implements Action{
    private String message;

    public ShowDialogBoxAction(){}
    public void messageAction(String message){
        this.message=message;
    }
    @Override
    public void startAction(){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();

    }
    @Override
    public String toString() {
        return "Show Dialog Box Action";
    }


}
