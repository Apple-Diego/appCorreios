package com.br.inmetrics.utils.android;

import static com.br.inmetrics.frm.base.DefaultBaseController.getDriver_;
import static com.br.inmetrics.frm.helpers.PropertyHelper.getProperty;
import static com.br.inmetrics.frm.utils.LoggerUtils.getMessage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Driver;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.inmetrics.frm.base.PageBase;
import com.br.inmetrics.frm.base.VirtualElement;
import com.br.inmetrics.frm.exceptions.ElementFindException;
import com.br.inmetrics.frm.exceptions.GenericException;
import com.br.inmetrics.frm.utils.DataTable;
import com.br.inmetrics.frm.utils.GenericDataTable;
import com.br.inmetrics.frm.utils.NewGenericDataTable;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings({"rawtypes", "unused"})
public class Utils extends PageBase {

	public static Logger logger = Logger.getLogger(Utils.class);
	private static Logger Log = Logger.getLogger(Utils.class.getName());
	private double  valorLimiteCartaoTela = 0;

	public double swipeStickBar(VirtualElement elemento, VirtualElement elementoCaptura, String valor, boolean operacao) {

		try {
			DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "BR"));
			NumberFormat nf = new DecimalFormat ("###,###.00", dfs);
			valorLimiteCartaoTela = nf.parse(elementoCaptura.getText()).doubleValue();
			
			Log.info("Valor Inicial da Tela: " + valorLimiteCartaoTela);
			
			int width = elemento.getSize().getWidth();
			int xInicio = elemento.getLocation().getX() + 10;
			int yTotal = elemento.getLocation().getY();
			int xSwipe = 0;
			int cont = 0;
			
			double valorLimite = nf.parse(valor).doubleValue();
			Log.info("Valor Limite: " + valorLimite);
			Log.info("Selecionando valor pretendido");
			
			if (operacao) {
				Log.info("[ " + this.getClass().getName() + "] Início aumento de limite");
				while (true) {
					xSwipe = xInicio + 15;
					swipeScreen(xInicio, yTotal, xSwipe, yTotal, 200);
					xInicio = xSwipe;
					valorLimiteCartaoTela = nf.parse(elementoCaptura.getText()).doubleValue();
					if (valorLimiteCartaoTela >= valorLimite || cont > 100)
						break;

					cont++;
				}

			}else {
				Log.info("[ " + this.getClass().getName() + "] Início diminuição de limite");
				while (true) {
					xSwipe = xInicio - 15;
					swipeScreen(xInicio, yTotal, xSwipe, yTotal, 200);
					xInicio = xSwipe;
					valorLimiteCartaoTela = nf.parse(elementoCaptura.getText()).doubleValue();
					if (valorLimiteCartaoTela <= valorLimite || cont > 100)
						break;

					cont++;
				}
			}
			
