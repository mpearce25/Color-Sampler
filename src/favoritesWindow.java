import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

public class favoritesWindow {

	private JFrame favoritesWindow;
	private static JToolBar copyToolbar;
	private static JToolBar manageToolbar;
	private static JList<colorFavorite> favoritesList;
	private ArrayList<colorFavorite> array;
	DefaultListModel<colorFavorite> listModel;

	public favoritesWindow(String title, ArrayList<colorFavorite> array) {

		this.array = array;
		initFrame(title);
		initCopyToolbar();
		initManageToolbar();
		initFavoritesList(array);

		favoritesWindow.pack();
		favoritesWindow.setVisible(true);
	}

	private void initFavoritesList(ArrayList<colorFavorite> array) {

		listModel = new DefaultListModel<>();

		for (colorFavorite favorite : array) {
			listModel.addElement(favorite);
		}

		// create the list
		favoritesList = new JList<>(listModel);
		favoritesWindow.add(favoritesList);

	}

	private void initManageToolbar() {
		manageToolbar = new JToolBar("Manage Toolbar");
		manageToolbar.setPreferredSize(new Dimension(400, 35));
		manageToolbar.setFloatable(false);
		manageToolbar.setFocusable(false);

		// //////// Remove selected
		JButton buttonCopyHex = new JButton("Remove Selected");
		buttonCopyHex.setPreferredSize(new Dimension(150, 35));
		buttonCopyHex.setFocusable(false);

		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				
				if(favoritesList.getSelectedIndices().length > 0){
					int[] selectedElements = favoritesList.getSelectedIndices();
					for(int i = selectedElements.length-1; i >= 0; i --){
						listModel.removeElementAt(selectedElements[i]);
						array.remove(selectedElements[i]);
						try {
							Utilities.writeFile("list.txt", array);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				

			}
		});

		manageToolbar.add(buttonCopyHex);
		// toolbar.addSeparator();

		// /////Remove all
		JButton buttonCopyRGB = new JButton("Remove All");
		buttonCopyRGB.setPreferredSize(new Dimension(150, 35));
		buttonCopyRGB.setFocusable(false);

		buttonCopyRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				array.removeAll(array); // /removes all existing favorites array
										// is the array from the lidt.txt file
				try {
					Utilities.writeFile("list.txt", array);

					listModel.removeAllElements();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		manageToolbar.add(buttonCopyRGB);
		favoritesWindow.getContentPane().add(manageToolbar, BorderLayout.SOUTH);

	}

	private void initFrame(String title) {
		favoritesWindow = new JFrame();
		favoritesWindow.setTitle(title);
		favoritesWindow.setLocation(400, 0);
		favoritesWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private void initCopyToolbar() {
		copyToolbar = new JToolBar("Copy Commands");
		copyToolbar.setPreferredSize(new Dimension(400, 35));
		copyToolbar.setFloatable(false);
		copyToolbar.setFocusable(false);

		// ////////Copy Hex button
		JButton buttonCopyHex = new JButton("Copy Hex");
		buttonCopyHex.setPreferredSize(new Dimension(150, 35));
		buttonCopyHex.setFocusable(false);

		buttonCopyHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		copyToolbar.add(buttonCopyHex);
		// toolbar.addSeparator();

		// /////Copy RGB button
		JButton buttonCopyRGB = new JButton("Copy RGB");
		buttonCopyRGB.setPreferredSize(new Dimension(150, 35));
		buttonCopyRGB.setFocusable(false);

		buttonCopyRGB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

			}
		});

		copyToolbar.add(buttonCopyRGB);
		favoritesWindow.getContentPane().add(copyToolbar, BorderLayout.NORTH);
	}
}
