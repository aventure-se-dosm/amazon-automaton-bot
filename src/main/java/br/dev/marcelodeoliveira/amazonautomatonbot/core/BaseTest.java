package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class BaseTest {
	
	protected BaseTest() {}
	@Before
	public void inicializa() {

		getDriver().get(CoreProperties.BASE_PATH);
		getDriver().manage().window().fullscreen();
		
		

	
	}
	@After
	public void finaliza() {
		if (getDriver() != null) {
			switch (CoreProperties.defaultExecutionMode) {

			case ONE_WINDOW_PER_TEST: {
				getDriver().quit();
				break;
			}

			case SINGLE_WINDOW: {
				killDriver();
				break;
			}

			case HEADLESS:
			default:
				getDriver().quit();
				break;
				

			}
		}
		
	}
}
