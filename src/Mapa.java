package roadFighter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapa {
	//private final String RUTA_IMAGEN = "ruta.png";
	private double bordeIzquierdo;
	private double bordeDerecho;
	private double inicio;
	private double fin;
	List<Obstaculo> listaObstaculos;
	List<AutoNpc> listaAutosNpc;

	public Mapa(double bordeIzquierdo, double bordeDerecho, double inicio, double fin) {
		this.bordeIzquierdo = bordeIzquierdo;
		this.bordeDerecho = bordeDerecho;
		this.inicio = inicio;
		this.fin = fin;
		this.listaObstaculos = new ArrayList<Obstaculo>();
		this.listaAutosNpc = new ArrayList<AutoNpc>();
	}

	public void agregarElemento(Obstaculo nuevo) {
		listaObstaculos.add(nuevo);
	}

	public void eliminarElemento(Obstaculo destruido) {
		listaObstaculos.remove(destruido);
	}

	public void agregarElemento(AutoNpc nuevo) {
		listaAutosNpc.add(nuevo);
	}

	public void eliminarElemento(AutoNpc destruido) {
		listaAutosNpc.remove(destruido);
	}

	public void mostrarElementosEnMapa() {
		for (Obstaculo elemento : listaObstaculos)
			System.out.println(elemento);

		for (AutoNpc elemento : listaAutosNpc)
			System.out.println(elemento);
	}

	public void generarObstaculos(int cantidadObstaculos) {

		double inicioDeObstaculos = (this.fin - this.inicio) * 0.01;
		int radioMinimo = 5;
		int radioMaximo = 10;

		Integer posicionX;
		Integer posicionY;
		double radioObstaculo;

		for (int i = 0; i < cantidadObstaculos; i++) {
			posicionX = (int) (Math.random() * (bordeDerecho + 1 - bordeIzquierdo) + bordeIzquierdo);
			posicionY = (int) (Math.random() * (this.fin + 1 - inicioDeObstaculos) + inicioDeObstaculos);
			radioObstaculo = (Math.random() * (radioMaximo + 1 - radioMinimo) + radioMinimo);
			radioObstaculo = Math.round(radioObstaculo*100.0)/100.0;
			
			listaObstaculos.add(new Obstaculo(posicionX, posicionY, radioObstaculo));
		}
		Collections.sort(listaObstaculos);
	}

}
