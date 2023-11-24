package it.unisa.diem.rules;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleService {
    private static RuleService instance;
    private ObservableList<Rule> rules;

    private RuleService() {
        
        rules= FXCollections.observableArrayList();
    }
    //setting the istance witch will grant access to the Observable List granting the sharing of the list and 
    //the inizialitazion of the ViewTable in the PrimaryController by passing parameters from the Secondary Controller
    public static RuleService getInstance() {
        if (instance == null) {
            instance = new RuleService();
        }
        return instance;
    }
    public void ruleAdd(boolean status,String name,Trigger t,Action a){
         rules.add(new Rule(status, name, t, a));

    }

    public ObservableList<Rule> getRules(){
        return rules;
    }
}

