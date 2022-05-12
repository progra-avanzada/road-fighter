package auto2;

public class Auto{
	private String color;
	private Estado estado;
	private int posicionX;
	private int posicionY;
	private int velocidad;
	
	public Auto(String color, Estado estado)
	{
		this.color = color;
		this.estado = estado;
		posicionX = 0;
		posicionY = 0;
		velocidad = 0;
	}
	
	
	public void moverseHorizontal(int movimiento){}
	public void acelerar(){}
	public void frenar(){}
	public void realizarChoque(Auto autoAChocar){}
	public void realizarChoque(AutoNpc autoAChocar){}
	public void recibirChoque(){} //Error? 
	
}
