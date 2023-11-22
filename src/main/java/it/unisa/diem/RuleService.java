package it.unisa.diem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleService extends Rule {
    private ObservableList<Rule> rules= FXCollections.observableArrayList();
    public RuleService(Boolean status,String name,Trigger t, Action a){
        super(status,name,t,a);
        Rule e=new Rule(super.getStatus(),super.getName(),super.getTrigger(),super.getAction());
        rules.add(e);
     
    }


    public ObservableList<Rule> getRules(){
        return rules;
    }

}
