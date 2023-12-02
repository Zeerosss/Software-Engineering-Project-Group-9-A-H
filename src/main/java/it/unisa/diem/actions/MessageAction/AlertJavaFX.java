package it.unisa.diem.actions.MessageAction;

import javafx.scene.control.Alert;

public class AlertJavaFX implements AlertDisplayer{
    @Override
    public void displayAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }


    
}
