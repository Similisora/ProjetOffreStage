package IHM.JTable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Objet.Candidature;

public class tabledebutcandidat extends AbstractTableModel {
	
    private final List<Candidature> lc = new ArrayList<Candidature>();
    private final String[] entetes = {"ID", "STATUT", "ID OFFRESTAGE", "ID ETUDIANT"};
 
    public tabledebutcandidat () {
    	
        super();
        
    }
 
    public int getRowCount() {
        return lc.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return lc.get(rowIndex).getID();
            case 1:
                return lc.get(rowIndex).getStatut();
            case 2:
                return lc.get(rowIndex).getIDOffreStage();
            case 3:
                return lc.get(rowIndex).getIDEtudiant();
            default:
                return null;
        }
    }
 
    public void addCandidature(Candidature c) {
        lc.add(c);
 
        fireTableRowsInserted(lc.size() -1, lc.size() -1);
    }
 
    public void removeCandidature(int rowIndex) {
        lc.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}