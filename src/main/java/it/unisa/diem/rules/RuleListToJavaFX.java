package it.unisa.diem.rules;


import java.time.Duration;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleListToJavaFX implements Observer{

    private RuleList ruleList = RuleList.getInstance();
    private ObservableList<Rule> observableList = FXCollections.observableArrayList();
    private static RuleListToJavaFX instance;
    
    public RuleListToJavaFX() {
        ruleList.addObserver(this);
    }

    public static RuleListToJavaFX getInstance() {
        if (instance == null) {
            instance = new RuleListToJavaFX();
        }
        return instance;
    }

    public void ruleAdd(boolean status,String name,Trigger t,Action a, boolean onlyOnce, Duration sleepingTime){
        ruleList.ruleAdd(status, name, t, a, onlyOnce, sleepingTime);
    }
   
    public void ruleDelete(Rule rule){
        ruleList.ruleDelete(rule);
    }

    public ObservableList<Rule> getRules(){
        
        return observableList;
    }

    public boolean isEmpty() {
        return ruleList.isEmpty();
    }

    @Override
    public void update() {
        System.out.println("aggiorno");
        observableList.setAll(ruleList.getRules());
    }
}
