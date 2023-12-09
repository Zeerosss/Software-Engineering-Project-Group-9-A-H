package it.unisa.diem.actions.MessageAction;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertJavaFX implements AlertDisplayer {
    @Override
    public void displayAlert(String title, String header, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
