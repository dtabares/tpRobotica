package clases;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.sun.media.sound.SoftSynthesizer;

import formularios.NuevoRecintoControlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public class Mapa {
	
	private Pane canvas;
	private float tamanioX;
	private float tamanioY;
	private float escalaX;
	private float escalaY;
	private final float tamanioCanvasX = 800;
	private final float tamanioCanvasY = 400;
	Collection <Recinto> recintos = new LinkedList<Recinto>();
		
	public Mapa(float tamanioX, float tamanioY) {
		super();
		this.tamanioX = tamanioX;
		this.tamanioY = tamanioY;
		vb = new VBox();
		canvas = new Pane();
	    canvas.setStyle("-fx-background-color: blue;");
	    canvas.setPrefSize(tamanioCanvasX,tamanioCanvasY);
	    this.escalaX = this.tamanioX / this.tamanioCanvasX;
	    this.escalaY = this.tamanioY / this.tamanioCanvasY;
	}
	
	public void agregarForma(Shape forma){
		
		canvas.getChildren().add(forma);
		vb.getChildren().add(canvas);
		
	}
	//Este metodo no deberia validar sino agregar todo al momento de dibujar
	public void agregarColleccionDeRecintos(Collection <Recinto> recintos){
		
		Collection <Shape> formas = new LinkedList<Shape>();
		Iterator <Recinto> iterador = recintos.iterator();
		
		while (iterador.hasNext()){
			Recinto r = iterador.next(); 
			if (!recintos.contains(r)){
			formas.add(iterador.next().getFormaRecinto());
			}
		}
		
		canvas.getChildren().addAll(formas);
		vb.getChildren().add(canvas);
		
	}
	
	public boolean agregarRecinto(Recinto recinto){
		
		Collection <Shape> formas = new LinkedList<Shape>();		
		
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida && !formas.contains(recinto.getFormaRecinto())){
			formas.add(recinto.getFormaRecinto());
		}

		canvas.getChildren().addAll(formas);
		vb.getChildren().add(canvas);
		return posicionValida;
	}
	
	//Getters & Setters
	public float getEscalaX() {
		return escalaX;
	}

	public float getEscalaY() {
		return escalaY;
	}

	public float getTamanioX() {
		return tamanioX;
	}

	public void setTamanioX(float tamanioX) {
		this.tamanioX = tamanioX;
	}

	public float getTamanioY() {
		return tamanioY;
	}

	public void setTamanioY(float tamanioY) {
		this.tamanioY = tamanioY;
	}
	
	public Collection<Recinto> getRecintos() {
		return recintos;
	}
	
	private VBox vb;
	public VBox getVb() {
		return vb;
	}

	public Pane getCanvas() {
		return canvas;
	}

	
}
