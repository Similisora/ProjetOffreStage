package IHM.JDialog;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import IHM.Autre.Log;
import IHM.JTable.tablefinal;
import Objet.Entreprise;

public class JDialogModifierEntreprise extends JDialog {
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.ITALIC, 20);
	JDialogModifierEntreprise me = this;

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
	JLabel logo = new JLabel("Chemin du fichier (logo) :");

	JTextField tnom = new JTextField(15);
	JTextField ttel = new JTextField(15);
	JTextField tcp = new JTextField(15);
	JTextField tville = new JTextField(15);
	JTextField tfrue = new JTextField(15);
	JTextField tmail = new JTextField(15);
	JTextField tsecteur = new JTextField(15);
	JTextField traison = new JTextField(15);
	JPasswordField tmdp = new JPasswordField(15);
	JTextField tlogo = new JTextField(15);

	JButton go = new JButton("Modifier");

	public JDialogModifierEntreprise (final Entreprise e, final tablefinal pere) {
		
		build();
		this.setSize(600, 320);// On lui donne une taille
		this.setTitle("Modifier une Entreprise"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tnom.setText(e.getNom());
		ttel.setText(e.getTelephone());
		tcp.setText(e.getCodePostal());
		tfrue.setText(e.getRue());
		tville.setText(e.getVille());
		tmail.setText(e.getMail());
		tsecteur.setText(e.getSecteurActivité());
		traison.setText(e.getRaisonSociale());
		tmdp.setText(e.getMdp());
		tlogo.setText(e.getLogo());
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				boolean flag = false; //Pour vérifier que le mot de passe n'est pas vide
				
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
				String slogo = tlogo.getText();
				
				EntrepriseDAO dao = new EntrepriseDAO();
				
				if (!snom.equals(e.getNom())) {
					e.setNom(snom);
					dao.update(e);
				}
				
				if (!sville.equals(e.getVille())) {
					e.setVille(sville);
					dao.update(e);
				}
				
				if (!scp.equals(e.getCodePostal())) {
					e.setCodePostal(scp);
					dao.update(e);
				}
				
				if (!srue.equals(e.getRue())) {
					e.setRue(srue);
					dao.update(e);
				}
				
				if (!stel.equals(e.getTelephone())) {
					e.setTelephone(stel);
					dao.update(e);
				}
				
				if (!smail.equals(e.getMail())) {
					e.setMail(smail);
					dao.update(e);
				}
				
				if (!ssecteur.equals(e.getSecteurActivité())) {
					e.setSecteurActivité(ssecteur);
					dao.update(e);
				}
				
				if (!sraison.equals(e.getRaisonSociale())) {
					e.setRaisonSociale(sraison);
					dao.update(e);
				}
				
				if (!smdp.equals(e.getMdp()) && !smdp.isEmpty()) {
					e.setMdp(smdp);
					dao.update(e);
					flag = false;
				}
				
				if (smdp.isEmpty()) { //Si le mot de passe est vide
					flag = true;
					JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (!slogo.equals(e.getLogo())) {
					e.setLogo(slogo);
					dao.update(e);
				}	
				
				if (!flag) { //Si le mot de passe n'est pas vide
					me.setVisible(false);
					pere.setVisible(false);	
					JOptionPane.showMessageDialog(null, "Vous avez modifié l'entreprise "+e.getID()+" avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				}
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
		this.add(J12);

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
		
		J12.add(logo);
		J12.add(tlogo);
		J12.add(go);
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
