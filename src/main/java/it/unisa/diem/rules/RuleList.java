package it.unisa.diem.rules;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;


public class RuleList  extends Observable implements Observer{
    private List<Rule> rules;
    
    private static RuleList instance;
    private RuleThread ruleThread;


    public synchronized static RuleList getInstance() {
        if (instance == null) {
            instance = new RuleList();
        }
        return instance;
    }

    private RuleList(){
        this.rules = new ArrayList<>();
        
        this.ruleThread = new RuleThread(this);
        new Thread(ruleThread).start();
    }

    //The view use this function
    public synchronized void ruleAdd(boolean status,String name,Trigger t,Action a, boolean onlyOnce, Duration sleepingTime){
        if(onlyOnce)
            sleepingTime = Duration.ZERO;
        Rule rule = new Rule(status, name, t, a, onlyOnce, sleepingTime);
        rule.addObserver(this);
        rules.add(rule);
        this.notifyObserver();
    }

    //autoSave use this function
    public synchronized void ruleAdd(boolean status,String name,Trigger t,
Action a, boolean onlyOnce, Duration sleepingTime, LocalDateTime nextUsefulDate){
    
        Rule rule = new Rule(status, name, t, a, onlyOnce, sleepingTime);
        rule.setNextUsefulDate(nextUsefulDate);
        rule.addObserver(this);
        rules.add(rule);
        this.notifyObserver();
        
    }

    public synchronized void ruleDelete(Rule rule){
        rule.removeObserver(this);
        rules.remove(rule);
        this.notifyObserver();
    }

    public synchronized List<Rule> getRules(){
        return rules;
    }
    public boolean isEmpty() {
        return rules.isEmpty();
    }


    @Override
    public synchronized void update() {
        notifyObserver();
    }

}
