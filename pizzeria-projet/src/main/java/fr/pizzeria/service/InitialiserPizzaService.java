package fr.pizzeria.service;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.PizzaMemDaoBis;

public class InitialiserPizzaService extends MenuService {
	public void executeUc(PizzaMemDaoBis daobis) throws SavePizzaException{
		showText("Initialisation de la base de donnée en cours");
		daobis.initialiseBddPizza();
		showText("Base de donnée initialisé");
	}
}
