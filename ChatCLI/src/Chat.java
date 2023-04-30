
public class Chat extends Thread{
	private String user,room;
	private boolean quit;
	
	public Chat(String user, String room) {
		this.user = user;
		this.room = room;
		quit = false;
	}
	
	public void joinRoom() {
		quit = false;
		//App.input.nextLine();
		
		
		ChatData.enterRoom(user, room);
		
		System.out.println("\nWelcome to " + room + ", " + user + " (/help for commands)");
		System.out.println("-----------------------------------------");
		
		this.start();
		
		while(!quit) if(App.input.hasNextLine()) userInput();
	}
	
	public void run() {
		int msgCount;
		int msgInd = ChatData.getMessageCount(room);
		
		while(!quit) {
			
			msgCount = ChatData.getMessageCount(room);
			
			if(msgCount > msgInd) {
				
				update(msgInd);
				
				msgInd = msgCount;
			}
		}
	}
	
	private void userInput() {
		String input = App.input.nextLine();
		
		if(input.length() < 1) return;
		
		if(input.charAt(0) == '/') command(input);
		else sendMessage(input);
	}
	
	private void command(String cmd){
		switch(cmd) {
		case "/list":
			listCmd();
			break;
			
		case "/leave":
			System.out.println("\n<Leaving '" + room + "'>");
			ChatData.exitRoom(user);
			quit = true;
			return;
			
		case "/help":
			helpCmd();
			break;
			
		case "/history":
			historyCmd();
			break;
			
		default:
			System.out.println("\n<ERROR: Command not recognized; Use '/help' for valid commands>");
		}
	}
	
	private void sendMessage(String msg) {
		
		ChatData.addMessage(room, user, msg);
		
		
	}
	
	private void update(int msgInd) {
		
		ChatData.printHistory(room, msgInd);
		
	}
	
	private void helpCmd() {
		
		System.out.println("-----------------------------------------");
		System.out.println("Commands:\n");
		System.out.println("/help          Print this message");
		System.out.println("/history       Print all past messages");
		System.out.println("/list          List all users currently in the chat room");
		System.out.println("/leave         Exit chat room");
		System.out.println("-----------------------------------------");

	}
	
	private void listCmd() {
		System.out.println("-----------------------------------------");
		System.out.println("Users currently in room '" + room + "':\n");
		ChatData.printActiveUsers(room);
		System.out.println("-----------------------------------------");
	}
	
	private void historyCmd() {
		
		ChatData.printHistory(room, 0);
		
	}
	

}