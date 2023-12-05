package it.unisa.diem.triggers;

import java.time.LocalDate;
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
        return "Day of month choosen: \n " + localDate.getDayOfMonth();
    }
    }
    

