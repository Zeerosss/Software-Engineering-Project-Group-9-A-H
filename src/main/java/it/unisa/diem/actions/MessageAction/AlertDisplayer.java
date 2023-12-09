package it.unisa.diem.actions.MessageAction;
import java.io.Serializable;

public interface AlertDisplayer extends Serializable{
public void displayAlert(String title, String header, String message);
public void displayAlertWarning(String title, String header, String message);
}
