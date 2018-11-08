package contracts;

import java.math.BigDecimal;

import javax.ejb.Remote;

@Remote
public interface FXMarketDataRemote {
	public BigDecimal GetOpen(String symbole);
}
