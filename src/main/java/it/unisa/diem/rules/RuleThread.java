package it.unisa.diem.rules;

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

                    // Print the next useful date and rule name for debugging purposes
                    System.out.println(rule.getNextUsefulDate() + " " + rule);

                    // Check if the rule is not in sleeping state, is enabled, and its trigger is validated
                    if (!rule.isSleeping() && rule.getStatus() && rule.getTrigger().isValidated()) {

                        // Update the next useful date if the rule is not in only-once execution mode
                        if (!rule.isOnlyOnce())
                            rule.updateNextUsefulDate();
                        else
                            rule.setStatus(false);

                        // Execute the action associated with the rule
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
