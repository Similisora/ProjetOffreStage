package IHM.JDialog;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import DAO.Md5;
import IHM.Autre.Log;
import IHM.JTable.tabledebut;
import Objet.Entreprise;

public class JDialogCreerEntreprise extends JDialog {
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.ITALIC, 20);
	JDialogCreerEntreprise me = this;

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

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel ville = new JLabel("Ville :");
	JLabel nom = new JLabel("Nom :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Adresse mail :");
	JLabel secteur = new JLabel("Secteur d'activité :");
	JLabel raison = new JLabel("Raison Sociale :");
	JLabel mdp = new JLabel("Mot de Passe:");

	JTextField tnom = new JTextField(15);
	JTextField ttel = new JTextField(15);
	JTextField tcp = new JTextField(15);
	JTextField tville = new JTextField(15);
	JTextField tid = new JTextField(15);
	JTextField tfrue = new JTextField(15);
	JTextField tmail = new JTextField(15);
	JTextField tsecteur = new JTextField(15);
	JTextField traison = new JTextField(15);
	JPasswordField tmdp = new JPasswordField(15);

	JButton go = new JButton("S'inscrire !");
	JButton retour = new JButton ("Retour");

	//Quand c'est l'Admin qui en créé une
	public JDialogCreerEntreprise (final tabledebut modele) {
		
		build();
		this.setSize(600, 280);
		this.setTitle("Ajouter une Entreprise");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String snom = tnom.getText();
				String sville = tville.getText();
				String scp = tcp.getText();
				String srue = tfrue.getText();
				String stel = ttel.getText();
				String smail = tmail.getText();
				String ssecteur = tsecteur.getText();
				String sraison = traison.getText();
				char[] cmdp = tmdp.getPassword();
				String smdp = TabtoString(cmdp);
				String logo = "avatar.jpg";
				
				Md5 md = new Md5(smdp);
				
				if (snom.isEmpty() || sville.isEmpty() || scp.isEmpty() || srue.isEmpty()
						|| stel.isEmpty() || smail.isEmpty() || ssecteur.isEmpty() || sraison.isEmpty() || cmdp.length == 0) {
					JOptionPane.showMessageDialog(null, "Erreur : Champ vide",
							"Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				} 
				
				else {

				Entreprise e1 = new Entreprise(md.getCode(),snom,sville,srue,scp,stel,smail,ssecteur,sraison,logo);
				EntrepriseDAO dao = new EntrepriseDAO();
				dao.create(e1);
				
				//Ajout au jTable
				modele.addEntreprise(e1);
				
				me.setVisible(false);
				JOptionPane.showMessageDialog(null, "Vous venez de créer une entreprise avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}

		});
	}
	
	//Quand on s'inscrit
	public JDialogCreerEntreprise () {
		
		build();
		this.setSize(600, 280);
		this.setTitle("Inscription d'une Entreprise");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				//Créer des variables locales avec ce que l'utilisateur a rentré dans les textfields
				String snom = tnom.getText();
				String sville = tville.getText();
				String scp = tcp.getText();
				String srue = tfrue.getText();
				String stel = ttel.getText();
				String smail = tmail.getText();
				String ssecteur = tsecteur.getText();
				String sraison = traison.getText();
				String slogo = "avatar.jpg";
				char[] cmdp = tmdp.getPassword();
				String smdp = TabtoString(cmdp);
				
				Md5 md = new Md5(smdp);
				
				//Vérifier que les champs ne sont pas vide
				if (snom.isEmpty() || sville.isEmpty() || scp.isEmpty() || srue.isEmpty()
						|| stel.isEmpty() || smail.isEmpty() || ssecteur.isEmpty() || sraison.isEmpty() || cmdp.length == 0) {
					JOptionPane.showMessageDialog(null, "Erreur : Champ vide",
							"Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				} 
				
				else {
			
					EntrepriseDAO dao = new EntrepriseDAO();
					
					//Je prends toute la liste des Entreprises existantes
					ArrayList <Entreprise> liste = new ArrayList <Entreprise>();
					liste = dao.afficher2(liste);
					boolean flag = false;
					
					//Si le Mail est déjà prit : ERROR + je passe le flag à true
					for( int i = 0 ; i<liste.size() ; i++) {
						if (liste.get(i).getMail().equals(smail)) {
							flag = true;
							JOptionPane.showMessageDialog(null, "Erreur : Mail déjà utilisé",
									"Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					//Si le flag est resté à false (mail OK) je créé une nouvelle entreprise et je l'ajoute à la BDD
					if (!flag) {		
					Entreprise e1 = new Entreprise (md.getCode(),snom,sville,srue,scp,stel,smail,ssecteur,sraison,slogo);
					dao.create(e1);
					Entreprise e = dao.find_id(e1.getMail());
					
					me.setVisible(false);
					JOptionPane.showMessageDialog(null, "Vous venez de vous inscrire avec succès ! Votre ID de connexion est"+e.getID()+" ",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					Log fi = new Log();
					}
					
				}
			}
		});
	
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
				Log log = new Log();
			}
		});
		
	}
	
	public void build() {

		this.setLayout(new FlowLayout());

		this.add(acceuil);
		this.add(description);
		this.add(J2);
		this.add(J3);
		this.add(J4);
		this.add(J5);
		this.add(J6);
		this.add(J7);
		this.add(J8);
		this.add(J9);
		this.add(J11);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J2.add(nom);
		J2.add(tnom);

		J3.add(ville);
		J3.add(tville);

		J4.add(cp);
		J4.add(tcp);

		J5.add(rue);
		J5.add(tfrue);
		
		J6.add(tel);
		J6.add(ttel);
		
		J7.add(mail);
		J7.add(tmail);
		
		J8.add(secteur);
		J8.add(tsecteur);
		
		J9.add(raison);
		J9.add(traison);
		
		J11.add(mdp);
		J11.add(tmdp);
		J11.add(go);
		J11.add(retour);
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
