package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class CInterfaz {

    /*Declaracion de elementos que interactuan dentro de la UI*/
	@FXML //  fx:id="myButton"
    private Button myButton; // Value injected by FXMLLoader

    public void initialize() {
        
    	assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'Interfaz.fxml'.";
        
        //Abre FormularioRecinto.fxml al clickear en el boton
        myButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
        		
        		System.out.println("Abriendo Formulario... ");
                Stage formStage = new Stage();
                GridPane formPane = new GridPane();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("FormularioRecinto.fxml"));
                try {
					formPane = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Scene scene = new Scene(formPane);
                formStage.setTitle("Nuevo Recinto");
                formStage.setScene(scene);
                formStage.show();
            }
        });

    }

}
