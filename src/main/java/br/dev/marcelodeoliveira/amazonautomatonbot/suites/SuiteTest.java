package br.dev.marcelodeoliveira.amazonautomatonbot.suites;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.dev.marcelodeoliveira.amazonautomatonbot.tests.LoginTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.tests.LogoutTest;;

//@RunWith(Suite.class)
//@SuiteClasses(
//		{ 
//			LoginTest.class,
//			LogoutTest.class,
//		}
//)
//
//public class SuiteTest {
//
//	@AfterClass
//	public void finaliza() {
//		killDriver();
//	}
//
//}
