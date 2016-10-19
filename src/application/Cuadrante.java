package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cuadrante {
	
	private boolean disponible;
	private Rectangle rectangulo;
	
	
	public Cuadrante(float posiscionX, float posicionY, float ancho, float alto){
		this.rectangulo = new Rectangle(posiscionX,posicionY,ancho,alto);
		this.rectangulo.setStroke(Color.BLACK);
		this.rectangulo.setFill(Color.WHITE);
		this.rectangulo.setStrokeWidth(1);	
		this.disponible = true;
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
