package it.unisa.diem.SEGroup9;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.actions.AppendTextAction.AppendTextAction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class AppendTextActionController implements AbstractActionController{

    private File file;
    @FXML
    private Label textFilePathLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create a FileChooser to allow the user to select a txt file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Open Resource File");

        // Show the file chooser dialog and get the selected file
        file = fileChooser.showOpenDialog(App.getStage());
        // Remove the comment of the next line if you want to print the path of the chosen file to the console
        // System.out.println(file);

        // Update the label with the name of the chosen file if file is not null
        if (file != null) {
            textFilePathLabel.setText("Chosen file: " + file.getName());
        }
    }

    @Override
    public Action createAction() {
        if (this.isFilled()) {
            return (new AppendTextAction(file.getPath(), null));
        } else {
            return null;
        }
    }

    @Override
    public boolean isFilled() {
       return(true);
    }
}
