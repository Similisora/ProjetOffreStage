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

import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import Objet.Entreprise;
import Objet.Etudiant;

public class JPanelOffre extends JPanel {
	//Panel qui affiche les Offres de Stage
	//Il sera constitué de PanelUniqueOffre

	Font police = new Font(" Police", Font.ITALIC, 30);

	JLabel bienvenue = new JLabel("Liste de mes Offres");
	
	JPanelOffre me = this;
	
	public JPanel panel2 = new JPanel();
	JPanel panel3 = new ImageDeFond("blanc.jpg", 840);
	
	JButton retour = new JButton("Retour");

	// Pour voir ses offres quand on est connecté en tant qu'entreprise (les siennes)
	public JPanelOffre(final Entreprise e, final Fenetre f, final Fenetre mere) {

		super();
		this.setBackground(Color.WHITE);
		
		OffreStageDAO dao = new OffreStageDAO();
		dao.relier2(e);
		
		bienvenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bienvenue.setFont(police);
		
		GridLayout gl = new GridLayout(e.getListeOffre().size(),1);
		this.panel2.setLayout(gl);
		
		this.add(panel2);
		
		for (int i = 0; i < e.getListeOffre().size(); i++) {
			
			PanelUniqueOffre offre = new PanelUniqueOffre(e.getListeOffre().get(i),me,mere,f);
			panel2.add(offre);
		}
		
		panel3.add(retour);
		
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				f.setVisible(false);
				mere.setVisible(true);
			}
		});

	}
	
	//Pour voir les offres d'une entreprise quand on est connecté en tant qu'étudiant
	public JPanelOffre(final Entreprise e, final Fenetre f, final Fenetre f2, final Etudiant et, final Fenetre mere) {

		super();
		this.setBackground(Color.WHITE);
		
		bienvenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bienvenue.setFont(police);
		int compt = 0;
		
		Color c = new Color (111,206,220);
		panel2.setBackground(c);
		
		for (int i = 0; i < e.getListeOffre().size(); i++) {
			
			if (e.getListeOffre().get(i).isValide()) {
				compt++;
			}
		}
		
		GridLayout gl = new GridLayout(compt,1,10,10);
		this.panel2.setLayout(gl);
		
		this.add(panel2);
		
		for (int i = 0; i < e.getListeOffre().size(); i++) {
			
			if (e.getListeOffre().get(i).isValide()) {
			PanelUniqueOffre offre = new PanelUniqueOffre(e.getListeOffre().get(i),f2,f,et,mere);
			panel2.add(offre);
			}
		}
		
		panel3.add(retour);

	}
}