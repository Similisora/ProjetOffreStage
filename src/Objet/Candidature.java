package Objet;
import java.util.ArrayList;

public class Candidature {
	//Une candidature fait le lien entre l'étudiant et l'offre de stage

	private int ID;
	private String statut;
	private int IDEtudiant;
	private int IDOffreStage;
	
	public Candidature(int id, String statut, int idoffrestage, int idetudiant) {
		
		this.ID = id;
		this.statut = statut;
		this.IDOffreStage = idoffrestage;
		this.IDEtudiant = idetudiant;
	}
	
	public Candidature(String statut, int idoffrestage, int idetudiant) {
		
		this.statut = statut;
		this.IDOffreStage = idoffrestage;
		this.IDEtudiant = idetudiant;
	}
	
	public Candidature() {
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getIDEtudiant() {
		return IDEtudiant;
	}
	public void setIDEtudiant(int iDEtudiant) {
		IDEtudiant = iDEtudiant;
	}
	public int getIDOffreStage() {
		return IDOffreStage;
	}
	public void setIDOffreStage(int iDOffreStage) {
		IDOffreStage = iDOffreStage;
	}

}
