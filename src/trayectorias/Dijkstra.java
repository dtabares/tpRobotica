package trayectorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

public class Dijkstra {
	
	public List<Integer> obtenerCaminoMasCorto(boolean [][] matrizDeAdyacencia, int inicio, int fin){
		
		Map<Integer, Boolean> visitados = new HashMap<Integer, Boolean>();
		Map<Integer, Integer> previos = new HashMap<Integer, Integer>();
		List<Integer> direcciones = new LinkedList<Integer>();
	    Queue<Integer> cola = new LinkedList<Integer>();

	    int actual = inicio;
		cola.add(actual);
		visitados.put(actual, true);
		    
		while(!cola.isEmpty()){
			actual = (int) cola.remove();
		    	if (actual==fin){
		            break;
		        }
		    	else{
		        	for (int i=0;i<matrizDeAdyacencia.length;i++){
		                if(!visitados.containsKey(i) && matrizDeAdyacencia[actual][i]==true){
		                    cola.add(i);
		                    visitados.put(i, true);
		                    previos.put(i, actual);
		                }
		            }
		        }
		}
		if (actual!=fin){
				//Si no encuentra el id del cuadrante destino sale de la funcion retornando null
		        return null;
		}
		for (int i = fin; i!=inicio; i = previos.get(i)){
		    	direcciones.add(i);
		}
		direcciones.add(inicio);
		return invertirLista(direcciones);
	
	}
	
	public float distanciaEntreDosPuntos(float xi, float yi, float xf, float yf){
		
		return (float) Math.sqrt((Math.pow((xf-xi),2))+(Math.pow((yf-yi),2)));
	
	}
	
	public List<Integer> invertirLista(List<Integer> lista){
		
		List<Integer> listaInvertida = new LinkedList<Integer>();
		ListIterator<Integer> i = lista.listIterator(lista.size());
		while(i.hasPrevious()) {
			listaInvertida.add((Integer) i.previous());
		}
		
		return listaInvertida;
	
	}

}
