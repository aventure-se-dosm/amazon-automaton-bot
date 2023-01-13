package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.*;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.getDriver;
import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;

public class BaseTest {

	protected BaseTest() {
	}

	@Before
	public void inicializa() {

		getDriver().get(CoreProperties.BASE_PATH);
		//getDriver().manage().window().setSize(new Dimension(1280, 2720));
		//getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(750));

	}

	@After
	public void finaliza() {
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

