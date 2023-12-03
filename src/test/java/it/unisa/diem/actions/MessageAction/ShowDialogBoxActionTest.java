package it.unisa.diem.actions.MessageAction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;


public class ShowDialogBoxActionTest {
    @Test
    public void testActionIsCreated() {
        ShowDialogBoxAction a= new ShowDialogBoxAction("Test",new AlertJavaFX());
        assertNotNull(a); //test to verify if a is created correctly
        
    }
    @Test
    public void testCorrectMessage(){
        ShowDialogBoxAction a= new ShowDialogBoxAction("Test",new AlertJavaFX());
        assertSame(a.getMessage(), "Test"); //test to verify if the message is actually the one we want
    }
}
