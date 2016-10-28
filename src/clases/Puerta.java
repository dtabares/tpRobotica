package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Puerta extends FormaPosicionableEnMapa {
	
	private Shape linea;
	private Orientacion orientacion;
	
//	protected float posicionX;
//	protected float posicionY;
//	protected float ancho;
//	protected float largo;
	
	public Puerta(float posicionX, float posicionY, Orientacion orientacion, float ancho){
		this.orientacion = orientacion;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		if(orientacion == Orientacion.Horizontal){
			this.ancho = ancho;
			this.largo = 0;
			this.setearLinea(posicionX + ancho, posicionY);
		}
		else{
			this.ancho = 0;
			this.largo = ancho;
			this.setearLinea(posicionX, posicionY+ancho);
		}
		
		
	}
	
	private void setearLinea(float posFinalX, float posFinalY){
		this.linea = new Line(this.posicionX,this.posicionY,posFinalX,posFinalY);
		this.linea.setFill(Color.ORANGE);
	}

}
