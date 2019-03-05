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
		MeasuredDataPoint clone = new MeasuredDataPoint();
		for(String k : this.data.keySet()) {
			clone.addQuantity(k, this.data.get(k));
		}
		return clone;
	}
	
	public Map<String, Double> getMap(){
		return this.data;
	}
}
