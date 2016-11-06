package trayectorias;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Dijkstra {
	
	public List<Integer> obtenerCaminoMasCorto(boolean [][] matrizDeAdyacencia, int inicio, int fin){

		Map<Integer, Boolean> vis = new HashMap<Integer, Boolean>();
		Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
		
		List<Integer> direcciones = new LinkedList<Integer>();
		    Queue<Integer> q = new LinkedList<Integer>();
		    int actual = inicio;
		    q.add(actual);
		    vis.put(actual, true);
		    while(!q.isEmpty()){
		        actual = (int) q.remove();
		        if (actual==fin){
		            break;
		        }else{
		        	for (int i=0;i<matrizDeAdyacencia.length;i++){
		                if(!vis.containsKey(i) && matrizDeAdyacencia[actual][i]==true){
		                    q.add(i);
		                    vis.put(i, true);
		                    prev.put(i, actual);
		                }
		            }
		        }
		    }
		    if (actual!=fin){
		        System.out.println("No existe el destino");
		    }
		    for (int i = fin; i!=inicio ;i = prev.get(i)){
		    	direcciones.add(i);
		    }
		    direcciones.add(inicio);
		    return direcciones;
		}

}
