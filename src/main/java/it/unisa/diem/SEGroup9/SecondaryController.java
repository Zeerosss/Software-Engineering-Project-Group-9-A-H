package it.unisa.diem.SEGroup9;

import javafx.fxml.FXMLLoader;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private AnchorPane triggerInputPane;

    
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
            Trigger trigger;
            try{
                trigger = triggerController.createTrigger();
                          
                
                a=actionController.createAction();
            
            
                
                ruleService.ruleAdd(true,ruleName,t,a);   
            System.out.println(trigger.toString());
                alreadyAdd.getItems().add(ruleName);
                triggerBox.setValue(null);
                actionBox.setValue(null);
                ruleNameLabel.setText(null);
            }catch(Exception e){
                System.err.println("excepiton in creation");
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
    private AbstractTriggerController getTriggerController(String fxml){
    
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
                    actionSelectionPane.getChildren().add(root);
                    return fxmlLoader.getController();
                } catch (IOException e) {
                    System.err.println("error in fxmlLoader");
                    return null;
                }
                
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
            if(newValue.intValue() != -1) 
            triggerController = getTriggerController(TypeConstant.TRIGGERTYPES_CONSTANTS.get(newValue.intValue()));
        });
    

    
        actionBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            actionSelectionPane.getChildren().clear();
            if (newValue.intValue() != -1) {
                actionController=getActionController(TypeConstant.ACTIONTYPES_CONSTANTS.get(newValue.intValue()));}
    });
    
    }
    

    
