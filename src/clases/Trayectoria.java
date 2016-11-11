package clases;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;

public class Trayectoria implements Serializable{
	
	private List<Paso> pasos;
	private List<Cuadrante> listaDeCuadrantesDeOrigenADestino;
	private double posicionEnGradosRespectoDelNorteMagnetico;
	

	public Trayectoria (List<Cuadrante> lista, double grados){
		this.pasos = new LinkedList<Paso>();
		this.listaDeCuadrantesDeOrigenADestino = lista;
		this.posicionEnGradosRespectoDelNorteMagnetico = grados;
	}
	
	private void agregarPaso(double direccion, int cantidadDeBaldozas){
		this.pasos.add(new Paso(direccion, cantidadDeBaldozas));
	}
	
	public List<Paso> getListaDePasos(){
		return this.pasos;
	}
	
	public void calcularTrayectoria(){
		Iterator<Cuadrante> iterador = this.listaDeCuadrantesDeOrigenADestino.iterator();
		Cuadrante cuadranteActual;
		Cuadrante cuadranteSiguiente = null;
		double dir;
		while(iterador.hasNext()){
			if (cuadranteSiguiente == null){
				cuadranteActual = iterador.next();
				cuadranteActual.getRectangle().setFill(Color.GREEN);
			}
			else{
				cuadranteActual = cuadranteSiguiente;
				cuadranteActual.getRectangle().setFill(Color.BLUE);
			}
			if(iterador.hasNext()){
				cuadranteSiguiente = iterador.next();
				dir = this.calcularDireccionEnGrados(cuadranteActual, cuadranteSiguiente);
				dir = dir + this.posicionEnGradosRespectoDelNorteMagnetico;
				if(dir > 360){
					dir = dir - 360;
				}
				Paso paso = new Paso(dir,1);
				this.pasos.add(paso);
			}
			cuadranteSiguiente.getRectangle().setFill(Color.GREEN);
		}
	}
	
	public void borrarTrayectoria(){
		
		Iterator<Cuadrante> iterador = this.listaDeCuadrantesDeOrigenADestino.iterator();
		Cuadrante cuadranteActual;
		while (iterador.hasNext()){
			cuadranteActual=iterador.next();
			cuadranteActual.getRectangle().setFill(Color.WHITE);
		}
		
	}
	
	private double calcularDireccionEnGrados(Cuadrante actual, Cuadrante siguiente){
		double direccion = 0;
		float x = actual.getPosicionX() - siguiente.getPosicionX();
		float y = actual.getPosicionY() - siguiente.getPosicionY();
		
		//En caso de que x == 0 quiere decir que puede se mueve solo en Y
		if (x == 0){
			// Cuadrante superior
			if(y == 1){
				direccion = 0;
			}
			//cuadrante inferior
			else{
				direccion = 180;
			}
		}
		else{
			//En caso de que y == 0 quiere decir que puede se mueve solo en X
			if (y == 0){
				//Cuadrante derecho
				if( x == -1){
					direccion = 90;
				}
				//Cuadrante izquierdo
				else{
					direccion = 270;
				}
			}
			// Llegado este caso, se que se mueve en diagonal
			else{
				// Cuadrante superior
				if(y== 1){
					//Cuadrante derecho
					if(x==-1){
						direccion = 45;
					}
					//Cuadrante izquierdo
					else{
						direccion = 315;
					}
				}
				// Cuadrante inferior
				else{
					//Cuadrante derecho
					if(x==-1){
						direccion = 135;
					}
					//Cuadrante izquierdo
					else{
						direccion = 225;
					}
				}
			}
		}
		
		return direccion;
	}
	
	//YA PODES CONSUMIR ESTO DESDE LA UI
	public List<String> obtenerSecuenciaDePasosComoString(){
		List<String> secuencia = new LinkedList<String>();
		Iterator<Paso> iterador = this.pasos.iterator();
		Paso paso;
		String s;
		String textoBaldosa = "baldosa";
		while(iterador.hasNext()){
			paso = iterador.next();
			double dir = paso.getDireccionEnGrados();
			int baldosas = paso.getNumeroDeBaldosas();
			if(baldosas > 1){
				textoBaldosa = "baldosas";
			}
			s = "Moverse " + baldosas + " " + textoBaldosa + " en direccion: " + dir ;
			secuencia.add(s);
		}
		return secuencia;
	}

}
