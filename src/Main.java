import java.util.ArrayList;

public class Main{
	private static GUIFrame guiFrame; 
	private static Environment environment;
	
	public static void main(String[] args) { 	
		guiFrame = new GUIFrame();
		//HelloJOGL hello = new HelloJOGL();
		System.out.println("hello this is is is a test of the main branch. ");
		
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