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

	public Portfolio() {
		stocks = new Stock[MAX_PROTFOLIO_SIZE];
		stockSatus = new StockStatus[MAX_PROTFOLIO_SIZE];	
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
	
	/**
	* getHtmlString method is a print method, defines a new variable of string type
	* and running loop over the stocks array and every time print different by symbol,Ask,Bid and date. 
	* sapir monza
	* 1/12/14
	* 
	*/

	public String getHtmlString(){
		int i = 0;
		String getHtmlString = "<h1>portfolio</h1>" ;
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