package it.unisa.diem.actions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ExecuteExternalProgramActionTest {
    @Test
    public void testStartAction() {
        // Creazione di un oggetto ExecuteExternalProgramAction con parametri validi
        File file = new File("C:\\Users\\Admin\\OneDrive\\Documenti\\NetBeansProjects\\ExecutionExternProgram\\ciao.exe");
        List<String> parameterList = new ArrayList<>(Arrays.asList("arg1", "arg2"));
        ExecuteExternalProgramAction action = new ExecuteExternalProgramAction(file, parameterList);

        // Verifica che l'esecuzione dell'azione non sollevi eccezioni
        try {
            action.startAction();
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Puoi aggiungere ulteriori asserzioni specifiche a seconda dei risultati attesi
        assertTrue("Output file should exist", new File("programResoult").exists());
    }
    @Test
    public void testStartActionWithException() {
        
        File file = new File("path/to/nonexistent/program");
        List<String> parameterList = new ArrayList<>(Arrays.asList("arg1", "arg2"));
        ExecuteExternalProgramAction action = new ExecuteExternalProgramAction(file, parameterList);

        action.startAction();
        
        assertTrue("Error file should exist", new File("ErrorLog").length() != 0);
    }
}
