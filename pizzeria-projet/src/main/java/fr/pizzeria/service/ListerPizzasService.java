package fr.pizzeria.service;

import java.util.List;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDaoBis;

public class ListerPizzasService extends MenuService{
	public void executeUc(PizzaMemDaoBis daobis) {
		List<Pizza> pizzas = daobis.getPizzas();
		if(pizzas.size() < 1) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		showText("Liste des pizzas");

		for(Pizza pizza: pizzas) {
			String text = pizza.code + " -> " + pizza.libelle + " (" + pizza.prix + " €) ";
			showText(text);
		}
	}
}
