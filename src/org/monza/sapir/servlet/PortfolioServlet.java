package org.monza.sapir.servlet;

import java.io.IOException;

import javax.servlet.http.*;

import org.monza.sapir.Stock;
import org.monza.sapir.model.Portfolio;
import org.monza.sapir.service.PortfolioService;

@SuppressWarnings("serial")

public class PortfolioServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStock();
		
		
		resp.setContentType("text/html");
		resp.getWriter().println(portfolio.getHtmlString());
		
	}

}
