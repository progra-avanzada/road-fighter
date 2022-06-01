package road_figther.objects;
//clase diseñada del UML
import road_figther.objects.Auto;
import road_figther.objects.AutoNpc;

public class NpcRojo extends AutoNpc {

	public NpcRojo() {
		this.color = "#ff1313";
		this.posicionX = 150;
		this.posicionY = -200;
		this.velocidad = 20;
		this.destruido = true;
	}

	@Override
	public void realizarChoque(AutoNpc c) {
		c.recibirChoque();
		this.velocidad = 0;

	}

	@Override
	public void recibirChoque() {
		if (!destruido) {
			this.velocidad = 0;
			destruido = true;
		}
	}
	
	@Override
	public void realizarChoque(Auto c) {
		c.destruirse();
	}
}
