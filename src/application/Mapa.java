package application;

import java.util.Collection;
import java.util.HashSet;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public class Mapa {
	
	VBox vb;
	Pane canvas;
	private float tamanioX;
	private float tamanioY;
	private float escalaX;
	private float escalaY;
	private final float tamanioCanvasX = 640;
	private final float tamanioCanvasY = 480;
	Collection <Recinto> recintos = new HashSet<Recinto>();
		
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

	public float getEscalaX() {
		return escalaX;
	}

	public float getEscalaY() {
		return escalaY;
	}

	Mapa(){
		
		vb = new VBox();
		canvas = new Pane();
	    canvas.setStyle("-fx-background-color: blue;");
	    canvas.setPrefSize(this.tamanioCanvasX,this.tamanioCanvasY);
	   
	}
	
	public void agregarForma(Shape forma){
		
		canvas.getChildren().add(forma);
		vb.getChildren().add(canvas);
		
	}
	
	public void agregarCollectionDeFormas(Collection <Shape> formas){
		
		canvas.getChildren().addAll(formas);
		vb.getChildren().add(canvas);
		
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
	
	public boolean agregarRecinto(Recinto recinto){
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida){
			this.recintos.add(recinto);
		}
		return posicionValida;
	}

	public Collection<Recinto> getRecintos() {
		return recintos;
	}
	
	
	
	
	
}
