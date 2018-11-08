package ideca.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Trade implements Serializable {
	private EnumEtatTrade etat=EnumEtatTrade.PENDING;
	private TradePk Tradepk=new TradePk();
	private Trader trader_source;
	private FXOptions fxOption;
	private Date dateDemand;
	private Date dateSettle ;
	private Trader trader_destination;
	private EquityOptions equityOption;
	
	@Override
	public String toString() {
		return "Trade [etat=" + etat + ", Tradepk=" + Tradepk + ", trader_source=" + trader_source
				+ ", trader_destination=" + trader_destination + ", equityOption=" + equityOption + ", fxOption="
				+ fxOption + ", dateDemand=" + dateDemand + ", dateSettle=" + dateSettle + "]";
	}

	
	
	public Trade(Trader trader_source, Trader trader_destination, EquityOptions equityOption) {
		super();
		this.trader_source = trader_source;
		this.trader_destination = trader_destination;
		this.equityOption = equityOption;
	}

	
	@Temporal(TemporalType.DATE)
	public Date getDateDemand() {
		return dateDemand;
	}

	public void setDateDemand(Date dateDemand) {
		this.dateDemand = dateDemand;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDateSettle() {
		return dateSettle;
	}

	public void setDateSettle(Date dateSettle) {
		this.dateSettle = dateSettle;
	}

	@OneToOne
	public EquityOptions getEquityOption() {
		return equityOption;
	}

	public void setEquityOption(EquityOptions equityOption) {
		this.equityOption = equityOption;
	}

	@OneToOne
	public FXOptions getFxOption() {
		return fxOption;
	}

	public void setFxOption(FXOptions fxOption) {
		this.fxOption = fxOption;
	}

	@EmbeddedId
	public TradePk getTradepk() {
		return Tradepk;
	}

	public void setTradepk(TradePk tradepk) {
		Tradepk = tradepk;
	}

	@ManyToOne
	@JoinColumn(name = "id_trader_source", referencedColumnName = "id", insertable = false, updatable = false)
	public Trader getTrader_source() {
		return trader_source;
	}

	public void setTrader_source(Trader trader_source) {
		this.trader_source = trader_source;
	}

	@ManyToOne
	@JoinColumn(name = "id_trader_destination", referencedColumnName = "id", insertable = false, updatable = false)
	public Trader getTrader_destination() {
		return trader_destination;
	}

	public void setTrader_destination(Trader trader_destination) {
		this.trader_destination = trader_destination;
	}

	public Trade() {

	}

	public Trade(EnumEtatTrade etat, Trader trader_source, Trader trader_destination, EquityOptions equityOption) {
		this.etat = etat;
		this.trader_source = trader_source;
		this.trader_destination = trader_destination;
		this.equityOption = equityOption;
	}
	@Column(length=255 , columnDefinition="varchar(255) DEFAULT 'PENDING'")
	@Enumerated(EnumType.STRING)
	public EnumEtatTrade getEtat() {
		return etat;
	}

	public void setEtat(EnumEtatTrade etat) {
		this.etat = etat;
	}
	

}
