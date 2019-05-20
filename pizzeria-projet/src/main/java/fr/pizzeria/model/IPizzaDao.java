package fr.pizzeria.model;
import java.util.List;

public interface IPizzaDao {
	List<Pizza> getPizzas();
	void addPizza(Pizza pizza);
	void updatePizzaFromCode(String codePizza, Pizza pizza);
	void deletePizzaFromCode(String codePizza);
	Pizza getPizzaFromCode(String codePizza);
	boolean checkPizzaExistFromCode(String codePizza);
}
