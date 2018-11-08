package Services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import contracts.ContactRemote;
import ideca.entity.Contact;

@Stateless
@LocalBean
public class ContactService implements ContactRemote{
	@PersistenceContext(unitName = "Ideca_intelixx-ejb")
	private EntityManager em;
	
	Logger logger = Logger.getAnonymousLogger();
	
	public ContactService() {
		super();
	}
	

	public ContactService(EntityManager em) {
		super();
		this.em = em;
	}


	@Override
	public Boolean addContact(Contact contact) {
		try {
			em.persist(contact);
			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception    ", e);
			return false;
		}
	}

	@Override
	public List<Contact> listContact() {
		TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c ORDER BY c.id DESC ", Contact.class);
		List<Contact> results = query.getResultList();

		return results;
	}

	@Override
	public void updateStatus(Contact contact) {
		try {
			em.merge(contact);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "no update ", e);

		}
	}
	@Override
	public Boolean supprimerContact(int id) {
		try {
			em.remove(findById(id));

			return true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Contact non suprimmé !!! ", e);
			return false;
		}
	}

	@Override
	public Contact findById(int id) {
		Contact f = null;
		try {
			f = em.find(Contact.class, id);
			return f;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "an exception", ex);
		}
		return f;
	}



	

}
