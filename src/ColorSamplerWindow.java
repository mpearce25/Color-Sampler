import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings("serial")
public class ColorSamplerWindow extends JFrame {

	private static JFrame frame;
	private static JLabel colorSample;
	private static JToolBar toolbar;
	private static JLabel colorInfo;
	private static boolean run = true;
	private static doublePoint frozenMouseLocation = new doublePoint(0, 0);
	private static favorites favoritesList;

	public ColorSamplerWindow(String title) throws IOException, AWTException {

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
		toolbar.setPreferredSize(new Dimension(400, 35));
		toolbar.setFloatable(false);
		toolbar.setFocusable(false);

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		buttonCopyHex.setPreferredSize(new Dimension(150, 35));
		buttonCopyHex.setFocusable(false);

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
		// toolbar.addSeparator();

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		buttonCopyRGB.setPreferredSize(new Dimension(150, 35));
		buttonCopyRGB.setFocusable(false);

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
		toolbar.addSeparator();

		// //////////Add to favorites button
		JButton addFavorite = new JButton("Add Favorite");
		addFavorite.setPreferredSize(new Dimension(150, 35));
		addFavorite.setFocusable(false);
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
						String name = getFavoriteName();

						favorites.addFavorite(new colorFavorite(screenInfo
								.getColor(frozenMouseLocation), name));
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		toolbar.add(addFavorite);

		// /////Favorites

		JButton favorites = new JButton("Favorites");
		favorites.setPreferredSize(new Dimension(150, 35));
		favorites.setFocusable(false);
		favorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println(favoritesList);
			}
		});

		toolbar.add(favorites);

		frame.getContentPane().add(toolbar, BorderLayout.NORTH);
	}

	// ////////////////////// color text values
	private void initColorInfo() {
		initColorInfoLabel();
		setColorInfoText("hfjghfgttr");
	}

	private void initColorInfoLabel() {

		colorInfo = new JLabel("", SwingConstants.CENTER);
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

	public static String getFavoriteName() {

		// //////create ask name window

		ImageIcon icon = new ImageIcon("/res/images/colorWheel.png",
				"colorWheel");

		int nextElement = favoritesList.getArray().size();

		String s = (String) JOptionPane.showInputDialog(frame,
				"Enter favorite name", "Add Favorite",
				JOptionPane.PLAIN_MESSAGE, icon, null, // makes it empty string
														// box
				"Favorite #" + nextElement + 1); // +1 is so that names start at
													// #1 not #0

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

	// //////// key listener for space par
	private class MyDispatcherSpace implements KeyEventDispatcher {
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
			System.out.println(e.getID());
			//if (e.getID() == KeyEvent.VK_SPACE) {

				System.out.println(e.getKeyChar());
				if (e.getKeyChar() == 'f') {// /if F key pressed

					if (run) {
						ImageIcon icon = new ImageIcon(
								"/res/images/colorWheel.png", "color wheel");
						JOptionPane.showMessageDialog(frame,
								"Please freeze program first.", "Error",
								JOptionPane.INFORMATION_MESSAGE, icon);
					} else {
						try {
							String name = getFavoriteName();

							favorites.addFavorite(new colorFavorite(screenInfo
									.getColor(frozenMouseLocation), name));
						} catch (AWTException f) {
							// TODO Auto-generated catch block
							f.printStackTrace();
						}

					}
				}
			//}
			return false;
		}
	}
}
