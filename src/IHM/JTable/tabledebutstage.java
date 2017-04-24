package IHM.JTable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Objet.OffreStage;

public class tabledebutstage extends AbstractTableModel {
	
    private final List<OffreStage> lo = new ArrayList<OffreStage>();
    private final String[] entetes = {"ID", "LIBELLE", "DESCRIPTION", "DOMAINE", "DATE DEBUT","DUREE (MOIS)", "VALIDE", "ID ENTREPRISE"};
 
    public tabledebutstage () {
    	
        super();
        
    }
 
    public int getRowCount() {
        return lo.size();
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
                return lo.get(rowIndex).getID();
            case 1:
                return lo.get(rowIndex).getLibelléOffre();
            case 2:
                return lo.get(rowIndex).getDescriptionOffre();
            case 3:
                return lo.get(rowIndex).getDomaineOffre();
            case 4:
                return lo.get(rowIndex).getDateDébutOffre();
            case 5:
                return lo.get(rowIndex).getDuréeOffre();
            case 6:
                return lo.get(rowIndex).isValide();
            case 7:
                return lo.get(rowIndex).getIDEntreprise();
            default:
                return null;
        }
    }
 
    public void addOffreStage(OffreStage o) {
        lo.add(o);
 
        fireTableRowsInserted(lo.size() -1, lo.size() -1);
    }
 
    public void removeOffreStage(int rowIndex) {
        lo.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}