package it.unisa.diem.triggers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTrigger implements Trigger {
    private LocalDate date;
    
    public DateTrigger(int day, int month, int year){
        date = LocalDate.now().withDayOfMonth(day).withMonth(month).withYear(year);
    }
    @Override
    public boolean isValidated() {
        LocalDate newDate = LocalDate.now();
        return date.isEqual(newDate);
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "The date choosen is: \n" + date.format(formatter);
    }

    
}
