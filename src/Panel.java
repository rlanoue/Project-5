import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Panel extends JFrame
{
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 800;

	private final class GraphicalCalculatorPanel extends JPanel implements MouseListener
	{
		
		private static final int PANEL_WIDTH = FRAME_WIDTH;
		private static final int PANEL_HEIGHT = 300; 
		private static final int REGION_WIDTH = 50;
		private static final int REGION_HEIGHT = 50;
		private static final int REGION_START_X = 50;
		private static final int REGION_START_Y = 50;
		private static final int REGION_INC_X = 60;

		Rectangle[] regions;

		Point[] textPoints;

		private int selectedRegion = 0;

		private int[] operands = {0, 0, 0};

		private String[] operators = {"+", "+"};

		public GraphicalCalculatorPanel()
		{
			this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			this.setupRegions();
			//this.setupTextPoints();
			this.addMouseListener(this);
		}

		private void setupRegions()
		{
			regions = new Rectangle[5]; 
			for (int i = 0; i < regions.length; i++) {
				regions[i] = new Rectangle(REGION_START_X + (i * REGION_INC_X), REGION_START_Y, REGION_WIDTH, REGION_HEIGHT); 
			}
		}
		
		public void Slider(ChangeEvent e) {
			
			
			final int MIN = 0; 
			final int MAX = 4; 
			final int CENTER = 2; 

			JSlider oneToFour = new JSlider(JSlider.HORIZONTAL, MIN, MAX, CENTER);

	
		}

		private void setupTzextPoints()
		{
			textPoints = new Point[7];

			int startX = REGION_START_X + 20;
			int startY = REGION_START_Y + 30;

			for (int pt = 0; pt < textPoints.length; pt++)
			{
				textPoints[pt] = new Point(startX + pt*REGION_INC_X, startY);
			}
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			// TODO: Draw bounding boxes on all regions (regions are stored as rectangles):
			for (int i = 0; i < regions.length; i++) {
				g.drawRect((int) regions[i].getX(), (int) regions[i].getY(), (int) regions[i].getWidth(), (int) regions[i].getHeight()); 
			}
			//for loop through the regions array to draw the rectangle of the given points as made in the setupRegion

			// Draw the text at the specified text points:
			// Pattern is: operand operator operand operator operand = result
			for (int pt = 0; pt < textPoints.length; pt++)
			{
				String drawString = "";
				switch(pt) {
				case 0:
					drawString = "" + operands[0];
					break;
				case 1:
					drawString = operators[0];
					break;
				case 2:
					drawString = "" + operands[1];
					break;
				case 3:
					drawString = operators[1];
					break;
				case 4:
					drawString = "" + operands[2];
					break;
				case 5:
					drawString = "=";
					break;
				case 6:
					drawString = "" + this.evaluate();
					break;
				}
				g.drawString(drawString, textPoints[pt].x, textPoints[pt].y);
			}

			// TODO: Draw translucent rectangle over selected region (use the highlight color):
			g.fillRect((int) regions[selectedRegion].getX(), (int) regions[selectedRegion].getY(), (int) regions[selectedRegion].getWidth(), 
					(int) regions[selectedRegion].getHeight()); 
			//colors the rectangle that is selected in the premade color 

		}

		/**
		 * When the panel is clicked, check if the point clicked lies within any of the editable regions
		 * of the expression. If so, that region set to the be the new selected region. Repaint at the
		 * end.
		 *
		 * (hint: look at the java.awt.Rectangle class for useful methods that you can use to check if
		 *  a point is within the rectangle)
		 */
		@Override
		public void mouseClicked(MouseEvent e)
		{
			for (int i = 0; i < regions.length; i++) {
				if (regions[i].contains(e.getPoint())) {
					selectedRegion = i; 
				}
			}
			//for loop to check if the region has that specific point and if so sets the selected region to the index of the array loop 


			// Repaint the panel (this will implicitly call paintComponent):
			this.repaint();
		}

		/**
		 * Attempts to set the content of the selected region. If the region is and operand the content must be
		 * a single-digit number (0-9). If the region is an operator the content must be "+", "-", or "*".
		 *
		 * Should call repaint at the end.
		 *
		 * @param content The value that the program will attempt to set the selected region to.
		 * @return True if the region content is sucessfully set. False otherwise.
		 */
		public boolean setSelectedRegionContents(String content)
		{
			//boolean success = true;

			int result = 0; 
			boolean janus = true; 
			try {
				result = Integer.parseInt(content);
				switch(selectedRegion) {
				case 0:
					if (result <= 9 && result >= 0) {
						operands[0] = result;
					}
					else {
						janus = false;
					}
					break;
				case 2:
					if (result <= 9 && result >= 0) {
						operands[1] = result;
					}
					else {
						janus = false;
					}
					break;
				case 4:
					if (result <= 9 && result >= 0) {
						operands[2] = result;
					}
					else {
						janus = false;
					}
					break;
				default:
					janus = false; 
				}		
			}
			catch (NumberFormatException e) {
				if (content.equals("+") || content.equals("*") || content.equals("-")) {
					switch(selectedRegion) {
					case 1: 
						operators[0] = content;
						break; 
					case 3: 
						operators[1] = content;
						break; 
					default: 
						janus = false; 
					}
				}
				else {
					System.out.println("Theres a horse, LOOSE, in the hospital - in other words, there's an issue");
					janus = false;} 
			}

			this.repaint();

			return janus;
			//try catch statement with a switch inside the try to alter the numbers input while the catch has the operators being set with 
			//some more if else statements 
			//boolean janus after the two faced greek god to show that it has two states, true or false 
		}

		/**
		 * Evaluates the equation on the panel. Operations are performed left-to-right, ignoring operator precendence
		 * (e.g. and equation of 2+3*4 will return (2+3)*4 = 20)
		 *
		 * @return The evaluation of the expression displayed by the Panel.
		 */
		public int evaluate()
		{
			int answer = 0; 

			switch(operators[0]) {
			case "+":
				answer = operands[0] + operands[1]; 
				break;
			case "*":
				answer = operands[0] * operands[1]; 
				break;
			case "-":
				answer = operands[0] - operands[1]; 
				break;
			default: 
				throw new Error("Theres a horse, LOOSE, in the hospital - in other words, there's an issue"); 
			}

			int newAnswer = 0; 
			switch(operators[1]) {
			case "+":
				newAnswer = answer + operands[2]; 
				break;
			case "*":
				newAnswer = answer * operands[2]; 
				break;
			case "-":
				newAnswer = answer - operands[2]; 
				break;
			default: 
				throw new Error("Theres a horse, LOOSE, in the hospital - in other words, there's an issue"); 
			}
			return newAnswer; 
			//another switch to return the correct calculations within the calculator 
			//an essential part was adding the first two indices' answer to the third rather than all together 
			//this only works because we are disregarding order of operations 
		}

		/** DO NOT MODIFY - DOES NOTHING */
		@Override
		public void mousePressed(MouseEvent e) {}

		/** DO NOT MODIFY - DOES NOTHING */
		@Override
		public void mouseReleased(MouseEvent e) {}

		/** DO NOT MODIFY - DOES NOTHING */
		@Override
		public void mouseEntered(MouseEvent e) {}

		/** DO NOT MODIFY - DOES NOTHING */
		@Override
		public void mouseExited(MouseEvent e) {}
	}

	/** panel for displaying and interacting with the expression */
	GraphicalCalculatorPanel gcPanel = new GraphicalCalculatorPanel();

	//==================================================================================================================
	// Panels for component grouping and organization:
	//==================================================================================================================

	/** panel to hold the non-GraphicalCalculatorPanel panels */
	JPanel panel0 = new JPanel(new GridLayout(2, 2));
	/** panel for operand text entry */
	JPanel panel1 = new JPanel();
	/** panel for the radio buttons */
	JPanel panel2 = new JPanel(new GridLayout(3, 0));
	/** panel for the set operand/operator buttons  */
	JPanel panel3 = new JPanel(new GridLayout(3, 0));
	/** panel for the error message */
	JPanel panel4 = new JPanel();
	
	JSlider slider = new JSlider(); 

	//==================================================================================================================
	// Operand Entry:
	//==================================================================================================================

	/** Text field for the user's number input */
	JTextField operandEntry = new JTextField("");

	/**
	 * Button to attempt to set the selected region in the Graphical panel to the value in the operand
	 * entry text field.
	 */
	JButton setOperand = new JButton("Set Operand");

	//==================================================================================================================
	// Operator Entry:
	//==================================================================================================================

	/** Group of operation buttons */
	ButtonGroup ops = new ButtonGroup();

	/** add operation radio button */
	JRadioButton add = new JRadioButton("+");
	/** divide operation radio button */
	JRadioButton subtract = new JRadioButton("-");
	/** multiply operation radio button */
	JRadioButton multiply = new JRadioButton("*");

	/**
	 * Button to attempt to set the selected region in the Graphical panel to the selected operator (as defined by
	 * the radio buttons).
	 */
	JButton setOperator = new JButton("Set Operator");

	//==================================================================================================================
	// Misc.
	//==================================================================================================================

	/** Text that display an error message */
	JLabel errorMessage = new JLabel();

	//==================================================================================================================
	// Main and constructor:
	//==================================================================================================================

	/**
	 * This method builds and operates the GUI window.
	 * @param title The title of the window.
	 */
	public Panel() {
		super("panel");

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new GridLayout(2, 0));

		panel1.add(operandEntry); 
		panel2.add(setOperator); 
		panel2.add(setOperand); 
		panel3.add(add); 
		panel3.add(subtract);
		panel3.add(multiply); 
		panel4.add(errorMessage); 
		panel4.add(slider); 
		

		//adds the right components to the specific panel that should implement those components
		add.setSelected(true); //remember, the button group ensures only one button is selected
		ops.add(add);
		ops.add(subtract);
		ops.add(multiply); 
		ops.add(setOperator);
		ops.add(setOperand);

		//the radio button group disallows the user to select more than one operator or the set operator/operand buttons

		panel0.add(panel1); 
		panel0.add(panel2); 
		panel0.add(panel3); 
		panel0.add(panel4); 



		// Adds all panels to frame:
		panel0.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 300));

		this.add(panel0);
		this.add(gcPanel);

		// Set ActionListeners on buttons:

		/*
		 * Attempts to set the selected region in gcPanel to the operand value in the operandEntry textbox.
		 * If the set operation fails, display the error message "Failed to set operand value".
		 * If the set operation succeeds, clear any error messages.
		 */
		setOperand.addActionListener((e) -> {
			if (gcPanel.setSelectedRegionContents(operandEntry.getText()))
			{
				errorMessage.setText("");
			}
			else 
			{
				errorMessage.setText("Failed to set operand value");
			}
		}
				);


		/*
		 * Attempts to set the selected region in gcPanel to the selected operator (which radio button is pressed).
		 * Pass the string:
		 * 	"+" if the add button is selected
		 *  "-" if the subtract button is selected
		 *  "*" if the multiply button is selected
		 *
		 * If the set operation fails, display the error message "Failed to set operator value".
		 * If the set operation succeeds, clear any error messages.
		 */
		setOperator.addActionListener((e) -> {
			// TODO: attempt to modify the selected region in gcPanel with the new operator value.
			boolean success = true; 

			/**
			 * action listener to check what operation is being selected and then translating it to the 
			 * regions of 1 and 3 as the operands fill 0 2 and 4 cases
			 * also throws an error if unable to due to a operator being set on an operand region 
			 */
			if (add.isSelected())
			{
				success = gcPanel.setSelectedRegionContents("+"); 
			}
			else if(subtract.isSelected()) 
			{
				success = gcPanel.setSelectedRegionContents("-"); 
			}
			else if(multiply.isSelected()) 
			{
				success = gcPanel.setSelectedRegionContents("*"); 
			}
			else {
				success = false; 
			}

			if (success) {
				errorMessage.setText("");
			}
			else {
				errorMessage.setText("Failed to set operator value");
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	/**
	 * Main method to the program. Creates a new GraphicalCalculatorFrame object,
	 * calling its constructor.
	 *
	 * @param args The program arguments.
	 */
	public static void main(String[] args)
	{
		new Panel();
	}
}
