package fr.pizzeria.service;

import java.util.List;

import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDaoBis;

public class ModifierPizzaService extends MenuService {
	public void executeUc(PizzaMemDaoBis daobis) throws UpdatePizzaException{
		
		showText("Mise à jour d�une pizza");

		List<Pizza> pizzas = daobis.getPizzas();
		if(pizzas.size() < 1) {
			throw new UpdatePizzaException("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
		}
		
		showText("Veuillez choisir le code de la pizza à modifier");
		String code = FromMenu.getCode();
		//V�rification de l'existance de la pizza
		if(!daobis.checkPizzaExistFromCode(code)) {
			throw new UpdatePizzaException("test Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n");
		}
		Pizza updatedPizza = FromMenu.getInformationPizza();

		daobis.updatePizzaFromCode(code,updatedPizza);
			
	}
}
