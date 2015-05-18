import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;


public class colorSamplerUpdater {

	private static doublePoint mouseLocation;
	private static boolean run = true;

	
	
	public colorSamplerUpdater() throws AWTException{
		run(run);
		Action pressed = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PAssed!");
				
			}
			
		};
	}
	
	private static void run(boolean run) throws AWTException{
		Robot bot = new Robot();
		while (run){
		
			mouseLocation = screenInfo.getMouseCoordinates();
			ColorSamplerWindow.setColorInfoText("Hex: " + screenInfo.getColorHex() + "\t\t\t\tRGB: " +  screenInfo.getColorRGB());
			ColorSamplerWindow.setColorSampleColor(screenInfo.getColor());

			
		}
	}

	public static String getHEX() throws AWTException {
		Robot bot = new Robot();

		return bot.getPixelColor((int)mouseLocation.getX(), (int)mouseLocation.getY()).toString();
	}
	
	
}
