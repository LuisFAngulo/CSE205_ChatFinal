
public class Session {
	private String user;
	
	public Session(String user) {
		this.user = user;
	}
	
	public void launch() {
		String input;
		String room = "";
		
		loop: while(true) {
		
			System.out.println("\nSelect from the following options:");
			System.out.println("(J)oin, (C)reate, (A)ccount, (L)ogout");
			System.out.println("-----------------------------------------");
			
			input = App.input.next().toLowerCase();
			if(App.input.hasNextLine()) room = App.input.nextLine();
			
			switch(input) {
				case "j":
					joinRoom(user, room);
					break;
				
				case "c":
					createRoom(room); 
					break;
				
				case "a":
					account();
					break;
					
				case "l":
					System.out.println("\n<Ending session>");
					break loop;
					
				default:
					System.out.println("\n<ERROR: Not a valid option>");
			}
		}
	}
	
	private void createRoom(String room) {
		
		if(room.equals("")) {
//			System.out.println("\nPlease provide a room name (Example: \"c room_name\")");
			return;
		}
		
		String truncRoom = room.substring(1);
		
		boolean validRoom = truncRoom.toLowerCase().equals(truncRoom);
		
		for(char i : truncRoom.toCharArray()) {
			if(!(Character.isLetterOrDigit(i))) {
				validRoom = false;
				break;
			}
		}
		
		if(!validRoom){
			
			System.out.println("\n<ERROR: Room names may only consist of lowercase letters and numbers>");
		
		}else if(ChatData.checkForRoom(truncRoom)) {
			
			System.out.println("\n<ERROR: Room '" + truncRoom + "' already exists>\n");
					
		}else {
			
			System.out.println("\n<Creating '" + truncRoom + "'>");
			
			ChatData.newRoomTable(truncRoom);
			
			joinRoom(user, room);
			
		}
	}
	
	private void joinRoom(String user, String room) {

		if(room.equals("")) {
			System.out.println("Please provide a room name (Example: \"j room_name\")");
			return;
		}
		
		room = room.substring(1);
		
		if(ChatData.checkForRoom(room)) {
			
			System.out.println("\n<Joining room '" + room + "'>");
			
			Chat chat = new Chat(user, room);
			chat.joinRoom();
			
					
		}else System.out.println("\n<ERROR: Room '" + room + "' does not exist>");
		
		
		
		
		
	}
	
	private void account() {
		String input;
		
		while(true) {
			
			System.out.print("\nChange (U)sername or (P)assword?:");
			input = App.input.nextLine().toLowerCase();
			
			if(input.equals("u")) {
				userChange();
				break;
			}
			
			else if(input.equals("p")) {
				passChange();
				break;
			}
			
			System.out.println("\n<ERROR: Not a valid option>");
		}
		
	}
	
	private void userChange() {
		String newUser;
		
		System.out.print("\nEnter new Username:");
		newUser = App.input.nextLine();
		
		if(newUser.equals("")) {
			
			System.out.println("\n<ERROR: Username cannot be blank>");
			
		}else if(!ChatData.checkForUser(newUser)) {
			
			ChatData.changeUsername(user, newUser);
			
			user = newUser;
			
			System.out.println("\n<Username changed to '" + newUser + "'>");
			
		}else System.out.println("\n<ERROR: User " + newUser + " already exists>");
		
	}
	
	private void passChange() {
		String newPass;
			
		System.out.print("\nEnter new Password:");
		newPass = App.input.nextLine();
		
		if(newPass.equals("")) {
			
			System.out.println("\n<ERROR: Password cannot be blank>");
			
		}else {
			
			ChatData.changePassword(user, newPass);
			
			System.out.println("\n<Password changed to '" + newPass + "'>");
			
		}
		
		
	}
}
