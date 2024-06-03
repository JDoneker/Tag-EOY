import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel implements Runnable{
	private BufferedImage back;
	private ArrayList<Integer> KeyCodes;
	private float x,y;
	private float vX, vY;
	private Color PlayerColor;
	private Environment E;
	private boolean grounded;
	private boolean it;
	private int playerIndex;
	private int score;
	private int immunity;
	private Sound sound;
	
	PlayerPanel(int i, ArrayList<Integer> keyCodes, Environment E1){
		sound = new Sound();
		playerIndex = i;
		immunity = 0;
		y = 0;
		E = E1;
		grounded = false;
		it = false;
		switch(i) {
		case 0:
			x = 0;
			PlayerColor = Color.RED;
			break;
		case 1:
			x = 500/4;
			PlayerColor = Color.GREEN;
			break;
		case 2: 
			x=500/2;
			PlayerColor = Color.BLUE;
			break;
		case 3:
			x = 1500/4;
			PlayerColor = Color.ORANGE;
			break;
		}
		KeyCodes = keyCodes;
		this.setPreferredSize(new Dimension(500,300));
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
		if(E.getGameover()){
			g2d.setColor(Color.BLACK);
			g2d.drawString("Congrats",50,50);
		}else{
		
		move();
		
		
		ArrayList<PlayerFrame> playerFrames = E.getPlayers();
		for(int i = 0; i<playerFrames.size(); i++) {
			playerFrames.get(i).getPanel().drawPlayer(g2d);
		}
		g2d.setColor(Color.BLACK);
		if(it){
			score++;
			g2d.drawString("You are it", 50, 50);
			for(int i = 0; i<playerFrames.size(); i++) {
				if(collidesWith(playerFrames.get(i).getPanel())&& i != playerIndex&&!(playerFrames.get(i).getPanel().getImmunity()>0)){
					immunity+=250;
					it=false;
					playerFrames.get(i).getPanel().setIt(true);
					
					
				}
			}
		}
		if(immunity>0){
			immunity --;
			g2d.drawString("You are immune", 50, 50);
		}
		}
		twoDgraph.drawImage(back, 0, 0, null);
		
	}
		public boolean collidesWith(PlayerPanel p) {
            return this.x < p.getXpos() + 25 &&
                   this.x + 25 > p.getXpos() &&
                   this.y < p.getYpos() + 25 &&
                   this.y + 25 > p.getYpos();
    }
	public void setIt(boolean b){
		it = b;
	}
	public int getImmunity(){
		return immunity;
	}
	public int getScore(){
		return score;
	}
	public void drawPlayer(Graphics g2d) {
		g2d.setColor(PlayerColor);
		g2d.fillRect((int)x, (int)y, 25, 25);
	}
	public ArrayList<Integer> getKeyCodes(){
		return KeyCodes;
	}
	public void moveR() {
		x++;
	}
	public void moveL() {
		x--;
	}
	public void moveU() {
		if(grounded){
			sound.playmusic("break.wav");
		vY=-5f;
		grounded = false;
		}
	}
	public void moveD() {
		y++;
	}
	public void move(){
		y+=vY;
		x+=vX;
		vY+=0.05;
		if(y+25>getSize().height){
			y=getSize().height-25;
			grounded = true;
		}
		if(x<0){
			x=0;
		}
		if(x+25>getSize().width){
			x=getSize().width-25;
		}
	}
	public float getXpos(){
		return this.x;
	}
	public float getYpos(){
		return this.y;
	}
}
