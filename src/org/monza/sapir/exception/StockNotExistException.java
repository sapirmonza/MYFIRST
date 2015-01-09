package org.monza.sapir.exception;
/**
* StockNotExistException class is Exception class that handle
* the Stock's array and give a warning if you try to remove or sell stock that you don't have
* sapir monza
* 9/1/15
* 
*/
public class StockNotExistException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockNotExistException() {
		super("Sorry, The stock does not exist");
	}

}
