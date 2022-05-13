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
	
	public void darPuntos( int puntos) {
		this.puntos += puntos;
	}
	
	public int getPuntos () {
		return puntos;
	}
	
	public void setPosicion( int puesto) {
		this.puestoPartida = puesto;
	}
	
	public int getPuesto() {
		return puestoPartida;
	}
	
	
}
