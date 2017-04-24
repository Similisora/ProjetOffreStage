package IHM.PanelListe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.JDialog.JDialogProfilEtudiant;
import Objet.Candidature;
import Objet.Etudiant;
import Objet.OffreStage;

public class PanelUniqueEtudiant extends JPanel {
	//Un panel qui décrit un étudiant
	//Il apparaitrat dans le JPanelListeEtudiant

	public Etudiant et;
	
	Font police = new Font(" TimesRoman", Font.ITALIC, 15);
	BorderLayout layout = new BorderLayout();
	
	JLabel nom = new JLabel();
	JLabel prenom = new JLabel();
	
	JButton profil = new JButton (new ImageIcon ("User-50.png"));
	JButton lettre = new JButton (new ImageIcon ("Word-48.png"));
	JButton accepter = new JButton(new ImageIcon ("Checkmark-48.png"));
	JButton refuser = new JButton(new ImageIcon ("Delete-48.png"));
	JButton cv = new JButton(new ImageIcon ("Resume-50.png"));
	
	JPanel info = new ImageDeFond("blanc.jpg");
	JPanel panelBouton = new ImageDeFond("blanc.jpg");
	JPanel desc = new ImageDeFond("blanc.jpg");
	
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	
	PanelUniqueEtudiant me = this;
	final OffreStageDAO dao3 = new OffreStageDAO();

	public PanelUniqueEtudiant (final Candidature c, final Etudiant et, final JPanelListeEtudiant j, final Fenetre mere, final Fenetre f, final Fenetre f2, final OffreStage offre) {

		super();
		this.et = et;
		
		Color color = new Color (111,206,220);
		//me.setBackground(color);
		
		nom.setText("Nom : " + et.getNom());
		nom.setFont(police);
		
		prenom.setText("Prénom : " + et.getPrenom());
		prenom.setFont(police);

		build();
		
		//Bouton "Voir profil"
		profil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				JDialogProfilEtudiant p = new JDialogProfilEtudiant(et);
				
			}
		});
		
		lettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
					if (et.getLettre().equals("")) {
						JOptionPane.showMessageDialog(null, "Aucune Lettre de Motivation disponible...",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					 { Desktop.getDesktop().open(new File(et.getLettre())); }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Pour accepter une candidature
		accepter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				c.setStatut("Acceptée"); //On passse le statut de la candidature à Acceptée
				
				CandidatureDAO dao = new CandidatureDAO();
				EtudiantDAO dao2 = new EtudiantDAO();
				
				dao.update(c); //On met à jour la base de donnée
				
				JOptionPane.showMessageDialog(null, "Candidature acceptée !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				
				//Ceci est pour afficher un "pop-up" lors de la prochaine connection de l'étudiant
				et.setAccept(true);
				et.setMessage(true);
				
				dao2.update(et);
				
				offre.setValide(false);
				dao3.update(offre);
					
				//Pour fermer les autres fenetres
				f.setVisible(false);
				f2.setVisible(false);
				mere.setVisible(true);
			}
		});
		
		//Même chose mais pour refuser
		refuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				CandidatureDAO dao = new CandidatureDAO();
				EtudiantDAO dao2 = new EtudiantDAO();
				
				c.setStatut("Refusée");
				dao.update(c);
				
				JOptionPane.showMessageDialog(null, "Candidature refusée !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				
				et.setMessage(true);
				et.setAccept(false);
				dao2.update(et);
				
				f.setVisible(false);
				f2.setVisible(false);
				mere.setVisible(true);
			}
		});
		
		cv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
					if (et.getCv().equals("")) {
						JOptionPane.showMessageDialog(null, "Aucun CV disponible...",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					 { Desktop.getDesktop().open(new File(et.getCv())); }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void build() {
		
		me.setLayout(new GridLayout(1,2,5,5));
		me.add(info);
		me.add(panelBouton);
		
		info.setLayout(new GridLayout(1,2));
		info.add(desc);
		info.add(profil);
		
		desc.setLayout(new GridLayout(2,1));
		desc.add(prenom);
		desc.add(nom);

		this.nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		panelBouton.setLayout(new GridLayout(2,2));
		
		profil.setText("Voir Profil");
		profil.setVerticalTextPosition(SwingUtilities.TOP);
		profil.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		cv.setText("Consulter CV");
		cv.setVerticalTextPosition(SwingUtilities.TOP);
		cv.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		accepter.setText("Accepter");
		accepter.setVerticalTextPosition(SwingUtilities.TOP);
		accepter.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		refuser.setText("Refuser");
		refuser.setVerticalTextPosition(SwingUtilities.TOP);
		refuser.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		lettre.setText("Lettre de Motivation");
		lettre.setVerticalTextPosition(SwingUtilities.TOP);
		lettre.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		panelBouton.add(cv);
		panelBouton.add(lettre);
		panelBouton.add(accepter);
		panelBouton.add(refuser);
		pan1.add(p1);
		pan1.add(p2);
		pan2.add(p3);
		pan2.add(p4);

	}

}