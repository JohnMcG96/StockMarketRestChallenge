package com.johnmcg.StockMarketRestChallenge.exceptions;

@SuppressWarnings("serial")
public class ErrorSymbolNotFound extends Exception{
	
	public ErrorSymbolNotFound() {
        super("Symbol not found!");
    }

    public ErrorSymbolNotFound(String message) {
        super(message);
    }

}