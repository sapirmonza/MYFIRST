package org.monza.sapir.model;

import java.util.Date;
import org.monza.sapir.model.Portfolio.ALGO_RECOMMENDATION;


public class StockStatus extends Stock {

	private ALGO_RECOMMENDATION recommendation;
	private int stockQuntity;
	
	/**
	* create new StockStatus
	* sapir monza
	* 8/12/14
	* 
	*/
	public StockStatus(String string, float Ask, float Bid, Date date1,int newStockQuntity, ALGO_RECOMMENDATION recom) {
		super(string, Ask, Bid, date1);
		// TODO Auto-generated constructor stub
		stockQuntity = newStockQuntity;
		recommendation = recom;
	
	}
	
	/**
	* Copies the data from the StockStatus and creates new StockStatus with the same data
	* sapir monza
	* 8/12/14
	* 
	*/	
	public StockStatus(StockStatus stockStatus){
		this(stockStatus.getsymbol(),stockStatus.getAsk(),stockStatus.getBid(), new Date(stockStatus.getDate().getTime()), stockStatus.getStockQuntity(), stockStatus.getRecommendation());
		
		}

	public StockStatus(Stock stock) {
		super(stock.getsymbol(), stock.getAsk(), stock.getBid(), stock.getDate());
		stockQuntity = 0;
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	public int getStockQuntity() {
		return stockQuntity;
	}
	public void setStockQuntity(int stockQuntity) {
		this.stockQuntity = stockQuntity;
	}



	
	

}
