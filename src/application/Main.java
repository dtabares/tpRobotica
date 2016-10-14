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
	
	private Stage primaryStage; /* Es un objeto que hereda de la clase Window, representa la ventana de la interfaz */
	private BorderPane rootLayout;
	Group root = new Group();
	private Mapa mapa = new Mapa();
	Collection <Shape> formas = new HashSet<Shape>();
	
	public void pruebaDeConcepto(){
		Recinto recinto = new Recinto();
		Shape forma = recinto.crearRecinto(0,0,100,100);
		Shape forma2 = recinto.crearRecinto(100,0,100,100);
		Shape forma3 = recinto.crearRecinto(200,0,100,100);
		Shape forma4 = recinto.crearRecinto(0,100,100,100);
		Shape forma5 = recinto.crearRecinto(0,200,100,100);
		Shape forma6 = recinto.crearRecinto(100,100,200,300);
	    formas.add(forma);
	    formas.add(forma2);
	    formas.add(forma3);
	    formas.add(forma4);
	    formas.add(forma5);
	    formas.add(forma6);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
	        
        //Usa la clase FXMLLoader para cargar desde un XML la interfaz de usuario
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Sample.fxml"));
        rootLayout = loader.load();
        
        //Crea algunas formas usando la clase recinto y se las agrega al mapa
        this.pruebaDeConcepto();	    
	    mapa.agregarCollectionDeFormas(formas);
        
	    //Sobreescribe el BorderPane y setea como canvas al canvas de nuestro mapa
	    rootLayout.setCenter(mapa.canvas);

        // Muestra el Scene que contiene al RootLayout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
			
	}

		
	public static void main(String[] args) {
		launch(args);
	}

}
