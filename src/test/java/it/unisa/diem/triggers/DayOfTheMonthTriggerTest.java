package it.unisa.diem.triggers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DayOfTheMonthTriggerTest {
    @Test
    public void isValidatedTest() {
        LocalDate localDate = LocalDate.now();
        DayOfTheMonthTrigger trigger = new DayOfTheMonthTrigger(localDate.getDayOfMonth()-1);
        assertFalse(trigger.isValidated());
        
    }

    @Test
    public void differentMonthTest(){
        LocalDate localDate = LocalDate.now().withMonth(5);
        DayOfTheMonthTrigger trigger = new DayOfTheMonthTrigger(localDate.getDayOfMonth());
        
        assertTrue(trigger.isValidated());
    }
    
    @Test
    public void moreTriggerSameMonth(){
        LocalDate localDate = LocalDate.now();
        DayOfTheMonthTrigger trigger1 = new DayOfTheMonthTrigger(localDate.getDayOfMonth());
        DayOfTheMonthTrigger trigger2 = new DayOfTheMonthTrigger(localDate.getDayOfMonth());

        assertTrue(trigger1.isValidated());
        assertTrue(trigger2.isValidated());
    }

    @Test
    public void testDayOfTheMonthTriggerToString() {
        DayOfTheMonthTrigger dayTrigger = new DayOfTheMonthTrigger(10);
        String choosenDay = "Day of month choosen: \n " + 10;
        assertEquals(choosenDay, dayTrigger.toString());
    }
}
