package trayectorias;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Dijkstra {

	private static Map<Integer, Boolean> vis = new HashMap<Integer, Boolean>();
	private static Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
	
	public static List<Integer> obtenerCaminoMasCorto(boolean [][] matrizDeAdyacencia, int inicio, int fin){
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
	
	public static void main(String[] args){
	
	//Matriz de 5 x 5 todos los cuadrantes disponibles	
	boolean [][] matriz = new boolean[][]{
	
		{false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{false,false,false,true,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{true,true,false,false,false,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false},
		{true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false},
		{false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false},
		{false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false},
		{false,false,false,true,true,false,false,false,true,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,true,true,false,false,false,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,true,true,false,false,false,true,false,false,false,false,true,true,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true,false,false,false,true,true,false,false,false},
		{false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false,false},
		{false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true,false},
		{false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false,true,true,true},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,true,false,false,false,false,true,true},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true,false,false,false},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true},
		{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,true,false}

	};
	
	System.out.println(obtenerCaminoMasCorto(matriz, 0,22));
	
	}

}
