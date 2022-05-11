package auto2;

public class NpcRojo extends AutoNpc {

	public NpcRojo() {
		this.color = "#ff1313";
		this.posicionX = 150;
		this.posicionY = -200;
		this.velocidad = 20;
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
