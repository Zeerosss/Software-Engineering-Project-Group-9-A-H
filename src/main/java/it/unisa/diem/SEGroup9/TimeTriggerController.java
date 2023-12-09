package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.TimeOfDay.TimeOfDayTrigger;
import it.unisa.diem.triggers.AbstractTriggerController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;


public class TimeTriggerController implements AbstractTriggerController {
     
    @FXML
    private Spinner<Integer> hSpinner;

    @FXML
    private Spinner<Integer> minSpinner;
    
    
    /*
     * implementation designed for formatting
     * numbers with a specific format. 
     * In this implementation, the format %d02 ensures that
     * the number is always displayed with two digits. 
     */
    private final StringConverter<Integer> converter = new StringConverter<Integer>() {
            @Override
            public String toString(final Integer number) {
                if (number == null) {
                    return "";
                }
                return String.format("%02d", number.intValue());
            }

            @Override
            public Integer fromString(String string) {
                try {
                    if (string == null || string.trim().isEmpty()) {
                        return null;
                    }
                    return Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            
        };
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 23));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59));
        hSpinner.getValueFactory().setValue(0);
        minSpinner.getValueFactory().setValue(0);
        hSpinner.getValueFactory().setConverter(converter);
        minSpinner.getValueFactory().setConverter(converter);
        hSpinner.getEditor().setAlignment(Pos.CENTER_RIGHT);
        minSpinner.getEditor().setAlignment(Pos.CENTER_RIGHT);
    }
    @Override
    public Trigger createTrigger() {
        LocalTime time = LocalTime.of(hSpinner.getValue(), minSpinner.getValue());
        return (new TimeOfDayTrigger(time));
        
    }
    
}
