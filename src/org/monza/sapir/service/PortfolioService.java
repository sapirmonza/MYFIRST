package org.monza.sapir.service;

import java.util.*;

import org.monza.sapir.model.Portfolio;
import org.monza.sapir.model.Stock;

/**
* PortfolioService class initializes all fields of the stocks
*create a new portfolio and add the stocks to the new portfolio.
* sapir monza
* 1/12/14
* 
*/

public class PortfolioService { 
	Portfolio myPortfolio;

	public PortfolioService() {
		myPortfolio = new Portfolio();
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

		stock1 = new Stock();
		stock2 = new Stock();
		stock3 = new Stock();

		stock1.setsymbol("PIH");
		stock1.setAsk((float) 12.4);
		stock1.setBid((float) 13.1);
		stock1.setDate(new Date(114, 10, 15));
		myPortfolio.addStock(stock1);

		stock2.setsymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		stock2.setDate(new Date(114, 10, 15));
		myPortfolio.addStock(stock2);

		stock3.setsymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		stock3.setDate(new Date(114, 10, 15));
		myPortfolio.addStock(stock3);

		return myPortfolio;
	}
}





