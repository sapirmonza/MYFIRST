package org.monza.sapir.service;

import java.util.*;

import org.monza.sapir.model.Portfolio;
import org.monza.sapir.model.StockStatus;
//import org.monza.sapir.model.Stock;
import org.monza.sapir.model.Portfolio.ALGO_RECOMMENDATION;



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
		myPortfolio = new Portfolio( new StockStatus[MAX_PROTFOLIO_SIZE], 0, "UNKNOWE",0);
	}
	
	
	/**
	* getPortfolio method is initializes all fields of the stocks, add them to the new protfolio
	* and return the new protfolio.
	* sapir monza
	* 1/12/14
	* 
	*/
	public Portfolio getPortfolio(){

		StockStatus stock1,stock2, stock3;
		
		Date date = new Date();
		
		stock1 = new StockStatus("PIH",(float)10,(float)8.5,date,0,ALGO_RECOMMENDATION.DO_NOTHING);
		myPortfolio.addStock(stock1);
		
		stock2 = new StockStatus("AAL",(float)30,(float)25.5,date,0,ALGO_RECOMMENDATION.DO_NOTHING);
		myPortfolio.addStock(stock2);
		
		stock3 = new StockStatus("CAAS",(float)20,(float)15.5,date,0,ALGO_RECOMMENDATION.DO_NOTHING);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("Potfolio #1");
		myPortfolio.setBalance(10000);
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}





