package br.dev.marcelodeoliveira.amazonautomatonbot.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import br.dev.marcelodeoliveira.amazonautomatonbot.core.BaseTest;
import br.dev.marcelodeoliveira.amazonautomatonbot.core.CoreProperties;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.CartPage;
import br.dev.marcelodeoliveira.amazonautomatonbot.pages.RequestItemPage;

public class CartTest extends BaseTest {

	private CartPage cartPage = new CartPage();

	private RequestItemPage requestPage = new RequestItemPage();
	// private RequestItemPage requestPage = new RequestItemPage();

	private String getFirstFoundItem(String item) {

		System.out.println("ãõáéíóúàèìòù");
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

//		var firstItemResult = requestPage.searchAndGatherItems(item).get(0);
//		
//		requestPage.startSearch("fogão");
//		var fogao = getDriver().findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div//h2/a[1]"));
//		System.out.println(fogao.getText());

		// fogao.click();

		var firstItemResult = requestPage.searchAndGatherItems(item).get(0);
//
		firstItemResult.click();
		cartPage.redirectWait();
		cartPage.scriptWait();

		// a url ANTES da adição ao carrinho!
		var beforeCartAddingkProductPage = cartPage.getUrl();

		// cartPage.addToCart();

		// cartPage.checkCart();

		return beforeCartAddingkProductPage;

	}

	@Test
	public void addToCartTest() {

		String item = "Iphone 14";
		var prodUrl = getFirstFoundItem(item);
		cartPage.addToCart();

		// cartPage.checkCart();

		// getItemUrlCode
		var onCartItemUrl = cartPage.getItemUrlOnTheCart();
		onCartItemUrl = onCartItemUrl.replace(CoreProperties.BASE_PATH + "/gp/product/", "");
		onCartItemUrl = onCartItemUrl.substring(0, onCartItemUrl.indexOf('/'));
		// fazer a asserção!
		Assert.assertTrue(onCartItemUrl, prodUrl.contains(onCartItemUrl));
	}

	@Test
	public void addTwoItemsToCartTest() {

		final int quantitdade2itens = 2;

		String item2 = "geladeira";
		// String item1 = "fogão";

		String item1 = "foguete";

		getFirstFoundItem(item1);
		cartPage.addToCart();
//		cartPage.redirectWait();
//		cartPage.scriptWait();
		getFirstFoundItem(item2);
		cartPage.addToCart();
		// cartPage.checkCart();

		Assert.assertEquals(quantitdade2itens, cartPage.getTotalItems());

	}

	@Test
	public void increaseItemsOnCartTest() {

		// read from xsl[0008 - ... ] test
		var item = "fone";
		String quantity = "2";

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

		var firstItemResult = requestPage.searchAndGatherItems(item).get(0);
		cartPage.resultGatheringWait();
		firstItemResult.click();

		// var itemPrice = cartPage.addToCartAndReturnNumericPrice();

		var precoInicial = cartPage.getNumericPrice();
		cartPage.addToCart();

		if (cartPage.isExtendedWarrantyOffered()) {

			cartPage.declineExtendedWarrantyOffer();
		}

		// cartPage.checkCart();

		// Assert.assertTrue(cartPage.changeQuantityTo(quantity));

		//
		Assert.assertEquals(precoInicial * integerQuantity, cartPage.getItemNumericalItemPrice(integerQuantity), 2);

	}

	// @Ignore
	@Test
	public void addToCartAndLoginRedirectionTest() {

		String item = "Iphone 14";
		getFirstFoundItem(item);
		cartPage.addToCart();

		/**
		 * IMPORTANTE:
		 * 
		 * [1]. Pode ser que ao clicar em "Adicionar ao Carrinho" apareça um frame de
		 * garantia extendida.
		 * 
		 * [a] caso apareça, a gente nega, fechando o frame, continuando o fluxo
		 * normalmente.
		 */

////		var firstItemResult = requestPage.searchAndGatherItems(item).get(0);
////
////		firstItemResult.click();
////
////		cartPage.addToCart();
////
////		if (cartPage.isExtendedWarrantyOffered()) {
////
////			cartPage.declineExtendedWarrantyOffer();
////		}
////
////		cartPage.finishOrder();
//		
//	//	addItemThenRemoveItAndVerifyEmptyCartTest();

		cartPage.redirectWait();
		cartPage.finishOrder();

		Assert.assertTrue(cartPage.getUrl().startsWith("https://www.amazon.com.br/ap/signin?"));

	}

	@Test
	public void addItemThenRemoveItAndVerifyEmptyCartTest() {
		String item = "Iphone 14";

		var itemTitle = getFirstFoundItem(item);
		var itemId = cartPage.addToCart();

		cartPage.removeFromCart(itemId[5]);
		Assert.assertTrue(cartPage.isElementDisplayed(By.xpath("//*[@class='sc-list-item-removed-msg']")));

	}

	@Test
	public void addItemsRemoveOneAndVerifyItsNotPresentTest() {
		String item1 = "fogão";
		getFirstFoundItem(item1);
		cartPage.addToCart();

		String item2 = "Frigideira";
		getFirstFoundItem(item2);
		cartPage.addToCart();

		cartPage.removeFromCart("");
		System.out.println("atestar remoção");

		Assert.assertTrue(cartPage.isElementDisplayed(By.xpath("//*[@class='sc-list-item-removed-msg']")));

	}

}
