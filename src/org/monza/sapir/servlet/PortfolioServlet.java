package org.monza.sapir.servlet;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.*;
import org.monza.sapir.Stock;
import org.monza.sapir.model.Portfolio;
import org.monza.sapir.service.StockService;

public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		StockService portfolioService = new StockService();
		Portfolio portfolio = new Portfolio();
		Stock[] stocks = portfolio.getStock();
		
		resp.setContentType("text/html");
		resp.getWriter().println(stocks);
		
	}

}
