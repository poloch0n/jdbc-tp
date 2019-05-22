package dev.banque.modele;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table( name="banque")
public class Banque {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nom", length = 50,unique = false)
	private String nom;
	

	@OneToMany(mappedBy="banque")
	private Set<Client> clients;
	
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

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Banque() {
		clients = new HashSet<Client>();
	}
}
