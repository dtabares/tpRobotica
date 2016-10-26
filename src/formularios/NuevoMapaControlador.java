package formularios;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NuevoMapaControlador extends GridPane {
    
	private Stage stage;
	private NuevoMapaControladorListener listener;
	
	@FXML private Button btnOK;
	@FXML private TextField inAncho;
	@FXML private TextField inAlto;
	
	public void initialize(){}
	
	public NuevoMapaControlador() {
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/formularios/NuevoMapaForm.fxml"));
		fXMLLoader.setRoot(NuevoMapaControlador.this);
		fXMLLoader.setController(NuevoMapaControlador.this);
		try {
			fXMLLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@FXML public void ok (ActionEvent event) {
		listener.nuevoMapaControladorListenerOK(event);
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
	
	//Getters & Setters
	public String getInAncho() {
		return inAncho.getText();
	}
	
	public String getInAlto() {
		return inAlto.getText();
	}

	public NuevoMapaControladorListener getListener() {
		return listener;
	}

	public void setListener(NuevoMapaControladorListener listener) {
		this.listener = listener;
	}

	public interface NuevoMapaControladorListener {
		public void nuevoMapaControladorListenerOK(ActionEvent e);
		public void nuevoMapaControladorListenerCancel(ActionEvent e);
	}
	
}
