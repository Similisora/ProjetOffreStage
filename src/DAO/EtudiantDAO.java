package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import IHM.JTable.tabledebutetudiant;
import Objet.Entreprise;
import Objet.Etudiant;

public class EtudiantDAO {
	
	Connection connection;
	final String update = "UPDATE etudiant set ID=?, Nom=?, Prenom=?, mdp=?, ville=?, rue=?, CodePostal=?, Telephone=?, Mail=?, accept=?, message=?, cv=?, lettre=? WHERE id=?;";
	final String create = "INSERT into etudiant (Nom, Prenom, mdp, ville, rue, codepostal, telephone, mail, accept, message, cv, lettre) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	final String delete = "DELETE FROM etudiant WHERE id=?;";
	final String afficher = "SELECT * FROM etudiant";
	final String affichertable = "SELECT * FROM etudiant";
	final String find = "SELECT * FROM etudiant WHERE ID=?";
	final String find_id = "SELECT * FROM etudiant WHERE Mail=?";
	
	 public EtudiantDAO () {
		 
		 connection = Connexion.connection();
	 }
	 
	 public ArrayList<Etudiant> afficher2 (ArrayList<Etudiant> liste) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
			 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				 
				 liste.add(new Etudiant (resultset.getInt("ID"),resultset.getString("Nom"),resultset.getString("Prenom"),resultset.getString("mdp"),resultset.getString("ville"),resultset.getString("rue"),resultset.getString("codepostal"),resultset.getString("telephone"),resultset.getString("mail"),resultset.getBoolean("accept"),resultset.getBoolean("message"), resultset.getString("cv"), resultset.getString("lettre")));
		 
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
		
		return liste;
 }
	 
	 public Etudiant find_id (String mail) {
		 
		 PreparedStatement stat = null;
		 Etudiant et = new Etudiant();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find_id);
			 stat.setString(1, mail);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					et = new Etudiant (resultset.getInt("ID"),resultset.getString("Nom"),resultset.getString("Prenom"),resultset.getString("mdp"),resultset.getString("ville"),resultset.getString("rue"),resultset.getString("codepostal"),resultset.getString("telephone"),resultset.getString("mail"),resultset.getBoolean("accept"),resultset.getBoolean("message"), resultset.getString("cv"), resultset.getString("lettre"));

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
			
			return et;
	 }
	 
	 public void update(Etudiant et) {
			PreparedStatement stat = null;
			try {
				stat = (PreparedStatement) connection.prepareStatement(update);
				stat.setInt(1, et.getID());
				stat.setString(2, et.getNom());
				stat.setString(3, et.getPrenom());
				stat.setString(4, et.getMdp());
				stat.setString(5, et.getVille());
				stat.setString(6, et.getRue());
				stat.setString(7, et.getCodePostal());
				stat.setString(8, et.getTelephone());
				stat.setString(9, et.getMail());
				stat.setBoolean(10, et.isAccept());
				stat.setBoolean(11, et.isMessage());
				stat.setString(12, et.getCv());
				stat.setString(13, et.getLettre());
				stat.setInt(14, et.getID());
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
	 
	 public tabledebutetudiant affichertable (tabledebutetudiant modele) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
			 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				 		 
				 modele.addEtudiant(new Etudiant(resultset.getInt("ID"),resultset.getString("Nom"),resultset.getString("Prenom"),resultset.getString("mdp"),resultset.getString("Ville"),resultset.getString("Rue"),resultset.getString("CodePostal"),resultset.getString("Telephone"),resultset.getString("Mail"),resultset.getString("cv"),resultset.getString("lettre")));
			       	 
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
	 
	 public Etudiant find (int id) {
		 
		 PreparedStatement stat = null;
		 Etudiant et = new Etudiant();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					et = new Etudiant (resultset.getInt("ID"),resultset.getString("Nom"),resultset.getString("Prenom"),resultset.getString("mdp"),resultset.getString("ville"),resultset.getString("rue"),resultset.getString("codepostal"),resultset.getString("telephone"),resultset.getString("mail"),resultset.getBoolean("accept"),resultset.getBoolean("message"), resultset.getString("cv"), resultset.getString("lettre"));
				 	
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
			
			return et;
	 }
	 
	 public void create(Etudiant obj) {
		 
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
				stat.setBoolean(9, obj.isAccept());
				stat.setBoolean(10, obj.isMessage());
				stat.setString(11, obj.getCv());
				stat.setString(12, obj.getLettre());
				
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