package dev.librairie.module;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(
   name="emprunt"
)
public class Emprunt {
	@Id
    private Integer id ;
	@Column(name = "DATE_DEBUT",unique = false)
	private java.sql.Date date_debut;
	@Column(name = "DATE_FIN",unique = false)
	private java.sql.Date  date_fin ;	
	@Column(name = "DELAI", length = 10,unique = false)
	private Integer delai;
	/*@Column(name = "ID_CLIENT")
	private Integer id_client;*/
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT")
	private Client mClient;

	@ManyToMany(mappedBy = "emprunts")
    public Set<Livre> livres;

	public Emprunt(){
		livres = new HashSet<Livre>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.sql.Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(java.sql.Date date_debut) {
		this.date_debut = date_debut;
	}

	public java.sql.Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(java.sql.Date date_fin) {
		this.date_fin = date_fin;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

/*	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}*/

	public Client getmClient() {
		return mClient;
	}

	public void setmClient(Client mClient) {
		this.mClient = mClient;
	}

	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}
	
	
}