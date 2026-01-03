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
        // 2. ASIGNACIÓN CRUCIAL: Guardamos el stage que nos da JavaFX
        stage = primaryStage; 
        
        mostrarVistaPrincipal();
    }

    public static void mostrarVistaPrincipal() throws Exception {
        // Ajusta la ruta a donde tengas tu archivo principal
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/ec/edu/espoch/gestornotasfx/view-estudiantes.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    // 3. El método para moverte entre vistas
    public static void cambiarVista(String fxml) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/ec/edu/espoch/gestornotasfx/" + fxml + ".fxml"));
            // Aquí ya no marcará error porque 'stage' ya tiene valor
            stage.getScene().setRoot(root); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
