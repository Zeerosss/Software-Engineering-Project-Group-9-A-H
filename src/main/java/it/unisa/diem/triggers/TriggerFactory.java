package it.unisa.diem.triggers;

import java.time.LocalTime;

public class TriggerFactory {
    

    /**
     * 
     */
    public static Trigger createTrigger(int triggerType,LocalTime time){
        if(triggerType == 0)
        return new TimeOfDayTrigger(time);
        else return null;
        
    }

    
}
