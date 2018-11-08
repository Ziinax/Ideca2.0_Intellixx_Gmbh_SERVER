package ideca.entity;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trader extends Utilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnumGrade grade;
	private String etat;
	private Double amount;
	private BackOfficeUser backOfficeUser;
	private List<FXOptions> listOptions;
	private List<EquityOptions> listEquitys;
	private List<Contact>listContacts;

	// Constructeurs
	public Trader() {
	super();
	}
	
	








	
	

	// ToString

	public Trader(EnumGrade grade) {
		super();
		this.grade = grade;
	}



	public Trader(int id, String country, String pwd, String firstName, String lasttName, String cin, String email,
			String nationalite, String adresse) {
		super(id, country, pwd, firstName, lasttName, cin, email, nationalite, adresse);

	}











	@Override
	public String toString() {
		return getFirstName() + " " + getLasttName()+" [" + grade + "]";
	}

	// getters and setters
	@ManyToOne
	public BackOfficeUser getBackOfficeUser() {
		return backOfficeUser;
	}

	public void setBackOfficeUser(BackOfficeUser backOfficeUser) {
		this.backOfficeUser = backOfficeUser;
	}

	@Enumerated(EnumType.STRING)
	public EnumGrade getGrade() {
		return grade;
	}

	public void setGrade(EnumGrade grade) {
		this.grade = grade;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@OneToMany(mappedBy = "trader")
	public List<FXOptions> getListOptions() {
		return listOptions;
	}

	public void setListOptions(List<FXOptions> listOptions) {
		this.listOptions = listOptions;
	}

	@OneToMany(mappedBy = "trader")
	public List<EquityOptions> getListEquitys() {
		return listEquitys;
	}

	public void setListEquitys(List<EquityOptions> listEquitys) {
		this.listEquitys = listEquitys;
	}
@OneToMany(mappedBy="trader")

	public List<Contact> getListContacts() {
		return listContacts;
	}

	public void setListContacts(List<Contact> listContacts) {
		this.listContacts = listContacts;
	}

}
