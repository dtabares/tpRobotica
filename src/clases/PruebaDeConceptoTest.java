package clases;
import java.util.Collection;
import java.util.LinkedList;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class PruebaDeConceptoTest {
	
	Stage primaryStage = new Stage();
	Group root = new Group();
	Collection <Recinto> formas = new LinkedList<Recinto>();
	public static Mapa mapa = new Mapa(800,400);

	public void ejecutarPruebas(){
		
		Recinto recinto = new Recinto(0,0,100,100);
		Recinto recinto1 = new Recinto(0,100,100,100);
		Recinto recinto2 = new Recinto(0,200,100,100);
		Recinto recinto3 = new Recinto(100,0,100,100);
		Recinto recinto4 = new Recinto(200,0,100,100);
		Recinto recinto5 = new Recinto(100,100,100,100);
		Recinto recinto6 = new Recinto(200,200,100,100);
		Recinto recinto7 = new Recinto(300,0,100,100);
		

		recinto3.agregarObstaculo(new Obstaculo(150,30,40,50));
		
		Grilla grillaRecinto3 = new Grilla(recinto3,30);
		recinto3.setGrilla(grillaRecinto3);
		recinto3.verificarDisponibilidadDeLaGrilla();
		grillaRecinto3.prepararGrillaParaDibujo();
		
		Grilla grillaRecinto = new Grilla(recinto,60);
		grillaRecinto.prepararGrillaParaDibujo();
		recinto.setGrilla(grillaRecinto);		
		
		Grilla grillaRecinto2 = new Grilla(recinto2,5);
		grillaRecinto2.prepararGrillaParaDibujo();
		recinto2.setGrilla(grillaRecinto2);	

		formas.add(recinto);
	    formas.add(recinto1);   
	    formas.add(recinto2);
	    formas.add(recinto3);
	    formas.add(recinto4);
	    formas.add(recinto5);
	    formas.add(recinto6);
	    formas.add(recinto7);
	    
	    
	    //Agrega a la collection actual de formas los componentes de cada grilla, lo ideal es que esto lo pueda hacer la propia grilla en el metodo mostrar()
	//    formas.addAll(recinto.getGrilla().getColeccionDeRectangulos());
	 //   formas.addAll(recinto2.getGrilla().getColeccionDeRectangulos());
	  //  formas.addAll(recinto3.getGrilla().getColeccionDeRectangulos());
	    
	    
	    mapa.agregarColleccionDeRecintos(formas);

	}
	
}
