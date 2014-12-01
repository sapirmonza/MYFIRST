package org.monza.sapir.model;

import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;

import org.monza.sapir.Stock;
import org.monza.sapir.model.Portfolio.StockStatus;

public class Portfolio {
	private final static int MAX_PROTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stockSatus;
	private int portfolioSize = 0;
	private String title = "<h1>portfolio</h1>";
	
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
	
	public String getHtmlString(){
		int i = 0;
		for(i=0;i<portfolioSize;i++)
			title += stocks[i].getHtmlDescription()+"<br>";
		return title;
	}
	
	public class StockStatus{
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuntity;
	}
}