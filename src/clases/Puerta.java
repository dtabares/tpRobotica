package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Puerta extends FormaPosicionableEnMapa {
	
	private Shape linea;
	private float posicionFinalX;
	private float posicionFinalY;
	private Orientacion orientacion;
	
//	protected float posicionX;
//	protected float posicionY;
//	protected float ancho;
//	protected float largo;
	
	public Puerta(float posicionX, float posicionY, Orientacion orientacion, float ancho){
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.orientacion = orientacion;
		if(orientacion == Orientacion.Horizontal){
			this.ancho = ancho;
			this.largo = 0;
			this.posicionFinalX = posicionX + ancho;
			this.posicionFinalY = posicionY;
		}
		else{
			this.ancho = 0;
			this.largo = ancho;
			this.posicionFinalX = posicionX;
			this.posicionFinalY = posicionY+ancho;
		}
		
		this.setearLinea(this.posicionFinalX, this.posicionFinalY);
		
		
	}
	
	private void setearLinea(float posFinalX, float posFinalY){
		this.linea = new Line(this.posicionX,this.posicionY,posFinalX,posFinalY);
		this.linea.setFill(Color.ORANGE);
	}

	public float getPosicionFinalX() {
		return posicionFinalX;
	}

	public float getPosicionFinalY() {
		return posicionFinalY;
	}
	
	public Orientacion getOrientacion() {
		return this.orientacion;
	}

	
}
