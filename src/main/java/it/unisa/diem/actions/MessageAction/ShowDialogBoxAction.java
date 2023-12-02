package it.unisa.diem.actions.MessageAction;

import it.unisa.diem.actions.Action;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ShowDialogBoxAction implements Action{
    private String message;

    public ShowDialogBoxAction(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
    @Override
    public void startAction(){
        Platform.runLater(() -> {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialog Box");
            alert.setHeaderText(message);
            alert.setContentText("Press OK to close the Window");
            alert.showAndWait(); 
        });
           
    }
    @Override
    public String toString() {
        return("Show Dialog Box Action:\n"+ message);
    }


}
