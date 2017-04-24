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

import DAO.CandidatureDAO;
import IHM.JDialog.JDialogCreerCandidature;
import IHM.JDialog.JDialogModifierCandidature;
import Objet.Candidature;

public class tablefinalcandidat extends JFrame {
	
    private JTable tableau;
 
    public tablefinalcandidat (CandidatureDAO dao, final tabledebutcandidat modele) {
    	
        super();
        
        setTitle("Liste des Candidatures");
 
        tableau = new JTable(modele);
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        /*int h = 600;
        tableau.getColumnModel().getColumn(0).setPreferredWidth(h);*/ //pour modifier taille d'une cellule
        this.setPreferredSize(new Dimension(1000, 300));
 
        JPanel boutons = new JPanel();
        
        JButton ajouter = new JButton("Ajouter");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Modifier");
        boutons.add(ajouter);
        boutons.add(supprimer);
        boutons.add(modifier);
        
        final tablefinalcandidat me = this;
 
        getContentPane().add(boutons, BorderLayout.SOUTH);
 
        pack();
        
       modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int ligne = tableau.getSelectedRow();
				CandidatureDAO dao = new CandidatureDAO ();
				int id = 0;
				if (ligne >= 0) {
					id = (int) modele.getValueAt(ligne, 0);
					Candidature c = dao.finder(id);
					JDialogModifierCandidature j = new JDialogModifierCandidature(c,me);
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune entreprise !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        
        ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogCreerCandidature j = new JDialogCreerCandidature (modele);
				
			}
		});
        
        supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				
				int[] selection = tableau.getSelectedRows();
				CandidatureDAO dao = new CandidatureDAO ();
				int id = 0;
				
				for (int i = selection.length - 1; i >= 0; i--){
		
		              id = (int) modele.getValueAt(selection[i], 0);
		              dao.delete(id);
		              JOptionPane.showMessageDialog(null, "Vous venez de supprimer la candidature " + id +" avec succès !",
								"Information", JOptionPane.INFORMATION_MESSAGE);
	               
	            }
				 
	            for (int i = selection.length - 1; i >= 0; i--){
	                modele.removeCandidature(selection[i]);
	               
	            }
	            
	            if (!(selection.length -1 >=0)) {
	            	JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune candidature !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
	            }
	            

			}
		});
    }
}