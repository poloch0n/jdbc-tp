package dev.librairie.module;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(
   name="client"
)
public class Client {
	@Id
    private Integer id ;
	@Column(name = "NOM", length = 50, nullable = false, unique = false)
	private String nom;
	@Column(name = "PRENOM", length = 50, nullable = false, unique = false)
	private String prenom ;

	@OneToMany(mappedBy="mClient")
	private Set<Emprunt> Emprunts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Emprunt> getEmprunts() {
		return Emprunts;
	}

	public void setEmprunts(Set<Emprunt> emprunts) {
		Emprunts = emprunts;
	}


	
}