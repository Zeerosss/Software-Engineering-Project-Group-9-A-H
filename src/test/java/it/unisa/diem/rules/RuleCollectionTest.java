package it.unisa.diem.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalTime;

import org.junit.Test;
import it.unisa.diem.actions.MessageAction.ShowDialogBoxAction;
import it.unisa.diem.triggers.TimeOfDayTrigger;

public class RuleCollectionTest {
      // loads a file with serialized Rule objects and adds them to the ObservableList of rules
    @Test
    public void test_load_file_and_add_to_list() {
        // Arrange
        RuleCollection ruleCollection = RuleCollection.getInstance();
        String path = "testfile.date";
    
        // Act
        try {
            boolean result = ruleCollection.load(path);
        
            // Assert
            assertTrue(result);
            assertFalse(ruleCollection.isEmpty());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
       // Saves a file with the given path if it does not exist
    @Test
    public void test_save_file_does_not_exist() {
        String path = "testfile.date";
    
        // Delete the file if it already exists
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    
        // Call the save method
        try {
            RuleCollection ruleCollection = RuleCollection.getInstance();
            ruleCollection.save(path);
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    
        // Check if the file is created
        assertTrue(file.exists());
    }

        // Writes the rules to the file using ObjectOutputStream
    @Test
    public void test_write_rules_to_file() {
        String path = "testfile.date";
    
        // Create a rule collection with some rules
        RuleCollection ruleCollection = RuleCollection.getInstance();
        ruleCollection.ruleAdd(true, "Rule 1", new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("ciao"));
        ruleCollection.ruleAdd(false, "Rule 2",  new TimeOfDayTrigger(LocalTime.now()), new ShowDialogBoxAction("ciao"));
    
        // Call the save method
        try {
            ruleCollection.save(path);
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    
        // Read the saved rules from the file
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Rule[] savedRules = (Rule[]) objectInputStream.readObject();
        
            // Check if the saved rules match the original rules
            assertEquals(2, savedRules.length);
            assertEquals("Rule 1", savedRules[0].getName());
            assertEquals("Rule 2", savedRules[1].getName());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
