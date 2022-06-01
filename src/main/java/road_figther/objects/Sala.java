package road_figther.objects;
import java.util.List;

import road_figther.objects.Usuario;
//clase diseñada del UML
public class Sala {
	private List<Usuario> usuarios;

	public Sala(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public void iniciarPartida(Usuario admin, List<Usuario> usuarios) {
	}
}