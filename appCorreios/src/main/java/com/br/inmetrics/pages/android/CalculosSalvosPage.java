package com.br.inmetrics.pages.android;

import static com.br.inmetrics.frm.helpers.QueryHelper.getElementById;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;

public class CalculosSalvosPage extends PageBase {

	@SuppressWarnings("rawtypes")
	VirtualElement 
					calculoSalvo						=	getElementById("listView_historico");
	
	public void acessarCalculoSalvo() throws ElementFindException {
		waitUntilExists(calculoSalvo);
		calculoSalvo.click();
	}
}