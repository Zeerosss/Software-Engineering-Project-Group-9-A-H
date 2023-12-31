package it.unisa.diem.triggers.TimeOfDay;

import java.time.LocalTime;

import it.unisa.diem.triggers.Trigger;

public class TimeOfDayTrigger implements Trigger {
    
    private LocalTime time;
    
    public TimeOfDayTrigger(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean isValidated(){
        LocalTime now = LocalTime.now();
        
        return( now.getHour() == time.getHour() && now.getMinute() == time.getMinute());
    }
    @Override 
    public String toString(){
        return "Time trigger set on: \n"+ String.format("%02d", time.getHour()) +":"+String.format("%02d", time.getMinute());
    }
}
