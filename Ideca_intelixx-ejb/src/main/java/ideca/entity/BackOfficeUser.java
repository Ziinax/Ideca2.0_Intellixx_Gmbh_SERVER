package ideca.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class BackOfficeUser extends Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codeBk;
	private List<Trader> listTrader;

	// constructeurs
	public BackOfficeUser() {
		super();
	}

	

	// toString

	@Override
	public String toString() {
		return "BackOfficeUser [codeBk=" + codeBk + ", getId()=" + getId() + ", getType()=" + getType() + ", getPwd()="
				+ getPwd() + ", getFirstName()=" + getFirstName() + ", getLasttName()=" + getLasttName()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getEmail()=" + getEmail() + ", getCountry()="
				+ getCountry() + ", getCin()=" + getCin() + ", getGender()=" + getGender() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getNationalite()=" + getNationalite() + ", getAdresse()=" + getAdresse() + "]";
	}

	// getters and setters
	public String getCodeBk() {
		return codeBk;
	}

	public void setCodeBk(String codeBk) {
		this.codeBk = codeBk;
	}

	@OneToMany(mappedBy = "backOfficeUser", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE,
			CascadeType.REFRESH })
	public List<Trader> getListTrader() {
		return listTrader;
	}

	public void setListTrader(List<Trader> listTrader) {
		this.listTrader = listTrader;
	}
}
