package ideca.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EquitySymbol implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Symbol;
	private String Name;
	private String Exchange;
	private String Country;
	private String CategoryName;
	private String CategoryNumber;
	private int Status ; 

	public EquitySymbol() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getSymbol() {
		return Symbol;
	}

	

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getExchange() {
		return Exchange;
	}

	public void setExchange(String exchange) {
		Exchange = exchange;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public String getCategoryNumber() {
		return CategoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		CategoryNumber = categoryNumber;
	}

	@Override
	public String toString() {
		return "EquitySymbol [Symbol=" + Symbol + ", Name=" + Name + ", Exchange=" + Exchange + ", Country=" + Country
				+ ", CategoryName=" + CategoryName + ", CategoryNumber=" + CategoryNumber + "]";
	}

	public EquitySymbol(String symbol, String name, String exchange, String country, String categoryName,
			String categoryNumber) {
		super();
		Symbol = symbol;
		Name = name;
		Exchange = exchange;
		Country = country;
		CategoryName = categoryName;
		CategoryNumber = categoryNumber;
	}

}
