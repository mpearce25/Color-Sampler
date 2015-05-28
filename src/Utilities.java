import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;

public class Utilities {

	// //Clipboard Tools
	public static void setClipboard(String text) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);
	}

	public static String convertToHex(Color color) {

		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(),
				color.getBlue());
	}

	public static void writeFile(String fileName, ArrayList<colorFavorite> array)
			throws IOException {
		File file = new File(fileName);
		file.delete();		//gets rid of all old files to prevent duplicates
		

		
		BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
		
		for (colorFavorite name : array) {
			outFile.write(name.toString());
			outFile.newLine();
			
		}

		outFile.close();
	}
	
}
