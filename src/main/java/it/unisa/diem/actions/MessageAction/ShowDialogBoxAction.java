package it.unisa.diem.actions.MessageAction;

import it.unisa.diem.actions.Action;

public class ShowDialogBoxAction implements Action{
    private String message;
    private transient AlertDisplayer dialogDisplayer=new AlertJavaFX();
    public ShowDialogBoxAction(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
    @Override
    public void startAction(){
        dialogDisplayer.displayAlert("Dialog Box", message, "Press OK to close the window");
        
    }
    @Override
    public String toString() {
        return("Show Dialog Box Action:\n"+ message);
    }


}
