
public class Init {
	
	public Init() {
	}
	
	public static void launch() {
		String input;
		
		loop: while(true) {
		
			System.out.println("\nSelect from the following options:");
			System.out.println("(R)egister, (L)ogin, (Q)uit");
			System.out.println("-----------------------------------------");
			
			input = App.input.nextLine().toLowerCase();
			
			switch(input) {
			case "": break;
			case "r":
			case "register":
				register();
				break;
				
			case "l":
			case "login":
				login();
				break;
				
			case "q":
			case "quit":
				break loop;
				
			default:
				System.out.println("\n<ERROR: Not a valid option>");
			}
		}
	}
	
	private static void register() {
		String usr, pass;
		
		System.out.println("\nEnter Username and Password for new user:");
		System.out.println("-----------------------------------------");
		
		System.out.print("Username: ");
		usr = App.input.nextLine();
		System.out.print("Password: ");
		pass = App.input.nextLine();
		
		if(ChatData.checkForUser(usr)) {
			
			System.out.println("\n<ERROR: User " + usr + " already exists>");
			
		}else if(usr.equals("") || pass.equals("")){
			
			System.out.println("\n<ERROR: Username/Password cannot be blank>");
		
		}else {
		
			ChatData.addUser(usr, pass);
			System.out.println("\n<New User " + usr + " created>");
		}
			
	}
	
	private static void login() {
		String usr, pass;
		
		System.out.println("\nEnter Username and Password:");
		System.out.println("-----------------------------------------");
			
		System.out.print("Username: ");
		usr = App.input.nextLine();
		System.out.print("Password: ");
		pass = App.input.nextLine();
			
		if(ChatData.authCheck(usr, pass)) {
			
			System.out.println("\n<Starting session>");
			System.out.println("\nWelcome, " + usr + ".");
			
			mainApp(usr);
			
		}else System.out.println("\n<ERROR: Invalid Username/Password>");		
		
	}
	
	private static void mainApp(String user) {
		
		Session main = new Session(user);
		main.launch();
		
	}
}
