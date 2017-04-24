package IHM.PanelListe;
import java.awt.BorderLayout;
import java.awt.Color;
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
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.JDialog.JDialogDescription;
import IHM.JDialog.JDialogModifierOffreStage;
import IHM.JTable.tablefinalstage;
import Objet.Candidature;
import Objet.Etudiant;
import Objet.OffreStage;

public class PanelUniqueOffre extends JPanel {
	//Un panel qui décrit une offre de stage
	//Il apparaitrat dans le JPanelOffre

	public OffreStage offre;
	
	Font police = new Font(" TimesRoman", Font.ITALIC, 15);
	BorderLayout layout = new BorderLayout();
	
	JLabel description = new JLabel();
	JLabel duree = new JLabel();
	JLabel date = new JLabel();
	JLabel libelle = new JLabel();
	JLabel valide = new JLabel();
	JLabel domaine = new JLabel();
	
	JPanel panel1 = new ImageDeFond("blanc.jpg", 340);
	JPanel panel2 = new ImageDeFond("blanc.jpg", 340);
	
	JPanel p1 = new ImageDeFond("blanc.jpg", 340);
	JPanel p2 = new ImageDeFond("blanc.jpg", 340);
	
	JPanel panelgeneral = new JPanel();
	
	JButton modifier = new JButton(new ImageIcon("Edit-48.png"));
	JButton supprimer = new JButton(new ImageIcon("Delete Property-48.png"));
	JButton voir = new JButton(new ImageIcon("User-50.png"));
	JButton desc = new JButton("Voir");
	
	JPanel pan = new ImageDeFond("blanc.jpg");
	JPanel infoFilm = new ImageDeFond("blanc.jpg");
	JPanel panelBouton = new ImageDeFond("blanc.jpg");
	
	PanelUniqueOffre me = this;
	
