package it.unisa.diem.rules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;


public class Rule implements Serializable,Observable{
    private static final long serialVersionUID = 1L;
    private transient List<Observer> observers = new ArrayList<>();
    private String name;
    private Trigger t;
    private Action a;
    private boolean status=true;


    public Rule(boolean status,String name,Trigger t,Action a){

        this.t=t;
        this.a=a;
        this.name=name;
        this.status=status;
    }
    public String getName() {
        return name;
    }

    public Trigger getTrigger() {
        return t;
    }

    public Action getAction() {
        return a;
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
        notifyObserver();
    }
    @Override
    public synchronized String toString() {
        return name;
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
      for(Observer observer : observers){
        observer.update();
      }
    }
    
}
