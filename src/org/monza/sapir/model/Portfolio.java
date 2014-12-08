package org.monza.sapir.model;

import java.util.*;
import java.text.SimpleDateFormat;

import org.monza.sapir.model.Portfolio.StockStatus;

/**
* Portfolio class holds all the stocks and the stocks status in arrays
* and print them. 
* sapir monza
* 1/12/14
* 
*/

public class Portfolio {
	private final static int MAX_PROTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stockSatus;
	private int portfolioSize = 0;
	private String title;

	public Portfolio(Stock[] newStocks, StockStatus[] newStockStatus) {
		stocks = newStocks;
		stockSatus = newStockStatus;	
	}
	
	public Portfolio(Portfolio portfolio)
	{
		this(portfolio.stocks, portfolio.stockSatus);
		
		for(int i = 0; i < MAX_PROTFOLIO_SIZE ; i++){
			stocks[i] = new Stock(portfolio.getStock()[i]);
		}
		
		this.stockSatus = portfolio.stockSatus;
		this.setTitle(portfolio.getTitle());
		
	}
	
	/**
	* addStock method is get stock parameter and add him to the stocks array.
	* sapir monza
	* 1/12/14
	* 
	*/

	public void addStock(Stock stock){
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}
	
	/**
	* getStock method is return the stocks array.
	* sapir monza
	* 1/12/14
	* 
	*/

	public Stock[] getStock(){
		return stocks;
	}
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	* getHtmlString method is a print method, defines a new variable of string type
	* and running loop over the stocks array and every time print different by symbol,Ask,Bid and date. 
	* sapir monza
	* 1/12/14
	* 
	*/

	public String getHtmlString(){
		int i = 0;
		String getHtmlString = "<h1>Stock Portfolio</h1>" ;
		for(i=0;i<portfolioSize;i++)
			getHtmlString += stocks[i].getHtmlDescription()+"<br>";
		return getHtmlString;
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