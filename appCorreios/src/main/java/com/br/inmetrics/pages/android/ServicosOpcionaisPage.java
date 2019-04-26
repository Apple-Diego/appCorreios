package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;

import org.testng.Assert;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class ServicosOpcionaisPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					lblServicosOpcionais			=	getElementById("lbl_mensagem_form"),
					chkMaoPropria 					= 	getElementById("checkMP"),
					chkAvisoRecebimento				= 	getElementById("checkAR"),
					chkDeclaracaoValor 				= 	getElementById("checkDeclValor"),
					inputDeclaraValor 				= 	getElementById("txt_valor"),
					btnVoltar						=	getElementById("button_voltar"),
					btnCalcular						=	getElementById("button_calcular");
	
	public void validarTela() throws ElementFindException {
		Assert.assertEquals(lblServicosOpcionais.getText(), "SERVIÇOS OPCIONAIS");
	}
	
	public void selecionarMaoPropria() throws ElementFindException {
		chkMaoPropria.click();
	}
	
	public void selecionarAvisoRecebimento() throws ElementFindException {
		chkAvisoRecebimento.click();
	}
	
	public void selecionarDeclaracaoValor() throws ElementFindException {
		chkDeclaracaoValor.click();
	}
	
	public void preencherDeclaracaoValor(String valor) throws ElementFindException {
		waitUntilExists(inputDeclaraValor);
		inputDeclaraValor.click();
		inputDeclaraValor.sendKeys(valor);
	}
	
	public void clicarBotaoVoltar() throws ElementFindException {
		btnVoltar.click();
	}
	
	public void clicarbotaoCalcular() throws ElementFindException {
		btnCalcular.click();
	}
}	
