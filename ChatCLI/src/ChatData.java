import java.sql.*;

public class ChatData {
	public static final String DBPATH = "jdbc:sqlite:ChatCLI/db/chatData.db";
	
	public static void setUsersTable() {
		String sql = "CREATE TABLE IF NOT EXISTS users (username STRING PRIMARY KEY, password STRING NOT NULL, activeRoom STRING)";
		
		DBUpdate(sql);
	}
	
	public static void newRoomTable(String roomName) {
		String sql = "CREATE TABLE IF NOT EXISTS " + roomName + " (msg TEXT NOT NULL, user STRING NOT NULL)";
		
		DBUpdate(sql);
	}
	
	public static void enterRoom(String user, String room) {
		String sql = "UPDATE users SET activeRoom = '" + room + "' WHERE username LIKE '" + user + "'";
		
		DBUpdate(sql);
	}
	
	public static void exitRoom(String user) {
		String sql = "UPDATE users SET activeRoom = null WHERE username = '" + user + "'";
		
		DBUpdate(sql);
	}
	
	public static void addUser(String user, String pass) {
		String sql = "INSERT INTO users(username, password) VALUES('" + user + "','" + pass + "')";

		DBUpdate(sql);
	}
	
	public static void changeUsername(String user, String newUser) {
		String sql = "UPDATE users SET username = '" + newUser + "' WHERE username = '" + user + "'";

		DBUpdate(sql);		
	}
	
	public static void changePassword(String user, String newPass) {
		String sql = "UPDATE users SET password = '" + newPass + "' WHERE username = '" + user + "'";

		DBUpdate(sql);		
	}
	
	public static void addMessage(String room, String user,String msg) {
		String sql = "INSERT INTO " + room + " (msg, user) VALUES ('" + msg + "', '" + user + "')";
		
		DBUpdate(sql);
	}
	
	public static boolean checkForRoom(String room) {
		String sql = "SELECT * FROM sqlite_master WHERE type='table' and name like'" + room + "'";
		
		return hasResults(sql);
	}
	
	public static boolean checkForUser(String usr) {
		String sql = "SELECT * FROM users WHERE username LIKE '" + usr + "'";
		
		return hasResults(sql);
	}
	
	public static boolean authCheck(String usr, String pass) {
		String sql = "SELECT * FROM users WHERE username LIKE '" + usr + "'" + " AND password LIKE '" + pass + "'";
		
		return hasResults(sql);
	}
	
	public static int getMessageCount(String room) {
		String sql = "SELECT COUNT(*) FROM " + room;
		
		return countResults(sql);
	}
	
	public static void printActiveUsers(String room) {
		String sql = "SELECT * FROM users WHERE activeRoom LIKE '" + room + "'";
		
		try {
			
			Connection con = DriverManager.getConnection(DBPATH);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("-" + rs.getString("username"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
	}
	
	public static void printHistory(String room, int msgInd) {
		String sql = "SELECT * FROM " + room + " WHERE rowid > " + msgInd;
		
		try {
			
			Connection con = DriverManager.getConnection(DBPATH);
			caseSensitiveLike(con);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("\n" + rs.getString("user") + ": ");
				System.out.println(rs.getString("msg"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}
	
	private static void DBUpdate(String sql) {
		
		try {
			
			Connection con = DriverManager.getConnection(DBPATH);
			caseSensitiveLike(con);
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private static boolean hasResults(String sql) {
		boolean hasRes = false;
		
		try {
			
			Connection con = DriverManager.getConnection(DBPATH);
			caseSensitiveLike(con);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			hasRes = rs.next();
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
		}
		
		return hasRes;
	}
	
	private static int countResults(String sql) {
		int results = 0;
		
		try {
			
			Connection con = DriverManager.getConnection(DBPATH);
			caseSensitiveLike(con);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			results = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return results;
	}
	
	public static void caseSensitiveLike(Connection con) throws SQLException {
		String sql = "PRAGMA case_sensitive_like = ON";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.executeUpdate();
		
		pstmt.close();
	}
	
}