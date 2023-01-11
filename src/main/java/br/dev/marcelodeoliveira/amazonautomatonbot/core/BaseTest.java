package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.closeDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

	protected BaseTest() {
	}

	@Before
	public void setupTest() {

		getDriver().get(CoreProperties.BASE_PATH);
		getDriver().manage().window().fullscreen();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}
	
	@AfterEach
	public void finishIndividualTest() {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		getDriver().close();
		
	}
	


	
//	@After
//	public void finaliza() {
//		if (getDriver() != null) {
//			switch (CoreProperties.defaultExecutionMode) {
//
//			case ONE_WINDOW_PER_TEST: {
//				getDriver().quit();
//				break;
//			}
//
//			case SINGLE_WINDOW: {
//				killDriver();
//				break;
//			}
//
//			case HEADLESS:
//			default:
//				getDriver().quit();
//				break;
//
//			}
//		}
//
//	}
}
