import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	public final static String DBPath = "jdbc:sqlite:db/chatData.db";
	
	private static Connection con;
	private static Statement stmt;
	
	//private static Scanner scan;
	
	public static void run() {
		con = ChatData.connect(DBPath);
		stmt = ChatData.makeStatement(con);
		ChatData.setTables();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nWelcome to Luis's CLI Chat App!\n");
		
		Init.menu(scan);
		
		System.out.println("\nGoodbye!");

	}

	public static Connection getCon() {
		return con;
	}

	public static Statement getStmt() {
		return stmt;
	}
	
//	public static Scanner getScan() {
//		return scan;
//	}
	
	
}
