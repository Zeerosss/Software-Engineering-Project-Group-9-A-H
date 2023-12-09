package it.unisa.diem.rules;

import java.time.Duration;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleListToJavaFX implements Observer {

    // Reference to the RuleList singleton
    private RuleList ruleList = RuleList.getInstance();

    // ObservableList to be used by JavaFX UI components
    private ObservableList<Rule> observableList = FXCollections.observableArrayList();

    // Singleton instance of RuleListToJavaFX
    private static RuleListToJavaFX instance;

    // Constructor to set up the observer relationship
    public RuleListToJavaFX() {
        ruleList.addObserver(this);
    }

    // Singleton pattern to get an instance of RuleListToJavaFX
    public static RuleListToJavaFX getInstance() {
        if (instance == null) {
            instance = new RuleListToJavaFX();
        }
        return instance;
    }

    // Method to add a rule to the RuleList
    public void ruleAdd(boolean status, String name, Trigger t, Action a, boolean onlyOnce, Duration sleepingTime) {
        ruleList.ruleAdd(status, name, t, a, onlyOnce, sleepingTime);
    }

    // Method to delete a rule from the RuleList
    public void ruleDelete(Rule rule) {
        ruleList.ruleDelete(rule);
    }

    // Method to get the observable list of rules for JavaFX UI components
    public ObservableList<Rule> getRules() {
        return observableList;
    }

    // Method to check if the rule list is empty
    public boolean isEmpty() {
        return ruleList.isEmpty();
    }

    // Implementation of the Observer interface method
    @Override
    public void update() {
        // Update the observable list with the latest rules from RuleList
        observableList.setAll(ruleList.getRules());
    }
    public void restartTrigger() {
        List<Rule> tempRules = ruleList.getRules();
        for(Rule rule : tempRules){
            if(!rule.isSleeping())
            rule.restartTrigger();
        }
    }
}
