import java.util.Map;
import java.util.HashMap;

public class ActionRequest {
	public static boolean clearConsole = false;
	public static boolean exitAlpha = false;
	
	public static void denyAll() {
		clearConsole = false;
		exitAlpha = false;
	}
	
	public static boolean anyRequestOpen() {
		if(clearConsole || exitAlpha) {
			return true;
		}
		return false;
	}
	
}
