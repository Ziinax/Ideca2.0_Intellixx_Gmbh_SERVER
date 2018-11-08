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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class EquityOptions implements Serializable {
	private Trader trader;
	private Trade trade;
	private Integer Id;

	private String name;

	private String type;
	// private MarketData marketData;
	private double price;

	private int numberOfShares;

	private double CurrentStockPrice;

	private double OptionStrikePrice;

	private double TimeUntilExercise;

	private double RiskFreeInterestRate;

	private double volatility;

	public EquityOptions() {
	}

	@Override
	public String toString() {
		return "EquityOptions [Id=" + Id + ", name=" + name + ", type=" + type + ", price=" + price
				+ ", numberOfShares=" + numberOfShares + ", CurrentStockPrice=" + CurrentStockPrice
				+ ", OptionStrikePrice=" + OptionStrikePrice + ", TimeUntilExercise=" + TimeUntilExercise
				+ ", RiskFreeInterestRate=" + RiskFreeInterestRate + ", volatility=" + volatility + "]";
	}

	public EquityOptions(String name, String type, double price, int numberOfShares, double currentStockPrice,
			double optionStrikePrice, double timeUntilExercise, double riskFreeInterestRate, double volatility) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.numberOfShares = numberOfShares;
		CurrentStockPrice = currentStockPrice;
		OptionStrikePrice = optionStrikePrice;
		TimeUntilExercise = timeUntilExercise;
		RiskFreeInterestRate = riskFreeInterestRate;
		this.volatility = volatility;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	@OneToOne(mappedBy="fxOption")
	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(int numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public double getCurrentStockPrice() {
		return CurrentStockPrice;
	}

	public void setCurrentStockPrice(double currentStockPrice) {
		CurrentStockPrice = currentStockPrice;
	}

	public double getOptionStrikePrice() {
		return OptionStrikePrice;
	}

	public void setOptionStrikePrice(double optionStrikePrice) {
		OptionStrikePrice = optionStrikePrice;
	}

	public double getTimeUntilExercise() {
		return TimeUntilExercise;
	}

	public void setTimeUntilExercise(double timeUntilExercise) {
		TimeUntilExercise = timeUntilExercise;
	}

	public double getRiskFreeInterestRate() {
		return RiskFreeInterestRate;
	}

	public void setRiskFreeInterestRate(double riskFreeInterestRate) {
		RiskFreeInterestRate = riskFreeInterestRate;
	}

	public double getVolatility() {
		return volatility;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquityOptions other = (EquityOptions) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
