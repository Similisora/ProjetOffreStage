package IHM.JDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.CandidatureDAO;
import IHM.JTable.tablefinalcandidat;
import Objet.Candidature;

public class JDialogModifierCandidature extends JDialog{
	
	Font policeTimesRoman = new Font("TimesRoman", Font.BOLD, 20);
	JDialogModifierCandidature me = this;
	
	JPanel acceuil = new JPanel();
	JPanel description = new JPanel();
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	
	JLabel bienvenue = new JLabel("Modifier le Statut");
	
	JLabel statut = new JLabel("Statut :");
	
	JTextField tstatut = new JTextField(15);
	
	JButton go = new JButton("Modifier");


	public JDialogModifierCandidature(final Candidature c, final tablefinalcandidat t) {
		
		build();
		this.setSize(290, 180);// On lui donne une taille
		this.setTitle("Modifier une candidature"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		tstatut.setText(c.getStatut());
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String sstatut = tstatut.getText();
				
				if (tstatut.getText().equals("Acceptée") || tstatut.getText().equals("En attente") || tstatut.getText().equals("Refusée")) {
				
					CandidatureDAO dao = new CandidatureDAO();
				
					if (!sstatut.equals(c.getStatut())) {
						c.setStatut(sstatut);
						dao.update(c);
					}
				
					JOptionPane.showMessageDialog(null, "Vous avez modifié la candidature "+c.getID()+" avec succès !",
						"Information", JOptionPane.INFORMATION_MESSAGE);
					}
				
				else {
					JOptionPane.showMessageDialog(null, "Statut incorrect !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				me.setVisible(false);
				t.setVisible(false);
				
			}

		});
	}
	
	public void build() {

		this.setLayout(new FlowLayout());

		this.add(acceuil);
		this.add(description);
		this.add(J1);
		this.add(J2);
		
		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J1.add(statut);
		J1.add(tstatut);
		J2.add(go);
	}
}

