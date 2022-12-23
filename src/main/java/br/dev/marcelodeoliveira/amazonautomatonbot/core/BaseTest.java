package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;

public class BaseTest {
	@Before
	public void inicializa() {

		getDriver().get(CoreProperties.basePath.toString());
		getDriver().manage().window().setSize(new Dimension(1366, 796));

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
				;

			}
		}
	}
}
