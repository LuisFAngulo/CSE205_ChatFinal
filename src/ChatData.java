import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatData {
	
	
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
					+ "password STRING NOT NULL)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		System.out.println("<Users Table Set>");
		
	}
	
	public static void newRoomTable(String roomName) {
		
		try {
			
			App.getStmt().execute("CREATE TABLE " + roomName + " ("
					+ "msg TEXT NOT NULL,"
					+ "user STRING NOT NULL)");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
	
	public static boolean checkForRoom(String room) {
		PreparedStatement stmt;
		boolean exists;
		try {
			
			stmt = App.getCon().prepareStatement("SELECT * FROM sqlite_master WHERE type='table and name='" + room + "'");
			exists = stmt.executeQuery().next();
			stmt.close();
			return exists;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public static boolean checkForUser(String usr) {
		PreparedStatement stmt;
		boolean exists;
		try {
			
			stmt = App.getCon().prepareStatement("SELECT * FROM users WHERE username LIKE '" + usr + "'");
			exists = stmt.executeQuery().next();
			stmt.close();
			return exists;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public static void addUser(String usr, String pass) {
		Statement stmt;

		try {
			
			stmt = App.getStmt();
			stmt.execute("INSERT INTO users(username, password) VALUES('"
					+ usr + "','"
					+ pass + "')");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static boolean authCheck(String usr, String pass) {
		PreparedStatement stmt;
		boolean auth;
		try {
			
			stmt = App.getCon().prepareStatement("SELECT * FROM users WHERE username LIKE '" + usr + "'"
					+ " AND password LIKE '" + pass + "'");
			auth = stmt.executeQuery().next();
			stmt.close();
			return auth;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}
	
}
