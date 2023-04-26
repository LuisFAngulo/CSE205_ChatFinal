import java.sql.SQLException;
import java.util.Scanner;

public class Init {
	
	public static void menu(Scanner scan) {
		boolean quit = false;
		String input;
		
		while(!quit) {
		
			System.out.println("\nSelect from the following options:");
			System.out.println("(R)egister, (L)ogin, (Q)uit");
			System.out.println("-----------------------------------------");
			
			input = scan.nextLine().toLowerCase();
			
			switch(input) {
				case "r":
				case "register":
					registerScreen(scan);
					break;
				case "l":
				case "login":
					loginScreen(scan);
					break;
				case "q":
				case "quit":
					quit = true;
					break;
					
				case"d": 
					try {
						
						App.getStmt().execute("DROP TABLE users");
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					break;
					
				default:
					System.out.println("\nERROR: Not a valid option");
		}
	}
}

private static void registerScreen(Scanner scan) {
	String usr, pass, input;
	
	while(true) {
		
		System.out.println("\nEnter Username and Password for new user:");
		System.out.println("-----------------------------------------");
		
		System.out.print("Username: ");
		usr = scan.nextLine();
		System.out.print("Password: ");
		pass = scan.nextLine();
		
		if(ChatData.checkForUser(usr)) {
			
			System.out.println("\nERROR: User " + usr + " already exists\n");
			
			while(true) {
				
				System.out.print("Try again?(y/n):");
				input = scan.nextLine().toLowerCase();
				
				if(input.equals("y")||
					input.equals("yes")||
					input.equals("n")||
					input.equals("no")) break;
				
				System.out.println("\nERROR: Not a valid option\n");
			}
			
			if(input.equals("n")|| input.equals("no")) break;
					
			
			continue;
		}
		
//		String confPass;
		
//		while(true) {
//			
//			System.out.print("Confirm Password:");
//			confPass = scan.nextLine();
//			
//			if(confPass.equals(pass)) {
//				break;
//			}
//			else if(confPass.toLowerCase().equals("cancel")) {
//				return;
//			}
//			
//			System.out.println("Not a Match");
//		}
		
		ChatData.addUser(usr, pass);
		System.out.println("\nNew User " + usr + " created\n");
		return;
		
	}
		
		
}

private static void loginScreen(Scanner scan) {
	String usr, pass, input;
	
	while(true) {
		
		System.out.println("\nEnter Username and Password:");
		System.out.println("-----------------------------------------");
		
		System.out.print("Username: ");
		usr = scan.nextLine();
		System.out.print("Password: ");
		pass = scan.nextLine();
		
		if(!ChatData.authCheck(usr, pass)) {
			
			System.out.println("\nERROR: Invalid Username/Password\n");
			
			while(true) {
				
				System.out.print("Try again?(y/n):");
				input = scan.nextLine().toLowerCase();
				
				if(input.equals("y")||
					input.equals("yes")||
					input.equals("n")||
					input.equals("no")) break;
				
				System.out.println("\nERROR: Not a valid option\n");
			}
			
			if(input.equals("n") || input.equals("no")) break;
					
			continue;
		}
		
		System.out.println("\nLogin successful\n");
		System.out.println("Welcome, " + usr + "\n");
		
		Core.menu(scan);
	}
}
}
