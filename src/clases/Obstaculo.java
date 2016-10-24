package clases;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Obstaculo extends FormaPosicionableEnMapa{
	
	private Shape obstaculo;
	
	public Obstaculo(int x, int y, Obstaculos tipo){
		setearCaracteristicasDelObstaculo(tipo);
		obstaculo = new Rectangle(x,y,this.ancho,this.largo);
		obstaculo.setStroke(Color.BLACK);
		obstaculo.setFill(Color.WHITESMOKE);
		obstaculo.setStrokeWidth(2);
		this.posicionX = x;
		this.posicionY = y;
		this.calcularPosicionBordes();
	}

	public Shape getObstaculo() {
		return obstaculo;
	}

	public void setObstaculo(Shape obstaculo) {
		this.obstaculo = obstaculo;
	}
	
	//Mesa, Silla, Pizarron, Armario
	private void setearCaracteristicasDelObstaculo(Obstaculos tipo){
		switch (tipo) {
		case Silla:
			this.ancho = (float) 0.4;
			this.largo = (float) 0.4;
			break;
		case Mesa:
			this.ancho = (float) 0.8;
			this.largo = (float) 2;
			break;
			
		case Pizarron:
			this.ancho = (float) 0.1;
			this.largo = (float) 2;
			break;
			
		case Armario:
			this.ancho = (float) 0.6;
			this.largo = (float) 1;
			break;

		default:
			break;
		}
	}

}
