import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;
import java.util.Collections;

public class Environment extends JFrame implements KeyListener, Runnable {
	JLabel label1,label2,label3,label4,label5;
	ArrayList<PlayerFrame> PlayerFrames;
	ArrayList<Integer> activeKeys;
	private long startTime, curTime,timeLeft;
	private boolean gameover;
	private Sound sound;
	public Environment() {
		super("Environment");
		this.setResizable(false);
		this.setSize(40,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sound = new Sound();
		
		PlayerFrames = new ArrayList<PlayerFrame>();
		activeKeys = new ArrayList<Integer>();
		startTime = System.currentTimeMillis();
		gameover = false;
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(0,1));
		label1 = new JLabel("hello");
		label2 = new JLabel("hello");
		label3 = new JLabel("hello");
		label4 = new JLabel("hello");
		label5 = new JLabel("hello");
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		contentPane.add(label4);
		contentPane.add(label5);
		
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
				
				if(gameover){
					ArrayList<Integer> scores = new ArrayList<>();
					for(int i = 0; i<PlayerFrames.size(); i++) {
						scores.add(PlayerFrames.get(i).getPanel().getScore());
					}
					int minValue = Collections.min(scores);
					label1.setText("Player " + Integer.toString(scores.indexOf(minValue)+1) + " Wins");
					
					
				}else{
					curTime = System.currentTimeMillis()-startTime;
					timeLeft = 30*1000-curTime;
					if(timeLeft<0){
						gameover = true;
						sound.playmusic("smb_gameover.wav");
					}
					label1.setText("Time Left: "+Float.toString(timeLeft/1000));
					if(PlayerFrames.size()>=1) {
						label2.setText("Player 1 Score: " + Integer.toString(PlayerFrames.get(0).getPanel().getScore()));
					}
					if(PlayerFrames.size()>=2) {
						label3.setText("Player 2 Score: " + Integer.toString(PlayerFrames.get(1).getPanel().getScore()));
					}
					if(PlayerFrames.size()>=3) {
						label4.setText("Player 3 Score: " + Integer.toString(PlayerFrames.get(2).getPanel().getScore()));
					}
					if(PlayerFrames.size()>=4) {
						label5.setText("Player 4 Score: " + Integer.toString(PlayerFrames.get(3).getPanel().getScore()));
					}
				}
				
				
			}
		}
		catch(Exception e) {}
	}
	public ArrayList<PlayerFrame> getPlayers(){
		return PlayerFrames;
	}
public boolean getGameover(){
	return gameover;
}
	public void start() {
		Random random = new Random();
		PlayerFrames.get(random.nextInt(PlayerFrames.size())).getPanel().setIt(true);
		new Thread(this).start();
		this.setFocusable(true);
		this.requestFocus();
	}
	public void checkKey(int key) {
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
	public void keyPressed(KeyEvent e) {
		if(!activeKeys.contains(e.getKeyCode())) {
			activeKeys.add(e.getKeyCode());
		}		
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
		activeKeys.remove(activeKeys.indexOf(e.getKeyCode()));
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}
