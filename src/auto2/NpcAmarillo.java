package auto2;

public class NpcAmarillo extends AutoNpc {

	public NpcAmarillo() {
		this.color = "#ffec33";
		this.posicionX = 150;
		this.posicionY = -200;
		this.velocidad = 30; // ejemplo 30px por segundo?
	}

	@Override
	public void realizarChoque(AutoNpc c) {
		c.recibirChoque();
		this.velocidad = 0;

	}

	@Override
	public void recibirChoque() {
		this.velocidad = 0;
	}

}
