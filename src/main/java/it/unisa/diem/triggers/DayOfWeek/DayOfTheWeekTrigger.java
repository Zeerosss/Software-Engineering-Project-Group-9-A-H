package it.unisa.diem.triggers.DayOfWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;

import it.unisa.diem.triggers.Trigger;

public class DayOfTheWeekTrigger implements Trigger{

    private DayOfWeek day;

    public DayOfTheWeekTrigger(DayOfWeek day){
        this.day = day;
    }

    @Override
    public boolean isValidated() {
        return LocalDate.now().getDayOfWeek().equals(day);
    }

    @Override
    public String toString() {
        return "Day of the week trigger\nset on: " + day.toString();
    }

}
