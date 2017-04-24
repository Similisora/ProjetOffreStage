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

public class JDialogChoixCV extends JDialog {
	
	JButton voir = new JButton ("Voir son CV");
	JButton ajouter = new JButton ("Ajouter un CV");
	
	JDialogChoixCV me = this;
	
	public JDialogChoixCV (final Etudiant et) {
		
		this.setLayout(new FlowLayout());
		this.setSize(240, 75);// On lui donne une taille
		this.setTitle("CV"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.add(voir);
		this.add(ajouter);
		
		voir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				try {
					if (et.getCv().equals("")) {
						JOptionPane.showMessageDialog(null, "Vous n'avez posté encore aucun CV.",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					 { Desktop.getDesktop().open(new File(et.getCv())); }
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
				   et.setCv(choix.getSelectedFile().getAbsolutePath());
				   dao.update(et);
				   JOptionPane.showMessageDialog(null, "CV modifié !",	
							"Information", JOptionPane.INFORMATION_MESSAGE);
				   
				}else;
				
				me.setVisible(false);
			}

		});
	}
	
}