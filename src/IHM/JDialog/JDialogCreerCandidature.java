package IHM.JDialog;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import DAO.CandidatureDAO;
import IHM.JTable.tabledebutcandidat;
import Objet.Candidature;

public class JDialogCreerCandidature extends JDialog {
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.ITALIC, 20);
	JDialogCreerCandidature me = this;

	JPanel acceuil = new JPanel();
	JPanel description = new JPanel();
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	JPanel J3 = new JPanel();
	JPanel J4 = new JPanel();

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel offre = new JLabel("ID de l'offre de stage :");
	JLabel etud = new JLabel("ID de l'étudiant :");
	JLabel statut = new JLabel("Statut :");

	JTextField toffre = new JTextField(15);
	JTextField tetud = new JTextField(15);
	
	String choixFinal;

	JButton go = new JButton("Créer !");
	
	JComboBox choix = new JComboBox();

	public JDialogCreerCandidature (final tabledebutcandidat modele) {
		
		build();
		this.setSize(450, 220);// On lui donne une taille
		this.setTitle("Ajouter une Candidature"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		choix.addItem("En attente");
		choix.addItem("Acceptée");
		choix.addItem("Refusée");
		
		choixFinal = "En attente"
;		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int soffre = Integer.parseInt(toffre.getText());
				int setud = Integer.parseInt(tetud.getText());

				Candidature c1 = new Candidature(choixFinal,soffre,setud);
				CandidatureDAO dao = new CandidatureDAO();
				dao.create(c1);
				
				//Ajout au jTable
				modele.addCandidature(c1);
				
				me.setVisible(false);
				JOptionPane.showMessageDialog(null, "Vous venez de créer une candidature avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}

		});
		
		choix.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e) {
				choixFinal = (String) e.getItem();
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

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J1.add(statut);
		J1.add(choix);

		J2.add(offre);
		J2.add(toffre);

		J3.add(etud);
		J3.add(tetud);

		J4.add(go);
	}

}