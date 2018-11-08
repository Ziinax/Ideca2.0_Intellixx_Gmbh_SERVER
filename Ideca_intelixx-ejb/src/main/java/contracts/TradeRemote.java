package contracts;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import ideca.entity.EnumEtatTrade;
import ideca.entity.EquitySymbol;
import ideca.entity.Trade;
import ideca.entity.Trader;

@Remote
public interface TradeRemote {
	public void demandTrade(Trade trade) ; 
	public void validateTrade(Trade trade) ; 
	public void refuserTrade(Trade trade) ;
	public void settleTrade(Trade trade) ; 
	public void unsettleTrade(Trade trade) ; 
	public Trade findByIdSourceIdDestination(int idSource,int idDestination) ;
	public List<Trade> getAllTradesByIdSource(Trader trader) ;
	public List<Trade> getAllTradesByBackOfficeUser(int id) ;
	public List<Trade> getAllTradesByTraderDestination(int id) ;

}
