package road_figther.utils;

import java.io.File;
//clase diseñada del UML
public class Audio {
	// File archivoAudio = new File("");
	int volumen = 5;

	public Audio(File archivoMuscia, int volumen) {
		// this.archivoAudio = archivoAudio;
		this.volumen = volumen;
	}

	public Audio() {
		// this.archivoAudio = archivoAudio;
		this.volumen = 5;
	}

	public int getVolumen() {
		return volumen;
	}

	public void reproducirAudio() {
		this.volumen = 5;
	}

	public void detenerAudio() {
		this.volumen = 0;
	}

	public void subirVolumen() {
		++this.volumen;
	}

	public void bajarVolumen() {
		--this.volumen;
	}
}
