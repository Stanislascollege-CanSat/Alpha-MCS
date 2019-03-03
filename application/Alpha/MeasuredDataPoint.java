import java.util.Map;
import java.util.HashMap;

public class MeasuredDataPoint {
	public Map<String, Double> data;
	
	
	public MeasuredDataPoint() {
		this.data = new HashMap<String, Double>();
	}
	
	private MeasuredDataPoint(Map<String, Double> data) {
		this.data = data;
	}
	
	public void addQuantity(String q, double v) {
		this.data.put(q, v);
	}
	
	public void clear() {
		this.data.clear();
	}
	
	public MeasuredDataPoint copy() {
		return new MeasuredDataPoint(new HashMap<String, Double>(this.data));
	}
	
	public Map<String, Double> getMap(){
		return this.data;
	}
}
