package com.johnmcg.StockMarketRestChallenge.resources;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesKey {
	
	public static Properties getPropertiesFile() throws Exception {
		
		InputStream inStream = PropertiesKey.class.getResourceAsStream("/application.properties");
        Properties prop = new Properties();
        prop.load(inStream);
        
        return prop;
    }

}
