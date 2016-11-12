import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;

import clases.Cuadrante;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Recinto;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Assert;

public class MapaTest {

	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void crearUnMapaConRecintosGrillasYObstaculos() throws IOException, InterruptedException{
		
		Stage primaryStage = new Stage();
		
		Mapa mapa = new Mapa(1024,768);
		Scene scene = new Scene(mapa);
      	
		Recinto r1 = new Recinto(0,0,100,100, "r1");
		Recinto r2 = new Recinto(200,200,200,200,"r2");
		Grilla g1 = new Grilla (r1,30,1);
		g1.prepararGrillaParaDibujo();
		r1.setGrilla(g1);
		      	
		mapa.agregarRecinto(r1);
      	mapa.agregarRecinto(r2);    	
      	mapa.dibujarMapa();
      	
      	//Agrega un recinto a partir de un mapa ya dibujado
      	Recinto r3 = new Recinto(600,0,200,200,"r3");
      	mapa.agregarRecinto(r3);
      	mapa.dibujarMapa();
      	
		primaryStage.setScene(scene);
      	primaryStage.setResizable(false);
      	primaryStage.show();
      	//Thread.sleep(3000);
		
	}
		
	@Test
	public void x(){
		Mapa mapa = new Mapa(800,600);
		Recinto rMapa = new Recinto((float)0,(float)0,(float)800,(float)600,"rmapa");
		mapa.setRecintoMapa(rMapa);
		mapa.getRecintoMapa().setGrilla(new Grilla(mapa.getRecintoMapa(),(float)200,(int)1));
		Recinto r1 = new Recinto(10,10,10,10, "r1");
		Grilla g1 = new Grilla (r1,5,1);
		r1.setGrilla(g1);
		r1.verificarDisponibilidadDeLaGrilla();
		mapa.agregarRecinto(r1);
		mapa.getRecintoMapa().setGrilla(mapa.getGrilla());
		mapa.getRecintoMapa().agregarObstaculo(new Obstaculo((float)10, (float)10, (float)10, (float)10, Obstaculos.Recinto));
		mapa.getRecintoMapa().verificarDisponibilidadDeLaGrilla();
		for (Cuadrante[] filaCuadrante : mapa.getRecintoMapa().getGrilla().getMatrizDeCuadrantes()) {
			for (Cuadrante cuadrante : filaCuadrante) {
				System.out.println("Cuadrante: " + cuadrante.getId() + " disponible :" + cuadrante.estaDisponible());
			}
		}
		
	}
	
	
}
