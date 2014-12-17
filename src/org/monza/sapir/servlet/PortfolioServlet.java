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
		Portfolio portfolio1 = portfolioService.getPortfolio();
		//Portfolio portfolio2 = new Portfolio(portfolio1);



		resp.setContentType("text/html");
		resp.getWriter().println(portfolio1.getHtmlString());
		/*portfolio2.setTitle("Potfolio #2");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio1.removeStock(0);
		resp.getWriter().println("<h1><b>After Remove Stock</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		portfolio2.getStock()[2].setBid((float)55.55);
		resp.getWriter().println("<h1><b>After Change The Bid</h1></b>");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());*/
		

	}

}
