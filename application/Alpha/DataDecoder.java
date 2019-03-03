import java.util.ArrayList;

public class DataDecoder {
	public static String data = "";
	
	private static boolean reading = false;
	private static boolean readingQuantity = false;
	private static boolean readingValue = false;
	
	private static String readQuantity = "";
	private static String readValue = "";
	
	private static MeasuredDataPoint buffer = new MeasuredDataPoint();
	
	private static ArrayList<MeasuredDataPoint> measuredData = new ArrayList<MeasuredDataPoint>();
	
	public static void addData(String s) {
		data += s;
	}
	
	public static void update() {
		for(int a = 0; a < data.length(); ++a) {
      char i = data.charAt(a);
			if(i == '{') {
				reading = true;
				readingQuantity = true;
			}else if(i == '}') {
				reading = false;
				readingQuantity = false;
				readingValue = false;
				measuredData.add(buffer.copy());
				buffer.clear();
			}else if(i == ':') {
				readingQuantity = false;
				readingValue = true;
			}else if(i == ';') {
				readingQuantity = true;
				readingValue = false;
				buffer.addQuantity(readQuantity, Double.valueOf(readValue));		
				readQuantity = "";
				readValue = "";		
			}else {
				if(reading) {
					if(readingQuantity) {
						readQuantity += i;
					}else if(readingValue) {
						readValue += i;
					}
				}
			}
		}
		data = "";
	}
	
	public static ArrayList<MeasuredDataPoint> getDecodedData(){
		return measuredData;
	}
}
