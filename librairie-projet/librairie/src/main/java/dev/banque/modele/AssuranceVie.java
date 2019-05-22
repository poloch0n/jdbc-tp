package dev.banque.modele;

import javax.persistence.*;

@Entity
@DiscriminatorValue("AV")
public class AssuranceVie extends Compte{
	
	@Column(name = "taux", length = 50,unique = false)
	private double taux;
	@Column(name = "dateFin",unique = false)
	private  java.sql.Date dateFin;
	
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public java.sql.Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(java.sql.Date dateFin) {
		this.dateFin = dateFin;
	}
	
}
