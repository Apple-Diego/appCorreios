package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;

import org.testng.Assert;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class OrigemDestinoPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					lblOrigemDestino				=	getElementById("lbl_mensagem_ori_dest"),
					inputCepOrigem 					= 	getElementById("txt_cep_ori"),
					inputCepDestino					=	getElementById("txt_cep_dest"),
					chkCalcularPrazo				=	getElementById("checkSoPrazo"),
					btnProximoPasso					=	getElementById("button_prox");
	
	public void validarTela() throws ElementFindException {
		Assert.assertEquals(lblOrigemDestino.getText(), "ORIGEM E DESTINO");
	}
	
	public void inserirCep(String origem, String destino) throws ElementFindException {
		waitUntilExists(inputCepOrigem);
		inputCepOrigem.click();
		inputCepOrigem.sendKeys(origem);
		
		waitUntilExists(inputCepDestino);
		inputCepDestino.click();
		inputCepDestino.sendKeys(destino);
	}
	
	public void clicarBotaoProximoPasso() throws ElementFindException {
		waitUntilExists(btnProximoPasso);
		btnProximoPasso.click();
	}
}