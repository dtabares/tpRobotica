package application;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import clases.Mapa;
import clases.Recinto;
import formularios.NuevoRecintoControlador;
import formularios.NuevoRecintoControlador.NuevoRecintoControladorListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

public class MainController extends BorderPane {
	
	private static Mapa mapa;
	private NuevoRecintoControlador nuevoRecintoControlador;
	Collection <Shape> formas = new LinkedList<Shape>();

	@FXML private Button myButton;
	@FXML private MenuItem botonNuevoMapa;
	@FXML private AnchorPane panelCentral;
	
    public void initialize() {
    	
        nuevoRecintoControlador = new NuevoRecintoControlador();
        nuevoRecintoControlador.setListener(new NuevoRecintoControladorListener() {

    	@Override public void nuevoRecintoControladorListenerOK (ActionEvent e) {
    		if (mapa != null){
	    		System.out.println("Creando Recinto.... " + "Ancho: " + nuevoRecintoControlador.getFormularioRecintoAncho() +  ",Alto: " + nuevoRecintoControlador.getFormularioRecintoAlto() + ",Posicion X: " + nuevoRecintoControlador.getFormularioRecintoPosicionX() + ",Posicion Y: " + nuevoRecintoControlador.getFormularioRecintoPosicionY());
	    		boolean recintoValido = mapa.agregarRecinto(new Recinto(Float.valueOf(nuevoRecintoControlador.getFormularioRecintoAncho()),Float.valueOf(nuevoRecintoControlador.getFormularioRecintoAlto()),Float.valueOf(nuevoRecintoControlador.getFormularioRecintoPosicionX()),Float.valueOf(nuevoRecintoControlador.getFormularioRecintoPosicionY())));
	    		if(recintoValido){
	    			nuevoRecintoControlador.ocultarFormulario();
		    		mapa.dibujarMapa();
		    		panelCentral.getChildren().setAll(mapa.getChildren());
	    		}
	    		else{
	    			
	    			System.out.println("Recinto invalido  " + recintoValido);
	    		}
	    		
            }
    		else{
    			System.out.println("No existe el mapa!");
    		}
    	}
    	public void nuevoRecintoControladorListenerCancel(ActionEvent e) {}
        });

    }
    
    @FXML public void ejecutar() throws IOException{
        		
    	System.out.println("Abriendo Formulario... ");
    	nuevoRecintoControlador.mostrarFormulario();

    }
    
    @FXML public void crearNuevoMapa(){
    	
    	//cambiarlo a dinamico
    	mapa = new Mapa(800,400);
    	panelCentral.getChildren().setAll(mapa.getChildren());
    	
    }
    

}
