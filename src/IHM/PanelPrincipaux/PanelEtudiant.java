package IHM.PanelPrincipaux;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import DAO.CandidatureDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.Autre.Log;
import IHM.JDialog.JDialogProfilAdmin;
import IHM.PanelListe.JPanelListeCandidature;
import IHM.PanelListe.JPanelListeEntreprise;
import Objet.Candidature;
import Objet.Etudiant;

public class PanelEtudiant extends ImageDeFond {
	//Le Panel Principal quand on se connecte en tant qu'Etudiant (la page d'acceuil)

	public JPanel mainPanel = new JPanel();
	
	public JLabel connecte = new JLabel();
	JLabel version = new JLabel("Version Etudiant");
	
	JLabel j1 = new JLabel ("GESTION DES OFFRES");
	JLabel j2 = new JLabel("DE STAGE");
	
	Font police = new Font("TimesRoman", Font.ITALIC, 30);
	
	JButton entreprise = new JButton (new ImageIcon ("Link Company Parent-50.png"));
	JButton stage = new JButton (new ImageIcon ("Generic Book File Type-40.png"));
	JButton profil = new JButton (new ImageIcon("User-50.png"));
	ImageIcon imageIcon = new ImageIcon(new ImageIcon("Shutdown-48.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	JButton deco = new JButton (imageIcon);
	
	Font italic = new Font(" Italic ", Font.ITALIC, 12);
	
	PanelEtudiant me = this;
	
	Color c = new Color (20,119,166);

	public PanelEtudiant(final Etudiant et, final Fenetre mere) {
		
		super("blanc.jpg",800);
		
		connecte.setText("Connecté en tant que "+et.getPrenom() + " " + et.getNom());
		connecte.setFont(italic);
		version.setFont(italic);
		
		j1.setFont(police);
		j2.setFont(police);
		
		me.setLayout(null);
		
		me.add(connecte);
		me.add(version);
		me.setBackground(Color.white);
		connecte.setBounds(550, 0, 300, 30);
		version.setBounds(100, 0, 100, 30);
		
		me.add(entreprise);
		me.add(deco);
		me.add(stage);
		me.add(profil);
		
		me.add(j1);
		me.add(j2);
		
		j1.setBounds(250, 10, 350, 80);
		j2.setBounds(325, 42, 200, 80);
		
		deco.setBounds(750, 330, 26, 26);
		deco.setBackground(Color.white);
		
		profil.setBounds(350, 270, 100, 80);
		profil.setText("Mon Profil");
		profil.setForeground(c);
		profil.setVerticalTextPosition(SwingUtilities.TOP);
		profil.setHorizontalTextPosition(SwingUtilities.CENTER);		
		
		stage.setBounds(560, 125, 180, 100);
		stage.setText("Mes Candidatures");
		stage.setForeground(c);
		stage.setVerticalTextPosition(SwingUtilities.TOP);
		stage.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		entreprise.setBounds(45, 125, 190, 100);
		entreprise.setText("Liste des Entreprises");
		entreprise.setForeground(c);
		entreprise.setVerticalTextPosition(SwingUtilities.TOP);
		entreprise.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		deco.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				mere.setVisible(false);
				Log log = new Log();
				log.setVisible(true);
				
			}
		});
		
		profil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogProfilAdmin p = new JDialogProfilAdmin(et,me,mere);
				mere.setVisible(false);
			}
		});
		
		entreprise.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				//Ceci va ouvrir la liste totale des entreprises
				final Fenetre f2 = new Fenetre ();
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				mere.setVisible(false);
				f2.setTitle("Liste des Entreprises");
				f2.setSize(800, 420);
				JPanelListeEntreprise j = new JPanelListeEntreprise(et,f2,mere);
				f2.add(j);
				f2.getContentPane().add(new JScrollPane(j.panel), BorderLayout.CENTER);
				f2.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						f2.setVisible(false);
						mere.setVisible(true);
						
					}
				});
				
			}
		});
		
		stage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				
				CandidatureDAO dao2 = new CandidatureDAO();
				ArrayList<Candidature> liste_candidature = new ArrayList<Candidature>();
				liste_candidature = dao2.liste(et.getID());
				
				if (liste_candidature.size() == 0) {
					JOptionPane.showMessageDialog(null, "Aucune candidature",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else {
					
					final Fenetre f = new Fenetre ();
					mere.setVisible(false);
					f.setTitle("Liste des Candidatures");
					f.setSize(720, 450);
					JPanelListeCandidature j = new JPanelListeCandidature(et,f,mere);
					f.add(j);
					f.getContentPane().add(new JScrollPane(j.panel), BorderLayout.CENTER);
					f.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						f.setVisible(false);
						mere.setVisible(true);
						
					}
									
				});
			}
			}
		});
	}

}
