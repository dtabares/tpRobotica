import org.junit.Assert;
import org.junit.Test;

import clases.Contador;
import clases.Grilla;
import clases.Mapa;
import clases.Obstaculo;
import clases.Obstaculos;
import clases.Recinto;

public class MatrizDeAdyacenciaTest {

	@Test
	public void x(){
		Mapa mapa = new Mapa(800,600);
		Contador contador = Contador.getContador();
		contador.setProximoNumeroDeCuadrante(0);
		
		Recinto nuevoRecinto = new Recinto(0, 0, 100, 100, "Nuevo Recinto");
		
		Grilla grilla = new Grilla(nuevoRecinto,20,1);
		
		nuevoRecinto.setGrilla(grilla);
		
		Obstaculo silla1 = new Obstaculo((float)1, (float)1, Obstaculos.Silla);
		Obstaculo silla2 = new Obstaculo((float)45, (float)45,Obstaculos.Silla);
		
		nuevoRecinto.agregarObstaculo(silla2);
		nuevoRecinto.agregarObstaculo(silla1);
		
		mapa.agregarRecinto(nuevoRecinto);
		nuevoRecinto.verificarDisponibilidadDeLaGrilla();
		
		
		
		boolean[][] matriz = grilla.getMatrizDeAdyacencia().getMatrizDeAdyacenciaEnBooleanos();
		boolean[][] matrizEsperada = new boolean[25][];
		matrizEsperada[0]= new boolean[] {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[1]= new boolean[]{false,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[2]= new boolean[]{false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[3]= new boolean[]{false,false,true,false,true,false,false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[4]= new boolean[]{false,false,false,true,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[5]= new boolean[]{false,true,false,false,false,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[6]= new boolean[]{false,true,true,false,false,true,false,true,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[7]= new boolean[]{false,true,true,true,false,false,true,false,true,false,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[8]= new boolean[]{false,false,true,true,true,false,false,true,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[9]= new boolean[]{false,false,false,true,true,false,false,false,true,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[10]= new boolean[]{false,false,false,false,false,true,true,false,false,false,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false};
		matrizEsperada[11]= new boolean[]{false,false,false,false,false,true,true,true,false,false,true,false,false,false,false,true,true,true,false,false,false,false,false,false,false};
		matrizEsperada[12]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		matrizEsperada[13]= new boolean[]{false,false,false,false,false,false,false,true,true,true,false,false,false,false,true,false,false,true,true,true,false,false,false,false,false};
		matrizEsperada[14]= new boolean[]{false,false,false,false,false,false,false,false,true,true,false,false,false,true,false,false,false,false,true,true,false,false,false,false,false};
		matrizEsperada[15]= new boolean[]{false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true,false,false,false,true,true,false,false,false};
		matrizEsperada[16]= new boolean[]{false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,true,false,true,false,false,true,true,true,false,false};
		matrizEsperada[17]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,true,false,true,false,false,true,false,true,false,false,true,true,true,false};
		matrizEsperada[18]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,true,false,true,false,false,true,true,true};
		matrizEsperada[19]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,true,false,false,false,false,true,true};
		matrizEsperada[20]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true,false,false,false};
		matrizEsperada[21]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false,false};
		matrizEsperada[22]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true,false};
		matrizEsperada[23]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,true,false,false,true,false,true};
		matrizEsperada[24]= new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,true,false};
		
//		for (boolean[] fila : matriz) {
//			for (boolean col : fila) {
//				System.out.print(col + " ");
//			}
//			System.out.println("");
//		}
		
		for (int i = 0; i< matrizEsperada.length;i++){
			Assert.assertArrayEquals(matrizEsperada[i], matriz[i]);
		}
		
		
		

	}
}
