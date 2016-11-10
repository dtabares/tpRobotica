package trayectorias;


import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class DijkstraTest {
	
	Dijkstra d = new Dijkstra();
	
	@Test
	public void obtenerElCaminoMasCortoDeUnRecintoDe5Por5(){
		
		//Recinto de 5 x 5 todos los cuadrantes disponibles	
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
		
		LinkedList<Integer> lista = new LinkedList<Integer>();
		lista.add(24);
		lista.add(18);
		lista.add(12);
		lista.add(6);
		lista.add(0);
		Assert.assertEquals(lista,d.obtenerCaminoMasCorto(matriz, 0, 24));
		
	}

}
