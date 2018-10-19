package br.relesi.rlessa.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import br.relesi.rlessa.entidades.Filme;
import br.relesi.rlessa.entidades.Locacao;
import br.relesi.rlessa.entidades.Usuario;
import br.relesi.rlessa.utils.DataUtils;

public class LocacaoServiceTest {

	
	@Test
	public void teste() {

		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme", 2, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		
		//verificacao
		assertThat(locacao.getValor(), is(equalTo(5.0)));
		assertThat(locacao.getValor(), is(not(6.0)));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}



	
	
}
