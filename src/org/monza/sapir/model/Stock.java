package org.monza.sapir.model;

import java.util.*;


public class Stock {
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private String stockHtmlDetailsString = "Unknown";

	/**
	* create new Stock
	* sapir monza
	* 8/12/14
	* 
	*/
	public Stock(String string, float Ask, float Bid, Date date1) {
		symbol = string;
		ask = Ask;
		bid = Bid;
		date = date1;
	}

	/**
	* Copies the data from the Stock and creates new Stock with the same data
	* sapir monza
	* 8/12/14
	* 
	*/
	public Stock(Stock stock){
		this(stock.getsymbol(),stock.getAsk(),stock.getBid(),new Date(stock.getDate().getTime()));
	}
	public String getsymbol() {
		return symbol;
	}
	public void setsymbol(String Symbol) {
		symbol = Symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float Ask) {
		ask = Ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float Bid) {
		bid = Bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date newDate) {
		date = newDate;
	}
	public String getHtmlDescription() {
		stockHtmlDetailsString = "<b>Stock Symbol</b> : "+getsymbol()+ "<b> Ask </b> : "+getAsk()+ "<b> Bid </b> : "+getBid()+ "<b> Date </b> : "+getDate();
		return stockHtmlDetailsString;
	}
	

}
