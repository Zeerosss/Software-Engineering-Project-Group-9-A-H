package it.unisa.diem.triggers.TimeOfDay;

import static org.junit.Assert.assertEquals;
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
        // Returns a string with the time trigger set on, with leading zeros for hours and minutes less than 10
    @Test
    public void test_leading_zeros() {
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(LocalTime.of(9, 5));
        String expected = "Time trigger set on: \n09:05";
        String actual = trigger.toString();
        assertEquals(expected, actual);
    }
}
