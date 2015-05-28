import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class designUtilities {

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
