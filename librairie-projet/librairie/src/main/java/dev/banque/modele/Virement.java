package dev.banque.modele;

import javax.persistence.*;

@Entity
@Table( name="virement")
@DiscriminatorValue("V")
public class Virement extends Operation {
	
	@Column(name = "beneficiaire", length = 50,unique = false)
	private String beneficiaire;

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
}
