package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;

import org.testng.Assert;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class FormatoPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					lblFormato						=	getElementById("lbl_mensagem_form"),
					btnCaixaPacote 					= 	getElementById("imgCaixa"),
					btnEnvelope						= 	getElementById("imgCarta"),
					btnRoloPrisma 					= 	getElementById("imgCilindro"),
					inputAltura 					= 	getElementById("txt_altura"),
					inputLargura 					= 	getElementById("txt_largura"),
					inputComprimento 				= 	getElementById("txt_comprimento"),
					inputDiametro 					= 	getElementById("txt_diametro"),
					chkPesoEstimado					=	getElementById("checkAte300"),
					btnVoltar						=	getElementById("button_voltar"),
					btnContinuar					=	getElementById("button_prox");
	
	public void validarTela() throws ElementFindException {
		Assert.assertEquals(lblFormato.getText(), "FORMATO");
	}
	
	public void selecionarCaixaPacote() throws ElementFindException {
		btnCaixaPacote.click();
	}
	
	public void selecionarEnvelope() throws ElementFindException {
		btnEnvelope.click();
	}
	
	public void selecionarRoloPrisma() throws ElementFindException {
		btnRoloPrisma.click();
	}
	
	public void preencherCaixaPacote(String altura, String largura, String comprimento) throws ElementFindException {
		waitUntilExists(btnCaixaPacote);
		btnCaixaPacote.click();
		inputAltura.click();
		inputAltura.sendKeys(altura);
		inputLargura.click();
		inputLargura.sendKeys(largura);
		inputComprimento.click();
		inputComprimento.sendKeys(comprimento);
	}
	
	public void preencherEnvelope(String largura, String comprimento) throws ElementFindException {
		waitUntilExists(btnEnvelope);
		btnEnvelope.click();
		inputLargura.click();
		inputLargura.sendKeys(largura);
		inputComprimento.click();
		inputComprimento.sendKeys(comprimento);
	}
	
	public void preencherRoloPrisma(String comprimento, String diametro) throws ElementFindException {
		waitUntilExists(btnRoloPrisma);
		btnRoloPrisma.click();
		inputComprimento.click();
		inputComprimento.sendKeys(comprimento);
		inputDiametro.click();
		inputDiametro.sendKeys(diametro);
	}
	
	public void selecionarPesoEstimado() throws ElementFindException {
		waitUntilExists(chkPesoEstimado);
		chkPesoEstimado.click();
	}
	
	public void clicarBotaoVoltar() throws ElementFindException {
		waitUntilExists(btnVoltar);
		btnVoltar.click();
	}
	
	public void clicarbotaoContinuar() throws ElementFindException {
		waitUntilExists(btnContinuar);
		btnContinuar.click();
	}
}	
