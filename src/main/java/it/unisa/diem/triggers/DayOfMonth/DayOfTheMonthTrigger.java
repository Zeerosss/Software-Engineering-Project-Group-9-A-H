package it.unisa.diem.triggers.DayOfMonth;

import java.time.LocalDate;

import it.unisa.diem.triggers.Trigger;
//Class to create the Day of the month trigger
public class DayOfTheMonthTrigger implements Trigger{
    private LocalDate localDate;

    public DayOfTheMonthTrigger(int day){
    localDate = LocalDate.now().withDayOfMonth(day);

    }
    @Override
    public boolean isValidated() {
        LocalDate date = LocalDate.now();
        return localDate.getDayOfMonth() == date.getDayOfMonth();
        
    }
    @Override
    public String toString() {
        return "The chosen day of the month is: \n " + localDate.getDayOfMonth();
    }
    }
    

