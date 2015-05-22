import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private static JList favoritesList;
	
	
	public favoritesWindow(String title, ArrayList<colorFavorite> array){
		
		initFrame(title);
		initCopyToolbar();
		initManageToolbar();
		initFavoritesList(array);
		
		
		favoritesWindow.pack();
		favoritesWindow.setVisible(true);
	}
	private void initFavoritesList(ArrayList<colorFavorite> array) {
		favoritesList = new JList();//data has type Object[]
		favoritesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		favoritesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		favoritesList.setVisibleRowCount(-1);
		
		
		JScrollPane listScroller = new JScrollPane(favoritesList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		//listScroller.addElement("hey");
		
		favoritesWindow.getContentPane().add(favoritesList, BorderLayout.CENTER);
		
	}
	private void initManageToolbar() {
	manageToolbar = new JToolBar("Manage Toolbar");
	manageToolbar.setPreferredSize(new Dimension(400,35));
	manageToolbar.setFloatable(false);
	manageToolbar.setFocusable(false);
	
	// //////// Remove one
	JButton buttonCopyHex = new JButton("Remove Selected");
	buttonCopyHex.setPreferredSize(new Dimension(150, 35));
	buttonCopyHex.setFocusable(false);

	buttonCopyHex.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			
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
			
		}
	});

	manageToolbar.add(buttonCopyRGB);
	favoritesWindow.getContentPane().add(manageToolbar, BorderLayout.SOUTH);
	
		
	}
	private void initFrame(String title){
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
