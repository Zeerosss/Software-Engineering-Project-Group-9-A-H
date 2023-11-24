package it.unisa.diem.SEGroup9;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.rules.RuleService;
import it.unisa.diem.triggers.TimeOfDayTrigger;
import it.unisa.diem.triggers.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private Action a;
    private Alert alert;
    private Trigger t;
    private AbstractActionController actionController; 

    @FXML
    private AnchorPane creationPage;
    @FXML
    private AnchorPane actionSelectionPane;
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
        
        if(triggerBox.getValue() == null || ruleName.isEmpty() || actionBox.getValue() == null||actionController.getText().isEmpty()){

            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Fill all the Field!");
            alert.showAndWait();

        }else{
            try{
                
                a=actionController.createAction();
                if(triggerBox.getValue().equals("Time of the day")){
                 t= new TimeOfDayTrigger();
            }
                
                ruleService.ruleAdd(true,ruleName,t,a);   
                alreadyAdd.getItems().add(ruleName);
                triggerBox.setValue(null);
                actionBox.setValue(null);
                ruleNameLabel.setText(null);
            }catch(Exception e){
                System.err.println("errore");
            }
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

    private AbstractActionController getActionController(String fxml){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

                try {
                    Parent root = fxmlLoader.load();
                    actionSelectionPane.getChildren().add(root);
                    return fxmlLoader.getController();
                } catch (IOException e) {
                    System.err.println("error in fxmlLoader");
                    return null;
                }
                
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alert = new Alert(Alert.AlertType.WARNING);
        actionBox.getItems().setAll("Display a Message","Play an Audio File" );
        triggerBox.getItems().setAll("Time of the day");    
    
    
        actionBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            actionSelectionPane.getChildren().clear();
            if (newValue.intValue() != -1) {
                actionController=getActionController(TypeConstant.ACTIONTYPES_CONSTANTS.get(newValue.intValue()));}
    });
    
    }
    }

    
