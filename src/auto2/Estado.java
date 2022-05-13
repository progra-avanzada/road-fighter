package auto2;

public class Estado {
	private String nombreEstado;
	private int tiempoInactividad;
	
	public Estado(String nombreEstado, int tiempoInactividad)
	{
		this.nombreEstado = nombreEstado;
		this.tiempoInactividad = tiempoInactividad;
	}
	
	public void setEstadoChocado()
	{
		this.nombreEstado="Chocado";
		this.tiempoInactividad = 1;
		System.out.println("Esperando " + tiempoInactividad + " segundos");
	}
	
	public void setEstadoExplotado()
	{
		this.nombreEstado="Explotado";
		this.tiempoInactividad = 3;
		System.out.println("Esperando " + tiempoInactividad + " segundos");
	}
	
	public void setEstadoNormal()
	{
		this.nombreEstado="Normal";
		this.tiempoInactividad = 0;
	}
	public int getTiempoInactividad()
	{
		return tiempoInactividad;
	}

}
