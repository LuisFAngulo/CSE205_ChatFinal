import java.util.Scanner;

public class Chat {
	
	public static void createRoom(String room) {
		ChatData.newRoomTable(room);
		
		System.out.println("\n");
	}
	
	public static void joinRoom(String room) {
		
		if(!ChatData.checkForRoom(room)) {
			
			System.out.println("\nERROR: No such room\n");
					
		}
		
		chat();
	}
	
	private static void chat() {
		
	}
	

}
