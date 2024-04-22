import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Main{
	private static GUIFrame guiFrame; 
	private static ArrayList<PlayerFrame> PlayerFrames;
	
	public static void main(String[] args) { 	
		guiFrame = new GUIFrame();
		PlayerFrames = new ArrayList<PlayerFrame>();
		
		synchronized(guiFrame) {
	        try {
	            guiFrame.wait();
	        }
	        catch(InterruptedException e){
	            e.printStackTrace();
	        }
	    }
		
		int numOfWindows = guiFrame.getNumOfPlayers();
		for(int i = 0; i<numOfWindows; i++) {
			PlayerFrames.add(new PlayerFrame(i));
		}
		
	}

}
