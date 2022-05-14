package auto2;

public abstract class AutoNpc {

	protected String color;
	protected int posicionX;
	protected int posicionY;
	protected int velocidad;
	protected boolean destruido;

	public abstract void realizarChoque(AutoNpc a);
	public abstract void realizarChoque(Auto a);

	public abstract void recibirChoque();
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) { 
		this.color = color;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isDestruido() {
		return destruido;
	}

	public void setDestruido(boolean destruido) {
		this.destruido = destruido;
	}
	
}
