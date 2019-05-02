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
	static JLabel text1;
	static JTextField textField1;
	static JSlider slider; 
	static JLabel text2;
	static JButton button1; 
	static JTextArea entry;
	static JTextArea hD; 
	static JButton button2; 
	static JLabel text3;
	static JButton button3;
	static JTextField textField2; 
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

		TreeSet<String> guiTreeSet = new TreeSet<String>(); 
		guiTreeSet = ob.getTreeSet(); 

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

		slider = new JSlider(1, 4, 2); 
		slider.setMajorTickSpacing(1);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		text1 = new JLabel("Enter Hamming Dist:"); 
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e){
				String state = ""; 
				state += slider.getValue(); 
				textField1.setText(state); 
			}
		}); 

		textField1 = new JTextField();
		textField1.setEditable(false);
		hD = new JTextArea(1, 10);

		button1 = new JButton("Show Station"); 
		entry = new JTextArea(20, 15); 
		entry.setEditable(false);

		JScrollPane scroller = new JScrollPane();

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entry.setText(""); 

				String newStation = "";
				try {
					ob.findNodeDistance((String) cBox1.getSelectedItem());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TreeSet<String> newTrees = ob.getStations(); 
				for(String a : newTrees) {
					newStation += a + "\n"; 
				}
				entry.setText(newStation);
			}
		});

		button2 = new JButton("Calculate HD"); 

		text3 = new JLabel("Compare with:"); 

		textField2 = new JTextField(4); 

		frame.setLayout(new GridLayout(0, 2));

		p0.setLayout(new GridLayout(7, 0));

		p1.setLayout(new GridLayout(0, 2));
		p2.setLayout(new GridLayout());
		p3.setLayout(new GridLayout());
		p4.setLayout(new GridLayout());
		p5.setLayout(new GridLayout());
		p6.setLayout(new GridLayout(7, 2));
		p7.setLayout(new GridLayout(1, 2));
		p8.setLayout(new GridLayout(0, 2)); 

		Object[] alphaStations = guiTreeSet.toArray();

		cBox1 = new JComboBox(alphaStations); 
		cBox1.setSize(20, 20);

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


		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ob.findNodeDistance((String) cBox1.getSelectedItem());
				} 
				catch (IOException i) {
					i.printStackTrace();
				}

				int[] tno = ob.getNodes();
				d0.setText("" + tno[0]);
				d1.setText("" + tno[1]);
				d2.setText("" + tno[2]);
				d3.setText("" + tno[3]);
				d4.setText("" + tno[4]);
			}
		});

		blank = new JLabel(); 

		add = new JButton("Add Station");  
		addee = new JTextField(""); 
		addingIssue = new JLabel("Unable to add");  
		add.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(addee.getText().length() == 4)
				{
					String newStation = ""; 
					newStation += addee.getText(); 
					cBox1.addItem(newStation.toUpperCase());
					p7.repaint();
				}
				else 
				{
					addingIssue.setVisible(true);
					p7.repaint();
				}
			}
		});

		timeD = new JLabel("The current time is: ");
		timeL = new JTextField(timer); 
		timeColor = new JButton("Change the color"); 
		timeColor.addActionListener(new ActionListener() {
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

		p1.add(text1); 
		p1.add(textField1); 

		p2.add(slider); 

		p3.add(button1); 

		p4.add(entry); 
		scroller.setViewportView(entry);
		p4.add(scroller);
		p5.add(text3);

		p5.add(cBox1); 

		p6.add(button2);
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


		frame.add(p0);
		frame.add(p8); 
		frame.setSize(600, 800);
		frame.setVisible(true);
	}

}