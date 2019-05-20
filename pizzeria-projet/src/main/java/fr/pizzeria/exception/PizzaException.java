package fr.pizzeria.exception;

public abstract class PizzaException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PizzaException(String message) {
		super(message);
	}

	public static void showText(String texte) {
		System.out.println(texte);
	}
}