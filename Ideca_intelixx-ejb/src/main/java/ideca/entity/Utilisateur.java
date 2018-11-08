package ideca.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Utilisateur implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String country;
	private String pwd;
	private String firstName;
	private String lasttName;
	private String cin;
	private Date dateOfBirth;
	private String email;
	private String gender;
	private String type;
	private String phoneNumber;
	private String nationalite;
	private String adresse;
	private Quiz quiz;

	public Utilisateur() {
		super();

	}

	public Utilisateur(int id, String country, String pwd, String firstName, String lasttName, String cin, String email,
			String nationalite, String adresse) {
		super();
		this.id = id;
		this.country = country;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.cin = cin;
		this.email = email;
		this.nationalite = nationalite;
		this.adresse = adresse;
	}

	// ToString
	@Override
	public String toString() {
		return "Utilisateur [country=" + country + ", firstName=" + firstName + ", lasttName=" + lasttName + ", cin="
				+ cin + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", gender=" + gender + ", type=" + type
				+ ", phoneNumber=" + phoneNumber + ", nationalite=" + nationalite + ", adresse=" + adresse + "]";
	}

	// getters and setters

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasttName() {
		return lasttName;
	}

	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}

	@Temporal(TemporalType.DATE)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@OneToOne
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
