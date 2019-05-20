package fr.pizzeria.exception;

public class DeletePizzaException extends PizzaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeletePizzaException(String msg) {
		super(msg);
		showText(msg);
	}
}
