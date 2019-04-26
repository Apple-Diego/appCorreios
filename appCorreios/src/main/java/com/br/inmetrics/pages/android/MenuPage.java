package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementByClassName;
import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;
import static com.br.inmetrics.frm.helpers.QueryHelper.getElementByText;

import org.testng.Assert;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class MenuPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					btnMenuSanduiche 					= 	getElementByClassName("android.widget.ImageView"),
					btnMenuSanduicheMaisOpcoes 			= 	getElementByClassName("android.widget.ImageButton"),
					btnNovoCalculo						=	getElementByText("Novo cálculo"),
					btnAjuda							=	getElementByText("Ajuda"),
					btnConfiguracoes					=	getElementByText("Configurações"),
					btnCalculosSalvos					=	getElementByText("Cálculos Salvos"),
					btnCalculoInternacional				=	getElementByText("Cálculo Internacional"),
					lblPrecoPrazo						=	getElementById("android:id/action_bar_title"),
					btnSalvar							=	getElementById("action_salvar"),
					inputNome							=	getElementById("ed_nome_calculo"),
					btnOk								=	getElementById("bt_ok"),
					btnCancelar							=	getElementById("bt_cancelar");
	
	public void clicarMenuSanduiche() throws ElementFindException {
		waitUntilExists(btnMenuSanduiche);
		btnMenuSanduiche.click();
	}
	
	public void clicarMenuSanduicheMaisOpcoes() throws ElementFindException {
		waitUntilExists(btnMenuSanduicheMaisOpcoes);
		btnMenuSanduicheMaisOpcoes.click();
	}
	
	public void clicarMenuNovoCalculo() throws ElementFindException {
		waitUntilExists(btnNovoCalculo);
		btnNovoCalculo.click();
	}
	
	public void clicarMenuCalculosSalvos() throws ElementFindException {
		waitUntilExists(btnCalculosSalvos);
		btnCalculosSalvos.click();
	}
	
	public void clicarMenuConfiguracoes() throws ElementFindException {
		waitUntilExists(btnConfiguracoes);
		btnConfiguracoes.click();
	}
	
	public void clicarMenuCalculoInternacional() throws ElementFindException {
		waitUntilExists(btnCalculoInternacional);
		btnCalculoInternacional.click();
	}
	
	public void clicarMenuAjuda() throws ElementFindException {
		waitUntilExists(btnAjuda);
		btnAjuda.click();
	}
	
	public void validarMenu() throws ElementFindException {
		Assert.assertEquals(lblPrecoPrazo.getText(), "Preço e Prazo");
		if(!elementExists(btnNovoCalculo, 3)){
			btnMenuSanduiche.click();
		}
	}
	
	public void clicarBotaoSalvar() throws ElementFindException {
		waitUntilExists(btnSalvar);
		btnSalvar.click();
	}
	
	public void inserirNome(String nome) throws ElementFindException {
		inputNome.sendKeys(nome);
	}
	
	public void clicarBotaoOk() throws ElementFindException {
		waitUntilExists(btnOk);
		btnOk.click();
	}
}