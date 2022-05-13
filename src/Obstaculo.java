package roadFighter;

public class Obstaculo implements Comparable<Obstaculo> {
	private int posicionX;
	private int posicionY;
	private double radio;

	public Obstaculo(int posicionX, int posicionY, double radio) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.radio = radio;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public double getRadio() {
		return radio;
	}

	public void mostrarPosicion() {
		System.out.println(posicionX + posicionY);
	}

	@Override
	public String toString() {
		return "Posicion Obstaculo= [" + posicionX + "," + posicionY + "," + radio + "]";
	}

	public void efecto(Auto auto) {
		auto.frenar();
		auto.moverseHorizontal(Math.random());
		// El auto se movera horizontalmente, la clase Auto definira si choca con el
		// borde del mapa o otro auto.

	}

	public int compareTo(Obstaculo o) {
		if (this.posicionY < o.posicionY) {
			return -1;
		} else if (this.posicionY > o.posicionY) {
			return 1;
		}
		return 0;
	}

}
