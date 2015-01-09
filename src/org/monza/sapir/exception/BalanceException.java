package org.monza.sapir.exception;
/**
* BalanceException class is Exception class that handle
* the balance and give a warning when you exceeded the balance
* sapir monza
* 9/1/15
* 
*/
public class BalanceException extends Exception {
	private static final long serialVersionUID = 1L;

	public BalanceException() {
		super("Sorry, You do not have enough balane to buy this stock");
	}

}
