package roadFighter;

public class AutoNpc {
	private int posicionX;
	private int posicionY;
	public AutoNpc(int posicionX, int posicionY) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public void mostrarPosicion() {
		System.out.println(posicionX + posicionY);
	}
	
	@Override
	public String toString() {
		return "Posicion AutoNpc= [" + posicionX + "," + posicionY + "]"; 
	}
	

}
