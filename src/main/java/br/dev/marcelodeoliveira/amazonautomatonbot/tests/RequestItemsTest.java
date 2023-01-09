package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Assert;
import org.junit.Test;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.ZipCodePage;

public class RequestItemsTest extends BaseTest {

	// de 0003 a 0006 não há conflitam!

	private RequestItemPage requestItemPage = new RequestItemPage();
	private ZipCodePage zipCodePage = new ZipCodePage();

	@Test
	public void RequestValidItemOnSearchBar() {

		String searchQueryString = "Bicicleta Aro 29";

		var relevantItems = requestItemPage.searchElement(searchQueryString);
		requestItemPage.redirectWait();
		requestItemPage.resultGatheringWait();
		System.out.println(relevantItems);
		Assert.assertTrue(relevantItems.size() > 0);

		/**
		 * ESTRATÉGIA
		 * 
		 * [1]. Escrever na barra de busca o nome do ítem; (pego da planilha)
		 * 
		 * [2]. Clicar no botão 'Pesquisar';
		 * 
		 * [3]. Buscar pelos ítens de título mais relevantes e mapeá-los
		 * 
		 * [a]. consulte o ítem ***ESTRATÉGIAS DE COMPARAÇÃO E VALIDAÇÃO***
		 * 
		 * 
		 * [4]. destacar todos os mais relevantes com CSS;
		 * 
		 * [5]. Caso NÃO haja resultados relevantes tentar nas próximas páginas!
		 * 
		 * [6]. Fazer a captura da primeira página com resultados.
		 * 
		 */
		Assert.assertEquals(("Amazon.com.br : " + searchQueryString), requestItemPage.getPageTitle());

	}
	
	@Test
	public void RequestInvalidItemOnSearchBar() {

		// MELHORAS: PÁGINA DE ÍTEM NÃO EXISTENTE É DIFERENTE DE QUANDO
		// ENCONTRA: QUE TAL?
		String searchQueryString = "ITEM_INEXISTENTE_012345";

		var relevantItems = requestItemPage.searchElement(searchQueryString);
		requestItemPage.redirectWait();
		requestItemPage.resultGatheringWait();
		System.out.println(relevantItems);
		Assert.assertTrue(relevantItems.size() == 0);

		/**
		 * ESTRATÉGIA
		 * 
		 * [1]. Escrever na barra de busca o nome do ítem; (pego da planilha)
		 * 
		 * [2]. Clicar no botão 'Pesquisar';
		 * 
		 * [3]. Buscar pelos ítens de título mais relevantes e mapeá-los
		 * 
		 * [a]. consulte o ítem ***ESTRATÉGIAS DE COMPARAÇÃO E VALIDAÇÃO***
		 * 
		 * [4]. destacar todos os mais relevantes com CSS;
		 * 
		 * [5]. Caso NÃO haja resultados relevantes tentar nas próximas páginas!
		 * 
		 * [6]. Fazer a captura da primeira página com resultados.
		 *
		 */

		Assert.assertEquals(("Amazon.com.br : " + searchQueryString), requestItemPage.getPageTitle());
	}

	@Test
	public void RequestItemAndGetShippingCostByZipCode() {
		String zipCode = "06010-067";
		
		/**
		 * 5. Calcular frete e prazo de um item
		 * 
		 *  ID: 0005  Descrição: Realizar a consulta pelo item “frigideira” no campo
		 * de busca, clicar no primeiro item disponível, digitar o CEP “06010-067” e
		 * validar o retorno da(s) informação(ões) de frete/prazo.
		 * 
		 */

		String searchQueryString = "frigideira";

		var relevantItems = requestItemPage.searchElement(searchQueryString);
		
		requestItemPage.redirectWait();
		requestItemPage.resultGatheringWait();
		System.out.println(relevantItems);
		Assert.assertTrue(relevantItems.size() > 0);
		
		// clicar no primeiro elemento
		requestItemPage.clickOnElement(relevantItems.get(0));
		zipCodePage.addZipCode(zipCode);
		
		// System.out.println("\n\n**********\n" +
		// zipCodePage.getDeliveryInfos()+"\n\n**********\n\n");
		Assert.assertFalse(zipCodePage.getDeliveryInfos().isEmpty());

	}

	@Test
	public void RequestItemWithInvalidZipCode() {

		String zipCode = "00000-000";
		// Importante passar pro pacote utils com region/lang

		// pra refatorar com locale

		// BR_ZipCodeErrorMessage deveria ser resultado de um

		// método getZipCodeErrorMessage (
		// String IsoCOuntry Name,
		// )

		String BR_ZipCodeErrorMessage = "Insira um CEP válido";
		String ZipCodeErrorMessage = BR_ZipCodeErrorMessage;

		/**
		 * 5. Calcular frete e prazo de um item
		 * 
		 *  ID: 0005
		 *  Descrição: Realizar a consulta pelo item “frigideira” no campo
		 * de busca, clicar no primeiro item disponível, digitar o CEP “06010-067” e
		 * validar o retorno da(s) informação(ões) de frete/prazo.
		 * 
		 */

		String searchQueryString = "frigideira";
		String BR_ZipCodeNotFound = "Número de CPF inválido";
		var relevantItems = requestItemPage.searchElement(searchQueryString);

		requestItemPage.redirectWait();
		requestItemPage.resultGatheringWait();

		System.out.println(relevantItems);
		Assert.assertTrue(relevantItems.size() > 0);

		// clicar no primeiro elemento
		requestItemPage.clickOnElement(relevantItems.get(0));
		zipCodePage.addZipCode(zipCode);

		// fazer a validação
		Assert.assertEquals(zipCodePage.isZipCodeInvalid(), ZipCodeErrorMessage);

		// terminar o assert de cpo com msg inválida"
	}

}
