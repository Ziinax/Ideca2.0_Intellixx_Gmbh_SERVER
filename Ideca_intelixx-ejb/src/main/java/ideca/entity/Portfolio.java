package ideca.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Portfolio implements Serializable {
	private Integer id;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Portfolio(Integer id) {
		super();
		this.id = id;
	}

	public Portfolio() {
		super();
	}

}
