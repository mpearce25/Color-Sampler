import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;


public class colorSamplerUpdater {

	private static doublePoint mouseFreezeLocation;
	private static doublePoint mouseLocation;
	
	private static boolean run = true;
	private static boolean freeze = false;

	
	
	public colorSamplerUpdater() throws AWTException{
		run(run);
		
	}
	
	private static void run(boolean run) throws AWTException{
		Robot bot = new Robot();
		while (run){
		
			
				mouseLocation = screenInfo.getMouseCoordinates();
				ColorSamplerWindow.setColorInfoText("Hex: " + screenInfo.getColorHex() + "\t\t\t\tRGB: " +  screenInfo.getColorRGB());
				ColorSamplerWindow.setColorSampleColor(screenInfo.getColor());
				//bot.delay(500);
			

			
		}
	}

	public static boolean getRun() {
		
		return run;
	}

	public static String getHEX() throws AWTException {
		Robot bot = new Robot();

		return bot.getPixelColor((int)mouseLocation.getX(), (int)mouseLocation.getY()).toString();
	}
	
	public static void switchRun() throws AWTException{
		
		if (run){
			run = false;
			System.out.println("run is false");
			mouseFreezeLocation = screenInfo.getMouseCoordinates();
			
			
			
		}
		else{
			run = true;
			
			System.out.println("Switch2");
		}
	}
	
	public static doublePoint getFreezeValues(){	//returns value of where mouse was when frozen
		return mouseFreezeLocation;
	}
	
	
	
	
}
