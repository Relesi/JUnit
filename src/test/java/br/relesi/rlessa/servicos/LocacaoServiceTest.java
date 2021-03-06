package br.relesi.rlessa.servicos;



import static br.relesi.rlessa.matchers.MatchersProprios.ehHoje;
import static br.relesi.rlessa.matchers.MatchersProprios.ehHojeComDiferencasDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.relesi.rlessa.entidades.Filme;
import br.relesi.rlessa.entidades.Locacao;
import br.relesi.rlessa.entidades.Usuario;
import br.relesi.rlessa.exceptions.FilmeSemEstoqueException;
import br.relesi.rlessa.exceptions.LocadoraException;
import br.relesi.rlessa.matchers.MatchersProprios;
import br.relesi.rlessa.utils.DataUtils;

public class LocacaoServiceTest {

	private LocacaoService service;

	private static int contador = 0;

	// Definicao contador

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		//System.out.println("Berfore");
		service = new LocacaoService();
		contador++;
		//System.out.println(contador);
		// incremento
		// impressao do contator
	}

	@After
	public void testDown() {
		//System.out.println("After");
	}

	@BeforeClass
	public static void setupClass() {
		//System.out.println("BerforeClass");
	}

	@AfterClass
	public static void testDownClass() {
		//System.out.println("AfterClass");
		LocacaoService service = new LocacaoService();
	}

	@Test
	public void deveAlugarFilme() throws Exception {
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		// cenario	
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//System.out.println("Teste");

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verificacao

		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		//error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));		
		error.checkThat(locacao.getDataLocacao(), ehHoje(0));		
		//error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));		
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencasDias(1));
		
		
		
	}

	// Teste de maneira elegante
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		
		// cenario		
		Usuario usuario = new Usuario("Usuario 1");	
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

		// acao
		service.alugarFilme(usuario, filmes);
	}

	// Teste de maneira robusta
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		// cenario
		
		// LocacaoService service = new LocacaoService();
		//Filme filme = new Filme("Filme 2", 1, 4.0);
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 4.0));
		

		// acao

		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	// Teste de maneira NOVA
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		
		Usuario usuario = new Usuario("Usuario 1");
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		// acao
		service.alugarFilme(usuario, null);

	}		

	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException{
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao retorno = service.alugarFilme(usuario, filmes);
		
		//verificacao
		assertThat(retorno.getDataRetorno(), MatchersProprios.caiNumaSegunda());
		
	}
	

}
