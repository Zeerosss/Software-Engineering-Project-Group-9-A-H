package it.unisa.diem.rules;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;

public class Rule {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return name;
    }
    
}
