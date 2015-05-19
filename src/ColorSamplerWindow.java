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

public class ColorSamplerWindow extends JFrame {

	private static JFrame frame;
	private static JLabel colorSample;
	private static JToolBar toolbar;
	private static JLabel colorInfo;

	public ColorSamplerWindow(String title) throws IOException, AWTException {
		initFrame(title);
		initPanel();
		initToolbar();
		initColorSample();
		initColorInfo();// /////creatres label & starts tracking values

		
		
		/////listener 
		
		
		
		/*SpaceBarListener listener = new SpaceBarListener();
		frame.getContentPane().add(listener);
		listener.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
				"Freeze Program");
		listener.getActionMap().put("Freeze Program", listener.getAction());

		/////end listner*/
		
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
		colorSample.setPreferredSize(new Dimension(200, 170));
		frame.getContentPane().add(colorSample, BorderLayout.CENTER);

	}

	public static JLabel getColorSample() {
		return colorSample;

	}

	public static void setColorSampleColor(Color color) {

		colorSample.setBackground(color);
		frame.getContentPane().add(colorSample, BorderLayout.CENTER);
	}

	// //// ToolBar
	private static void initToolbar() {
		toolbar = new JToolBar("Copy Commands");
		toolbar.setPreferredSize(new Dimension(400, 35));
		frame.getContentPane().add(toolbar, BorderLayout.NORTH);

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Utilities.setClipboard(screenInfo.getColorHex());
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		buttonCopyHex.setIcon(new ImageIcon("/res/images/colorWheel.png"));
		buttonCopyHex.setPreferredSize(new Dimension(200, 35));
		toolbar.add(buttonCopyHex);

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		buttonCopyRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Utilities.setClipboard(screenInfo.getColorRGB());
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		buttonCopyRGB.setPreferredSize(new Dimension(200, 35));
		// buttonCopyRGB.setIcon(new ImageIcon("res/images/colorWheel.png"));
		toolbar.add(buttonCopyRGB);
	}

	// ////////////////////// color text values
	private static void initColorInfo() {
		initColorInfoLabel();
		setColorInfoText("hfjghfgttr");
	}

	private static void initColorInfoLabel() {

		colorInfo = new JLabel("", SwingConstants.CENTER);
		colorInfo.setBackground(new Color(255, 255, 255));
		colorInfo.setPreferredSize(new Dimension(400, 25));
		frame.getContentPane().add(colorInfo, BorderLayout.SOUTH);
	}

	public static void setColorInfoText(String text) {

		colorInfo.setText(text);

	}

}
