import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class PlayerFrame extends JFrame {
	private PlayerPanel panel;
	
	public PlayerFrame(int i) {
		super("Player "+Integer.toString(i));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new PlayerPanel();
		this.add(panel);
		this.setFocusable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
