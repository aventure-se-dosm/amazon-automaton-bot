package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.CartPage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;

public class CartTest extends BaseTest {

	private CartPage cartPage = new CartPage();


	private RequestItemPage requestPage = new RequestItemPage();

	@Test
	public void addToCartTest() {

		String item = "Iphone 14";

		/**
		 * IMPORTANTE:
		 * 
		 * [1]. Pode ser que ao clicar em "Adicionar ao Carrinho" apareça um frame de
		 * garantia extendida.
		 * 
		 * [a] caso apareça, a gente nega, fechando o frame, continuando o fluxo
		 * normalmente.
		 */

		var firstItemResult = requestPage.searchElement(item).get(0);

		firstItemResult.click();
		
		cartPage.addToCart();
		
		if (cartPage.isExtendedWarrantyOffered()) {
			cartPage.declineExtendedWarrantyOffer();
		}
		
		cartPage.finishOrder();

		// adicionar carrinho

	}

}
