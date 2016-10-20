package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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


public class Controlador implements Initializable {

    @FXML //  fx:id="myButton"
    private Button myButton; // Value injected by FXMLLoader
    
    @FXML //  fx:id="recinto"
    private Rectangle recinto;


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        
        //Abre un formulario al clickear en el boton
        myButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
                //System.out.println("That was easy, wasn't it?");
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
