package it.unisa.diem.triggers.TimeOfDay;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

import it.unisa.diem.triggers.Trigger;


public class TimeOfDayTriggerTest {
    @Test
    public void testIsValidated() {
        LocalTime time = LocalTime.now();
        Trigger trigger = new TimeOfDayTrigger(time);
        assertTrue(trigger.isValidated());
    }
}
