package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Recinto {
	
	public Shape crearRecinto(int x, int y, int ancho, int alto){
		
		Rectangle rectangle = new Rectangle(x,y,ancho,alto);
		rectangle.setStroke(Color.BLACK);
		rectangle.setFill(Color.WHITESMOKE);
		rectangle.setStrokeWidth(2);
		return rectangle;
	
	}

}
