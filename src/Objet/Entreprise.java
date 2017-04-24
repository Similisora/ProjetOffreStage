package Objet;

import java.util.ArrayList;

public class Entreprise extends Utilisateur {
	//Une entreprise quelconque

	private String Nom;
	private String SecteurActivit�;
	private String RaisonSociale;
	private String logo;	
	private ArrayList<OffreStage> ListeOffre = new ArrayList<OffreStage>();
	
	public Entreprise() {
		
	}

	public Entreprise (int id , String mdp, String nom, String ville, String rue, String CodePostal, String Telephone, String Mail, String SecteurActivit�, String RaisonSociale, String logo) {
		
		super (id, mdp, ville, rue, CodePostal, Telephone, Mail);
		this.Nom = nom;
		this.SecteurActivit� = SecteurActivit�;
		this.RaisonSociale = RaisonSociale;
		this.logo = logo;
		
	}
	//Constructeur pour instancier sans id, gr�ce � l'auto-incr�mentation (dans EntrepriseDAO)
	public Entreprise (String mdp, String nom, String ville, String rue, String CodePostal, String Telephone, String Mail, String SecteurActivit�, String RaisonSociale, String logo) {
		
		super (mdp, ville, rue, CodePostal, Telephone, Mail);
		this.Nom = nom;
		this.SecteurActivit� = SecteurActivit�;
		this.RaisonSociale = RaisonSociale;
		this.logo = logo;
		
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public ArrayList<OffreStage> getListeOffre() {
		return ListeOffre;
	}

	public void setListeOffre(ArrayList<OffreStage> listeOffre) {
		ListeOffre = listeOffre;
	}


	public String getRaisonSociale() {
		return RaisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		RaisonSociale = raisonSociale;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getSecteurActivit�() {
		return SecteurActivit�;
	}

	public void setSecteurActivit�(String secteurActivit�) {
		SecteurActivit� = secteurActivit�;
	}
	
}
