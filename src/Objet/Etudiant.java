package Objet;

import java.util.ArrayList;

public class Etudiant extends Utilisateur {
	//Un étudiant quelconque
	
	private String nom;
	private String prenom;
	private boolean accept;
	private boolean message;
	private String cv;
	private String lettre;	
	private ArrayList<Candidature> ListeCandidature = new ArrayList<Candidature>();
	
	public Etudiant() {
		
	}

	public Etudiant (int id , String nom , String prenom, String mdp, String ville, String rue, String CodePostal, String Telephone, String Mail, String cv, String lettre) {
		
		super (id, mdp, ville, rue, CodePostal, Telephone, Mail);
		this.nom = nom;
		this.prenom = prenom;
		this.accept = false;
		this.message = false;
		this.cv = cv;
		this.lettre = lettre;
		
	}
	
	public Etudiant (int id , String nom , String prenom, String mdp, String ville, String rue, String CodePostal, String Telephone, String Mail, boolean accept, boolean message, String cv, String lettre) {
		
		super (id, mdp, ville, rue, CodePostal, Telephone, Mail);
		this.nom = nom;
		this.prenom = prenom;
		this.accept = accept;
		this.message = message;
		this.cv = cv;
		this.lettre = lettre;
		
	}
	
	//Constructeur pour instancier sans id, grâce à l'auto-incrémentation (dans EtudiantDAO)
	public Etudiant (String nom, String prenom, String mdp, String ville, String rue, String CodePostal, String Telephone, String Mail) {
		
		super (mdp, ville, rue, CodePostal, Telephone, Mail);
		this.nom = nom;
		this.prenom = prenom;
		this.accept = false;
		this.message = false;
		this.cv = "";
		this.lettre = "";
		
	}
	
	public ArrayList<Candidature> getListeCandidature() {
		return ListeCandidature;
	}

	public void setListeCandidature(ArrayList<Candidature> listeCandidature) {
		ListeCandidature = listeCandidature;
	}
	

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getLettre() {
		return lettre;
	}

	public void setLettre(String lettre) {
		this.lettre = lettre;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
