package org.monza.sapir;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet {
	private String symbol;
	private float Ask;
	private float Bid;
	private Date date;
	private String stockHtmlDetailsString = "Unknown";


	public Stock() {
		symbol = "Unknown";
		Ask = 0;
		Bid = 0;
		date = null;
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
	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);
	}
	public void setDate(Date newDate) {
		date = newDate;
	}
	public String getHtmlDescription() {
		stockHtmlDetailsString = "<b>stock symbol</b> : "+getsymbol()+ "<b> Ask </b> : "+getAsk()+ "<b> Bid </b> : "+getBid()+ "<b> Date </b> : "+getDate();
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
		stock1.setDate(new Date(114, 10, 15));
		
		stock2.setsymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		stock2.setDate(new Date(114, 10, 15));
		
		stock3.setsymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		stock3.setDate(new Date(114, 10, 15));
		
		resp.setContentType("text/html");
		resp.getWriter().println(stock1.getHtmlDescription()+"<br>"+stock2.getHtmlDescription()+"<br>"+stock3.getHtmlDescription());

	}

}
