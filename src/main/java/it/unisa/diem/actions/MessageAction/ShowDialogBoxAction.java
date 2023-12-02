package it.unisa.diem.actions.MessageAction;

import it.unisa.diem.actions.Action;

public class ShowDialogBoxAction implements Action{
    private String message;
    private AlertDisplayer dialogDisplayer;

    public ShowDialogBoxAction(String message, AlertDisplayer alertDisplayer){
        this.message=message; 
        this.dialogDisplayer = alertDisplayer;
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
