package com.br.inmetrics.features.android;

import com.br.inmetrics.frm.bdd.Feature;
import com.br.inmetrics.frm.bdd.Scenario;

import static com.br.inmetrics.frm.bdd.Gherkin.*;

import java.util.concurrent.ExecutionException;

@Feature("CalculoSalvo")
public class CalculosSalvosFeature {
	
	@SuppressWarnings("static-access")
	@Scenario("Abrir Cálculo Salvo")  
	public void abrirCalculoSalvo() throws ExecutionException {
		
		given_("Dado que eu esteja na tela de menu").
		when_("Quando eu acessar a opção Cálculos Salvos").
		and_("E clicar no arquivo salvo").
		and_("E clicar nos botões de confirmação").
		then_("Então vejo o Resultado do Cálculo").
		execute_();
	}
}