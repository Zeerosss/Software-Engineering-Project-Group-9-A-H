package it.unisa.diem.triggers;

import org.junit.Test;

import it.unisa.diem.rules.Observer;

import static org.junit.Assert.*;

import java.io.IOException;

public class ExecProgramTest {


    private static final Class IOException = null;

    // Can construct ExecProgram object with valid path, args, and observer
    @Test
    public void test_constructExecProgramWithValidPathArgsAndObserver() throws IOException {
        String path = "src/test/java/it/unisa/diem/triggers/test.exe";
        String args = "valid args";
        Observer observer = new Observer() {
            @Override
            public void update() {
                // do nothing
            }
        };
    
        ExecProgram execProgram = new ExecProgram(path, args, observer);
    
        assertNotNull(execProgram);
    }

    // Can get exit status of program after execution
    @Test
    public void test_getExitStatusAfterExecution() throws IOException {
        String path = "src/test/java/it/unisa/diem/triggers/test.exe";
        String args = "valid args";
        Observer observer = new Observer() {
            @Override
            public void update() {
                // do nothing
            }
        };
    
        ExecProgram execProgram = new ExecProgram(path, args, observer);
    

    
        assertNull(execProgram.getExitStatus());
    }

    // Can restart program and obtain new exit status
    @Test
    public void test_restartProgramAndGetNewExitStatus() throws IOException {
        String path = "src/test/java/it/unisa/diem/triggers/test.exe";
        String args = "valid args";
        Observer observer = new Observer() {
            @Override
            public void update() {
                // do nothing
            }
        };
    
        ExecProgram execProgram = new ExecProgram(path, args, observer);
    
        execProgram.restart();
        Integer exitStatus = execProgram.getExitStatus();
    
        assertNull(exitStatus);
    }


   

}



