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
	static JTextArea hD; 
	static JButton button2; 
	static JLabel text3;
	static JButton button3;
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
	static JLabel blank; 
	static JButton add; 
	static JTextField addee; 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException
	{
		HammingDistance ob = new HammingDistance(); 
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

		slider = new JSlider(0, 4, 2); 
		slider.setMajorTickSpacing(1);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		text1 = new JLabel("Enter Hamming Dist:"); 
		textField1 = new JTextField();
		textField1.setEditable(false);
		hD = new JTextArea(1, 10);

		button1 = new JButton("Show Station"); 
		entry = new JTextArea(20, 15); 
		entry.setEditable(false);

		button2 = new JButton("Calculate HD"); 

		text3 = new JLabel("Compare with:"); 
		button3 = new JButton("Add Station"); 

		ActionListener addStation = new GUI(); 
		button3.addActionListener(addStation);

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

		Object[] alphaStations = guiTreeSet.toArray();

		cBox1 = new JComboBox(alphaStations); 
		cBox1.setSize(20, 20);

		text5 = new JLabel("Compre w/ new");

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

		blank = new JLabel(); 

		add = new JButton("Add Station");  
		addee = new JTextField(""); 

		p1.add(text1); 
		p1.add(textField1); 

		p2.add(slider); 

		p3.add(button1); 

		p4.add(entry); 

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

		p0.add(p1); 
		p0.add(p2);
		p0.add(p3); 
		p0.add(p4); 
		p0.add(p5); 
		p0.add(p6); 
		p0.add(p7); 

		frame.add(p0);
		frame.setSize(600, 800);
		frame.setVisible(true);

	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String s = e.getActionCommand(); 
//		if (s.contentEquals("HERE")) {
//			text3.setText(entry.getText());
//		}
	//}
}