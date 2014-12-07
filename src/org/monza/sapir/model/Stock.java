package org.monza.sapir.model;

import java.util.*;


public class Stock {
	private String symbol;
	private float Ask;
	private float Bid;
	private Date date;
	private String stockHtmlDetailsString = "Unknown";


	public Stock(String string, float ask, float bid, Date date1) {
		symbol = string;
		Ask = ask;
		Bid = bid;
		date = date1;
	}

	public Stock(Stock stock){
		this(stock.getsymbol(),stock.getAsk(),stock.getBid(),stock.getDate());
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date newDate) {
		date = newDate;
	}
	public String getHtmlDescription() {
		stockHtmlDetailsString = "<b>stock symbol</b> : "+getsymbol()+ "<b> Ask </b> : "+getAsk()+ "<b> Bid </b> : "+getBid()+ "<b> Date </b> : "+getDate();
		return stockHtmlDetailsString;
	}
	

}
