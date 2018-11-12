package br.relesi.rlessa.suites;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.relesi.rlessa.servicos.CalculadoraTest;
import br.relesi.rlessa.servicos.CalculoValorLocacaoTest;
import br.relesi.rlessa.servicos.LocacaoServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	CalculadoraTest.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTest.class
})
public class SuiteExecucao {

	//Remova se puder
	
	
}
