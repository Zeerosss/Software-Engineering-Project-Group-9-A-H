package it.unisa.diem.rules;
import javafx.concurrent.Task;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;


public class RuleService extends ScheduledService<Void>{
    
    private RuleCollection rules;

    public RuleService(RuleCollection rules){
        this.rules = rules;
    }

    @Override
    protected Task<Void> createTask() {
     
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception {

                for(Rule rule : rules.getRules()){

                    if(rule.getTrigger().isValidated()){
                        //Platform.runLater mi permette di eseguire l'istruzione nel thread di JavaFx non ho bisogno di altri service
                        Platform.runLater(() -> {
                            //elimino prima la regola così non verrà ricontrollata
                            rule.getAction().startAction();
                        });
                    
                    }  
                    
                }
 
                return null;
                   
           }
            
        };
    }
    
}

