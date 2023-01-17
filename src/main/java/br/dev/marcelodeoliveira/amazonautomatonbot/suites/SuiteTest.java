package br.dev.marcelodeoliveira.amazonautomatonbot.suites;

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


}
