package it.unisa.diem.triggers.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

public class DayOfTheWeekTriggerTest {
    @Test
    public void testIsValidated() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        DayOfTheWeekTrigger trigger = new DayOfTheWeekTrigger(currentDay);

        assertTrue(trigger.isValidated());

        for (DayOfWeek otherDay : DayOfWeek.values()) {
            DayOfTheWeekTrigger falseTrigger = new DayOfTheWeekTrigger(otherDay);
            if (otherDay != currentDay) {
                assertFalse("Il trigger non dovrebbe essere valido per " + otherDay, falseTrigger.isValidated());
            }
        }
    }
    @Test
    public void testDayOfTheWeekTriggerToString(){
        DayOfTheWeekTrigger trigger = new DayOfTheWeekTrigger(DayOfWeek.MONDAY);
        String expected = "Day of the week trigger\nset on: " + "MONDAY";
        String actual = trigger.toString();
        assertEquals(expected, actual);
    }
}
