import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	MyPanel panel;
	MyFrame(){
		super("Extreme Tags");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		panel = new MyPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		
		//this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
