package IHM.JDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Objet.Etudiant;

public class JDialogProfilEtudiant extends JDialog{
	
	//Pour afficher des infos rapides sur l'étudiant quand l'entreprise voit une candidature de quelqu'un
	
	Font policeTimesRoman = new Font("TimesRoman", Font.BOLD, 20);
	JDialogProfilEtudiant me = this;
	
	JPanel acceuil = new JPanel();
	JPanel description = new JPanel();
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	JPanel J3 = new JPanel();
	JPanel J4 = new JPanel();
	JPanel J5 = new JPanel();
	JPanel J6 = new JPanel();
	JPanel J7 = new JPanel();
	JPanel info = new JPanel();
	
	JLabel bienvenue = new JLabel("Informations sur l'étudiant");
	JLabel detail = new JLabel();
	
	JLabel nom = new JLabel("Nom :");
	JLabel prenom = new JLabel("Prénom :");
	JLabel ville = new JLabel("Ville :");
	JLabel rue = new JLabel("Rue :");
	JLabel cp = new JLabel("Code Postal :");
	JLabel tel = new JLabel("Téléphone :");
	JLabel mail = new JLabel("Mail :");
	
	JLabel nom1 = new JLabel();
	JLabel prenom1 = new JLabel();
	JLabel ville1 = new JLabel();
	JLabel rue1 = new JLabel();
	JLabel cp1 = new JLabel();
	JLabel tel1 = new JLabel();
	JLabel mail1 = new JLabel();
	
	public JDialogProfilEtudiant(final Etudiant et) {
		
		build();
		this.setSize(320, 300);
		this.setTitle("Inforamtion sur "+et.getPrenom() +" "+et.getNom()); 
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		nom1.setText(et.getNom());
		prenom1.setText(et.getPrenom());
		ville1.setText(et.getVille());
		rue1.setText(et.getRue());
		cp1.setText(et.getCodePostal());
		tel1.setText(et.getTelephone());
		mail1.setText(et.getMail());
	}
	
	public void build() {

		this.setLayout(new FlowLayout());
		
		info.setLayout(new GridLayout(9,1));
		this.add(acceuil);
		this.add(description);
		this.add(info);
		info.add(J1);
		info.add(J2);
		info.add(J3);
		info.add(J4);
		info.add(J5);
		info.add(J6);
		info.add(J7);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);
		description.add(detail);

		J1.add(nom);
		J1.add(nom1);

		J2.add(prenom);
		J2.add(prenom1);
		
		J3.add(ville);
		J3.add(ville1);
		
		J4.add(rue);
		J4.add(rue1);
		
		J5.add(cp);
		J5.add(cp1);
		
		J6.add(tel);
		J6.add(tel1);
		
		J7.add(mail);
		J7.add(mail1);

	}
}