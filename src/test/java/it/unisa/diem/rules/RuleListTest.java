package it.unisa.diem.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;


import it.unisa.diem.actions.MessageAction.AlertJavaFX;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import it.unisa.diem.triggers.TimeOfDayTrigger;

public class RuleListTest {


       @Test
    public void test_addRule_successfully() {
        RuleList ruleList = RuleList.getInstance();
        ruleList.getRules().clear();
        ruleList.ruleAdd(true, "Rule 1", new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("Test",new AlertJavaFX()), true, null, LocalDateTime.now());
        List<Rule> rules = ruleList.getRules();
        assertEquals(1, rules.size());
        assertEquals("Rule 1", rules.get(0).getName());
    }

        // The method successfully removes the given rule from the list of rules.
    @Test
    public void test_remove_rule_from_list() {
        // Arrange
        RuleList ruleList = RuleList.getInstance();
    
        ruleList.ruleAdd(true, "Rule 1", new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("Test",new AlertJavaFX()), true, null, LocalDateTime.now());
        Rule rule = ruleList.getRules().get(0);
        // Act

        ruleList.ruleDelete(rule);
    
        // Assert
        assertFalse(ruleList.getRules().contains(rule));
    }
    @Test
    public void test_returns_true_if_rules_list_is_empty() {
        RuleList ruleList = RuleList.getInstance();
        ruleList.getRules().clear();
        assertTrue(ruleList.isEmpty());
    }

    @Test
    public void test_returns_false_if_rules_list_is_not_empty() {
        RuleList ruleList = RuleList.getInstance();
        ruleList.ruleAdd(true, "Rule 1", new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("Test",new AlertJavaFX()), true, null, LocalDateTime.now()); 
       assertFalse(ruleList.isEmpty());
    }


}
