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
		        System.out.println("No existe el destino!");
		}
		for (int i = fin; i!=inicio; i = previos.get(i)){
		    	direcciones.add(i);
		}
		direcciones.add(inicio);
		return invertirLista(direcciones);
	
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
