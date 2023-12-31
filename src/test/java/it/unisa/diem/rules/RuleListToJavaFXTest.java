package it.unisa.diem.rules;

import org.junit.Test;

import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.DialogAction.ShowDialogBoxAction;
import it.unisa.diem.triggers.Date.DateTrigger;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDate;

public class RuleListToJavaFXTest {


    // RuleListToJavaFX can be instantiated successfully
    @Test
    public void test_instantiation_success() {
        RuleListToJavaFX ruleListToJavaFX = RuleListToJavaFX.getInstance();
        assertNotNull(ruleListToJavaFX);
    }

    // Rule can be added to the RuleList using RuleListToJavaFX
    @Test
    public void test_add_rule() {
        RuleListToJavaFX ruleListToJavaFX = RuleListToJavaFX.getInstance();
        ruleListToJavaFX.ruleAdd(true, "Test Rule", new DateTrigger(1,1,1), new ShowDialogBoxAction("", null), false, Duration.ofSeconds(5));
        assertFalse(ruleListToJavaFX.isEmpty());
    }



    // RuleListToJavaFX cannot delete a null rule from the RuleList
    @Test
    public void test_delete_null_rule() {
        RuleListToJavaFX ruleListToJavaFX = RuleListToJavaFX.getInstance();
        try {
            ruleListToJavaFX.ruleDelete(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

}