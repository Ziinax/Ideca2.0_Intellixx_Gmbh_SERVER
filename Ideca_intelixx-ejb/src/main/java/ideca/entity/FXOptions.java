package ideca.entity;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FXOptions implements Serializable {
	private Trade trade;
	private Trader trader;
	private Integer Id;
	// private MarketData marketData;
	private String name;
	private double SpotRate;

	private double StrikePrice;

	private double InterestRateCurrency1;

	private double InterestRateCurrency2;

	private double Volatility;

	private String type;

	private double price;

	private double TimeToMaturity;

	private int Nominal;

	public FXOptions(String name, double spotRate, double strikePrice, double interestRateCurrency1,
			double interestRateCurrency2, double volatility, String type, double price, double timeToMaturity,
			int nominal) {

		this.name = name;
		SpotRate = spotRate;
		StrikePrice = strikePrice;
		InterestRateCurrency1 = interestRateCurrency1;
		InterestRateCurrency2 = interestRateCurrency2;
		Volatility = volatility;
		this.type = type;
		this.price = price;
		TimeToMaturity = timeToMaturity;
		Nominal = nominal;
	}

	public FXOptions() {
	}

	@Override
	public String toString() {
		return "FXOptions [Id=" + Id + ", name=" + name + ", SpotRate=" + SpotRate + ", StrikePrice=" + StrikePrice
				+ ", InterestRateCurrency1=" + InterestRateCurrency1 + ", InterestRateCurrency2="
				+ InterestRateCurrency2 + ", Volatility=" + Volatility + ", type=" + type + ", price=" + price
				+ ", TimeToMaturity=" + TimeToMaturity + ", Nominal=" + Nominal + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}
	@OneToOne(mappedBy="equityOption")
	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpotRate() {
		return SpotRate;
	}

	public void setSpotRate(double spotRate) {
		SpotRate = spotRate;
	}

	public double getStrikePrice() {
		return StrikePrice;
	}

	public void setStrikePrice(double strikePrice) {
		StrikePrice = strikePrice;
	}

	public double getInterestRateCurrency1() {
		return InterestRateCurrency1;
	}

	public void setInterestRateCurrency1(double interestRateCurrency1) {
		InterestRateCurrency1 = interestRateCurrency1;
	}

	public double getInterestRateCurrency2() {
		return InterestRateCurrency2;
	}

	public void setInterestRateCurrency2(double interestRateCurrency2) {
		InterestRateCurrency2 = interestRateCurrency2;
	}

	public double getVolatility() {
		return Volatility;
	}

	public void setVolatility(double volatility) {
		Volatility = volatility;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTimeToMaturity() {
		return TimeToMaturity;
	}

	public void setTimeToMaturity(double timeToMaturity) {
		TimeToMaturity = timeToMaturity;
	}

	public int getNominal() {
		return Nominal;
	}

	public void setNominal(int nominal) {
		Nominal = nominal;
	}
	/*
	 * @ManyToOne public MarketData getMarketData() { return marketData; }
	 * 
	 * public void setMarketData(MarketData marketData) { this.marketData =
	 * marketData; }
	 */
@ManyToOne
	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

}
