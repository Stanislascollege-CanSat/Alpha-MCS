// SerialController.java
// Processing 3.4
// Rens Dur (Project Bèta)
// Alpha MCS software package


import java.util.ArrayList;

import processing.serial.*;

// TASK: improve, this is just sufficient.

public static class SerialController {
	public static Serial serialCom = null;
	public static boolean isOpen = false;
	public static ArrayList<String> received = new ArrayList<String>();
	
	public static String[] getAvailablePorts() {
		return Serial.list();
	}
	
	public static void open(PApplet javaEnvironment, String portName, int baudRate) {
		if(isOpen && !(serialCom == null)) {
			serialCom.stop();
			serialCom = null;
			isOpen = false;
		}
		if(portName.length() > 0 && baudRate >= 300) {
			serialCom = new Serial(javaEnvironment, portName, baudRate);
			isOpen = true;
		}
	}
	
	public static void close() {
		if(isOpen) {
			serialCom.stop();
		}
	}
	
	public static void send(String s) {
		if(isOpen) {
			serialCom.write(s);
		}
	}
	
	public static boolean available() {
		if(isOpen && (serialCom.available() > 0)) {
			received.add(serialCom.readString());
			return true;
		}
		return false;
	}
	
	public static String[] getReceived() {
		if(received.size() > 0) {
			String[] output = new String[received.size()];
			for(int i = 0; i < received.size(); ++i) {
				output[i] = received.get(i);
			}
			received.clear();
			return output;
		}
		return new String[0];
	}
}
