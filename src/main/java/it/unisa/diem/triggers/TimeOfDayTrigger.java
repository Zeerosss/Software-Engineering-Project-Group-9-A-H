package it.unisa.diem.triggers;

import java.time.LocalTime;

public class TimeOfDayTrigger implements Trigger {
    LocalTime time;

    public TimeOfDayTrigger(){
    }
    public void timeTrigger(LocalTime time){
        this.time = time;
    }
    @Override
    public boolean isValidated(){
        LocalTime now = LocalTime.now();
        
        return( now.getHour() == time.getHour() && now.getMinute() == time.getMinute());
    }
    @Override 
    public String toString(){
        return("Time trigger set on "+ String.format("%02d", time.getHour()) +":"+String.format("%02d", time.getMinute()));
    }
}
