package contracts;

import java.util.List;

import javax.ejb.Remote;

import ideca.entity.Contact;

@Remote
public interface ContactRemote {
	public Boolean addContact(Contact contact);
	public List<Contact> listContact();
	public void updateStatus(Contact contact);
	public Contact findById(int id);
	public Boolean supprimerContact(int id);

}