	//Pour afficher les offres quand on est connecté en tant qu'entreprise
	public PanelUniqueOffre (final OffreStage offre, final JPanelOffre j, final Fenetre mere, final Fenetre f) {

		super();
		this.offre = offre;
		final OffreStageDAO dao = new OffreStageDAO();
		
		String str = new String();
		if(offre.isValide()) str = "Oui";
		else str = "Non";
		
		Color c = new Color (111,206,220);
		me.setBackground(c);
		
		modifier.setText("Modifier");
		modifier.setVerticalTextPosition(SwingUtilities.TOP);
		modifier.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		supprimer.setText("Supprimer");
		supprimer.setForeground(Color.red);
		supprimer.setVerticalTextPosition(SwingUtilities.TOP);
		supprimer.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		voir.setText("Voir candidat");
		voir.setVerticalTextPosition(SwingUtilities.TOP);
		voir.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		
		libelle.setText("Libellé : " + offre.getLibelléOffre());
		libelle.setFont(police);
		
		domaine.setText("Domaine : " + offre.getDomaineOffre());
		domaine.setFont(police);
		
		description.setText("Description : ");
		description.setFont(police);

		duree.setText("\nDurée : " + offre.getDuréeOffre()+" mois");
		duree.setFont(police);

		date.setText("\nDébut : " + offre.getDateDébutOffre());
		date.setFont(police);
		
		if (offre.isChanger()) {
			valide.setText("\nCANDIDATURE ANNULEE");
			valide.setFont(police);
			valide.setForeground(Color.red);
			offre.setChanger(false);
			dao.update(offre);
		}
		else {
		valide.setText("\nValide : " + str);
		valide.setFont(police);
		}

		build();
		
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				JOptionPane.showMessageDialog(null, "Offre de Stage "+offre.getID()+" supprimée avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				
				ArrayList<Candidature> liste = new ArrayList<Candidature>();
				CandidatureDAO dao2 = new CandidatureDAO();
				liste = dao2.find(offre.getID());
				
				for (int i = 0 ; i<liste.size() ; i++) {
					dao2.delete(liste.get(i).getID());
				}
				dao.delete(offre.getID());
				
				f.setVisible(false);
				mere.setVisible(true);
			}
		});
		
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {


				tablefinalstage table = new tablefinalstage(dao, null,mere);
				JDialogModifierOffreStage j = new JDialogModifierOffreStage (offre,table,mere);
				f.setVisible(false);
				
			}
		});
		
		desc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				final Fenetre fe = new Fenetre ();
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				fe.setTitle("Description de l'Offre");
				fe.setSize(650, 400);
				JDialogDescription d = new JDialogDescription(offre);
				fe.add(d);
				fe.getContentPane().add(new JScrollPane(d.textArea), BorderLayout.CENTER);
				fe.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						fe.setVisible(false);
						f.setVisible(true);
						
					}
				});
			}
		});
		
		voir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if (!voir.getText().equals("Aucun candidat")) {
					
				final Fenetre fe = new Fenetre ();
				JPanel pan = new JPanel();
				JButton retour = new JButton("Retour");
				pan.add(retour);
				f.setVisible(false);
				fe.setTitle("Liste des Etudiants");
				fe.setSize(700, 400);
				JPanelListeEtudiant j = new JPanelListeEtudiant(fe,mere,offre,f);
				fe.add(j);
				fe.getContentPane().add(new JScrollPane(j.panel), BorderLayout.CENTER);
				fe.getContentPane().add(pan, BorderLayout.SOUTH);
				
				retour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						
						fe.setVisible(false);
						f.setVisible(true);
						
					}
				});
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Aucun candidat pour cette offre !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
	}
	
	//Pour afficher les offres quand on est connecté en tant qu'étudiant
	public PanelUniqueOffre (final OffreStage offre, final Fenetre f2, final Fenetre f, final Etudiant et, final Fenetre mere) {

		super();
		this.offre = offre;
		
		String str = new String();
		if(offre.isValide()) str = "Oui";
		else str = "Non";
		
		libelle.setText(offre.getLibelléOffre());
		libelle.setFont(police);
		
		domaine.setText("Domaine : " + offre.getDomaineOffre());
		domaine.setFont(police);

		duree.setText("\nDurée : " + offre.getDuréeOffre()+" mois");
		duree.setFont(police);

		date.setText("\nDébut : " + offre.getDateDébutOffre());
		date.setFont(police);

		me.setLayout(new GridLayout(1,2));
		me.add(infoFilm);
		me.add(pan);
		pan.setLayout(new GridLayout(1,2));
		JButton desc = new JButton (new ImageIcon ("Survey-50.png"));
		JButton post = new JButton (new ImageIcon ("Sign Up-40.png"));
		pan.add(desc);
		pan.add(post);
		
		desc.setText("Voir Description de l'Offre");
		desc.setVerticalTextPosition(SwingUtilities.TOP);
		desc.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		post.setText("Postuler pour cette Offre !");
		post.setVerticalTextPosition(SwingUtilities.TOP);
		post.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		infoFilm.setLayout(new GridLayout(1,2));
		infoFilm.add(p1);
		infoFilm.add(p2);
		
		p1.setLayout(new GridLayout(2,1));
		p2.setLayout(new GridLayout(2,1));
		
		p1.add(libelle);
		p1.add(domaine);
		p2.add(duree);
		p2.add(date);

		this.libelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.domaine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		post.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				boolean flag = false;
				CandidatureDAO dao = new CandidatureDAO();
				ArrayList<Candidature> liste = new ArrayList<Candidature>();
				liste = dao.find(offre.getID());
				
				offre.getListeCandidature().removeAll(offre.getListeCandidature());
				for (int i = 0 ; i < liste.size() ; i++) {
					offre.getListeCandidature().add(liste.get(i));
				}
				
				//Boucle qui passe le flag à true si l'étudiant à déjà postulé une offre pour ce stage
				for (int i = 0 ; i < offre.getListeCandidature().size() ; i++)
				if (et.getID() == offre.getListeCandidature().get(i).getIDEtudiant()) {
					flag = true;
				}
				
				//Si tout va bien
				if (!flag) {
					Candidature c = new Candidature ("En attente",offre.getID(),et.getID());
					dao.create(c);
					JOptionPane.showMessageDialog(null, "Votre candidature a bien été envoyée !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					f.setVisible(false);
					f2.setVisible(false);
					mere.setVisible(true);
				}
				
				//Si l'étudiant a déjà postulé
				else {
					
					JOptionPane.showMessageDialog(null, "IMPOSSIBLE ! Candidature déjà envoyée !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		
		desc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				Fenetre f = new Fenetre ();
				//mere.setVisible(false);
				f.setTitle("Description de l'Offre");
				f.setSize(650, 400);
				JDialogDescription d = new JDialogDescription(offre);
				f.add(d);
				f.getContentPane().add(new JScrollPane(d.textArea), BorderLayout.CENTER);
			}
		});
		
	}

	public void build() {

		this.add(panelgeneral);
		
		panelgeneral.setLayout(new GridLayout(1,2));
		panelgeneral.add(infoFilm);
		panelgeneral.add(panelBouton);
		
		infoFilm.setLayout(new GridLayout(1,2));
		infoFilm.add(p1);
		infoFilm.add(p2);
		
		p1.setLayout(new GridLayout(3,1));
		p2.setLayout(new GridLayout(3,1));
		
		p1.add(libelle);
		p1.add(domaine);
		p1.add(pan);
		pan.add(description);
		pan.add(desc);
		
		p2.add(duree);
		p2.add(date);
		p2.add(valide);

		this.libelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.valide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.domaine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		panelBouton.setLayout(new GridLayout(1,2));
		panelBouton.add(panel1);
		panelBouton.add(panel2);
		panel1.add(modifier);
		panel1.add(supprimer);
		
		CandidatureDAO dao = new CandidatureDAO();
		ArrayList<Candidature> liste = new ArrayList<Candidature>();
		liste = dao.find(offre.getID());
		
		offre.getListeCandidature().removeAll(offre.getListeCandidature());
		for (int i = 0 ; i < liste.size() ; i++) {
			offre.getListeCandidature().add(liste.get(i));
		}
		
		String str = new String();
		int compt = 0;
		
		//Boucle pour incrémenter le nombre de candidat à l'offre
		//On vérifie que c'est en attente, sinon ça va afficher même ceux acceptés
		for (int i = 0 ; i<offre.getListeCandidature().size() ; i++) {
			if (offre.getListeCandidature().get(i).getStatut().equals("En attente")) {
				compt ++;
			}
			
		}
		
		//Pour ne pas afficher "voir les 0 candidats"
		if (compt == 0) offre.getListeCandidature().removeAll(offre.getListeCandidature()); 
		
		//Afficher la nombre de candidat à cette offre
		if (offre.getListeCandidature().size()>0) str = " Voir les "+compt+ " candidats";
		else str = "Aucun candidat";
		
		voir.setText(str);
		panel2.add(voir);

	}

}