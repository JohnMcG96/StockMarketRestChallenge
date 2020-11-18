package com.johnmcg.StockMarketRestChallenge.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnmcg.StockMarketRestChallenge.dao.StockRepo;
import com.johnmcg.StockMarketRestChallenge.exceptions.ErrorSymbolNotFound;
import com.johnmcg.StockMarketRestChallenge.models.StockObject;
import com.johnmcg.StockMarketRestChallenge.utils.ValidSymbol;

@Service
public class SymbolServ {
	private static final Logger log = Logger.getLogger(SymbolServ.class.getName());
    private StockObject stockObj;

    @Autowired
    HTTPServ httpService;

    @Autowired
    StockRepo stockMarketRepo;
    
    public StockObject getStockQuoteFromDB(String symbol) throws ErrorSymbolNotFound {
    	symbol = symbol.toUpperCase();
        if (!ValidSymbol.readValidSymbol(symbol)){
            throw new ErrorSymbolNotFound("Symbol not valid! Please try again.");
        }

        // If symbol exists in DB, retrieve data from DB otherwise throw SymbolNotFoundException.
        log.info("action=getStockQuoteFromDB, receive=symbol, symbol=" + symbol);
        if (stockMarketRepo.findById(symbol).isPresent()) {
            return stockMarketRepo.findById(symbol).get();
        }

        throw new ErrorSymbolNotFound("Symbol not found.");
    }
    
    public StockObject getStockQuoteFromApi(String symbol) throws ErrorSymbolNotFound {
    	symbol = symbol.toUpperCase();
        if (!ValidSymbol.readValidSymbol(symbol)){
            throw new ErrorSymbolNotFound("Provided symbol " + symbol + " is not valid.");
        }

        // Get a stock quote from API
        log.info("action=getStockQuoteFromApi, receive=symbol, symbol=" + symbol);
        stockObj = httpService.get(symbol);

        // If not exist in DB, add to DB otherwise the DB will be updated.
        log.info("action=updateOrAddToDB");
        stockMarketRepo.save(stockObj);
        return stockObj;
    }

}
