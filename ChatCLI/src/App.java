import java.io.File;
import java.util.Scanner;

public class App {
	public static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		File dbDir = new File("ChatCLI/db");
		if (!dbDir.exists()){
			dbDir.mkdirs();
		}
		
		ChatData.setUsersTable();
		
		System.out.println("\nWelcome to Luis's CLI Chat App!\n");
		
		Init.launch();
		
		System.out.println("\nGoodbye!");

	}
	
	
}
