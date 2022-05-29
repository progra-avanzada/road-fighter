package test.java;


import org.junit.Test;
import roadfighter.objects.AutoNpc;
import roadfighter.objects.Mapa;
import roadfighter.objects.NpcAmarillo;
import roadfighter.objects.Obstaculo;

public class MapaTest {
	
	Mapa mapa1 = new Mapa(10,200,0,50000);
	
	@Test
	public void test() {

		Obstaculo o1 = new Obstaculo(1,3,5);
		AutoNpc autoNpc1 = new NpcAmarillo();
		
		mapa1.agregarElemento(o1);
		mapa1.agregarElemento(autoNpc1);
		mapa1.agregarElemento(new Obstaculo(5,7,3));
		mapa1.agregarElemento(new NpcAmarillo());
		
		mapa1.mostrarElementosEnMapa();
		
		mapa1.eliminarElemento(o1);
		mapa1.eliminarElemento(autoNpc1);
		System.out.println("\nDespues de eliminar----");
		mapa1.mostrarElementosEnMapa();
		
		mapa1.generarObstaculos(5);
		System.out.println("\nSe genero varios obstaculos----");
		mapa1.mostrarElementosEnMapa();
		
	}

}
