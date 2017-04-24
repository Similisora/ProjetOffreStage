package IHM.JDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.EtudiantDAO;
import IHM.JTable.tablefinaletudiant;
import Objet.Etudiant;

public class JDialogModifierEtudiant extends JDialog{
	
	Font policeTimesRoman = new Font("TimesRoman", Font.ITALIC, 20);
	JDialogModifierEtudiant me = this;
	
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

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel ville = new JLabel("Ville :");
	JLabel nom = new JLabel("Nom :");
	JLabel prenom = new JLabel("Prénom :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Adresse mail :");
	JLabel mdp = new JLabel("Mot de Passe:");
	JLabel cv = new JLabel("CV (chemin):");
	JLabel lettre = new JLabel("Lettre de Motivation (chemin):");
	
	JTextField tnom = new JTextField(15);
	JTextField tprenom = new JTextField(15);
	JTextField ttel = new JTextField(15);
	JTextField tcp = new JTextField(15);
	JTextField tville = new JTextField(15);
	JTextField tfrue = new JTextField(15);
	JTextField tmail = new JTextField(15);
	JPasswordField tmdp = new JPasswordField(15);
	JTextField tcv = new JTextField(15);
	JTextField tlettre = new JTextField(15);
	
	JButton go = new JButton("Modifier");
	
	public JDialogModifierEtudiant(final Etudiant et, final tablefinaletudiant table) {
		
		build();
		this.setSize(600, 300);
		this.setTitle("Modifier un étudiant");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tnom.setText(et.getNom());
		tprenom.setText(et.getPrenom());
		ttel.setText(et.getTelephone());
		tcp.setText(et.getCodePostal());
		tfrue.setText(et.getRue());
		tville.setText(et.getVille());
		tmail.setText(et.getMail());
		tmdp.setText(et.getMdp());
		tcv.setText(et.getCv());
		tlettre.setText(et.getLettre());
			
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				boolean flag = false;
				
				String snom = tnom.getText();
				String sprenom = tprenom.getText();
				String sville = tville.getText();
				String scp = tcp.getText();
				String srue = tfrue.getText();
				String stel = ttel.getText();
				String smail = tmail.getText();
				char[] cmdp = tmdp.getPassword();
				String smdp = TabtoString(cmdp);
				String scv = tcv.getText();
				String slettre = tlettre.getText();
				
				EtudiantDAO dao = new EtudiantDAO();
				
				if (!snom.equals(et.getNom())) {
					et.setNom(snom);
					dao.update(et);
				}
				
				if (!sprenom.equals(et.getPrenom())){
					et.setPrenom(sprenom);
					dao.update(et);
				}
				
				if (!sville.equals(et.getVille())) {
					et.setVille(sville);
					dao.update(et);
				}
				
				if (!scp.equals(et.getCodePostal())) {
					et.setCodePostal(scp);
					dao.update(et);
				}
				
				if (!srue.equals(et.getRue())) {
					et.setRue(srue);
					dao.update(et);
				}
				
				if (!stel.equals(et.getTelephone())) {
					et.setTelephone(stel);
					dao.update(et);
				}
				
				if (!smail.equals(et.getMail())) {
					et.setMail(smail);
					dao.update(et);
				}
				
				if (!smdp.equals(et.getMdp()) && !smdp.isEmpty()) {
					et.setMdp(smdp);
					dao.update(et);
					flag = false;
				}
				
				if (!scv.equals(et.getCv())) {
					et.setCv(scv);
					dao.update(et);
				}
				
				if (!slettre.equals(et.getLettre())) {
					et.setLettre(slettre);
					dao.update(et);
				}
				
				if (smdp.isEmpty()) { //Si le mot de passe est vide
					flag = true;
					JOptionPane.showMessageDialog(null, "Mot de passe incorrect",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (!flag) {
				me.setVisible(false);
				table.setVisible(false);
				
				JOptionPane.showMessageDialog(null, "Vous avez modifié l'étudiant "+et.getID()+" avec succès !",
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
		this.add(J10);
		
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
		
		J8.add(mdp);
		J8.add(tmdp);
		
		J9.add(cv);
		J9.add(tcv);
		
		J10.add(lettre);
		J10.add(tlettre);
		J10.add(go);
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
