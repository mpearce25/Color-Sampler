import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ColorPickerWindow extends JFrame {

	private static JFrame frame;
	private static JLabel colorSample;
	private static JButton copyHex;
	private static JPanel panel;


	public ColorPickerWindow(String title) throws IOException, AWTException {
		initFrame(title);
		initPanel();
		initColorSample();

		frame.pack();
		frame.setVisible(true);// / draws the frame once all components have
								// been added

	}

	// ////Main JFrame + Menu Bar
	private static void initFrame(String title) throws IOException {

		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static JFrame getFrame() {
		return frame;

	}

	// ///////Main Panel
	public static void initPanel() {
		
	}
	
	// ///// Color sample Jlabels
	private static void initColorSample() throws AWTException {

		colorSample = new JLabel();
		colorSample.setOpaque(true);
		colorSample.setBackground(screenInfo.getColor());
		colorSample.setSize(new Dimension(250, 70));
		frame.getContentPane().add(colorSample, BorderLayout.CENTER);

		JButton quitButton = new JButton("Quit");
	       quitButton.setBounds(50, 60, 80, 30);
	       quitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);
	          }
	       });

	       frame.getContentPane().add(quitButton);

	      

	}

	public static JLabel getColorSample() {
		return colorSample;

	}

	public static void setColorSampleColor(Color color) {

	}

	// //// Buttons
	// copy hex
	private static void initCopyHex() {

		copyHex = new JButton("Copy Hex");
		copyHex.setSize(new Dimension(100, 100));
		getFrame().add(copyHex);

	}

	public static JButton getCopyHex() {
		return copyHex;

	}
	// copy RGB

}
