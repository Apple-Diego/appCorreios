package com.br.inmetrics.steps.android;

import static com.br.inmetrics.frm.base.DefaultBaseController.getPage_;

import com.br.inmetrics.frm.bdd.DesignSteps;
import com.br.inmetrics.frm.bdd.Step;
import com.br.inmetrics.frm.exceptions.ElementFindException;
import com.br.inmetrics.pages.android.CalculosSalvosPage;
import com.br.inmetrics.pages.android.FormatoPage;
import com.br.inmetrics.pages.android.MenuPage;
import com.br.inmetrics.pages.android.OrigemDestinoPage;
import com.br.inmetrics.pages.android.ResultadoPage;
import com.br.inmetrics.pages.android.ServicosOpcionaisPage;

@DesignSteps 
public class CalculosSalvosSteps {
	
	MenuPage  				menuPage  				= getPage_(MenuPage.class);
	OrigemDestinoPage 		origemDestinoPage 		= getPage_(OrigemDestinoPage.class);
	FormatoPage				formatoPage				= getPage_(FormatoPage.class);
	ServicosOpcionaisPage	servicosOpcionaisPage	= getPage_(ServicosOpcionaisPage.class);
	ResultadoPage			resultadoPage			= getPage_(ResultadoPage.class);
	CalculosSalvosPage		calculosSalvosPage		= getPage_(CalculosSalvosPage.class);
	
	@Step("Quando eu acessar a opção Cálculos Salvos")
	public void quandoEuAcessarOpcaoCalculosSalvos() throws ElementFindException {
		menuPage.clicarMenuCalculosSalvos();
	}

	@Step("E clicar no arquivo salvo")
	public void clicarNoArquivoSalvo() throws ElementFindException {
		calculosSalvosPage.acessarCalculoSalvo();
	}
	
	@Step("E clicar nos botões de confirmação")
	public void clicarNosBotoesDeConfirmacao() throws ElementFindException {
		origemDestinoPage.clicarBotaoProximoPasso();
		formatoPage.clicarbotaoContinuar();
		servicosOpcionaisPage.clicarbotaoCalcular();
	}
	
	@Step("Então vejo o Resultado do Cálculo")
	public void EntaoVejoResultadoDoCalculo() throws ElementFindException {
		resultadoPage.validarTela();
		resultadoPage.validarEnderecoOrigem();
		resultadoPage.validarEnderecoDestino();
	}
}
