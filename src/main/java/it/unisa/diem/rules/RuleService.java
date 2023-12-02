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
    return new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            for (Rule rule : rules.getRules()) {
                if (rule.getTrigger().isValidated() && rule.getStatus()) {
                    Platform.runLater(() -> {
                        rules.ruleDelete(rule);
                        rule.getAction().startAction();
                    });
                }
            }
            return null;
        }
    };
}

    
}

