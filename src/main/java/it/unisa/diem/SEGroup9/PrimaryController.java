package it.unisa.diem.SEGroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.Action;
import it.unisa.diem.rules.Rule;
import it.unisa.diem.rules.RuleService;
import it.unisa.diem.triggers.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable{
    
    private static boolean firstUse = true;
    //getting the RuleService Instance to get the elements from the ObservableList
    private RuleService ruleService = RuleService.getInstance();
    
    @FXML
    private Button createSet;
    
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
    void switchToCreateView(ActionEvent event) throws IOException{
        firstUse = false;
        App.setRoot("createsetview");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(firstUse)
        createSet.setVisible(true);
       //future proofing
       //table column initialization+ settings
        actionNameId.setCellValueFactory(new PropertyValueFactory<Rule,Action>("Action"));
        triggerNameId.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("Trigger"));
        ruleNameId.setCellValueFactory(new PropertyValueFactory<Rule,String>("Name"));
        statusId.setCellValueFactory(new PropertyValueFactory<Rule,Boolean>("Status"));
        rulesTable.setItems(ruleService.getRules());
        System.out.println(ruleService.getRules());
    }

}
    
