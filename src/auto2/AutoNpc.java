package auto2;

public abstract class AutoNpc {

	protected String color;
	protected int posicionX;
	protected int posicionY;
	protected int velocidad;
	protected boolean destruido; // vivo 1 o destruido 0

	public abstract void realizarChoque(AutoNpc a);

	public abstract void recibirChoque();
	
	public abstract void moverse();

}
