package fr.pizzeria;

import java.util.Scanner;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class FromMenu {
	
	static Scanner questionUser;
	public FromMenu() {
		questionUser = new Scanner(System.in);
	}
	
	public static Pizza getInformationPizza() {

		showText("Veuillez saisir le code :");
		String code = getCode();

		showText("Veuillez saisir le libelle (sans espace au possible) :");
		String libelle = getLibelle();
		
		showText("Veuillez saisir le prix :");
		double prix = getPrice();

		showText("Veuillez choisir une catégorie:");
		//afficher toutes les catégories
		CategoriePizza categorie = null;
		for(int i = 0;i < CategoriePizza.values().length;i++) {
			showText((i+1) +". "+CategoriePizza.values()[i].toString());
		}
		categorie = CategoriePizza.values()[getCategorie()-1];
		return new Pizza(code,libelle,prix,categorie);
	}
	
	public static String getCode() {
		String code = getStringFromMenu();
		return code;
	}

	public static String getLibelle() {
		String libelle = getStringFromMenu();
		return libelle;
	}

	public static Double getPrice() {
		Double prix = getDoubleFromMenu();
		return prix;
	}
	
	public static Integer getCategorie() {
		Integer categorie = getIntFromMenu();
		return categorie;
	}
	
	public static String getStringFromMenu() {
		try {
			//todo : attention injection ?
			return (String) questionUser.next();
		} catch(Exception e)  {
			// Gestion des cas o� l'utilisateur ne rentre pas un text au bon format
			return "";
		}
	}

	public static Double getDoubleFromMenu() {
		String input = questionUser.next();
	    double number = 0;
	    try {
	        number = Double.parseDouble(input);
	        return number;
	    } catch (Exception e) {
	        return -1.00;
	    }
	}
	
	public static int getIntFromMenu() {
		String input = questionUser.next();
        int number = 0;
        try {
            number = Integer.parseInt(input);
            return number;
        } catch (Exception e) {
            return 0;
        }   
	}

	private static void showText(String texte) {
		System.out.println(texte);
	}
}
