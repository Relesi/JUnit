package br.relesi.rlessa.servicos;

import java.util.Date;

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

		// verificacao
		
		Assert.assertEquals(5.0,locacao.getValor(), 0.01);		
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

	}
	
}
