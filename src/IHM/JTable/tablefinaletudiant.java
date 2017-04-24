package IHM.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import DAO.EtudiantDAO;
import IHM.JDialog.JDialogCreerEtudiant;
import IHM.JDialog.JDialogModifierEtudiant;
import Objet.Etudiant;

public class tablefinaletudiant extends JFrame {
	
    private JTable tableau;
 
    public tablefinaletudiant (EtudiantDAO dao, final tabledebutetudiant modele) {
    	
        super();
        
        setTitle("Liste des Etudiants");
 
        tableau = new JTable(modele);
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        /*int h = 600;
        tableau.getColumnModel().getColumn(0).setPreferredWidth(h);*/ //pour modifier taille d'une cellule
        this.setPreferredSize(new Dimension(1200, 300));
 
        JPanel boutons = new JPanel();
        
        JButton ajouter = new JButton("Ajouter");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Modifier");
        boutons.add(ajouter);
        boutons.add(supprimer);
        boutons.add(modifier);
        
        final tablefinaletudiant me = this;
 
        getContentPane().add(boutons, BorderLayout.SOUTH);
 
        pack();
        
        modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int ligne = tableau.getSelectedRow();
				EtudiantDAO dao = new EtudiantDAO ();
				int id = 0;
				if (ligne >= 0) {
					id = (int) modele.getValueAt(ligne, 0);
					Etudiant et = dao.find(id);
					JDialogModifierEtudiant j = new JDialogModifierEtudiant(et, me);
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucun étudiant !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        
        ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogCreerEtudiant j = new JDialogCreerEtudiant (modele);
				
			}
		});
        
        supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				
				int[] selection = tableau.getSelectedRows();
				EtudiantDAO dao = new EtudiantDAO ();
				int id = 0;
				
				for (int i = selection.length - 1; i >= 0; i--){
		
		              id = (int) modele.getValueAt(selection[i], 0);
		              dao.delete(id);
		              System.out.println("etudiant " + id + " supprime avec succes");
		              JOptionPane.showMessageDialog(null, "Vous venez de supprimer l'édutiant " + id +" avec succès !",
								"Information", JOptionPane.INFORMATION_MESSAGE);
	               
	            }
				 
	            for (int i = selection.length - 1; i >= 0; i--){
	                modele.removeEtudiant(selection[i]);
	               
	            }
	            
	            if (!(selection.length -1 >=0)) {
	            	JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucun étudiant !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
	            }
	            

			}
		});
    }
}