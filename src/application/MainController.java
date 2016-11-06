package application;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import clases.Cuadrante;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Orientacion;
import clases.Puerta;
import clases.Recinto;
import clases.Trayectoria;
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
import trayectorias.Dijkstra;

public class MainController extends BorderPane {
	
	private static Mapa mapa;
	Collection <Shape> formas = new LinkedList<Shape>();
	NuevoMapaControlador nuevoMapaControlador;
	//Objeto para instanciar mensajes 
	Component frame = null;

	@FXML private AnchorPane panelCentral;
	
	//Listas Observables
	ObservableList<Obstaculos> obstaculos = FXCollections.observableArrayList(Arrays.asList(Obstaculos.values()));
	ObservableList<Orientacion> orientaciones = FXCollections.observableArrayList(Arrays.asList(Orientacion.values()));
	
	//Items menu Mapa
	@FXML private CheckBox inMapaCheckboxGrilla;
	@FXML private TextField inMapaTamanioGrilla;
	@FXML private ComboBox<Integer> inMapaComboVertices;
	
	//Items menu Recintos
	@FXML private ComboBox<String> inRecintosComboBox;
	@FXML private ComboBox<Integer> inRecintosComboVertices;
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
	@FXML private ComboBox<String> inObstaculosComboRecintos;
	
	//Items menu Puertas
	@FXML private ComboBox<Orientacion> inPuertasComboBox;
	@FXML private TextField inPuertasPosicionX;
	@FXML private TextField inPuertasPosicionY;
	@FXML private ComboBox<String> inPuertasComboRecintos;
	@FXML private TextField inPuertasAncho;

	public void initialize() {
        
		inObstaculosComboBox.setValue(Obstaculos.Mesa);
		obstaculos.remove(Obstaculos.Recinto);
		inObstaculosComboBox.setItems(obstaculos);
		
		inRecintosComboBox.getSelectionModel().selectFirst();
		inRecintosComboBox.getItems().add("Nuevo Recinto");
		inRecintosComboVertices.setValue(1);
		inRecintosComboVertices.setItems(FXCollections.observableArrayList(1,2,3,4));
		
		inPuertasComboBox.setItems(orientaciones);
		
		inMapaComboVertices.setValue(1);
		inMapaComboVertices.setItems(FXCollections.observableArrayList(1,2,3,4));
		

		
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
    
    @FXML public void agregarRecinto(){
    	
    	if(inRecintosComboBox.getValue()==null || inRecintosComboBox.getValue()== "Nuevo Recinto"){
	    	if (mapa != null){
	    		Recinto recinto = new Recinto(Float.valueOf(inPosicionX.getText()),Float.valueOf(inPosicionY.getText()),Float.valueOf(inAncho.getText()),Float.valueOf(inAlto.getText()), nombreRecinto.getText());
	    		boolean recintoValido = mapa.agregarRecinto(recinto);
	    		if(recintoValido){
	    			mapa.getRecintoMapa().agregarObstaculo(new Obstaculo(recinto.getPosicionX(), recinto.getPosicionY(), recinto.getAncho(), recinto.getAlto(), Obstaculos.Recinto));
	    			inRecintosComboBox.getItems().add(recinto.getNombre());
	    			inPuertasComboRecintos.getItems().add(recinto.getNombre());
	    			inObstaculosComboRecintos.getItems().add(recinto.getNombre());
			    	if(inCheckboxGrilla.isSelected()){
			    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()),(int)inRecintosComboVertices.getValue());
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
	    		Grilla grilla = new Grilla (recinto,Float.valueOf(inTamanioGrilla.getText()),(int)inRecintosComboVertices.getValue());
	    		grilla.prepararGrillaParaDibujo();
	    		recinto.setGrilla(grilla);
	    		mapa.regenerarIdsCuadrantesDeTodoElMapa();
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
    			if(inObstaculosComboRecintos.getValue()!=null){
    				Obstaculo obstaculo = new Obstaculo(Float.valueOf(inObstaculosPosicionX.getText()),Float.valueOf(inObstaculosPosicionY.getText()),inObstaculosComboBox.getValue());
    				recinto = mapa.buscarRecintoPorNombre(inObstaculosComboRecintos.getValue());
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
    	
    	if (inMapaCheckboxGrilla.isSelected()){
    		mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),Float.valueOf(inMapaTamanioGrilla.getText()),(int)inMapaComboVertices.getValue()));
    		mapa.getGrilla().prepararGrillaParaDibujo();
    		mapa.dibujarMapa();
			panelCentral.getChildren().setAll(mapa.getChildren());
    	}
    	
    }
    
    @FXML public void agregarPuerta(){
    	System.out.println("Entre a agregarPuerta");
    	boolean puertaValida;
    	Recinto recinto; 
    	if (mapa != null){
    		recinto = mapa.buscarRecintoPorNombre(inPuertasComboRecintos.getValue());
    		if (recinto != null){
        		System.out.println("Nombre Recinto: " + recinto.getNombre());
    			Puerta puerta = new Puerta (Float.valueOf(inPuertasPosicionX.getText()),Float.valueOf(inPuertasPosicionY.getText()),(Orientacion)inPuertasComboBox.getValue(),(float)10);
    			puertaValida = recinto.agregarPuerta(puerta,mapa);
    			System.out.println("Puerta valida? : " + puertaValida);
				if(puertaValida){
					mapa.dibujarMapa();
					panelCentral.getChildren().setAll(mapa.getChildren());
					System.out.println("Puerta agregado!");
				}
				else{
					this.mostrarMensajeDeError("La puerta No se encuentra en una pared valida del recinto");
				}
    		}
			else{
				this.mostrarMensajeDeError("El recinto seleccionado es invalido");
			}
    		
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    	
    	
    	
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
    
    //AMIGACHO: Agregarlo a la UI
    @FXML public void buscarCaminoMasCorto(){
    	if (mapa != null){
    		System.out.println("Entre a buscarCaminoMasCorto");
    		Dijkstra dijkstra = new Dijkstra();
    		List<Integer> listaDeIds;
    		List<Cuadrante> listaDeCuadrantes = new LinkedList<Cuadrante>();
    		//AMIGACHO: cambiar el origen y destino a algo que venga de la UI
    		listaDeIds = dijkstra.obtenerCaminoMasCorto(mapa.getMatrizDeAdyacenciaGlobal().getMatrizDeAdyacenciaEnBooleanos(), 0, 0);
    		
    		for (Integer id : listaDeIds) {
				Cuadrante c = mapa.buscarCuadrantePorId(id);
				listaDeCuadrantes.add(c);
			}
    		
    		Trayectoria trayectoria = new Trayectoria(listaDeCuadrantes,mapa.getPosicionEnGradosRespectoDelNorteMagnetico());
    		trayectoria.calcularTrayectoria();
    	}
    	else{
    		this.mostrarMensajeDeError("Debe crear un mapa primero!");
    	}
    	
    }

}
