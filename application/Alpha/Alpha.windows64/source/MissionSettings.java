public class MissionSettings {
	private static String serialPort;
	private static int serialBaudRate;
	private static String outputFolder;
	private static String missionIdentifier;
	private static boolean doCreateConsoleLogFile;
	private static boolean doCreateCSVDataOutputFile;

	private MissionSettings(){
	    
	}
	
	
	//
	// SETTER METHODS
	//
	
	public static void set(String sP, int sB, String oF, String mI, boolean cL, boolean csv) {
		serialPort = sP;
		serialBaudRate = sB;
		outputFolder = oF;
		missionIdentifier = mI;
		doCreateConsoleLogFile = cL;
		doCreateCSVDataOutputFile = csv;
	}
	
	public static void setSerial(String sP, int sB) {
		serialPort = sP;
		serialBaudRate = sB;
	}
	
	public static void setSerialPort(String sP) {
		serialPort = sP;
	}
	
	public static void setSerialBaudRate(int sB) {
		serialBaudRate = sB;
	}
	
	public static void setOutputFolderPath(String oF) {
		outputFolder = oF;
	}
	
	public static void setMissionIdentifier(String mI) {
		missionIdentifier = mI;
	}
	
	public static void setCreateConsoleLogFile(boolean cL) {
		doCreateConsoleLogFile = cL;
	}
	
	public static void setCreateCSVDataOutputFile(boolean csv) {
		doCreateCSVDataOutputFile = csv;
	}
	
	
	
	//
	// GETTER METHODS
	//
	
	public static String getSerialPort() {
		return serialPort;
	}
	
	public static int getSerialBaudRate() {
		return serialBaudRate;
	}
	
	public static String getOutputFolderPath() {
		return outputFolder;
	}
	
	public static String getMissionIdentifier() {
		return missionIdentifier;
	}
	
	public static boolean getCreateConsoleLogFile() {
		return doCreateConsoleLogFile;
	}
	
	public static boolean getCreateCSVDataOutputFile() {
		return doCreateCSVDataOutputFile;
	}
}
