package IHM.PanelListe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
import IHM.JDialog.JDialogProfilEntreprise;
import Objet.Entreprise;
import Objet.Etudiant;

public class PanelUniqueEntreprise extends JPanel {
	//Un panel qui décrit une entreprise
	//Il apparaitrat dans le JPanelListeEntreprise
	
	JLabel nom = new JLabel();
	JLabel secteur = new JLabel();
	
	public Image ImageAffiche;
	public String nomImage;
	public JLabel LabelAffiche;
	
	Entreprise e = new Entreprise();
	
	Font police = new Font(" TimesRoman", Font.ITALIC, 20);
	
	JPanel panelgeneral = new ImageDeFond("blanc.jpg", 340);
	JPanel info = new ImageDeFond("blanc.jpg", 340);
	JPanel panelBouton = new ImageDeFond("blanc.jpg", 340);
	JPanel panelImage = new ImageDeFond("blanc.jpg", 340);
	PanelUniqueEntreprise me = this;
	
	JButton offre = new JButton(new ImageIcon ("Generic Book File Type-40.png"));
	JButton voir = new JButton(new ImageIcon ("Details Popup-40.png"));
	
	public PanelUniqueEntreprise(final Entreprise e, final Etudiant et, final Fenetre f2, final Fenetre mere) {
		
		super();
		this.e = e;
		
		nom.setText(e.getNom());
		nom.setFont(police);
		
		secteur.setText("Secteur : " + e.getSecteurActivité());
		secteur.setFont(police);

		build();
		
		offre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				OffreStageDAO dao = new OffreStageDAO();
				dao.relier2(e);
				
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				
				
				if (e.getListeOffre().size() == 0) {
					JOptionPane.showMessageDialog(null, "Aucune offre disponible pour le moment",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else {
					f2.setVisible(false);
					final Fenetre f = new Fenetre ();
					f.setTitle("Liste des Offres de Stage");
					f.setSize(780, 450);
					JPanelOffre j = new JPanelOffre(e,f,f2,et,mere);
					f.add(j);
					f.getContentPane().add(new JScrollPane(j.panel2), BorderLayout.CENTER);
					f.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						f.setVisible(false);
						f2.setVisible(true);
						
					}
				});

				}
			}
		});
		
		voir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				JDialogProfilEntreprise j = new JDialogProfilEntreprise(e);
			}
		});
		
	}
	
	public void build() {
		
		this.add(panelgeneral);
		
		Color c = new Color (111,206,220);
		me.setBackground(c);
		
		ImageIcon ImageAfficheFilm = new ImageIcon(e.getLogo());
		ImageAffiche = scaleImage(ImageAfficheFilm.getImage(), 180);
		LabelAffiche = new JLabel(new ImageIcon(ImageAffiche));

		panelgeneral.setLayout(new GridLayout(1,3));
		panelgeneral.add(panelImage);
		panelgeneral.add(info);
		panelgeneral.add(panelBouton);
		
		panelImage.add(LabelAffiche);
		panelImage.setPreferredSize(new Dimension (200,100));
		
		info.setLayout(new GridLayout(2,1));
		
		info.add(nom);
		info.add(secteur);
		
		panelBouton.setLayout(new GridLayout (1,2));
		panelBouton.add(voir);
		panelBouton.add(offre);
		
		offre.setText("Offres de Stage");
		offre.setVerticalTextPosition(SwingUtilities.TOP);
		offre.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		voir.setText("L'Entreprise");
		voir.setVerticalTextPosition(SwingUtilities.TOP);
		voir.setHorizontalTextPosition(SwingUtilities.CENTER);

		this.nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.secteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


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
		if (width < height) {// portrait
			f = (double) height / (double) width;
			width = (int) (size / f);
			height = size;
		} else {// paysage
			f = (double) width / (double) height;
			width = size;
			height = (int) (size / f);
		}
		return scaleImage(source, width, height);
	}

}
