import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class GUIFrame extends JFrame implements ActionListener{
	private JComboBox dropDown;
	private int numOfPlayers;
	protected JButton controls, start;
	private Popup controlsPopup;
	private ArrayList<ArrayList<Integer>> KeyCodes;
	
	GUIFrame() {
		super("Extreme Tag");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setUndecorated(true);
		
		KeyCodes = new ArrayList<ArrayList<Integer>>();
		KeyCodes.add(new ArrayList<Integer>(Arrays.asList(38,40,37,39,32,47)));
		KeyCodes.add(new ArrayList<Integer>(Arrays.asList(87,83,65,68,81,69)));
		KeyCodes.add(new ArrayList<Integer>(Arrays.asList(84,71,70,72,82,89)));
		KeyCodes.add(new ArrayList<Integer>(Arrays.asList(73,75,74,76,85,79)));
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(0,1));
		
        JLabel title = new JLabel("Extreme Tag", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 32));
        contentPane.add(title);
        
        JPanel grid = new JPanel(new GridLayout(2,2));
        grid.add(new JLabel("Number of players: "));
        String[] numbers = {"1","2","3","4"};
        dropDown = new JComboBox(numbers);
        dropDown.setSelectedIndex(3);
        grid.add(dropDown);
        grid.add(new JLabel("Controls"));
        controls = new JButton("Edit Controls"); 
        controls.addActionListener(this);
        grid.add(controls);
        
        Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        CompoundBorder line = new CompoundBorder(empty, blackLine);
        Border gridBorder = BorderFactory.createTitledBorder(line, "Settings");
        grid.setBorder(gridBorder); 
        contentPane.add(grid);
        
        start = new JButton("Start Game");
        start.addActionListener(this);
        start.setFont(new Font("Arial", Font.PLAIN, 32));
        contentPane.add(start);
        
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	public ArrayList<ArrayList<Integer>> getKeyCodes(){
		return KeyCodes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {
			synchronized(this) {
				numOfPlayers = dropDown.getSelectedIndex()+1;
				for(var i = 0; i<(4-numOfPlayers);i++) {
					KeyCodes.remove(KeyCodes.size()-1);
				}
				notify();
			    this.dispose();
			}
		}else if(e.getSource()==controls){
			PopupFactory popupFactory = new PopupFactory();
			ControlsPanel f = new ControlsPanel(dropDown.getSelectedIndex()+1);
			controlsPopup = popupFactory.getPopup(this,f,0,0);
			controlsPopup.show();
		}
		
	}
}