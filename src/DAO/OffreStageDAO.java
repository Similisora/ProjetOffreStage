package DAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import IHM.JTable.tabledebutstage;
import Objet.Entreprise;
import Objet.OffreStage;

public class OffreStageDAO {
	
	Connection connection;
	final String update = "UPDATE offrestage set libelle=?, description=?, domaine=?, datedebut=?, duree=?, valide=?, IDentreprise=?, changer=? WHERE id=?;";
	final String create = "INSERT into offrestage (libelle, description, domaine, datedebut, duree, valide, IDentreprise, changer) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	final String delete = "DELETE FROM offrestage WHERE id=?;";
	final String relier = "SELECT * FROM offrestage INNER JOIN entreprise ON offrestage.IDentreprise = entreprise.ID AND offrestage.IDentreprise = ?";
	final String find = "SELECT * FROM offrestage WHERE ID=?";
	final String affichertable = "SELECT * FROM offrestage";
	final String afficher = "SELECT * FROM offrestage";
	final String relier2 = "SELECT * FROM offrestage INNER JOIN entreprise ON offrestage.IDentreprise = entreprise.ID AND offrestage.IDentreprise = ?";
	
	public OffreStageDAO () {
		
		connection = Connexion.connection();
	}
	
	public void update(OffreStage o) {
		PreparedStatement stat = null;
		try {
			stat = (PreparedStatement) connection.prepareStatement(update);
			stat.setString(1, o.getLibelléOffre());
			stat.setString(2, o.getDescriptionOffre());
			stat.setString(3, o.getDomaineOffre());
			stat.setDate(4, new Date(o.getDateDébutOffre().getTime()));
			stat.setInt(5, o.getDuréeOffre());
			stat.setBoolean(6, o.isValide());
			stat.setInt(7, o.getIDEntreprise());
			stat.setBoolean(8, o.isChanger());
			stat.setInt(9, o.getID());
			stat.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return;
	}
	
	public OffreStage find (int id) {
		 
		 PreparedStatement stat = null;
		 OffreStage o = new OffreStage();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
				 o = new OffreStage(resultset.getInt("ID"),resultset.getString("libelle"),resultset.getString("description"),resultset.getString("domaine"),resultset.getDate("datedebut"),resultset.getInt("duree"),resultset.getBoolean("valide"),resultset.getInt("IDentreprise"),resultset.getBoolean("changer"));
				}
		 }
		 
		 catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			finally {
				
				try {
					stat.close();
				}
				
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			return o;
	 }
	
	public void delete(int id) {
		PreparedStatement stat = null;
		try {
			stat = (PreparedStatement) connection.prepareStatement(delete);
			stat.setInt(1, id);
			stat.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public tabledebutstage affichertable (tabledebutstage modele) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
				 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				 				 		 
				modele.addOffreStage(new OffreStage(resultset.getInt("ID"),resultset.getString("libelle"),resultset.getString("description"),resultset.getString("domaine"),resultset.getDate("datedebut"),resultset.getInt("duree"),resultset.getBoolean("valide"),resultset.getInt("IDentreprise"),resultset.getBoolean("changer")));
			       	 
			 }
		 }
		 
		 catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			finally {
				
				try {
					stat.close();
				}
				
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			
			return modele;
	 }
	
	public void create(OffreStage obj) {
		 
		PreparedStatement stat = null;
		
		try {
			stat = (PreparedStatement) connection.prepareStatement(create);
			stat.setString(1, obj.getLibelléOffre());
			stat.setString(2, obj.getDescriptionOffre());
			stat.setString(3, obj.getDomaineOffre());
			stat.setDate(4, new Date(obj.getDateDébutOffre().getTime()));
			stat.setInt(5, obj.getDuréeOffre());
			stat.setBoolean(6, obj.isValide());
			stat.setInt(7, obj.getIDEntreprise());
			stat.setBoolean(8, obj.isChanger());
			stat.execute();
			}
		
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			
			try {
				stat.close();
			}
			
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return;
	}
	
	public void relier2 (Entreprise e) {
		 
		PreparedStatement stat = null;
		OffreStage o = new OffreStage();
		
		try {
			
			int id = e.getID();
			stat = (PreparedStatement) connection.prepareStatement(relier);
			stat.setInt(1, id);		
			ResultSet resultset = stat.executeQuery();
			e.getListeOffre().removeAll(e.getListeOffre());
			
			 while (resultset.next()) {
					o = new OffreStage(resultset.getInt("ID"),resultset.getString("libelle"),resultset.getString("description"),resultset.getString("domaine"),resultset.getDate("datedebut"),resultset.getInt("duree"),resultset.getBoolean("valide"),resultset.getInt("IDentreprise"),resultset.getBoolean("changer"));	
					e.getListeOffre().add(o);
				}
			}
		
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		finally {
			
			try {
				stat.close();
			}
			
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return;
	}

}
