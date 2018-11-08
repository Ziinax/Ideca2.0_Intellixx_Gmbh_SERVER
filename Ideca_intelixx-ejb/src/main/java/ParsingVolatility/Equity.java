package ParsingVolatility;

import java.util.ArrayList;
import java.util.List;

public class Equity {

	private String symbol;
	private Double open;
	private Double close;
	private String date;

	public Equity() 
{super();

	}

	

	public Equity(String symbol, Double open, Double close, String date) {
		super();
		this.symbol = symbol;
		this.open = open;
		this.close = close;
		this.date = date;
	}



	@Override
	public String toString() {
		return "Equity [Symbol=" + symbol + ", Open=" + open + ", Close=" + close + ", Date=" + date + "]";
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String today) {
		this.date = today;
	}

	public static double calculVolatility(List<Equity> lequity) {
		
		List<Double> listReturns = new ArrayList<Double>();
		List<Double> squaredlistReturns = new ArrayList<Double>();
		double dailyreturns;
		double averageReturns = 0;
		double historicalVolatility;
		double modifReturns;
		double squaredReturns;
		double squaredvolatility = 0;
		double moysquaredvolatility;
		double volatility;
		for (int i = 0; i < 252; i++) {
			dailyreturns = Math.log(lequity.get(i + 1).getClose() / lequity.get(i).getClose());
			listReturns.add(dailyreturns);
		}
		for (int i = 0; i < listReturns.size(); i++) {
			averageReturns = averageReturns + listReturns.get(i);
		}

		averageReturns = averageReturns / listReturns.size();

		for (int i = 0; i < listReturns.size(); i++) {
			modifReturns = listReturns.get(i) - averageReturns;
			squaredReturns = Math.pow(modifReturns, 2);
			squaredlistReturns.add(squaredReturns);

		}
		for (int i = 0; i < squaredlistReturns.size(); i++) {
			squaredvolatility = squaredvolatility + squaredlistReturns.get(i);
		}
		moysquaredvolatility = squaredvolatility / (squaredlistReturns.size() - 1);

		volatility = Math.sqrt(moysquaredvolatility*252);
		return volatility;

	}

}
