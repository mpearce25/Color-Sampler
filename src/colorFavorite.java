import java.awt.Color;

public class colorFavorite {

	private Color color;
	private String name;

	public colorFavorite(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public String getName() {
		return name;

	}

	public Color getColor() {
		return color;
	}

	public String getHex() {
		return Utilities.convertToHex(color);
	}

	public String getRGB() {
		return color.getRed() + ", " + color.getGreen() + ", "
				+ color.getBlue();
	}

	public String toString() {

		return "Name:" + name + "," + "Hex Code:"
				+ Utilities.convertToHex(color);

	}
}
