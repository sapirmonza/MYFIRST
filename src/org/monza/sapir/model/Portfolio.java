package org.monza.sapir.model;

import java.util.*;

import org.monza.sapir.exception.BalanceException;
import org.monza.sapir.exception.PortfolioFullException;
import org.monza.sapir.exception.StockAlreadyExistsException;
import org.monza.sapir.exception.StockNotEnoughException;
import org.monza.sapir.exception.StockNotExistException;
//import org.monza.sapir.service.PortfolioService;
import org.monza.sapir.model.StockStatus;

import java.util.logging.Logger;

/**
* Portfolio class holds all the stocks and the stocks status in arrays
* and print them. 
* sapir monza
* 1/12/14
* 
*/

public class Portfolio {
	private static final Logger log = Logger.getLogger(Portfolio.class.getSimpleName());
	public final static int MAX_PROTFOLIO_SIZE = 5;
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
	public Portfolio( StockStatus[] newStockStatus, int newPortfolioSize, String newTitle, float newBalance) {
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
		this( new StockStatus[MAX_PROTFOLIO_SIZE], 0, "UNKNOWE",0);
		
		for(int i = 0; i < portfolio.portfolioSize ; i++){
			stockStatus[i] = new StockStatus(portfolio.stockStatus[i]);
		}
		this.setTitle(portfolio.getTitle());
		this.portfolioSize = portfolio.portfolioSize;
		this.balance = portfolio.balance;

		
	}
	
	
	public Portfolio(List<StockStatus> stockStatuses) {
		// TODO Auto-generated constructor stub
		this(new StockStatus[MAX_PROTFOLIO_SIZE], 0, "UNKNOWE",0);
		for(int i = 0; i < portfolioSize; i++)
			this.stockStatus[i] = stockStatuses.get(i);
	}

	public enum ALGO_RECOMMENDATION {
		DO_NOTHING,SELL,BUY;
	}
	
	/**
	* addStock method is get stock parameter and add him to the stocks array.
	* sapir monza
	* 1/12/14
	* 
	*/

	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		if(this.portfolioSize<MAX_PROTFOLIO_SIZE){
			for(int i=0; i<=portfolioSize-1; i++){
				if(stock.getsymbol().equals(this.stockStatus[i].getsymbol())){
					log.warning("Sorry, Stock " + stock.getsymbol() + " already exists");
					throw new StockAlreadyExistsException(stock.getsymbol());
				}
			}
			stockStatus[portfolioSize] = new StockStatus(stock.getsymbol(),stock.getAsk(),stock.getBid(),new Date(stock.getDate().getTime()),0 ,ALGO_RECOMMENDATION.DO_NOTHING);
			portfolioSize++;
		}
		else{
			log.warning("Sorry, You had reached maximum portfolio size [" + MAX_PROTFOLIO_SIZE + "]");
			throw new PortfolioFullException();
		}
	}
	
	
	/**
	* getStock method is return the stocks array.
	* sapir monza
	* 1/12/14
	* 
	*/

	public StockStatus[] getStock(){
		return stockStatus;
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

	public void updateBalance(float amount) throws BalanceException{
		this.balance += amount;
	}
	
	public float getStocksValue(){
		float totalValueStocks = 0;
		for(int i=0; i<=portfolioSize-1; i++){
			totalValueStocks += this.stockStatus[i].bid*this.stockStatus[i].getStockQuntity();
		}
		return totalValueStocks;
	}
	
	public float getTotalValue(){
		return (this.getBalance()+this.getStocksValue());
	}
	
	public StockStatus findBySymbol (String symbol)
	{
		for(int i = 0; i < portfolioSize; i++){
			if(stockStatus[i].getsymbol().toLowerCase().equals(symbol))// == symbol)
				return stockStatus[i];
		}
		return null;
	}
	
	/**
	* sellStock method sell the stock only if it is exist in the Portfolio 
	* and only if there is enough quantity in the stockQuntity.
	* sapir monza
	* 1/12/14
	* 
	*/
	public void sellStock(String symbol, int qu) throws StockNotEnoughException,StockNotExistException,BalanceException{
		int i;
		for(i=0; i<=portfolioSize-1 ; i++ ){
			if(symbol.equals(this.stockStatus[i].getsymbol()) && qu == -1 ||symbol.equals(this.stockStatus[i].getsymbol()) && this.stockStatus[i].getStockQuntity() == qu){
				this.updateBalance(this.stockStatus[i].bid*this.stockStatus[i].getStockQuntity());
				this.stockStatus[i].setStockQuntity(0);
				}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.stockStatus[i].getStockQuntity()>qu){
				this.updateBalance(this.stockStatus[i].bid*qu);
				this.stockStatus[i].setStockQuntity(-qu);
			}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.stockStatus[i].getStockQuntity()<qu){
				log.warning("Sorry, You do not have enough from this stock");
				throw new StockNotEnoughException();
			}
		}
		if(i == portfolioSize){
			log.warning("Sorry, The stock does not exist");
			throw new StockNotExistException();
		}
	}
	
	
	/**
	* buyStock method Buy the stock only if it is exist in the portfolio 
	* and only if there is enough money in the balance.
	* sapir monza
	* 1/12/14
	* 
	*/
	public void buyStock(String symbol, int qu) throws BalanceException, StockNotExistException{
		int i;
		for(i=0; i<=portfolioSize-1; i++ ){
			if(symbol.equals(this.stockStatus[i].getsymbol()) && qu == -1){
				this.stockStatus[i].setStockQuntity((int)(this.balance/this.stockStatus[i].ask));
				this.updateBalance(-1*this.stockStatus[i].ask*this.stockStatus[i].getStockQuntity());
			}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.balance>this.stockStatus[i].ask*qu){
				this.stockStatus[i].setStockQuntity(qu);
				this.updateBalance(this.stockStatus[i].ask*qu*-1);
			}
			else if (symbol.equals(this.stockStatus[i].getsymbol()) && this.balance<this.stockStatus[i].ask*qu){
				log.warning("Sorry, You do not have enough balane to buy this stock");
				throw new BalanceException();
			}
		}
		if(i == portfolioSize){
			log.warning("Sorry, The stock does not exist");
			throw new StockNotExistException();
		}
	}
	
	/**
	* removeStock method is get the stock's symbol that we whant to remove
	* and if the stock is exist in the Portfolio, the method first of all sell all the quantity from this stock
	* and then remove the stock from the protfolio.
	* sapir monza
	* 8/12/14
	 * @throws  
	* 
	*/
	public void removeStock(String symbol) throws StockNotEnoughException, StockNotExistException,BalanceException{
		int i;
		for(i = 0; i<=portfolioSize-1; i++){
			if(symbol.equals(this.stockStatus[i].getsymbol())){
				this.sellStock(symbol,-1);
				this.portfolioSize--;
				for(int j=i; j<=portfolioSize-1; j++){
					this.stockStatus[j] = this.stockStatus[j+1];
					
				}
				
			}
					
		}
		if(i == portfolioSize){
			log.warning("Sorry, The stock does not exist");
			throw new StockNotExistException();
		}
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
				getHtmlString +="<b>Stock "+(i+1)+".</b> "+ stockStatus[i].getHtmlDescription()+"<b> Quntity</b>: "+this.stockStatus[i].getStockQuntity()+"<br>";
			}
			return getHtmlString;
	}
}