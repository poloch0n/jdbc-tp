package fr.pizzeria.service;

import fr.pizzeria.ShowMenu;
import fr.pizzeria.model.PizzaMemDaoBis;

public class DefaultService extends MenuService{

	public DefaultService() {
		// TODO Auto-generated constructor stub
	}

	public void executeUc(PizzaMemDaoBis daobis) {
		ShowMenu.showText("Veuillez choisir une option valide");
	}
}
