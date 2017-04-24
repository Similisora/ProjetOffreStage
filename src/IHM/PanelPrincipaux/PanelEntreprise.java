package IHM.PanelPrincipaux;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.Autre.Log;
import IHM.JDialog.JDialogCreerOffreStage;
import IHM.JDialog.JDialogProfilEntreprise;
import IHM.JTable.tabledebutstage;
import IHM.PanelListe.JPanelOffre;
import Objet.Entreprise;

public class PanelEntreprise extends ImageDeFond {
	//Le Panel Principal quand on se connecte en tant qu'Entreprise (la page d'acceuil)

	public JPanel mainPanel = new JPanel();
	
	public JLabel connecte = new JLabel();
	JLabel version = new JLabel("Version Entreprise");
	
	public Image ImageAffiche;
	public String nomImage;
	public JLabel LabelAffiche;
	
	JButton saisir = new JButton(new ImageIcon("Add List-48.png"));
	JButton consulter = new JButton (new ImageIcon("Generic Book File Type-40.png"));
	JButton profil = new JButton (new ImageIcon("User-50.png"));
	
	ImageIcon imageIcon = new ImageIcon(new ImageIcon("Shutdown-48.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	JButton deco = new JButton (imageIcon);
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.BOLD, 20);
	Font italic = new Font(" Italic ", Font.ITALIC, 12);
	
	PanelEntreprise me = this;
	
	JLabel j1 = new JLabel ("GESTION DES OFFRES");
	JLabel j2 = new JLabel("DE STAGE");
	
	Font police = new Font("TimesRoman", Font.ITALIC, 30);
	
	Color c = new Color (20,119,166);

	public PanelEntreprise(final Entreprise e, final Fenetre mere) {
		
		super("blanc.jpg",800);
		
		connecte.setText("Connecté en tant que "+e.getNom());
		connecte.setFont(italic);
		version.setFont(italic);
		
		ImageIcon ImageAfficheFilm = new ImageIcon(e.getLogo());
		ImageAffiche = scaleImage(ImageAfficheFilm.getImage(), 160);
		LabelAffiche = new JLabel(new ImageIcon(ImageAffiche));
		
		me.add(LabelAffiche);
		LabelAffiche.setBounds(320, 130, 160, 100);
		
		j1.setFont(police);
		j2.setFont(police);
		j1.setBounds(250, 10, 350, 80);
		j2.setBounds(325, 42, 200, 80);
		
		me.setLayout(null);
		
		me.add(connecte);
		me.add(version);
		me.setBackground(Color.white);
		connecte.setBounds(550, 0, 300, 30);
		version.setBounds(100, 0, 150, 30);
		
		me.add(saisir);
		me.add(deco);
		me.add(consulter);
		me.add(profil);
		
		me.add(j1);
		me.add(j2);
		
		profil.setBounds(350, 270, 100, 80);
		profil.setText("Mon Profil");
		profil.setVerticalTextPosition(SwingUtilities.TOP);
		profil.setHorizontalTextPosition(SwingUtilities.CENTER);
		profil.setForeground(c);
		
		deco.setBounds(750, 330, 26, 26);
		deco.setBackground(Color.white);
		
		consulter.setText("Mes Offres");
		consulter.setVerticalTextPosition(SwingUtilities.TOP);
		consulter.setHorizontalTextPosition(SwingUtilities.CENTER);
		consulter.setBounds(600, 125, 150, 100);
		consulter.setForeground(c);
		
		saisir.setText("Créer une Offre");
		saisir.setVerticalTextPosition(SwingUtilities.TOP);
		saisir.setHorizontalTextPosition(SwingUtilities.CENTER);
		saisir.setBounds(45, 125, 150, 100);
		saisir.setForeground(c);
		
		deco.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				mere.setVisible(false);
				Log log = new Log();
				log.setVisible(true);
				
			}
		});
		
		consulter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				final Fenetre f = new Fenetre ();
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				
				mere.setVisible(false);
				f.setTitle("Liste de mes Offres de Stage");
				f.setSize(800, 450);
				JPanelOffre j = new JPanelOffre(e,f,mere);
				f.add(j);
				f.getContentPane().add(new JScrollPane(j.panel2), BorderLayout.CENTER);
				f.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						f.setVisible(false);
						mere.setVisible(true);
						
					}
				});
				
			}
		});
		
		saisir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				tabledebutstage modele = new tabledebutstage ();
				EntrepriseDAO dao = new EntrepriseDAO();

				JDialogCreerOffreStage j = new JDialogCreerOffreStage(modele,dao,e,mere);
				mere.setVisible(false);
			}
		});
		
		profil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogProfilEntreprise p = new JDialogProfilEntreprise(e,me,mere);
				mere.setVisible(false);
			}
		});
		
	}
	
	public static Image scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return img;
	}
	
	public static Image scaleImage(Image source, int size) {
	    int width = source.getWidth(null);
	    int height = source.getHeight(null);
	    double f = 0;
	    if (width < height) {//portrait
	        f = (double) height / (double) width;
	        width = (int) (size / f);
	        height = size;
	    } else {//paysage
	        f = (double) width / (double) height;
	        width = size;
	        height = (int) (size / f);
	    }
	    return scaleImage(source, width, height);
	}

}
