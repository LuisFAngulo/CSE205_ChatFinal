import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	public final static String DBPath = "jdbc:sqlite:db/chatData.db";
	
	private static Connection con;
	private static Statement stmt;
	
	//private static Scanner scan;
	
	public static void main(String[] args) {
		con = ChatData.connect(DBPath);
		stmt = ChatData.makeStatement(con);
		ChatData.setTables();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nWelcome to Luis's CLI Chat App!\n");
		
		initScreen(scan);
		
		System.out.println("\nGoodbye!");

	}
	
	private static void initScreen(Scanner scan) {
			boolean quit = false;
			String input;
			
			while(!quit) {
			
				System.out.println("Select from the following options:");
				System.out.println("(R)egister, (L)ogin, (Q)uit");
				System.out.println("-----------------------------------------");
				
				input = scan.nextLine().toLowerCase();
				
				switch(input) {
					case "r":
					case "register":
						
						break;
					case "l":
					case "login":
						
						break;
					case "q":
					case "quit":
						quit = true;
						break;
						
					default:
						System.out.println("\nNot a valid option,");
			}
		}
	}
	
	private static void registerScreen(Scanner scan) {
		boolean quit = false;
		String usr, pass;
		
		while(!quit) {
			
			System.out.println("Enter Username and Password for new user:");
			System.out.println("-----------------------------------------");
			System.out.print("Username: ");
			
			usr = scan.nextLine();
			
			if(!ChatData.isUniqueUser(usr)) {
				continue;
			}
		}
			
			
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
