package ec.edu.espoch.gestornotasfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Stage stage; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage; 
        
        mostrarVistaPrincipal();
    }

    public static void mostrarVistaPrincipal() throws Exception {
        
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/ec/edu/espoch/gestornotasfx/view-estudiantes.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    
    public static void cambiarVista(String fxml) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/ec/edu/espoch/gestornotasfx/" + fxml + ".fxml"));
            
            stage.getScene().setRoot(root); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
