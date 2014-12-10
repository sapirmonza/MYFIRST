package org.monza.sapir.model;

import java.util.*;
import java.text.SimpleDateFormat;

import org.monza.sapir.service.PortfolioService;
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

	/**
	* create new portfolio
	* sapir monza
	* 8/12/14
	* 
	*/
	public Portfolio(Stock[] newStocks, StockStatus[] newStockStatus, int newPortfolioSize, String newTitle) {
		stocks = newStocks;
		stockSatus = newStockStatus;	
		portfolioSize = newPortfolioSize;
		title = newTitle;
	}
	
	/**
	* Copies the data from the portfolio and creates new portfolio with the same data
	* sapir monza
	* 8/12/14
	* 
	*/
	public Portfolio(Portfolio portfolio)
	{
		this(new Stock[MAX_PROTFOLIO_SIZE], new StockStatus[MAX_PROTFOLIO_SIZE], 0, "UNKNOWE");
		
		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stocks[i] = new Stock(portfolio.stocks[i]);
			stockSatus[i] = new StockStatus(portfolio.stockSatus[i]);
		}
		this.setTitle(portfolio.getTitle());
		this.portfolioSize = portfolio.portfolioSize;
		//this.stockSatus = portfolio.stockSatus;
		
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
	
	
	
	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	* removeStock method is get the stock index that we whant to remove
	* and remove him .
	* sapir monza
	* 8/12/14
	* 
	*/
	public void removeStock(int stocksLocation){
		if(stocksLocation == portfolioSize){
			this.portfolioSize--;
			}
		else{
			this.portfolioSize--;
			for(int i = stocksLocation; i<= portfolioSize-1; i++){
				this.stocks[i] = this.stocks[i+1];
			}
			
		}
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
			String getHtmlString = "<br><b><h1>"+ this.getTitle()+":</h1></b><br><br>" ;
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
		
		/**
		* create new StockStatus
		* sapir monza
		* 8/12/14
		* 
		*/
		public StockStatus(String string, float cBid, float cAsk, Date date1, int recom, int stockQ){
			symbol = string;
			currentBid = cBid;
			currentAsk = cAsk;
			date = date1;
			recommendation = recom;
			stockQuntity = stockQ;
		}
		
		/**
		* Copies the data from the StockStatus and creates new StockStatus with the same data
		* sapir monza
		* 8/12/14
		* 
		*/	
		public StockStatus(StockStatus stockStatus){
			if(this.symbol != null)
		{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentAsk = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuntity = stockStatus.stockQuntity;
			}
		}
		
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuntity() {
			return stockQuntity;
		}
		public void setStockQuntity(int stockQuntity) {
			this.stockQuntity = stockQuntity;
		}
		
	}
}