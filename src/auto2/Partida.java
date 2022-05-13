package auto2;

import java.util.List;

public class Partida {
	private List<Jugador> jugadores;
	private String mapa; //Falta crear la clase Mapa y reemplazar
	
	private Partida(List<Jugador> participantes)
	{
		jugadores = participantes;
		mapa = "";
	}
	
	public void calcularResultados()
	{
		//Es un dato que nos tiene que dar el mapa
	}
	
	public void iniciar(){
		//Falta interaccion con el mapa
	}
	
	public void finalizar(){
		//Falta interaccion con el mapa
	}
}
