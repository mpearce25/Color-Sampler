import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class favoritesWindow {

	private JFrame favoritesWindow;
	private JToolBar copyToolbar;
	private JToolBar manageToolbar;
	private JList<colorFavorite> favoritesList;
	private ArrayList<colorFavorite> array;
	private DefaultListModel<colorFavorite> listModel;
	
	private JTable favoritesListTable;

	public favoritesWindow(String title, ArrayList<colorFavorite> array, doublePoint locationOfColorFavoritesWindow) {

		
		this.array = array;
		initFrame(title, locationOfColorFavoritesWindow, new Dimension(308,260));
		initCopyToolbar();
		initManageToolbar();
		initFavoritesList(array);

		favoritesWindow.pack();
		favoritesWindow.setVisible(true);
	}

	private void initFavoritesList(ArrayList<colorFavorite> array) {

		
		
		String[] columnNames = {"Name", "Hex Code"};
		
		Object[][] data = {
			    {"yellow", "random hex"},{"yellohgjdfgsw", "random fgdhex"},
			   
			};
		
		//listModel = new DefaultListModel<>();
		favoritesListTable = new JTable(data,columnNames);
		//favoritesListTable.setModel(new MyTableModel()); // causing error
		
		/*for (colorFavorite favorite : array) {
			listModel.addElement(favorite); // adds all array elements to jlist
		}*/

		// create the list
		//favoritesList = new JList<>(listModel);
		
		favoritesWindow.add(favoritesListTable);
		//favoritesWindow.add(favoritesList);
	}

	private void initManageToolbar() {
		manageToolbar = new JToolBar("Manage Toolbar");
		uiUtil.initToolbar(manageToolbar, new doublePoint(400,35), Color.WHITE);

		// //////// Remove selected
		JButton removeSelectedButton = new JButton("Remove Selected");
		
		uiUtil.setMaterialButton(removeSelectedButton, new Dimension(150,35));
	
		removeSelectedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (favoritesList.getSelectedIndices().length > 0) {
					int[] selectedElements = favoritesList.getSelectedIndices();
					for (int i = selectedElements.length - 1; i >= 0; i--) {
						listModel.removeElementAt(selectedElements[i]);
						array.remove(selectedElements[i]);
						try {
							Utilities.writeFile("list.txt", array);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}
			}
		});

		manageToolbar.add(removeSelectedButton);
		manageToolbar.addSeparator(new Dimension(8,0));

		// /////Remove all
		JButton removeAllButton = new JButton("Remove All");
		uiUtil.setMaterialButton(removeAllButton, new Dimension(150,35));

		removeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				array.removeAll(array); // /removes all existing favorites array
										// is the array from the lidt.txt file
				try {
					Utilities.writeFile("list.txt", array);

					listModel.removeAllElements();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		manageToolbar.add(removeAllButton);
		favoritesWindow.getContentPane().add(manageToolbar, BorderLayout.SOUTH);

	}

	private void initFrame(String title, doublePoint location, Dimension size) {
		favoritesWindow = new JFrame();
		uiUtil.initFrame(favoritesWindow, title, location, size);

		favoritesWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //overrides defaut behavoir from initiframe method
		
	}

	private void initCopyToolbar() {
		copyToolbar = new JToolBar("Copy Commands");
		uiUtil.initToolbar(copyToolbar, new doublePoint(408,35), Color.WHITE);
		
		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		uiUtil.setMaterialButton(buttonCopyHex, new Dimension(150,35));

		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (favoritesList.getSelectedIndex() == -1) { // if nothing is
																// selected
					JOptionPane.showMessageDialog(favoritesWindow,
							"Please select at least one favorite.");
				} else if (favoritesList.getSelectedIndices().length > 1) { // ///if
																			// mutiple
																			// are
																			// selected
					JOptionPane.showMessageDialog(favoritesWindow,
							"Please only select one favorite.");
				} else {
					Utilities.setClipboard((array.get(favoritesList
							.getSelectedIndex()).getHex()));
				}
			}
		});

		copyToolbar.add(buttonCopyHex);
		copyToolbar.addSeparator(new Dimension(8,0));

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		uiUtil.setMaterialButton(buttonCopyRGB, new Dimension(150,35));
		buttonCopyRGB.setFocusable(false);

		buttonCopyRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (favoritesList.getSelectedIndex() == -1) { // if nothing is
																// selected
					JOptionPane.showMessageDialog(favoritesWindow,
							"Please select at least one favorite.");
				} else if (favoritesList.getSelectedIndices().length > 1) { // ///if
																			// mutiple
																			// are
																			// selected
					JOptionPane.showMessageDialog(favoritesWindow,
							"Please only select one favorite.");
				} else { // when only one is selected
					Utilities.setClipboard((array.get(favoritesList
							.getSelectedIndex()).getRGB()));
				}
			}
		});

		copyToolbar.add(buttonCopyRGB);
		favoritesWindow.getContentPane().add(copyToolbar, BorderLayout.NORTH);
	}
}
