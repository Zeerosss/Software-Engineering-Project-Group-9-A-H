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

import it.unisa.diem.actions.MessageAction.AlertJavaFX;
import it.unisa.diem.rules.AutoSaveManager;
import it.unisa.diem.rules.RuleListToJavaFX;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private Alert alert = new Alert(Alert.AlertType.WARNING);
    private AutoSaveManager autoSaveManager = new AutoSaveManager();
    //the application needs an istance
    private RuleListToJavaFX ruleListToJavaFX = RuleListToJavaFX.getInstance();
    
   
    @Override
    public void start(Stage stage) throws IOException {
        
        //getting the instale at the start of the program
        try {
            AlertController.initialize(new AlertJavaFX());
            autoSaveManager.load();
            ruleListToJavaFX.restartTrigger();


        } catch (IOException e) {
            
            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("error in reload saves");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        } catch (Exception e) {
            
            
        }
        
        scene = new Scene(loadFXML("ruleview"));
        Image icon = new Image("file:src/main/resources/it/unisa/diem/logo_unisa.png");
        stage.getIcons().add(icon);
        stage.setTitle("IFTTT application");
        String css = this.getClass().getResource("application.css").toExternalForm(); 
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            System.out.println("ciao mondo");
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exit");
            alert.setContentText("Are you sure you want to exit?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                
                    System.exit(0);
              
            }});
           
        });

        

        stage.show(); 

        
    }

/**
* Retrieves the primary stage associated with the current scene.
 * 
 * @return The primary stage.
 */
    public static Stage getStage(){
        return ((Stage)scene.getWindow());
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }
}