package it.unisa.diem.rules;

import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;


public class RuleList implements Observer,Observable {
    private List<Rule> rules = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private static RuleList instance;


    public static RuleList getInstance() {
        if (instance == null) {
            instance = new RuleList();
        }
        return instance;
    }
    public void ruleAdd(boolean status,String name,Trigger t,Action a){
        Rule rule = new Rule(status, name, t, a);
        rule.addObserver(this);
        rules.add(rule);
        notifyObserver();
        
    }
    public void ruleDelete(Rule rule){
        rule.removeObserver(this);
        rules.remove(rule);
        this.notifyObserver();
    }

    public List<Rule> getRules(){
        return rules;
    }
    public boolean isEmpty() {
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
    public void notifyObserver() {
        System.out.println("ciao");
      for(Observer observer : observers){
        observer.update();
        
      }
    }

    @Override
    public void update() {
        notifyObserver();
    }

}
