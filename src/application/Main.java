package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
				
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		System.out.println("Iniciando aplicacion...");
		BorderPane borderPane = (BorderPane) FXMLLoader.load(getClass().getResource("MainInterfaz.fxml"));
        
//		PruebaDeConceptoTest pc = new PruebaDeConceptoTest();
//		pc.ejecutarPruebas();
//		borderPane.setCenter(pc.mapa.getCanvas());
		
		Scene scene = new Scene(borderPane);
        primaryStage.setTitle("TP Robotica");
      	primaryStage.setScene(scene);
      	primaryStage.setResizable(false);
      	primaryStage.show();
			
	}
		
	public static void main(String[] args) {
		launch(args);
	}

}
