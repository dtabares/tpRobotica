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
import javafx.stage.StageStyle;

public class NuevoRecintoControlador extends GridPane {
    
	private Stage stage;
	private NuevoRecintoControladorListener listener;
	
	@FXML private Button formularioRecintoOk;
	@FXML private TextField formularioRecintoAncho;
	@FXML private TextField formularioRecintoAlto;
	@FXML private TextField formularioRecintoPosicionX;
	@FXML private TextField formularioRecintoPosicionY;
	
	public void initialize(){}
	
	public NuevoRecintoControlador() {
		FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/formularios/NuevoRecintoForm.fxml"));
		fXMLLoader.setRoot(NuevoRecintoControlador.this);
		fXMLLoader.setController(NuevoRecintoControlador.this);
		try {
			fXMLLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@FXML public void ok (ActionEvent event) {
		listener.nuevoRecintoControladorListenerOK(event);
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
	public String getFormularioRecintoAncho() {
		return formularioRecintoAncho.getText();
	}
	
	public String getFormularioRecintoAlto() {
		return formularioRecintoAlto.getText();
	}

	public String getFormularioRecintoPosicionX() {
		return formularioRecintoPosicionX.getText();
	}

	public String getFormularioRecintoPosicionY() {
		return formularioRecintoPosicionY.getText();
	}

	public NuevoRecintoControladorListener getListener() {
		return listener;
	}

	public void setListener(NuevoRecintoControladorListener listener) {
		this.listener = listener;
	}

	public interface NuevoRecintoControladorListener {
		public void nuevoRecintoControladorListenerOK(ActionEvent e);
		public void nuevoRecintoControladorListenerCancel(ActionEvent e);
	}
	
}
