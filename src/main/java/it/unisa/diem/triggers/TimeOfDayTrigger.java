package it.unisa.diem.triggers;

import java.time.LocalTime;

public class TimeOfDayTrigger implements Trigger {
    LocalTime time;

    public TimeOfDayTrigger(LocalTime time){
        this.time = time;
    }
    @Override
    public boolean isValidated(){
        LocalTime now = LocalTime.now();
        
        return( now.getHour() == time.getHour() && now.getMinute() == time.getMinute());
    }
    
}
