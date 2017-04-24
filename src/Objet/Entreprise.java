package Objet;

import java.util.ArrayList;

public class Entreprise extends Utilisateur {
	//Une entreprise quelconque

	private String Nom;
	private String SecteurActivité;
	private String RaisonSociale;
	private String logo;	
	private ArrayList<OffreStage> ListeOffre = new ArrayList<OffreStage>();
	
	public Entreprise() {
		
	}

	public Entreprise (int id , String mdp, String nom, String ville, String rue, String CodePostal, String Telephone, String Mail, String SecteurActivité, String RaisonSociale, String logo) {
		
		super (id, mdp, ville, rue, CodePostal, Telephone, Mail);
		this.Nom = nom;
		this.SecteurActivité = SecteurActivité;
		this.RaisonSociale = RaisonSociale;
		this.logo = logo;
		
	}
	//Constructeur pour instancier sans id, grâce à l'auto-incrémentation (dans EntrepriseDAO)
	public Entreprise (String mdp, String nom, String ville, String rue, String CodePostal, String Telephone, String Mail, String SecteurActivité, String RaisonSociale, String logo) {
		
		super (mdp, ville, rue, CodePostal, Telephone, Mail);
		this.Nom = nom;
		this.SecteurActivité = SecteurActivité;
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

	public String getSecteurActivité() {
		return SecteurActivité;
	}

	public void setSecteurActivité(String secteurActivité) {
		SecteurActivité = secteurActivité;
	}
	
}
