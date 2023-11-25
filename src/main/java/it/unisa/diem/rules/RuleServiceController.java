package it.unisa.diem.rules;

import javafx.util.Duration;

public class RuleServiceController {

    private RuleService ruleService;
    
    public RuleServiceController(RuleService ruleService){

        this.ruleService = ruleService;
        
        ruleService.setPeriod(Duration.seconds(10));
        ruleService.start();
        
    }

}
