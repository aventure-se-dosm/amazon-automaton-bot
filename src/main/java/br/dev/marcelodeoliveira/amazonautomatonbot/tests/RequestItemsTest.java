package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Test;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;

public class RequestItemsTest extends BaseTest {

	// de 0003 a 0006 não há conflitam!

	private RequestItemPage page = new RequestItemPage();

	@Test
	public void RequestValidItemOnSearchBar() {

		/**
		 * ESTRATÉGIA
		 * 
		 * [1]. Escrever na barra de busca o nome do ítem;
		 * 
		 * [2]. Clicar no botão 'Pesquisar';
		 * 
		 * [3]. Buscar pelos ítens de título mais relevantes e mapeá-los;
		 * 
		 * 
		 * [4]. destacar todos os mais relevantes com CSS;
		 * 
		 * [5]. Caso NÃO haja resultados relevan- tes tentar nas próximas páginas!
		 * 
		 * [6]. Fazer a captura da primeira página com resultados.
		 * 
		 */
	}

	public void RequestInvalidItemOnSearchBar() {

		/**
		 * ESTRATÉGIA
		 * 
		 * [1]. Escrever na barra de busca o nome do ítem;
		 * 
		 * [2]. Clicar no botão 'Pesquisar';
		 * 
		 * 
		 */
	}
	
	public void RequestItemAndGetShippingCostByZipCode() {

		/**
		 * ESTRATÉGIA
		 * 
		 * [1]. Escrever na barra de busca o nome do ítem;
		 * 
		 * [2]. Clicar no botão 'Pesquisar';
		 * 
		 * 
		 */
	}
	
	public void RequestItemWithInvalidZipCode() {

		/**
		 * ESTRATÉGIA
		 * 
		 * 
		 * 
		 */
	}

}
