import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class GUIFrame extends JFrame implements ActionListener{
	private JComboBox dropDown;
	private int numOfPlayers;
	
	GUIFrame() {
		super("Extreme Tag");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setUndecorated(true);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(0,1));
		
        JLabel title = new JLabel("Extreme Tag",SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 32));
        contentPane.add(title);
        
        JPanel grid = new JPanel(new GridLayout(2,2));
        grid.add(new JLabel("Number of players: "));
        String[] numbers = {"1","2","3","4"};
        dropDown = new JComboBox(numbers);
        dropDown.setSelectedIndex(0);
        grid.add(dropDown);
        grid.add(new JLabel("Setting 2: "));
        grid.add(new JButton("Button 4"));
        
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        CompoundBorder line = new CompoundBorder(empty, blackLine);
        Border gridBorder = BorderFactory.createTitledBorder(line, "Settings");
        grid.setBorder(gridBorder); 
        contentPane.add(grid);
        
        JButton button = new JButton("Start Game");
        button.addActionListener(this);
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        contentPane.add(button);
        
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized(this) {
			numOfPlayers = dropDown.getSelectedIndex()+1;
			notify();
		    this.dispose();
		}
	}
}
