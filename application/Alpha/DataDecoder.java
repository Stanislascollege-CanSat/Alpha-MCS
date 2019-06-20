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

	public static boolean askDeployPermissionRequested = false;
	public static boolean notifyBabyCansRequested = false;

	public static void init() {
		availableQuantities.put("GT", "GPSTime");
		availableQuantities.put("TS", "Time since startup");
		availableQuantities.put("GA", "GPS Latitude");
		availableQuantities.put("GO", "GPS Longitude");
		availableQuantities.put("GH", "GPS altitude");
		availableQuantities.put("GS", "GPS speed");
		availableQuantities.put("GV", "GPS angle");
		availableQuantities.put("G3", "GPS 3D-fix");
		availableQuantities.put("GN", "GPS satellites");
		availableQuantities.put("AP", "Air-pressure");
		availableQuantities.put("AT", "Air-temperature");
		availableQuantities.put("AL", "Altitude");
		availableQuantities.put("HM", "Humidity");
		availableQuantities.put("AX", "Acceleration X");
		availableQuantities.put("AY", "Acceleration Y");
		availableQuantities.put("AZ", "Acceleration Z");
		availableQuantities.put("GX", "Gyroscope X");
		availableQuantities.put("GY", "Gyroscope Y");
		availableQuantities.put("GZ", "Gyroscope Z");
		availableQuantities.put("CX", "Compass X");
		availableQuantities.put("CY", "Compass Y");
		availableQuantities.put("CZ", "Compass Z");
		availableQuantities.put("OC", "TVOC");
		availableQuantities.put("O2", "ECO2");
		availableQuantities.put("RS", "Radio RSSI");
		availableQuantities.put("BV", "Battery voltage");
		availableQuantities.put("DS", "Data Synchronization Point");

		availableStatusIdentifiers.put("SBT", "Booted");
		availableStatusIdentifiers.put("SMU", "Flight-mode");
		availableStatusIdentifiers.put("SDP", "Deployed");
		availableStatusIdentifiers.put("SRC", "RadioConnection");

		for(String k : availableStatusIdentifiers.keySet()) {
			babyBetaStatus.put(k, 0);
			babyRhoStatus.put(k, 0);
			motherStatus.put(k, 0);
		}



		initialized = true;
	}

	public static void addData(String s) {
		if(initialized) {
			data += s;
		}
	}

	public static void update() {
    try{
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
							case 3:
								motherMeasuredData.add(buffer.copy());
								break;
							case 4:
								babyBetaMeasuredData.add(buffer.copy());
								break;
							case 5:
								babyRhoMeasuredData.add(buffer.copy());
								break;
						}
						pushDataToDataSetDeposit(buffer);
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
								if(readQuantity.equals("CAN")) {
									// Can selected
									selectedCan = Integer.valueOf(readValue);
								}else if(readQuantity.equals("F")) {
									// Function called
									String readFunction = "";
									String readArg = "";
									boolean readingName = true;
									ArrayList<String> readArgs = new ArrayList<String>();
									for(int j = 0; j < readValue.length(); ++j){
										if(readValue.charAt(j) == ','){
											readingName = false;
											if(readArg.length() > 0){
												readArgs.add(readArg);
											}
											readArg = "";
										}else{
											if(readingName){
												readFunction += readValue.charAt(j);
											}else{
												readArg += readValue.charAt(j);
											}
										}
									}
									if(readArg.length() > 0){
										readArgs.add(readArg);
									}
									if(readFunction.equals("LOG")){
										for(String s : readArgs){
											MessageLogBuffer.addSerial(s);
										}
									}else if(readFunction.equals("ASK")){
										askDeployPermissionRequested = true;
									}
								}else if(readQuantity.charAt(0) == 'S' && readQuantity.length() == 3) {
									// Status update called
									if(!(availableStatusIdentifiers.get(readQuantity) == null)) {
										switch(selectedCan) {
											case 3:
												//MOTHERCAN
												//MessageLogBuffer.addSerial(readQuantity + ": " + readValue);
												if(readQuantity.equals("SBT")) {
													DataSetDeposit.mu_bootState = Integer.valueOf(readValue);
												}else if(readQuantity.equals("SFM")){
													//System.out.println(readValue);
													DataSetDeposit.mu_flightMode = Integer.valueOf(readValue);
												}else if(readQuantity.equals("SDP")){
													if(Integer.valueOf(readValue) == 0){
														DataSetDeposit.mu_babyCansDeployed = false;
													}else{
														DataSetDeposit.mu_babyCansDeployed = true;
														notifyBabyCansRequested = true;
													}
												}else if(readQuantity.equals("SMU")){
													DataSetDeposit.mu_flightMode = Integer.valueOf(readValue);
												}
												break;
											case 4:
												//BETA
												if(readQuantity.equals("SBT")) {
													DataSetDeposit.beta_bootState = Integer.valueOf(readValue);
												}else if(readQuantity.equals("SFM")){
													//System.out.println(readValue);
													DataSetDeposit.beta_flightMode = Integer.valueOf(readValue);
												}
												break;
											case 5:
												//RHO
												if(readQuantity.equals("SBT")) {
													DataSetDeposit.rho_bootState = Integer.valueOf(readValue);
												}else if(readQuantity.equals("SFM")){
													//System.out.println(readValue);
													DataSetDeposit.rho_flightMode = Integer.valueOf(readValue);
												}
												break;
										}
									}
								}else {
									// Standard value pushed
									if(!(availableQuantities.get(readQuantity) == null || availableQuantities.get(readQuantity) == "")) {
										if(!(readQuantity.equals("GT"))) {
											buffer.addQuantity(availableQuantities.get(readQuantity), (readValue.length() > 0 ? Double.valueOf(readValue) : 0));
										}else {
											if(readValue.length() == 6) {
												buffer.addQuantity("GPSTime hour", Double.valueOf(readValue.substring(0, 2)));
												buffer.addQuantity("GPSTime minute", Double.valueOf(readValue.substring(2, 4)));
												buffer.addQuantity("GPSTime second", Double.valueOf(readValue.substring(4, 6)));
											}else {
												buffer.addQuantity("GPSTime hour", 0);
												buffer.addQuantity("GPSTime minute", 0);
												buffer.addQuantity("GPSTime second", 0);

											}
										}
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
    }catch(Exception e){}
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

	private static void pushDataToDataSetDeposit(MeasuredDataPoint p) {
		switch(selectedCan) {
			case 1:
				if(!(p.get("Radio RSSI") == null)){DataSetDeposit.groundStation_RSSI = p.get("Radio RSSI");}
				break;
			case 2:
				if(!(p.get("Radio RSSI") == null)){DataSetDeposit.groundStation_RSSI = p.get("Radio RSSI");}
				break;
			case 3:
				//MOTHERCAN
				if(!(p.get("Time since startup") == null)) {
					p.addQuantity("Time since startup", p.get("Time since startup")/1000.0);
					if(!(p.get("Acceleration X") == null)) {DataSetDeposit.mu_accX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration X")));}
					if(!(p.get("Acceleration Y") == null)) {DataSetDeposit.mu_accY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Y")));}
					if(!(p.get("Acceleration Z") == null)) {DataSetDeposit.mu_accZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Z")));}
					if(!(p.get("Gyroscope X") == null)) {DataSetDeposit.mu_gyroX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope X")));}
					if(!(p.get("Gyroscope Y") == null)) {DataSetDeposit.mu_gyroY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Y")));}
					if(!(p.get("Gyroscope Z") == null)) {DataSetDeposit.mu_gyroZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Z")));}
					if(!(p.get("Compass X") == null)) {DataSetDeposit.mu_compassX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass X")));}
					if(!(p.get("Compass Y") == null)) {DataSetDeposit.mu_compassY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Y")));}
					if(!(p.get("Compass Z") == null)) {DataSetDeposit.mu_compassZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Z")));}
					if(!(p.get("Air-pressure") == null)) {DataSetDeposit.mu_airPres.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-pressure")));}
					if(!(p.get("Air-temperature") == null)) {DataSetDeposit.mu_airTemp.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-temperature")));}
					if(!(p.get("Altitude") == null)) {DataSetDeposit.mu_altitude.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Altitude")));}
					if(!(p.get("Humidity") == null)) {DataSetDeposit.mu_humidity.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Humidity")));}
					if(!(p.get("GPS Latitude") == null)) {DataSetDeposit.mu_GPSLat.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Latitude")));}
					if(!(p.get("GPS Longitude") == null)) {DataSetDeposit.mu_GPSLon.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Longitude")));}
					if(!(p.get("TVOC") == null)) {DataSetDeposit.mu_TVOC.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("TVOC")));}
					if(!(p.get("ECO2") == null)) {DataSetDeposit.mu_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("ECO2")));}
					if(!(p.get("Data Synchronization Point") == null)) {DataSetDeposit.mu_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Data Synchronization Point")));}
					if(!(p.get("GPS 3D-fix") == null)) {DataSetDeposit.mu_GPSFix = (p.get("GPS 3D-fix") > 0 ? true : false);}
					if(!(p.get("GPS satellites") == null)) {DataSetDeposit.mu_GPSSatellites = p.get("GPS satellites").intValue();}
					if(!(p.get("Battery voltage") == null)) {DataSetDeposit.mu_batteryVoltage = p.get("Battery voltage").floatValue();}
					DataSetDeposit.mu_pointsMeasured++;
				}
				break;
			case 4:
				//BETA
				if(!(p.get("Time since startup") == null)) {
					p.addQuantity("Time since startup", p.get("Time since startup")/1000.0);
					if(!(p.get("Acceleration X") == null)) {DataSetDeposit.beta_accX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration X")));}
					if(!(p.get("Acceleration Y") == null)) {DataSetDeposit.beta_accY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Y")));}
					if(!(p.get("Acceleration Z") == null)) {DataSetDeposit.beta_accZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Z")));}
					if(!(p.get("Gyroscope X") == null)) {DataSetDeposit.beta_gyroX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope X")));}
					if(!(p.get("Gyroscope Y") == null)) {DataSetDeposit.beta_gyroY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Y")));}
					if(!(p.get("Gyroscope Z") == null)) {DataSetDeposit.beta_gyroZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Z")));}
					if(!(p.get("Compass X") == null)) {DataSetDeposit.beta_compassX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass X")));}
					if(!(p.get("Compass Y") == null)) {DataSetDeposit.beta_compassY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Y")));}
					if(!(p.get("Compass Z") == null)) {DataSetDeposit.beta_compassZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Z")));}
					if(!(p.get("Air-pressure") == null)) {DataSetDeposit.beta_airPres.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-pressure")));}
					if(!(p.get("Air-temperature") == null)) {DataSetDeposit.beta_airTemp.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-temperature")));}
					if(!(p.get("Altitude") == null)) {DataSetDeposit.beta_altitude.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Altitude")));}
					if(!(p.get("Humidity") == null)) {DataSetDeposit.beta_humidity.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Humidity")));}
					if(!(p.get("GPS Latitude") == null)) {DataSetDeposit.beta_GPSLat.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Latitude")));}
					if(!(p.get("GPS Longitude") == null)) {DataSetDeposit.beta_GPSLon.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Longitude")));}
					if(!(p.get("TVOC") == null)) {DataSetDeposit.beta_TVOC.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("TVOC")));}
					if(!(p.get("ECO2") == null)) {DataSetDeposit.beta_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("ECO2")));}
					if(!(p.get("Data Synchronization Point") == null)) {DataSetDeposit.beta_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Data Synchronization Point")));}
					if(!(p.get("GPS 3D-fix") == null)) {DataSetDeposit.beta_GPSFix = (p.get("GPS 3D-fix") > 0 ? true : false);}
					if(!(p.get("GPS satellites") == null)) {DataSetDeposit.beta_GPSSatellites = p.get("GPS satellites").intValue();}
					if(!(p.get("Battery voltage") == null)) {DataSetDeposit.beta_batteryVoltage = p.get("Battery voltage").floatValue();}
					DataSetDeposit.beta_pointsMeasured++;
				}
				break;
			case 5:
				//RHO
				if(!(p.get("Time since startup") == null)) {
					p.addQuantity("Time since startup", p.get("Time since startup")/1000.0);
					if(!(p.get("Acceleration X") == null)) {DataSetDeposit.rho_accX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration X")));}
					if(!(p.get("Acceleration Y") == null)) {DataSetDeposit.rho_accY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Y")));}
					if(!(p.get("Acceleration Z") == null)) {DataSetDeposit.rho_accZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Acceleration Z")));}
					if(!(p.get("Gyroscope X") == null)) {DataSetDeposit.rho_gyroX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope X")));}
					if(!(p.get("Gyroscope Y") == null)) {DataSetDeposit.rho_gyroY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Y")));}
					if(!(p.get("Gyroscope Z") == null)) {DataSetDeposit.rho_gyroZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Gyroscope Z")));}
					if(!(p.get("Compass X") == null)) {DataSetDeposit.rho_compassX.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass X")));}
					if(!(p.get("Compass Y") == null)) {DataSetDeposit.rho_compassY.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Y")));}
					if(!(p.get("Compass Z") == null)) {DataSetDeposit.rho_compassZ.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Compass Z")));}
					if(!(p.get("Air-pressure") == null)) {DataSetDeposit.rho_airPres.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-pressure")));}
					if(!(p.get("Air-temperature") == null)) {DataSetDeposit.rho_airTemp.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Air-temperature")));}
					if(!(p.get("Altitude") == null)) {DataSetDeposit.rho_altitude.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Altitude")));}
					if(!(p.get("Humidity") == null)) {DataSetDeposit.rho_humidity.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Humidity")));}
					if(!(p.get("GPS Latitude") == null)) {DataSetDeposit.rho_GPSLat.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Latitude")));}
					if(!(p.get("GPS Longitude") == null)) {DataSetDeposit.rho_GPSLon.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("GPS Longitude")));}
					if(!(p.get("TVOC") == null)) {DataSetDeposit.rho_TVOC.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("TVOC")));}
					if(!(p.get("ECO2") == null)) {DataSetDeposit.rho_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("ECO2")));}
					if(!(p.get("Data Synchronization Point") == null)) {DataSetDeposit.rho_ECO2.addDataPoint(new DataPoint(p.get("Time since startup"), p.get("Data Synchronization Point")));}
					if(!(p.get("GPS 3D-fix") == null)) {DataSetDeposit.rho_GPSFix = (p.get("GPS 3D-fix") > 0 ? true : false);}
					if(!(p.get("GPS satellites") == null)) {DataSetDeposit.rho_GPSSatellites = p.get("GPS satellites").intValue();}
					if(!(p.get("Battery voltage") == null)) {DataSetDeposit.rho_batteryVoltage = p.get("Battery voltage").floatValue();}
					DataSetDeposit.rho_pointsMeasured++;
				}
				break;
		}
	}

	//public static DataSet createDataSet(String q) {

	//}
}
