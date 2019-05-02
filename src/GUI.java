import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Timestamp;
import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TreeSet;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author LanoueAdmin
 * All the static items to be added to the frame and panels 
 */
public class GUI extends JFrame {
	static JFrame frame; 
	static JLabel enterHD;
	static JTextField enterHDtextField;
	static JSlider slider; 
	static JButton showStations; 
	static JTextArea stationList;
	static JTextArea HD; 
	static JButton calcHD; 
	static JLabel compareWith;
	static JComboBox<String> cBox1; 	
	static JLabel l0; 
	static JTextField d0;
	static JLabel l1;
	static JTextField d1; 
	static JLabel l2;
	static JTextField d2; 
	static JLabel l3;
	static JTextField d3; 
	static JLabel l4; 
	static JTextField d4; 
	static JLabel blank; 
	static JButton add; 
	static JTextField addee; 
	static JLabel addingIssue; 

	static JLabel timeD;
	static JTextField timeL;

	static JButton timeColor; 

	/**
	 * Is the main block of code that displays the panel and frame 
	 * @param args
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException
	{
		//Adds a clock to the creative side of the project
		ZoneId timeZone = ZoneId.of("America/Chicago"); 
		Clock clock = Clock.tickMinutes(timeZone); 
		ZonedDateTime time = clock.instant().atZone(clock.getZone()); 
		String timer = ""; 
		timer += time; 
		//checks to see if the current time is between a certain time frame
		//if between 8am and 5pm, the background can be set to yellow 
		//if between 5pm and 8am, the background will be set to grey
		LocalTime target = LocalTime.parse("11:59:00");
		Boolean inZone = (target.isBefore(LocalTime.parse("08:00:00")) && 
				target.isAfter(LocalTime.parse("17:00:00"))); 
		//object from the hamming distance class that allows things to be called from other class
		HammingDistance ob = new HammingDistance(); 
		//reads the file
		ob.read("Mesonet.txt");

		//reads the other class' treeset to this classes treeset 
		TreeSet<String> guiTreeSet = new TreeSet<String>(); 
		guiTreeSet = ob.getTreeSet(); 

		//adds the frame and panels to the gui
		frame = new JFrame("Hamming Distance");
		JPanel p0 = new JPanel(); 
		JPanel p1 = new JPanel(); 
		JPanel p2 = new JPanel(); 
		JPanel p3 = new JPanel(); 
		JPanel p4 = new JPanel(); 
		JPanel p5 = new JPanel(); 
		JPanel p6 = new JPanel(); 
		JPanel p7 = new JPanel(); 
		JPanel p8 = new JPanel(); 

		//creates the slider and displays the numbers and 
		slider = new JSlider(1, 4, 2); 
		slider.setMajorTickSpacing(1);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		//Shows the words that show what the 
		enterHD = new JLabel("Enter Hamming Dist:"); 
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e){
				String state = ""; 
				state += slider.getValue(); 
				enterHDtextField.setText(state); 
			}
		}); 

		//textField for the slider's number to go
		enterHDtextField = new JTextField();
		enterHDtextField.setEditable(false);
		HD = new JTextArea(1, 10);

		//area of where the stations are listed - non editable 
		showStations = new JButton("Show Station"); 
		stationList = new JTextArea(20, 15); 
		stationList.setEditable(false);

		//Scroller for the showStations area to see all the stations under the same
		JScrollPane scroller = new JScrollPane();

		
		showStations.addActionListener(new ActionListener() {
			/**
			 * action listener that shows the number of stations that 
			 * match the slider for the particular station chosen
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				stationList.setText(""); 

				String newStation = "";
				try 
				{
					ob.findNodeDistance((String) cBox1.getSelectedItem());
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				TreeSet<String> newTrees = ob.getStations(); 
				for(String a : newTrees) {
					newStation += a + "\n"; //adds a new line after each loop through to keep formatting
				}
				stationList.setText(newStation);
			}
		});

		//button to calculate the hamming distance
		calcHD = new JButton("Calculate HD"); 

		//label attached the combo box of all the stations 
		compareWith = new JLabel("Compare with:"); 

		//layout manager for the frame
		frame.setLayout(new GridLayout(0, 2));
		//main panels that other panels are other 
		p0.setLayout(new GridLayout(7, 0));

		//p1 is set to be 2 rows and 0 columns 
		//others are blank becasue there is only one thing to add 
		//p6/7/8 are specific based on the things added to that panel later
		p1.setLayout(new GridLayout(0, 2));
		p2.setLayout(new GridLayout());
		p3.setLayout(new GridLayout());
		p4.setLayout(new GridLayout());
		p5.setLayout(new GridLayout());
		p6.setLayout(new GridLayout(7, 2));
		p7.setLayout(new GridLayout(1, 2));
		p8.setLayout(new GridLayout(0, 2)); 

		//changes the treeset values to an array and then makes them a 
		//general object array
		Object[] alphaStations = guiTreeSet.toArray();

		//comboBox using the object array 
		cBox1 = new JComboBox(alphaStations); 
		cBox1.setSize(20, 20);

		//labels and blanks for the distances
		l0 = new JLabel("Distance 0");  
		d0 = new JTextField();
		d0.setEditable(false);
		l1  = new JLabel("Distance 1");  
		d1 = new JTextField();  
		d1.setEditable(false);
		l2  = new JLabel("Distance 2");  
		d2 = new JTextField();  
		d2.setEditable(false);
		l3  = new JLabel("Distance 3");  
		d3 = new JTextField();
		d3.setEditable(false);
		l4  = new JLabel("Distance 4");  
		d4 = new JTextField();
		d4.setEditable(false);

		//action listener to find the node distance and update the labels and blanks
		calcHD.addActionListener(new ActionListener() {
			@Override
			/**
			 * action listener for node distance 
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					ob.findNodeDistance((String) cBox1.getSelectedItem());
				} 
				catch (IOException i) {
					i.printStackTrace();
				}

				int[] nodeActionListener = ob.getNodes();
				d0.setText("" + nodeActionListener[0]);
				d1.setText("" + nodeActionListener[1]);
				d2.setText("" + nodeActionListener[2]);
				d3.setText("" + nodeActionListener[3]);
				d4.setText("" + nodeActionListener[4]);
			}
		});

		//adds a blank next to the calculate hd to correct the spacing
		blank = new JLabel(); 

		//Add Station button and the textfield and an error if unable to add 
		add = new JButton("Add Station");  
		addee = new JTextField(""); 
		addingIssue = new JLabel("Unable to add");  
		add.addActionListener(new ActionListener(){
			/**
			 * action listener for adding new station to the list of stations
			 * if statement to verify the length is correct
			 * if else to see if it's already contained in list
			 * throws error if so
			 */
			public void actionPerformed(ActionEvent e){
				if(addee.getText().length() == 4)
				{ 
					String newStation = addee.getText();
					TreeSet<String> addTreeSet = ob.getTreeStations();
					if(!(addTreeSet.contains(addee.getText()))){
						addTreeSet.add(newStation);  
						cBox1.addItem(newStation.toUpperCase());
					}
					else 
					{
						addingIssue.setVisible(true);
						p7.repaint();
					}
				}
			}
		});

		//Creative panel - clock and color change
		timeD = new JLabel("The current time is: ");
		timeL = new JTextField(timer); 
		timeColor = new JButton("Change the color"); 
		timeColor.addActionListener(new ActionListener() {
			/**
			 * action listener to see if the aforementioned boolean is true or false
			 * and thus change the color if applicable
			 */
			public void actionPerformed(ActionEvent e) {
				if(inZone == false)
				{
					p8.setBackground(Color.LIGHT_GRAY);
				}
				else {
					p8.setBackground(Color.YELLOW);
				}
			}

		});

		//adds all the components to the panels
		p1.add(enterHD); 
		p1.add(enterHDtextField); 

		p2.add(slider); 

		p3.add(showStations); 

		p4.add(stationList); 
		scroller.setViewportView(stationList);
		p4.add(scroller);
		p5.add(compareWith);

		p5.add(cBox1); 

		p6.add(calcHD);
		p6.add(blank); 
		p6.add(l0); 
		p6.add(d0);
		p6.add(l1); 
		p6.add(d1); 
		p6.add(l2); 
		p6.add(d2); 
		p6.add(l3); 
		p6.add(d3); 
		p6.add(l4); 
		p6.add(d4); 

		p7.add(add); 
		p7.add(addee); 
		p7.add(addingIssue); 
		//error message is hidden until shown later if thrown 
		addingIssue.setVisible(false); 

		p0.add(p1); 
		p0.add(p2);
		p0.add(p3); 
		p0.add(p4); 
		p0.add(p5); 
		p0.add(p6); 
		p0.add(p7); 

		p8.add(timeD); 
		p8.add(timeL);
		p8.add(timeColor); 

		//adds the main two panels to the frame and sets visibility to true 
		frame.add(p0);
		frame.add(p8); 
		frame.setSize(600, 800);
		frame.setVisible(true);
	}
}