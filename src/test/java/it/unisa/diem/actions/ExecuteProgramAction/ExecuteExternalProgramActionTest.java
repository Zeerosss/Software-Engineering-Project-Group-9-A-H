package it.unisa.diem.actions.ExecuteProgramAction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ExecuteExternalProgramActionTest {
    @Test
    public void testStartAction() {
        
        File file = new File("src\\test\\java\\it\\unisa\\diem\\triggers\\test.exe");
        List<String> parameterList = new ArrayList<>(Arrays.asList("arg1", "arg2"));
        ExecuteExternalProgramAction action = new ExecuteExternalProgramAction(file, parameterList);
        try {
            action.startAction();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue("Output file should exist", new File("testLog\\" + file.getName() + ".txt").exists());
    }
    
    @Test
    public void testExecuteExternalProgramActionToString(){
        File file = new File("testToString.txt");
        List<String> parameterList = new ArrayList<>(Arrays.asList("arg1", "arg2"));
        ExecuteExternalProgramAction action = new ExecuteExternalProgramAction(file, parameterList);
        String expected = "Execute this external program:\n" + "testToString.txt";
        String actual = action.toString();
        assertEquals(expected, actual);
    }
}
