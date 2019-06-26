import java.util.ArrayList;
import java.lang.Math;

public class DataSet {
	private String quantity;
	private String unitX;
	private String unitY;
	private ArrayList<DataPoint> data;

	public DataSet(String q) {
		this.quantity = q;
		this.data = new ArrayList<DataPoint>();
	}

	// SETTER METHODS
	public void setQuantity(String s) {
		this.quantity = s;
	}

	// ADDITIVE METHODS
	public void addDataPoint(DataPoint a){
		this.data.add(a);
	}

	public void addDataPoint(DataPoint a, boolean sort){
		this.data.add(a);
		if(sort){
			this.sort();
		}
	}

	// EDITIVE METHODS
	public void sort(){
		// sort data here
	}


	// GETTER METHODS
	public String getQuantity() {
		return this.quantity;
	}


	public DataPoint getDataAt(int x){
		if(x >= 0 && x < this.data.size()){
			return this.data.get(x);
		}
		return null;
	}

	public DataPoint getClosestTo(double x){
		if(this.data.size() > 0){
			int index = 0;
			double smallest = Math.abs(this.data.get(index).getX() - x);
			for(int i = 0; i < this.data.size(); ++i){
				if(Math.abs(this.data.get(i).getX() - x) < smallest){
					smallest = Math.abs(this.data.get(i).getX() - x);
					index = i;
				}
			}
			if(index >= 0 && index < this.data.size()){
				return this.data.get(index);
			}
		}
		return new DataPoint(0, 0);
	}

	public ArrayList<DataPoint> getData(){
		return this.data;
	}

	// CALCULATORY METHODS
	public DataSet calcDerivative(){
		if(this.data.size() > 1){
			DataSet output = new DataSet(this.quantity + "'");
			double dx = 0;
			double dy = 0;
			for(int i = 0; i < this.data.size() - 2; ++i){
				dx = this.data.get(i+1).getX() - this.data.get(i).getX();
				dy = this.data.get(i+1).getY() - this.data.get(i).getY();
				output.addDataPoint(new DataPoint(this.data.get(i).getX(), dy/dx));
			}
			return output;
		}
		return new DataSet("!NO_DATA[" + this.quantity + "'");
	}


	public int size(){
		return this.data.size();
	}

	public DataPoint getLatestDataPoint(){
		if(this.data.size() > 0){
			return this.data.get(this.data.size() - 1);
		}
		return new DataPoint(0, 0);
	}


}
