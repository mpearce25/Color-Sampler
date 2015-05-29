import java.awt.*;
import java.awt.event.*;

import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class ColorSamplerWindow extends JFrame {

	private static JFrame colorSamplerWindowFrame;
	private static JLabel colorSample, colorInfo;
	private static JToolBar toolbar;
	private static boolean run = true;
	private static doublePoint frozenMouseLocation = new doublePoint(0, 0);
	private static favorites favoritesList;

	public ColorSamplerWindow(String title, doublePoint location)
			throws IOException, AWTException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		favoritesList = new favorites();
		initFrame(title, location);
		initToolbar();
		initColorSample();
		initColorInfo();// /////creatres label & starts tracking value
		initSpaceListener();
		initFListener();

		displayFrame();

		initColorUpdater(); // must be run last
	}

	private void displayFrame() {
		colorSamplerWindowFrame.pack();
		colorSamplerWindowFrame.setVisible(true);// / draws the frame once all
													// components have
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
	private void initFrame(String title, doublePoint location)
			throws IOException {

		colorSamplerWindowFrame = new JFrame();
		uiUtil.initFrame(colorSamplerWindowFrame, title, location);
	}

	// ///// Color sample Jlabels
	private void initColorSample() throws AWTException {

		colorSample = new JLabel();
		colorSample.setOpaque(true);
		colorSample.setBackground(screenInfo.getColor());
		colorSample.setPreferredSize(new Dimension(200, 170));
		colorSamplerWindowFrame.getContentPane().add(colorSample,
				BorderLayout.CENTER);
	}

	public static void setColorSampleColor(Color color) {

		colorSample.setBackground(color);
		colorSamplerWindowFrame.getContentPane().add(colorSample,
				BorderLayout.CENTER);
	}

	// //// ToolBar
	private void initToolbar() {
		toolbar = new JToolBar();
		uiUtil.initToolbar(toolbar, new doublePoint(424, 42), Color.WHITE);
		toolbar.addSeparator(new Dimension(4, 0));

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");

		uiUtil.setMaterialButton(buttonCopyHex, new Dimension(150, 35));

		// buttonCopyHex.setBackground(Color.GREEN);
		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {

					if (run) {
						Utilities.setClipboard(screenInfo.getColorHex()); // sets
																			// //
																			// to
																			// current
					} else {
						// if program is not running will use paused values
						Utilities.setClipboard(screenInfo
								.getColorHex(frozenMouseLocation));
					}

				} catch (AWTException e) {

					e.printStackTrace();
				}
			}
		});

		toolbar.add(buttonCopyHex);
		toolbar.addSeparator(new Dimension(4, 0));

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");

		uiUtil.setMaterialButton(buttonCopyRGB, new Dimension(150, 35));
		// buttonCopyRGB.setBorderPainted(false);

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

					e.printStackTrace();
				}
			}
		});

		toolbar.add(buttonCopyRGB);
		toolbar.addSeparator(new Dimension(8, 0));

		// //////////Add to favorites button
		JButton addFavorite = new JButton("Add Favorite");
		uiUtil.setMaterialButton(addFavorite, new Dimension(150, 35));
		addFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (run) {
					ImageIcon icon = new ImageIcon(
							"/res/images/colorWheel.png", "color wheel");
					JOptionPane.showMessageDialog(colorSamplerWindowFrame,
							"Please freeze program first.", "Error",
							JOptionPane.INFORMATION_MESSAGE, icon);
				} else {
					try {
						addNewFavorite();
					} catch (IOException e) {

						e.printStackTrace();
					} catch (AWTException e) {

						e.printStackTrace();
					}
				}

			}
		});

		toolbar.add(addFavorite);

		toolbar.addSeparator(new Dimension(4, 0));

		// /////Favorites

		JButton favoritesButtton = new JButton("Favorites");

		uiUtil.setMaterialButton(favoritesButtton, new Dimension(150, 35));
		favoritesButtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					favoritesList.populateArray();
					//creates point of top right hand corner
					doublePoint topRightCornerLocation = new doublePoint(
							colorSamplerWindowFrame.getLocation().getX()
									+ colorSamplerWindowFrame.getWidth(),

									colorSamplerWindowFrame.getLocation().getY() + 20); //need + 20 to properly align
					new favoritesWindow("Favorites", favorites.getArray(),topRightCornerLocation); // creates popup frame

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});

		toolbar.add(favoritesButtton);
		toolbar.addSeparator(new Dimension(4, 0));

		colorSamplerWindowFrame.getContentPane().add(toolbar,
				BorderLayout.NORTH);
	}

	// ////////////////////// color text values
	private void initColorInfo() {
		initColorInfoLabel();
		setColorInfoText("Color Info");
	}

	private void initColorInfoLabel() {

		colorInfo = new JLabel("", SwingConstants.CENTER);
		// colorInfo.setOpaque(true);
		colorInfo.setBackground(new Color(255, 255, 255));
		colorInfo.setPreferredSize(new Dimension(400, 25));
		colorSamplerWindowFrame.getContentPane().add(colorInfo,
				BorderLayout.SOUTH);
	}

	public static void setColorInfoText(String text) {

		colorInfo.setText(text);

	}

	public static void initColorUpdater() throws AWTException {

		Robot bot = new Robot();
		while (true) { // always run

			bot.delay(50);
			if (run) {// lets it constantaly run with out having to re call
						// method which causes it to crash

				ColorSamplerWindow.setColorInfoText("Hex: "
						+ screenInfo.getColorHex() + "\t\t\t\tRGB: "
						+ screenInfo.getColorRGB());
				ColorSamplerWindow.setColorSampleColor(screenInfo.getColor());

			}

		}
	}

	@SuppressWarnings("static-access")
	public static String getFavoriteName() throws IOException { // //input
																// dialog

		// //////create ask name window

		favoritesList.populateArray(); // to get next in array

		int nextElement = favoritesList.getArray().size();

		String s = (String) JOptionPane.showInputDialog(
				colorSamplerWindowFrame, "Enter favorite name", "Add Favorite",
				JOptionPane.PLAIN_MESSAGE, null, null, // makes it empty string

				"Favorite #" + (nextElement + 1)); // +1 is so that names start
													// at #1 not #0 /
													// placeholder
		return s;
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
						frozenMouseLocation.setPoints(Utilities
								.getMouseCoordinates());

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
					JOptionPane.showMessageDialog(colorSamplerWindowFrame,
							"Please freeze program first.", "Error",
							JOptionPane.INFORMATION_MESSAGE, icon);
				} else {
					try {
						addNewFavorite();
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (AWTException e1) {

						e1.printStackTrace();
					}
				}
			}
			return false;
		}
	}
}
