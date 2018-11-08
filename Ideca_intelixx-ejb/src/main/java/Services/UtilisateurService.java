package Services;

import java.text.Normalizer;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import contracts.UtilisateurRemote;
import ideca.entity.BackOfficeUser;
import ideca.entity.Contact;
import ideca.entity.EnumGrade;
import ideca.entity.Trader;
import ideca.entity.Utilisateur;

@Stateless
@LocalBean
public class UtilisateurService implements UtilisateurRemote {

	@PersistenceContext(unitName = "Ideca_intelixx-ejb")
	private EntityManager em;
	Logger logger = Logger.getAnonymousLogger();

	public UtilisateurService() {
		super();
	}

	public UtilisateurService(EntityManager em) {

		this.em = em;

	}

	@Override
	public Boolean ajouterUtilisateur(Utilisateur trader) {
		try {
			em.persist(trader);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception    ", e);
			return false;
		}
	}

	@Override
	public Utilisateur findById(int id) {
		Utilisateur f = null;
		try {
			f = em.find(Utilisateur.class, id);
			return f;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "an exception", ex);
		}
		return f;
	}

	@Override
	public Boolean supprimerUtilisateur(int id) {
		try {
			em.remove(findById(id));

			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "utilisateur non suprimmé !!! ", e);
			return false;
		}
	}

	@Override
	public List<Utilisateur> listeUser(String type) {

		String jpql = "SELECT U FROM Utilisateur U where U.type='" + type + "'";
		Query qry = em.createQuery(jpql);
		return qry.getResultList();
	}

	// verifier la connexion d'un trader
	@Override
	public Utilisateur verifConnexion(String email, String password) {

		Utilisateur user = null;

		String jpql = "select e from Utilisateur e where e.email=:x and e.pwd=:y and e.etat=1";
		try {

			user = em.createQuery(jpql, Utilisateur.class).setParameter("x", email).setParameter("y", password)
					.getSingleResult();
		}

		catch (NoResultException ex) {
			logger.log(Level.SEVERE, "prob de connexion requette!!! ", ex);
		}
		return user;
	}

	@Override
	public void pwdForgotten(String cin) {

		String jpql = "update Utilisateur e set pwd:=cin where e.cin=:x and e.etat=1";

		int modified = em.createQuery(jpql).setParameter("x", cin).setParameter("cin", cin).executeUpdate();
		if (modified == 1) {
			logger.log(Level.FINE, "pas de prob dans le reset du password ");
		} else {
			logger.log(Level.SEVERE, "prob dans le reset du password ");
		}

	}

	// si l'un des utilisateur modifie ses donnees

	@Override
	public void updateUtilisateur(Utilisateur user) {
		try {
			em.merge(user);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "no update ", e);

		}
	}

	@Override
	public void updateTrader(Trader trader) {
		try {
			em.merge(trader);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "no update ", e);

		}
	}

	@Override
	public void updateBackOffice(BackOfficeUser backOffice) {
		try {
			em.merge(backOffice);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "no update ", e);

		}
	}
	// liste des trader non validés

	@Override
	public List<Trader> listeTraderNonValide() {

		String jpql = "SELECT U FROM Utilisateur U where U.type=:x and U.etat=0";
		Query qry = em.createQuery(jpql);
		qry.setParameter("x", "Trader");
		@SuppressWarnings("unchecked")
		List<Trader> list = qry.getResultList();
		return list;

	}

	

	@Override
	public List<Trader> listeTraderValide() {

		String jpql = "SELECT U FROM Utilisateur U where U.type=:x and U.etat=1";
		Query qry = em.createQuery(jpql);
		qry.setParameter("x", "Trader");
		@SuppressWarnings("unchecked")
		List<Trader> list = qry.getResultList();
		return list;

	}

	// un BackOffice User peut valider l inscription d'un trader et devient son
	// BackOffice User associé

	@Override
	public void validerTrader(int idTrader, int idBU, EnumGrade grade, Double amount) {
		BackOfficeUser backofficeUser = em.find(BackOfficeUser.class, idBU);
		Trader trader = em.find(Trader.class, idTrader);
		trader.setBackOfficeUser(backofficeUser);
		backofficeUser.getListTrader().add(trader);

		Query query = em.createQuery("update Utilisateur e set e.etat=1,e.grade=:x,e.amount=:y where e.id=:z");
		query.setParameter("x", grade);
		query.setParameter("y", amount);
		query.setParameter("z", idTrader);
		int modified = query.executeUpdate();
		if (modified == 1) {

			logger.log(Level.SEVERE, "Successfully ");
		} else {
			logger.log(Level.SEVERE, "no modifications");
		}

	}

	// Liste des trader associé a un backOffice User

	@Override
	public List<Trader> listeTraderParBU(int idBU) {

		BackOfficeUser backofficeUser = em.find(BackOfficeUser.class, idBU);
		return backofficeUser.getListTrader();

	}

	@Override
	public long findByType(String type) {
		String jpql = "SELECT COUNT(U) FROM Utilisateur U where U.type=:x";
		Query qry = em.createQuery(jpql, Long.class);
		qry.setParameter("x", type);

		return (long) qry.getSingleResult();
	}

	@Override
	public long findByEtat(String etat) {
		String jpql = "SELECT COUNT(U) FROM Utilisateur U where U.etat=:x";
		Query qry = em.createQuery(jpql, Long.class);
		qry.setParameter("x", etat);

		return (long) qry.getSingleResult();
	}

	@Override
	public Set<Trader> findAllTreeSet() {
		Set<Trader> treset = new HashSet<>();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Trader> query = cb.createQuery(Trader.class);
			Root<Trader> m = query.from(Trader.class);
			query.select(m);
			Set<Trader> setTrader = new HashSet<Trader>(em.createQuery(query).getResultList());
			return setTrader;
		} catch (NoResultException nre) {
			logger.log(Level.SEVERE, "an exception stat", nre);
			return treset;

		} catch (IllegalArgumentException iae) {
			logger.log(Level.SEVERE, "an exception stat!! ", iae);
			return treset;
		}
	}

	@Override
	public List<Trader> listeUserEtatType(String type) {

		String jpql = "select e from Utilisateur e where e.type=:x and e.etat=1 ";

		Query qry = em.createQuery(jpql, Utilisateur.class);

		qry.setParameter("x", type);
		return qry.getResultList();
	}

	@Override
	public Trader findByIdTrader(int id) {
		Trader f = null;
		try {
			f = em.find(Trader.class, id);
			return f;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "an exception  was thrown ", ex);
		}
		return f;
	}

	@Override
	public List<Trader> rechercheUser(String name) {
		TypedQuery<Trader> query = em.createQuery("SELECT c FROM Utilisateur c where c.firstName LIKE :Name ",
				Trader.class);
		List<Trader> results = query.setParameter("Name", "%" + name + "%").getResultList();
		return results;
	}

	@Override
	public List<Trader> getAllTraders(int id) {
		String jpql = "SELECT U FROM Utilisateur U where  U.type='Trader' ";
		Query qry;
		qry = em.createQuery(jpql);
		return qry.getResultList();
	}

	@Override
	public List<BackOfficeUser> listeBackOfficeUser() {

		String jpql = "SELECT U FROM Utilisateur U where U.type='BackOfficeUser'";
		Query qry = em.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<BackOfficeUser> list = qry.getResultList();
		return list;

	}

	@Override
	public List<Trader> listeTraderLimit() {
		String jpql = "SELECT U FROM Utilisateur U where U.type=:x and U.etat=1 LIMIT 0,4";
		Query qry = em.createQuery(jpql);
		qry.setParameter("x", "Trader");
		@SuppressWarnings("unchecked")
		List<Trader> list = qry.getResultList();
		return list;
	}

	@Override
	public BackOfficeUser findByIdBu(int id) {
		BackOfficeUser f = null;
		try {
			f = em.find(BackOfficeUser.class, id);
			return f;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "an exception  was thrown ", ex);
		}
		return f;
	}

	@Override
	public List<String> listNamesTraderValide() {
		String jpql = "SELECT U.firstName FROM Utilisateur U where U.type=:x and U.etat=1";
		Query qry = em.createQuery(jpql);
		qry.setParameter("x", "Trader");

		List<String> list = qry.getResultList();
		return list;
	}

	@Override
	public Trader rechercheByNameTrader(String name) {

		String jpql = "SELECT U FROM Utilisateur U where U.type=:x and U.etat=1 and U.firstName=:y";
		Query qry = em.createQuery(jpql);
		qry.setParameter("x", "Trader");
		qry.setParameter("y", name);

		return (Trader) qry.getSingleResult();
	}

	
	@Override
	public boolean filtercontenu(String chaine) {

		boolean contenuides = false;

		String[] Dictionaire = { "HEDI", "BETE", "ZERO", "NULL" };

		String normalized = Normalizer.normalize(chaine, Normalizer.Form.NFD);
		String propre = normalized.replaceAll("[\u0300-\u036F]", "");// bech
																		// yne7i
																		// el é
		String upercase = propre.toUpperCase();

		String[] espace = upercase.split("\\s");

		for (String i : Dictionaire) {
			for (String j : espace) {
				if (i.equals(j)) {

					contenuides = true;

					break;
				}
			}
		}

		return contenuides;
	}

}
