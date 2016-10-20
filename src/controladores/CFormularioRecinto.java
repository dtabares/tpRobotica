package controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CFormularioRecinto {
    
    @FXML //  fx:id="formularioRecintoOk"
    private Button formularioRecintoOk;
    
   // @Override
    public void initialize() {
        
    	assert formularioRecintoOk != null : "fx:id=\"formularioRecintoOk\" was not injected: check your FXML file 'FormularioRecinto.fxml'.";

        formularioRecintoOk.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
        		System.out.println("Creando Recinto... ");
        		
            }
        });

    }

}
