import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseMaker {

	private static String createDatabaseString;
	private static String createTableString;
	private static String populateTableString;
				
	public static void main(String[] args) {
		String username = System.getProperty("dbUsername"), password = System.getProperty("dbPassword");

		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/",username,password);
			Statement statement = conn.createStatement();
			System.out.println("connettendo al server con nome utente: " + username + "...");
			statement.executeUpdate(createDatabaseString);
			System.out.println("database creato...");
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost/searchEngineDB",username,password);
			statement = conn.createStatement();
			statement.executeUpdate(createTableString);
			System.out.println("relazioni create...");
			
			statement.executeUpdate(populateTableString);
			System.out.println("relazioni popolate...");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
