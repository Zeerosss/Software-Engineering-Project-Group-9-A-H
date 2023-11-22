package it.unisa.diem.SEGroup9;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class PrimaryController implements Initializable{
    
    private static boolean firstUse = true;

    @FXML
    private Button createSet;

    @FXML
    private TableView<?> rulesTable;

    @FXML
    void switchToCreateView(ActionEvent event) throws IOException{
        firstUse = false;
        App.setRoot("createsetview");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(firstUse)
        createSet.setVisible(true);
       //future proofing
    }

}
    
