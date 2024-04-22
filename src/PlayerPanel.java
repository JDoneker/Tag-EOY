import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel implements Runnable,KeyListener {
	private BufferedImage back;
	private int offset;
	PlayerPanel(){
		offset = 0;
		this.setPreferredSize(new Dimension(500,320));
		this.setBackground(Color.GRAY);
		back = null;

		this.addKeyListener(this);
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
		g2d.fillRect(390, 0, 10, 10);
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, 390, 10);
		g2d.fillRect(0,310,10,10);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(offset, 0, 25, 25);
		twoDgraph.drawImage(back, 0, 0, null);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		System.out.println("hello1");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		offset++;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		System.out.println("hello3");
	}
}
