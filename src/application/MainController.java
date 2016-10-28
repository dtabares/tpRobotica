package application;

import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Recinto;
import formularios.NuevoMapaControlador;
import formularios.NuevoMapaControlador.NuevoMapaControladorListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	NuevoMapaControlador nuevoMapaControlador;
	//Objeto para instanciar mensajes 
	Component frame = null;

	@FXML private AnchorPane panelCentral;
	
	//Listas Observables
	ObservableList<Obstaculos> obstaculos = FXCollections.observableArrayList(Arrays.asList(Obstaculos.values()));
	
	//Items menu Mapa
	@FXML private CheckBox inMenuCheckboxGrilla;
	@FXML private TextField inMenuTamanioGrilla;
	
	
	//Items menu Recintos
	@FXML private ComboBox<String> inRecintosComboBox;
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
	
	//Items menu Puertas
	@FXML private ComboBox inPuertasComboBox;
	@FXML private TextField inPuertasPosicionX;
	@FXML private TextField inPuertasPosicionY;

	public void initialize() {
        
		inObstaculosComboBox.setValue(Obstaculos.Mesa);
		inObstaculosComboBox.setItems(obstaculos);
		inRecintosComboBox.getSelectionModel().selectFirst();
		inRecintosComboBox.getItems().add("Nuevo Recinto");
    	
		nuevoMapaControlador = new NuevoMapaControlador();
 		nuevoMapaControlador.setListener(new NuevoMapaControladorListener() {	
    	@Override public void nuevoMapaControladorListenerOK (ActionEvent e) {
    		System.out.println("Creando nuevo mapa...");
        	mapa = new Mapa(Float.parseFloat(nuevoMapaControlador.getInAncho()),Float.parseFloat(nuevoMapaControlador.getInAlto()));
        	mapa.setRecintoMapa(new Recinto(0,0,Float.parseFloat(nuevoMapaControlador.getInAncho()),Float.parseFloat(nuevoMapaControlador.getInAlto()),"Recinto Mapa"));
        	mapa.setStyle("-fx-background-color: black;");    	
        	mapa.dibujarMapa();
        	panelCentral.getChildren().setAll(mapa.getChildren());
        	nuevoMapaControlador.ocultarFormulario();
    	}
    	@Override
    	public void nuevoMapaControladorListenerCancel(ActionEvent e) {}
        });
		
    }
    
    @FXML public void ejecutar(){
    	
    	if(inRecintosComboBox.getValue()==null || inRecintosComboBox.getValue()== "Nuevo Recinto"){
	    	if (mapa != null){
	    		Recinto recinto = new Recinto(Float.valueOf(inPosicionX.getText()),Float.valueOf(inPosicionY.getText()),Float.valueOf(inAncho.getText()),Float.valueOf(inAlto.getText()), nombreRecinto.getText());
	    		boolean recintoValido = mapa.agregarRecinto(recinto);
	    		if(recintoValido){
	    			inRecintosComboBox.getItems().add(recinto.getNombre());
			    	if(inCheckboxGrilla.isSelected()){
			    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()));
			    		grilla.prepararGrillaParaDibujo();
			    		recinto.setGrilla(grilla);
			    	}
	    			mapa.dibujarMapa();
		    		panelCentral.getChildren().setAll(mapa.getChildren());
	    		}
	    		else{
	    			this.mostrarMensajeDeError("Recinto invalido");
	    		}
	    		
	        }
			else{
				this.mostrarMensajeDeError("No existe el mapa!");
			}
    	}
    	else {
    		System.out.println("Modificacion");
    		Recinto recinto = mapa.buscarRecintoPorNombre(inRecintosComboBox.getValue());
	    	if(inCheckboxGrilla.isSelected()){
	    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()));
	    		grilla.prepararGrillaParaDibujo();
	    		recinto.setGrilla(grilla);
	    	}
			mapa.dibujarMapa();
    		panelCentral.getChildren().setAll(mapa.getChildren());
    		
    	}
    }
    
    private void mostrarMensajeDeError(String mensaje) {
		JOptionPane.showMessageDialog(frame,mensaje,
				"Error",
			    JOptionPane.ERROR_MESSAGE);
	}

	@FXML public void agregarObstaculo(){
    	boolean obstaculoValido;
    	Recinto recinto;
    	if (mapa != null){
    		if(inObstaculosComboBox.getValue()!=null){
    			if(inRecintosComboBox.getValue()!=null){
    				Obstaculo obstaculo = new Obstaculo(Float.valueOf(inObstaculosPosicionX.getText()),Float.valueOf(inObstaculosPosicionY.getText()),inObstaculosComboBox.getValue());
    				recinto = mapa.buscarRecintoPorNombre(inRecintosComboBox.getValue());
    				if (recinto != null){
    					obstaculoValido = recinto.agregarObstaculo(obstaculo);
    					if(obstaculoValido){
    						mapa.dibujarMapa();
    						panelCentral.getChildren().setAll(mapa.getChildren());
    						System.out.println("Obstaculo agregado!");
    					}
    					else{
    						this.mostrarMensajeDeError("El Obstaculo no se agrego ya que estaba en una posicion invalida!");
    					}
    				}
    				else{
    					this.mostrarMensajeDeError("El recinto seleccionado es invalido");
    				}
    			}
    			else{
    				this.mostrarMensajeDeError("Debe seleccionar un recinto!");
    			}
    		}
    		else{
    			this.mostrarMensajeDeError("Debe seleccionar un obstaculo!");
    		}
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    	
    }
    
    @FXML public void crearNuevoMapa(){
    	
    	nuevoMapaControlador.mostrarFormulario();
    	
    }
    
    @FXML public void agregarGrillaEnMapa(){
    	
    	if (inMenuCheckboxGrilla.isSelected()){
    		mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),Float.valueOf(inMenuTamanioGrilla.getText())));
    		mapa.getGrilla().prepararGrillaParaDibujo();
    		mapa.dibujarMapa();
			panelCentral.getChildren().setAll(mapa.getChildren());
    	}
    	
    }
    
    @FXML public void agregarPuerta(){
    	
    	
    }

    @FXML public void borrarMapa(){
    	if(mapa!=null){
    	mapa.getChildren().removeAll(mapa.getChildren());
    	panelCentral.getChildren().setAll(mapa.getChildren());
    	mapa = null;
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    }

}
