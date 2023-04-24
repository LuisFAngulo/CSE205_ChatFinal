import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public  class ChatData {
	
	
	public static Connection connect(String path) {
		Connection con = null;
		
		try {
			
			con = DriverManager.getConnection(path);
			System.out.println("<Connection Successful>");
			
		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
		}
		
		return con;
	}
	
	public static Statement makeStatement(Connection con) {
		Statement stmt = null;
		
		try {
			
			stmt = con.createStatement();
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
		}
		
		System.out.println("<Statement Created>");
		
		return stmt;
	}
	
	public static void setTables() {
		Statement stmt = App.getStmt();
		
		try {
			
			stmt.execute("CREATE TABLE IF NOT EXISTS users ("
					+ "username STRING PRIMARY KEY,"
					+ "password STRING NOT NULL);");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		System.out.println("<Users Table Set>");
		
	}
	
	public static void createRoom(String roomName) {
		
		try {
			
			App.getStmt().execute("CREATE TABLE " + roomName + " ("
					+ "msg TEXT NOT NULL,"
					+ "user STRING NOT NULL);");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
	
	public static boolean isUniqueUser(String usr) {
		PreparedStatement stmt;
		
		try {
			
			stmt = App.getCon().prepareStatement("SELECT * FROM users WHERE username LIKE '" + usr + "';");
			return !stmt.executeQuery().next();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
}
