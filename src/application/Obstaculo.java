package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Obstaculo extends FormaPosicionableEnMapa{
	
	private Shape obstaculo;
	
	public Obstaculo(int x, int y, int ancho, int alto){
		obstaculo = new Rectangle(x,y,ancho,alto);
		obstaculo.setStroke(Color.BLACK);
		obstaculo.setFill(Color.WHITESMOKE);
		obstaculo.setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.ancho = ancho;
		this.alto = alto;
		this.calcularPosicionBordes();
	}

	public Shape getObstaculo() {
		return obstaculo;
	}

	public void setObstaculo(Shape obstaculo) {
		this.obstaculo = obstaculo;
	}

}
