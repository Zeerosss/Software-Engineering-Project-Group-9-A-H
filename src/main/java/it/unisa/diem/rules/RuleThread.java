package it.unisa.diem.rules;

import it.unisa.diem.SEGroup9.AlertController;

public class RuleThread implements Runnable {

    private RuleList ruleList;

    public RuleThread(RuleList ruleList){
        this.ruleList = ruleList;
    }

    @Override
    public void run() {
        while(true){
            try {

                for(Rule rule : ruleList.getRules()){

                    System.out.println(rule.getNextUsefulDate() + " " + rule);

                    if(!rule.isSleeping() && rule.getStatus() && rule.getTrigger().isValidated()){
                        
                        //the status change if the rule is in only once execution mode
                        if(!rule.isOnlyOnce())
                            rule.updateNextUsefulDate();
                        else
                            rule.setStatus(false);
                        if (rule.getTrigger().isFailed()) {
                                AlertController.displayAlert("error", "the trigger failed", rule.getTrigger()+"\nhas failed");
                        }
                        else
                        rule.getAction().startAction();
                        
                    
                    }  
                    
                }

                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
