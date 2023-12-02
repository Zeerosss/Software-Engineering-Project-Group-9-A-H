package it.unisa.diem.rules;

import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;


public class RuleList implements Observer,Observable {
    private List<Rule> rules;
    private List<Observer> observers;
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
        observers = new ArrayList<>();
        this.ruleThread = new RuleThread(this);
        new Thread(ruleThread).start();
    }

    public synchronized void ruleAdd(boolean status,String name,Trigger t,Action a){
        Rule rule = new Rule(status, name, t, a);
        rule.addObserver(this);
        rules.add(rule);
        notifyObserver();
        
    }
    public synchronized void ruleDelete(Rule rule){
        rule.removeObserver(this);
        rules.remove(rule);
        this.notifyObserver();
    }

    public synchronized List<Rule> getRules(){
        return rules;
    }
    public synchronized boolean isEmpty() {
        return rules.isEmpty();
    }
    @Override
    public void addObserver(Observer observer) {
       observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
      observers.remove(observer);
    }
    @Override
    public synchronized void notifyObserver() {
        System.out.println("ciao");
      for(Observer observer : observers){
        observer.update();
      }
    }

    @Override
    public synchronized void update() {
        notifyObserver();
    }

}
