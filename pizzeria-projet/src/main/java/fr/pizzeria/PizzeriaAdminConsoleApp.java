package fr.pizzeria;

import fr.pizzeria.FromMenu;
import fr.pizzeria.ShowMenu;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.PizzaMemDaoBis;
import fr.pizzeria.service.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	private static PizzaMemDaoBis daobis;
	private static MenuServiceFactory msf;
	private static FromMenu fm;
	public static void main(String[] args) throws Exception {
		
		daobis = new PizzaMemDaoBis();
		fm = new FromMenu();
		msf = new MenuServiceFactory();
		ShowMenu.showIntroduction();
		while(true) {
			ShowMenu.showMenuOptions();
			int answer = FromMenu.getIntFromMenu();
			try {
				msf.run(answer).executeUc(daobis);				
			} catch(PizzaException pizzaException) {
				
			}
		}
	}
}