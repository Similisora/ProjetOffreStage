package IHM.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import DAO.CandidatureDAO;
import DAO.EntrepriseDAO;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.JDialog.JDialogCreerOffreStage;
import IHM.JDialog.JDialogModifierOffreStage;
import Objet.Candidature;
import Objet.OffreStage;

public class tablefinalstage extends JFrame {
	
    private JTable tableau;
    tablefinalstage me = this;
 
    public tablefinalstage (OffreStageDAO dao, final tabledebutstage modele, final Fenetre mere) {
    	
        super();
        
        setTitle("Liste des Offres de Stage");
 
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
 
        getContentPane().add(boutons, BorderLayout.SOUTH);
 
        pack();
        
        ajouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				EntrepriseDAO dao = new EntrepriseDAO();
				
				JDialogCreerOffreStage j = new JDialogCreerOffreStage (modele,dao);
				
			}
		});
        
        supprimer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
					
				int[] selection = tableau.getSelectedRows();
				OffreStageDAO dao = new OffreStageDAO ();
				int id = 0;
				
				for (int i = selection.length - 1; i >= 0; i--){
		
					id = (int) modele.getValueAt(selection[i], 0);
					ArrayList<Candidature> liste = new ArrayList<Candidature>();
					CandidatureDAO dao2 = new CandidatureDAO();
					liste = dao2.find(id);
						
					for (int j = 0 ; j<liste.size() ; j++) {
						dao2.delete(liste.get(j).getID());
					}
					
		            dao.delete(id);
		              
		            JOptionPane.showMessageDialog(null, "Vous venez de supprimer l'offre " + id +" avec succès ! (ainsi que les candidatures qui y étaient associées)",
								"Information", JOptionPane.INFORMATION_MESSAGE);
	               
	            }
				 
	            for (int i = selection.length - 1; i >= 0; i--){
	                modele.removeOffreStage(selection[i]);
	               
	            }
	            
	            if (!(selection.length -1 >=0)) {
	            	JOptionPane.showMessageDialog(null, "Vous n'avez séléctionné aucune offre !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
	            }
	            

			}
		});
        
        modifier.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		
        		int ligne = tableau.getSelectedRow();
        		OffreStageDAO dao = new OffreStageDAO();
        		int id = 0;
        		if(ligne >= 0){
        			id =(int) modele.getValueAt(ligne, 0);
        			OffreStage os = dao.find(id);
        			JDialogModifierOffreStage o = new JDialogModifierOffreStage(os, me,mere);
        		}
        	}
        });
    }
}