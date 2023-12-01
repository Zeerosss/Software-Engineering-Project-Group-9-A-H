package it.unisa.diem.triggers;

import java.io.Serializable;

public interface Trigger extends Serializable {
    public boolean isValidated();
}
