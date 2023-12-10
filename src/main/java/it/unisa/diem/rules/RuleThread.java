package it.unisa.diem.rules;

import it.unisa.diem.SEGroup9.AlertController;

public class RuleThread implements Runnable {

    // Reference to the RuleList instance
    private RuleList ruleList;

    public RuleThread(RuleList ruleList) {
        this.ruleList = ruleList;
    }

    // Implementation of the run method from the Runnable interface
    @Override
    public void run() {
        while (true) {
            try {
                for (Rule rule : ruleList.getRules()) {            
                    // Check if the rule is not in sleeping state, is enabled, and its trigger is validated
                    if (!rule.isSleeping() && rule.getStatus() && rule.getTrigger().isValidated()) {

                        // Update the next useful date if the rule is not in only-once execution mode
                        if (!rule.isOnlyOnce())
                            rule.updateNextUsefulDate();
                        else
                            rule.setStatus(false);
                        if (rule.getTrigger().isFailed()) {
                                AlertController.displayAlert("error", "the trigger failed", 
                            rule.getTrigger()+"\nhas failed");
                        }
                        else
                        rule.getAction().startAction();
                    }
                }

                // Sleep for 10 seconds before the next iteration
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
