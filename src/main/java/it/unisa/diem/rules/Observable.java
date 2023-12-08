package it.unisa.diem.rules;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
private transient List<Observer> observers = new ArrayList<>();
    


    public void addObserver(Observer observer) {
       observers.add(observer);
    }

    public void removeObserver(Observer observer) {
      observers.remove(observer);
    }
    public void notifyObserver() {
      for(Observer observer : observers){
        observer.update();
      }
    }
}
