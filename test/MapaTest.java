import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;

import clases.Cuadrante;
import clases.Grilla;
import clases.Mapa;
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
	
//	@Test
//	public void x() throws IOException, InterruptedException{
//		Stage primaryStage = new Stage();
//		Mapa mapa = new Mapa(1024,768);
//		Scene scene = new Scene(mapa);
//		Recinto rMapa = new Recinto((float)0,(float)0,(float)1024,(float)768,"rmapa");
//		mapa.setRecintoMapa(rMapa);
//		mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),(float)10,(int)1));
//		mapa.getGrilla().prepararGrillaParaDibujo();
//		mapa.getRecintoMapa().setGrilla(mapa.getGrilla());
//		Recinto r1 = new Recinto(0,0,100,100, "r1");
//		Grilla g1 = new Grilla (r1,30,1);
//		g1.prepararGrillaParaDibujo();
//		r1.setGrilla(g1);
//		mapa.agregarRecinto(r1);
//		mapa.buscarCuadrantePorId(5).getRectangle().setFill(Color.BLUE);;
//		mapa.dibujarMapa();
//		primaryStage.setScene(scene);
//      	primaryStage.setResizable(false);
//      	primaryStage.show();
//      	
//      	Thread.sleep(3000);
//      	
//      	mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),(float)5,(int)1));
//		mapa.getGrilla().prepararGrillaParaDibujo();
//		mapa.buscarCuadrantePorId(5).getRectangle().setFill(Color.BLUE);;
//		
//		mapa.dibujarMapa();
//		primaryStage.setScene(scene);
//      	primaryStage.setResizable(false);
//      	primaryStage.show();
//      	
//      	Thread.sleep(3000);
//	}
	
	
//	@Test
//	public void y(){
//		Mapa mapa = new Mapa(20,20);
//		Recinto rMapa = new Recinto((float)0,(float)0,(float)20,(float)20,"rmapa");
//		mapa.setRecintoMapa(rMapa);
//		mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),(float)10,(int)1));
//		mapa.getGrilla().prepararGrillaParaDibujo();
//		Recinto r1 = new Recinto(0,0,10,10, "r1");
//		Grilla g1 = new Grilla (r1,5,1);
//		g1.prepararGrillaParaDibujo();
//		r1.setGrilla(g1);
//		mapa.agregarRecinto(r1);
//		Cuadrante c = mapa.buscarCuadrantePorId(1);
//		int num= c.getId();
//		float posX = c.getPosicionX();
//		float posY = c.getPosicionY();
//		int cuadrantes = mapa.getGrilla().getContador().getProximoNumeroDeCuadrante();
//		System.out.println("Id Cuadrante: " + num + " Pos X: " + posX + " Pos Y: " + posY);
//		System.out.println("Cantidad total de cuadrantes: " + cuadrantes);
//		Assert.assertEquals(10, c.getPosicionX(),0);
//		Assert.assertEquals(0, c.getPosicionY(),0);
//		Assert.assertEquals(1, c.getId());
//		Grilla grilla = new Grilla(mapa.getRecintoMapa(),(float)5,(int)1);
//		grilla.prepararGrillaParaDibujo();
//		mapa.setGrilla(grilla);
//		mapa.regenerarIdsCuadrantesDeTodoElMapa();
//		cuadrantes = mapa.getGrilla().getContador().getProximoNumeroDeCuadrante();
//		System.out.println("Cantidad total de cuadrantes: " + cuadrantes);
//		Cuadrante cnuevo = mapa.buscarCuadrantePorId(1);
//		num= cnuevo.getId();
//		posX = cnuevo.getPosicionX();
//		posY = cnuevo.getPosicionY();
//		
//		System.out.println("Id Cuadrante: " + num + " Pos X: " + posX + " Pos Y: " + posY);
//		
//		Assert.assertEquals(5, cnuevo.getPosicionX(),0);
//		Assert.assertEquals(0, cnuevo.getPosicionY(),0);
//		Assert.assertEquals(1, cnuevo.getId());
//		
//	}
	
	
}
