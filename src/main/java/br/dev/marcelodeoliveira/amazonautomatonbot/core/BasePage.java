package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;

import org.junit.Before;
import org.openqa.selenium.Dimension;

public abstract class BasePage {

	
	protected String path;
	
	
	@Before
	public void inicializa() {

		getDriver().get(CoreProperties.basePath.toString());
		getDriver().manage().window().setSize(new Dimension(1366, 796));

	} 

}
