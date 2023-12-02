package it.unisa.diem.SEGroup9;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileAction;
import it.unisa.diem.actions.AudioFileAction.PlayAudioFileJavaFX;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class AudioActionController implements AbstractActionController {

    @FXML
    private AnchorPane fileChooserPane;
    @FXML
    private Label audioFilePathLabel;
    private File file;

    // This method is called when the controller is initialized
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a FileChooser to allow the user to select a WAV file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");

        // Show the file chooser dialog and get the selected file
        file = fileChooser.showOpenDialog(App.getStage());
        // Remove the comment of the next line if you want to print the path of the chosen file to the console
        // System.out.println(file);

        // Update the label with the name of the chosen file if file is not null
        if (file != null) {
            audioFilePathLabel.setText("Chosen file: " + file.getName());
        }
    }

    // Create and return an Action based on the selected file
    @Override
    public Action createAction() {
        // If the file is selected, create a PlayAudioFileAction with the file path
        if (this.isFilled()) {
            try {
                return (new PlayAudioFileAction(file.getPath(), new PlayAudioFileJavaFX() {             
                }));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    // Check if the file is selected
    @Override
    public boolean isFilled() {
        return (!(file == null));
    }
}
