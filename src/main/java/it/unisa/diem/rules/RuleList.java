package it.unisa.diem.rules;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;

public class RuleList implements Observer, Observable {
    // List to store rules and observers
    private List<Rule> rules;
    private List<Observer> observers;

    // Singleton instance and RuleThread for automatic rule processing
    private static RuleList instance;
    private RuleThread ruleThread;

    // Singleton pattern to get an instance of RuleList
    public synchronized static RuleList getInstance() {
        if (instance == null) {
            instance = new RuleList();
        }
        return instance;
    }

    // Private constructor to initialize the rule list and observers
    // Also starts the RuleThread for automatic rule processing
    private RuleList() {
        this.rules = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.ruleThread = new RuleThread(this);
        new Thread(ruleThread).start();
    }

    // Function to add a rule to the list with default sleeping time if it's a one-time rule
    // Notifies observers about the change
    public synchronized void ruleAdd(boolean status, String name, Trigger t, Action a, boolean onlyOnce,
            Duration sleepingTime) {
        if (onlyOnce)
            sleepingTime = Duration.ZERO;

        Rule rule = new Rule(status, name, t, a, onlyOnce, sleepingTime);
        rule.addObserver(this);
        rules.add(rule);
        this.notifyObserver();
    }

    // Function to add a rule to the list with specified next useful date
    // Notifies observers about the change
    public synchronized void ruleAdd(boolean status, String name, Trigger t, Action a, boolean onlyOnce,
            Duration sleepingTime, LocalDateTime nextUsefulDate) {

        Rule rule = new Rule(status, name, t, a, onlyOnce, sleepingTime);
        rule.setNextUsefulDate(nextUsefulDate);
        rule.addObserver(this);
        rules.add(rule);
        this.notifyObserver();

    }

    // Function to delete a rule from the list
    // Notifies observers about the change
    public synchronized void ruleDelete(Rule rule) {
        rule.removeObserver(this);
        rules.remove(rule);
        this.notifyObserver();
    }

    // Function to get the list of rules
    public synchronized List<Rule> getRules() {
        return rules;
    }

    // Function to check if the rule list is empty
    public synchronized boolean isEmpty() {
        return rules.isEmpty();
    }

    // Implementation of Observer interface methods
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Function to notify observers about changes in the rule list
    @Override
    public synchronized void notifyObserver() {

        for (Observer observer : observers) {
            observer.update();
            System.out.println("ciao");
        }
    }

    // Implementation of the Observer interface method
    @Override
    public synchronized void update() {
        notifyObserver();
    }

}
