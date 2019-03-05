import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DataDecoder {	
	public static String data = "";
	
	public static Map<String, String> availableQuantities = new HashMap<String, String>();
	public static Map<String, String> availableStatusIdentifiers = new HashMap<String, String>();
	
	public static Map<String, Integer> babyBetaStatus = new HashMap<String, Integer>();
	public static Map<String, Integer> babyRhoStatus = new HashMap<String, Integer>();
	public static Map<String, Integer> motherStatus = new HashMap<String, Integer>();
	
	private static boolean reading = false;
	private static boolean readingQuantity = false;
	private static boolean readingValue = false;
	private static int selectedCan = 0;
	
	private static String readQuantity = "";
	private static String readValue = "";
	
	private static MeasuredDataPoint buffer = new MeasuredDataPoint();
	
	private static ArrayList<MeasuredDataPoint> babyBetaMeasuredData = new ArrayList<MeasuredDataPoint>();
	private static ArrayList<MeasuredDataPoint> babyRhoMeasuredData = new ArrayList<MeasuredDataPoint>();
	private static ArrayList<MeasuredDataPoint> motherMeasuredData = new ArrayList<MeasuredDataPoint>();
	
	private static boolean initialized = false;
	
	public static void init() {
		availableQuantities.put("GT", "GPSTime");
		availableQuantities.put("ST", "SystemTime");
		
		availableStatusIdentifiers.put("SBT", "Booted");
		availableStatusIdentifiers.put("SRC", "RadioConnection");
		
		babyBetaStatus.put("Booted", 0);
		babyBetaStatus.put("RadioConnection", 0);
		
		babyRhoStatus.put("Booted", 0);
		babyRhoStatus.put("RadioConnection", 0);
		
		motherStatus.put("Booted", 0);
		motherStatus.put("RadioConnection", 0);
		
		initialized = true;
	}
	
	public static void addData(String s) {
		if(initialized) {
			data += s;
		}
	}
	
	public static void update() {
		if(initialized) {
			for(int a = 0; a < data.length(); ++a) {
				char i = data.charAt(a);
				switch(i) {
					case '{':
						reading = true;
						readingQuantity = true;
						readingValue = false;
						readQuantity = "";
						break;
					case '}':
						reading = false;
						readingQuantity = false;
						readingValue = false;
						switch(selectedCan) {
							case 0:
								motherMeasuredData.add(buffer.copy());
								break;
							case 1:
								babyBetaMeasuredData.add(buffer.copy());
								break;
							case 2:
								babyRhoMeasuredData.add(buffer.copy());
								break;
						}
						buffer.clear();
						break;
					case ':':
						if(reading && readingQuantity) {
							readingQuantity = false;
							readingValue = true;
							readValue = "";
						}
						break;
					case ';':
						if(reading && readingValue) {
							readingValue = false;
							readingQuantity = true;
							if(readQuantity.length() > 0) {
								if(readQuantity == "CAN") {
									// Can selected
									selectedCan = Integer.valueOf(readValue);
								}else if(readQuantity == "F") {
									// Function called
									
								}else if(readQuantity.charAt(0) == 'S' && readQuantity.length() == 3) {
									// Status update called
									if(!(availableStatusIdentifiers.get(readQuantity) == null)) {
										switch(selectedCan) {
											case 0:
												//MOTHERCAN
												motherStatus.put(availableStatusIdentifiers.get(readQuantity), Integer.valueOf(readValue));
												break;
											case 1:
												//BETA
												babyBetaStatus.put(availableStatusIdentifiers.get(readQuantity), Integer.valueOf(readValue));
												break;
											case 2:
												//RHO
												babyRhoStatus.put(availableStatusIdentifiers.get(readQuantity), Integer.valueOf(readValue));
												break;
										}
									}
								}else {
									// Standard value pushed
									if(!(availableQuantities.get(readQuantity) == null)) {
										buffer.addQuantity(availableQuantities.get(readQuantity), Double.valueOf(readValue));
									}
								}
							}
							readQuantity = "";
						}
						break;
					default:
						if(reading) {
							if(readingQuantity) {
								readQuantity += i;
							}else if(readingValue) {
								readValue += i;
							}
						}
						break;
				}
			}
			data = "";
		}
	}
	
	public static ArrayList<MeasuredDataPoint> getDecodedMotherData(){
		return motherMeasuredData;
	}
	
	public static ArrayList<MeasuredDataPoint> getDecodedBetaData(){
		return babyBetaMeasuredData;
	}
	
	public static ArrayList<MeasuredDataPoint> getDecodedRhoData(){
		return babyRhoMeasuredData;
	}
}
