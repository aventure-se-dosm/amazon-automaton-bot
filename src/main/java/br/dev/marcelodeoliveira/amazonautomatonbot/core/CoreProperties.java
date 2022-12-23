package br.dev.marcelodeoliveira.amazonautomatonbot.core;

public class CoreProperties {

	public enum Browsers {
		
		CHROME,
		EDGE,
		IEXPLORER,
		FIREFOX,
		SAFARI,
	}
	
	
	public static enum ExecutionModes {
		
		SINGLE_WINDOW,
		ONE_WINDOW_PER_TEST,
		HEADLESS
	}

	public static Browsers defaultBrowser = Browsers.CHROME;
	
	public static ExecutionModes defaultExecutionMode = ExecutionModes.SINGLE_WINDOW;
	
	public static final String basePath = "https://www.amazon.com.br/";
	
	
	
}
