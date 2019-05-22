package dev.banque.modele;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table( name="compte")
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "numero", length = 50,unique = false)
	private Integer numero;
	@Column(name = "solde", length = 50,unique = false)
	private String solde;

	@ManyToMany
	@JoinTable(
			name="COMPTE_CLIENT",
			joinColumns= @JoinColumn(name="ID_COMPTE", referencedColumnName="ID"),
			inverseJoinColumns= @JoinColumn(name="ID_CLIENT", referencedColumnName="ID")
	)
	private Set<Client> clients;
	

	@OneToMany(mappedBy="compte")
	private Set<Operation> operations;
	
	public Compte() {
		clients = new HashSet<Client>();
		operations = new HashSet<Operation>();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public String getSolde() {
		return solde;
	}


	public void setSolde(String solde) {
		this.solde = solde;
	}


	public Set<Client> getClients() {
		return clients;
	}


	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}


	public Set<Operation> getOperations() {
		return operations;
	}


	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

}
