import java.io.IOException;

import org.junit.Test;

import application.Main;
import clases.Mapa;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapaTest {

	@Test
	public void crearUnMapaConRecintosGrillasYObstaculos() throws IOException{
		
		Stage primaryStage = new Stage();
		Main aplicacion = new Main();
		aplicacion.start(primaryStage);

		Mapa mapa = new Mapa(800,600);
		
		Scene scene = new Scene(mapa);
      	primaryStage.setScene(scene);
      	primaryStage.setResizable(false);
      	primaryStage.show();
		
		
	}
	
	
}
