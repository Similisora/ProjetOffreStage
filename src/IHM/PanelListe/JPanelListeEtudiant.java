package IHM.PanelListe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import DAO.CandidatureDAO;
import DAO.EtudiantDAO;
import IHM.Fenetre;
import Objet.Candidature;
import Objet.Etudiant;
import Objet.OffreStage;

public class JPanelListeEtudiant extends JPanel {
	//Panel qui affiche la liste des étudiants candidats à une offre de stage
	//Il sera constitué de PanelUniqueEtudiant

	Font police = new Font(" Police", Font.ITALIC, 30);

	JPanelListeEtudiant me = this;
	
	public JPanel panel = new JPanel();

	ArrayList <Candidature> liste = new ArrayList<Candidature>();
	
	public JPanelListeEtudiant (final Fenetre f, final Fenetre mere, final OffreStage offre, final Fenetre f2) {

		super();
		
		//On récupère la liste les candidatures d'une offre
		CandidatureDAO dao = new CandidatureDAO();
		liste = dao.find(offre.getID());
		
		//On récupère le nombre de candidatures en attente
		int compt = 0;
		for (int i = 0 ; i<liste.size() ; i++) {
			if (liste.get(i).getStatut().equals("En attente")) compt ++;
		}
		
		//Si elle fait seulement 1, j'incrémente sinon ça ne rend pas très beau sur l'interface graphique
		if (compt == 1) compt ++;
		
		//Je construis mon GridLayout avec le nombre de candidatures
		GridLayout gl = new GridLayout(compt,1,10,10);
		this.panel.setLayout(gl);
		this.add(panel);
		
		Color color = new Color (111,206,220);
		panel.setBackground(color);
		
		for (int i = 0; i < liste.size() ; i++) {
		
			if (liste.get(i).getIDOffreStage() == offre.getID() && liste.get(i).getStatut().equals("En attente")) {
				
				int id = liste.get(i).getIDEtudiant();
				EtudiantDAO dao2 = new EtudiantDAO();
				Etudiant et = new Etudiant ();
				Candidature c = liste.get(i);
				
				et = dao2.find(id);
				PanelUniqueEtudiant pe = new PanelUniqueEtudiant(c,et,me,mere,f,f2,offre);
				panel.add(pe);
			}
		}

	}
	
}