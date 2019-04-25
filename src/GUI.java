import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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

	 

	

	public static void main(String[] args) throws IOException
	{
		HammingDistance ob = new HammingDistance(); 
		
		ob.read("Mesonet.txt");
		
		TreeSet<String> guiTreeSet = new TreeSet<String>(); 
		guiTreeSet = ob.getTreeSet(); 

		frame = new JFrame("Panel"); 
		JPanel p = new JPanel(); 

		text1 = new JLabel("Enter Hamming Distance"); 
		textField1 = new JTextField(4);
		hD = new JTextArea(1, 10);

		slider = new JSlider(0, 4, 2); 
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		//ActionListener sliding = new NewFrame(); 
		//slider.addMouseListener(sliding);
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

		frame.setLayout(new GridBagLayout());

		Object[] alphaStations = guiTreeSet.toArray();

		cBox1 = new JComboBox(alphaStations); 

		text5 = new JLabel("Compre w/ new"); 

		p.add(text1); 
		p.add(textField1); 
		p.add(slider); 
		p.add(text2); 
		p.add(button1); 
		p.add(entry); 
		p.add(hD); 
		p.add(button2);
		p.add(text3);
		p.add(text4); 
		p.add(button3);
		p.add(textField2);
		p.add(cBox1); 
		p.add(text5); 




		frame.add(p); 
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
