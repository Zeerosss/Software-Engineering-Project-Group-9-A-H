package it.unisa.diem.triggers;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.CompletableFuture;

public class ProgramExitStatusTriggerTest {


    // ProgramExitStatusTrigger can be instantiated with a path and args.
    @Test
    public void test_instantiation() {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test.exe";
        String args = "arg1 arg2";
        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);
        assertNotNull(trigger);
    }

    // ProgramExitStatusTrigger is not validated if exit status is null.
    @Test
    public void test_validation_exit_status_null() {
        String path = "path/to/program";
        String args = "arg1 arg2";
        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);
        assertFalse(trigger.isValidated());
    }

    @Test
    public void test_program_exits_with_positive_exit_status() throws InterruptedException {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test_error.exe";
        String args = "arg1 arg2";

        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);
        while(trigger.isValidated())
        assertTrue(trigger.isFailed());
    }
    @Test
    public void test_program_exit_status_zero() {
        String path = "src\\test\\java\\it\\unisa\\diem\\triggers\\test.exe";
        String args = "arg1 arg2";

        ProgramExitStatusTrigger trigger = new ProgramExitStatusTrigger(path, args);

        while(trigger.isValidated())
        assertFalse(trigger.isFailed());
    }
}
