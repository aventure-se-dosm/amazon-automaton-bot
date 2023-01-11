package br.dev.marcelodeoliveira.amazonautomatonbot.utils;

public class ConversionUtils {

	public float strToFLoatTest (String integerPart, String fractionaryPart)
	{
		//nao funciona para esta conversão
		//var decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
		final char decimalSeparator = '.';
		var price = integerPart.replaceAll("[,.]", "")
			+ decimalSeparator
			+ fractionaryPart;
		
		System.out.println(price);
		return Float.valueOf(price);
		
	}
}
