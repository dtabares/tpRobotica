import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;

import clases.Grilla;
import clases.Mapa;
import clases.Recinto;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapaTest {

	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void crearUnMapaConRecintosGrillasYObstaculos() throws IOException, InterruptedException{
		
		Stage primaryStage = new Stage();
		Mapa mapa = new Mapa(800,600);
		Scene scene = new Scene(mapa);
      	
		Recinto r1 = new Recinto(0,0,100,100);
		Recinto r2 = new Recinto(200,200,200,200);
		Grilla g1 = new Grilla (r1,30);
		g1.prepararGrillaParaDibujo();
		r1.setGrilla(g1);
		
		mapa.agregarRecinto(r1);
      	mapa.agregarRecinto(r2);
      	Collection <Grilla> grillas = new LinkedList<Grilla>();
      	grillas.add(g1);
      	mapa.mostrarGrillas(grillas);
      	
		primaryStage.setScene(scene);
      	primaryStage.setResizable(false);
      	primaryStage.show();
      	Thread.sleep(5000);
		
	}
	
	
}
