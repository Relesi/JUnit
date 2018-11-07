package br.relesi.rlessa.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.relesi.rlessa.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		
		//cenario
		
		int a = 5;
		int b = 3;
		
		//Calculadora calc = new Calculadora();
	
		
		//acao
		
		int resultado = calc.somar(a, b);
		
		//Verificacao
		
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		
		//cenario
		
		int a = 5;
		int b = 3;
		

		
		
		//acao
		
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		
		Assert.assertEquals(2,resultado);
	}
	
	@Test
	public void deveMultiplicaDoisValores() throws NaoPodeDividirPorZeroException {
		
		//cenario
		
		int a = 10;
		int b = 2; 
		
	
				
		//acao
		
		int resultado = calc.divide(a, b);		
		
		//verificacao
		
		Assert.assertEquals(5,resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExceptionAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		
		int a = 10;
		int b = 0; 
			
		
		calc.divide(a,b);
	}
	

}
