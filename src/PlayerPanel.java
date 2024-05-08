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
	PlayerPanel(ArrayList<Integer> keyCodes){
		x = 0;
		y = 0;
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
		g2d.clearRect(0,0,getSize().width,getSize().height);
		
		g2d.fillRect(0, 0, 10, 310);
		g2d.fillRect(490, 0, 10, 10);
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, 490, 10);
		g2d.fillRect(0,310,10,10);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, 25, 25);
		
		twoDgraph.drawImage(back, 0, 0, null);
		
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
