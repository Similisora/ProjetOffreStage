package Objet;

import java.util.ArrayList;
import java.util.Date;

public class OffreStage {
	//Une OffreStage relie l'entreprise � une candidature
	//Une entreprise peut cr�er des offres de stage
	
	private int ID;
	private String Libell�Offre;
	private String DescriptionOffre;
	private String DomaineOffre;
	private Date DateD�butOffre;
	private int Dur�eOffre;
	private boolean Valide;
	private int IDEntreprise;
	private boolean changer;
	
	public boolean isChanger() {
		return changer;
	}

	public void setChanger(boolean changer) {
		this.changer = changer;
	}

	private ArrayList<Candidature> ListeCandidature = new ArrayList<Candidature>();
	
	public ArrayList<Candidature> getListeCandidature() {
		return ListeCandidature;
	}

	public void setListeCandidature(ArrayList<Candidature> listeCandidature) {
		ListeCandidature = listeCandidature;
	}

	public OffreStage() {
		
	}
	
	public OffreStage(String Libell�Offre, String DescriptionOffre, String DomaineOffre, Date DateD�butOffre, int Dur�eOffre, boolean Valide, int IDEntreprise) {
	
		this.Libell�Offre = Libell�Offre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateD�butOffre = DateD�butOffre;
		this.Dur�eOffre = Dur�eOffre;
		this.Valide = Valide;
		this.IDEntreprise = IDEntreprise;
		this.changer = false;
		
	}
	
	public OffreStage(int ID, String Libell�Offre, String DescriptionOffre, String DomaineOffre, Date DateD�butOffre, int Dur�eOffre, boolean Valide, int IDEntreprise) {
		
		this.Libell�Offre = Libell�Offre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateD�butOffre = DateD�butOffre;
		this.Dur�eOffre = Dur�eOffre;
		this.Valide = Valide;
		this.IDEntreprise = IDEntreprise;
		this.ID = ID;
		this.changer = false;
		
	}
	
	public OffreStage(int ID, String Libell�Offre, String DescriptionOffre, String DomaineOffre, Date DateD�butOffre, int Dur�eOffre, boolean Valide, int IDEntreprise, boolean changer) {
		
		this.Libell�Offre = Libell�Offre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateD�butOffre = DateD�butOffre;
		this.Dur�eOffre = Dur�eOffre;
		this.Valide = Valide;
		this.IDEntreprise = IDEntreprise;
		this.ID = ID;
		this.changer = changer;
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLibell�Offre() {
		return Libell�Offre;
	}

	public void setLibell�Offre(String libell�Offre) {
		Libell�Offre = libell�Offre;
	}

	public String getDescriptionOffre() {
		return DescriptionOffre;
	}

	public void setDescriptionOffre(String descriptionOffre) {
		DescriptionOffre = descriptionOffre;
	}

	public String getDomaineOffre() {
		return DomaineOffre;
	}

	public void setDomaineOffre(String domaineOffre) {
		DomaineOffre = domaineOffre;
	}

	public Date getDateD�butOffre() {
		return DateD�butOffre;
	}

	public void setDateD�butOffre(Date dateD�butOffre) {
		DateD�butOffre = dateD�butOffre;
	}

	public int getDur�eOffre() {
		return Dur�eOffre;
	}

	public void setDur�eOffre(int dur�eOffre) {
		Dur�eOffre = dur�eOffre;
	}

	public boolean isValide() {
		return Valide;
	}

	public void setValide(boolean valide) {
		Valide = valide;
	}

	public int getIDEntreprise() {
		return IDEntreprise;
	}

	public void setIDEntreprise(int iDEntreprise) {
		IDEntreprise = iDEntreprise;
	}

}
