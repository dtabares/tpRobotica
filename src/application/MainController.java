package application;

import java.util.Collection;
import java.util.LinkedList;
import clases.Grilla;
import clases.Mapa;
import clases.Recinto;
import formularios.NuevoRecintoControlador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

public class MainController extends BorderPane {
	
	private static Mapa mapa;
//	private NuevoRecintoControlador nuevoRecintoControlador;
	Collection <Shape> formas = new LinkedList<Shape>();

	@FXML private AnchorPane panelCentral;
	
	@FXML private TextField inPosicionX;
	@FXML private TextField inPosicionY;
	@FXML private TextField inAncho;
	@FXML private TextField inAlto;
	@FXML private TextField inTamanioGrilla;
	@FXML private CheckBox inCheckboxGrilla;
	@FXML private Button btnRecintoOK;	
	
    public void initialize() {
    	
//        nuevoRecintoControlador = new NuevoRecintoControlador();
//        nuevoRecintoControlador.setListener(new NuevoRecintoControladorListener() {
//
//    	@Override public void nuevoRecintoControladorListenerOK (ActionEvent e) {
//    		
//    	}
//    	public void nuevoRecintoControladorListenerCancel(ActionEvent e) {}
//        });

    }
    
    @FXML public void ejecutar(){
    	System.out.println("Ancho: " + (inAncho.getText()) + " Alto: " + Float.valueOf(inAlto.getText()) + " PosX: " + Float.valueOf(inPosicionX.getText()) + " PosY: " + Float.valueOf(inPosicionY.getText()));
    	if (mapa != null){
    		Recinto recinto = new Recinto(Float.valueOf(inPosicionX.getText()),Float.valueOf(inPosicionY.getText()),Float.valueOf(inAncho.getText()),Float.valueOf(inAlto.getText()));
    		boolean recintoValido = mapa.agregarRecinto(recinto);
    		if(recintoValido){
		    	if(inCheckboxGrilla.isSelected()){
		    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()));
		    		grilla.prepararGrillaParaDibujo();
		    		recinto.setGrilla(grilla);
		    	}
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
    
    @FXML public void crearNuevoMapa(){
    	
    	//cambiarlo a dinamico
    	mapa = new Mapa(800,400);
    	panelCentral.getChildren().setAll(mapa.getChildren());
    	
    }
    

}
