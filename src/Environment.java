import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Environment extends JFrame implements KeyListener, Runnable {
	JLabel label;
	ArrayList<PlayerFrame> PlayerFrames;
	
	public Environment() {
		super("Environment");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PlayerFrames = new ArrayList<PlayerFrame>();
		
		label = new JLabel("hello");
		this.add(label);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addKeyListener(this);
	}

	public void addPlayer(PlayerFrame playerFrame) {
		PlayerFrames.add(playerFrame);
		
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

	public void start() {
		new Thread(this).start();
		this.setFocusable(true);
		this.requestFocus();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		ArrayList<Integer> tempKeyCodes;
		if(PlayerFrames.size()>=1) {
			tempKeyCodes = PlayerFrames.get(0).getPanel().getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(0).getPanel().moveU();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(0).getPanel().moveD();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(0).getPanel().moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(0).getPanel().moveR();
			}
		}
		if(PlayerFrames.size()>=2) {
			tempKeyCodes = PlayerFrames.get(1).getPanel().getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(1).getPanel().moveU();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(1).getPanel().moveD();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(1).getPanel().moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(1).getPanel().moveR();
			}
		}
		if(PlayerFrames.size()>=3) {
			tempKeyCodes = PlayerFrames.get(2).getPanel().getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(2).getPanel().moveU();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(2).getPanel().moveD();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(2).getPanel().moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(2).getPanel().moveR();
			}
		}
		if(PlayerFrames.size()>=4) {
			tempKeyCodes = PlayerFrames.get(3).getPanel().getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(3).getPanel().moveU();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(3).getPanel().moveD();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(3).getPanel().moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(3).getPanel().moveR();
			}
		}
	}
 
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}