			return valorLimiteCartaoTela;

		} catch (ElementFindException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Log.info("[ " + this.getClass().getName() + "] Final metodo de swipe stick bar.");
		return valorLimiteCartaoTela;
	}

	public static void clickAlertAccept(int time, String nameButton) throws GenericException {

		WebDriverWait wait = new WebDriverWait(getDriver_(), time);

		logger.debug(getMessage("Início - Clicar no Botão ' " + nameButton + " ' (Alert)"));

		try {
			getDriver_().findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();;
			
			logger.debug(getMessage("Fim - Clicar no Botão ' " + nameButton + " ' (Alert)"));

		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Botão ' " + nameButton + " ' (Alert) NÃO apresentado"));
		}
	}

	public static void clickAlertDismiss(int time, String nameButton) throws GenericException {

		WebDriverWait wait = new WebDriverWait(getDriver_(), time);

		logger.debug(getMessage("Início - Clicar no Botão ' " + nameButton + " ' (Alert)"));

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			getDriver_().switchTo().alert().dismiss();

			logger.debug(getMessage("Fim - Clicar no Botão ' " + nameButton + " ' (Alert)"));

		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Botão ' " + nameButton + " ' (Alert) NÃO apresentado"));
		}
	}

	public static void clickButton(VirtualElement element, int time, String nameButton) throws ElementFindException {

		logger.debug(getMessage("Início - Clicar no Botão ' " + nameButton + " ' "));

		if (elementIsPresent(element, time) && elementIsClickable(element, time)) {
			element.click();
			logger.debug(getMessage("Final - Clicar no Botão ' " + nameButton + " ' "));
		} else {
			logger.debug(getMessage("Atenção - Botão ' " + nameButton + " ' NÁO foi apresentado."));
		}
	}

	public static void printElements(By by) {

		List<WebElement> elementos;
		logger.debug(getMessage("Início - Capturando elementos"));
		try {
			elementos = getDriver_().findElements(by);

			if (elementos.size() > 0) {
				for (int i = 0; i < elementos.size(); i++) {
					logger.debug(getMessage("Elemento " + i + ": " + elementos.get(i).getAttribute("name")));
				}
			} else {
				logger.debug(getMessage("Não encontrou elementos"));
			}
		} catch (Exception e) {
			logger.debug(getMessage("Entrou na Exceção"));
		}

		logger.debug(getMessage("Fim - Capturando elementos"));
	}

	public static boolean elementIsPresent(VirtualElement element, int seconds) {

		WebDriverWait wait;

		try {
			wait = new WebDriverWait(getDriver_(), seconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(element.getBys()));

			return true;

		} catch (Exception e1) {
			return false;
		}
	}
	
	public static boolean elementIsClickable(VirtualElement element, int seconds) {

		WebDriverWait wait;

		try {
			wait = new WebDriverWait(getDriver_(), seconds);
			wait.until(ExpectedConditions.elementToBeClickable((element.getBys())));

			return true;

		} catch (Exception e1) {
			return false;
		}
	}
	
	public static void horizontalSwipeLeft(int qtdSwipe) {
		logger.debug(getMessage("Início - Horizontal Swipe Left"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int centerY = (getDriver_().manage().window().getSize().height)/2;
			int centerX = (getDriver_().manage().window().getSize().width)/2; 
	         	        	     	  	    
	        for(int i = 0; i < qtdSwipe; i++){
	        	touchAction.press(PointOption.point(centerX, centerY/2)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(centerX/2, centerY/2))
				.release().perform();
	        }	
        
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Horizontal Swipe Left NÃO Funcionou"));
		}
		
		logger.debug(getMessage("Fim - Horizontal Swipe Left"));
	}
	
	public static void clickAndDrag(VirtualElement element) {
		logger.debug(getMessage("Início - Click and Drag"));
		
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int finalX 	= element.getSize().getWidth();
			int x 		= element.getLocation().getX();
			int y 		= element.getLocation().getY();
			
			touchAction.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(finalX, y))
			.release().perform();
					
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Click and Drag NÃO Funcionou"));
		}
		logger.debug(getMessage("Fim - Click and Drag"));
	}
	
	public static void clickAndDragMidToEnd(VirtualElement element) {
		logger.debug(getMessage("Início - Click and Drag Mid To End"));
		
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int finalX 	= element.getSize().getWidth();
			int x 		= element.getLocation().getX() + finalX/2; 
			int y 		= element.getLocation().getY();
			
			touchAction.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(finalX, y))
			.release().perform();
					
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Click and Drag Mid To End NÃO Funcionou"));
		}
		logger.debug(getMessage("Fim - Click and Drag Mid To End"));
	}
	
	public static void clickAndDragMidToStart(VirtualElement element) {
		logger.debug(getMessage("Início - Click and Drag Mid To Start"));
		
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int finalX 	= element.getSize().getWidth();
			int xMid 	= element.getLocation().getX() + finalX/2; 
			int x		= element.getLocation().getX();
			int y 		= element.getLocation().getY();
			
			touchAction.press(PointOption.point(xMid, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(x, y))
			.release().perform();
					
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Click and Drag Mid To Start NÃO Funcionou"));
		}
		logger.debug(getMessage("Fim - Click and Drag Mid To Start"));
	}
	
	public static void horizontalSwipeRight(int qtdSwipe) {
		logger.debug(getMessage("Início - Horizontal Swipe Right"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int centerY = (getDriver_().manage().window().getSize().height)/2;
			int centerX = (getDriver_().manage().window().getSize().width)/2; 
	         	        	     	  	    
	        for(int i = 0; i < qtdSwipe; i++){
	        	touchAction.press(PointOption.point(centerX/2, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(centerX, centerY))
				.release().perform();
	        }	
        
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Horizontal Swipe Right NÃO Funcionou"));
		}
		
		logger.debug(getMessage("Fim - Horizontal Swipe Right"));
	}
	
	public static void horizontalSwipeRightElementToElement(VirtualElement elemento1, VirtualElement elemento2) {
		logger.debug(getMessage("Início - Horizontal Swipe Right Element To Element"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
		
			int startElementX = elemento1.getSize().getWidth();
			int finalElementX = elemento2.getLocation().getX() ;
			int finalElementY = elemento2.getLocation().getY() - 30;

			if(elementIsPresent(elemento1, 1) && elementIsPresent(elemento2,1)) {
				touchAction.press(PointOption.point(finalElementX, finalElementY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(startElementX, finalElementY))
				.release().perform();
			}
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Horizontal Swipe Right Element To Element NÃO Funcionou"));
		}
		logger.debug(getMessage("Fim - Horizontal Swipe Right Element To Element"));
	}
	
	public static void horizontalSwipeLeftElementToElement(VirtualElement elemento1, VirtualElement elemento2) {
		logger.debug(getMessage("Início - Horizontal Swipe Left Element To Element"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
		
			int startElementX = elemento1.getSize().getWidth();
			int startElementY = elemento1.getLocation().getY();
			int finalElementX = elemento2.getLocation().getX();
			int finalElementY = elemento2.getLocation().getY();
			
			if(elementIsPresent(elemento1, 1) && elementIsPresent(elemento2,1)) {
				touchAction.press(PointOption.point(startElementX, startElementY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(finalElementX, finalElementY))
				.release().perform();
			}
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Horizontal Swipe Left Element To Element NÃO Funcionou"));
		}
		logger.debug(getMessage("Fim - Horizontal Swipe Left Element To Element"));
	}
	
	public static void verticalSwipePresenceElement(VirtualElement elemento, int qtdSwipe ) {
		logger.debug(getMessage("Início - Vertical Swipe Presence Element"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
			int centerY = (getDriver_().manage().window().getSize().height)/2;
			int centerX = (getDriver_().manage().window().getSize().width)/2;
	         	        	     	  	    
	        for(int i = 0; i < qtdSwipe; i++){
	        	
	         	try {		         		         	
		        	if (elementIsPresent(elemento, 1))
		        		break;		            
		        	else
		        		touchAction.press(PointOption.point(5, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(5, centerY/2))
		        		.release().perform();
	         	} catch (Exception e) { }
	        }	
        
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Vertical Swipe Presence Element NÃO funcionou"));
		}
		
		logger.debug(getMessage("Fim - Vertical Swipe Presence Element"));
	}
	
	public static void verticalSwipeClickableElement(VirtualElement elemento, int qtdSwipe ) {
		logger.debug(getMessage("Início - Vertical Swipe Clickable Element"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((AndroidDriver) getDriver_());
			int centerY = (getDriver_().manage().window().getSize().height)/2+30;	     
	         	 
	        for(int i = 0; i < qtdSwipe; i++){	        	
	        	try {				
		        	if (elementIsClickable(elemento, 1))		        		
		        		break;		            		        	
		        	else
		        		touchAction.press(PointOption.point(5, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(5, centerY/2))
		        		.release().perform();
		        } catch (Exception e) { }
		   }
        
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Vertical Swipe Clickable Element NÃO funcionou"));
		}
		
		logger.debug(getMessage("Fim - Vertical Swipe Clickable Element"));
	}
	
	public static void verticalSwipe(int qtdSwipe) {
		logger.debug(getMessage("Início - Vertical Swipe"));
		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((AndroidDriver) getDriver_());
			int centerY = (getDriver_().manage().window().getSize().height)/2+30;	     
	         	 
	        for(int i = 0; i < qtdSwipe; i++){	        	
	        		touchAction.press(PointOption.point(5, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(5, centerY/2))
	        		.release().perform();
		   }
        
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Vertical Swipe NÃO funcionou"));
		}
		
		logger.debug(getMessage("Fim - Vertical Swipe Clickable"));
	}
	
	
	public static void doScroll(String direction) {
		
		logger.debug(getMessage("Início - Scroll ' " + direction + " '"));
		
		try {
			
			JavascriptExecutor js = (JavascriptExecutor) getDriver_();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", direction);
			js.executeScript("mobile: scroll", scrollObject);
			
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Scroll ' " + direction + " ' NÃO funcionou"));
		}
		
		logger.debug(getMessage("Fim - Scroll ' " + direction + " '"));
	}	

	public static void tapElementScreen(MobileElement elemento, int x, int y, double duration) {
		
		logger.debug(getMessage("Início - Tab Element Screen: X = ' " + x + " ' Y = ' " + y +" '"));
		
		try {
		
			JavascriptExecutor js = (JavascriptExecutor) getDriver_();			
	
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("duration", duration);
			params.put("x", x);
			params.put("y", y);
			params.put("element", ((RemoteWebElement) elemento).getId());
			js.executeScript("mobile: tap", params);
		
		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Tab Element Screen: X = ' " + x + " ' Y = ' " + y +" ' NÃO funcionou"));
		}
		
		logger.debug(getMessage("Fim - Tab Element Screen: X = ' " + x + " ' Y = ' " + y +" '"));
	}	

	public static void dragElementScreen(MobileElement elemento, int startX, int startY, int endX, int endY, double duration) {
		
		logger.debug(getMessage("Início - Drag Element Screen: startX = ' " + startX + " ' startY = ' " + startY +" ', endX ' " + endX + " ' endY ' " + endY + " '." ));
		
		try {
		 
			JavascriptExecutor js = (JavascriptExecutor) getDriver_();				
			
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("duration", duration);
			params.put("fromX", startX);
			params.put("fromY", startY);
			params.put("toX", endX);
			params.put("toY", endY);
			params.put("element", ((RemoteWebElement) elemento).getId());
			js.executeScript("mobile: dragFromToForDuration", params);
			
		} catch (Exception e) {
			logger.debug(getMessage("Início - Drag Element Screen: startX = ' " + startX + " ' startY = ' " + startY +" ', endX ' " + endX + " ' endY ' " + endY + " ' NÃO funcionou." ));
		}	
		
		logger.debug(getMessage("Fim - Drag Element Screen: startX = ' " + startX + " ' startY = ' " + startY +" ', endX ' " + endX + " ' endY ' " + endY + " '." ));
	}
	
	
	public static void touchPosition(int x, int y) throws GenericException {
		TouchAction touch = new TouchAction<AndroidTouchAction>((AndroidDriver) getDriver_());
		
		touch.tap(PointOption.point(x, y)).perform();
	}
	
	public static void clickAndDragElement(int x, int y, int finalX, int finalY) {
		logger.debug(getMessage("Início - Click and Drag Mid To Start"));

		try {
			TouchAction touchAction = new TouchAction<AndroidTouchAction>((PerformsTouchActions) getDriver_());
	
			touchAction.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).moveTo(PointOption.point(finalX, finalY))
			.release().perform();

		} catch (Exception e) {
			logger.debug(getMessage("Atenção - Click and Drag Mid To Start NÃO Funcionou"));
		}
			logger.debug(getMessage("Fim - Click and Drag"));
	}
	
	
//	public static boolean webElementIsPresent(By element, int seconds) {
//
//		WebDriverWait wait;
//		
//		try {
//			wait = new WebDriverWait(getDriver_(), seconds);
//			wait.until(ExpectedConditions.presenceOfElementLocated(element));
//
//			return true;
//
//		} catch (Exception e1) {
//			return false;
//		}
//	}
//	
//	public static void scrollToWebElement(By elemento) throws GenericException {
//		JavascriptExecutor jse = (JavascriptExecutor)getDriver_();
//		
//		while (!webElementIsPresent(elemento, 2)) {
//			jse.executeScript("window.scrollBy(0,250)", "");
//		}
//	}
	
	
}