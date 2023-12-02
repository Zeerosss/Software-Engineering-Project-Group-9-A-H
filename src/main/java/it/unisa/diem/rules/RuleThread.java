package it.unisa.diem.rules;

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

                    if(rule.getStatus() && rule.getTrigger().isValidated()){
                        //Platform.runLater mi permette di eseguire l'istruzione nel thread di JavaFx non ho bisogno di altri service
                            rule.setStatus(false);
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
