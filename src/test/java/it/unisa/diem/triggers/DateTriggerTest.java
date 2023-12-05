package it.unisa.diem.triggers;

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
}
