package it.unisa.diem.triggers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;

import org.junit.Test;

public class DateTriggerTest {
    @Test
    public void isValidatedTest() {
        LocalDate date = LocalDate.now();
        DateTrigger trigger = new DateTrigger(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
        assertTrue(trigger.isValidated());

    }

    @Test
    public void futureDateTest(){
        LocalDate date = LocalDate.now().plusDays(7);
        DateTrigger trigger = new DateTrigger(date.getDayOfMonth(),date.getMonthValue(),date.getYear());
        assertFalse(trigger.isValidated());
    }

    @Test
    public void dateToStringTest(){
        DateTrigger dateTrigger = new DateTrigger(15, 6, 2024); 
        assertEquals("The date choosen is: \n15-06-2024", dateTrigger.toString());
    }


}
