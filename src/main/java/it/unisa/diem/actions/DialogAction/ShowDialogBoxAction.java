package it.unisa.diem.actions.DialogAction;

import it.unisa.diem.actions.Action;

public class ShowDialogBoxAction implements Action {
    private String message;
    private AlertDisplayer dialogDisplayer;

    public ShowDialogBoxAction(String message, AlertDisplayer alertDisplayer) {
        this.message = message;
        this.dialogDisplayer = alertDisplayer;
    }

    public String getMessage() {
        return this.message;
    }

    // Override the startAction method as required by the Action interface
    @Override
    public void startAction() {
        // Display an alert dialog with the specified title, message, and content text
        dialogDisplayer.displayAlert("Dialog Box", message, "Press OK to close the window");
    }

    // Override the toString method to provide a human-readable representation of the action
    @Override
    public String toString() {
        return ("Show Dialog Box Action:\n" + message);
    }
}
