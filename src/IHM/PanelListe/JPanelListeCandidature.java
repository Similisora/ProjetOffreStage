package IHM.PanelListe;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

import DAO.CandidatureDAO;
import DAO.EntrepriseDAO;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import Objet.Candidature;
import Objet.Entreprise;
import Objet.Etudiant;
import Objet.OffreStage;

public class JPanelListeCandidature extends JPanel {
	//Panel qui affiche la liste des candidatures d'une offre de stage quand on est connecté en tant qu'entreprise
	//Il sera constitué de PanelUniqueCandidature
	
	public JPanel panel = new JPanel();
	ArrayList<Candidature> liste_candidature = new ArrayList<Candidature>();
	
	CandidatureDAO dao2 = new CandidatureDAO();
	EntrepriseDAO dao = new EntrepriseDAO();
	OffreStageDAO dao3 = new OffreStageDAO();
	
	OffreStage offre = new OffreStage();
	Entreprise e = new Entreprise();
	
	Color c = new Color (111,206,220);
	
	public JPanelListeCandidature (final Etudiant et, final Fenetre mere, final Fenetre f) {
		
		super();
		
		liste_candidature = dao2.liste(et.getID());
		panel.setBackground(c);
		et.getListeCandidature().removeAll(et.getListeCandidature());
		
		GridLayout gl = new GridLayout(liste_candidature.size(),1,5,5);
		this.panel.setLayout(gl); //Autant de panel que de candidatures
		
		this.add(panel);
		
		//Cette boucle sert à faire le lien entre les candidatures et l'entreprise
		for (int i = 0 ; i < liste_candidature.size() ; i++) {
			et.getListeCandidature().add(liste_candidature.get(i));
			int id = et.getListeCandidature().get(i).getIDOffreStage();
			offre = dao3.find(id);
			int id2 = offre.getIDEntreprise();
			e = dao.find(id2);
			
			PanelUniqueCandidature candi = new PanelUniqueCandidature(e,offre,liste_candidature.get(i),mere,f);
			panel.add(candi); //On ajoute chaque panel de candidatures dans le panel principal
		}
		
	}

}
