package IHM.PanelListe;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import DAO.CandidatureDAO;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.JDialog.JDialogProfilEntreprise;
import IHM.JDialog.JDialogProfilOffre;
import Objet.Candidature;
import Objet.Entreprise;
import Objet.OffreStage;

public class PanelUniqueCandidature extends JPanel {
	//Un panel qui décrit une candidature
	//Il apparaitrat dans le JPanelListeCandidature
	
	JLabel nomEntreprise = new JLabel();
	JLabel nomOffre = new JLabel();
	JLabel etat = new JLabel();
	
	Entreprise e = new Entreprise();
	OffreStage o = new OffreStage();
	Candidature c = new Candidature();
	
	public Image ImageAffiche;
	public String nomImage;
	public JLabel LabelAffiche;
	
	Font police = new Font(" TimesRoman", Font.ITALIC, 25);
	
	JPanel info = new ImageDeFond("blanc.jpg");
	JPanel panelBouton = new ImageDeFond("blanc.jpg");
	JPanel panelgeneral = new ImageDeFond("blanc.jpg");
	PanelUniqueCandidature me = this;
	
	JButton entreprise = new JButton(new ImageIcon ("Link Company Parent-50.png"));
	JButton offre = new JButton(new ImageIcon ("Generic Book File Type-40.png"));
	JButton annuler = new JButton(new ImageIcon ("Delete-48.png"));
	
	public PanelUniqueCandidature (final Entreprise e, final OffreStage o, final Candidature c, final Fenetre mere, final Fenetre f) {
		
		super();
		this.e = e;
		this.o = o;
		this.c = c;
		
		entreprise.setText("Détails sur " + e.getNom());	
		offre.setText("Détails sur " + o.getLibelléOffre());
		
		etat.setFont(police);
		
		build();
		
		entreprise.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {			

				JDialogProfilEntreprise p = new JDialogProfilEntreprise(e);
				p.setVisible(true);
			}
		});
		
		offre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogProfilOffre p = new JDialogProfilOffre(o);
				
			}
		});
		
		annuler.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				CandidatureDAO dao = new CandidatureDAO();
				OffreStageDAO dao2 = new OffreStageDAO();
				dao.delete(c.getID());
				o.setValide(true);
				o.setChanger(true);
				dao2.update(o);
				
				mere.setVisible(false);
				f.setVisible(true);
				
				JOptionPane.showMessageDialog(null, "La candidature à l'offre "+o.getLibelléOffre()+" a bien été retirée !",	
						"Information", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
		
	}
	
	public void build() {
		
		ImageIcon ImageAfficheFilm = new ImageIcon(e.getLogo());
		ImageAffiche = scaleImage(ImageAfficheFilm.getImage(), 240);
		LabelAffiche = new JLabel(new ImageIcon(ImageAffiche));

		this.add(panelgeneral);
		
		Color color = new Color (111,206,220);
		me.setBackground(color);
		
		panelgeneral.setLayout(new GridLayout(1,2));
		panelgeneral.add(info);
		panelgeneral.add(panelBouton);
		
		info.add(LabelAffiche);
		
		panelBouton.setLayout(new GridLayout(2,2));
		panelBouton.add(entreprise);
		panelBouton.add(offre);
		panelBouton.add(etat);
		panelBouton.add(annuler);
		etat.setText(c.getStatut());	
		
		entreprise.setVerticalTextPosition(SwingUtilities.TOP);
		entreprise.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		offre.setVerticalTextPosition(SwingUtilities.TOP);
		offre.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		
		annuler.setText("Annuler sa candidature");
		annuler.setForeground(Color.red);
		annuler.setVerticalTextPosition(SwingUtilities.TOP);
		annuler.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		this.etat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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