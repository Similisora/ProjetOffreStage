package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Objet.Admin;

public class AdminDAO {
	
	Connection connection;
	final String update = "UPDATE admin set ID=?, Nom=?, Prenom=?, mdp=?, ville=?, rue=?, CodePostal=?, Telephone=?, Mail=? WHERE id=?;";
	final String create = "INSERT into admin (Nom, Prenom, mdp, ville, rue, codepostal, telephone, mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	final String delete = "DELETE FROM admin WHERE id=?;";
	final String find = "SELECT * FROM admin WHERE ID=?";
	
	 public AdminDAO () {
		 
		 connection = Connexion.connection();
	 }
	 
	 //Mise à jour de l'Admin
	 public void update(Admin a) {
			PreparedStatement stat = null;
			try {
				stat = (PreparedStatement) connection.prepareStatement(update);
				stat.setInt(1, a.getID());
				stat.setString(2, a.getNom());
				stat.setString(3, a.getPrenom());
				stat.setString(4, a.getMdp());
				stat.setString(5, a.getVille());
				stat.setString(6, a.getRue());
				stat.setString(7, a.getCodePostal());
				stat.setString(8, a.getTelephone());
				stat.setString(9, a.getMail());
				stat.setInt(10, a.getID());
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
	 
	 //Trouver l'Admin dans une base de donnée
	 public Admin find (int id) {
		 
		 PreparedStatement stat = null;
		 Admin a = new Admin();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					a = new Admin (resultset.getInt("ID"),resultset.getString("Nom"),resultset.getString("Prenom"),resultset.getString("mdp"),resultset.getString("ville"),resultset.getString("rue"),resultset.getString("codepostal"),resultset.getString("telephone"),resultset.getString("mail"));
				 	
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
			
			return a;
	 }
	 
	 //Création de l'Admin
	 public void create(Admin obj) {
		 
			PreparedStatement stat = null;
			
			try {
				stat = (PreparedStatement) connection.prepareStatement(create);
				stat.setString(1, obj.getNom());
				stat.setString(2, obj.getPrenom());
				stat.setString(3, obj.getMdp());
				stat.setString(4, obj.getVille());
				stat.setString(5, obj.getRue());
				stat.setString(6, obj.getCodePostal());
				stat.setString(7, obj.getTelephone());
				stat.setString(8, obj.getMail());
				
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
	 
	 //Suppression de l'Admin
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

}
