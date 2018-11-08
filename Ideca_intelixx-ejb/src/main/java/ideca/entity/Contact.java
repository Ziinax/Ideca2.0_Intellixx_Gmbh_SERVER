package ideca.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;

@Entity
public class Contact {
	
private int id ;
private String subject;
private String descreption;
private Trader trader;
private String status;
private Date dateOfContact;

public Contact() {
	super();
}

public Contact(int id,String subject, String descreption, String status) {
	super();
	this.id=id;
	this.subject = subject;
	this.descreption = descreption;
	
	this.status = status;
}

@Id
@GeneratedValue
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getDescreption() {
	return descreption;
}
public void setDescreption(String descreption) {
	this.descreption = descreption;
}
@ManyToOne
public Trader getTrader() {
	return trader;
}
public void setTrader(Trader trader) {
	this.trader = trader;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Temporal(TemporalType.TIMESTAMP)
public Date getDateOfContact() {
	return dateOfContact;
}

public void setDateOfContact(Date dateOfContact) {
	this.dateOfContact = dateOfContact;
}



}
