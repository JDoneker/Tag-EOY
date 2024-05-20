import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel implements Runnable{
	private BufferedImage back;
	private ArrayList<Integer> KeyCodes;
	private int x,y;
	private Color PlayerColor;
	private Environment E;
	
	PlayerPanel(int i, ArrayList<Integer> keyCodes, Environment E1){
		x = 0;
		y = 0;
		E = E1;
		switch(i) {
		case 0:
			PlayerColor = Color.RED;
			break;
		case 1:
			PlayerColor = Color.GREEN;
			break;
		case 2: 
			PlayerColor = Color.BLUE;
			break;
		case 3:
			PlayerColor = Color.ORANGE;
			break;
		}
		KeyCodes = keyCodes;
		this.setPreferredSize(new Dimension(500,320));
		this.setBackground(Color.GRAY);
		back = null;

		new Thread(this).start();
	}
	
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}
	}
	public void paint(Graphics g) {
		Graphics2D twoDgraph = (Graphics2D)g;
		if(back == null) {
			back = (BufferedImage) (createImage(getWidth(),getHeight()));
		}
		Graphics g2d = back.createGraphics();
		g2d.setColor(PlayerColor);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		g2d.clearRect(5,5,getSize().width-10,getSize().height-10);
		
		ArrayList<PlayerFrame> playerFrames = E.getPlayers();
		for(int i = 0; i<playerFrames.size(); i++) {
			playerFrames.get(i).getPanel().drawPlayer(g2d);
		}
		
		twoDgraph.drawImage(back, 0, 0, null);
		
	}
	public void drawPlayer(Graphics g2d) {
		g2d.setColor(PlayerColor);
		g2d.fillRect(x, y, 25, 25);
	}
	public ArrayList<Integer> getKeyCodes(){
		return KeyCodes;
	}
	public void moveR() {
		x++;
	}
	public void moveL() {
		x--;
	}
	public void moveU() {
		y--;
	}
	public void moveD() {
		y++;
	}
}
