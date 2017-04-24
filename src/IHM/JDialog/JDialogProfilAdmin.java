package IHM.JDialog;
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
import java.io.File;

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

import DAO.AdminDAO;
import DAO.EtudiantDAO;
import DAO.Md5;
import IHM.Fenetre;
import IHM.Autre.Log;
import IHM.PanelPrincipaux.PanelAdmin;
import IHM.PanelPrincipaux.PanelEtudiant;
import Objet.Admin;
import Objet.Etudiant;

public class JDialogProfilAdmin extends JDialog{
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.BOLD, 30);
	Font police = new Font(" TimesRoman", Font.ITALIC, 25);
	Font police2 = new Font(" TimesRoman", Font.BOLD, 25);
	JDialogProfilAdmin me = this;

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
	
	JPanel general = new JPanel();

	JLabel bienvenue = new JLabel("Informations du profil");
	JLabel detail = new JLabel();
	
	JLabel ID = new JLabel("ID :");
	JLabel nom = new JLabel("Nom :");
	JLabel prenom = new JLabel("Prénom :");
	JLabel mdp = new JLabel("Mot de Passe:");
	JLabel ville = new JLabel("Ville :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Mail :");
	JLabel lettrem = new JLabel("Lettre de Motivation :");
	JLabel cvm = new JLabel ("CV :");
	
	JLabel ID1 = new JLabel();
	JLabel nom1 = new JLabel();
	JLabel prenom1 = new JLabel();
	JLabel mdp1 = new JLabel();
	JLabel ville1 = new JLabel();
	JLabel rue1 = new JLabel();
	JLabel cp1 = new JLabel();
	JLabel tel1 = new JLabel();
	JLabel mail1 = new JLabel();

	JTextField tid = new JTextField(10);
	JTextField tnom = new JTextField(10);
	JTextField tprenom = new JTextField(10);
	JPasswordField tmdp = new JPasswordField(10);
	JTextField tville = new JTextField(10);
	JTextField tfrue = new JTextField(10);
	JTextField tcp = new JTextField(10);
	JTextField ttel = new JTextField(10);
	JTextField tmail = new JTextField(10);
	

	JButton modifier = new JButton(new ImageIcon ("Edit-48.png"));
	JButton retour = new JButton (new ImageIcon("Undo-48.png"));
	JButton cv = new JButton (new ImageIcon ("Resume-50.png"));
	JButton lettre = new JButton (new ImageIcon ("Word-48.png"));
	
	//profil d'un admin
	public JDialogProfilAdmin(final Admin a, final PanelAdmin pa, final Fenetre mere) {
		
		build();
		this.setSize(800, 580);// On lui donne une taille
		this.setTitle("Mon Profil"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tid.setText(String.valueOf(a.getID()));
		tnom.setText(a.getNom());
		tprenom.setText(a.getPrenom());
		tmdp.setText(Log.mdpSaisis);
		tville.setText(a.getVille());
		tfrue.setText(a.getRue());
		tcp.setText(a.getCodePostal());
		ttel.setText(a.getTelephone());
		tmail.setText(a.getMail());
		
		ID1.setText(String.valueOf(a.getID()));
		nom1.setText(a.getNom());
		prenom1.setText(a.getPrenom());
		mdp1.setText("*****");
		ville1.setText(a.getVille());
		rue1.setText(a.getRue());
		cp1.setText(a.getCodePostal());
		tel1.setText(a.getTelephone());
		mail1.setText(a.getMail());
		
		info.setLayout(new GridLayout(9,1,3,3));
		info.remove(J11);
		info.remove(J12);
		
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
				mere.setVisible(true);
			}

		});
		
		modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (modifier.getText().equals("Modifier")) {
					
					J1.remove(ID1);
					J1.add(tid);

					J2.remove(nom1);
					J2.add(tnom);

					J3.remove(prenom1);
					J3.add(tprenom);

					J4.remove(mdp1);
					J4.add(tmdp);

					J5.remove(ville1);
					J5.add(tville);
					
					J6.remove(rue1);
					J6.add(tfrue);
					
					J7.remove(cp1);
					J7.add(tcp);
					
					J8.remove(tel1);
					J8.add(ttel);
					
					J9.remove(mail1);
					J9.add(tmail);

					modifier.setText("Enregistrer les modifications");

				}

				else if (modifier.getText().equals("Enregistrer les modifications")) {
					
					char[] cmdp = tmdp.getPassword();
					String smdp = TabtoString(cmdp);
					boolean  flag = false; //Pour voir s'il y a eu des modifications ou non
					boolean flag2 = false; //Pour vérifier que le mdp n'est pas vide
					AdminDAO dao = new AdminDAO();
					Md5 md = new Md5(smdp);

					if (!tid.getText().equals(String.valueOf(a.getID()))) {
						a.setID(Integer.parseInt(tid.getText()));
						dao.update(a);
						flag = true;
					}
					
					if (!tnom.getText().equals(a.getNom())) {
						a.setNom(tnom.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!tprenom.getText().equals(a.getPrenom())) {
						a.setPrenom(tprenom.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!md.getCode().equals(a.getMdp()) && !smdp.isEmpty()) {
						a.setMdp(md.getCode());
						dao.update(a);
						flag = true;
						flag2 = false;
					}
					
					if (smdp.isEmpty()) { //Si le mot de passe est vide
						flag2 = true;
						JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (!tville.getText().equals(a.getVille())) {
						a.setVille(tville.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!tfrue.getText().equals(a.getRue())) {
						a.setRue(tfrue.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!tcp.getText().equals(a.getCodePostal())) {
						a.setCodePostal(tcp.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!ttel.getText().equals(a.getTelephone())) {
						a.setTelephone(ttel.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!tmail.getText().equals(a.getMail())) {
						a.setMail(tmail.getText());
						dao.update(a);
						flag = true;
					}
					
					if (!flag2) {//Seulement si le mot de passe n'est pas vide
					
					ID1.setText(String.valueOf(a.getID()));
					nom1.setText(a.getNom());
					prenom1.setText(a.getPrenom());
					ville1.setText(a.getVille());
					rue1.setText(a.getRue());
					cp1.setText(a.getCodePostal());
					tel1.setText(a.getTelephone());
					mail1.setText(a.getMail());
					
					J1.remove(tid);
					J1.add(ID1);

					J2.remove(tnom);
					J2.add(nom1);

					J3.remove(tprenom);
					J3.add(prenom1);

					J4.remove(tmdp);
					J4.add(mdp1);

					J5.remove(tville);
					J5.add(ville1);
					
					J6.remove(tfrue);
					J6.add(rue1);
					
					J7.remove(tcp);
					J7.add(cp1);
					
					J8.remove(ttel);
					J8.add(tel1);
					
					J9.remove(tmail);
					J9.add(mail1);

					modifier.setText("Modifier");
					
					if (flag) {
						JOptionPane.showMessageDialog(null, "Modifications effectuées !",
					
							"Information", JOptionPane.INFORMATION_MESSAGE);
						}
					else {
						JOptionPane.showMessageDialog(null, "Aucune Modification effectuée !",
								
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
					pa.connecte.setText("Connecté en tant que "+a.getPrenom() + " " + a.getNom());
					
					}
				}

			}
		});
		
	}
	
	//profil d'un étudiant
	public JDialogProfilAdmin(final Etudiant et, final PanelEtudiant pet, final Fenetre mere) {
		
		build();
		this.setSize(680, 680);// On lui donne une taille
		this.setTitle("Mon Profil"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tid.setText(String.valueOf(et.getID()));
		tnom.setText(et.getNom());
		tprenom.setText(et.getPrenom());
		tmdp.setText(Log.mdpSaisis);
		tville.setText(et.getVille());
		tfrue.setText(et.getRue());
		tcp.setText(et.getCodePostal());
		ttel.setText(et.getTelephone());
		tmail.setText(et.getMail());
		
		ID1.setText(String.valueOf(et.getID()));
		nom1.setText(et.getNom());
		prenom1.setText(et.getPrenom());
		mdp1.setText("*****");
		ville1.setText(et.getVille());
		rue1.setText(et.getRue());
		cp1.setText(et.getCodePostal());
		tel1.setText(et.getTelephone());
		mail1.setText(et.getMail());
		
		cv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogChoixCV choixCV = new JDialogChoixCV(et);
				choixCV.setVisible(true);
				
			}

		});
		
		lettre.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogChoixLettre choixLettre = new JDialogChoixLettre(et);
				choixLettre.setVisible(true);
			}

		});
		
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
				mere.setVisible(true);
			}

		});
		
		modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (modifier.getText().equals("Modifier")) {

					J2.remove(nom1);
					J2.add(tnom);

					J3.remove(prenom1);
					J3.add(tprenom);

					J4.remove(mdp1);
					J4.add(tmdp);

					J5.remove(ville1);
					J5.add(tville);
					
					J6.remove(rue1);
					J6.add(tfrue);
					
					J7.remove(cp1);
					J7.add(tcp);
					
					J8.remove(tel1);
					J8.add(ttel);
					
					J9.remove(mail1);
					J9.add(tmail);

					modifier.setText("Enregistrer les modifications");

				}

				else if (modifier.getText().equals("Enregistrer les modifications")) {
					
					char[] cmdp = tmdp.getPassword();
					String smdp = TabtoString(cmdp);
					boolean  flag = false;
					boolean flag2 = false;
					EtudiantDAO dao = new EtudiantDAO();
					Md5 md = new Md5(smdp);
					
					if (!tnom.getText().equals(et.getNom())) {
						et.setNom(tnom.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!tprenom.getText().equals(et.getPrenom())) {
						et.setPrenom(tprenom.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!md.getCode().equals(et.getMdp()) &&  !smdp.isEmpty()) {
						et.setMdp(md.getCode());
						dao.update(et);
						flag = true;
						flag2 = false;
					}
					
					if (smdp.isEmpty()) { //Si le mot de passe est vide
						flag2 = true;
						JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (!tville.getText().equals(et.getVille())) {
						et.setVille(tville.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!tfrue.getText().equals(et.getRue())) {
						et.setRue(tfrue.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!tcp.getText().equals(et.getCodePostal())) {
						et.setCodePostal(tcp.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!ttel.getText().equals(et.getTelephone())) {
						et.setTelephone(ttel.getText());
						dao.update(et);
						flag = true;
					}
					
					if (!tmail.getText().equals(et.getMail())) {
						et.setMail(tmail.getText());
						dao.update(et);
						flag = true;
					}
					
					if(!flag2) {
					
					nom1.setText(et.getNom());
					prenom1.setText(et.getPrenom());
					ville1.setText(et.getVille());
					rue1.setText(et.getRue());
					cp1.setText(et.getCodePostal());
					tel1.setText(et.getTelephone());
					mail1.setText(et.getMail());

					J2.remove(tnom);
					J2.add(nom1);

					J3.remove(tprenom);
					J3.add(prenom1);

					J4.remove(tmdp);
					J4.add(mdp1);

					J5.remove(tville);
					J5.add(ville1);
					
					J6.remove(tfrue);
					J6.add(rue1);
					
					J7.remove(tcp);
					J7.add(cp1);
					
					J8.remove(ttel);
					J8.add(tel1);
					
					J9.remove(tmail);
					J9.add(mail1);

					modifier.setText("Modifier");
					
					if (flag) {
						JOptionPane.showMessageDialog(null, "Modifications effectuées !",
					
							"Information", JOptionPane.INFORMATION_MESSAGE);
						}
					else {
						JOptionPane.showMessageDialog(null, "Aucune Modification effectuée !",
								
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
					pet.connecte.setText("Connecté en tant que "+et.getPrenom() + " " + et.getNom());
					
					}
				}

			}
		});
		
	}
	
	public void build() {

		this.add(general);
		
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
		bienvenue.setForeground(Color.white);
		description.add(detail);
		
		ID.setFont(police2);
		ID1.setFont(police);
		nom.setFont(police2);
		nom1.setFont(police);
		prenom.setFont(police2);
		prenom1.setFont(police);
		mdp.setFont(police2);
		mdp1.setFont(police);
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
		cp.setFont(police2);
		cvm.setFont(police);
		lettrem.setFont(police);
		
		cv.setText("Consulter le CV");
		cv.setVerticalTextPosition(SwingUtilities.TOP);
		cv.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		lettre.setText("Lettre de Motivation");
		lettre.setVerticalTextPosition(SwingUtilities.TOP);
		lettre.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		modifier.setText("Modifier");
		modifier.setVerticalTextPosition(SwingUtilities.TOP);
		modifier.setHorizontalTextPosition(SwingUtilities.CENTER);
		
		retour.setText("Retour");
		retour.setVerticalTextPosition(SwingUtilities.TOP);
		retour.setHorizontalTextPosition(SwingUtilities.CENTER);

		J1.add(ID);
		J1.add(ID1);

		J2.add(nom);
		J2.add(nom1);

		J3.add(prenom);
		J3.add(prenom1);

		J4.add(mdp);
		J4.add(mdp1);
		
		J5.add(ville);
		J5.add(ville1);
		
		J6.add(rue);
		J6.add(rue1);
		
		J7.add(cp);
		J7.add(cp1);
		
		J8.add(tel);
		J8.add(tel1);
		
		J9.add(mail);
		J9.add(mail1);
		
		J10.add(modifier);
		J10.add(retour);
		
		J11.add(cv);
		
		J12.add(lettre);
	
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