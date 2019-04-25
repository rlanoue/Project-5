import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{
	static JFrame frame; 
	static JLabel text1;
	static JTextField textField1;

	static JSlider slider; 
	static JLabel text2;


	static JButton button1; 
	static JTextArea entry;

	//static JLabel text4;
	static JTextArea hD; 

	static JButton button2; 

	static JLabel text3;

	static JButton button3;
	static JLabel text4; 
	static JTextField textField2; 

	static JLabel text5; 
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


	public static void main(String[] args) throws IOException
	{
		HammingDistance ob = new HammingDistance(); 

		ob.read("Mesonet.txt");

		TreeSet<String> guiTreeSet = new TreeSet<String>(); 
		guiTreeSet = ob.getTreeSet(); 

		frame = new JFrame("Panel"); 
		JPanel p1 = new JPanel(); 
		//JPanel p1 = new JPanel(); 

		//p1.setSize(100, 20);

		text1 = new JLabel("Enter Hamming Distance"); 
		textField1 = new JTextField(4);
		hD = new JTextArea(1, 10);

		slider = new JSlider(0, 4, 2); 
		slider.setMajorTickSpacing(1);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		//	ActionListener sliding = new NewFrame(); 
		//slider.addActionListener(sliding);
		text2 = new JLabel("" + slider.getValue()); 

		button1 = new JButton("Show Station"); 
		//ActionListener textEntry = new NewFrame(); 
		//button1.addActionListener(textEntry);
		entry = new JTextArea(20, 15); 

		button2 = new JButton("Calculate HD"); 

		text3 = new JLabel("Compare with:"); 

		text4 = new JLabel("REPLACE ME WITH STATION NAME"); 
		button3 = new JButton("Add Station"); 
		ActionListener addStation = new GUI(); 
		button3.addActionListener(addStation);
		textField2 = new JTextField(4); 

		//JList 
		frame.setLayout(new GridLayout(0, 2));
		p1.setLayout(new GridLayout(0, 2));

		Object[] alphaStations = guiTreeSet.toArray();

		cBox1 = new JComboBox(alphaStations); 

		text5 = new JLabel("Compre w/ new");

		l0 = new JLabel("Distance 0");  
		d0 = new JTextField();  
		l1  = new JLabel("Distance 1");  
		d1 = new JTextField();  
		l2  = new JLabel("Distance 2");  
		d2 = new JTextField();  
		l3  = new JLabel("Distance 3");  
		d3 = new JTextField();
		l4  = new JLabel("Distance 4");  
		d4 = new JTextField();  



		//p.add(p1); 
		p1.add(text1); 
		p1.add(textField1); 
		p1.add(slider); 
		p1.add(text2); 
		p1.add(button1); 
		p1.add(entry); 
		p1.add(hD); 
		p1.add(button2);
		p1.add(text3);
		p1.add(text4); 
		p1.add(button3);
		p1.add(textField2);
		p1.add(cBox1); 
		p1.add(text5);
		p1.add(l0); 
		p1.add(d0);
		p1.add(l1); 
		p1.add(d1); 
		p1.add(l2); 
		p1.add(d2); 
		p1.add(l3); 
		p1.add(d3); 
		p1.add(l4); 
		p1.add(d4); 

		frame.add(p1); 
		frame.setSize(600, 800);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand(); 
		if (s.contentEquals("HERE")) {
			text3.setText(entry.getText());
		}
	}
}