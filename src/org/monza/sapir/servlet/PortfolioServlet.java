package org.monza.sapir.servlet;

import java.io.IOException;

import javax.servlet.http.*;

import org.monza.sapir.exception.BalanceException;
import org.monza.sapir.exception.PortfolioFullException;
import org.monza.sapir.exception.StockAlreadyExistsException;
import org.monza.sapir.exception.StockNotEnoughException;
import org.monza.sapir.exception.StockNotExistException;
import org.monza.sapir.model.Portfolio;
import org.monza.sapir.model.Stock;
import org.monza.sapir.service.PortfolioService;

/**
* PortfolioServlet class is used as a server app,The application running on the network because of him.
* Create new  portfolioService and new portfolio, Initializes the portfolio by the values 
* determined
* and the call the method that print them.
* sapir monza
* 1/12/14
* 
*/

public class PortfolioServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		resp.setContentType("text/html");
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio1;
		try{
			portfolio1 = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio1.getHtmlString());	
		}
		catch (PortfolioFullException e) {
			resp.getWriter().println("Sorry, You had reached maximum portfolio size!");
		}
		catch(StockAlreadyExistsException ee) {
			resp.getWriter().println("Sorry, Stock already exists!");			
		}
		catch(BalanceException eee) {
			resp.getWriter().println("Sorry, You do not have enough balane to buy this stock!");			
		}
		catch(StockNotEnoughException eeee) {
			resp.getWriter().println("Sorry, You do not have enough from this stock!");			
		}
		
		catch(StockNotExistException eeeee) {
			resp.getWriter().println("Sorry, The stock does not exist!");			
		}
		
				

	}

}
