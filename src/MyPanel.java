import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements ActionListener{
	private int clicks = 0;
	 JLabel label = new JLabel("Number of players");
	
	MyPanel(){
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 40, 50));
        this.setLayout(new GridLayout(0, 1));
        this.add(label);
        String[] numbers = {"1","2","3","4"};
        JComboBox dropDown = new JComboBox(numbers);
        dropDown.setSelectedIndex(0);
        dropDown.addActionListener(this);
        this.add(dropDown);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	

}
