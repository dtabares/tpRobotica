package application;

import java.util.Collection;
import java.util.Iterator;


public class Validador {
	
	public static boolean validarSiElRecintoEntraEnElMapa(Mapa mapa, Recinto recinto){
		boolean entra = false;
		Collection<Recinto> recintos = mapa.getRecintos();
		Iterator<Recinto> iterador = recintos.iterator();
		
		if(recinto.getBordeSuperiorIzquierdo().getX() >= 0 && 
			recinto.getBordeSuperiorDerecho().getX() <= mapa.getTamanioX() &&
			recinto.getBordeSuperiorIzquierdo().getY() >= 0 &&
			recinto.getBordeInferiorDerecho().getY() <= mapa.getTamanioY()){
			entra = true;
		}
		
		while(iterador.hasNext() && entra == true){
			if(seSuperponenLosRecintos(iterador.next(), recinto)){
				entra = false;
			}
		}
		return entra;
	}
	
	//Devuelve true si se superponen
	private static boolean seSuperponenLosRecintos(Recinto a, Recinto b){
		boolean seSuperponen = true;
		if (!sonIgualesYTienenLaMismaPosicion(a, b)){
			seSuperponen = (sePisanEnX(a, b) && sePisanEnY(a, b));
		}
		
		
		return seSuperponen;
	}
	
	private static boolean sonIgualesYTienenLaMismaPosicion(Recinto a, Recinto b){
		boolean iguales = false;
		
		if(a.getBordeInferiorDerecho().equals(b.getBordeInferiorDerecho())  &&
				a.getBordeInferiorIzquierdo().equals(b.getBordeInferiorIzquierdo()) &&
				a.getBordeSuperiorDerecho().equals(b.getBordeSuperiorDerecho()) &&
				a.getBordeSuperiorIzquierdo().equals(b.getBordeSuperiorIzquierdo())){
			iguales = true;
		}
		
		return iguales;
	}
	
	private static boolean sePisanEnX(Recinto a, Recinto b){
		boolean sePisan = false;
		
		if((b.getBordeSuperiorDerecho().getX() >= a.getBordeSuperiorDerecho().getX() && 
				b.getBordeSuperiorIzquierdo().getX() <= a.getBordeSuperiorDerecho().getX()) 
				|| (b.getBordeInferiorDerecho().getX() >= a.getBordeInferiorDerecho().getX() && 
					b.getBordeInferiorIzquierdo().getX() <= a.getBordeInferiorDerecho().getX())){
			sePisan = true;
		}
		else{
			if((b.getBordeSuperiorIzquierdo().getX() <= a.getBordeSuperiorIzquierdo().getX() && 
				b.getBordeSuperiorDerecho().getX() >= a.getBordeSuperiorIzquierdo().getX()) ||
				(b.getBordeInferiorIzquierdo().getX() <= a.getBordeInferiorIzquierdo().getX() && 
				b.getBordeInferiorDerecho().getX() >= a.getBordeInferiorIzquierdo().getX())){
				sePisan = true;
			}
			else {
				if((b.getBordeSuperiorIzquierdo().getX() >= a.getBordeSuperiorIzquierdo().getX() && 
					b.getBordeSuperiorDerecho().getX() <= a.getBordeSuperiorDerecho().getX()) ||
					(b.getBordeInferiorIzquierdo().getX() >= a.getBordeInferiorIzquierdo().getX() && 
					b.getBordeInferiorDerecho().getX() <= a.getBordeInferiorDerecho().getX())){
					sePisan = true;
				}
			}
		}
		return sePisan;
	}
	
	private static boolean sePisanEnY(Recinto a, Recinto b){
		boolean sePisan = false;
		
		if((b.getBordeSuperiorDerecho().getY() <= a.getBordeSuperiorDerecho().getY() && 
			b.getBordeInferiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY()) ||
			(b.getBordeSuperiorIzquierdo().getY() <= a.getBordeSuperiorIzquierdo().getY() && 
			b.getBordeInferiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY())){
			sePisan = true;
		}
		else{
			if((b.getBordeSuperiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY() && 
				b.getBordeInferiorIzquierdo().getY() >= a.getBordeInferiorIzquierdo().getY()) ||
				(b.getBordeSuperiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY() && 
				b.getBordeInferiorDerecho().getY() >= a.getBordeInferiorDerecho().getY())){
				sePisan = true;
			}
			else {
				if((b.getBordeSuperiorIzquierdo().getY() >= a.getBordeSuperiorIzquierdo().getY() && 
					b.getBordeInferiorIzquierdo().getY() <= a.getBordeInferiorIzquierdo().getY()) ||
					(b.getBordeSuperiorDerecho().getY() >= a.getBordeSuperiorDerecho().getY() && 
					b.getBordeInferiorDerecho().getY() <= a.getBordeInferiorDerecho().getY())){
					sePisan = true;
				}
			}
		}
		return sePisan;
	}

}
