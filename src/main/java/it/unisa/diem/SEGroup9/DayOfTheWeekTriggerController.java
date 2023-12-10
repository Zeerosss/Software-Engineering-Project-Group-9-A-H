package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.DayOfWeek.DayOfTheWeekTrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DayOfTheWeekTriggerController implements AbstractTriggerController {

    @FXML
    private Spinner<String> dayOfTheWeekSpinnerID;

    private ObservableList<String> daysOfTheWeek;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        daysOfTheWeek = FXCollections.observableArrayList();
        daysOfTheWeek.addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        dayOfTheWeekSpinnerID.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(daysOfTheWeek));
        
    }

    @Override
    public Trigger createTrigger() {
        
        return new DayOfTheWeekTrigger(DayOfWeek.valueOf(dayOfTheWeekSpinnerID.getValue().toUpperCase()));
    }

}
