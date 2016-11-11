package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cuadrante extends FormaPosicionableEnMapa {
	
	private boolean disponible;
	private Rectangle rectangulo;
	private int id;
	private int idLocal;
	
	public Cuadrante(float posicionX, float posicionY, float ancho, float alto, int numero){
		this.rectangulo = new Rectangle(posicionX,posicionY,ancho,alto);
		this.rectangulo.setStroke(Color.BLACK);
		this.rectangulo.setFill(Color.WHITE);
		this.rectangulo.setStrokeWidth(1);	
		this.disponible = true;
		this.largo = alto;
		this.ancho = ancho;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.calcularPosicionBordes();
		this.id = numero;
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
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	
	

}
