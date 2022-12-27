package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

public class TestProperties {

	public static String getUserEmail() {
		return USER_EMAIL;
	}

	public static String getUserPassword() {
		return USER_PASSWORD;
	}

	public static String getUserFirstName() {
		return USER_FIRST_NAME;
	}

	public static String getUserSurname() {
		return USER_SURNAME;
	}

	public String[] getLoginTestExpectedValue() {
		return LOGIN_TEST_EXPECTED_VALUES;
	}

	private final static String USER_EMAIL = "automation.dvmrkolv@gmail.com";
	private final static String USER_PASSWORD = "Zrb!S7YM23bmrMp";
	private final static String USER_FIRST_NAME = "Automation";
	private final static String USER_SURNAME = "Dvmrkol";

	private final String[] LOGIN_TEST_EXPECTED_VALUES = 
		{
				"Olá, ".concat(USER_FIRST_NAME)
		};
}
