import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ButtonUI;

public class ColorSamplerWindow extends JFrame{

	private static JFrame frame;
	private static JLabel colorSample;
	private static JToolBar toolbar;
	private static JLabel colorInfo;
	private static Border raisedEtched;

	public ColorSamplerWindow(String title) throws IOException, AWTException {
		initFrame(title);
		initToolbar();
		initColorSample();
		initColorInfo();// /////creatres label & starts tracking value
		initKeyListener();
		
		
		raisedEtched = BorderFactory.createLineBorder(Color.BLACK);
				
		frame.pack();
		frame.setVisible(true);// / draws the frame once all components have
								// been added
 
	}

	private void initKeyListener() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
		
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
		toolbar.setFloatable(false);
		toolbar.setFocusable(false);
		

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		buttonCopyHex.setPreferredSize(new Dimension(200, 35));
		buttonCopyHex.setFocusable(false);
	
		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					
						Utilities.setClipboard(screenInfo.getColorHex());	//sets to currrent scren location
				
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		toolbar.add(buttonCopyHex);
		toolbar.addSeparator();
		
		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		buttonCopyRGB.setPreferredSize(new Dimension(200, 35));
		buttonCopyRGB.setFocusable(false);
		
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
		
		toolbar.add(buttonCopyRGB);
		frame.getContentPane().add(toolbar, BorderLayout.NORTH);
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
	
	

	//////////does key listener
	private class MyDispatcher implements KeyEventDispatcher {
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){///if key pressed
                	System.out.println("Space");
                	try {
						colorSamplerUpdater.switchRun();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            } 
            return false;
        }
    }

}
