import java.awt.AWTException;
import java.io.IOException;

import javax.swing.JPanel;


public class ColorSampler extends JPanel{

	public ColorSampler () throws AWTException, IOException{
		ColorSamplerWindow window = new ColorSamplerWindow("Color Sampler - Press space to freeze");
		colorSamplerUpdater updater = new colorSamplerUpdater();
	}
	
	public static void main(String[] a) throws IOException, AWTException{
		
		ColorSamplerWindow window = new ColorSamplerWindow("Color Sampler - Press space to freeze");
		colorSamplerUpdater updater = new colorSamplerUpdater();
		
		
	}
}
