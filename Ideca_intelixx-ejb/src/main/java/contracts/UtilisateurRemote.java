package contracts;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import ideca.entity.BackOfficeUser;
import ideca.entity.EnumGrade;
import ideca.entity.Trader;
import ideca.entity.Utilisateur;

@Remote
public interface UtilisateurRemote {

	public Boolean ajouterUtilisateur(Utilisateur user);

	public Boolean supprimerUtilisateur(int id);

	public List<Trader> getAllTraders(int id);

	public void updateUtilisateur(Utilisateur user);

	public Utilisateur findById(int id);

	public Trader findByIdTrader(int id);

	public List<Trader> listeTraderValide();

	public List<BackOfficeUser> listeBackOfficeUser();

	public List<Utilisateur> listeUser(String type);

	public List<Trader> listeUserEtatType(String type);

	public Utilisateur verifConnexion(String email, String password);

	public void pwdForgotten(String cin);

	public void validerTrader(int idTrader, int idBU, EnumGrade grade, Double amount);

	public List<Trader> listeTraderParBU(int idBU);

	public List<Trader> listeTraderNonValide();

	public List<Trader> listeTraderLimit();

	public long findByType(String type);

	public Set<Trader> findAllTreeSet();

	public List<Trader> rechercheUser(String name);

	public long findByEtat(String etat);

	public void updateTrader(Trader trader);

	public boolean filtercontenu(String chaine);

	public void updateBackOffice(BackOfficeUser backOffice);
	
	public List<String> listNamesTraderValide();
	
	public Trader rechercheByNameTrader(String name);

	BackOfficeUser findByIdBu(int id);

}
