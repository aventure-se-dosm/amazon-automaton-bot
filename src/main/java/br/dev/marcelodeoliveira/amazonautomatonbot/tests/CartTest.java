package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import static br.dev.marcelodeoliveira.amazonautomatonbot.core.DriverFactory.closeDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.CoreProperties;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.CartPage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;
public class CartTest extends BaseTest {

	private CartPage cartPage = new CartPage();

	private RequestItemPage requestPage = new RequestItemPage();

	private String addItemToCart(String item) {

		// String item = "Iphone 14";

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
		//a url tá certa
		var afterclickProductPage = cartPage.getUrl();
		System.out.println(afterclickProductPage);

		cartPage.addToCart();

	
		cartPage.checkCart();

		return afterclickProductPage;

		

	}

	@Test
	public void addToCartTest() {

		String item = "Iphone 14";
		var prodUrl = addItemToCart(item);
		
		//cartPage.checkCart();
		
		//getItemUrlCode
		var onCartItemUrl = cartPage.getItemUrlOnTheCart();
		onCartItemUrl = onCartItemUrl.replace(CoreProperties.BASE_PATH+"/gp/product/", "");
		onCartItemUrl = onCartItemUrl.substring(0, onCartItemUrl.indexOf('/'));
		// fazer a asserção!
		Assert.assertTrue(onCartItemUrl, prodUrl.contains(onCartItemUrl));
	}

	@Test
	public void addTwoItemsToCartTest() {

		final int quantitdade2itens = 2;

		String item1 = "fogão";
		String item2 = "geladeira";
		addItemToCart(item1);
		cartPage.redirectWait();
		cartPage.scriptWait();
		addItemToCart(item2);
		//cartPage.checkCart();

		Assert.assertEquals(quantitdade2itens, cartPage.getTotalItems());

	}

	@Test
	public void increaseItemsOnCartTest() {

		// read from xsl[0008 - ... ] test
		var item = "prego";
		String quantity = "4";

		// converter
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

		var itemPrice = cartPage.addToCartAndReturnNumericPrice();

		// var precoInicial = cartPage.getItemPrice();

		if (cartPage.isExtendedWarrantyOffered()) {

			cartPage.declineExtendedWarrantyOffer();
		}

		//cartPage.checkCart();

		Assert.assertTrue(cartPage.changeQuantityTo(quantity));

		//
		Assert.assertEquals(itemPrice * integerQuantity, cartPage.getItemNumericalItemPrice(integerQuantity), 2);

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
	
	
	@Ignore
	@Test
	public void addItemThenRemoveItAndVerifyEmptyCartTest(){
		String item = "Iphone 14";
		var itemTitle = addItemToCart(item);
		//cartPage.checkCart();
		//cartPage.removeFromCart();
		
		
	}
	
	


}
