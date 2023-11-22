package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

public class TimeTriggerController implements Initializable {
     
    @FXML
    private Spinner<Integer> hSpinner;

    @FXML
    private Spinner<Integer> minSpinner;

    public LocalTime getTime() {
        LocalTime time = LocalTime.of(hSpinner.getValue(), minSpinner.getValue());
        return time;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 23));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59));
        hSpinner.getValueFactory().setValue(0);
        minSpinner.getValueFactory().setValue(0);
    }
}
