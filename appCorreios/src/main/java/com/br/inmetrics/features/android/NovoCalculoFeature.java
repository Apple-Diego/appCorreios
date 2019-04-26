package com.br.inmetrics.features.android;

import com.br.inmetrics.frm.bdd.Feature;
import com.br.inmetrics.frm.bdd.Scenario;

import static com.br.inmetrics.frm.bdd.Gherkin.*;

import java.util.concurrent.ExecutionException;

@Feature("NovoCalculo")
public class NovoCalculoFeature {
	
	@SuppressWarnings("static-access")
	@Scenario("Efetuar Novo Cálculo")  
	public void efetuarNovoCalculo() throws ExecutionException {
		
		given_("Dado que eu esteja na tela de menu").
		when_("Quando eu acessar a opção Novo Cálculo").
		and_("E preencher os campos pedidos").
		and_("E devo visualizar a tela de Resultados do Cálculo").
		then_("Então salvo o Resultado do Cálculo").
		execute_();
	}
}