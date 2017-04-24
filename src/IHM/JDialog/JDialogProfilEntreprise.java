package IHM.JDialog;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import DAO.Md5;
import IHM.Fenetre;
import IHM.Autre.Log;
import IHM.PanelPrincipaux.PanelEntreprise;
import Objet.Entreprise;

public class JDialogProfilEntreprise extends JDialog{
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.BOLD, 20);
	JDialogProfilEntreprise me = this;
	
	Font police = new Font(" TimesRoman", Font.ITALIC, 25);
	Font police2 = new Font(" TimesRoman", Font.BOLD, 25);
	
	JPanel general = new JPanel();

	JPanel acceuil = new JPanel();
	JPanel description = new JPanel();
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	JPanel J3 = new JPanel();
	JPanel J4 = new JPanel();
	JPanel J5 = new JPanel();
	JPanel J6 = new JPanel();
	JPanel J7 = new JPanel();
	JPanel J8 = new JPanel();
	JPanel J9 = new JPanel();
	JPanel J10 = new JPanel();
	JPanel J11 = new JPanel();
	JPanel J12 = new JPanel();
	JPanel info = new JPanel();

	JLabel bienvenue = new JLabel("Informations du profil");
	JLabel detail = new JLabel();
	
	JLabel ID = new JLabel("ID :");
	JLabel mdp = new JLabel("Mot de passe :");
	JLabel nom = new JLabel("Nom :");
	JLabel ville = new JLabel("Ville :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Mail :");
	JLabel secteur = new JLabel("Secteur d'Actitivé :");
	JLabel raison = new JLabel("Raison Sociale :");
	
	JLabel ID1 = new JLabel();
	JLabel mdp1 = new JLabel();
	JLabel nom1 = new JLabel();
	JLabel ville1 = new JLabel();
	JLabel rue1 = new JLabel();
	JLabel cp1 = new JLabel();
	JLabel tel1 = new JLabel();
	JLabel mail1 = new JLabel();
	JLabel secteur1 = new JLabel();
	JLabel raison1 = new JLabel();

	JTextField tid = new JTextField(15);
	JPasswordField tmdp = new JPasswordField(15);
	JTextField tnom = new JTextField(15);
	JTextField tville = new JTextField(15);
	JTextField tfrue = new JTextField(15);
	JTextField tcp = new JTextField(15);
	JTextField ttel = new JTextField(15);
	JTextField tmail = new JTextField(15);
	JTextField tsecteur = new JTextField(15);
	JTextField traison = new JTextField(15);
	

	JButton modifier = new JButton(new ImageIcon ("Edit-48.png"));
	JButton jlogo = new JButton (new ImageIcon ("Picture-50.png"));
	JButton retour = new JButton (new ImageIcon("Undo-48.png"));
	
	//Pour afficher son profil quand on est connecté en tant qu'entreprise
	public JDialogProfilEntreprise(final Entreprise e, final PanelEntreprise pe, final Fenetre mere) {
		
		build();
		this.setSize(860, 670);
		this.setTitle("Mon Profil");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tmdp.setText(Log.mdpSaisis);
		tnom.setText(e.getNom());
		tville.setText(e.getVille());
		tfrue.setText(e.getRue());
		tcp.setText(e.getCodePostal());
		ttel.setText(e.getTelephone());
		tmail.setText(e.getMail());
		tsecteur.setText(e.getSecteurActivité());
		traison.setText(e.getRaisonSociale());
		
		ID1.setText(String.valueOf(e.getID()));
		mdp1.setText("*****");
		nom1.setText(e.getNom());
		ville1.setText(e.getVille());
		rue1.setText(e.getRue());
		cp1.setText(e.getCodePostal());
		tel1.setText(e.getTelephone());
		mail1.setText(e.getMail());
		secteur1.setText(e.getSecteurActivité());
		raison1.setText(e.getRaisonSociale());
		
		modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (modifier.getText().equals("Modifier")) {

					J2.remove(mdp1);
					J2.add(tmdp);

					J3.remove(nom1);
					J3.add(tnom);

					J4.remove(ville1);
					J4.add(tville);

					J5.remove(rue1);
					J5.add(tfrue);
					
					J6.remove(cp1);
					J6.add(tcp);
					
					J7.remove(tel1);
					J7.add(ttel);
					
					J8.remove(mail1);
					J8.add(tmail);
					
					J9.remove(secteur1);
					J9.add(tsecteur);

					J10.remove(raison1);
					J10.add(traison);

					modifier.setText("Enregistrer les modifications");

				}

				else if (modifier.getText().equals("Enregistrer les modifications")) {
					
					char[] cmdp = tmdp.getPassword();
					String smdp = TabtoString(cmdp);
					boolean  flag = false;
					boolean  flag2 = false; //Vérifier que le mdp n'est pas vide
					EntrepriseDAO dao = new EntrepriseDAO();
					Md5 md = new Md5(smdp);
					
					if (!md.getCode().equals(e.getMdp()) && !smdp.isEmpty()) {
						e.setMdp(md.getCode());
						dao.update(e);
						flag = true;
						flag2 = false;
					}
					
					if (smdp.isEmpty()) { //Si le mot de passe est vide
						flag2 = true;
						JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
			
					if (!tnom.getText().equals(e.getNom())) {
						e.setNom(tnom.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!tville.getText().equals(e.getVille())) {
						e.setVille(tville.getText());
						dao.update(e);
						flag = true;
					}
	
					if (!tfrue.getText().equals(e.getRue())) {
						e.setRue(tfrue.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!tcp.getText().equals(e.getCodePostal())) {
						e.setCodePostal(tcp.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!ttel.getText().equals(e.getTelephone())) {
						e.setTelephone(ttel.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!tmail.getText().equals(e.getMail())) {
						e.setMail(tmail.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!tsecteur.getText().equals(e.getSecteurActivité())) {
						e.setSecteurActivité(tsecteur.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!traison.getText().equals(e.getRaisonSociale())) {
						e.setRaisonSociale(traison.getText());
						dao.update(e);
						flag = true;
					}
					
					if (!flag2) {
					nom1.setText(e.getNom());
					ville1.setText(e.getVille());
					rue1.setText(e.getRue());
					cp1.setText(e.getCodePostal());
					tel1.setText(e.getTelephone());
					mail1.setText(e.getMail());
					secteur1.setText(e.getSecteurActivité());
					raison1.setText(e.getRaisonSociale());

					J2.remove(tmdp);
					J2.add(mdp1);

					J3.remove(tnom);
					J3.add(nom1);

					J4.remove(tville);
					J4.add(ville1);

					J5.remove(tfrue);
					J5.add(rue1);
					
					J6.remove(tcp);
					J6.add(cp1);
					
					J7.remove(ttel);
					J7.add(tel1);
					
					J8.remove(tmail);
					J8.add(mail1);
					
					J9.remove(tsecteur);
					J9.add(secteur1);

					J10.remove(traison);
					J10.add(raison1);

					modifier.setText("Modifier");
					
					if (flag) {
						JOptionPane.showMessageDialog(null, "Modifications effectuées !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
						}
					else {
						JOptionPane.showMessageDialog(null, "Aucune Modification effectuée !",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
					pe.connecte.setText("Connecté en tant que "+e.getNom());
					
					}
				}

			}
		});
		
		jlogo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JFileChooser choix = new JFileChooser();
				int retour=choix.showOpenDialog(getParent());
				if(retour==JFileChooser.APPROVE_OPTION){
				   // un fichier a été choisi (sortie par OK)
				   // nom du fichier  choisi 
				   choix.getSelectedFile().getName();
				   // chemin absolu du fichier choisi
				   choix.getSelectedFile().getAbsolutePath();
				   
				   EntrepriseDAO dao = new EntrepriseDAO();
				   e.setLogo(choix.getSelectedFile().getAbsolutePath());
				   dao.update(e);
				   JOptionPane.showMessageDialog(null, "Logo modifié !",	
							"Information", JOptionPane.INFORMATION_MESSAGE);
				   
				   
				}else;
			}

		});
		
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
				mere.setVisible(true);
			}

		});
		
	}
	
	//Pour Afficher les détails de l'entreprise quand on est connecté en tant qu'étudiant
	public JDialogProfilEntreprise(final Entreprise e) {
		
		this.setSize(420, 240);// On lui donne une taille
		this.setTitle("Profil de L'entreprise"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		nom1.setText(e.getNom());
		ville1.setText(e.getVille());
		rue1.setText(e.getRue());
		cp1.setText(e.getCodePostal());
		tel1.setText(e.getTelephone());
		mail1.setText(e.getMail());
		secteur1.setText(e.getSecteurActivité());
		
		this.setLayout(new FlowLayout());
		
		JButton back = new JButton ("Retour");
		
		info.setLayout(new GridLayout(7,1));
		this.add(acceuil);
		this.add(description);
		this.add(info);
		info.add(J3);
		info.add(J4);
		info.add(J5);
		info.add(J6);
		info.add(J7);
		info.add(J8);
		info.add(J9);
		info.add(J11);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);
		description.add(detail);

		J3.add(nom);
		J3.add(nom1);
		
		J4.add(ville);
		J4.add(ville1);
		
		J5.add(rue);
		J5.add(rue1);
		
		J6.add(cp);
		J6.add(cp1);
		
		J7.add(tel);
		J7.add(tel1);
		
		J8.add(mail);
		J8.add(mail1);
		
		J9.add(secteur);
		J9.add(secteur1);
		
		J11.add(back);
		
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);

			}
		});
		
	}
	
	public void build() {

		this.add(general);
		
		modifier.setText("Modifier");
		modifier.setVerticalTextPosition(SwingUtilities.TOP);
		modifier.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		retour.setText("Retour");
		retour.setVerticalTextPosition(SwingUtilities.TOP);
		retour.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		jlogo.setText("Mon Logo");
		jlogo.setVerticalTextPosition(SwingUtilities.TOP);
		jlogo.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		bienvenue.setForeground(Color.white);
		
		info.setLayout(new GridLayout(11,1,3,3));
		general.add(acceuil);
		general.add(description);
		general.add(info);
		info.add(J1);
		info.add(J2);
		info.add(J3);
		info.add(J4);
		info.add(J5);
		info.add(J6);
		info.add(J7);
		info.add(J8);
		info.add(J9);
		info.add(J10);
		info.add(J11);
		info.add(J12);

		Color c = new Color (111,206,220);
		info.setBackground(c);
		description.setBackground(c);
		acceuil.setBackground(c);
		general.setBackground(c);
		J1.setBackground(Color.white);
		J2.setBackground(Color.white);
		J3.setBackground(Color.white);
		J4.setBackground(Color.white);
		J5.setBackground(Color.white);
		J6.setBackground(Color.white);
		J7.setBackground(Color.white);
		J8.setBackground(Color.white);
		J9.setBackground(Color.white);
		J10.setBackground(Color.white);
		J11.setBackground(Color.white);
		J12.setBackground(Color.white);
		
		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);
		description.add(detail);
		
		ID.setFont(police2);
		ID1.setFont(police);
		mdp.setFont(police2);
		mdp1.setFont(police);
		nom.setFont(police2);
		nom1.setFont(police);
		ville.setFont(police2);
		ville1.setFont(police);
		rue.setFont(police2);
		rue1.setFont(police);
		cp.setFont(police2);
		cp1.setFont(police);
		tel.setFont(police2);
		tel1.setFont(police);
		mail.setFont(police2);
		mail1.setFont(police);
		secteur.setFont(police2);
		secteur1.setFont(police);
		raison.setFont(police2);
		raison1.setFont(police);

		J1.add(ID);
		J1.add(ID1);

		J2.add(mdp);
		J2.add(mdp1);

		J3.add(nom);
		J3.add(nom1);
		
		J4.add(ville);
		J4.add(ville1);
		
		J5.add(rue);
		J5.add(rue1);
		
		J6.add(cp);
		J6.add(cp1);
		
		J7.add(tel);
		J7.add(tel1);
		
		J8.add(mail);
		J8.add(mail1);
		
		J9.add(secteur);
		J9.add(secteur1);
		
		J10.add(raison);
		J10.add(raison1);
		
		J11.add(jlogo);
		
		J12.add(modifier);
		J12.add(retour);
	
	}
	
	public String TabtoString(char[] tab) {
		int i = 0;
		String str = new String();
		while (i < tab.length) {
			char a = tab[i];
			str += a;
			i++;

		}
		return str;
	}
	
}