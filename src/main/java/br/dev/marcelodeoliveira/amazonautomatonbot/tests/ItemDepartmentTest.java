package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Test;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.ItemDepartmentPage;

public class ItemDepartmentTest extends BaseTest {

	private ItemDepartmentPage depMenuPege = new ItemDepartmentPage();

	String category = "Computadores e Informática";

	@Test
	public void findItemByCategorty() {
		depMenuPege.findCategory(category);

	}
}
