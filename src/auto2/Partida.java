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
		//Aca recorremos la lista de jugadores y asignamos los puntos
	}
	
	public void iniciar(){}
	public void finalizar(){}
}
