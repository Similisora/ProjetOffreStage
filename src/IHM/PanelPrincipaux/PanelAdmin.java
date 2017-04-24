package IHM.PanelPrincipaux;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import DAO.CandidatureDAO;
import DAO.EntrepriseDAO;
import DAO.EtudiantDAO;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.Autre.ImageDeFond;
import IHM.Autre.Log;
import IHM.JDialog.JDialogProfilAdmin;
import IHM.JTable.tabledebut;
import IHM.JTable.tabledebutcandidat;
import IHM.JTable.tabledebutetudiant;
import IHM.JTable.tabledebutstage;
import IHM.JTable.tablefinal;
import IHM.JTable.tablefinalcandidat;
import IHM.JTable.tablefinaletudiant;
import IHM.JTable.tablefinalstage;
import Objet.Admin;

public class PanelAdmin extends ImageDeFond {
	//Le Panel Principal quand on se connecte en tant qu'Admin (la page d'acceuil)

	public JPanel mainPanel = new JPanel();
	public JPanel Gestion = new JPanel();
	public JPanel Annuler = new ImageDeFond("blanc.jpg", 1000);
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.BOLD, 20);
	Font italic = new Font(" Italic ", Font.ITALIC, 12);
	
	public JLabel connecte = new JLabel();
	JLabel version = new JLabel("Version Administrateur");
	
	JButton entreprise = new JButton (new ImageIcon ("Link Company Parent-50.png"));
	JButton stage = new JButton (new ImageIcon("Generic Book File Type-40.png"));
	JButton etudiant = new JButton (new ImageIcon ("Students-48.png"));
	JButton candidat = new JButton (new ImageIcon("Sign Up-40.png"));
	
	JButton profil = new JButton (new ImageIcon("User-50.png"));
	ImageIcon imageIcon = new ImageIcon(new ImageIcon("Shutdown-48.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	JButton deco = new JButton (imageIcon);
	
	JLabel j1 = new JLabel ("GESTION DES OFFRES");
	JLabel j2 = new JLabel("DE STAGE");
	
	Font police = new Font("TimesRoman", Font.ITALIC, 30);
	
	PanelAdmin me = this;
	
	Color c = new Color (20,119,166);

	public PanelAdmin(final Admin a, final Fenetre mere) {
		
		super("blanc.jpg",800);
		
		connecte.setText("Connecté en tant que "+a.getPrenom() + " " + a.getNom());
		connecte.setFont(italic);
		version.setFont(italic);
		
		j1.setFont(police);
		j2.setFont(police);
		j1.setBounds(250, 10, 350, 80);
		j2.setBounds(325, 42, 200, 80);
		
		me.setLayout(null);
		
		me.add(connecte);
		me.add(version);	
		me.add(j1);
		me.add(j2);
		me.add(entreprise);
		me.add(etudiant);
		me.add(stage);
		me.add(candidat);
		me.add(profil);
		me.add(deco);
		
		connecte.setBounds(550, 0, 300, 30);
		version.setBounds(100, 0, 150, 30);
		
		entreprise.setText("Liste des Entreprises");
		entreprise.setVerticalTextPosition(SwingUtilities.TOP);
		entreprise.setHorizontalTextPosition(SwingUtilities.CENTER);
		entreprise.setBounds(45, 65, 180, 100);
		entreprise.setForeground(c);
		
		etudiant.setText("Liste des Etudiants");
		etudiant.setVerticalTextPosition(SwingUtilities.TOP);
		etudiant.setHorizontalTextPosition(SwingUtilities.CENTER);
		etudiant.setBounds(45, 225, 180, 100);
		etudiant.setForeground(c);
		
		stage.setText("Liste des Offres de Stage");
		stage.setVerticalTextPosition(SwingUtilities.TOP);
		stage.setHorizontalTextPosition(SwingUtilities.CENTER);
		stage.setBounds(570, 65, 180, 100);
		stage.setForeground(c);
		
		candidat.setText("Liste des Candidatures");
		candidat.setVerticalTextPosition(SwingUtilities.TOP);
		candidat.setHorizontalTextPosition(SwingUtilities.CENTER);
		candidat.setBounds(570, 225, 180, 100);
		candidat.setForeground(c);
		
		profil.setText("Mon Profil");
		profil.setVerticalTextPosition(SwingUtilities.TOP);
		profil.setHorizontalTextPosition(SwingUtilities.CENTER);
		profil.setBounds(350, 160, 100, 80);
		profil.setForeground(c);
		
		deco.setBounds(750, 330, 26, 26);
		deco.setBackground(Color.white);
		
		
		profil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogProfilAdmin p = new JDialogProfilAdmin(a,me,mere);
			}
		});
		
		entreprise.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				//Affichage JTable
				EntrepriseDAO dao = new EntrepriseDAO();
				tabledebut modele = new tabledebut();
				modele = dao.affichertable(modele);
				tablefinal tj = new tablefinal(dao, modele);
				tj.setVisible(true);
			}
		});
		
		candidat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				//Affichage JTable
				CandidatureDAO dao = new CandidatureDAO();
				tabledebutcandidat modele = new tabledebutcandidat();
				modele = dao.affichertable(modele);
				tablefinalcandidat tj = new tablefinalcandidat(dao, modele);
				tj.setVisible(true);
			}
		});
		
		stage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				OffreStageDAO dao = new OffreStageDAO();
				tabledebutstage modele = new tabledebutstage();
				modele = dao.affichertable(modele);
				tablefinalstage tj = new tablefinalstage(dao, modele,mere);
				tj.setVisible(true);
			}
		});
		
		etudiant.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				EtudiantDAO dao = new EtudiantDAO();
				tabledebutetudiant modele = new tabledebutetudiant();
				modele = dao.affichertable(modele);
				tablefinaletudiant tj = new tablefinaletudiant(dao, modele);
				tj.setVisible(true);
				
			}
		});
		
		deco.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				mere.setVisible(false);
				Log log = new Log();
				log.setVisible(true);
				
			}
		});
		
	}

}
