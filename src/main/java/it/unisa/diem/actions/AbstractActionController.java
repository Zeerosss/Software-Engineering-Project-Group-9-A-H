package it.unisa.diem.actions;

import javafx.fxml.Initializable;

public interface AbstractActionController extends Initializable {
    public Action createAction();
    public boolean isFilled();
    
}
