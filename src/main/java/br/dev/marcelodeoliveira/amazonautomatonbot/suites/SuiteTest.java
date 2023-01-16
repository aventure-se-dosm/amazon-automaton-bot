package br.dev.marcelodeoliveira.amazonautomatonbot.suites;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.dev.marcelodeoliveira.amazonautomatonbot.tests.CartTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.tests.LoginTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.tests.LogoutTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.tests.RequestItemsTest;;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			LoginTest.class,
			LogoutTest.class,
			RequestItemsTest.class,
			CartTest.class,
			

})

public class SuiteTest {

//	@AfterClass
//	public static void finaliza() {
//		killDriver();
//	}

}
