package application;

import java.util.Collection;
import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto extends FormaPosicionableEnMapa{
	

	private Shape recinto;
	private Grilla grilla;
	Collection <Obstaculo> obstaculos = new HashSet<Obstaculo>();
	
	public Collection<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(Collection<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}

	public Recinto(int x, int y, int ancho, int alto){
		setRecinto(new Rectangle(x,y,ancho,alto));
		getRecinto().setStroke(Color.BLACK);
		getRecinto().setFill(Color.WHITESMOKE);
		getRecinto().setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.alto = alto;
		this.calcularPosicionBordes();
	}
	
	public Shape crearRecinto(int x, int y, int ancho, int alto){
		
		Rectangle rectangle = new Rectangle(x,y,ancho,alto);
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.WHITESMOKE);
		rectangle.setStrokeWidth(2);
		return rectangle;
	}

	public Grilla getGrilla() {
		return grilla;
	}
	
	public void setGrilla(Grilla grilla) {
		this.grilla = grilla;
	}

	public Shape getRecinto() {
		return recinto;
	}

	public void setRecinto(Shape recinto) {
		this.recinto = recinto;
	}
	
	public boolean agregarObstaculo(Obstaculo obstaculo){
		boolean posicionValida = Validador.validarSiElObstaculoEntraEnElRecinto(this, obstaculo);
		if(posicionValida){
			this.obstaculos.add(obstaculo);
		}
		return posicionValida;
	}

}
