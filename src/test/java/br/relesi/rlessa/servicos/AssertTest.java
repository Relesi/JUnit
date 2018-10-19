package br.relesi.rlessa.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.relesi.rlessa.entidades.Usuario;

public class AssertTest {

	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Comparação errada",1, 2);
		Assert.assertEquals(0.51234, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		
		Assert.assertEquals(Integer.valueOf(i),i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("renato", "renato");
		Assert.assertNotEquals("renato", "java");
		
		Assert.assertTrue("renato".equalsIgnoreCase("Renato"));
		Assert.assertTrue("renato".startsWith("re"));		
		
		Usuario u1 = new Usuario("usuario 1");
		Usuario u2 = new Usuario("usuario 1");
		Usuario u3 = null;
		
		Assert.assertEquals(u1, u2);
		Assert.assertSame(u2, u2);
		Assert.assertNotSame(u1, u2);
		Assert.assertNotNull(u2);
		Assert.assertNull(u3);
		
	}
}
