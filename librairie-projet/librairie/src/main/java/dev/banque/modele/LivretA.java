
package dev.banque.modele;

import javax.persistence.*;

@Entity
@DiscriminatorValue("LA")
public class LivretA extends Compte {

	@Column(name = "taux", length = 50,unique = false)
	private double taux;

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
}