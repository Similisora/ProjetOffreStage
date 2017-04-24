package Objet;
public class Admin extends Utilisateur {
	//Administrateur, il n'y en a qu'un seul. Il a accès à toutes les fonctionalités
	
	private String nom;
	private String prenom;
	
	//Constructeur pour instancier sans id, grâce à l'auto-incrémentation (dans AdminDAO)
	public Admin (String nom, String prenom, String mdp, String ville, String rue, String CodePostal, String Telephone, String Mail) {
		
		super (mdp, ville, rue, CodePostal, Telephone, Mail);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Admin (int id, String nom, String prenom, String mdp, String ville, String rue, String CodePostal, String Telephone, String Mail) {
		
		super (id, mdp, ville, rue, CodePostal, Telephone, Mail);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Admin() {
		
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
