package br.relesi.rlessa.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.relesi.rlessa.entidades.Filme;
import br.relesi.rlessa.entidades.Locacao;
import br.relesi.rlessa.entidades.Usuario;
import br.relesi.rlessa.exceptions.FilmeSemEstoqueException;
import br.relesi.rlessa.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	
	public LocacaoService service;
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value=1)
	public Double valorLocacao;
	
	@Parameter(value=2)
	public String cenario;

	@Before
	public void setup() {
		service = new LocacaoService();
	}
		private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
		private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
		private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
		
		private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
		private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
		private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
		
	
	@Parameters(name="{2}")		
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object [][] {
			{ Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
			{ Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
			{ Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
			{ Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "5 Filmes: 100%"},
		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		
		//cenario
		Usuario usuario = new Usuario();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificacao		

		assertThat(resultado.getValor(), is(valorLocacao));
	}
//	@Test
//	public void print() {
//		System.out.println(valorLocacao);
//	}
}
