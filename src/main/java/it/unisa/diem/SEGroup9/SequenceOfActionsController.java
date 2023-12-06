package it.unisa.diem.SEGroup9;

import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SequenceOfActionsController implements AbstractActionController{
    
    @FXML
    private Button saveButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox scrollPaneVbox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        scrollPaneVbox.setFillWidth(true);
        String [] actionStrings={
            "Play an audio file", "Display a message","Copy File","Move File","Delete File","Append a message to a file"};

        for(String s: actionStrings){
            CheckBox checkBox = new CheckBox();
            Label label= new Label(s);

            HBox hbox = new HBox(checkBox, label);
            hbox.setSpacing(5);
            scrollPaneVbox.getChildren().add(hbox); 
            
        }
        
    }

    @Override
    public Action createAction() {
        throw new UnsupportedOperationException("Unimplemented method 'isFilled'");
    }

    @Override
    public boolean isFilled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFilled'");
    }


}
