import java.awt.Color;


public class colorFavorite {

	private Color color;
	private String name;
	
	public colorFavorite(Color color, String name){
		this.color = color;
		this.name = name;
	}
	public String toString(){
	
		return "Name:" + name + "," + "Hex Code:" + Utilities.convertToHex(color) ;
		
	}
}
