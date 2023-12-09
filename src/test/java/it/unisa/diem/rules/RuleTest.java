package it.unisa.diem.rules;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.MessageAction.AlertJavaFX;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import it.unisa.diem.triggers.Trigger;
import it.unisa.diem.triggers.TimeOfDay.TimeOfDayTrigger;

public class RuleTest {
    @Test
    public void test_getters() {
        // Arrange
        boolean status = true;
        String name = "Test Rule";
        Trigger t = new TimeOfDayTrigger(LocalTime.now());
        Action a = new ShowDialogBoxAction("Test", new AlertJavaFX());
        boolean onlyOnce = true;
        Duration sleepingTime = Duration.ofMinutes(5);
        LocalDateTime nextUsefulDate = LocalDateTime.now().plus(sleepingTime).truncatedTo(ChronoUnit.MINUTES);
    
        Rule rule = new Rule(status, name, t, a, onlyOnce, sleepingTime);
        rule.setNextUsefulDate(nextUsefulDate);
    
        // Act & Assert
        assertEquals(status, rule.getStatus());
        assertEquals(name, rule.getName());
        assertEquals(t, rule.getTrigger());
        assertEquals(a, rule.getAction());
        assertEquals(onlyOnce, rule.isOnlyOnce());
        assertEquals(sleepingTime, rule.getSleepingTime());
        assertEquals(nextUsefulDate, rule.getNextUsefulDate());
    }
   
    
}
