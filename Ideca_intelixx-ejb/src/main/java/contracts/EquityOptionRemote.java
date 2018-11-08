package contracts;


import java.util.List;

import javax.ejb.Remote;

import ideca.entity.EquityOptions;

import ideca.entity.FXOptions;


@Remote
public interface EquityOptionRemote {
	public void ajouterEquityOption(EquityOptions eo);
	public List<EquityOptions> getAllEquityOps();
	public List<EquityOptions> getEquityOpsParName(String mc);
	public EquityOptions getEquityOps(int idEO);
	public void supprimerEO(int idEO);
	public void updateEO(EquityOptions eo);
	public float pricingCallEquityOptions(EquityOptions f);
	public float pricingPutEquityOptions(EquityOptions f);
}
