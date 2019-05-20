package fr.pizzeria.service;

import java.util.List;

import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.Pizza;
//import fr.pizzeria.model.PizzaMemDao;
import fr.pizzeria.model.PizzaMemDaoBis;

public class SupprimerPizzaService extends MenuService{

	public void executeUc(PizzaMemDaoBis daobis) throws DeletePizzaException {
		showText("Suppression d�une pizza");

		List<Pizza> pizzas = daobis.getPizzas();
		if(pizzas.size() < 1) {
			throw new DeletePizzaException("Vous n'avez pas encore ajoutez de pizza, prenez l'option 2");
		}
		showText("Veuillez choisir le code de la pizza � supprimer");

		String code = FromMenu.getCode();
		//V�rification de l'existance de la pizza
		if(!daobis.checkPizzaExistFromCode(code)) {
			throw new DeletePizzaException("Le code saisi semble ne correspondre a aucune pizza, pouvez vous r�essayer ?\r\n\"");
		}

		daobis.deletePizzaFromCode(code);
		// dao.deletePizza(code);
		showText("what is done can't be undone. you won't see him again");
	
	}
}
