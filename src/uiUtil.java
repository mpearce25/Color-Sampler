import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class uiUtil{
	public static void initFrame(JFrame frame, String title, doublePoint location){
		frame.setTitle(title);
		frame.setLocation((int) location.getX(), (int) location.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	///ads location variable
	public static void initFrame(JFrame frame, String title, doublePoint location, Dimension size){
		frame.setTitle(title);
		frame.setLocation((int) location.getX(), (int) location.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(size);
	}
	//////for relative location
	public static void initFrame(JFrame frame, String title, JFrame relativeFrame, Dimension size){
		frame.setTitle(title);
		frame.setLocationRelativeTo(relativeFrame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(size);
	}
	public static void initToolbar(JToolBar toolbar, doublePoint size,  Color backgroundColor){
		toolbar.setPreferredSize(new Dimension(size.getIntX(), size.getIntY()));
		toolbar.setFloatable(false);
		toolbar.setFocusable(false);
		toolbar.setBackground(backgroundColor);
		toolbar.setBorder(new LineBorder(Color.WHITE));
	}
	public static void setMaterialButton(JButton button, Dimension size) {

		Border line = new LineBorder(new Color(160, 160, 160));
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);

		button.setPreferredSize(size);
		button.setFocusable(false);
		button.setBackground(Color.WHITE);
		button.setBorder(compound);
	}
}
