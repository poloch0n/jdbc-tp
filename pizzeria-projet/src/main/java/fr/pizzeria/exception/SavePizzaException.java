package fr.pizzeria.exception;

public class SavePizzaException extends PizzaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public SavePizzaException(String msg) {
		super(msg);
		showText(msg);
	}
}
