package clases;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

//import com.sun.media.sound.SoftSynthesizer;


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
	
	public void dibujarMapa(){
		System.out.println("Entre a dibujar mapa");
		Collection<Shape> formasPosicionablesEnMapa = new LinkedList<Shape>();
		Collection<Shape> recintosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> grillasParaSerAgregadas = new LinkedList<Shape>();
		Collection<Shape> obstaculosParaSerAgregados = new LinkedList<Shape>();
		
		for (Recinto recinto : this.recintos) {
			recintosParaSerAgregados.add(recinto.getFormaRecinto());
			if(recinto.getGrilla() != null){
				grillasParaSerAgregadas.addAll(recinto.getGrilla().getColeccionDeRectangulos());
			}
			for (Obstaculo obstaculo : recinto.getObstaculos()) {
				obstaculosParaSerAgregados.add(obstaculo.getObstaculo());
			}
		}
		
		//Primero se agregan los recintos en orden
		formasPosicionablesEnMapa.addAll(recintosParaSerAgregados);
		//Luego se agregan las grillas en orden
		formasPosicionablesEnMapa.addAll(grillasParaSerAgregadas);
		//Finalmente los obstaculos en orden
		formasPosicionablesEnMapa.addAll(obstaculosParaSerAgregados);
		
		canvas.getChildren().clear();
		canvas.getChildren().addAll(formasPosicionablesEnMapa);
		vb.getChildren().clear();
		vb.getChildren().add(canvas);
	}
	
	public boolean agregarRecinto(Recinto recinto){
		
		//Collection <Shape> formas = new LinkedList<Shape>();		
		
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		//if(posicionValida && !formas.contains(recinto.getFormaRecinto())){
		if(posicionValida){
			this.recintos.add(recinto);
		}

		//canvas.getChildren().addAll(formas);
		//vb.getChildren().add(canvas);
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
