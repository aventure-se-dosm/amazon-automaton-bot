package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.After;
import org.junit.Test;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.ItemDepartmentPage;

public class ItemDepartmentTest extends BaseTest {

	ItemDepartmentPage depMenuPage = new ItemDepartmentPage();

	String[] category = { "Computadores e Inform", "Notebook" };

	@Test
	public void findItemByCategortyTest() {
		
		String[] category = { "Computadores e Inform", "Notebook" };


		/**
		 *  ID: 0013 *
		 * 
		 *  Descrição: Clicar no menu principal, clicar na opção categoria/departamento
		 * correspondente à “Informática”, clicar no submenu referente à “Notebook(s)” e
		 * validar a exibição de ao menos um item no catálogo de resultados de itens.
		 */
		depMenuPage.findCategory(category);

		depMenuPage.highlightGatheredItemDivElements();
		// validar resultados

	}

	@Test
	public void filterItemByCategoryTest() {
		/*
		 *  ID: 0014  *
		 * 
		 *  Descrição: Realizar o descritivo do cenário de ID 0013, selecionar nas
		 * opções de filtro a marca “Lenovo” e ao final validar se o primeiro item
		 * disponível no catálogo de resultados de itens contém em seu nome/descritivo a
		 * palavra (marca filtrada) “Lenovo”.
		 */
		
		String[] category = { "Computadores e Inform", "Notebook" };
		depMenuPage.findCategory(category);
		
		depMenuPage.highlightGatheredItemDivElements();
	}

	@Test
	public void sortItemByCategoryBy() {
		
		String[] category = { "Computadores e Inform", "Notebook" };
		depMenuPage.findCategory(category);

		depMenuPage.highlightGatheredItemDivElements();
		/*
		 * ID: 0015  Descrição: Realizar o descritivo do cenário de ID 0014, selecionar
		 * a opção de ordenação por maiores preços e validar no catálogo de resultados
		 * de itens se o valor do primeiro item é maior do que o do segundo.
		 */
	}

}
