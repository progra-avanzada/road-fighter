import static org.junit.Assert.*;

import org.junit.Test;

import Clases.Audio;

public class Testeos {

	@Test
	public void testSubirVolumen() {

		Audio audio1 = new Audio();

		audio1.subirVolumen();

		assertTrue(audio1.getVolumen() == 6);

	}

	@Test
	public void testBajarVolumen() {

		Audio audio1 = new Audio();

		audio1.bajarVolumen();

		assertTrue(audio1.getVolumen() == 4);

	}

	@Test
	public void testDetenerVolumen() {

		Audio audio1 = new Audio();

		audio1.detenerAudio();

		assertTrue(audio1.getVolumen() == 0);

	}

}
