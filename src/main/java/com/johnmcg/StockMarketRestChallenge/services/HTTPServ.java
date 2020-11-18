package com.johnmcg.StockMarketRestChallenge.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.johnmcg.StockMarketRestChallenge.exceptions.ErrorSymbolNotFound;
import com.johnmcg.StockMarketRestChallenge.models.StockObject;
import com.johnmcg.StockMarketRestChallenge.resources.PropertiesKey;

@Service
public class HTTPServ {
	
	private StockObject stockObj;
	
	public StockObject get(String symbol) throws ErrorSymbolNotFound {
		stockObj = new StockObject();
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = null;
		try {
			
			String API_KEY = PropertiesKey.getPropertiesFile().getProperty("API_KEY");
			
			request = HttpRequest.newBuilder()
					.uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol))
					.header("x-rapidapi-key", API_KEY)
					.header("x-rapidapi-host", "alpha-vantage.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
            String responseBody = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            DiscountJSON(responseBody, stockObj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
		
		return stockObj;
		
	}
        
        public void DiscountJSON(String toBeParsed, StockObject sObj) {
    		String endLine = "\n";
    		ArrayList<String> parsedLine = new ArrayList<String>();
    		
    		StringTokenizer st = new StringTokenizer(toBeParsed, endLine);
    		while (st.hasMoreTokens()) {
    			String token = st.nextToken();
    			if (token.contains(":")) {
    				char quote = '"';
    				int firstSep = 0;
    				int secSep = 0;
    				
    				String[] splitString = token.split(": ");
    				token = splitString[1];
    				firstSep = token.indexOf(quote);
    				secSep = token.lastIndexOf(quote);
    				
    				if (firstSep > 0 || secSep > 0) {
    					token = token.substring(firstSep+1, secSep);
    					parsedLine.add(token);
    				}
    			}
    		}
    		
    		sObj.setSymbol(parsedLine.get(0));
    		sObj.setOpen(Float.parseFloat(parsedLine.get(1)));
    		sObj.setHigh(Float.parseFloat(parsedLine.get(2)));
    		sObj.setLow(Float.parseFloat(parsedLine.get(3)));
    		sObj.setCurrent(Float.parseFloat(parsedLine.get(4)));
    		sObj.setClose(Float.parseFloat(parsedLine.get(7)));
    	}

}
