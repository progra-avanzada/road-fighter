package auto2;

public class Jugador {
	private int id;
	private Auto auto;
	private int puntos;
	private int puestoPartida;
	
	public Jugador(Auto auto)
	{
		id = 0; //Random? de donde lo sacariamos?
		this.auto = auto;
		puntos = 0;
		puestoPartida = 0;
	}
}
