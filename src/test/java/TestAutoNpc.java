package test.java;

import org.junit.Test;
import roadfighter.objects.NpcAmarillo;
import roadfighter.objects.NpcRojo;

import static org.junit.Assert.assertTrue;

public class TestAutoNpc {

	@Test
	public void testAutoAmarillo() {
		NpcAmarillo auto1 = new NpcAmarillo();
		NpcAmarillo auto2 = new NpcAmarillo();

		auto1.realizarChoque(auto2);

		assertTrue(auto1.getVelocidad() == 0);
		//assertTrue(auto2.getVelocidad() == 0);
		assertTrue(auto2.isDestruido());
	}

	@Test
	public void testAutoRojo() {
		NpcRojo auto1 = new NpcRojo();
		NpcAmarillo auto2 = new NpcAmarillo();
 
		auto1.realizarChoque(auto2);

		assertTrue(auto1.getVelocidad() == 0);
		//assertTrue(auto2.getVelocidad() == 0);
		assertTrue(auto2.isDestruido());
	}

}
