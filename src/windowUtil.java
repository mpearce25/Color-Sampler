import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

public class windowUtil{
	public static void initFrame(JFrame frame, String title, doublePoint location){
		frame.setTitle(title);
		frame.setLocation((int) location.getX(), (int) location.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void initToolbar(JToolBar toolbar, doublePoint size, Color backgroundColor){
		toolbar.setPreferredSize(new Dimension(size.getIntX(), size.getIntY()));
		toolbar.setFloatable(false);
		toolbar.setFocusable(false);
		toolbar.setBackground(backgroundColor);
		toolbar.setBorder(new LineBorder(Color.WHITE));
	}
}
