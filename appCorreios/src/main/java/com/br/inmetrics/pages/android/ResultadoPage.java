package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;

import org.testng.Assert;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class ResultadoPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					lblServicosOpcionais			=	getElementById("lbl_mensagem_form"),
					txtOrigem 						= 	getElementById("tv_origem"),
					txtDestino						= 	getElementById("tv_destino"),
					chkDeclaracaoValor 				= 	getElementById("checkDeclValor"),
					inputDeclaraValor 				= 	getElementById("txt_valor"),
					btnVoltar						=	getElementById("button_voltar"),
					btnCalcular						=	getElementById("button_calcular");
	
	public void validarTela() throws ElementFindException {
		waitUntilExists(lblServicosOpcionais);
		Assert.assertEquals(lblServicosOpcionais.getText(), "RESULTADOS DO CÁLCULO");
	}
	
	public void validarEnderecoOrigem() throws ElementFindException {
		waitUntilExists(txtOrigem);
		Assert.assertEquals(txtOrigem.getText(), "Avenida Engenheiro Luiz Carlos Berrini - até 1405 - lado ímpar, Cidade Monções - São Paulo/SP");
	}
	
	public void validarEnderecoDestino() throws ElementFindException {
		waitUntilExists(txtDestino);
		Assert.assertEquals(txtDestino.getText(), "Avenida Interlagos - de 2253 a 3493 - lado ímpar, Jardim Umuarama - São Paulo/SP");
	}
}	
