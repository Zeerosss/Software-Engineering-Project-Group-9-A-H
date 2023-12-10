package it.unisa.diem.rules;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;

public class Rule extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;




    // Instance variables for a rule
    private final String name;
    private Trigger t;
    private Action a;
    private boolean status = true;
    private final boolean onlyOnce;
    private final Duration sleepingTime;
    private LocalDateTime nextUsefulDate;

    // Constructor to initialize the rule
    public Rule(boolean status, String name, Trigger t, Action a, boolean onlyOnce, Duration sleepingTime) {
        this.t = t;
        this.a = a;
        this.name = name;
        this.status = status;
        this.onlyOnce = onlyOnce;
        this.sleepingTime = sleepingTime;
        this.nextUsefulDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    // Getter methods for rule properties
    public synchronized String getName() {
        return name;
    }

    public synchronized Trigger getTrigger() {
        return t;
    }

    public synchronized Action getAction() {
        return a;
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized Duration getSleepingTime() {
        return sleepingTime;
    }

    public synchronized LocalDateTime getNextUsefulDate() {
        return nextUsefulDate;
    }

    public synchronized String getNextExecution(){
        if(onlyOnce)
            return "only once";
        return "Will be available from\n" + getNextUsefulDate().toString();
    }

    // Setter method for next useful date and notifies observers
    public synchronized void setNextUsefulDate(LocalDateTime nextUsefulDate) {
        this.nextUsefulDate = nextUsefulDate;
        notifyObserver();
    }

    // Method to update next useful date and notifies observers
    public synchronized void updateNextUsefulDate() {
        nextUsefulDate = LocalDateTime.now().plus(sleepingTime).truncatedTo(ChronoUnit.MINUTES);
        notifyObserver();
    }

    public synchronized boolean isOnlyOnce() {
        return onlyOnce;
    }

    // Setter method for rule status and notifies observers
    public synchronized void setStatus(boolean status) {
        this.status = status;
        notifyObserver();
    }

    // Method to toggle rule status and notifies observers
    public synchronized void changeRuleStatus() {
        this.status = !this.status;
        restartTrigger();
        notifyObserver();
    }

    // Method to check if the rule is in sleeping state
    public synchronized boolean isSleeping() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).isBefore(nextUsefulDate);
    }

    // Override the toString method to provide a human-readable representation of the rule
    @Override
    public synchronized String toString() {
        return name;
    }

    public void restartTrigger() {
        if(status)  t.startTrigger();

    }

    
}
