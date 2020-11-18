package com.johnmcg.StockMarketRestChallenge.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSymbol {
	
	public static boolean readValidSymbol(String symbol) {
		String regex = "([A-Z]{1,7})\\w+";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(symbol);
				
		return matcher.find();
	}
}
