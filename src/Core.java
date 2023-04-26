import java.sql.SQLException;
import java.util.Scanner;

public class Core {
	
	public static void menu(Scanner scan) {
		boolean quit = false;
		String input;
		String room;
		
		while(!quit) {
		
			System.out.println("\nSelect from the following options:");
			System.out.println("(J)oin, (C)reate, (A)ccount, (L)ogout");
			System.out.println("-----------------------------------------");
			
			input = scan.next().toLowerCase();
			
			switch(input) {
				case "j":
				case "join":
					room = scan.nextLine();
//					Chat.join(scan, room);
					break;
				case "c":
				case "create":
					room = scan.nextLine();
					Chat.createRoom(scan, room);
					break;
				
				case "a":
				case "account:":
//					account(scan, room);
					break;
					
				case "l":
				case "logout":
					quit = true;
					break;
					
				default:
					System.out.println("\nNot a valid option");
			}
		}
	}
}
