package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileAction;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class AudioActionController implements AbstractActionController{

    @FXML
    private AnchorPane fileChooserPane;
    private File file;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");
        file = fileChooser.showOpenDialog(App.getStage());
        System.out.println(file);
        
            
        
        //TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public Action createAction() {
        // TODO Auto-generated method stub
        if( this.isFilled()){
            return( new PlayAudioFileAction(file.getPath()));
        }else return null;
        
    }

    @Override
    public boolean isFilled() {
        return (!(file == null));
    }
    
}
