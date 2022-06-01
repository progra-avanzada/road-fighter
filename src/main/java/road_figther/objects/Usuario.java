package road_figther.objects;
//clase diseñada del UML
public class Usuario {

	private String nombreUsuario;
	private String password;
	private int puntuacion;

	public Usuario(String nombreUsuario, String password, int puntuacion) {

		this.setNombreUsuario(nombreUsuario);
		this.setPassword(password);
		this.setPuntuacion(puntuacion);
	}

	public Usuario() {

	}

	public Usuario(String nombreUsuario, String password) {
		// TODO Auto-generated constructor stub
		this.setNombreUsuario(nombreUsuario);
		this.setPassword(password);
		this.setPuntuacion(0);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void crearSala() {
	}

	public void unirseSala() {
	}

	public void salirDeSala() {
	}

	public void eliminarSala() {
	}

}
