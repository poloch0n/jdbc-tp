package dev.banque.modele;

import javax.persistence.*;

public class Client {

	@Id
	private Integer id;
	@Column(name = "nom",unique = false)
	private String nom;
	@Column(name = "prenom",unique = false)
	private String prenom;
	@Column(name = "dateNaissance",unique = false)
	private  java.sql.Date LocalDate;

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public java.sql.Date getLocalDate() {
		return LocalDate;
	}
	public void setLocalDate(java.sql.Date localDate) {
		LocalDate = localDate;
	}
}
