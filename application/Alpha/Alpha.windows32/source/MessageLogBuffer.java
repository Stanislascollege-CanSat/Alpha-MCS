import java.util.ArrayList;

public class MessageLogBuffer {
	public static ArrayList<String> serialBuffer = new ArrayList<String>();

	public static void addSerial(String s){
		serialBuffer.add(s);
	}

	public static void clearSerial(){
		serialBuffer.clear();
	}
}
