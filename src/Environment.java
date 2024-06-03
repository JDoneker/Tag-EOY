import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Environment extends JFrame implements KeyListener, Runnable {
	JLabel label;
	ArrayList<PlayerFrame> PlayerFrames;
	ArrayList<Integer> activeKeys;
	public Environment() {
		super("Environment");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PlayerFrames = new ArrayList<PlayerFrame>();
		activeKeys = new ArrayList<Integer>();
		
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
				for(int i = 0; i < activeKeys.size(); i++) {
					checkKey(activeKeys.get(i));
				}
				label.setText(Long.toString(System.currentTimeMillis()));
			}
		}
		catch(Exception e) {}
	}
	public ArrayList<PlayerFrame> getPlayers(){
		return PlayerFrames;
	}
	public void start() {
		new Thread(this).start();
		this.setFocusable(true);
		this.requestFocus();
	}
	public void checkKey(int key) {
		ArrayList<Integer> tempKeyCodes;
		if(PlayerFrames.size()>=1) {
			tempKeyCodes = PlayerFrames.get(0).getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(0).moveF();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(0).moveB();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(0).moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(0).moveR();
			}
			if(key == tempKeyCodes.get(4)){
				PlayerFrames.get(0).moveJ();
			}
			if(key == tempKeyCodes.get(5)){
				PlayerFrames.get(0).strafeTrue();
			}
		}
		if(PlayerFrames.size()>=2) {
			tempKeyCodes = PlayerFrames.get(1).getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(1).moveF();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(1).moveB();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(1).moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(1).moveR();
			}
			if(key == tempKeyCodes.get(4)){
				PlayerFrames.get(1).moveJ();
			}
			if(key == tempKeyCodes.get(5)){
				PlayerFrames.get(1).strafeTrue();
			}
		}
		if(PlayerFrames.size()>=3) {
			tempKeyCodes = PlayerFrames.get(2).getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(2).moveF();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(2).moveB();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(2).moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(2).moveR();
			}
			if(key == tempKeyCodes.get(4)){
				PlayerFrames.get(2).moveJ();
			}
			if(key == tempKeyCodes.get(5)){
				PlayerFrames.get(2).strafeTrue();
			}
		}
		if(PlayerFrames.size()>=4) {
			tempKeyCodes = PlayerFrames.get(3).getKeyCodes();
			if(key == tempKeyCodes.get(0)) {
				PlayerFrames.get(3).moveF();
			}
			if(key == tempKeyCodes.get(1)) {
				PlayerFrames.get(3).moveB();
			}
			if(key == tempKeyCodes.get(2)) {
				PlayerFrames.get(3).moveL();
			}
			if(key == tempKeyCodes.get(3)) {
				PlayerFrames.get(3).moveR();
			}
			if(key == tempKeyCodes.get(4)){
				PlayerFrames.get(3).moveJ();
			}
			if(key == tempKeyCodes.get(5)){
				PlayerFrames.get(3).strafeTrue();
			}
		}
	}
	private void checkStrafeFalse(int key){
		ArrayList<Integer> tempKeyCodes;
		if(PlayerFrames.size()>=1) {
			tempKeyCodes = PlayerFrames.get(0).getKeyCodes();
			if(key == tempKeyCodes.get(5)) {
				PlayerFrames.get(0).strafeFalse();
			} 
		}
		if(PlayerFrames.size()>=2) {
			tempKeyCodes = PlayerFrames.get(1).getKeyCodes();
			if(key == tempKeyCodes.get(5)) {
				PlayerFrames.get(1).strafeFalse();
			}
		}
		if(PlayerFrames.size()>=3) {
			tempKeyCodes = PlayerFrames.get(2).getKeyCodes();
			if(key == tempKeyCodes.get(5)) {
				PlayerFrames.get(2).strafeFalse();
			}
		}
		if(PlayerFrames.size()>=4) {
			tempKeyCodes = PlayerFrames.get(3).getKeyCodes();
			if(key == tempKeyCodes.get(5)) {
				PlayerFrames.get(3).strafeFalse();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!activeKeys.contains(e.getKeyCode())) {
			activeKeys.add(e.getKeyCode());
		}
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
		checkStrafeFalse(e.getKeyCode());
		activeKeys.remove(activeKeys.indexOf(e.getKeyCode()));
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}