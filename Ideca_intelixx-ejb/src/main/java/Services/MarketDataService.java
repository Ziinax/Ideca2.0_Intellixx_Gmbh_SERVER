
package Services;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.LocalBean;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ParsingVolatility.Equity;
import ParsingVolatility.YahooFinanceEquityDownloader;
import contracts.MarketDataLocal;
import contracts.MarketDataRemote;
import ideca.entity.FXOptions;
import ideca.entity.MarketData;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Stateless
@LocalBean
public class MarketDataService implements MarketDataRemote,MarketDataLocal {
	@PersistenceContext
	EntityManager entitymanager;

	@Override
//@Schedule(minute = "*", hour = "*")
	public void addMarketData() {
		Stock stock;
		EquitySymbolService equitySymbolService = new EquitySymbolService();
		List<String> listSymbols = new ArrayList();

		// List<String> listSymbols = new ArrayList() ;

		listSymbols.add("AAPL");
		listSymbols.add("IBM");
		listSymbols.add("FB");
		listSymbols.add("DANOY");
		listSymbols.add("MSFT");
		//listSymbols.addAll( getAddedSymbols());
		// String[] tableuxSymbol = { "AAPL", "IBM", "FB", "DANOY", "MSFT" };
		YahooFinanceEquityDownloader y = new YahooFinanceEquityDownloader();
		{
			try {
				for (int i = 0; i < listSymbols.size(); i++) {
					stock = YahooFinance.get(listSymbols.get(i));
					BigDecimal price = stock.getQuote(true).getPrice();
					double volatility = y.MarketParser(listSymbols.get(i));

					if (findData() < i + 1) {
						MarketData marketdata = new MarketData(listSymbols.get(i), volatility, price);
						entitymanager.persist(marketdata);
						System.out.print(price);
					} else {
						UpdateData(listSymbols.get(i), volatility, price);
					}

				}

			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public MarketData findBySymbol(String symbol) {

		MarketData marketdata = null;
		try {
			marketdata = entitymanager.find(MarketData.class, symbol);
			return marketdata;
		} catch (Exception ex) {
		}
		return marketdata;
	}

	@Override
	public boolean updateMarketData(MarketData marketdata) {
		try {
			entitymanager.merge(marketdata);
			System.out.println(" updated with success");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void Trancate() {
		entitymanager.createNativeQuery("TRUNCATE TABLE MarketData").executeUpdate();

	}

	public void UpdateData(String symbol, Double volatility, BigDecimal price) {
		entitymanager.createQuery("Update MarketData set open=" + price + ", volatility=" + volatility
				+ " where symbole='" + symbol + "'").executeUpdate();

	}

	@Override
	public BigDecimal GetOpen(String symbole) {
		TypedQuery<MarketData> query = entitymanager.createQuery("SELECT c FROM MarketData c where c.symbole=:symbole",
				MarketData.class);
		// List<MarketData> results = query.getResultList();

		return query.setParameter("symbole", symbole).getSingleResult().getOpen();

	}

	@Override
	public MarketData findById(int id) {
		MarketData marketData = null;
		try {
			marketData = entitymanager.find(MarketData.class, id);
			return marketData;
		} catch (Exception ex) {
		}
		return marketData;
	}

	@Override
	public int findData() {

		TypedQuery<MarketData> query = entitymanager.createQuery("SELECT c FROM MarketData c", MarketData.class);
		List<MarketData> results = query.getResultList();
		return results.size();

	}

	@Override
	public double GetVolatility(String symbole) {
		TypedQuery<MarketData> query = entitymanager.createQuery("SELECT c FROM MarketData c where c.symbole=:symbole",
				MarketData.class);
		MarketData marketData = query.setParameter("symbole", symbole).getSingleResult();
		return marketData.getVolatility();
	}

	@Override
	public List<String> getAddedSymbols() {
		TypedQuery<String> query = entitymanager.createQuery("SELECT c.Symbol FROM EquitySymbol c where c.Status=1",
				String.class);
		List<String> results = query.getResultList();
		return results;
	}

	@Override
	public List<MarketData> getMarketData() {
		TypedQuery<MarketData> query = entitymanager.createQuery("SELECT c FROM MarketData c",
				MarketData.class);
		List<MarketData> results = query.getResultList();
		return results;
	}

}
