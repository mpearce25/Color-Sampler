import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;


public class screenInfo {

	private static doublePoint tempLocation;
	
	public static doublePoint getMouseCoordinates() {

		double x = MouseInfo.getPointerInfo().getLocation().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY();

		return new doublePoint(x, y);
	}

	public static Color getColor() throws AWTException {

		Robot bot = new Robot();
		doublePoint mouseLocation = getMouseCoordinates();

		return (bot.getPixelColor( (int)mouseLocation.getX(), (int)mouseLocation.getY()));

	}
	
	public static String getColorHex() throws AWTException{
		Color color = getColor();
		
		String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
		return hex;
	}
	
	public static String getColorRGB() throws AWTException{
		Color color = getColor();
		
		return (color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
	}
	
	public static void storeCoordinates(doublePoint location){
		tempLocation.setX(location.getX());
		tempLocation.setY(location.getY());
	}
	
	
}
