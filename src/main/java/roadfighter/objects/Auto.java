package roadfighter.objects;

public class Auto{
	private String color;
	private Estado estado;
	private int posicionX;
	private int posicionY;
	private int velocidad;
	private int velocidadMaxima;
	
	public Auto(String color, Estado estado)
	{
		this.color = color;
		this.estado = estado;
		posicionX = 0;
		posicionY = 0;
		velocidad = 0;
		velocidadMaxima = 200;
	}
	
	
	public void moverseHorizontal(int movimiento){
		posicionX += movimiento;
	}
	
	public void acelerar(){
		if(velocidad<velocidadMaxima)
			velocidad++;
	}
	
	public void frenar(){
		if(velocidad>0)
			velocidad--;
	}
	
	public void realizarChoque(Auto autoAChocar){
		autoAChocar.estado.setEstadoChocado();
	}
	
	public void realizarChoque(AutoNpc autoAChocar){
		//autoAChocar.estado.setEstadoChocado(); ?? creo que no corresponde 
		//realizar un choque a un auto NPC 
	}
	
	public void recibirChoque(){
		estado.setEstadoChocado();
	} 
	
	public void destruirse() {
		estado.setEstadoExplotado();
	}
	
	public String getNombreEstado() {
		return estado.getEstado();
	}
}
