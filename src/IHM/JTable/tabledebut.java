package IHM.JTable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Objet.Entreprise;

public class tabledebut extends AbstractTableModel {
	
    private final List<Entreprise> le = new ArrayList<Entreprise>();
    private final String[] entetes = {"ID", "MDP", "NOM", "VILLE", "RUE","CP", "TEL", "MAIL", "SECTEUR", "RAISON","LOGO"};
 
    public tabledebut () {
    	
        super();
        
    }
 
    public int getRowCount() {
        return le.size();
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
                return le.get(rowIndex).getID();
            case 1:
                return le.get(rowIndex).getMdp();
            case 2:
                return le.get(rowIndex).getNom();
            case 3:
                return le.get(rowIndex).getVille();
            case 4:
                return le.get(rowIndex).getRue();
            case 5:
                return le.get(rowIndex).getCodePostal();
            case 6:
                return le.get(rowIndex).getTelephone();
            case 7:
                return le.get(rowIndex).getMail();
            case 8:
                return le.get(rowIndex).getSecteurActivité();
            case 9:
                return le.get(rowIndex).getRaisonSociale();
            case 10:
            	return le.get(rowIndex).getLogo();
            default:
                return null;
        }
    }
 
    //Ajout d'une entreprise au JTable
    public void addEntreprise(Entreprise e) {
        le.add(e);
 
        fireTableRowsInserted(le.size() -1, le.size() -1);
    }
 
    //Suppression d'une entreprise au JTable
    public void removeEntreprise(int rowIndex) {
        le.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}