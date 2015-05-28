public class doublePoint {

	private Double x, y;

	public doublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getIntX() {
		return x.intValue();
	}

	public int getIntY() {
		return y.intValue();
	}

	////set methods
	public void setPoints(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setPoints(doublePoint point){
		this.x = point.getX();
		this.y = point.getY();
	}
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
}
