package com.br.inmetrics.tests.android;

import static com.br.inmetrics.frm.bdd.Gherkin.executeScenario_;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.br.inmetrics.frm.base.TestBase;
import com.br.inmetrics.frm.controllers.AndroidController;
import com.br.inmetrics.frm.testng.DataTableConfig;
import com.br.inmetrics.frm.testng.TestConfig;

@TestConfig(controllerType = AndroidController.class)
public class TestAppCorreiosGherkin extends TestBase {
	
	@DataTableConfig(ct = 1)
	@Test(groups = {"NovoCalculo"}, priority = 1, testName = "CT01 - Efetuar Novo Cálculo")
	public void CT01_EfetuarNovoCalculo() {

		try {
			executeScenario_("NovoCalculo","Efetuar Novo Cálculo");
		} catch (Exception e) {
			Assert.fail("Falha Em Efetuar o Novo Cálculo", e);
		}
	}
	
	@DataTableConfig(ct = 1)
	@Test(groups = {"NovoCalculo"}, priority = 1, testName = "CT02 - Abrir Cálculo Salvo")
	public void CT02_AbrirCalculoSalvo() {

		try {
			executeScenario_("CalculoSalvo","Abrir Cálculo Salvo");
		} catch (Exception e) {
			Assert.fail("Falha Em Abrir Cálculo Salvo", e);
		}
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup(final Method method, final ITestContext context) {

		super.setup(method, context);

	}

	@AfterMethod
	public void teardown(final Method method, final ITestContext context) {

		super.teardown(method, context);
	}
}