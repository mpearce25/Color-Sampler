import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ColorSamplerWindow extends JFrame {

	private static JFrame frame;
	private static JLabel colorSample;
	private static JToolBar toolbar;
	private static JLabel colorInfo;
	private static boolean run = true;
	private static doublePoint frozenMouseLocation = new doublePoint(0, 0);
	private static favorites favoritesList;

	public ColorSamplerWindow(String title) throws IOException, AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		// //lookup baloon tip
		favoritesList = new favorites();
		initFrame(title);
		initToolbar();
		initColorSample();
		initColorInfo();// /////creatres label & starts tracking value
		initSpaceListener();
		initFListener();

		frame.pack();
		frame.setVisible(true);// / draws the frame once all components have

		initColorUpdater();

	}

	private void initSpaceListener() {
		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcherSpace());

	}

	private void initFListener() {
		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new MyDispatcherF());
	}

	// ////Main JFrame + Menu Bar
	private void initFrame(String title) throws IOException {

		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public JFrame getFrame() {
		return frame;

	}

	// ///// Color sample Jlabels
	private void initColorSample() throws AWTException {

		colorSample = new JLabel();
		colorSample.setOpaque(true);
		colorSample.setBackground(screenInfo.getColor());
		colorSample.setPreferredSize(new Dimension(200, 170));
		frame.getContentPane().add(colorSample, BorderLayout.CENTER);

	}

	public JLabel getColorSample() {
		return colorSample;

	}

	public static void setColorSampleColor(Color color) {

		colorSample.setBackground(color);
		frame.getContentPane().add(colorSample, BorderLayout.CENTER);
	}

	// //// ToolBar
	private void initToolbar() {
		toolbar = new JToolBar("Copy Commands");
		toolbar.setPreferredSize(new Dimension(424, 42));
		toolbar.setFloatable(false);
		toolbar.setFocusable(false);
		toolbar.setBackground(new Color(255,255,255));
		toolbar.setBorder(new LineBorder(Color.WHITE));
		toolbar.addSeparator(new Dimension(4,0));

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		designUtilities.setMaterialButton(buttonCopyHex, new Dimension(150,35));
		

		//buttonCopyHex.setBackground(Color.GREEN);
		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {

					if (run) {
						Utilities.setClipboard(screenInfo.getColorHex()); // sets
																			// to
																			// current
					} else {
						// if program is not running will use paused values
						Utilities.setClipboard(screenInfo
								.getColorHex(frozenMouseLocation));
					}

				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		toolbar.add(buttonCopyHex);
		toolbar.addSeparator(new Dimension(4,0));

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		designUtilities.setMaterialButton(buttonCopyRGB, new Dimension(150,35));
		//buttonCopyRGB.setBorderPainted(false);
		
		
		
		buttonCopyRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {

					if (run) {
						Utilities.setClipboard(screenInfo.getColorRGB());
					} else {
						Utilities.setClipboard(screenInfo
								.getColorRGB(frozenMouseLocation));
					}
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		toolbar.add(buttonCopyRGB);
		toolbar.addSeparator(new Dimension(8,0));
		

		// //////////Add to favorites button
		JButton addFavorite = new JButton("Add Favorite");
		designUtilities.setMaterialButton(addFavorite, new Dimension(150,35));
		addFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (run) {
					ImageIcon icon = new ImageIcon(
							"/res/images/colorWheel.png", "color wheel");
					JOptionPane.showMessageDialog(frame,
							"Please freeze program first.", "Error",
							JOptionPane.INFORMATION_MESSAGE, icon);
				} else {
					try {
						addNewFavorite();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		toolbar.add(addFavorite);
		toolbar.addSeparator(new Dimension(4,0));

		// /////Favorites

		JButton favoritesButtton = new JButton("Favorites");
		designUtilities.setMaterialButton(favoritesButtton, new Dimension(150,35));
		favoritesButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					favoritesList.populateArray();
					new favoritesWindow("Favorites", favoritesList.getArray());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		toolbar.add(favoritesButtton);
		toolbar.addSeparator(new Dimension(4,0));

		frame.getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	// ////////////////////// color text values
	private void initColorInfo() {
		initColorInfoLabel();
		setColorInfoText("Color Info");
	}

	private void initColorInfoLabel() {

		colorInfo = new JLabel("", SwingConstants.CENTER);
		//colorInfo.setOpaque(true);
		colorInfo.setBackground(new Color(255, 255, 255));
		colorInfo.setPreferredSize(new Dimension(400, 25));
		frame.getContentPane().add(colorInfo, BorderLayout.SOUTH);
	}

	public static void setColorInfoText(String text) {

		colorInfo.setText(text);

	}
	
	public static void initColorUpdater() throws AWTException {

		doublePoint mouseLocation;
		int test = 1;
		Robot bot = new Robot();
		while (test == 1) {

			bot.delay(50);
			if (run) {// lets it constantaly run with out having to re call
						// method which causes it to crash
				mouseLocation = screenInfo.getMouseCoordinates();
				ColorSamplerWindow.setColorInfoText("Hex: "
						+ screenInfo.getColorHex() + "\t\t\t\tRGB: "
						+ screenInfo.getColorRGB());
				ColorSamplerWindow.setColorSampleColor(screenInfo.getColor());
				// bot.delay(500);
			}

		}
	}

	@SuppressWarnings("static-access")
	public static String getFavoriteName() throws IOException { // //input
																// dialog for
																// name

		// //////create ask name window

		ImageIcon icon = new ImageIcon("/res/images/colorWheel.png",
				"colorWheel");

		favoritesList.populateArray();

		int nextElement = favoritesList.getArray().size();

		
		// /////

		String s = (String) JOptionPane.showInputDialog(frame,
				"Enter favorite name", "Add Favorite",
				JOptionPane.PLAIN_MESSAGE, icon, null, // makes it empty string
														// box
				"Favorite #" + (nextElement + 1)); // +1 is so that names start
													// at #1 not #0

		// If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {

			return s;
		} else {
			JOptionPane.showMessageDialog(frame, "Please enter a name",
					"Error", JOptionPane.INFORMATION_MESSAGE, icon);
			getFavoriteName();
		}

		return null; // wont ever be called since windows wont be closed until
						// valid input is entered

	}

	public static void addNewFavorite() throws IOException, AWTException {
		String name = getFavoriteName();

		favoritesList.updateArray();
		favoritesList.addFavorite(new colorFavorite(screenInfo
				.getColor(frozenMouseLocation), name));
	}

	// //////// key listener for space bar
	private static class MyDispatcherSpace implements KeyEventDispatcher {
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {// /if key pressed

					if (run) {
						run = false;
						frozenMouseLocation.setX(MouseInfo.getPointerInfo()
								.getLocation().getX());
						frozenMouseLocation.setY(MouseInfo.getPointerInfo()
								.getLocation().getY());

					} else {
						run = true;
					}
				}
			}
			return false;
		}

	}

	private class MyDispatcherF implements KeyEventDispatcher {

		public boolean dispatchKeyEvent(KeyEvent e) {

			if (e.getKeyChar() == 'f' && e.getID() == 402) {// /if F key pressed

				if (run) {
					ImageIcon icon = new ImageIcon(
							"/res/images/colorWheel.png", "color wheel");
					JOptionPane.showMessageDialog(frame,
							"Please freeze program first.", "Error",
							JOptionPane.INFORMATION_MESSAGE, icon);
				} else {
					try {
						addNewFavorite();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
