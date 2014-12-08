package org.monza.sapir.service;

import java.util.*;

import org.monza.sapir.model.Portfolio;
import org.monza.sapir.model.Portfolio.StockStatus;
import org.monza.sapir.model.Stock;



/**
* PortfolioService class initializes all fields of the stocks
*create a new portfolio and add the stocks to the new portfolio.
* sapir monza
* 1/12/14
* 
*/

public class PortfolioService { 
	private final static int MAX_PROTFOLIO_SIZE = 5;
	Portfolio myPortfolio;

	public PortfolioService() {
		myPortfolio = new Portfolio(new Stock[MAX_PROTFOLIO_SIZE], new StockStatus[MAX_PROTFOLIO_SIZE]);
	}
	
	/**
	* getPortfolio method is initializes all fields of the stocks, add them to the new protfolio
	* and return the new protfolio.
	* sapir monza
	* 1/12/14
	* 
	*/
	public Portfolio getPortfolio(){

		Stock stock1, stock2, stock3;
		
		Date date = new Date();

		
		stock1 = new Stock("PIH",(float)12.4,(float)13.1,date);
		myPortfolio.addStock(stock1);
		
		stock2 = new Stock("AAL",(float)5.5,(float)5.78,date);
		myPortfolio.addStock(stock2);
		
		stock3 = new Stock("CAAS",(float)31.5,(float)31.2,date);
		myPortfolio.addStock(stock3);


		return myPortfolio;
	}
}





