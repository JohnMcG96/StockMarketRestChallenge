package com.johnmcg.StockMarketRestChallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.johnmcg.StockMarketRestChallenge.exceptions.ErrorSymbolNotFound;
import com.johnmcg.StockMarketRestChallenge.models.StockObject;
import com.johnmcg.StockMarketRestChallenge.services.SymbolServ;

@RestController
@RequestMapping("/stockmarketapi/stock")
public class SymbolCont {
	
	@Autowired
    SymbolServ symbolService;

    @GetMapping("/{symbol}")
    public StockObject getStockQuoteFromDB(@PathVariable String symbol){
        try {
            return symbolService.getStockQuoteFromDB(symbol);
        } catch (ErrorSymbolNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/{symbol}")
    public StockObject getStockQuoteFromApi(@PathVariable String symbol) {
        try {
            return symbolService.getStockQuoteFromApi(symbol);
        } catch (ErrorSymbolNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }    
    }
}
