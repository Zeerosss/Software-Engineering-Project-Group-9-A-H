package it.unisa.diem.SEGroup9;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileAction;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import it.unisa.diem.rules.Rule;
import it.unisa.diem.rules.RuleService;
import it.unisa.diem.triggers.TimeOfDayTrigger;
import it.unisa.diem.triggers.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SecondaryController implements Initializable {
    //getting the RuleService Instance to set the elements into the ObservableList
    private RuleService ruleService = RuleService.getInstance();
    
    @FXML
    private Button confirmRuleButton;
    private Rule e;
    private Alert alert;
    private Action a;
    private Trigger t;
    @FXML
    private AnchorPane creationPage;

    @FXML
    private ListView<String> alreadyAdd;

    @FXML
    private ChoiceBox<String> actionBox;

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
            if(actionBox.getValue().equals("Display a Message")){
                 a=new ShowDialogBoxAction();
            }
            if(actionBox.getValue().equals("Play an Audio File")){
                 a=new PlayAudioFileAction();
            }
            if(triggerBox.getValue().equals("Time of the day")){
                 t= new TimeOfDayTrigger();
                    
            }
                //adding a new rule to the ObservableList
                ruleService.ruleAdd(true,ruleName,t,a);   
                alreadyAdd.getItems().add(ruleName);
            
            

            }

    }
    
    @FXML
    private void confirmSet(ActionEvent event) throws IOException{
        if(alreadyAdd.getItems().isEmpty()){
            
            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Enter at least one rule");
            alert.showAndWait();

        }else App.setRoot("ruleview");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        alert = new Alert(Alert.AlertType.WARNING);
        actionBox.getItems().setAll("Display a Message","Play an Audio File" );
        triggerBox.getItems().setAll("Time of the day");    }
}