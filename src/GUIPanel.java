import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPanel extends JPanel implements ActionListener{
	private JComboBox dropDown;
	GUIFrame frame;
	
	GUIPanel(GUIFrame guiFrame) {
		frame = guiFrame;
		
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
        this.setLayout(new GridLayout(0, 1));
        
        JLabel title = new JLabel("Extreme Tag");
        title.setFont(new Font("Arial", Font.PLAIN, 32));
        this.add(title);
        
        JLabel label = new JLabel("Number of players");
        this.add(label);
        
        String[] numbers = {"1","2","3","4"};
        dropDown = new JComboBox(numbers);
        dropDown.setSelectedIndex(0);
        this.add(dropDown);
        
        JButton button = new JButton("Start Game");
        button.addActionListener(this);
        this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numOfPlayers = dropDown.getSelectedIndex()+1;
		frame.setNumOfPlayers(numOfPlayers);
		
		
	}
	

}
