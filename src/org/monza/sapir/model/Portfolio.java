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
	private StockStatus[] stockStatus;
	private int portfolioSize = 0;
	private String title;
	private float balance;

	/**
	* create new portfolio
	* sapir monza
	* 8/12/14
	* 
	*/
	public Portfolio(Stock[] newStocks, StockStatus[] newStockStatus, int newPortfolioSize, String newTitle, float newBalance) {
		stocks = newStocks;
		stockStatus = newStockStatus;	
		portfolioSize = newPortfolioSize;
		title = newTitle;
		balance = newBalance;
	}
	
	/**
	* Copies the data from the portfolio and creates new portfolio with the same data
	* sapir monza
	* 8/12/14
	* 
	*/
	public Portfolio(Portfolio portfolio)
	{
		this(new Stock[MAX_PROTFOLIO_SIZE], new StockStatus[MAX_PROTFOLIO_SIZE], 0, "UNKNOWE",0);
		
		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stocks[i] = new Stock(portfolio.stocks[i]);
			stockStatus[i] = new StockStatus(portfolio.stockStatus[i]);
		}
		this.setTitle(portfolio.getTitle());
		this.portfolioSize = portfolio.portfolioSize;
		this.balance = portfolio.balance;

		
	}
	
	private enum ALGO_RECOMMENDATION {
		DO_NOTHING,SELL,BUY;
	}
	
	/**
	* addStock method is get stock parameter and add him to the stocks array.
	* sapir monza
	* 1/12/14
	* 
	*/

	public void addStock(Stock stock){
		for(int i=0; i<=portfolioSize-1; i++){
			if(stock.getsymbol().equals(this.stocks[i].getsymbol())){
				System.out.println("The stock Already exist");
				return;
			}
		}
		stocks[portfolioSize] = stock;
		stockStatus[portfolioSize] = new StockStatus(stock.getsymbol(),stock.getBid(),stock.getAsk(),new Date(stock.getDate().getTime()),ALGO_RECOMMENDATION.DO_NOTHING, 0);
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
	
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void updateBalance(float amount){
		this.balance += amount;
	}
	
	public float getStocksValue(){
		float totalValueStocks = 0;
		for(int i=0; i<=portfolioSize-1; i++){
			totalValueStocks += this.stockStatus[i].currentBid*this.stockStatus[i].stockQuntity;
		}
		return totalValueStocks;
	}
	
	public float getTotalValue(){
		return (this.getBalance()+this.getStocksValue());
	}
	
	/**
	* sellStock method sell the stock only if it is exist in the Portfolio 
	* and only if there is enough quantity in the stockQuntity.
	* sapir monza
	* 1/12/14
	* 
	*/
	public boolean sellStock(String symbol, int qu){
		for(int i=0; i<=portfolioSize-1 ; i++ ){
			if(symbol.equals(this.stocks[i].getsymbol()) && qu == -1){
				this.updateBalance(this.stockStatus[i].currentBid*this.stockStatus[i].stockQuntity);
				this.stockStatus[i].setStockQuntity(0);
				return true;
				}
			else if(symbol.equals(this.stocks[i].getsymbol()) && this.stockStatus[i].stockQuntity>qu){
				this.updateBalance(this.stockStatus[i].currentBid*qu);
				this.stockStatus[i].stockQuntity -= qu;
				return true;
			}
			else if(symbol.equals(this.stocks[i].getsymbol()) && this.stockStatus[i].stockQuntity<qu){
				System.out.println("You do not have enough from this stock");
				return false;
			}
		}
		System.out.println("The stock does not exist");
		return false;
	}
	
	
	/**
	* buyStock method Buy the stock only if it is exist in the portfolio 
	* and only if there is enough money in the balance.
	* sapir monza
	* 1/12/14
	* 
	*/
	public boolean buyStock(String symbol, int qu){
		for(int i=0; i<=portfolioSize-1; i++ ){
			if(symbol.equals(this.stocks[i].getsymbol()) && qu == -1){
				this.stockStatus[i].stockQuntity = (int)(this.balance/this.stockStatus[i].currentAsk);
				this.updateBalance(-1*this.stockStatus[i].currentAsk*this.stockStatus[i].stockQuntity);
				return true;
			}
			else if(symbol.equals(this.stocks[i].getsymbol()) && this.balance>this.stockStatus[i].currentAsk*qu){
				this.stockStatus[i].stockQuntity = qu;
				this.updateBalance(this.stockStatus[i].currentAsk*qu*-1);
				return true;
			}
			else if (symbol.equals(this.stocks[i].getsymbol()) && this.balance<this.stockStatus[i].currentAsk*qu){
					System.out.println("You do not have enough balane to buy this stock");
					return false;
			}
		}
		System.out.println("The stock does not exist");
		return false;
	}
	
	/**
	* removeStock method is get the stock's symbol that we whant to remove
	* and if the stock is exist in the Portfolio, the method first of all sell all the quantity from this stock
	* and then remove the stock from the protfolio.
	* sapir monza
	* 8/12/14
	* 
	*/
	public boolean removeStock(String symbol){
		for(int i = 0; i<=portfolioSize-1; i++){
			if(symbol.equals(this.stocks[i].getsymbol())){
				this.sellStock(symbol,-1);
				this.portfolioSize--;
				for(int j=i; j<=portfolioSize-1; j++){
					this.stocks[j] = this.stocks[j+1];
					this.stockStatus[j] = this.stockStatus[j+1];
					
				}
				return true;
			}
					
		}
		System.out.println("The stock does not exist");
		return false;
	}

	

	/**
	* getHtmlString method is a print method, defines a new variable of string type
	* and first print the Portfolio Value, Total Stocks value and the Balance
	* after that the method running loop over the stocks array and every time print different by symbol,Ask,Bid and date. 
	* sapir monza
	* 1/12/14
	* 
	*/

	public String getHtmlString(){
			String getHtmlString = "<br><b><h1>"+ this.getTitle()+":</h1></b><br><br>" ;
			getHtmlString += "<b>Portfolio Value: </b>"+this.getTotalValue()+"$ <b>Total Stocks value: </b>"+this.getStocksValue()+"$ <b>Balance: </b>"+this.getBalance()+"$<br><br>";
			getHtmlString += "<U><b><H3>Stocks:</H3></b></U><br>";
			for(int i = 0; i<=portfolioSize-1;i++){
				getHtmlString +="<b>Stock "+(i+1)+".</b> "+ stocks[i].getHtmlDescription()+"<b> Quntity</b>: "+this.stockStatus[i].stockQuntity+"<br>";
			}
			return getHtmlString;
	}

	public class StockStatus{

		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuntity;
		

		
		/**
		* create new StockStatus
		* sapir monza
		* 8/12/14
		* 
		*/
		public StockStatus(String string, float cBid, float cAsk, Date date1, ALGO_RECOMMENDATION recom, int stockQ){
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
		//	if(this.symbol != null)
		//{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentBid = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuntity = stockStatus.stockQuntity;
		//	}
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
		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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