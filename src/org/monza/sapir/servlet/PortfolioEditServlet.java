package org.monza.sapir.servlet;

import org.monza.sapir.dto.PortfolioEditDto;
import org.monza.sapir.exception.BalanceException;
import org.monza.sapir.exception.IllegalQuantityException;
import org.monza.sapir.exception.PortfolioFullException;
import org.monza.sapir.exception.StockAlreadyExistsException;
import org.monza.sapir.exception.StockNotExistsException;
import org.monza.sapir.exception.SymbolNotFoundInNasdaq;
import org.monza.sapir.model.Portfolio;
import org.monza.sapir.model.StockStatus;
import org.monza.sapir.service.PortfolioService;
import org.monza.sapir.service.PortfolioService.OPERATION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioEditServlet extends AbstractAlgoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		Portfolio portfolio = portfolioService.getPortfolio();
		StockStatus[] stockStatusArray = portfolio.getStock();
		List<StockStatus> stockStatusList = new ArrayList<>();
		for (StockStatus ss : stockStatusArray) {
			if(ss != null)
				stockStatusList.add(ss);
		}

		PortfolioEditDto ret = new PortfolioEditDto();
		ret.setTitle(portfolio.getTitle());
		ret.setBalance(portfolio.getBalance());
		ret.setStockStatusList(stockStatusList);
		resp.getWriter().print(withNullObjects().toJson(ret));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		try {
			String title = req.getParameter("title");
			if(title != null && !title.isEmpty()) {
				portfolioService.setTitle(title);
			}

			String balance = req.getParameter("balance");
			if(balance != null && !balance.isEmpty()) {
				portfolioService.setBalance(Float.valueOf(balance));
			}

			String symbol = req.getParameter("symbol");

			String qStr = req.getParameter("quantityforaction");
			int quantity;
			if(qStr == null || qStr.isEmpty()) {
				quantity = -1;
			}else {
				quantity = Integer.valueOf(qStr);					
			}

			String operationStr = req.getParameter("operation");
			if (operationStr != null) {
				PortfolioService.OPERATION operation = OPERATION.valueOf(operationStr.toUpperCase());

				switch (operation) {
				case ADD:
					portfolioService.addStock(symbol);
					break;

				case BUY:
					portfolioService.buyStock(symbol, quantity);
					break;

				case SELL:
					portfolioService.sellStock(symbol, quantity);
					break;

				case REMOVE:
					portfolioService.removeStock(symbol);
					break;

				default:
					break;
				}
			}
			resp.sendRedirect("/portfolioedit.jsp");
		}catch(StockNotExistsException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=Stock not exist in portfolio");
		}catch(StockAlreadyExistsException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=Stock already exists in portfolio");
		}catch(PortfolioFullException | SymbolNotFoundInNasdaq | IllegalQuantityException | BalanceException e) {
			resp.sendRedirect("/portfolioedit.jsp?error=" + e.getMessage());
		}catch(Exception e) {
			log.severe("Error: " + e.getMessage());
			resp.sendRedirect("/portfolioedit.jsp?error=General error occured.");
		}
	}
}
