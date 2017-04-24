package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import IHM.JTable.tabledebut;
import Objet.Entreprise;

public class EntrepriseDAO {

	Connection connection;
	final String update = "UPDATE entreprise set Nom=?, Ville=?, CodePostal=?, Rue=?, Telephone=?, Mail=?, SecteurActivité=?, RaisonSociale=?, mdp=?, logo=? WHERE id=?;";
	final String create = "INSERT into entreprise (CodePostal, Mail, mdp, Nom, RaisonSociale, Rue, SecteurActivité, Telephone, Ville, logo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	final String delete = "DELETE FROM entreprise WHERE id=?;";
	final String afficher = "SELECT * FROM entreprise";
	final String find = "SELECT * FROM entreprise WHERE ID=?";
	final String affichertable = "SELECT * FROM entreprise";
	final String lister = "SELECT * FROM entreprise";
	final String find_id = "SELECT * FROM entreprise WHERE Mail=?";
	
	 public EntrepriseDAO () {
		 
		 connection = Connexion.connection();
	 }
	 
	 public void update(Entreprise e) {
			PreparedStatement stat = null;
			try {
				stat = (PreparedStatement) connection.prepareStatement(update);
				stat.setString(1, e.getNom());
				stat.setString(2, e.getVille());
				stat.setString(3, e.getCodePostal());
				stat.setString(4, e.getRue());
				stat.setString(5, e.getTelephone());
				stat.setString(6, e.getMail());
				stat.setString(7, e.getSecteurActivité());
				stat.setString(8, e.getRaisonSociale());
				stat.setString(9, e.getMdp());
				stat.setString(10, e.getLogo());
				stat.setInt(11, e.getID());
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
	 
	 public ArrayList<Integer> lister () {
		 
		 PreparedStatement stat = null;
		 ArrayList<Integer> Liste = new ArrayList<Integer>();
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
			 ResultSet resultset=stat.executeQuery();
			 JPanel p = new JPanel();
			 
			 while(resultset.next()) {
				 
				 int id = resultset.getInt("ID");
				 Liste.add(id);
			
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
		 
		 return Liste;
	 }
	 
	 public Entreprise find (int id) {
		 
		 PreparedStatement stat = null;
		 Entreprise e = new Entreprise();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					e = new Entreprise(resultset.getInt("ID"), resultset.getString("mdp"),resultset.getString("Nom"), resultset.getString("Ville"), resultset.getString("Rue"),resultset.getString("CodePostal"),resultset.getString("Telephone"),resultset.getString("Mail"),resultset.getString("SecteurActivité"),resultset.getString("RaisonSociale"),resultset.getString("logo"));
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
			
			return e;
	 }
	 
	 public Entreprise find_id (String mail) {
		 
		 PreparedStatement stat = null;
		 Entreprise e = new Entreprise();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find_id);
			 stat.setString(1, mail);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					e = new Entreprise(resultset.getInt("ID"), resultset.getString("mdp"),resultset.getString("Nom"), resultset.getString("Ville"), resultset.getString("Rue"),resultset.getString("CodePostal"),resultset.getString("Telephone"),resultset.getString("Mail"),resultset.getString("SecteurActivité"),resultset.getString("RaisonSociale"), resultset.getString("Logo"));
				 	
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
			
			return e;
	 }
	 
	 public ArrayList<Entreprise> afficher2 (ArrayList<Entreprise> liste) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
			 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				 
				 liste.add(new Entreprise(resultset.getInt("ID"),resultset.getString("mdp"),resultset.getString("Nom"),resultset.getString("Ville"),resultset.getString("Rue"),resultset.getString("CodePostal"),resultset.getString("Telephone"),resultset.getString("Mail"),resultset.getString("SecteurActivité"),resultset.getString("RaisonSociale"),resultset.getString("logo")));
				 
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
	 
	 public tabledebut affichertable (tabledebut modele) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(afficher);
			 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				 modele.addEntreprise(new Entreprise(resultset.getInt("ID"),resultset.getString("mdp"),resultset.getString("Nom"),resultset.getString("Ville"),resultset.getString("Rue"),resultset.getString("CodePostal"),resultset.getString("Telephone"),resultset.getString("Mail"),resultset.getString("SecteurActivité"),resultset.getString("RaisonSociale"),resultset.getString("logo")));
			       	 
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
	 
	 public void create(Entreprise obj) {
		 
			PreparedStatement stat = null;
			
			try {
				stat = (PreparedStatement) connection.prepareStatement(create);
				stat.setString(1, obj.getCodePostal());
				stat.setString(2, obj.getMail());
				stat.setString(3, obj.getMdp());
				stat.setString(4, obj.getNom());
				stat.setString(5, obj.getRaisonSociale());
				stat.setString(6, obj.getRue());
				stat.setString(7, obj.getSecteurActivité());
				stat.setString(8, obj.getTelephone());
				stat.setString(9, obj.getVille());
				stat.setString(10, obj.getLogo());
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
