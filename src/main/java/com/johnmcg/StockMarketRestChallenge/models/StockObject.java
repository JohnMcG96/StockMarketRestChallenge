package com.johnmcg.StockMarketRestChallenge.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockObject {
	
	@Id
	private String symbol; 
	private float open, high, low, current, pClose;
	
	public StockObject(){
		
	}
	public StockObject(String symbol, float open, float high, float low, float current, float pClose){
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.current = current;
		this.pClose = pClose;
	}
	
	//GETTERS
	public String getSymbol() {
		return symbol;
	}
	public float getOpen() {
		return open;
	}
	public float getHigh() {
		return high;
	}
	public float getLow() {
		return low;
	}
	public float getCurrent() {
		return current;
	}
	public float getClose() {
		return pClose;
	}

	
	//SETTERS
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public void setCurrent(float current) {
		this.current = current;
	}
	public void setClose(float pClose) {
		this.pClose = pClose;
	}
	
	
	public String toString() {
		return "Symbol: " + this.symbol + "\nClose: " + this.pClose + "\nOpen: " + this.open + "\nHigh: " + this.high + "\nLow: " + this.low + "\nCurrent: " + this.current;
	}

}
