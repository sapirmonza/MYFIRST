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
	

}
