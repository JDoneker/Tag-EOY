import javax.swing.JFrame;

public class GUIFrame extends JFrame{
	GUIPanel panel;
	int numOfPlayers;
	
	GUIFrame() {
		super("Extreme Tag");
		this.setResizable(false);
		//this.setUndecorated(true);
		
		panel = new GUIPanel(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(panel);
		this.pack();
		
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void setNumOfPlayers(int numOfPlayers1) {
		synchronized(this) {
			numOfPlayers = numOfPlayers1;
			System.out.print(this);
			System.out.println(numOfPlayers);
		    notify();
		    this.dispose();
		}
	}
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
}
