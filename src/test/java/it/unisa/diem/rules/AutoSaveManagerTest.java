package it.unisa.diem.rules;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import it.unisa.diem.actions.MessageAction.AlertJavaFX;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import it.unisa.diem.triggers.TimeOfDay.TimeOfDayTrigger;

public class AutoSaveManagerTest {
        @Test
    public void test_save_rules_to_file_when_notified_of_changes() {
        // Arrange
        AutoSaveManager autoSaveManager = new AutoSaveManager("rulestest.date");
        RuleList ruleList = RuleList.getInstance();
        ruleList.ruleAdd(true, "Rule 1", new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("Test",new AlertJavaFX()), true, null, LocalDateTime.now()); 
        Rule rule = ruleList.getRules().get(0);
        File file = new File("src\\main\\resources\\it\\unisa\\diem\\rules.date");
        // Act
        autoSaveManager.update();
    
        // Assert
        // Check if the file exists and contains the saved rule
        assertTrue(file.exists());
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object[] rulesArray = (Object[]) objectInputStream.readObject();
            boolean ruleFound = false;
            for (Object obj : rulesArray) {
                if (obj instanceof Rule) {
                    Rule savedRule = (Rule) obj;
                    if (savedRule.getName().equals(rule.getName())) {
                        ruleFound = true;
                        break;
                    }
                }
            }
            assertTrue(ruleFound);
        } catch (Exception e) {
            fail("Exception thrown while reading file");
        }
    }
}
