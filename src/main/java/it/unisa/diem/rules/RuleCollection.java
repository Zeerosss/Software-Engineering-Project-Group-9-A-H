package it.unisa.diem.rules;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.unisa.diem.actions.Action;
import it.unisa.diem.triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleCollection {
    private static RuleCollection instance;
    private ObservableList<Rule> rules;
    private RuleServiceController ruleServiceController;

    private RuleCollection() {
        
        rules = FXCollections.observableArrayList();
        ruleServiceController = new RuleServiceController(new RuleService(this));
    }
    //setting the istance witch will grant access to the Observable List granting the sharing of the list and 
    //the inizialitazion of the ViewTable in the PrimaryController by passing parameters from the Secondary Controller
    public static RuleCollection getInstance() {
        if (instance == null) {
            instance = new RuleCollection();
        }
        return instance;
    }
    public void ruleAdd(boolean status,String name,Trigger t,Action a){
        rules.add(new Rule(status, name, t, a));
    }
    public void ruleAdd(Rule rule){
        rules.add(rule);
    }
    public void ruleDelete(Rule rule){
        rules.remove(rule);
    }

    public ObservableList<Rule> getRules(){
        return rules;
    }
    public boolean isEmpty() {
        return rules.isEmpty();
    }

    /**
     * 
     */
    public void save(String path) throws IOException {
        File file= new File(path);
        if (!file.exists()) 
              try {
                file.createNewFile();
              } catch (IOException e) {
                System.err.println("error in file creation");
              }  
            
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            Rule[] rules = this.rules.toArray(new Rule[0]);
            objectOutputStream.writeObject(rules);
    }
    }

    public Boolean load(String path) throws Exception{
    File file = new File(path);

    if (file.exists()) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            // Read the object from the file using deserialization
            Object[] rulesArray = (Object[]) objectInputStream.readObject();

            //add to the list;
           
            for (Object obj : rulesArray) {
                if (obj instanceof Rule) {
                    ruleAdd((Rule) obj);
                }
            }

          
        return true;    
        }
    }else{
        return false;
    }
}
    
}

