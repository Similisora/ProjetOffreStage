package IHM.JDialog;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import DAO.EtudiantDAO;
import Objet.Etudiant;

public class JDialogChoixLettre extends JDialog {
	
	JButton voir = new JButton ("Voir sa Lettre");
	JButton ajouter = new JButton ("Ajouter une Lettre");
	
	JDialogChoixLettre me = this;
	
	public JDialogChoixLettre (final Etudiant et) {
		
		this.setLayout(new FlowLayout());
		this.setSize(280, 75);// On lui donne une taille
		this.setTitle("Lettre de Motivation"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.add(voir);
		this.add(ajouter);
		
		voir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				try {
					if (et.getLettre().equals("")) {
						JOptionPane.showMessageDialog(null, "Vous n'avez posté encore aucune Lettre de Motivation.",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					 { Desktop.getDesktop().open(new File(et.getLettre())); }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				me.setVisible(false);
			}

		});
		
		ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JFileChooser choix = new JFileChooser();
				int retour=choix.showOpenDialog(getParent());
				if(retour==JFileChooser.APPROVE_OPTION){
				   // un fichier a été choisi (sortie par OK)
				   // nom du fichier  choisi 
				   choix.getSelectedFile().getName();
				   // chemin absolu du fichier choisi
				   choix.getSelectedFile().getAbsolutePath();
				   
				   EtudiantDAO dao = new EtudiantDAO();
				   et.setLettre(choix.getSelectedFile().getAbsolutePath());
				   dao.update(et);
				   JOptionPane.showMessageDialog(null, "Lettre de Motivation modifiés !",	
							"Information", JOptionPane.INFORMATION_MESSAGE);
				   
				}else;
				
				me.setVisible(false);
			}

		});
	}
	
}