package fr.pizzeria.service;

import fr.pizzeria.ShowMenu;
import fr.pizzeria.model.PizzaMemDaoBis;

public class ExitService extends MenuService{

	public ExitService() {
		// TODO Auto-generated constructor stub
	}
	
	public void executeUc(PizzaMemDaoBis daobis) {
		ShowMenu.showText("Aurevoir☹ ");
		System.exit(0);
	}
}
