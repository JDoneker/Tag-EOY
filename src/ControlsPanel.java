import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsPanel extends JPanel implements KeyListener,Runnable{
	ArrayList<ArrayList<JButton>> Buttons;
	
	public ControlsPanel(int numOfPlayers) {
		
		Buttons = new ArrayList<ArrayList<JButton>>(); 
 
		for(int i=0;i<numOfPlayers;i++) {
			ArrayList<JButton> temp = new ArrayList<JButton>();
			temp.add(new JButton("Forward"));
			temp.add(new JButton("Back"));
			temp.add(new JButton("Right"));
			temp.add(new JButton("Left"));
			temp.add(new JButton("Jump"));
			temp.add(new JButton("Strafe/Spin"));
			Buttons.add(temp);
		}
		this.setBackground(Color.GRAY);
		this.setLayout(new GridLayout(0,7));
		
		
		for(int i=0;i<numOfPlayers;i++) {
			this.add(new JLabel("Player "+Integer.toString(i+1)+": "));
			for (JButton j : Buttons.get(i)) {
				this.add(j);
	    	}
		}
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
			new Thread(this).start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		Buttons.get(0).get(0).setText("Hello");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
