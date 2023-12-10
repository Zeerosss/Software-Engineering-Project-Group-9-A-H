package it.unisa.diem.triggers.ProgramExitStatus;


import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;


public class ProgramExitStatusTriggerTest {


    // ProgramExitStatusTrigger can be instantiated with a path and args.
    @Test
    public void test_instantiation() throws IOException {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test.exe";
        String args = "arg1,arg2";
        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);
        assertNotNull(trigger);
    }


    @Test
    public void test_program_exits_with_positive_exit_status() throws InterruptedException, IOException {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test_error.exe";
        String args = "arg1,arg2";

        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);
        while(trigger.isValidated())
        assertTrue(trigger.isFailed());
    }
    @Test
    public void test_program_exit_status_zero() throws IOException {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test.exe";
        String args = "arg1,arg2";

        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);

        while(trigger.isValidated())
        assertFalse(trigger.isFailed());
    }
}
