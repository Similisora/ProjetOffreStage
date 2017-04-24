package Objet;

import java.util.ArrayList;
import java.util.Date;

public class OffreStage {
	//Une OffreStage relie l'entreprise à une candidature
	//Une entreprise peut créer des offres de stage
	
	private int ID;
	private String LibelléOffre;
	private String DescriptionOffre;
	private String DomaineOffre;
	private Date DateDébutOffre;
	private int DuréeOffre;
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
	
	public OffreStage(String LibelléOffre, String DescriptionOffre, String DomaineOffre, Date DateDébutOffre, int DuréeOffre, boolean Valide, int IDEntreprise) {
	
		this.LibelléOffre = LibelléOffre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateDébutOffre = DateDébutOffre;
		this.DuréeOffre = DuréeOffre;
		this.Valide = Valide;
		this.IDEntreprise = IDEntreprise;
		this.changer = false;
		
	}
	
	public OffreStage(int ID, String LibelléOffre, String DescriptionOffre, String DomaineOffre, Date DateDébutOffre, int DuréeOffre, boolean Valide, int IDEntreprise) {
		
		this.LibelléOffre = LibelléOffre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateDébutOffre = DateDébutOffre;
		this.DuréeOffre = DuréeOffre;
		this.Valide = Valide;
		this.IDEntreprise = IDEntreprise;
		this.ID = ID;
		this.changer = false;
		
	}
	
	public OffreStage(int ID, String LibelléOffre, String DescriptionOffre, String DomaineOffre, Date DateDébutOffre, int DuréeOffre, boolean Valide, int IDEntreprise, boolean changer) {
		
		this.LibelléOffre = LibelléOffre;
		this.DescriptionOffre = DescriptionOffre;
		this.DomaineOffre = DomaineOffre;
		this.DateDébutOffre = DateDébutOffre;
		this.DuréeOffre = DuréeOffre;
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

	public String getLibelléOffre() {
		return LibelléOffre;
	}

	public void setLibelléOffre(String libelléOffre) {
		LibelléOffre = libelléOffre;
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

	public Date getDateDébutOffre() {
		return DateDébutOffre;
	}

	public void setDateDébutOffre(Date dateDébutOffre) {
		DateDébutOffre = dateDébutOffre;
	}

	public int getDuréeOffre() {
		return DuréeOffre;
	}

	public void setDuréeOffre(int duréeOffre) {
		DuréeOffre = duréeOffre;
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
