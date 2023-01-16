package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.closeDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.timeoutForWebDriverInitOrKill;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	protected BaseTest() {
	}
	
	@Rule
	public TestName tname = new TestName();

	@Before
	public void inicializa() {
		
		

		getDriver().get(CoreProperties.BASE_PATH);
		
		
		try {
			Thread.sleep(1000);
			do {
				getDriver().get(CoreProperties.BASE_PATH);
				
				
			}while(getDriver().findElements(By.id("nav-tools")).size() < 1);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//getDriver().manage().window().setSize(new Dimension(1280, 2720));
		//getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(750));

	}
	
	
	public void takeScreenshot() {// Funcionalidade de Screenshot
	
		
		TakesScreenshot shoter = (TakesScreenshot) getDriver();
	var temp = shoter.getScreenshotAs(OutputType.FILE);

	try {

		FileUtils.copyFile(temp, new File(("./target/screenshots/" + tname.getMethodName() + ".jpg").replaceAll("/",
				File.separator + File.separator)));

	} 
		catch (IOException e) {
		System.out.println("Erro: não foi possível salvar o arquivo!");;
	}
	
	catch (Exception e) {
		System.out.println("Erro:\n" + e.getLocalizedMessage());;
	}

}

	@After
	public void finaliza() {
		
		timeoutForWebDriverInitOrKill(800);
		takeScreenshot();
		
		
		
		if (getDriver() != null) {
			switch (CoreProperties.defaultExecutionMode) {

			
			case ONE_WINDOW_PER_TEST: {
				//closeDriver();
				killDriver();
				
				break;
			}

			case SINGLE_WINDOW: {
				closeDriver();
				//killDriver();
				break;
			}

			case HEADLESS:
				closeDriver();
				break;
			
			default:
			;
			}
			
			
		}
	}
}

