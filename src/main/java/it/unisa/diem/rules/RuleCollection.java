package it.unisa.diem.rules;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleCollection {
    private static RuleCollection instance;
    private ObservableList<Rule> rules;
    private RuleServiceController ruleServiceController;

    private RuleCollection() {
        
        rules = FXCollections.observableArrayList();
        ruleServiceController = new RuleServiceController(new RuleService(this));
    }
    //setting the istance witch will grant access to the Observable List granting the sharing of the list and 
    //the inizialitazion of the ViewTable in the PrimaryController by passing parameters from the Secondary Controller
    public static RuleCollection getInstance() {
        if (instance == null) {
            instance = new RuleCollection();
        }
        return instance;
    }
    public void ruleAdd(boolean status,String name,Trigger t,Action a){
        rules.add(new Rule(status, name, t, a));
    }
    public void ruleDelete(Rule rule){
        rules.remove(rule);
    }
    public void changeRuleStatus(Rule rule) {
        rule.changeRuleStatus();
    }    

    public ObservableList<Rule> getRules(){
        return rules;
    }
    public boolean isEmpty() {
        return rules.isEmpty();
    }
    
}

