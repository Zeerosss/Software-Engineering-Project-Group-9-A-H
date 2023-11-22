package it.unisa.diem.triggers;

public class TimeOfDayTrigger implements Trigger {
    private int hour;
    private int minute;

    public TimeOfDayTrigger(){
    }
    public void timeTrigger(int hour,int minute){
        this.hour=hour;
        this.minute=minute;
    }
    @Override
    public boolean isValidated(){

        return true;
    }
    @Override
    public String toString() {
        return "Time Of Day Trigger";
    }
    
}
