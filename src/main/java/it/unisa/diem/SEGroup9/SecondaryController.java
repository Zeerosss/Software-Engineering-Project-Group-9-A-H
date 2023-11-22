package it.unisa.diem.SEGroup9;

import javafx.fxml.FXMLLoader;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SecondaryController implements Initializable {
    @FXML
    private Button confirmRuleButton;

    private TimeTriggerController timeTriggerController; 

    private Alert alert;

    @FXML
    private AnchorPane creationPage;

    @FXML
    private ListView<String> alreadyAdd;

    @FXML
    private ChoiceBox<String> actionBox;

    @FXML
    private AnchorPane triggerInputPane;

    
    @FXML
    private ChoiceBox<String> triggerBox;
    @FXML
    private TextField ruleNameLabel;
    
    @FXML
    private void switchToPrimary() throws IOException {

        String ruleName = ruleNameLabel.getText();

        if(triggerBox.getValue() == null || ruleName.isEmpty() || actionBox.getValue() == null){

            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Fill all the Field!");
            alert.showAndWait();

        }else{

            alreadyAdd.getItems().add(ruleName);
            triggerBox.setValue(null);
            actionBox.setValue(null);
            ruleNameLabel.setText(null);
            

            }

    }
    
    @FXML
    private void confirmSet(ActionEvent event) throws IOException{
        if(alreadyAdd.getItems().isEmpty()){
            
            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Enter at least one rule");
            alert.showAndWait();
            

        }else 
        App.setRoot("ruleview");
    }

    @Override
    /**
     * Initializes the SecondaryController.
     * loads specific triggers fxml and connects this controller to the controller related to the UI part for specific triggers.
     * 
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        alert = new Alert(Alert.AlertType.WARNING);
        actionBox.getItems().setAll("Play an audio file wip", "Display a message wip");
        triggerBox.getItems().setAll("Time of day Trigger wip");

        triggerBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            triggerInputPane.getChildren().clear();

            if (newValue.intValue() == 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("timetrigger.fxml"));

                try {
                    Parent root = fxmlLoader.load();
                    triggerInputPane.getChildren().add(root);
                    timeTriggerController = fxmlLoader.getController();
                } catch (IOException e) {
                    System.err.println("error in fxmlLoader");
                }
            }
        });
    }
}