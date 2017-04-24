package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import IHM.JTable.tabledebutcandidat;
import Objet.Candidature;

public class CandidatureDAO {

	Connection connection;
	final String update = "UPDATE candidature set statut=?, idOffreStage=?, idEtudiant=? WHERE id=?;";
	final String create = "INSERT into candidature (statut, idOffreStage, idEtudiant) VALUES (?, ?, ?);";
	final String delete = "DELETE FROM candidature WHERE id=?;";
	final String find = "SELECT * FROM candidature WHERE idOffreStage=?";//Récupérer les candidatures d'une offre
	final String liste = "SELECT * FROM candidature WHERE idEtudiant=?";//Récupérer les candidatures d'un étudiant
	final String affichertable = "SELECT * FROM candidature"; //Récupérer toutes les candidatures pour le JTable
	final String listerall = "SELECT * FROM candidature"; //Récupérer toutes les candidatures
	final String lister = "SELECT * FROM candidature WHERE idEtudiant=?";
	final String finder = "SELECT * FROM candidature WHERE ID=?";
	
	public CandidatureDAO () {
		 
		 connection = Connexion.connection();
	 }
	
	//Récuperer une candidature par l'ID
	public Candidature finder (int id) {
		 
		 PreparedStatement stat = null;
		 Candidature c = new Candidature();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(finder);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 if (resultset.next()) {
					c = new Candidature (resultset.getInt("ID"),resultset.getString("statut"),resultset.getInt("idOffreStage"),resultset.getInt("idEtudiant"));
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
			
			return c;
	 }
	
	//Retourner la liste des candidatures
	public ArrayList<Candidature> listerall () {
		 
		 PreparedStatement stat = null;
		 ArrayList<Candidature> liste = new ArrayList<Candidature>();
		 Candidature c = new Candidature();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(listerall);		 
			 ResultSet resultset = stat.executeQuery();
			 while (resultset.next()) {
				c = new Candidature (resultset.getInt("ID"),resultset.getString("statut"),resultset.getInt("idOffreStage"),resultset.getInt("idEtudiant"));
				liste.add(c);
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
	
	//Retourner la liste des candidatures d'un Etudiant
	public ArrayList<Candidature> liste (int id) {
		 
		 PreparedStatement stat = null;
		 ArrayList<Candidature> liste = new ArrayList<Candidature>();
		 Candidature c = new Candidature();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(lister);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 while (resultset.next()) {
				c = new Candidature (resultset.getInt("ID"),resultset.getString("statut"),resultset.getInt("idOffreStage"),resultset.getInt("idEtudiant"));
				liste.add(c);
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
	
	//Retourner la liste des candidatures d'une offre de stage
	public ArrayList<Candidature> find (int id) {
		 
		 PreparedStatement stat = null;
		 ArrayList<Candidature> liste = new ArrayList<Candidature>();
		 Candidature c = new Candidature();
		 
		 try {
			 
			 stat = (PreparedStatement) connection.prepareStatement(find);
			 stat.setInt(1, id);
			 
			 ResultSet resultset = stat.executeQuery();
			 while (resultset.next()) {
				c = new Candidature (resultset.getInt("ID"),resultset.getString("statut"),resultset.getInt("idOffreStage"),resultset.getInt("idEtudiant"));
				liste.add(c);
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
	 
	//Création d'une candidature
	 public void create(Candidature obj) {
		 
			PreparedStatement stat = null;
			
			try {
				stat = (PreparedStatement) connection.prepareStatement(create);
				stat.setString(1, obj.getStatut());
				stat.setInt(2, obj.getIDOffreStage());
				stat.setInt(3, obj.getIDEtudiant());
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
	 
	 //Suppression d'une candidature
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
	 
	 //Mise à jour d'une candidature
	 public void update(Candidature c) {
			PreparedStatement stat = null;
			try {
				stat = (PreparedStatement) connection.prepareStatement(update);
				stat.setString(1, c.getStatut());
				stat.setInt(2, c.getIDOffreStage());
				stat.setInt(3, c.getIDEtudiant());
				stat.setInt(4, c.getID());
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
	 
	 //Afficher les candidatures dans un JTable
	 public tabledebutcandidat affichertable (tabledebutcandidat modele) {
		 
		 PreparedStatement stat = null;
		 
		 try {
			 stat = (PreparedStatement) connection.prepareStatement(affichertable);
			 ResultSet resultset=stat.executeQuery();
			 
			 while(resultset.next()) {
				
				modele.addCandidature(new Candidature(resultset.getInt("ID"),resultset.getString("statut"),resultset.getInt("idOffreStage"),resultset.getInt("idEtudiant")));
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
}
