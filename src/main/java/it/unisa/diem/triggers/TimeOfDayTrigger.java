package it.unisa.diem.triggers;

public class TimeOfDayTrigger implements Trigger {
    private int hour;
    private int minute;

    public TimeOfDayTrigger(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
    }
    @Override
    public boolean isValidated(){

        return true;
    }
    
}
