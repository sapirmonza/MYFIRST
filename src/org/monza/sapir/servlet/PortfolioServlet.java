package org.monza.sapir.servlet;

import java.io.IOException;

import javax.servlet.http.*;

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
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();



		resp.setContentType("text/html");
		resp.getWriter().println(portfolio.getHtmlString());

	}

}
