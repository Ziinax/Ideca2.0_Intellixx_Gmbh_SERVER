package contracts;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import ideca.entity.EquityOptions;
import ideca.entity.EquitySymbol;


@Remote
public interface EquitySymbolRemote {
	public void ajouterEquitySymbol(EquitySymbol equitySymbol);
	public void fillEquitySymbolTable()  ; 
	public List<EquitySymbol> getAllSymbols();
	public List<EquitySymbol> reseachByName(String name);
	public void addToMarketData(String symbol) throws IOException ;
	public void removeFromMarketData(String symbol); 
	
}
