package it.unisa.diem.SEGroup9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
   
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ruleview"));
        Image icon = new Image("file:src/main/resources/it/unisa/diem/logo_unisa.png");
        stage.getIcons().add(icon);
        stage.setTitle("IFTTT application");
        String css = this.getClass().getResource("application.css").toExternalForm(); 
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setScene(scene);
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

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }
}