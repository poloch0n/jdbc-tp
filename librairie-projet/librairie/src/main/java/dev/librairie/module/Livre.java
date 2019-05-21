package dev.librairie.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(
   name="livre"
)
public class Livre {
	@Id
    private Integer id ;
	@Column(name = "TITRE", length = 255, nullable = false, unique = false)
	private String titre;
	@Column(name = "AUTEUR", length = 50, nullable = false, unique = false)
	private String auteur ;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
}