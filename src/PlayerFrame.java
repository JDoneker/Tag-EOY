import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import com.jogamp.opengl.awt.GLCanvas;
import java.nio.*;
import javax.swing.*;
import java.lang.Math;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;
import org.joml.*;
public class PlayerFrame extends JFrame implements GLEventListener {
	private int PlayerIndex;
	private GLCanvas myCanvas;
	private ArrayList<Integer> KeyCodes;
	private Color PlayerColor;
	
	public PlayerFrame(int i, ArrayList<Integer> KeyCodes1) {
		PlayerIndex = i;
		KeyCodes = KeyCodes1;
		switch(i) {
		case 0:
			PlayerColor = Color.RED;
			break;
		case 1:
			PlayerColor = Color.GREEN;
			break;
		case 2: 
			PlayerColor = Color.BLUE;
			break;
		case 3:
			PlayerColor = Color.ORANGE;
			break;
		}
		
		setTitle("Player "+Integer.toString(i+1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(false);
		setSize(500,320);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		myCanvas.setFocusable(false);
		this.add(myCanvas);
		
		
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
		
		setLocation(windowLocations[i]);

		this.setVisible(true);
	}
	public ArrayList<Integer> getKeyCodes(){
		return KeyCodes;
	}
	public void moveR() {
		System.out.println("Player "+Integer.toString(PlayerIndex)+ " Move Right");
	}
	public void moveL() {
		System.out.println("Player "+Integer.toString(PlayerIndex)+ " Move Left");
	}
	public void moveU() {
		System.out.println("Player "+Integer.toString(PlayerIndex)+ " Move Up");
	}
	public void moveD() {
		System.out.println("Player "+Integer.toString(PlayerIndex)+ " Move Down");
	}
	public void init(GLAutoDrawable drawable){}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) { }
	public void dispose(GLAutoDrawable drawable) { }
	public void display(GLAutoDrawable drawable){}
}