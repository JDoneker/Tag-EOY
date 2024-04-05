import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable,KeyListener {
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private BufferedImage back;
	
	MyPanel(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
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
		g2d.fillRect(0, 0, 10, 690);
		g2d.fillRect(990, 0, 10, 10);
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, 990, 10);
		g2d.fillRect(0,690,10,10);
		twoDgraph.drawImage(back, 0, 0, null);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
