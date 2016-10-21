package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cuadrante extends FormaPosicionableEnMapa{
	
	private boolean disponible;
	private Rectangle rectangulo;
	
	
	public Cuadrante(float posicionX, float posicionY, float ancho, float alto){
		this.rectangulo = new Rectangle(posicionX,posicionY,ancho,alto);
		this.rectangulo.setStroke(Color.BLACK);
		this.rectangulo.setFill(Color.WHITE);
		this.rectangulo.setStrokeWidth(1);	
		this.disponible = true;
		this.alto = alto;
		this.ancho = ancho;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.calcularPosicionBordes();
	}
	
	public void marcarComoDisponible(){
		this.disponible = true;
		this.rectangulo.setFill(Color.WHITE);
	}
	
	public void marcarComoNoDisponible(){
		this.disponible = false;
		this.rectangulo.setFill(Color.RED);
	}
	
	public boolean estaDisponible(){
		return this.disponible;
	}
	
	public Rectangle getRectangle(){
		return this.rectangulo;
	}

}
