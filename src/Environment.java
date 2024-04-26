import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Environment extends JFrame implements KeyListener, Runnable {
	JLabel label;
	
	public Environment(ArrayList<PlayerFrame> playerFrames) {
		super("Environment");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.requestFocus();
		
		label = new JLabel("hello");
		this.add(label);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addKeyListener(this);
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				label.setText(Long.toString(System.currentTimeMillis()));
			}
		}
		catch(Exception e) {}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}
 
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
	}
}
