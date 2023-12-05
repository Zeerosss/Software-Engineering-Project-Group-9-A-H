package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.DateTrigger;
import it.unisa.diem.triggers.Trigger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DateTriggerController implements AbstractTriggerController {
    private LocalDate date=LocalDate.now();
    @FXML
    private Spinner<Integer> daySpinner;

    @FXML
    private Spinner<Integer> monthSpinner;

    @FXML
    private Spinner<Integer>yearSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Day Spinner setting. The spinner is set to show only the current and upcoming days in order to handle the choosing of previous dates.
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(date.getDayOfMonth(), date.lengthOfMonth()));
        daySpinner.getEditor().setAlignment(Pos.CENTER);
        daySpinner.getValueFactory().setValue(1);

        //Month Spinner setting
        monthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12));
        monthSpinner.getEditor().setAlignment(Pos.CENTER_LEFT);
        monthSpinner.getValueFactory().setValue(date.getMonthValue());

        //Year Spinner setting
        yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2023,2028 ));
        yearSpinner.getEditor().setAlignment(Pos.CENTER_RIGHT);
        yearSpinner.getValueFactory().setValue(2023);

        // The listeners are used to check if a different month or year is selected so that the day of the selected month
        // can be calculated again and there is no problem (Es: February with 30 days).

        monthSpinner.valueProperty().addListener((obs, oldValue, newValue) ->{
            int selectedMonth = newValue;
            int selectedYear = yearSpinner.getValue();
            //using the combination year of and length of month , it is possible to get the number of days in a month of a choosen year.
            int daysInMonth = YearMonth.of(selectedYear,selectedMonth).lengthOfMonth();
            daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(date.getDayOfMonth(), daysInMonth));
        });

        yearSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            int selectedMonth = monthSpinner.getValue();
            int selectedYear = newValue;
        
            int daysInMonth = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();
            daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(date.getDayOfMonth(), daysInMonth));
        });
    }
        

    @Override
    public Trigger createTrigger() {
        try{
        return new DateTrigger(daySpinner.getValue(),monthSpinner.getValue(), yearSpinner.getValue());}
        catch(Exception e){
            System.err.println("The Date trigger hasn't been created");
            return null;
        }
    }
    
}
