import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PlayerFrame extends JFrame {
	private PlayerPanel panel;
	
	public PlayerFrame(int i) {
		super("Player "+Integer.toString(i+1));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new PlayerPanel();
		this.add(panel);
		this.setFocusable(true);
		this.pack();
		
		int screenLeft = (int)(0.25*Toolkit.getDefaultToolkit().getScreenSize().getWidth()-0.5*this.getWidth());
		int screenRight = (int)(0.75*Toolkit.getDefaultToolkit().getScreenSize().getWidth()-0.5*this.getWidth());
		int screenTop = (int)(0.25*Toolkit.getDefaultToolkit().getScreenSize().getHeight()-0.5*this.getHeight());
		int screenBottom = (int)(0.75*Toolkit.getDefaultToolkit().getScreenSize().getHeight()-0.59*this.getHeight());
		Point[] windowLocations = {
				new Point(screenLeft,screenTop),
				new Point(screenRight,screenTop),
				new Point(screenLeft,screenBottom),
				new Point(screenRight,screenBottom)
		};
		this.setLocation(windowLocations[i]);
		
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
