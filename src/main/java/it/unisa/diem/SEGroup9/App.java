package it.unisa.diem.SEGroup9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import it.unisa.diem.actions.DialogAction.AlertJavaFX;
import it.unisa.diem.rules.AutoSaveManager;
import it.unisa.diem.rules.RuleListToJavaFX;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    public final static String SAVE_PATH= "src\\main\\resources\\it\\unisa\\diem\\rules.date";
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    // AutoSaveManager instance for managing rule auto-saving
    private AutoSaveManager autoSaveManager = new AutoSaveManager(SAVE_PATH);

    // RuleListToJavaFX instance for managing rules in JavaFX
    private RuleListToJavaFX ruleListToJavaFX = RuleListToJavaFX.getInstance();

    @Override
    public void start(Stage stage) throws IOException { 
        AlertController.initialize(new AlertJavaFX());
        // Load saved rules at the start of the program
        try {
           
            autoSaveManager.load();
            ruleListToJavaFX.restartTrigger();


        } catch (IOException e) {
        AlertController.displayAlertWarning("Warning", null,"Error in reloading saves");
        } catch (ClassNotFoundException e) {
        AlertController.displayAlertWarning("Warning", null,e.toString());
        } catch (Exception e) {
        AlertController.displayAlertWarning("Warning", null,e.toString());    // Handle other exceptions
        }

        // Set up the main scene
        scene = new Scene(loadFXML("ruleview"));
        Image icon = new Image("file:src/main/resources/it/unisa/diem/logo_unisa.png");
        stage.getIcons().add(icon);
        stage.setTitle("IFTTT application");
        
        // Set up CSS for the scene
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Some stage changes
        stage.setResizable(false);
        stage.setScene(scene);

        // Set an action for the close button
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exit");
            alert.setContentText("Are you sure you want to exit?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Exit the application when the user clicks OK
                    System.exit(0);
                }
                else{
                    event.consume();
                }
            });
        });

        stage.show();
    }

    /**
     * Retrieves the primary stage associated with the current scene.
     * 
     * @return The primary stage.
     */
    public static Stage getStage() {
        return ((Stage) scene.getWindow());
    }

    /**
     * Changes the root FXML for the current scene.
     * 
     * @param fxml The name of the FXML file to load.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file and returns its root.
     * 
     * @param fxml The name of the FXML file to load.
     * @return The root of the loaded FXML file.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main method to launch the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }
}
