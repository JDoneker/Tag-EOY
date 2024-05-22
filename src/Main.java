import java.util.ArrayList;

public class Main{
	private static GUIFrame guiFrame; 
	private static Environment environment;
	
	public static void main(String[] args) { 	
		guiFrame = new GUIFrame();
		System.out.println("hello world.");
		
		synchronized(guiFrame) {
	        try {
	            guiFrame.wait();
	        }
	        catch(InterruptedException e){
	            e.printStackTrace();
	        }
	    }
		
		environment = new Environment();
		
		int numOfWindows = guiFrame.getNumOfPlayers();
		ArrayList<ArrayList<Integer>> KeyCodes = guiFrame.getKeyCodes();
		
		for(int i = 0; i<numOfWindows; i++) {
			environment.addPlayer(new PlayerFrame(i,KeyCodes.get(i), environment));
		}
		
		environment.start();
	}

}
