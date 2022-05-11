package auto2;

public abstract class AutoNpc {

	protected String color;
	protected int posicionX;
	protected int posicionY;
	protected int velocidad;

	public abstract void realizarChoque(AutoNpc a);

	public abstract void recibirChoque();

}
