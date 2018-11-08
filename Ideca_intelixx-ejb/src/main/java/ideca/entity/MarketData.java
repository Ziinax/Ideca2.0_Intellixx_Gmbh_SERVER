package ideca.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MarketData implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String symbole;
	private Double volatility;
	private BigDecimal open;
	/*private List<FXOptions> listFXoptions;
	private List<EquityOptions> listEquityOption;
*/
	public MarketData() {
	}

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

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

/*	//@OneToMany(mappedBy="marketData")
	public List<FXOptions> getListFXoptions() {
		return listFXoptions;
	}

	public void setListFXoptions(List<FXOptions> listFXoptions) {
		this.listFXoptions = listFXoptions;
	}

	//@OneToMany(mappedBy="marketData")
	public List<EquityOptions> getListEquityOption() {
		return listEquityOption;
	}

	public void setListEquityOption(List<EquityOptions> listEquityOption) {
		this.listEquityOption = listEquityOption;
	}
*/
	@Override
	public String toString() {
		return symbole +  " -->" + open +" $" ;
	}

	public MarketData(String symbole, Double volatility, BigDecimal open) {

		this.symbole = symbole;
		this.volatility = volatility;
		this.open = open;
	}

}
