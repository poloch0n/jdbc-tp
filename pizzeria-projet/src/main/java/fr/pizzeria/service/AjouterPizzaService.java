package fr.pizzeria.service;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDaoBis;
import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.SavePizzaException;

public class AjouterPizzaService extends MenuService{
	public void executeUc(PizzaMemDaoBis daobis) throws SavePizzaException{
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = FromMenu.getInformationPizza();
		String message = daobis.checkInformationPizza(newPizza,true,"add");

		if(!message.equals("")) {
			throw new SavePizzaException(message);
		}
		daobis.addPizza(newPizza);
	}
}
