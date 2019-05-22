package dev.banque.modele;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(
   name="client"
)
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nom", length = 50,unique = false)
	private String nom;
	@Column(name = "prenom", length = 50,unique = false)
	private String prenom;
	@Column(name = "dateNaissance",unique = false)
	private  java.sql.Date LocalDate;
	
	@ManyToOne
	@JoinColumn(name="Banque_id")
	private Banque banque;

	@ManyToMany(mappedBy="clients")
	private Set<Compte> comptes;
	

    @Embedded
    private Adresse adresse;
    
	public Client() {
		comptes = new HashSet<Compte>();
	}
	
	public Set<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
