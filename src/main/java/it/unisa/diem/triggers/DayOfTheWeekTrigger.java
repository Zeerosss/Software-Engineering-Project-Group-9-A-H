package it.unisa.diem.triggers;

import java.time.DayOfWeek;
import java.time.LocalDate;

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
        return "DayOfTheWeekTrigger [day=" + day + "]";
    }

}
