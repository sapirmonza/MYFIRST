package org.monza.sapir.exception;
/**
* StockAlreadyExistsException class is Exception class that handle
* the Stock's array and give a warning if you try to add stock that already exists
* sapir monza
* 9/1/15
* 
*/
public class StockAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) {
		super("Sorry, Stock " + symbol + " already exists!");
	}

}
