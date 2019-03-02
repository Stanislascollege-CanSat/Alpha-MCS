import java.util.ArrayList;

public class DataPoint {
	private double x;
	private double y;

	public DataPoint(double x, double y){
		this.x = x;
		this.y = y;
	}

	// SETTER METHODS
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	// GETTER METHODS
	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}
}
