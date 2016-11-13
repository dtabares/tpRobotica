package application;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import clases.Coordenada;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import trayectorias.Dijkstra;

public class MainController extends BorderPane {
	
	private static Mapa mapa;
	Collection <Shape> formas = new LinkedList<Shape>();
	NuevoMapaControlador nuevoMapaControlador;
	Trayectoria trayectoria;
	//Objeto para instanciar mensajes 
	Component frame = null;
	File archivo=null;

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
	
	//Items menu Trayectorias
	@FXML private TextField inTrayectoriasOrigenPosicionX;
	@FXML private TextField inTrayectoriasOrigenPosicionY;
	@FXML private TextField inTrayectoriasDestinoPosicionX;
	@FXML private TextField inTrayectoriasDestinoPosicionY;
	
	public void initialize() {
        
		inObstaculosComboBox.setValue(Obstaculos.Mesa);
		obstaculos.remove(Obstaculos.Recinto);
		inObstaculosComboBox.setItems(obstaculos);
		inRecintosComboBox.getSelectionModel().selectFirst();
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
	    		if(!inRecintosComboBox.getItems().contains(nombreRecinto.getText())){
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
		    			this.mostrarMensajeDeError("Recinto invalido!");
		    		}
	    		}
	    		else{
	    			this.mostrarMensajeDeError("El nombre de recinto ya existe!");
	    		}
	        }
			else{
				this.mostrarMensajeDeError("No existe el mapa!");
			}
    	}
    	else {
    		System.out.println("Modificando Recinto");
    		mapa.getRecintos().remove(mapa.buscarRecintoPorNombre(inRecintosComboBox.getValue()));
    		inRecintosComboBox.getItems().remove(inRecintosComboBox.getValue());
    		inRecintosComboBox.setValue("Nuevo Recinto");
    		this.agregarRecinto();
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
    					this.mostrarMensajeDeError("El recinto seleccionado es invalido!");
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
		inRecintosComboBox.getItems().add("Nuevo Recinto");
		inObstaculosComboRecintos.getItems().add("Mapa");
    	inPuertasComboRecintos.getItems().add("Mapa");
    	
    }
    
    @FXML public void agregarGrillaEnMapa(){
    	
    	if (inMapaCheckboxGrilla.isSelected()){
    		mapa.setGrilla(new Grilla(mapa.getRecintoMapa(),Float.valueOf(inMapaTamanioGrilla.getText()),(int)inMapaComboVertices.getValue()));
    		mapa.getGrilla().prepararGrillaParaDibujo();
    		mapa.regenerarIdsCuadrantesDeTodoElMapa();
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
    			Puerta puerta = new Puerta (Float.valueOf(inPuertasPosicionX.getText()),Float.valueOf(inPuertasPosicionY.getText()),(Orientacion)inPuertasComboBox.getValue(),Float.valueOf(inPuertasAncho.getText()));
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
		inRecintosComboBox.getItems().clear();
		inObstaculosComboRecintos.getItems().clear();
    	inPuertasComboRecintos.getItems().clear();
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    
    }
    
    @FXML public void buscarCaminoMasCorto(){
    	
    	if (mapa != null){
    		if(trayectoria != null){ trayectoria.borrarTrayectoria();}
    		Dijkstra dijkstra = new Dijkstra();
    		List<Integer> listaDeIds;
    		List<Cuadrante> listaDeCuadrantes = new LinkedList<Cuadrante>();
    		Coordenada origen = new Coordenada (Float.parseFloat(inTrayectoriasOrigenPosicionX.getText()),Float.parseFloat(inTrayectoriasOrigenPosicionY.getText()));
    		Coordenada destino = new Coordenada (Float.parseFloat(inTrayectoriasDestinoPosicionX.getText()),Float.parseFloat(inTrayectoriasDestinoPosicionY.getText()));
    		Cuadrante cuadranteOrigen = mapa.buscarCuadrantePorCoordenada(origen);
    		Cuadrante cuadranteDestino = mapa.buscarCuadrantePorCoordenada(destino);
    		if(cuadranteOrigen == null || cuadranteDestino == null){
    			if(cuadranteOrigen == null){
    				this.mostrarMensajeDeError("Cuadrante Origen Invalido!");
    			}
    			else{
    				this.mostrarMensajeDeError("Cuadrante Destino Invalido!");
    			}
    		}
    		else{
    			if (!cuadranteOrigen.estaDisponible() || !cuadranteDestino.estaDisponible()){
    				if (!cuadranteOrigen.estaDisponible()){
    					this.mostrarMensajeDeError("Cuadrante Origen no esta libre!");
    				}
    				else{
    					this.mostrarMensajeDeError("Cuadrante Destino no esta libre!");
    				}
    			}
    			else{
    				mapa.calcularMatrizDeAdyacenciaGlobal();
    	    		listaDeIds = dijkstra.obtenerCaminoMasCorto(mapa.getMatrizDeAdyacenciaGlobal().getMatrizDeAdyacenciaEnBooleanos(), cuadranteOrigen.getId(), cuadranteDestino.getId());
    	    		for (Integer id : listaDeIds) {
    					Cuadrante c = mapa.buscarCuadrantePorId(id);
    					listaDeCuadrantes.add(c);
    				}
    	    		trayectoria = new Trayectoria(listaDeCuadrantes,mapa.getPosicionEnGradosRespectoDelNorteMagnetico());
    	    		trayectoria.calcularTrayectoria();
    			}
    		}
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    	
    }
    
    @FXML public void guardarMapa() throws IOException{
    	
    	Map<String,String> extensiones = new HashMap<String,String>();	
    	extensiones.put("Imagen (*.png)", "*.png");
    	extensiones.put("Objeto (*.obj)", "*.obj");
    	if(mapa!=null){
    		String ubicacion = buscarDirectorio(extensiones,"Guardar").getAbsolutePath();
    		if(ubicacion.contains("png")){
    			System.out.println("Guardando mapa como imagen");
    		    WritableImage imagen = panelCentral.snapshot(new SnapshotParameters(), null);
    		    File archivo = new File(ubicacion);
    		    try {
    		        ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", archivo);
    		        this.archivo = new File(archivo.getParent());
    		        System.out.println("Imagen del mapa guardada en: " + ubicacion);
    		    } catch (IOException e) {
    		    	System.out.println(e.getMessage());
    		    }
    		}
    		else if (ubicacion.contains("obj")){
    			System.out.println("Guardando mapa como objeto");
    			if(ubicacion!=null){	
	    	    	FileOutputStream fout = new FileOutputStream(ubicacion);
	    	    	ObjectOutputStream oos = new ObjectOutputStream(fout);
	    	    	oos.writeObject(mapa);
	    	    	oos.close();
	    	    	fout.close();
	    	    	this.archivo = new File(archivo.getParent());
	    	    	System.out.println("Mapa guardado en: " + ubicacion);
    			}
    		}
    		else{
    			this.mostrarMensajeDeError("Extension Invalida!");
    		}
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
    
    }
    
    public File buscarDirectorio(Map<String,String> extensiones, String modo){
		
    	Stage directorioStage = null;
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Directorio");
	    if (archivo!=null){
	    	fc.setInitialDirectory(archivo);
	    }
        for (Map.Entry<String, String> posicionMapa : extensiones.entrySet()) {
        	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(posicionMapa.getKey(),posicionMapa.getValue()));
        }
        if(modo.equals("Guardar")){
        	archivo = fc.showSaveDialog(directorioStage);
        }
        else if(modo.equals("Cargar")){
        	archivo = fc.showOpenDialog(directorioStage);
        }
        else{
        	this.mostrarMensajeDeError("Modo incorrecto!");
        }
        if(!archivo.getName().contains(".png") && fc.getSelectedExtensionFilter().equals("*.png")) {
        	archivo = new File(archivo.getAbsolutePath() + ".png");
        }
        else if(!archivo.getName().contains(".obj") && fc.getSelectedExtensionFilter().equals("*.obj")){
        	archivo = new File(archivo.getAbsolutePath() + ".obj");
        }
        else if(!archivo.getName().contains(".txt") && fc.getSelectedExtensionFilter().equals("*.txt")){
        	archivo = new File(archivo.getAbsolutePath() + ".txt");
        }
		return archivo;
	
    }
    
    @FXML public void cargarMapa() throws IOException, ClassNotFoundException{
    	
    	Map<String,String> extensiones = new HashMap<String,String>();	
    	extensiones.put("Objeto (*.obj)", "*.obj");
    	String ubicacion = buscarDirectorio(extensiones,"Cargar").getAbsolutePath();
    	if(ubicacion.contains("obj")){
	    	FileInputStream fis = new FileInputStream(ubicacion);
	    	ObjectInputStream ois = new ObjectInputStream(fis);
	    	mapa = (Mapa) ois.readObject();
	    	mapa.regenerarFiguras();
	    	mapa.dibujarMapa();
			panelCentral.getChildren().setAll(mapa.getChildren());
			ois.close();
			fis.close();
			cargarComboBoxesEnUI();
			this.archivo = new File(archivo.getParent());
    	}
		else{
			this.mostrarMensajeDeError("Extension Invalida!");
		}
    
    }

	@FXML public void guardarTrayectoriasEnTxT() throws IOException{
		
    	Map<String,String> extensiones = new HashMap<String,String>();	
    	extensiones.put("Archivo de Texto (*.txt)", "*.txt");
		if(mapa!=null){
    		if(this.trayectoria!=null){
				String ubicacion = buscarDirectorio(extensiones,"Guardar").getAbsolutePath();
	    		if(ubicacion.contains("txt")){
	        		File archivo = new File(ubicacion);
	        		FileWriter fw = new FileWriter(archivo , true);
	        		BufferedWriter bf = new BufferedWriter(fw);
	        		Iterator<String> i = this.trayectoria.obtenerSecuenciaDePasosComoString().iterator();
	        		while(i.hasNext()){
	        			bf.write(i.next());
	        			bf.newLine();
	        		}
	        		bf.close();
	        		fw.close();
	        		this.archivo = new File(archivo.getParent());
	        		System.out.println("Trayectorias guardadas en: " + ubicacion);
	    		}
	    		else{
	    			this.mostrarMensajeDeError("Extension Invalida!");
	    		}
    		}
    		else{
    			this.mostrarMensajeDeError("No existen trayectorias!");
    		}
		}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
	}
	
	@FXML public void guardarTrayectorias() throws IOException{
		
    	Map<String,String> extensiones = new HashMap<String,String>();	
    	extensiones.put("Objeto (*.obj)", "*.obj");
		if(mapa!=null){
    		String ubicacion = buscarDirectorio(extensiones,"Guardar").getAbsolutePath();
    		if (ubicacion.contains("obj")){
    			System.out.println("Guardando trayectorias");
    			if(ubicacion!=null){	
	    	    	FileOutputStream fout = new FileOutputStream(ubicacion);
	    	    	ObjectOutputStream oos = new ObjectOutputStream(fout);
	    	    	oos.writeObject(this.trayectoria);
	    	    	oos.close();
	    	    	fout.close();
	    	    	this.archivo = new File(archivo.getParent());
	    	    	System.out.println("Trayectorias guardadas en: " + ubicacion);
    			}
    		}
    		else{
    			this.mostrarMensajeDeError("Extension Invalida!");
    		}
    	}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
	}
	
	@FXML public void cargarTrayectorias() throws IOException, ClassNotFoundException{
		
    	Map<String,String> extensiones = new HashMap<String,String>();	
    	extensiones.put("Objeto (*.obj)", "*.obj");
		if(mapa!=null){
    		String ubicacion = buscarDirectorio(extensiones,"Cargar").getAbsolutePath();
    		if(ubicacion.contains("obj")){
    			FileInputStream fis = new FileInputStream(ubicacion);
    	    	ObjectInputStream ois = new ObjectInputStream(fis);
    	    	trayectoria = (Trayectoria) ois.readObject();
    	    	trayectoria.regenerarFormasTrayectoria();
    	    	trayectoria.calcularTrayectoria();
    			ois.close();
    			fis.close();
    			this.archivo = new File(archivo.getParent());
    		}
    		else{
    			this.mostrarMensajeDeError("Extension Invalida!");
    		}
		}
    	else{
    		this.mostrarMensajeDeError("No existe el mapa!");
    	}
	
	}
	
	public void cargarComboBoxesEnUI(){
		
		Collection <Recinto> recintos = new LinkedList<Recinto>();
		recintos.add(mapa.getRecintoMapa());
		recintos.addAll(mapa.getRecintos());
		
		for (Recinto recinto : recintos) {
			if(recinto.getGrilla() != null){
				inRecintosComboBox.getItems().add(recinto.getNombre());
				inPuertasComboRecintos.getItems().add(recinto.getNombre());
				inObstaculosComboRecintos.getItems().add(recinto.getNombre());			
			}
		}
		
	}
	
	@FXML public void cerrarAplicacion(){
		System.exit(0);
	}
    
}
