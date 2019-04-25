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

public class NewPanel {

		private static final int FRAME_WIDTH = 600;
		private static final int FRAME_HEIGHT = 800;

		private final class NewPanel extends JPanel implements MouseListener
		{

			private static final int PANEL_WIDTH = FRAME_WIDTH;
			private static final int PANEL_HEIGHT = 300; 
			private static final int REGION_WIDTH = 50;
			private static final int REGION_HEIGHT = 50;
			private static final int REGION_START_X = 50;
			private static final int REGION_START_Y = 50;
			private static final int REGION_INC_X = 60;

			public void Slider(ChangeEvent e) {


				final int MIN = 0; 

				final int MAX = 4; 
				final int CENTER = 2; 

				JSlider oneToFour = new JSlider(JSlider.HORIZONTAL, MIN, MAX, CENTER);


			}
		}
}