package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import auto2.Auto;
import auto2.AutoNpc;
import auto2.Estado;
import auto2.NpcAmarillo;

public class TestAuto {

	Auto pj1, pj2, pj3, pj4, pj5;
	
	@Before
	public void setUp() throws Exception {
		
		pj1 = new Auto("Azul",new Estado("Nuevo",0));
		pj2 = new Auto("Rojo",new Estado("Nuevo",0));
		pj3 = new Auto("Verde",new Estado("Nuevo",0));
		pj4 = new Auto("Amarillo",new Estado("Nuevo",0));
		pj5 = new Auto("Blanco",new Estado("Nuevo",0));
	}

	@Test
	public void test01_choqueConOtroAuto() {
		
		pj1.realizarChoque(pj2);
		Assert.assertEquals(pj2.getNombreEstado(),"Chocado");
	}
	
	@Test
	public void test02_choqueContraUnNpcAmarillo() {
		AutoNpc npc = new NpcAmarillo();
		
		npc.realizarChoque(pj1);
		Assert.assertEquals(pj1.getNombreEstado(),"Chocado");
		Assert.assertEquals(npc.getVelocidad(),0);
	}

}
