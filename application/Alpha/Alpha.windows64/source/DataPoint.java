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
		this.x = y;
	}

	// GETTER METHODS
	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

  public float getXFloat(){
    return (float)this.x;
  }

  public float getYFloat(){
    return (float)this.y;
  }

  public String stringify(){
    return "(" + Double.toString(this.x) + ", " + Double.toString(this.y) + ")";
  }
}
