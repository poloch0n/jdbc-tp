package dev.banque.modele;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table( name="operation")
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "date", length = 50,unique = false)
	private LocalDateTime  date;
	@Column(name = "montant", length = 50,unique = false)
	private Double montant;
	@Column(name = "motif", length = 50,unique = false)
	private String motif;
	

	@ManyToOne
	@JoinColumn(name="Compte_id")
	private Compte compte;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
}