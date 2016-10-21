package application;
	
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.PruebaDeConceptoTest;
import clases.Recinto;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

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
