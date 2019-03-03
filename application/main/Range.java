public class Range {
	public float min;
	public float max;
	
	public Range(float min, float max) {
		this.min = min;
		this.max = max;
	}
	
	public static Range create(float min, float max) {
		return new Range(min, max);
	}
	
	public void set(float min, float max) {
		this.min = min;
		this.max = max;
	}
	
	public Range copy() {
		return new Range(this.min, this.max);
	}
	
	
	
	 
}