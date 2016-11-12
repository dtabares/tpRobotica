package formularios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.tools.DiagnosticCollector;

import clases.Mapa;
import formularios.NuevoMapaControlador.NuevoMapaControladorListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuardarMapaControlador extends GridPane {
    
	private Stage stage;
	Mapa mapa;
	File file;
	
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
		
		String location = directorio.getText();
		if(location.contains("png")){
            WritableImage imagen = mapa.snapshot(new SnapshotParameters(), new WritableImage((int) mapa.getTamanioX(),(int) mapa.getTamanioY()));
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imagen,
                        null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
		}
		else{
			if(directorio!=null){	
	    	FileOutputStream fout = new FileOutputStream(location);
	    	ObjectOutputStream oos = new ObjectOutputStream(fout);
	    	oos.writeObject(mapa);
	    	oos.close();
	    	System.out.println("Mapa guardado en: " + location);
			}
		}
		this.ocultarFormulario();
	}
	
	@FXML public File buscarDirectorio(){
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Mapa");          
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagen (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Objeto (*.obj)", "*.obj"));
        file = fileChooser.showSaveDialog(stage);
        if(!file.getName().contains(".png") && fileChooser.getSelectedExtensionFilter().equals("*.png")) {
        	file = new File(file.getAbsolutePath() + ".png");
        }
        else if(!file.getName().contains(".obj") && fileChooser.getSelectedExtensionFilter().equals("*.obj")){
        	file = new File(file.getAbsolutePath() + ".obj");
        }
        directorio.setText(file.getPath());
		return file;
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
