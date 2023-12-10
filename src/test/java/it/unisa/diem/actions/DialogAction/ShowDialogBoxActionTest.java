package it.unisa.diem.actions.DialogAction;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ShowDialogBoxActionTest {
    @Test
    public void testActionIsCreated() {
        ShowDialogBoxAction a= new ShowDialogBoxAction("Test",new AlertJavaFX());
        assertNotNull(a); //test to verify if a is created correctly
        
    }
}
