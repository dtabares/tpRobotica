package application;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Recinto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

public class MainController extends BorderPane {
	
	private static Mapa mapa;
	Collection <Shape> formas = new LinkedList<Shape>();

	@FXML private AnchorPane panelCentral;
	
	//Listas Observables
	ObservableList<Obstaculos> obstaculos = FXCollections.observableArrayList(Arrays.asList(Obstaculos.values()));
	
	//Items menu Recintos
	@FXML private ComboBox<Recinto> inRecintosComboBox;
	@FXML private TextField inPosicionX;
	@FXML private TextField inPosicionY;
	@FXML private TextField inAncho;
	@FXML private TextField inAlto;
	@FXML private TextField inTamanioGrilla;
	@FXML private CheckBox inCheckboxGrilla;
	@FXML private Button btnRecintoOK;
	@FXML private TextField nombreRecinto;
	
	//Items menu Obstaculos
	@FXML private ComboBox<Obstaculos> inObstaculosComboBox;
	@FXML private TextField inObstaculosPosicionX;
	@FXML private TextField inObstaculosPosicionY;

	public void initialize() {
        
		inObstaculosComboBox.setValue(Obstaculos.Mesa);
		inObstaculosComboBox.setItems(obstaculos);
		inRecintosComboBox.getSelectionModel().selectFirst();
    	
    }
    
    @FXML public void ejecutar(){
    	
    	if(inRecintosComboBox.getValue()==null){
	    	if (mapa != null){
	    		Recinto recinto = new Recinto(Float.valueOf(inPosicionX.getText()),Float.valueOf(inPosicionY.getText()),Float.valueOf(inAncho.getText()),Float.valueOf(inAlto.getText()), nombreRecinto.getText());
	    		boolean recintoValido = mapa.agregarRecinto(recinto);
	    		if(recintoValido){
	    			inRecintosComboBox.getItems().add(recinto);
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
    	else {
    		System.out.println("Modificacion");
    		Recinto recinto = inRecintosComboBox.getValue();
	    	if(inCheckboxGrilla.isSelected()){
	    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()));
	    		grilla.prepararGrillaParaDibujo();
	    		recinto.setGrilla(grilla);
	    	}
			mapa.dibujarMapa();
    		panelCentral.getChildren().setAll(mapa.getChildren());
    		
    	}
    }
    
    @FXML public void agregarObstaculo(){
    	
    	if (mapa != null){
    		if(inObstaculosComboBox.getValue()!=null){
    			if(inRecintosComboBox.getValue()!=null){
    				Obstaculo obstaculo = new Obstaculo(Float.valueOf(inObstaculosPosicionX.getText()),Float.valueOf(inObstaculosPosicionY.getText()),inObstaculosComboBox.getValue());
    				inRecintosComboBox.getValue().agregarObstaculo(obstaculo);
    				mapa.dibujarMapa();
    	    		panelCentral.getChildren().setAll(mapa.getChildren());
    	    		System.out.println("Obstaculo agregado!");
    			}
    			else{
    				System.out.println("Debe seleccionar un recinto!");
    			}
    		}
    		else{
    			System.out.println("Debe seleccionar un obstaculo!");
    		}
    	}
    	else{
    		System.out.println("No existe el mapa!");
    	}
    	
    }
    
    @FXML public void crearNuevoMapa(){
    	
    	//cambiarlo a dinamico
    	mapa = new Mapa(790,390);
    	panelCentral.getChildren().setAll(mapa.getChildren());
    	
    }
    
    /* Ejemplo listener, debe ir dentro de initialize
		nuevoRecintoControlador = new NuevoRecintoControlador();
 		nuevoRecintoControlador.setListener(new NuevoRecintoControladorListener() {	
    	@Override public void nuevoRecintoControladorListenerOK (ActionEvent e) {
    		
    	}
    	public void nuevoRecintoControladorListenerCancel(ActionEvent e) {}
        });
 
     * */  

}
