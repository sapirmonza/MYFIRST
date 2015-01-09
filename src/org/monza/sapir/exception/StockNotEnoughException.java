package org.monza.sapir.exception;
/**
* StockNotEnoughException class is Exception class that handle
* the Stock's quantity and give a warning if you try to remove or sell stock's quantity that you don't have
* sapir monza
* 9/1/15
* 
*/
public class StockNotEnoughException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockNotEnoughException() {
		super("Sorry, You do not have enough from this stock");
	}

}
