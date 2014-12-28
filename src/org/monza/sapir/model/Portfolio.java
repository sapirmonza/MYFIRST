package org.monza.sapir.model;

import java.util.*;
import java.text.SimpleDateFormat;

import org.monza.sapir.service.PortfolioService;
import org.monza.sapir.model.StockStatus;

/**
* Portfolio class holds all the stocks and the stocks status in arrays
* and print them. 
* sapir monza
* 1/12/14
* 
*/

public class Portfolio {
	private final static int MAX_PROTFOLIO_SIZE = 5;
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
	
	public enum ALGO_RECOMMENDATION {
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
			if(stock.getsymbol().equals(this.stockStatus[i].getsymbol())){
				System.out.println("The stock Already exist");
				return;
			}
		}
		stockStatus[portfolioSize] = new StockStatus(stock.getsymbol(),stock.getAsk(),stock.getBid(),new Date(stock.getDate().getTime()),0 ,ALGO_RECOMMENDATION.DO_NOTHING);
		portfolioSize++;
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

	public void updateBalance(float amount){
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
	
	/**
	* sellStock method sell the stock only if it is exist in the Portfolio 
	* and only if there is enough quantity in the stockQuntity.
	* sapir monza
	* 1/12/14
	* 
	*/
	public boolean sellStock(String symbol, int qu){
		for(int i=0; i<=portfolioSize-1 ; i++ ){
			if(symbol.equals(this.stockStatus[i].getsymbol()) && qu == -1){
				this.updateBalance(this.stockStatus[i].bid*this.stockStatus[i].getStockQuntity());
				this.stockStatus[i].setStockQuntity(0);
				return true;
				}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.stockStatus[i].getStockQuntity()>qu){
				this.updateBalance(this.stockStatus[i].bid*qu);
				this.stockStatus[i].setStockQuntity(-qu);
				return true;
			}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.stockStatus[i].getStockQuntity()<qu){
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
			if(symbol.equals(this.stockStatus[i].getsymbol()) && qu == -1){
				this.stockStatus[i].setStockQuntity((int)(this.balance/this.stockStatus[i].ask));
				this.updateBalance(-1*this.stockStatus[i].ask*this.stockStatus[i].getStockQuntity());
				return true;
			}
			else if(symbol.equals(this.stockStatus[i].getsymbol()) && this.balance>this.stockStatus[i].ask*qu){
				this.stockStatus[i].setStockQuntity(qu);
				this.updateBalance(this.stockStatus[i].ask*qu*-1);
				return true;
			}
			else if (symbol.equals(this.stockStatus[i].getsymbol()) && this.balance<this.stockStatus[i].ask*qu){
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
			if(symbol.equals(this.stockStatus[i].getsymbol())){
				this.sellStock(symbol,-1);
				this.portfolioSize--;
				for(int j=i; j<=portfolioSize-1; j++){
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
				getHtmlString +="<b>Stock "+(i+1)+".</b> "+ stockStatus[i].getHtmlDescription()+"<b> Quntity</b>: "+this.stockStatus[i].getStockQuntity()+"<br>";
			}
			return getHtmlString;
	}
}