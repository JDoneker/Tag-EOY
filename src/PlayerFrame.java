import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

public class PlayerFrame extends JFrame {
	private PlayerPanel panel;
	
	public PlayerFrame(int i, ArrayList<Integer> KeyCodes, Environment E1) {
		super("Player "+Integer.toString(i+1));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(false);
		
		panel = new PlayerPanel(i, KeyCodes,E1);
		panel.setFocusable(false);
		this.add(panel);
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

		this.setVisible(true);
	}
	public PlayerPanel getPanel() {
		return panel;
	}


}