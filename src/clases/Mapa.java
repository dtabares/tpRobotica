package clases;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Mapa extends AnchorPane{

	private float tamanioX;
	private float tamanioY;
	private float escalaX;
	private float escalaY;
	Collection <Recinto> recintos = new LinkedList<Recinto>();
		
	public Mapa(float tamanioX, float tamanioY) {
		this.setPrefSize(tamanioX, tamanioY);
		this.tamanioX = tamanioX;
		this.tamanioY = tamanioY;
		this.ajustarEscala();
	}
	
	private void ajustarEscala() {
		// TODO Auto-generated method stub
		
	}

	public void agregarForma(Shape forma){
		
		System.out.println(this.getChildren().size());
		if (this.getChildren().contains(forma)){
			System.out.println("La forma: " + forma + "ya existe");
		}
		else{
			this.getChildren().add(forma);
		}
		System.out.println(this.getChildren().size());
	}
	
	public void mostrarGrillas(Collection <Grilla> grillas){
		
		Iterator<Grilla> iGrillas = grillas.iterator();
		while (iGrillas.hasNext()){
			Grilla g = iGrillas.next();
			Iterator<Shape> iFormas = g.getColeccionDeRectangulos().iterator();
				while (iFormas.hasNext()){
					agregarForma(iFormas.next());
				}
			}
	
	}

	
	public void dibujarMapa(){
		Collection<Shape> formasPosicionablesEnMapa = new LinkedList<Shape>();
		Collection<Shape> recintosParaSerAgregados = new LinkedList<Shape>();
		Collection<Shape> grillasParaSerAgregadas = new LinkedList<Shape>();
		Collection<Shape> obstaculosParaSerAgregados = new LinkedList<Shape>();
		Rectangle background = new Rectangle (tamanioX,tamanioY);
		background.setStroke(Color.BLACK);
		background.setFill(Color.WHITE);
		
		for (Recinto recinto : this.recintos) {
			recintosParaSerAgregados.add(recinto.getFormaRecinto());
			if(recinto.getGrilla() != null){
				recinto.verificarDisponibilidadDeLaGrilla();
				grillasParaSerAgregadas.addAll(recinto.getGrilla().getColeccionDeRectangulos());
			}
			for (Obstaculo obstaculo : recinto.getObstaculos()) {
				obstaculosParaSerAgregados.add(obstaculo.getObstaculo());
			}
		}
		
		//Primero se agrega el fondo
		formasPosicionablesEnMapa.add(background);
		//Luego se agregan los recintos en orden
		formasPosicionablesEnMapa.addAll(recintosParaSerAgregados);
		//Luego se agregan las grillas en orden
		formasPosicionablesEnMapa.addAll(grillasParaSerAgregadas);
		//Finalmente los obstaculos en orden
		formasPosicionablesEnMapa.addAll(obstaculosParaSerAgregados);
		
		this.getChildren().clear();
		this.getChildren().addAll(formasPosicionablesEnMapa);

	}
	
	public boolean agregarRecinto(Recinto recinto){
			
		boolean posicionValida = Validador.validarSiElRecintoEntraEnElMapa(this, recinto);
		if(posicionValida){
			this.recintos.add(recinto);
		}

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

	public Recinto buscarRecintoPorNombre(String nombreBuscado) {
		boolean encontro = false;
		Recinto recintoBuscado = null;
		for (Recinto recinto : recintos) {
			if (encontro == true){
				break;
			}
			else{
				if(recinto.getNombre().equals(nombreBuscado)){
					recintoBuscado = recinto;
				}
			}
		}
		return recintoBuscado;
	}

}
