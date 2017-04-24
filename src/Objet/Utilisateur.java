package Objet;


public abstract class Utilisateur {
	//Super-classe pour entreprise, etudiant et admin

	private int ID;
	private String mdp;
	private String Ville;
	private String Rue;
	private String CodePostal;
	private String Telephone;
	private String Mail;
	
	public String getVille() {
		return Ville;
	}

	public void setVille(String ville) {
		Ville = ville;
	}

	public String getRue() {
		return Rue;
	}

	public void setRue(String rue) {
		Rue = rue;
	}

	public String getCodePostal() {
		return CodePostal;
	}

	public void setCodePostal(String codePostal) {
		CodePostal = codePostal;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public Utilisateur() {
		
	}
	
	public Utilisateur (int id, String mdp, String Ville,String Rue, String CodePostal, String Telephone, String Mail) {
		this.ID = id;
		this.mdp = mdp;
		this.Ville = Ville;
		this.Rue = Rue;
		this.CodePostal = CodePostal;
		this.Telephone = Telephone;
		this.Mail = Mail;
	}
	
	public Utilisateur (String mdp, String Ville,String Rue, String CodePostal, String Telephone, String Mail) {
		this.mdp = mdp;
		this.Ville = Ville;
		this.Rue = Rue;
		this.CodePostal = CodePostal;
		this.Telephone = Telephone;
		this.Mail = Mail;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}
