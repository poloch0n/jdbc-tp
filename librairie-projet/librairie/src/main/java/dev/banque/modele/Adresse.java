package dev.banque.modele;

import javax.persistence.*;

@Embeddable
public class Adresse {

	@Column(name = "numero", length = 50,unique = false)
	private Integer numero;
	@Column(name = "rue", length = 50,unique = false)
	private String rue;
	@Column(name = "codePostal", length = 50,unique = false)
	private Integer codePostal;
	@Column(name = "ville", length = 50,unique = false)
	private String ville;

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
