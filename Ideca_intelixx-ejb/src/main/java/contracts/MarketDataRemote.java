package contracts;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import ideca.entity.MarketData;

@Remote
public interface MarketDataRemote {
	
	void addMarketData() ; 
	MarketData findBySymbol(String symbol); 
	boolean updateMarketData(MarketData marketdata) ; 
	public BigDecimal GetOpen(String symbole);
	public double GetVolatility(String symbole);
	public MarketData findById(int id);
	public int findData();
	public List<String> getAddedSymbols();
	public List<MarketData> getMarketData();
}
