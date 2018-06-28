// Main application class that loads a
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class VentanaInicio extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Util.ingresarStockDePrueba();
        
        Parent root = 
           FXMLLoader.load(getClass().getResource("VentanaInicio.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Bin Packing - ADA");
        stage.setScene(scene);
        stage.show();
        
    }
    
   public static void main(String[] args) {
      launch(args);
   }
}