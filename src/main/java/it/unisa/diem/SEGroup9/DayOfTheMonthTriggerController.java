package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.DayOfTheMonthTrigger;
import it.unisa.diem.triggers.Trigger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
//Class to controll the Day of the month trigger-> The value of the month will be taken from a spinner.
public class DayOfTheMonthTriggerController implements AbstractTriggerController{
    private LocalDate date=LocalDate.now();
    @FXML
    private Spinner<Integer> daySpinner;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(date.getDayOfMonth(), date.lengthOfMonth()));
        daySpinner.getEditor().setAlignment(Pos.CENTER_RIGHT);
        daySpinner.getValueFactory().setValue(date.getDayOfMonth());


    }

    @Override
    public Trigger createTrigger() {
        try{ 
            return new DayOfTheMonthTrigger(daySpinner.getValue());}
        catch(Exception e){
            System.err.println("Day of the month trigger hasn't been created");
            return null;
        }
         
    }
    

}