
package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import contracts.TradeRemote;
import ideca.entity.EnumEtatTrade;
import ideca.entity.EquitySymbol;
import ideca.entity.Trade;
import ideca.entity.TradePk;
import ideca.entity.Trader;

@Stateless
@LocalBean
public class TradeService implements TradeRemote {
	@PersistenceContext(unitName = "Ideca_intelixx-ejb")
	EntityManager entityManager;



	public TradeService() {
			}
	@Override
	public void demandTrade(Trade trade) {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		if (verifExiste(trade.getTrader_source().getId(), trade.getTrader_destination().getId()))
		{TradePk tradePk=new TradePk(trade.getTrader_source().getId(), trade.getTrader_destination().getId()); 
		trade.setTradepk(tradePk);
		trade.setDateDemand(sqlDate);
		entityManager.persist(trade) ; 
		System.out.println("magic system");
		}
		else {
			System.out.println("ça ne marche pas");
		}
	}
	
	@Override
	public Trade findByIdSourceIdDestination(int idSource, int idDestination) {
		TradePk tradePk=new TradePk(idSource, idDestination); 
		Trade trade=entityManager.find(Trade.class,tradePk);
		
		return trade;
	}
	public boolean verifExiste(int idSource, int idDestination) {
		TradePk tradePk=new TradePk(idSource, idDestination); 
		Trade trade=entityManager.find(Trade.class,tradePk);
		
		if (trade == null) {
			return true;
		}
		return false;
	}
	@Override
	public void validateTrade(Trade trade) {
		Trade tradeVerif=findByIdSourceIdDestination(trade.getTrader_source().getId(),trade.getTrader_destination().getId());
		tradeVerif.setEtat(EnumEtatTrade.ACCEPTED);
		entityManager.merge(tradeVerif);
		
	}
	@Override
	public void refuserTrade(Trade trade) {
		Trade tradeVerif=findByIdSourceIdDestination(trade.getTrader_source().getId(),trade.getTrader_destination().getId());
		tradeVerif.setEtat(EnumEtatTrade.REFUSED);
		entityManager.merge(tradeVerif);
	}
	@Override
	public void settleTrade(Trade trade) {
		Trade tradeVerif=findByIdSourceIdDestination(trade.getTrader_source().getId(),trade.getTrader_destination().getId());
		tradeVerif.setEtat(EnumEtatTrade.SETTLED);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		tradeVerif.setDateSettle(sqlDate);
		entityManager.merge(tradeVerif);
	}
	@Override
	public void unsettleTrade(Trade trade) {
		Trade tradeVerif=findByIdSourceIdDestination(trade.getTrader_source().getId(),trade.getTrader_destination().getId());
		tradeVerif.setEtat(EnumEtatTrade.UNSETTLED);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		tradeVerif.setDateSettle(sqlDate);
		entityManager.merge(tradeVerif);
	}
	@Override
	public List<Trade> getAllTradesByIdSource(Trader trader) {
		TypedQuery<Trade> query = entityManager.createQuery("SELECT c FROM Trade c where c.trader_source=:x", Trade.class);
		query.setParameter("x",trader );
		List<Trade> results = query.getResultList();

		return results;		
	}
	@Override
	public List<Trade> getAllTradesByBackOfficeUser(int id) {
		TypedQuery<Trade> query = entityManager.createQuery("SELECT c FROM Trade c JOIN c.trader_source u  where u.backOfficeUser.id="+id, Trade.class);
	//	query.setParameter("x",trader );
		List<Trade> results = query.getResultList();

		return results;		
	}
	@Override
	public List<Trade> getAllTradesByTraderDestination(int id) {
		TypedQuery<Trade> query = entityManager.createQuery("SELECT c FROM Trade c where c.trader_destination.id=:x and c.etat='ACCEPTED' or c.etat='SETTLED' or c.etat='UNSETTLED' ", Trade.class);
			query.setParameter("x",id );
			List<Trade> results = query.getResultList();

			return results;	
	}	
	
	public List<Trade> getAllTrades() {
		TypedQuery<Trade> query = entityManager.createQuery("SELECT c FROM Trade c  ", Trade.class);
			
			List<Trade> results = query.getResultList();

			return results;	
	}	
	
	
}
