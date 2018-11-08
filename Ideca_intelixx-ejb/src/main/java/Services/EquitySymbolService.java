
package Services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import contracts.EquitySymbolRemote;
import ideca.entity.EquityOptions;
import ideca.entity.EquitySymbol;
import ideca.entity.FXMarketData;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Singleton
public class EquitySymbolService implements EquitySymbolRemote {
	@PersistenceContext
	private EntityManager entitymanager;

	@Override
	public void ajouterEquitySymbol(EquitySymbol equitySymbol) {
		// TODO Auto-generated method stub

	}

	@Override

	//@Schedule(minute = "*", hour = "*")
	public void fillEquitySymbolTable()  {
		
			if (verifyEmpty()) {
				File f = new File("..\\docs\\Yahoo-Ticker-Symbols-Jan 2016-new.xls");
				Workbook workbook;

			try {
				workbook = Workbook.getWorkbook(f);
				Sheet sheet = workbook.getSheet(0);

				List<EquitySymbol> listEquitySymbol = new ArrayList<>();

				for (int i = 1; (i < sheet.getRows() && i < 100); i++) {
					EquitySymbol equitySymbol = new EquitySymbol();

					equitySymbol.setSymbol(sheet.getCell(0, i).getContents());
					equitySymbol.setName(sheet.getCell(1, i).getContents());
					equitySymbol.setExchange(sheet.getCell(2, i).getContents());
					equitySymbol.setCountry(sheet.getCell(3, i).getContents());
					equitySymbol.setCategoryName(sheet.getCell(4, i).getContents());
					equitySymbol.setCategoryNumber(sheet.getCell(5, i).getContents());
					equitySymbol.setStatus(0);
					listEquitySymbol.add(equitySymbol);

					}
					for (int i = 0; i < listEquitySymbol.size(); i++) {
						entitymanager.persist(listEquitySymbol.get(i));
					}
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
		}


	public boolean verifyEmpty() {
		Query query = entitymanager.createQuery("Select COUNT(c) FROM EquitySymbol c", Long.class);
		long nombreLigne = (long) query.getSingleResult();

		if (nombreLigne == 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<EquitySymbol> getAllSymbols() {
		TypedQuery<EquitySymbol> query = entitymanager.createQuery("SELECT c FROM EquitySymbol c", EquitySymbol.class);
		List<EquitySymbol> results = query.getResultList();

		return results;
	}

	@Override
	public List<EquitySymbol> reseachByName(String name) {
		TypedQuery<EquitySymbol> query = entitymanager
				.createQuery("SELECT c FROM EquitySymbol c where c.Name LIKE :Name", EquitySymbol.class);
		List<EquitySymbol> results = query.setParameter("Name", "%" + name + "%").getResultList();
		return results;
	}

	@Override
	public void addToMarketData(String symbol) throws IOException {
		Stock stock;
		stock = YahooFinance.get(symbol);
		if(stock!=null){
		entitymanager.createQuery("Update EquitySymbol set Status=1  where Symbol='" + symbol + "'").executeUpdate();
	}
	}

	@Override
	public void removeFromMarketData(String symbol) {
		entitymanager.createQuery("Update EquitySymbol set Status=0  where Symbol='" + symbol + "'").executeUpdate();

	}

	

}
