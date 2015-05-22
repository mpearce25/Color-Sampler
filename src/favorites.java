import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class favorites {

	private static ArrayList<colorFavorite> favoritesList;

	public favorites() {
		favoritesList = new ArrayList<colorFavorite>();
	}

	
	public void populateArray() throws IOException{
		File f = new File("list.txt");

		if (fileExists("list.txt")) {

			BufferedReader inFile = new BufferedReader(new FileReader(f));
			String fileRead = inFile.readLine();

			clearArray();
			while (fileRead != null) {

				String colorName = fileRead.substring(
						fileRead.indexOf(':') + 1, fileRead.indexOf(','));
				String colorHex = fileRead.substring(
						fileRead.indexOf(':', fileRead.indexOf(',')) + 1,
						fileRead.length() - 1);

				favoritesList.add(new colorFavorite(Color.decode(colorHex),
						colorName));
				fileRead = inFile.readLine();

			}
			f.delete();
			
			inFile.close();}
	}
	public void addFavorite(colorFavorite favorite) throws IOException {
		favoritesList.add(favorite);

		writeList();
	}

	public static ArrayList<colorFavorite> getArray() {
		return favoritesList;
	}

	public static void writeList() throws IOException {
		updateArray();
		Utilities.writeFile("list.txt", favoritesList);
	}

	public static boolean fileExists(String fileName) {

		File f = new File(fileName);

		if (f.exists() && !f.isDirectory()) {
			return true;

		} else
			return false;
	}

	public static void updateArray() throws IOException {

		
		File f = new File("list.txt");

		if (fileExists("list.txt")) {

			BufferedReader inFile = new BufferedReader(new FileReader(f));
			String fileRead = inFile.readLine();

			clearArray();
			while (fileRead != null) {

				String colorName = fileRead.substring(
						fileRead.indexOf(':') + 1, fileRead.indexOf(','));
				String colorHex = fileRead.substring(
						fileRead.indexOf(':', fileRead.indexOf(',')) + 1,
						fileRead.length() - 1);

				favoritesList.add(new colorFavorite(Color.decode(colorHex),
						colorName));
				fileRead = inFile.readLine();

			}
			f.delete();
			
			inFile.close();

		}
	}

	public static void clearArray() {
		favoritesList.removeAll(favoritesList);
	}

	public String toString() {
		return favoritesList.toString();

	}
}
