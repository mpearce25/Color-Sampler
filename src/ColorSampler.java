import java.awt.AWTException;
import java.io.IOException;

import javax.swing.*;;

@SuppressWarnings("serial")
public class ColorSampler extends JPanel {

	private static ColorSamplerWindow window;

	public ColorSampler() throws AWTException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		ColorSamplerWindow window = new ColorSamplerWindow(
				"Color Sampler - Press space to freeze", new doublePoint(0,0));
	}

	public static void main(String[] a) throws IOException, AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		window = new ColorSamplerWindow("Color Sampler - Press space to freeze", new doublePoint(0,0) );  ///everything mangaed from here

	}
}
