package it.unisa.diem.SEGroup9;

import javafx.fxml.FXMLLoader;


import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.rules.Rule;
import it.unisa.diem.rules.RuleListToJavaFX;
import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;


public class SecondaryController implements Initializable {
    //getting the RuleService Instance to set the elements into the ObservableList
    private static RuleListToJavaFX ruleCollection = RuleListToJavaFX.getInstance();
    
    @FXML
    private Button confirmRuleButton;

    private Alert alert;
    
    private AbstractTriggerController triggerController;

    private AbstractActionController actionController; 
    @FXML 
    private AnchorPane actionInputPane;
    @FXML
    private AnchorPane creationPage;
    @FXML
    private ListView<Rule> alreadyAdd;
    @FXML
    private ChoiceBox<String> actionBox;
    @FXML
    private AnchorPane triggerInputPane;
    @FXML
    private ChoiceBox<String> triggerBox;
    @FXML
    private TextField ruleNameLabel;
    @FXML
    private RadioButton onlyOnceRadio;
    @FXML
    private RadioButton sleepingTimeRadio;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
    @FXML
    private Spinner<Integer> daySpinner;
    
    @FXML
    private void switchToPrimary() throws IOException {

        String ruleName = ruleNameLabel.getText();

        Duration sleepingTime;
        
        if(triggerBox.getValue() == null || ruleName.isEmpty() || actionBox.getValue() == null){

            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Fill all the Field!");
            alert.showAndWait();

        }else{
            Trigger trigger = triggerController.createTrigger();
            
            Action action = actionController.createAction();
            System.err.println(action);
            if( ( action == null )||( trigger == null) ){
                
                // If the action/trigger object is null
                alert.setTitle("Warning");
                alert.setHeaderText("WARNING!");
                alert.setContentText("Error in action or trigger fields");
                alert.showAndWait();
            }else{try{
                sleepingTime = Duration.ofDays(daySpinner.getValue()).plusHours(hourSpinner.getValue()
            ).plusMinutes(minuteSpinner.getValue());
            
                ruleCollection.ruleAdd(true,ruleName,trigger,action, onlyOnceRadio.isSelected(), sleepingTime);   
                
               
                triggerBox.setValue(null);
                actionBox.setValue(null);
                ruleNameLabel.clear();
               
            
            }catch(Exception e){
                System.err.println("exception in creation");
            }            
        
           
    }}
}     private AbstractTriggerController getTriggerController(String fxml){
    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

            try {
                Parent root = fxmlLoader.load();
                triggerInputPane.getChildren().add(root);
                return fxmlLoader.getController();
            } catch (IOException e) {
                System.err.println("error in fxmlLoader");
                return null;
            }       
}

private AbstractActionController getActionController(String fxml){

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

            try {
                Parent root = fxmlLoader.load();
                actionInputPane.getChildren().add(root);
                return fxmlLoader.getController();
            } catch (IOException e) {
                System.err.println("error in fxmlLoader");
                e.printStackTrace();
                return null;
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
        actionBox.getItems().setAll("Play an audio file", "Display a message","Copy File","Move File","Delete File","Append a message to a file");
        triggerBox.getItems().setAll("Time of day Trigger","File exists in a directory Trigger","File dimension exceeds Trigger","exit status of a program");

        alreadyAdd.setItems(ruleCollection.getRules());

        triggerBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            triggerInputPane.getChildren().clear(); 
            if(newValue.intValue() != -1) 
                 triggerController = getTriggerController(TypeConstant.TRIGGERTYPES_CONSTANTS.get(newValue.intValue()));
        });
    
        actionBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            actionInputPane.getChildren().clear();
            if (newValue.intValue() != -1) {
                actionController=getActionController(TypeConstant.ACTIONTYPES_CONSTANTS.get(newValue.intValue()));}
        });

        onlyOnceRadio.setSelected(true);

        hourSpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        minuteSpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        daySpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000));
    
    }
}
    

    
