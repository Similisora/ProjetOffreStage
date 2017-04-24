package IHM.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import DAO.OffreStageDAO;
import IHM.JDialog.JDialogCreerEntreprise;
import IHM.JDialog.JDialogModifierEntreprise;
import Objet.Entreprise;

public class tablefinal extends JFrame {
	
    private JTable tableau;
 
    public tablefinal(EntrepriseDAO dao, final tabledebut modele) {
    	
        super();
        
        setTitle("Liste des Entreprises");
 
        tableau = new JTable(modele);
        
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        this.setPreferredSize(new Dimension(1200, 300));
 
        JPanel boutons = new JPanel();
        
        JButton ajouter = new JButton("Ajouter");
        JButton supprimer = new JButton("Supprimer");
        JButton modifier = new JButton("Modifier");
        boutons.add(ajouter);
        boutons.add(supprimer);
        boutons.add(modifier);
        
        final tablefinal me = this;
 
        getContentPane().add(boutons, BorderLayout.SOUTH);
 
        pack();
        
        modifier.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				int ligne = tableau.getSelectedRow();
				EntrepriseDAO dao = new EntrepriseDAO ();
				int id = 0;
				if (ligne >= 0) {
					id = (int) modele.getValueAt(ligne, 0);
					Entreprise e = dao.find(id);
					JDialogModifierEntreprise j = new JDialogModifierEntreprise(e,me);
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune entreprise !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
        
        //Pour ajouter une entreprise, on ouvre un formulaire
        ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				JDialogCreerEntreprise j = new JDialogCreerEntreprise (modele);
				
			}
		});
        
        //Pour supprimer une entreprise
        supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
						
				int[] selection = tableau.getSelectedRows(); //Ensemble des lignes sélectionnées dans le JTable
				EntrepriseDAO dao = new EntrepriseDAO ();
				Entreprise e = new Entreprise();
				int id = 0;
				
				//Boucle pour supprimer les entreprises sélectionnées
				for (int i = selection.length - 1; i >= 0; i--) {
					
					id = (int) modele.getValueAt(selection[i], 0);
					e = dao.find(id);
					OffreStageDAO dao2 = new OffreStageDAO();
					dao2.relier2(e);
					
					//Boucle pour supprimer les offres de stage des entreprises sélectionnées
					for (int j = 0 ; j<e.getListeOffre().size() ; j++) {
	               
						dao2.delete(e.getListeOffre().get(j).getID());
					}
						
					dao.delete(id);
						
					JOptionPane.showMessageDialog(null, "Vous venez de supprimer l'entreprise " + id +" avec succès ! (ainsi que toutes les Offres qui lui étaient relié)",
								"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				 
				//Pour supprimer du JTable
	            for (int i = selection.length - 1; i >= 0; i--){
	                modele.removeEntreprise(selection[i]);
	               
	            }
	            //Si on n'a selectionné aucune entreprise
	            if (!(selection.length -1 >=0)) {
	            	JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune entreprise !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
	            }
	            

			}
		});
    }
}