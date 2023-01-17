package br.dev.marcelodeoliveira.amazonautomatonbot.core.dtos;



/**
 * 
 * @author MarcelodeOliveiraSan
 *
 *	Objetivos:	ser o retorno de AddItem, contendo
 *				informações recorrentemente necessárias sobre 
 *				ítem que representa.
 *
 */

public class DTOItem {
	
	
	public String getUrlProductId() {
		return urlProductId;
	}
	public String getTitle() {
		return title;
	}
	public float getNumericPrice() {
		return numericPrice;
	}
	
	public DTOItem(String urlProductId, String title, float numericPrice) {
		super();
		this.urlProductId = urlProductId;
		this.title = title;
		this.numericPrice = numericPrice;
	}
	private String urlProductId, title;
	private float numericPrice;
	

}
