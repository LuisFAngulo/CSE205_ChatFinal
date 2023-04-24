import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

public class Chat {
	private final String url = "jdbc:postgresql://localhost/chat";
	private final String usr = "postgres";
	private final String pswd = "chat";
	
	public static void main(String[] args) {
		
		Chat chat = new Chat();
		
		Connection con = chat.connect();
		
		Statement stmt = chat.getStatement(con);
		
		boolean quit = false;
	}
	
	private Connection connect() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, usr, pswd);
			
			System.out.println("Connected to database successfully");
		}catch(SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return con;
	}
	
	private Statement getStatement(Connection con) {
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Statement created successfully");
		return stmt;
	}

}
