package formularios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.tools.DiagnosticCollector;

import clases.Mapa;
import formularios.NuevoMapaControlador.NuevoMapaControladorListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class GuardarMapaControlador extends GridPane {
    
	private Stage stage;
	Mapa mapa;
	
	@FXML private TextField directorio;
	
	public void initialize(){}
	
	public GuardarMapaControlador(Mapa mapa) {
		this.mapa = mapa;
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/formularios/GuardarMapaForm.fxml"));
		fXMLLoader.setRoot(GuardarMapaControlador.this);
		fXMLLoader.setController(GuardarMapaControlador.this);
		try {
			fXMLLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@FXML public void guardarMapa() throws IOException{
		if(directorio!=null){
			String location = directorio.getText()+"mapa.obj";
    	FileOutputStream fout = new FileOutputStream(location);
    	ObjectOutputStream oos = new ObjectOutputStream(fout);
    	oos.writeObject(mapa);
    	oos.close();
    	System.out.println("Mapa guardado en: " + location);
		}
	}
	
	@FXML public File buscarDirectorio(){
		
		System.out.println("Directorio");
		DirectoryChooser dc = new DirectoryChooser();
		File directorio = dc.showDialog(stage);
		this.directorio.setText(directorio.toString());
		return directorio;
		
	}
	
	@FXML public void cancelar(){
		ocultarFormulario();
	} 
	
	public void mostrarFormulario() {
		if (stage == null) {
			stage = new Stage();
			stage.setResizable(false);
			stage.setScene(new Scene(this));
			stage.show();
		} else {
			stage.show();
		}
	}

	public void ocultarFormulario() {
		if (stage != null) {
			stage.hide();
		}
	}

}
