import java.util.ArrayList;


public class favorites {

	private static  ArrayList<colorFavorite> favoritesList;
	
	public favorites(){
		favoritesList = new ArrayList<colorFavorite>();
	}
	
	public static void addFavorite(colorFavorite favorite){
		favoritesList.add(favorite);
	}
	
	public static ArrayList<colorFavorite> getArray(){
		return favoritesList;
	}
	public String toString(){
		return favoritesList.toString() ;
		
	}
}
