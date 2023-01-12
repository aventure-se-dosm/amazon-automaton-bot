package br.dev.marcelodeoliveira.amazonautomatonbot.core;

import br.dev.marcelodeoliveira.amazonautomatonbot.utils.DomainUtils.Countries;
public class CoreProperties {

	public enum Browsers {

		CHROME, EDGE, IEXPLORER, FIREFOX, SAFARI,
	}

	public static enum ExecutionModes {

		SINGLE_WINDOW, ONE_WINDOW_PER_TEST, HEADLESS
	}
	
	public static Countries SELECTED_COUNTRY = Countries.Brazil;

	
	public static Browsers defaultBrowser = Browsers.CHROME;

	public static ExecutionModes defaultExecutionMode = ExecutionModes.ONE_WINDOW_PER_TEST;

	public static final String BASE_PATH = "https://www.amazon.com.br";
	
	public static Countries navigationCountry = Countries.Brazil;

}
