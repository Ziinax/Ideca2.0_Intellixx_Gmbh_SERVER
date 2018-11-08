
package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import contracts.EquityOptionRemote;
import ideca.entity.EquityOptions;
import ideca.entity.FXOptions;
import ideca.entity.MarketData;

@Stateless
@LocalBean
public class EquityOptionService implements EquityOptionRemote {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void ajouterEquityOption(EquityOptions eo) {
		em.persist(eo);

	}

	@Override
	public List<EquityOptions> getAllEquityOps() {
		TypedQuery<EquityOptions> query = em.createQuery("SELECT c FROM EquityOptions c", EquityOptions.class);
		List<EquityOptions> results = query.getResultList();

		return results;
	}

	@Override
	public List<EquityOptions> getEquityOpsParName(String mc) {
		Query req = em.createQuery("select eo from equityoptions eo where eo.name like :x");
		req.setParameter("x", "%" + mc + "%");
		return req.getResultList();
	}

	@Override
	public EquityOptions getEquityOps(int idEO) {
		EquityOptions eo = em.find(EquityOptions.class, idEO);
		return eo;
	}

	@Override
	public void supprimerEO(int idEO) {
		EquityOptions eo = em.find(EquityOptions.class, idEO);

		em.remove(eo);

	}

	@Override
	public void updateEO(EquityOptions eo) {
		EquityOptions eo1 = em.find(EquityOptions.class, eo.getId());
		eo1.setName(eo.getName());
		em.persist(eo1);

	}

	public double CumulNormalDis(double x) {
		int neg = (x < 0d) ? 1 : 0;
		if (neg == 1)
			x *= -1d;

		double k = (1d / (1d + 0.2316419 * x));
		double y = ((((1.330274429 * k - 1.821255978) * k + 1.781477937) * k - 0.356563782) * k + 0.319381530) * k;
		y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

		return (1d - neg) * y + neg * (1d - y);
	}

	public float pricingCallEquityOptions(EquityOptions f) {
		double Sp = f.getCurrentStockPrice();

		double t = f.getTimeUntilExercise();
		double Kp = f.getOptionStrikePrice();
		double r = f.getRiskFreeInterestRate();
		double s = f.getVolatility();

		double d1 = (Math.log(Sp / Kp) + (r + Math.pow(s, 2) / 2) * t) / (s * Math.sqrt(t));
		double d2 = d1 - (s * Math.sqrt(t));
		double callfx;
		callfx = Sp * CumulNormalDis(d1) - CumulNormalDis(d2) * Kp * Math.exp(-r * t);
		return (float) callfx;
	}

	@Override
	public float pricingPutEquityOptions(EquityOptions f) {
		double Sp = f.getCurrentStockPrice();
		double t = f.getTimeUntilExercise();
		double Kp = f.getOptionStrikePrice();
		double r = f.getRiskFreeInterestRate();
		double s = f.getVolatility();

		double d1 = (Math.log(Sp / Kp) + (r + s * s / 2) * t) / (s * Math.sqrt(t));
		// double d2 = d1 - s * Math.sqrt(t);

		double puteo = Kp * Math.exp(-r * t) - Sp + pricingCallEquityOptions(f);

		return (float) puteo;
	}

}
