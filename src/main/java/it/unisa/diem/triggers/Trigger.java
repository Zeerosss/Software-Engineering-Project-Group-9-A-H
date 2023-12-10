package it.unisa.diem.triggers;

import java.io.Serializable;

public interface Trigger extends Serializable {
    public boolean isValidated();
    
    public default void startTrigger(){};
    
    public default Boolean isFailed(){
    return false;
    };
}
