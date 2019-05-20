package fr.pizzeria.service;

import fr.pizzeria.ShowMenu;
import fr.pizzeria.model.PizzaMemDao;

public class ExitService extends MenuService{

	public ExitService() {
		// TODO Auto-generated constructor stub
	}
	
	void executeUc(PizzaMemDao dao) {
		ShowMenu.showText("Aurevoir☹ ");
		System.exit(0);
	}
}
