import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class designUtilities {

	public static void setMaterialButton(JButton button, Dimension size){
		
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		
		
		button.setPreferredSize(size);
		button.setFocusable(false);
		button.setBackground(Color.WHITE);
		button.setBorder(compound);
	}
}
