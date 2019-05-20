package fr.pizzeria.exception;

public class UpdatePizzaException extends PizzaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdatePizzaException(String msg) {
		super(msg);
		showText(msg);
	}

}
