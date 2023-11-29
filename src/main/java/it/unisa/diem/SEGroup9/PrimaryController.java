package it.unisa.diem.SEGroup9;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import it.unisa.diem.actions.Action;
import it.unisa.diem.rules.Rule;
import it.unisa.diem.rules.RuleCollection;
import it.unisa.diem.triggers.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{
    

    //getting the RuleCollection Instance to get the elements from the ObservableList
    private RuleCollection ruleService = RuleCollection.getInstance();
    
    @FXML
    private Button createSet;
    
    @FXML
    private Button delete;

    @FXML
    private TableView<Rule> rulesTable;
    @FXML
    private TableColumn<Rule,String> ruleNameId;
    @FXML
    private TableColumn<Rule,Boolean> statusId;
    @FXML
    private TableColumn<Rule,Trigger> triggerNameId;
    @FXML
    private TableColumn<Rule,Action> actionNameId;
   
    @FXML
    void deleteSelectedRule(ActionEvent event) throws IOException{
        Rule selectedRule = rulesTable.getSelectionModel().getSelectedItem();
        if(selectedRule != null){

            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to perform this action?");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK)
                    ruleService.ruleDelete(selectedRule);
                    if(ruleService.isEmpty()){
                    delete.setVisible(false);
                    createSet.setText("Create new rule Set");
                }
        }
        );
        }
    }

    @FXML
    void switchToCreateView(ActionEvent event) throws IOException{

        App.setRoot("createsetview");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!ruleService.isEmpty()){
        createSet.setText("Add new rules");
        delete.setVisible(true);
        }

        
       //future proofing
       //table column initialization+ settings
        actionNameId.setCellValueFactory(new PropertyValueFactory<Rule,Action>("Action"));
        triggerNameId.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("Trigger"));
        ruleNameId.setCellValueFactory(new PropertyValueFactory<Rule,String>("Name"));
        statusId.setCellValueFactory(new PropertyValueFactory<Rule,Boolean>("Status"));
        rulesTable.setItems(ruleService.getRules());

    }

}
    
