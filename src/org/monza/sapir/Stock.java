package org.monza.sapir;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet {
	private String symbol;
	private float Ask;
	private float Bid;
	private java.util.Date date;
	private String stockHtmlDetailsString = "Unknown";
	
	public Stock() {
		symbol = "Unknown";
		Ask = 0;
		Bid = 0;
		date = new java.util.Date();
	}
	public String getsymbol() {
		return symbol;
	}
	public void setsymbol(String Symbol) {
		symbol = Symbol;
	}
	public float getAsk() {
		return Ask;
	}
	public void setAsk(float ask) {
		Ask = ask;
	}
	public float getBid() {
		return Bid;
	}
	public void setBid(float bid) {
		Bid = bid;
	}
	public java.util.Date getdate() {
		return date;
	}
	public void setdate(java.util.Date Date) {
		date = Date;
	}
	public String getHtmlDescription() {
		stockHtmlDetailsString = "<b>stock symbol</b> : "+getsymbol()+ "<b> Ask </b> : "+getAsk()+ "<b> Bid </b> : "+getBid()+ "<b> Date </b> : "+getdate();
		return stockHtmlDetailsString;
	}
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Stock stock1,stock2,stock3;
		
		stock1 = new Stock();
		stock2 = new Stock();
		stock3 = new Stock();
		
		stock1.setsymbol("PIH");
		stock1.setAsk((float) 12.4);
		stock1.setBid((float) 13.1);
		
		stock2.setsymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		
		stock3.setsymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		
		
		resp.setContentType("text/html");
		resp.getWriter().println(stock1.getHtmlDescription()+"<br>"+stock2.getHtmlDescription()+"<br>"+stock3.getHtmlDescription());

	}

}
