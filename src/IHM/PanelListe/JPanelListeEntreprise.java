package IHM.PanelListe;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import DAO.EntrepriseDAO;
import IHM.Fenetre;
import Objet.Entreprise;
import Objet.Etudiant;

public class JPanelListeEntreprise extends JPanel {
	//Panel qui affiche la liste des entreprises existantes
	//Il sera constitué de PanelUniqueEntreprise
	
	public JPanel panel = new JPanel();
	ArrayList <Entreprise> le = new ArrayList<Entreprise>();
	
	public JPanelListeEntreprise (final Etudiant et, final Fenetre f2,final Fenetre mere) {
		
		super();
		
		EntrepriseDAO dao = new EntrepriseDAO();
		dao.afficher2(le);
		
		//Le GridLayout aura la taille du nombre total d'entreprise
		GridLayout gl = new GridLayout(le.size(),1,5,5);
		this.panel.setLayout(gl);
		
		this.add(panel);
		
		Color c = new Color (111,206,220);
		panel.setBackground(c);
		
		//Ceci va afficher plusieurs panels d'entreprise dans le Gridlayout
		for (int i = 0; i < le.size(); i++) {
			
			PanelUniqueEntreprise entreprise = new PanelUniqueEntreprise(le.get(i), et, f2,mere);
			panel.add(entreprise);
		}
		
	}

}
