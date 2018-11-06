package br.relesi.rlessa.servicos;

import static br.relesi.rlessa.utils.DataUtils.isMesmaData;
import static br.relesi.rlessa.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.relesi.rlessa.entidades.Filme;
import br.relesi.rlessa.entidades.Locacao;
import br.relesi.rlessa.entidades.Usuario;
import br.relesi.rlessa.exceptions.FilemSemEstoqueException;
import br.relesi.rlessa.exceptions.LocadoraException;

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
		System.out.println("Berfore");
		service = new LocacaoService();
		contador++;
		System.out.println(contador);
		// incremento
		// impressao do contator
	}

	@After
	public void testDown() {
		System.out.println("After");
	}

	@BeforeClass
	public static void setupClass() {
		System.out.println("BerforeClass");
	}

	@AfterClass
	public static void testDownClass() {
		System.out.println("AfterClass");
		LocacaoService service = new LocacaoService();
	}

	@Test
	public void testeLocacao() throws Exception {
		// cenario
		// LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 1, 5.0);

		System.out.println("Teste");

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}

	// Teste de maneira elegante
	@Test(expected = FilemSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// cenario
		// LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 2", 0, 4.0);

		// acao
		service.alugarFilme(usuario, filme);
	}

	// Teste de maneira robusta
	@Test
	public void testLocacao_usuarioVazio() throws FilemSemEstoqueException {
		// cenario

		// LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2", 1, 4.0);
		// Usuario usuario = new Usuario("Usuario 1");

		// acao

		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	// Teste de maneira NOVA
	@Test
	public void testLocacao_FilmeVazio() throws FilemSemEstoqueException, LocadoraException {
		// LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		// acao
		service.alugarFilme(usuario, null);

	}

}
