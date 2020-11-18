package com.johnmcg.StockMarketRestChallenge.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johnmcg.StockMarketRestChallenge.models.StockObject;

@Repository
public interface StockRepo extends CrudRepository<StockObject, String> {

}