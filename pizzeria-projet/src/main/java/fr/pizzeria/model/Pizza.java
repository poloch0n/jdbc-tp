package fr.pizzeria.model;

public class Pizza {
	public int id;
	public String code;
	public String libelle;
	public double prix;
	private int compteur = 0;
	public CategoriePizza categorie;
	
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie){
		compteur++;
		this.id = compteur;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	public Pizza(Integer id,String code, String libelle, double prix, CategoriePizza categorie){
		//to think doublons
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}
}
