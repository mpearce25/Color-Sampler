import java.awt.AWTException;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorSampler extends JPanel {

	private static ColorSamplerWindow window;

	public ColorSampler() throws AWTException, IOException {
		ColorSamplerWindow window = new ColorSamplerWindow(
				"Color Sampler - Press space to freeze");
	}

	public static void main(String[] a) throws IOException, AWTException {

		window = new ColorSamplerWindow("Color Sampler - Press space to freeze");  ///everything mangaed from here

	}
}
