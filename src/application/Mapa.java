package application;

import java.util.Collection;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;

public class Mapa {
	
	VBox vb;
	Pane canvas;
		
	Mapa(){
		
		vb = new VBox();
		canvas = new Pane();
	    canvas.setStyle("-fx-background-color: blue;");
	    canvas.setPrefSize(640,480);
	   
	}
	
	public void agregarForma(Shape forma){
		
		canvas.getChildren().add(forma);
		vb.getChildren().add(canvas);
		
	}
	
	public void agregarCollectionDeFormas(Collection <Shape> formas){
		
		canvas.getChildren().addAll(formas);
		vb.getChildren().add(canvas);
		
	}
	
	
	
}
