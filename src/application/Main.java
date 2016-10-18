package application;
	
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

public class Main extends Application {
	
	Stage primaryStage = new Stage();
	private BorderPane rootLayout;
	Group root = new Group();
	private Mapa mapa = new Mapa(1024,768);
	Collection <Shape> formas = new HashSet<Shape>();
	
	public void pruebaDeConcepto(){
		Recinto recinto = new Recinto(0,0,100,100);
		Recinto recinto1 = new Recinto(0,100,100,100);
		Recinto recinto2 = new Recinto(0,200,100,100);
		Recinto recinto3 = new Recinto(100,0,100,100);
		Recinto recinto4 = new Recinto(200,0,100,100);
		Recinto recinto5 = new Recinto(100,100,100,100);
		Recinto recinto6 = new Recinto(200,200,100,100);
		Recinto recinto7 = new Recinto(300,0,100,100);
		
		Grilla grillaRecinto3 = new Grilla(recinto3,50);
		recinto3.setGrilla(grillaRecinto3);	
		
		Grilla grillaRecinto = new Grilla(recinto,10);
		recinto.setGrilla(grillaRecinto);		
	    
		Grilla grillaRecinto2 = new Grilla(recinto2,20);
		recinto2.setGrilla(grillaRecinto2);	
		
		//Tuve que comentar las formas que contienen grillas porque se superponen las renderizaciones entre si
		//formas.add(recinto.recinto);
	    formas.add(recinto1.getRecinto());   
	    //formas.add(recinto2.recinto);
	    //formas.add(recinto3.recinto);
	    formas.add(recinto4.getRecinto());
	    formas.add(recinto5.getRecinto());
	    formas.add(recinto6.getRecinto());
	    formas.add(recinto7.getRecinto());
	    
	    //Agrega a la collection actual de formas los componentes de cada grilla, lo ideal es que esto lo pueda hacer la propia grilla en el metodo mostrar()
	    formas.addAll(recinto.getGrilla().getColeccionDeRectangulos());
	    formas.addAll(recinto2.getGrilla().getColeccionDeRectangulos());
	    formas.addAll(recinto3.getGrilla().getColeccionDeRectangulos());

	}

	@Override
	public void start(Stage primaryStage) throws IOException {
	        
        //Usa la clase FXMLLoader para cargar desde un XML la interfaz de usuario
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Interfaz.fxml"));
        rootLayout = loader.load();
        
        //Crea algunas formas usando la clase recinto y se las agrega al mapa
        this.pruebaDeConcepto();	    
	    mapa.agregarCollectionDeFormas(formas);
        
	    //Sobreescribe el BorderPane y setea como canvas al canvas de nuestro mapa
	    rootLayout.setCenter(mapa.canvas);

        // Muestra el Scene que contiene al RootLayout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setTitle("TP Robotica");
        primaryStage.setScene(scene);
        primaryStage.show();
			
	}
		
	public static void main(String[] args) {
		launch(args);
	}

}
