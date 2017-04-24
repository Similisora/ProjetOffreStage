package IHM.JDialog;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import DAO.EtudiantDAO;
import DAO.Md5;
import IHM.Autre.Log;
import IHM.JTable.tabledebutetudiant;
import Objet.Entreprise;
import Objet.Etudiant;

public class JDialogCreerEtudiant extends JDialog {
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.ITALIC, 20);
	JDialogCreerEtudiant me = this;

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

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel ville = new JLabel("Ville :");
	JLabel nom = new JLabel("Nom :");
	JLabel prenom = new JLabel("Prénom :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Adresse mail :");
	JLabel mdp = new JLabel("Mot de Passe:");

	JTextField tnom = new JTextField(15);
	JTextField tprenom = new JTextField(15);
	JTextField ttel = new JTextField(15);
	JTextField tcp = new JTextField(15);
	JTextField tville = new JTextField(15);
	JTextField tid = new JTextField(15);
	JTextField tfrue = new JTextField(15);
	JTextField tmail = new JTextField(15);
	JPasswordField tmdp = new JPasswordField(15);

	JButton go = new JButton("S'inscrire !");
	JButton retour = new JButton ("Retour");

	//Quand c'est l'Admin qui en créé un
	public JDialogCreerEtudiant (final tabledebutetudiant modele) {
		
		build();
		this.setSize(600, 270);
		this.setTitle("Ajouter un Etudiant");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String snom = tnom.getText();
				String sprenom = tprenom.getText();
				String sville = tville.getText();
				String scp = tcp.getText();
				String srue = tfrue.getText();
				String stel = ttel.getText();
				String smail = tmail.getText();
				char[] cmdp = tmdp.getPassword();
				String smdp = TabtoString(cmdp);
				
				Md5 md = new Md5(smdp);

				if (snom.isEmpty() || sprenom.isEmpty() || sville.isEmpty()
						|| scp.isEmpty() || srue.isEmpty() || stel.isEmpty() || smail.isEmpty() || cmdp.length == 0) {
					JOptionPane.showMessageDialog(null, "Erreur : Champ vide",
							"Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				} 
				
				else {
				Etudiant et1 = new Etudiant(md.getCode(),sprenom,smdp,sville,srue,scp,stel,smail);
				EtudiantDAO dao = new EtudiantDAO();
				dao.create(et1);
				
				//Ajout au jTable
				modele.addEtudiant(et1);
				
				me.setVisible(false);
				JOptionPane.showMessageDialog(null, "Vous venez de créer un étudiant avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}

			}
		});
	}
	
	//Quand on s'inscrit
	public JDialogCreerEtudiant () {
		
		build();
		this.setSize(600, 270);
		this.setTitle("Inscription d'un Etudiant");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String snom = tnom.getText();
				String sprenom = tprenom.getText();
				String sville = tville.getText();
				String scp = tcp.getText();
				String srue = tfrue.getText();
				String stel = ttel.getText();
				String smail = tmail.getText();
				char[] cmdp = tmdp.getPassword();
				String smdp = TabtoString(cmdp);
				
				Md5 md = new Md5(smdp);
				
				//Vérifier que les champs ne sont pas vide
				if (snom.isEmpty() || sprenom.isEmpty() || sville.isEmpty()
						|| scp.isEmpty() || srue.isEmpty() || stel.isEmpty() || smail.isEmpty() || cmdp.length == 0) {
					JOptionPane.showMessageDialog(null, "Erreur : Champ vide",
							"Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
				} 
				
				else {

					EtudiantDAO dao = new EtudiantDAO();
					
					//Je prends toute la liste des Entreprises existantes
					ArrayList <Etudiant> liste = new ArrayList <Etudiant>();
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
					if (!flag) {
					Etudiant et1 = new Etudiant(md.getCode(),sprenom,smdp,sville,srue,scp,stel,smail);
					dao.create(et1);
					Etudiant et = dao.find_id(et1.getMail());
					
					me.setVisible(false);
					JOptionPane.showMessageDialog(null, "Vous venez de vous inscrire avec succès ! Votre ID de connexion est "+ et.getID()+" ",
							"Inscription réussie", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Good.png"));
					Log log = new Log();
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
		this.add(J1);
		this.add(J2);
		this.add(J3);
		this.add(J4);
		this.add(J5);
		this.add(J6);
		this.add(J7);
		this.add(J8);
		this.add(J9);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J1.add(nom);
		J1.add(tnom);

		J2.add(prenom);
		J2.add(tprenom);

		J3.add(mdp);
		J3.add(tmdp);

		J4.add(ville);
		J4.add(tville);

		J5.add(rue);
		J5.add(tfrue);
		
		J6.add(cp);
		J6.add(tcp);
		
		J7.add(tel);
		J7.add(ttel);
		
		J8.add(mail);
		J8.add(tmail);

		J9.add(go);
		J9.add(retour);
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