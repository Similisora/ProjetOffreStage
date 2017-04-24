package DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Connexion {
	//Effectuer la connection entre la BDD et le programme
	
	 static Connection conn= null;
	 
	 public static Connection connection() {
		 
		 try {
			 
			 if (conn == null) {
			 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/projetstage", "root",
					 "");
			 System.out.println("connexion réussi");
			 }
			 
		 } catch (Exception e) {
			 System.out.println("raté");
		 }
		 return conn;
	 }
}