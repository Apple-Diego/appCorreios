package com.br.inmetrics.steps.android;

import static com.br.inmetrics.frm.base.DefaultBaseController.getPage_;
import static com.br.inmetrics.frm.helpers.DataTableHelper.getDt_;

import com.br.inmetrics.frm.bdd.DesignSteps;
import com.br.inmetrics.frm.bdd.Step;
import com.br.inmetrics.frm.exceptions.ElementFindException;
import com.br.inmetrics.pages.android.FormatoPage;
import com.br.inmetrics.pages.android.MenuPage;
import com.br.inmetrics.pages.android.OrigemDestinoPage;
import com.br.inmetrics.pages.android.ResultadoPage;
import com.br.inmetrics.pages.android.ServicosOpcionaisPage;

@DesignSteps 
public class NovoCalculoSteps {
	
	MenuPage  				menuPage  				= getPage_(MenuPage.class);
	OrigemDestinoPage 		origemDestinoPage 		= getPage_(OrigemDestinoPage.class);
	FormatoPage				formatoPage				= getPage_(FormatoPage.class);
	ServicosOpcionaisPage	servicosOpcionaisPage	= getPage_(ServicosOpcionaisPage.class);
	ResultadoPage			resultadoPage			= getPage_(ResultadoPage.class);
	
	@Step("Dado que eu esteja na tela de menu")
	public void dadoQueEuEstejaNaTelaDeMenu() throws ElementFindException {
		menuPage.validarMenu();
	}
	
	@Step("Quando eu acessar a opção Novo Cálculo")
	public void quandoEuAcessarOpcaoNovoCalculo() throws ElementFindException {
		menuPage.clicarMenuNovoCalculo();
	}
	
	@Step("E preencher os campos pedidos")
	public void preencherOsCamposPedidos() throws ElementFindException {
		origemDestinoPage.validarTela();
		origemDestinoPage.inserirCep(getDt_().getStringOf("ORIGEM"), getDt_().getStringOf("DESTINO"));
		origemDestinoPage.clicarBotaoProximoPasso();
		
		formatoPage.validarTela();
		formatoPage.preencherCaixaPacote(getDt_().getStringOf("ALTURA"), getDt_().getStringOf("LARGURA"), getDt_().getStringOf("COMPRIMENTO"));
		formatoPage.selecionarPesoEstimado();
		formatoPage.clicarbotaoContinuar();
		
		servicosOpcionaisPage.validarTela();
		servicosOpcionaisPage.selecionarMaoPropria();
		servicosOpcionaisPage.selecionarAvisoRecebimento();
		servicosOpcionaisPage.selecionarDeclaracaoValor();
		servicosOpcionaisPage.preencherDeclaracaoValor(getDt_().getStringOf("VALOR"));
		servicosOpcionaisPage.clicarbotaoCalcular();
	}
	
	@Step("E devo visualizar a tela de Resultados do Cálculo")
	public void entaoDevoVisualizarTelaDeResultadosDoCalculo() throws ElementFindException {
		resultadoPage.validarTela();
		resultadoPage.validarEnderecoOrigem();
		resultadoPage.validarEnderecoDestino();
	}
	@Step("Então salvo o Resultado do Cálculo")
	public void entaoSalvoResultadoDoCalculo() throws ElementFindException {
		menuPage.clicarBotaoSalvar();
		menuPage.inserirNome(getDt_().getStringOf("NOME"));
		menuPage.clicarBotaoOk();
	}
}