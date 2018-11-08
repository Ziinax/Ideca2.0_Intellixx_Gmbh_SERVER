package ideca.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FXMarketData implements Serializable {

	private Integer id;
	private String symbole;
	private Double volatility;
	private BigDecimal price;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}

	public Double getVolatility() {
		return volatility;
	}

	public void setVolatility(Double volatility) {
		this.volatility = volatility;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "FXMarketData [id=" + id + ", symbole=" + symbole + ", volatility=" + volatility + ", price=" + price
				+ "]";
	}

	public FXMarketData(String symbole, Double volatility, BigDecimal price) {

		this.symbole = symbole;
		this.volatility = volatility;
		this.price = price;
	}

	public FXMarketData() {

	}

}
