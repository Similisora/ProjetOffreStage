package IHM.JTable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Objet.Etudiant;

public class tabledebutetudiant extends AbstractTableModel {
	
    private final List<Etudiant> let = new ArrayList<Etudiant>();
    private final String[] entetes = {"ID", "MDP", "NOM", "PRENOM", "VILLE", "RUE", "CP", "TEL", "MAIL","CV","LETTRE"};
 
    public tabledebutetudiant () {
    	
        super();
        
    }
 
    public int getRowCount() {
        return let.size();
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
                return let.get(rowIndex).getID();
            case 1:
                return let.get(rowIndex).getMdp();
            case 2:
                return let.get(rowIndex).getNom();
            case 3:
            	return let.get(rowIndex).getPrenom();
            case 4:
                return let.get(rowIndex).getVille();
            case 5:
                return let.get(rowIndex).getRue();
            case 6:
                return let.get(rowIndex).getCodePostal();
            case 7:
                return let.get(rowIndex).getTelephone();
            case 8:
                return let.get(rowIndex).getMail();
            case 9:
                return let.get(rowIndex).getCv();
            case 10:
                return let.get(rowIndex).getLettre();
            default:
                return null;
        }
    }
 
    public void addEtudiant(Etudiant et) {
        let.add(et);
 
        fireTableRowsInserted(let.size() -1, let.size() -1);
    }
 
    public void removeEtudiant(int rowIndex) {
        let.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}