package org.monza.sapir.model;

import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;

import org.monza.sapir.Stock;
import org.monza.sapir.model.Portfolio.StockStatus;

public class Portfolio {
	final int MAX_PROTFOLIO_SIZE = 5;
	final Stock stocks[];
	final StockStatus stockSatus[];
	private int portfolioSize = 0;
	
	public Portfolio() {
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
		stockSatus = new StockStatus[MAX_PROTFOLIO_SIZE];	
	}
	
	public void addStock(Stock stock){
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	public Stock[] getStock(){
		return stocks;
	}
	
	public class StockStatus{
		final int DO_NOTHING = 0;
		final int BUY = 1;
		final int SELL = 2;
		public String symbol;
		public float currentBid;
		public float currentAsk;
		public Date date;
		public int recommendation;
		public int stockQuntity;
	}
}