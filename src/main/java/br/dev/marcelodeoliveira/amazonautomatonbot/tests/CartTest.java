package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.CartPage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;

public class CartTest extends BaseTest {

	private CartPage cartPage = new CartPage();

	private RequestItemPage requestPage = new RequestItemPage();

	private void addToCart(String item) {

		//String item = "Iphone 14";

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
		
		cartPage.redirectWait();
		cartPage.scriptWait();

		cartPage.addToCart();

		if (cartPage.isExtendedWarrantyOffered()) {

			cartPage.declineExtendedWarrantyOffer();
		}

	}
	

	@Test
	public void addToCartTest() {

		String item = "Iphone 14";
		addToCart(item);
		
		//fazer a asserção!

	}
	
	@Test
	public void addToCartTestTwoTImes() {

		final int quantitdade2itens = 2;
		
		String item1 = "fogão";
		addToCart(item1);
		addToCart(item1);
		cartPage.checkCart();
	
		Assert.assertEquals(quantitdade2itens, cartPage.getTotalItems());

	}

	public void increaseItemsOnCartTest() {

		
		//read from xsl[0008 - ... ] test
		var item = "Iphone 14";
		String quantity = "4";
		
		
		//converter
		var integerQuantity = Integer.parseInt(quantity);

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

	}

	@Test
	public void addToCartAndLoginRedirectionTest() {

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

		cartPage.redirectWait();
		// bad smell! put it in a dictionary on later refactoring.

		Assert.assertTrue(cartPage.getUrl(), cartPage.getUrl().startsWith("https://www.amazon.com.br/ap/signin?"));

	}

}
