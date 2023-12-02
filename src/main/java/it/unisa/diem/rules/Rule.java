package it.unisa.diem.rules;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
//"The synchronized keyword makes functions thread-safe. In the future, another thread will be used to parallelize auto-save."

public class Rule implements Serializable,Observable{
    private static final long serialVersionUID = 1L;
    private transient List<Observer> observers = new ArrayList<>();
    private final String name;
    private Trigger t;
    private Action a;
    private boolean status=true;
    private final boolean onlyOnce;
    private final Duration sleepingTime;
    private LocalDateTime nextUsefulDate;


    public Rule(boolean status,String name,Trigger t,Action a, boolean onlyOnce, Duration sleepingTime){

        this.t=t;
        this.a=a;
        this.name=name;
        this.status=status;
        this.onlyOnce = onlyOnce;
        this.sleepingTime = sleepingTime;
        this.nextUsefulDate = LocalDateTime.of(0,1,1,0,0);

    }

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

    public synchronized Duration getSleepingTime(){
        return sleepingTime;
    }

    public synchronized LocalDateTime getNextUsefulDate(){
        return nextUsefulDate;
    }

    public synchronized void setNextUsefulDate(LocalDateTime nextUsefulDate){
        this.nextUsefulDate = nextUsefulDate;
        notifyObserver();
    }

    public synchronized void updateNextUsefulDate(){
        nextUsefulDate = LocalDateTime.now().plus(sleepingTime).truncatedTo(ChronoUnit.MINUTES);
        notifyObserver();
    }

    public synchronized boolean isOnlyOnce(){
        return onlyOnce;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
        notifyObserver();
    }

    public synchronized boolean isSleeping(){
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).isBefore(nextUsefulDate);
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
    public void notifyObserver() {
      for(Observer observer : observers){
        observer.update();
      }
    }
    
}
