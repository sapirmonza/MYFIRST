package org.monza.sapir.exception;
/**
* PortfolioFullException class is Exception class that handle
* the portfolio full exception warning
* sapir monza
* 9/1/15
* 
*/

public class PortfolioFullException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PortfolioFullException() {
		super("Sorry, You had reached maximum portfolio size!");
	}

}
