import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Map; 
import java.util.Calendar; 
import java.util.Date; 
import processing.opengl.PGL; 
import processing.opengl.PJOGL; 
import static javax.swing.JOptionPane.*; 
import java.io.FileWriter; 
import java.lang.Math; 
import java.math.BigDecimal; 
import java.util.*; 
import java.util.ArrayList; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Alpha extends PApplet {

// main.pde
// Processing 3.4
// Rens Dur (Project Bèta)







public PImage appIcon;
public PImage MOUSEPOINTER_arrow;
public PImage MOUSEPOINTER_text;
public String CURRENT_MOUSEPOINTER;
public boolean SET_MOUSEPOINTER_TEXT;



public boolean completedStartup;

// Frame components
public float w, h;
public int minWidth, minHeight;


// Fonts
public Map<String, HashMap<String, PFont>> fonts;

// Conditions
public boolean ALT_PRESSED;
public boolean CTRL_PRESSED;

// Application components
public AppController appController;
//StartupView startupView;

 //PImage mouse_pointer_img;


public Calendar CALENDAR;
public String[] DAYS;

public void settings(){
  //size(1000, 700, P3D);
  fullScreen(P3D);
  pixelDensity(displayDensity());
  if(displayDensity() < 2) {
	  smooth(8);
  }
  PJOGL.setIcon("1024x1024.png");
}

public void setup(){
  surface.setTitle("Alpha");
  surface.setResizable(true);
//  appIcon = loadImage("icon1000.png");
//  surface.setIcon(appIcon);
  background(200);
  
  MOUSEPOINTER_arrow = loadImage("arrow.png");
  MOUSEPOINTER_arrow.resize(25, 25);
  
  MOUSEPOINTER_text = loadImage("text.png");
  MOUSEPOINTER_text.resize(12, 20);
  
  CURRENT_MOUSEPOINTER = "";
  
  SET_MOUSEPOINTER_TEXT = false;

  // Frame components
  w = width;
  h = height;
  minWidth = 800;
  minHeight = 700;

  // Cursor settings
   //mouse_pointer_img = loadImage("MousePointer.png");
   //cursor(mouse_pointer_img, 2, 13);

  // Load fonts
  fonts = new HashMap<String, HashMap<String, PFont>>();
  fonts.put("Lora", new HashMap<String, PFont>());
  fonts.get("Lora").put("Regular",      createFont("data/fonts/Lora/Regular.ttf",      15));
  fonts.get("Lora").put("Bold",         createFont("data/fonts/Lora/Bold.ttf",         15));
  fonts.get("Lora").put("Italic",       createFont("data/fonts/Lora/Italic.ttf",       15));

  fonts.put("OverpassMono", new HashMap<String, PFont>());
  fonts.get("OverpassMono").put("Regular",      createFont("data/fonts/OverpassMono/Regular.ttf",      15));
  fonts.get("OverpassMono").put("Bold",         createFont("data/fonts/OverpassMono/Bold.ttf",         15));
  fonts.get("OverpassMono").put("Light",       createFont("data/fonts/OverpassMono/Light.ttf",         15));

  fonts.put("PlexMono", new HashMap<String, PFont>());
  fonts.get("PlexMono").put("Regular",      createFont("data/fonts/PlexMono/Regular.ttf",      15));
  fonts.get("PlexMono").put("Bold",         createFont("data/fonts/PlexMono/Bold.ttf",         15));
  fonts.get("PlexMono").put("Italic",       createFont("data/fonts/PlexMono/Italic.ttf",       15));
  fonts.get("PlexMono").put("Light",       createFont("data/fonts/PlexMono/Light.ttf",         15));

  fonts.put("SF", new HashMap<String, PFont>());
  fonts.get("SF").put("Black",      createFont("data/fonts/SF/Black.otf",      8));
  fonts.get("SF").put("Bold",         createFont("data/fonts/SF/Bold.otf",     8));
  fonts.get("SF").put("Heavy",       createFont("data/fonts/SF/Heavy.otf",     8));
  fonts.get("SF").put("Light",       createFont("data/fonts/SF/Light.otf",     8));
  fonts.get("SF").put("Medium",       createFont("data/fonts/SF/Medium.otf",   8));
  fonts.get("SF").put("Regular",       createFont("data/fonts/SF/Regular.otf", 8));
  fonts.get("SF").put("Thin",       createFont("data/fonts/SF/Thin.otf",       8));

  fonts.get("SF").put("Black 6",      createFont("data/fonts/SF/Black.otf",      6));
  fonts.get("SF").put("Bold 6",         createFont("data/fonts/SF/Bold.otf",     6));
  fonts.get("SF").put("Heavy 6",       createFont("data/fonts/SF/Heavy.otf",     6));
  fonts.get("SF").put("Light 6",       createFont("data/fonts/SF/Light.otf",     6));
  fonts.get("SF").put("Medium 6",       createFont("data/fonts/SF/Medium.otf",   6));
  fonts.get("SF").put("Regular 6",       createFont("data/fonts/SF/Regular.otf", 6));
  fonts.get("SF").put("Thin 6",       createFont("data/fonts/SF/Thin.otf",       6));

  fonts.get("SF").put("Black 15",      createFont("data/fonts/SF/Black.otf",      15));
  fonts.get("SF").put("Bold 15",         createFont("data/fonts/SF/Bold.otf",     15));
  fonts.get("SF").put("Heavy 15",       createFont("data/fonts/SF/Heavy.otf",     15));
  fonts.get("SF").put("Light 15",       createFont("data/fonts/SF/Light.otf",     15));
  fonts.get("SF").put("Medium 15",       createFont("data/fonts/SF/Medium.otf",   15));
  fonts.get("SF").put("Regular 15",       createFont("data/fonts/SF/Regular.otf", 15));
  fonts.get("SF").put("Thin 15",       createFont("data/fonts/SF/Thin.otf",       15));

  fonts.get("SF").put("Black 20",      createFont("data/fonts/SF/Black.otf",      20));
  fonts.get("SF").put("Bold 20",         createFont("data/fonts/SF/Bold.otf",     20));
  fonts.get("SF").put("Heavy 20",       createFont("data/fonts/SF/Heavy.otf",     20));
  fonts.get("SF").put("Light 20",       createFont("data/fonts/SF/Light.otf",     20));
  fonts.get("SF").put("Medium 20",       createFont("data/fonts/SF/Medium.otf",   20));
  fonts.get("SF").put("Regular 20",       createFont("data/fonts/SF/Regular.otf", 20));
  fonts.get("SF").put("Thin 20",       createFont("data/fonts/SF/Thin.otf",       20));
  
  CALENDAR = Calendar.getInstance();
  CALENDAR.setTime(new Date());
  
  DAYS = new String[7];
  DAYS[0] = "Monday";
  DAYS[1] = "Tuesday";
  DAYS[2] = "Wednesday";
  DAYS[3] = "Thursday";
  DAYS[4] = "Friday";
  DAYS[5] = "Saturday";
  DAYS[6] = "Sunday";


  // Application components
  appController = new AppController(this);

  //println(MAX_INT);

}

public final void setMousePointerToARROW(){
  if(!(CURRENT_MOUSEPOINTER.equals("ARROW"))){
    cursor(MOUSEPOINTER_arrow, 1, 1);
    CURRENT_MOUSEPOINTER = "ARROW";
  }
}

public final void setMousePointerToTEXT(){
  if(!(CURRENT_MOUSEPOINTER.equals("TEXT"))){
    // cursor(MOUSEPOINTER_text, 6, 10);
    CURRENT_MOUSEPOINTER = "TEXT";
    cursor(TEXT);
  }
}

public void draw(){
  if(!completedStartup){
    //showMessageDialog(null, "This application is still being developed. Some functions might not work.", "Work in progress", WARNING_MESSAGE);
    setMousePointerToARROW();
    completedStartup = true;
  }else{

  if(w != width || h != height){
    if(width < minWidth){
      surface.setSize(minWidth, height);
    }
    if(height < minHeight){
      surface.setSize(width, minHeight);
    }

    // window resized
    appController.resize();
  }

  background(255);

  appController.show();

  w = width;
  h = height;

  }
}

public void mousePressed(){
  appController.mousePressed();
}

public void mouseReleased(){
  appController.mouseReleased();
}

public void mouseWheel(MouseEvent event){
  appController.mouseWheel(PApplet.parseFloat(event.getCount()));
}

public void keyPressed(){
  if(keyCode == ALT){
    ALT_PRESSED = true;
  }else if(keyCode == CONTROL){
    CTRL_PRESSED = true;
  }
  appController.keyPressed(key, keyCode);
}

public void keyTyped(){
  appController.keyTyped(key);

  if(key == 's' && ALT_PRESSED && CTRL_PRESSED){
    save("screen-shot.png");
  }
}

public void keyReleased(){
  ALT_PRESSED = false;
  CTRL_PRESSED = false;
  appController.keyReleased();
}
public class SpaceSegment {
  public PVector begin;
  public PVector pointer;
  public PVector temp;
  public float length;
  
  public SpaceSegment(PVector a, PVector b){
    this.begin = a;
    this.pointer = b;
    this.temp = new PVector(0, 0);
    this.length = this.pointer.mag();
  }
  
  public void show(){
    line(this.begin.x, this.begin.y, this.begin.x + this.pointer.x, this.begin.y + this.pointer.y);
  }
  
  public void follow(float x, float y){
    this.temp.set(x - this.begin.x, y - this.begin.y);
    this.pointer.rotate(this.temp.heading() - this.pointer.heading());
    this.begin.x = x - this.pointer.x;
    this.begin.y = y - this.pointer.y;
  }
}

public class SpaceImageSegment extends SpaceSegment {
  Parachute p;
  
  public SpaceImageSegment(PVector a){
    super(a, new PVector(2, 2));
    this.p = new Parachute(0, 0, 0, 100, 100, 50, 30, 60, 5, color(0), color(244, 161, 66));
  }
  
  public void show(){
    translate(this.begin.x, this.begin.y, 0);
    rotate(this.pointer.heading());
    
    stroke(0);
    strokeWeight(1);
    fill(255, 0, 0, 150);
    rectMode(CENTER);
    //rect(-15, 0, 30, 15);
    
    //textAlign(RIGHT);
    //textFont(fonts.get("SF").get("Heavy"));
    //text("Project Bèta", 0, 4);
    
    rotate(-PI/2);
    
    this.p.show();
    
    rotate(PI/2);
    
    rotate(-this.pointer.heading());
    translate(-this.begin.x, -this.begin.y);
  }
}

public class SpaceAnimation {
  public ArrayList<SpaceSegment> points;
  
  public SpaceAnimation(){
    this.points = new ArrayList<SpaceSegment>();
    this.points.add(new SpaceSegment(new PVector(0,0), new PVector(3,3)));
    for(int i = 0; i < 50; ++i){
      this.points.add(new SpaceSegment(new PVector(-i * 10, -i * 10), new PVector(3, 3)));
    }
    this.points.add(new SpaceImageSegment(new PVector(0, 0)));
  }
  
  public void show(){
    if(this.points.size() > 0){
      this.points.get(0).follow(mouseX, mouseY);
    }
    for(int i = 1; i < this.points.size(); ++i){
      this.points.get(i).follow(this.points.get(i-1).begin.x, this.points.get(i-1).begin.y);
    }
    
    stroke(0, 50);
    strokeWeight(2);
    for(SpaceSegment s : this.points){
      s.show();
    }
  }
}
// AppController.pde
// Processing 3.4
// Rens Dur (Project Bèta)




public interface AppController_Interface {
  public void show();

  public void addView(ViewController v);
  public void exitApplication();
  public void resize();
  public void mousePressed();
  public void mouseReleased();
  public void keyPressed(char k, int c);
  public void keyTyped(char k);
  public void keyReleased();

  public void displaySetupScheme();
}

public class AppController implements AppController_Interface {
  public PApplet mainJavaEnvironment;

  private ArrayList<ViewController> viewControllers;


  private StartupView startupView;
  private SetupView setupView;

  // ------- MISSION VIEWS --------- //
  private ViewSelectorView viewSelectorView;

  private View_MissionStart view_MissionStart;
  private View_MissionInfo view_MissionInfo;
  private View_FlightPath view_FlightPath;
  private View_DataCharts view_DataCharts;
  private View_BabyCanInfo view_BabyCanInfo;
  private View_MotherCanInfo view_MotherCanInfo;
  private View_ForceDeploy view_forceDeploy;
  private View_ControlButtons view_controlButtons;
  private View_UniversalText view_universalText;

  private ConsoleView overviewConsoleView;

  private FileWriter serial_receive_file;
  private boolean serial_receive_file_opened;

  public AppController(PApplet environment) {


    this.mainJavaEnvironment = environment;

    this.viewControllers = new ArrayList<ViewController>();

    this.startupView = new StartupView(this, 0, 0, width, height);

    this.setupView = new SetupView(this, 0, 0, width, height);
    this.setupView.visible = false;

    this.viewSelectorView = new ViewSelectorView(this, 0, 0, width, 80);
    this.viewSelectorView.visible = false;

    this.view_MissionStart = new View_MissionStart(this, 0, 80, width, height - 80);
    this.view_MissionStart.visible = false;

    this.view_MissionInfo = new View_MissionInfo(this, 0, 80, width, height - 80);
    this.view_MissionInfo.visible = false;

    this.view_FlightPath = new View_FlightPath(this, 0, 80, width, height - 80);
    this.view_FlightPath.visible = false;

    this.view_DataCharts = new View_DataCharts(this, 0, 80, width, height - 80);
    this.view_DataCharts.visible = false;

    this.view_BabyCanInfo = new View_BabyCanInfo(this, 0, 80, width, height - 80);
    this.view_BabyCanInfo.visible = false;

    this.view_MotherCanInfo = new View_MotherCanInfo(this, 0, 80, width, height - 80);
    this.view_MotherCanInfo.visible = false;

    this.view_forceDeploy = new View_ForceDeploy(this, 0, 80, width, height - 80);
    this.view_forceDeploy.visible = false;

    this.view_controlButtons = new View_ControlButtons(this, 0, 80, width, height - 80);
    this.view_controlButtons.visible = false;

    this.view_universalText = new View_UniversalText(this, 0, 80, width, height - 80);
    this.view_universalText.visible = false;

    this.overviewConsoleView = new ConsoleView(this, 0, 80, 500, height - 80);
    this.overviewConsoleView.visible = false;

    this.viewControllers.add(this.startupView);
    this.viewControllers.add(this.setupView);
    this.viewControllers.add(this.viewSelectorView);
    this.viewControllers.add(this.view_MissionStart);
    this.viewControllers.add(this.view_MissionInfo);
    this.viewControllers.add(this.view_FlightPath);
    this.viewControllers.add(this.view_DataCharts);
    this.viewControllers.add(this.view_BabyCanInfo);
    this.viewControllers.add(this.view_MotherCanInfo);
    this.viewControllers.add(this.view_forceDeploy);
    this.viewControllers.add(this.view_controlButtons);
    this.viewControllers.add(this.view_universalText);
    this.viewControllers.add(this.overviewConsoleView);

    DataDecoder.init();
  }

  public void show() {
    SET_MOUSEPOINTER_TEXT = false;
    for (ViewController v : this.viewControllers) {
      if (v.visible) {
        v.show();
      }
    }
    if (SET_MOUSEPOINTER_TEXT) {
      setMousePointerToTEXT();
    } else {
      setMousePointerToARROW();
    }

    if (SerialController.available()) {
      for (String s : SerialController.getReceived()) {
        this.overviewConsoleView.logSerial(s);
        DataDecoder.addData(s);
        if (this.serial_receive_file_opened) {
          try {	
            this.serial_receive_file.write(s);
          }
          catch(IOException e) {
            //doewatleuks.
          }
          catch(Exception e) {
            // doe wat leuks.
          }
        }
      }
    }

    DataDecoder.update();
    if(MessageLogBuffer.serialBuffer.size() > 0){
    	for(String s : MessageLogBuffer.serialBuffer){
    		this.overviewConsoleView.logSerial(s);
    	}
    	MessageLogBuffer.clearSerial();
    }

    if(DataDecoder.askDeployPermissionRequested){
      this.askDeployPermission();
      DataDecoder.askDeployPermissionRequested = false;
    }
    if(DataDecoder.notifyBabyCansRequested){
      this.notifyBabyCansDeployed();
      DataDecoder.notifyBabyCansRequested = false;
    }

    if (frameRate < 45) {
      strokeWeight(1);
      fill(0);
      textAlign(LEFT);
      textFont(fonts.get("SF").get("Regular"));
      text(PApplet.parseInt(frameRate), 10, 20);
    }
  }

  public void resize() {
    this.startupView.resize(0, 0, width, height);
    this.setupView.resize(0, 0, width, height);
    this.viewSelectorView.resize(0, 0, width, 80);
    this.view_MissionStart.resize(0, 80, width, height - 80);
    this.view_MissionInfo.resize(0, 80, width, height - 80);
    this.view_FlightPath.resize(0, 80, width, height - 80);
    if (this.viewSelectorView.currentViewIdentifier.equals("overview")) {
      this.overviewConsoleView.resize(0, 80, 500, height - 80);
    } else if (this.viewSelectorView.currentViewIdentifier.equals("console")) {
      this.overviewConsoleView.resize(0, 80, width, height - 80);
    }
    this.view_DataCharts.resize(0, 80, width, height - 80);
    this.view_BabyCanInfo.resize(0, 80, width, height - 80);
    this.view_MotherCanInfo.resize(0, 80, width, height - 80);
    this.view_forceDeploy.resize(0, 80, width, height - 80);
    this.view_controlButtons.resize(0, 80, width, height - 80);
    this.view_universalText.resize(0, 80, width, height - 80);
  }

  public void addView(ViewController v) {
    this.viewControllers.add(v);
  }

  public void exitApplication() {
    SerialController.close();
    if (this.serial_receive_file_opened) {
      try {
        this.serial_receive_file.close();
      }
      catch(IOException e) {
        //doewatleuks
      }
      catch(Exception e) {
        //doewatleuks
      }
    }

    try {
      FileWriter decoded_data_mother = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_MotherCan.txt");
      decoded_data_mother.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
      decoded_data_mother.write("--> Measured " + DataDecoder.getDecodedMotherData().size() + " data points.\n");
      for (MeasuredDataPoint p : DataDecoder.getDecodedMotherData()) {
        decoded_data_mother.write("\n\n--\n\n");
        for (String k : p.getMap().keySet()) {
          decoded_data_mother.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
        }
        println("\n\n");
      }
      decoded_data_mother.close();

      FileWriter decoded_data_beta = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_BetaCan.txt");
      decoded_data_beta.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
      decoded_data_beta.write("--> Measured " + DataDecoder.getDecodedBetaData().size() + " data points.\n");
      for (MeasuredDataPoint p : DataDecoder.getDecodedBetaData()) {
        decoded_data_beta.write("\n\n--\n\n");
        for (String k : p.getMap().keySet()) {
          decoded_data_beta.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
        }
      }
      decoded_data_beta.close();

      FileWriter decoded_data_rho = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_RhoCan.txt");
      decoded_data_rho.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
      decoded_data_rho.write("--> Measured " + DataDecoder.getDecodedRhoData().size() + " data points.\n");
      for (MeasuredDataPoint p : DataDecoder.getDecodedRhoData()) {
        decoded_data_rho.write("\n\n--\n\n");
        for (String k : p.getMap().keySet()) {
          decoded_data_rho.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
        }
      }
      decoded_data_rho.close();

      if(MissionSettings.getCreateCSVDataOutputFile()){
        FileWriter dataoutput_csv = new FileWriter(MissionSettings.getOutputFolderPath() + "/MissionData.csv");
        dataoutput_csv.write("PB Mission Data Output File;\n@MISSION: " + MissionSettings.getMissionIdentifier() + ";\n.\n.\n");
        dataoutput_csv.write("----- ----- ----- BETA DATA ----- ----- -----;\n");
        dataoutput_csv.write("Point nr;");
        for(int i = 0; i < DataSetDeposit.beta_accX.getData().size(); ++i){
          dataoutput_csv.write(str(i) + ";");
        }
        dataoutput_csv.write("\nTime (s);");
        for(DataPoint p : DataSetDeposit.beta_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getX()) + ";");
        }
        dataoutput_csv.write("\nAcceleration X (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_accY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_accZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope X (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_gyroX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_gyroY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_gyroZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass X (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_compassX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_compassY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.beta_compassZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir pressure (Pa);");
        for(DataPoint p : DataSetDeposit.beta_airPres.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir temperature (degC);");
        for(DataPoint p : DataSetDeposit.beta_airTemp.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAltitude (m)[corrected];");
        for(DataPoint p : DataSetDeposit.beta_altitude.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nHumidity (%);");
        for(DataPoint p : DataSetDeposit.beta_humidity.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-latitude (deg);");
        for(DataPoint p : DataSetDeposit.beta_GPSLat.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-longitude (deg);");
        for(DataPoint p : DataSetDeposit.beta_GPSLon.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nTVOC (ppm);");
        for(DataPoint p : DataSetDeposit.beta_TVOC.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nECO2 (ppm);");
        for(DataPoint p : DataSetDeposit.beta_ECO2.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }

        dataoutput_csv.write("\nBattery voltage (volt);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedBetaData()){
          if(p.get("Battery voltage") != null){
            dataoutput_csv.write(Double.toString(p.get("Battery voltage")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS fix (1/0);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedBetaData()){
          if(p.get("GPS 3D-fix") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS 3D-fix")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS satellites (int);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedBetaData()){
          if(p.get("GPS satellites") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS satellites")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS timestring;");
        for(MeasuredDataPoint p : DataDecoder.getDecodedBetaData()){
          if(p.get("GPSTime hour") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime hour")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime minute") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime minute")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime second") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime second")));
          }else{
            dataoutput_csv.write("--");
          }
          dataoutput_csv.write(";");
        }


        dataoutput_csv.write("\n.\n.\n----- ----- ----- RHO DATA ----- ----- -----;\n");
        dataoutput_csv.write("Point nr;");
        for(int i = 0; i < DataSetDeposit.rho_accX.getData().size(); ++i){
          dataoutput_csv.write(str(i) + ";");
        }
        dataoutput_csv.write("\nTime (s);");
        for(DataPoint p : DataSetDeposit.rho_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getX()) + ";");
        }
        dataoutput_csv.write("\nAcceleration X (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_accY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_accZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope X (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_gyroX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_gyroY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_gyroZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass X (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_compassX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_compassY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.rho_compassZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir pressure (Pa);");
        for(DataPoint p : DataSetDeposit.rho_airPres.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir temperature (degC);");
        for(DataPoint p : DataSetDeposit.rho_airTemp.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAltitude (m)[corrected];");
        for(DataPoint p : DataSetDeposit.rho_altitude.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nHumidity (%);");
        for(DataPoint p : DataSetDeposit.rho_humidity.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-latitude (deg);");
        for(DataPoint p : DataSetDeposit.rho_GPSLat.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-longitude (deg);");
        for(DataPoint p : DataSetDeposit.rho_GPSLon.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nTVOC (ppm);");
        for(DataPoint p : DataSetDeposit.rho_TVOC.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nECO2 (ppm);");
        for(DataPoint p : DataSetDeposit.rho_ECO2.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }

        dataoutput_csv.write("\nBattery voltage (volt);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedRhoData()){
          if(p.get("Battery voltage") != null){
            dataoutput_csv.write(Double.toString(p.get("Battery voltage")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS fix (1/0);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedRhoData()){
          if(p.get("GPS 3D-fix") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS 3D-fix")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS satellites (int);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedRhoData()){
          if(p.get("GPS satellites") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS satellites")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS timestring;");
        for(MeasuredDataPoint p : DataDecoder.getDecodedRhoData()){
          if(p.get("GPSTime hour") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime hour")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime minute") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime minute")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime second") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime second")));
          }else{
            dataoutput_csv.write("--");
          }
          dataoutput_csv.write(";");
        }


        dataoutput_csv.write("\n.\n.\n----- ----- ----- MU DATA ----- ----- -----;\n");
        dataoutput_csv.write("Point nr;");
        for(int i = 0; i < DataSetDeposit.mu_accX.getData().size(); ++i){
          dataoutput_csv.write(str(i) + ";");
        }
        dataoutput_csv.write("\nTime (s);");
        for(DataPoint p : DataSetDeposit.mu_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getX()) + ";");
        }
        dataoutput_csv.write("\nAcceleration X (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_accX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_accY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAcceleration Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_accZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope X (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_gyroX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_gyroY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGyroscope Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_gyroZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass X (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_compassX.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Y (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_compassY.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nCompass Z (m/s/s);");
        for(DataPoint p : DataSetDeposit.mu_compassZ.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir pressure (Pa);");
        for(DataPoint p : DataSetDeposit.mu_airPres.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAir temperature (degC);");
        for(DataPoint p : DataSetDeposit.mu_airTemp.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nAltitude (m)[corrected];");
        for(DataPoint p : DataSetDeposit.mu_altitude.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nHumidity (%);");
        for(DataPoint p : DataSetDeposit.mu_humidity.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-latitude (deg);");
        for(DataPoint p : DataSetDeposit.mu_GPSLat.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nGPS-longitude (deg);");
        for(DataPoint p : DataSetDeposit.mu_GPSLon.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nTVOC (ppm);");
        for(DataPoint p : DataSetDeposit.mu_TVOC.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }
        dataoutput_csv.write("\nECO2 (ppm);");
        for(DataPoint p : DataSetDeposit.mu_ECO2.getData()){
          dataoutput_csv.write(Double.toString(p.getY()) + ";");
        }

        dataoutput_csv.write("\nBattery voltage (volt);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedMotherData()){
          if(p.get("Battery voltage") != null){
            dataoutput_csv.write(Double.toString(p.get("Battery voltage")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS fix (1/0);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedMotherData()){
          if(p.get("GPS 3D-fix") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS 3D-fix")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS satellites (int);");
        for(MeasuredDataPoint p : DataDecoder.getDecodedMotherData()){
          if(p.get("GPS satellites") != null){
            dataoutput_csv.write(Double.toString(p.get("GPS satellites")) + ";");
          }else{
            dataoutput_csv.write(";");
          }
        }
        dataoutput_csv.write("\nGPS timestring;");
        for(MeasuredDataPoint p : DataDecoder.getDecodedMotherData()){
          if(p.get("GPSTime hour") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime hour")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime minute") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime minute")));
          }else{
            dataoutput_csv.write("--");
          }
          if(p.get("GPSTime second") != null){
            dataoutput_csv.write(Double.toString(p.get("GPSTime second")));
          }else{
            dataoutput_csv.write("--");
          }
          dataoutput_csv.write(";");
        }

        dataoutput_csv.close();
      }
    }
    catch(IOException e) {
    }
    catch(Exception e) {
    }

    exit();
  }

  public void mousePressed() {
    for (ViewController v : this.viewControllers) {
      if (v.visible && v.userInteractionEnabled) {
        v.mousePressed();
      }
    }
  }

  public void mouseReleased() {
    for (ViewController v : this.viewControllers) {
      v.userInteractionEnabled = true;
      if (v.visible) {
        v.mouseReleased();
      }
    }
  }

  public void mouseWheel(float count) {
    for (ViewController v : this.viewControllers) {
      if (v.visible && v.userInteractionEnabled) {
        v.mouseWheel(count);
      }
    }
  }

  public void keyPressed(char k, int c) {
    if (k == '1' && CTRL_PRESSED) {
      this.exitApplication();
    }
    for (ViewController v : this.viewControllers) {
      if (v.visible) {
        v.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k) {
    for (ViewController v : this.viewControllers) {
      if (v.visible) {
        v.keyTyped(k);
      }
    }
  }

  public void keyReleased() {
    for (ViewController v : this.viewControllers) {
      if (v.visible) {
        v.keyReleased();
      }
    }
  }

  private void blockInteraction() {
    for (ViewController v : this.viewControllers) {
      v.userInteractionEnabled = false;
    }
    for (ViewController v : this.viewControllers) {
      v.visible = false;
      v.blockInteraction();
    }
  }






  public void displaySetupScheme() {
    this.blockInteraction();
    this.setupView.visible = true;
  }

  public void displayStartupScheme() {
    this.blockInteraction();
    this.startupView.visible = true;
  }

  public void runMissionSetup() throws IOException {
    // println(this.setupView.getSelectedSerialPort());
    // println(this.setupView.getSelectedSerialBaud());
    // println(this.setupView.getSelectedMissionPath());
    // println(this.setupView.getSelectedMissionIdentifier());
    // println(this.setupView.getSelectedDoConsoleLogFile());
    // println(this.setupView.getSelectedDoCSVDataFile());

    MissionSettings.set(
      this.setupView.getSelectedSerialPort(), 
      PApplet.parseInt(this.setupView.getSelectedSerialBaud()), 
      this.setupView.getSelectedMissionPath(), 
      this.setupView.getSelectedMissionIdentifier(), 
      this.setupView.getSelectedDoConsoleLogFile(), 
      this.setupView.getSelectedDoCSVDataFile()
      );



    //    this.overviewConsoleView.logSetup("Serial port:\n    " + this.setupView.getSelectedSerialPort());
    //    this.overviewConsoleView.logSetup("Serial baud-rate:\n    " + this.setupView.getSelectedSerialBaud());
    //    this.overviewConsoleView.logSetup("Mission folder:\n    " + this.setupView.getSelectedMissionPath());
    //    this.overviewConsoleView.logSetup("Mission identifier:\n    " + this.setupView.getSelectedMissionIdentifier());
    //    this.overviewConsoleView.logSetup("Create console log file:\n    " + (this.setupView.getSelectedDoConsoleLogFile() ? "YES" : "NO"));
    //    this.overviewConsoleView.logSetup("Create CSV data output file:\n    " + (this.setupView.getSelectedDoCSVDataFile() ? "YES" : "NO"));

    if (!(SerialController.open(this.mainJavaEnvironment, MissionSettings.getSerialPort(), MissionSettings.getSerialBaudRate()))) {
      this.overviewConsoleView.logError("Failed to open serial port.\n\nRestart Alpha and check the USB-connection with the device.");
    }
    // try {
    //   SerialController.open(this.mainJavaEnvironment, MissionSettings.getSerialPort(), MissionSettings.getSerialBaudRate());
    // }catch(Exception e){}
    
    try {
      this.serial_receive_file = new FileWriter(MissionSettings.getOutputFolderPath() + "/serial_receive.txt");
      this.serial_receive_file_opened = true;
    }
    catch(Exception e) {
      this.overviewConsoleView.logError("Failed to open serial_receive_file.\n\nRestart Alpha and check the validity of the mission-folder you selected.");
    }


    this.blockInteraction();
    this.viewSelectorView.visible = true;
    this.view_MissionStart.visible = true;
  }

  // ------------------ CONSOLE COMMANDS --------------------------- //

  public void consoleLogSerial(String s){
  	this.overviewConsoleView.logSerial(s);
  }

  public String[] parseCommand(String input) {
    String[] output = input.trim().split("\\s+");
    return output;
  }

  public void runCommand(String command, String[] args) {
    if (ActionRequest.anyRequestOpen() && !(command.equals("confirm") || command.equals("deny"))) {
      ActionRequest.denyAll();
      this.overviewConsoleView.logResponse("All requests denied.");
    }
    switch(command) {
    case "log":
      if (args.length > 0) {
        String msg = "";
        for (int i = 1; i < args.length; ++i) {
          msg += args[i];
          msg += " ";
        }
        if (args[0].equals("msg")) {
          this.overviewConsoleView.logMessage(msg);
        } else if (args[0].equals("wrn")) {
          this.overviewConsoleView.logWarning(msg);
        } else if (args[0].equals("err")) {
          this.overviewConsoleView.logError(msg);
        } else {
          String argslist = "";
          for (int i = 0; i < args.length; ++i) {
            argslist += "\n[" + str(i) + "]->" + args[i];
          }
          this.overviewConsoleView.logSpecial("Command: '" + command + "'\nArguments:" + argslist + "\n\nShould be:\n" + command + " <msg,wrn,err> <text>", "syntax_error");
          argslist = null;
        }
        msg = null;
      } else {
        String argslist = "";
        for (int i = 0; i < args.length; ++i) {
          argslist += "\n[" + str(i) + "]->" + args[i];
        }
        this.overviewConsoleView.logSpecial("Command: '" + command + "'\nArguments:" + argslist + "\n\nShould be:\n" + command + " <msg,wrn,err> <text>", "syntax_error");
        argslist = null;
      }
      break;
    case "print":
      if (args.length > 0) {
        String msg = "";
        for (int i = 0; i < args.length; ++i) {
          msg += args[i];
          msg += " ";
        }
        this.overviewConsoleView.logMessage(msg);
      } else {
        String argslist = "";
        for (int i = 0; i < args.length; ++i) {
          argslist += "\n[" + str(i) + "]->" + args[i];
        }
        this.overviewConsoleView.logSpecial("Command: '" + command + "'\nArguments:" + argslist + "\n\nShould be:\n" + command + " <text>", "syntax_error");
        argslist = null;
      }
      break;
    case "clear":
      this.overviewConsoleView.logResponse("Do you want to clear the console?\n\nType: confirm / deny");
      ActionRequest.clearConsole = true;
      break;
    case "exit":
      this.overviewConsoleView.logResponse("Do you want to exit Alpha?\n\nType: confirm / deny");
      ActionRequest.exitAlpha = true;
      break;
    case "getMissionSetting":
      if (args.length == 1) {
        switch(args[0]) {
        case "serialPort":
          this.overviewConsoleView.logResponse(MissionSettings.getSerialPort());
          break;
        case "serialBaudRate":
          this.overviewConsoleView.logResponse(Integer.toString(MissionSettings.getSerialBaudRate()));
          break;
        case "missionFolder":
          this.overviewConsoleView.logResponse(MissionSettings.getOutputFolderPath());
          break;
        case "missionIdentifier":
          this.overviewConsoleView.logResponse(MissionSettings.getMissionIdentifier());
          break;
        case "createConsoleLogFile":
          this.overviewConsoleView.logResponse(Boolean.toString(MissionSettings.getCreateConsoleLogFile()));
          break;
        case "createCSVDataOutputFile":
          this.overviewConsoleView.logResponse(Boolean.toString(MissionSettings.getCreateCSVDataOutputFile()));
          break;
        default:
          this.overviewConsoleView.logSpecial("getInfo::" + args[0] + " does not exist.", "syntax_error");
          break;
        }
      }
      break;
    case "send":
      if (args.length > 0) {
        for (String s : args) {
          SerialController.send(s);
        }
        this.overviewConsoleView.logResponse("Done.");
      } else {
        this.overviewConsoleView.logSpecial("send <text>", "syntax_error");
      }
      break;
    case "forceDeploy":
      this.switchViewToForceDeploy();
      break;
    case "help":
      if (args.length == 1) {
        switch(args[0]) {
        case "log":
          this.overviewConsoleView.logHelp("-> log <msg,wrn,err> <text>");
          break;
        case "print":
          this.overviewConsoleView.logHelp("-> print <text>");
          break;
        case "clear":
          this.overviewConsoleView.logHelp("-> clear\n\nConfirmation needed");
          break;
        case "exit":
          this.overviewConsoleView.logHelp("-> exit\n\nConfirmation needed");
          break;
        case "getMissionSetting":
          this.overviewConsoleView.logHelp("-> getMissionSetting <setting_name>\n   setting_name options:\n   - serialPort\n   - serialBaudRate\n   - missionFolder\n   - missionIdentifier\n   - createConsoleLogFile\n   - createCSVDataOutputFile\n");
          break;
        case "send":
          this.overviewConsoleView.logHelp("-> send <text>");
          break;
        default:
          this.overviewConsoleView.logSpecial("Command '" + args[0] + "' not found.", "syntax_error");
        }
      } else {
        this.overviewConsoleView.logSpecial("help <command_name>\nType 'list' for list of commands.", "syntax_error");
      }
      break;
    case "list":
      this.overviewConsoleView.logResponse("List of commands:\n\nlog\nprint\nclear\nexit\ngetMissionSetting\nsend");
      break;
    case "confirm":
      if (ActionRequest.clearConsole) {
        this.overviewConsoleView.clearMessages();
      }
      if (ActionRequest.exitAlpha) {
        this.exitApplication();
      }
      ActionRequest.denyAll();
      break;
    case "deny":
      ActionRequest.denyAll();
      this.overviewConsoleView.logResponse("All requests denied.");
      break;
    default:
      this.overviewConsoleView.logSpecial("'" + command + "'", "unknown_command");
    }
  }

  public void deleteMessageFromConsole(int id) {
    this.overviewConsoleView.deleteMessage(id);
  }

  public void clearConsoleMessages() {
    this.overviewConsoleView.clearMessages();
  }

  // ------------------ VIEW SWITCH METHODS ------------------------ //

  public void switchViewToMissionInfo() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_MissionInfo.visible = true;
    this.viewSelectorView.currentViewIdentifier = "missionInfo";
  }

  public void switchViewToFlightPath() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_FlightPath.visible = true;
    this.viewSelectorView.currentViewIdentifier = "flightPath";
  }

  public void switchViewToOverview() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.overviewConsoleView.resize(0, 80, 500, height - 80);
    this.overviewConsoleView.visible = true;
    this.viewSelectorView.currentViewIdentifier = "overview";
  }

  public void switchViewToConsole() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.overviewConsoleView.resize(0, 80, width, height - 80);
    this.overviewConsoleView.visible = true;
    this.viewSelectorView.currentViewIdentifier = "console";
  }

  public void switchViewToDataCharts() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_DataCharts.visible = true;
    this.viewSelectorView.currentViewIdentifier = "dataCharts";
  }

  public void switchViewToBabyCanInfo() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_BabyCanInfo.visible = true;
    this.viewSelectorView.currentViewIdentifier = "babyCanInfo";
  }

  public void switchViewToMotherCanInfo() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_MotherCanInfo.visible = true;
    this.viewSelectorView.currentViewIdentifier = "motherCanInfo";
  }

  public void switchViewToControlButtons() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_controlButtons.visible = true;
    this.viewSelectorView.currentViewIdentifier = "controlButtons";
  }

  public void switchViewToForceDeploy() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_forceDeploy.visible = true;
    this.viewSelectorView.currentViewIdentifier = "forceDeploy";
    this.mouseReleased();
  }

  public void displayUniversalMessage(String t, String s) {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_universalText.setMessage(t, s);
    this.view_universalText.visible = true;
    this.viewSelectorView.currentViewIdentifier = "universalText";
    this.mouseReleased();
  }



  // ------------------ CANSAT FUNCTIONS --------------------------- //
  public void askDeployPermission(){
    this.switchViewToForceDeploy();
  }

  public void notifyBabyCansDeployed(){
    this.displayUniversalMessage("BABYCANS DEPLOYED", "The BabyCans have successfully been deployed.");
  }

  public void sendForceBabyCanDeploy(){
    SerialController.send("[DEP]");
  }




  // ------------------ PUSH UNIVERSAL FUNCTIONS TO VIEW-SELECTOR-VIEW ------------------- //

  public void viewSelectorMethod(String func) {
    this.viewSelectorView.universalMethod(func);
  }




  // ------------------ DIALOG WINDOWS ----------------------------- //





  // ------------------ FILE SELECTION METHODS --------------------- //
  public void SetupView_ask_folder_MissionData() {
    SetupView_ask_folder_MissionData();
  }

  public void SetupView_selected_folder_MissionData(File selected) {
    this.setupView.Response_selected_folder_MissionData(selected);
  }
}
// Charts.pde
// Processing 3.4
// Rens Dur (Project Bèta)



public class ChartRange {
  private float min;
  private float max;

  public ChartRange(float min, float max){
    this.min = min;
    this.max = max;
  }

  public float getMin(){
    return this.min;
  }

  public float getMax(){
    return this.max;
  }

  public ChartRange copy(){
    return new ChartRange(this.min, this.max);
  }

  public void set(float min, float max){
    this.min = min;
    this.max = max;
  }

  public void setMin(float min){
    this.min = min;
  }

  public void setMax(float max){
    this.max = max;
  }

}



public interface Chart_Interface {

}

public class Chart implements Chart_Interface {
  public ChartRange xRange;
  public ChartRange yRange;
  public PVector pos;
  public PVector dim;
  public ArrayList<DataSet> dataSets;
  public int[] colorList;
  public String title;
  public String Xquantity;
  public String Yquantity;
  public String Xunit;
  public String Yunit;
  public boolean autoScroll;

  public Chart(float x, float y, float w, float h, ChartRange xR, ChartRange yR, String title, String Xq, String Xu, String Yq, String Yu){
    this.xRange = xR;
    this.yRange = yR;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.dataSets = new ArrayList<DataSet>();
    this.colorList = new int[7];
    this.colorList[0] = color(255,   0,   0);
    this.colorList[1] = color(  0, 255,   0);
    this.colorList[2] = color(  0,   0, 255);
    this.colorList[3] = color(255,   0, 233);
    this.colorList[4] = color(255, 174,   0);
    this.colorList[5] = color(203,   0, 255);
    this.colorList[6] = color(  0,   0,   0);
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );

    this.title = title;
    this.Xquantity = Xq;
    this.Xunit = Xu;
    this.Yquantity = Yq;
    this.Yunit = Yu;

    this.autoScroll = false;
  }

  public void addDataSet(DataSet a){
    this.dataSets.add(a);
  }

  public void clear(){
    this.dataSets.clear();
  }
  
  public void resize(float x, float y, float w, float h) {
	 this.pos.set(x, y);
	 this.dim.set(w, h);
  }
  
  public void setRange(ChartRange xR, ChartRange yR) {
	 this.xRange = xR;
	 this.yRange = yR;
  }

  public void setXRange(ChartRange xR){
    this.xRange = xR;
  }

  public void setYRange(ChartRange yR){
    this.yRange = yR;
  }

  public void addScroll(float count){
    count = -count/100*(this.yRange.max - this.yRange.min);
    this.yRange.min += count;
    this.yRange.max += count;
  }



  public void show(){
    //
    // GRID
    //

    float dx = this.xRange.max - this.xRange.min;
    int n = floor(log10(dx));
    float amntOfUnitsPerLine = pow(10, n-1);
    while(((amntOfUnitsPerLine / dx) * this.dim.x) < 20){
      amntOfUnitsPerLine *= 2;
    }

    stroke(200);
    fill(0);
    strokeWeight(1);
    textFont(fonts.get("SF").get("Regular 6"));
    textAlign(LEFT);
    for(float currentX = 0; currentX <= this.xRange.max; currentX += amntOfUnitsPerLine){
      if(currentX >= this.xRange.min){
        line(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
        translate(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2 + 10);
        rotateZ(PI/3);
        text(str(currentX), 0, 7);
        rotateZ(-PI/3);
        translate(-map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), -this.pos.y - this.dim.y/2 - 10);
      }
    }
    for(float currentX = 0; currentX >= this.xRange.min; currentX -= amntOfUnitsPerLine){
      if(currentX <= this.xRange.max){
        line(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
        translate(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2 + 10);
        rotateZ(PI/3);
        text(str(currentX), 0, 7);
        rotateZ(-PI/3);
        translate(-map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), -this.pos.y - this.dim.y/2 - 10);
      }
    }

    float dy = this.yRange.max - this.yRange.min;
    n = floor(log10(dy));
    amntOfUnitsPerLine = pow(10, n-1);
    while(((amntOfUnitsPerLine / dy) * this.dim.y) < 20){
      amntOfUnitsPerLine *= 2;
    }

    stroke(200);
    fill(0);
    strokeWeight(1);
    textFont(fonts.get("SF").get("Regular 6"));
    textAlign(RIGHT);
    for(float currentY = 0; currentY <= this.yRange.max; currentY += amntOfUnitsPerLine){
      if(currentY >= this.yRange.min){
        line(this.pos.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
        text(str(currentY), this.pos.x - 10, map(currentY, this.yRange.min, yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2) + 4);
      }
    }
    for(float currentY = 0; currentY >= this.yRange.min; currentY -= amntOfUnitsPerLine){
      if(currentY <= this.yRange.max){
        line(this.pos.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
        text(str(currentY), this.pos.x - 10, map(currentY, this.yRange.min, yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2) + 4);
      }
    }

    //
    // AXES
    //
    stroke(0);
    strokeWeight(2);
    if(this.xRange.min <= 0 && this.xRange.max >= 0){
      //Draw y-axis

      line(map(0, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(0, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
    }

    if(this.yRange.min <= 0 && this.yRange.max >= 0){
      // Draw x-axis

      line(this.pos.x, map(0, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(0, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
    }

    //
    // PLOTTING POINTS
    //

    strokeWeight(2);
    noFill();
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);

    int colorScheme = 0;
    int dataSetNumber = 0;

    if(colorScheme < this.colorList.length){
      stroke(this.colorList[colorScheme]);
    }

    for(DataSet l : this.dataSets){

    	if(!(l == null)) {
	      beginShape();
	      float Pres = l.size()/this.dim.x;
	      int increment = 1;
	      while(Pres > 8){
	        increment *= 2;
	        Pres = (l.size()/increment)/this.dim.x;
	      }
	      for(int i = 0; i < l.size(); i += increment){
	        if(i < l.size()){
	          if(l.getDataAt(i).getXFloat() >= this.xRange.min && l.getDataAt(i).getXFloat() <= this.xRange.max && l.getDataAt(i).getYFloat() >= this.yRange.min && l.getDataAt(i).getYFloat() <= this.yRange.max){
	            vertex(
	              map(l.getDataAt(i).getXFloat(), this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x),
	              map(l.getDataAt(i).getYFloat(), this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2)
	            );
	          }
	          if(l.getDataAt(i).getYFloat() < this.yRange.min || l.getDataAt(i).getYFloat() > this.yRange.max){
	            endShape();
	            beginShape();
	          }
	        }
	      }
	      endShape();
        // if(l.size() > 0){
        //   if(l.getDataAt(l.size()-1).getXFloat() >= this.xRange.max){
        //     this.xRange.max = l.getDataAt(l.size()-1).getXFloat() + (this.xRange.max - this.xRange.min)/20;
        //   }
        // }
	      
	      fill(this.colorList[colorScheme]);
	      text(l.getQuantity(), this.pos.x + 5, this.pos.y - this.dim.y/2 + 15 + 15*dataSetNumber);
	      noFill();
	      
	
	
	      colorScheme++;
	      dataSetNumber++;
	      if(colorScheme >= this.colorList.length){
	        colorScheme = 0;
	      }
	      stroke(this.colorList[colorScheme]);
    	}
    }
    
    
    

    //
    // OUTER BORDER
    //

    stroke(0);
    strokeWeight(1);
    noFill();

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    //
    // TITLE AND NAMINGS
    //

    fill(0);
    textFont(fonts.get("SF").get("Bold"));
    textAlign(CENTER);
    text(this.title, this.pos.x + this.dim.x/2, this.pos.y - this.dim.y/2 - 10);

    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text(this.Xquantity + " (" + this.Xunit + ")", this.pos.x + this.dim.x + 10, this.pos.y + this.dim.y/2);
    textAlign(CENTER);
    text(this.Yquantity + " (" + this.Yunit + ")", this.pos.x, this.pos.y - this.dim.y/2 - 10);
  }
}
// ConsoleElements.pde
// Processing 3.4
// Rens Dur (Project Bèta)

// 'Elements.pde' must be included in your project, since all ConsoleElements are an extention of class Elements

public class ConsoleMessageListElement extends Element {
  public ArrayList<ConsoleMessageElement> messageElements;

  public ConsoleMessageListElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);

    this.messageElements = new ArrayList<ConsoleMessageElement>();
  }

  public void appendMessage(int hours, int mins, int secs, String msg){
    //this.messageElements.add(new ConsoleMessageElement(this.appController, ))
  }
}


//-----------------------------------------------------------------------------------------------------------------//

public class ConsoleMessageElement extends Element {
  public float originalYPos;
  public String[] time;
  public String preMsg;
  public String buffer;
  public String message;
  public String title;
  public int lineCounter;
  public int backgroundColor;
  public boolean isInsideScrollContainer;
  public float scrollContainerMin;
  public float scrollContainerMax;
  public boolean isLayerShift;

  public int consoleViewList_ID;

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg){
    super(a, v, x, y, w, 30);

    this.consoleViewList_ID = -1;

    this.isInsideScrollContainer = false;

    this.originalYPos = this.pos.y;

    this.time = new String[3];
    this.time[0] = str(hours);
    this.time[1] = str(mins);
    this.time[2] = str(secs);
    while(this.time[0].length() < 2){
      this.time[0] = "0" + this.time[0];
    }
    while(this.time[1].length() < 2){
      this.time[1] = "0" + this.time[1];
    }
    while(this.time[2].length() < 2){
      this.time[2] = "0" + this.time[2];
    }


    this.preMsg = msg;
    this.buffer = "";
    this.message = "";
    this.title = "";

    textFont(fonts.get("SF").get("Regular"));
    while(this.preMsg.length() > 0){
      if(!(textWidth(this.buffer + this.preMsg.charAt(0)) > 2*this.dim.x/3 - 20)){
        this.buffer += this.preMsg.charAt(0);
        this.preMsg = this.preMsg.substring(1, this.preMsg.length());
      }else{
        this.message += this.buffer;
        this.buffer = "";
        if(this.preMsg.length() > 0){
          this.message += "\n";
        }
      }
    }
    if(this.buffer.length() > 0){
      this.message += this.buffer;
    }

    this.lineCounter = 1;
    for(int i = 0; i < this.message.length(); ++i){
      if(this.message.charAt(i) == '\n'){
        this.lineCounter++;
      }
    }
    this.calcAndSetHeight(this.lineCounter);

    this.backgroundColor = color(56, 132, 255);

  }

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg, int c){
    this(a, v, x, y, w, hours, mins, secs, msg);
    this.backgroundColor = c;
  }

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg, String c){
    this(a, v, x, y, w, hours, mins, secs, msg);
    if(c == "blue"){
      this.backgroundColor = color(56, 132, 255);
    }else if(c == "yellow"){
      this.backgroundColor = color(255, 233, 0);
    }else if(c == "red"){
      this.backgroundColor = color(255, 0, 0);
    }else if(c == "orange"){
      this.backgroundColor = color(244, 185, 66);
    }
  }

  public void setId(int id){
    this.consoleViewList_ID = id;
  }

  public void setScrollContainer(float scrlMin, float scrlMax){
    this.scrollContainerMin = scrlMin;
    this.scrollContainerMax = scrlMax;
    this.isInsideScrollContainer = true;
  }

  public void removeScrollContainer(){
    this.isInsideScrollContainer = false;
  }



  public void calcAndSetHeight(int lines){
    this.dim.y = lines * 20.5f + 14;
  }

  public void setTitle(String t){
    if(this.title == "" && t.length() > 0){
      this.lineCounter++;
      this.title = t;
    }
    this.calcAndSetHeight(this.lineCounter);
  }

  public void resize(float x, float y){
    this.pos.set(x, y);
  }

  public void applyScrollAmount(float amnt){
    this.pos.y = this.originalYPos + amnt;
  }

  public void removeTitle(){
    this.title = "";
    this.lineCounter--;
    this.calcAndSetHeight(this.lineCounter);
  }

  public void show(){
    if(!this.isInsideScrollContainer || (this.pos.y - this.dim.y/2 < this.scrollContainerMax && this.pos.y + this.dim.y/2 > this.scrollContainerMin)){
      if(this.pos.y + this.dim.y/2 > this.scrollContainerMax || this.pos.y - this.dim.y/2 < this.scrollContainerMin){
        this.isLayerShift = true;
        translate(0, 0, -1);
      }
      if(this.pos.y + this.dim.y/2 >= 0 && this.pos.y - this.dim.y/2 <= this.viewController.dim.y){
        stroke(0);
        strokeWeight(1);
        fill(0);

        textAlign(LEFT);
        textFont(fonts.get("SF").get("Regular"));
        text(this.time[0] + ":" + this.time[1] + ":" + this.time[2], this.pos.x + 10, this.pos.y + 6);


        stroke(this.backgroundColor);
        strokeWeight(2);
        fill(this.backgroundColor, 50);

        rectMode(CORNER);

        rect(this.pos.x + this.dim.x/3, this.pos.y - this.dim.y/2, 2*this.dim.x/3, this.dim.y);
        stroke(0);
        strokeWeight(1);
        fill(0);
        text((this.title.length() > 0 ? "\n" : "") + this.message, this.pos.x + this.dim.x/3 + 10, this.pos.y - this.dim.y/2 + 22);
        if(this.title.length() > 0){
          textFont(fonts.get("SF").get("Bold"));
          text(this.title, this.pos.x + this.dim.x/3 + 10, this.pos.y - this.dim.y/2 + 22);
        }

        if(this.selected){
          this.drawSelectionOutline();
        }
      }
      if(this.isLayerShift){
        this.isLayerShift = false;
        translate(0, 0, 1);
      }
    }
  }



  public void keyPressed(char k, int c){
    if(c == BACKSPACE && CTRL_PRESSED){
      this.deselect();
      this.appController.deleteMessageFromConsole(this.consoleViewList_ID);
    }
  }
}
// ConsoleView.pde
// Processing 3.4
// Rens Dur (Project Bèta)



public class ConsoleView extends ViewController {
  public VerticalScrollElement scrollBar;
  public ArrayList<ConsoleMessageElement> messages;

  public ArrayList<String[]> messagesToLog;
  public ArrayList<Integer> messagesToRemove;

  public TickBoxElement autoScrollTickBox;
  public TextElement autoScrollLabel;

  public ButtonElement clearMessagesButton;

  public NewLineInputElement commandInput;

  public float calculatedMessageHeight;
  public float messageViewHeight;
  public float messageWidth;

  public ConsoleView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.messageWidth = (this.dim.x > 600) ? 600 : this.dim.x;

    this.messageViewHeight = this.dim.y - 80;

    this.scrollBar = new VerticalScrollElement(this.appController, this, this.dim.x - 10, this.messageViewHeight/2, this.messageViewHeight, 0, this.messageViewHeight);


    this.messages = new ArrayList<ConsoleMessageElement>();

    this.messagesToLog = new ArrayList<String[]>();
    this.messagesToRemove = new ArrayList<Integer>();

    this.autoScrollTickBox = new TickBoxElement(this.appController, this, 15, this.dim.y - 20);
    this.autoScrollTickBox.setValue(true);

    this.autoScrollLabel = new TextElement(this.appController, this, 30, this.dim.y - 20, 200, "Autoscroll", LEFT);

    this.clearMessagesButton = new ButtonElement(this.appController, this, this.dim.x - 65, this.dim.y - 20, 50, "Clear"){
      public void clickEvent(){
        this.appController.clearConsoleMessages();
      }
    };

    this.commandInput = new NewLineInputElement(this.appController, this, 10, this.dim.y - 55, this.dim.x - 20){
      public void enterEvent(){
        String[] parse = this.appController.parseCommand(this.getValue());
        if(parse.length > 0){
          this.appController.runCommand(parse[0], Arrays.copyOfRange(parse, 1, parse.length));
        }
        this.reset();
      }
    };
    this.commandInput.setTextFont(fonts.get("SF").get("Regular"));
    this.commandInput.setPlaceholder("Enter command");

    this.calculatedMessageHeight = 0;

    this.elements.add(this.scrollBar);
    this.elements.add(this.autoScrollTickBox);
    this.elements.add(this.autoScrollLabel);
    this.elements.add(this.clearMessagesButton);
    this.elements.add(this.commandInput);

    // this.addMessage("First message", "error");
    // for(int i = 0; i < 100; ++i){
    //   this.addMessage("This is a message", "message");
    // }
    // this.addMessage("Last message", "error");


  }


  private void arrangeMessages(){
    this.calculatedMessageHeight = 0;
    for(ConsoleMessageElement e : this.messages){
      e.resize((this.dim.x - 10)/2 - (this.messageWidth - 20)/2, this.calculatedMessageHeight + 5 + e.dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + e.dim.y;
    }
    this.calculatedMessageHeight += 5;
    this.scrollBar.setExtremes(0, this.messageViewHeight);
    if(this.calculatedMessageHeight > this.messageViewHeight){
      // Now messages start to fall outside of the visible field
      this.scrollBar.setExtremes(0, this.calculatedMessageHeight);
      if(this.autoScrollTickBox.getValue()){
        this.scrollBar.setCurrentPosition(this.calculatedMessageHeight - this.messageViewHeight, this.calculatedMessageHeight);
      }else{
    	 this.scrollBar.setCurrentPosition(this.scrollBar.getMinimumValue(), this.scrollBar.getMinimumValue() + this.messageViewHeight);
    	 if(this.scrollBar.getMaximumValue() > this.calculatedMessageHeight) {
    		 this.scrollBar.setCurrentPosition(this.calculatedMessageHeight - this.messageViewHeight, this.calculatedMessageHeight);
    	 }
      }
    }else {
        this.scrollBar.setCurrentPosition(0, this.messageViewHeight);
    }

  }

  private void addMessage(String msg, String type){
	switch(type) {
	case "warning":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("WARNING");
	  break;
	case "unknown_command":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("UNKNOWN COMMAND");
	  break;
	case "syntax_error":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("SYNTAX ERROR");
	  break;
	case "error":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "red"));
	  this.messages.get(this.messages.size() - 1).setTitle("ERROR");
	  break;
	case "serial":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "orange"));
	  this.messages.get(this.messages.size() - 1).setTitle("SERIAL");
	  break;
	case "setup":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, color(66, 241, 244)));
	  this.messages.get(this.messages.size() - 1).setTitle("SETUP MESSAGE");
	  break;
	case "response":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "blue"));
	  this.messages.get(this.messages.size() - 1).setTitle("RESPONSE");
	  break;
	case "help":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "blue"));
	  this.messages.get(this.messages.size() - 1).setTitle("HELP");
	  break;
	default:
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg));
	  this.messages.get(this.messages.size() - 1).setTitle("MESSAGE");
	  break;
	}
	this.messages.get(this.messages.size() - 1).setScrollContainer(0, this.messageViewHeight);
	this.arrangeMessages();
  }

  private void removeMessage(int id){
    if(id >= 0 && id < this.messages.size()){
      this.messages.remove(id);
    }
  }

  public void deleteMessage(int id){
    this.messagesToRemove.add(id);
  }

  public void clearMessages(){
    for(ConsoleMessageElement e : this.messages){
      e.deselect();
    }
    this.messages.clear();
    this.arrangeMessages();
  }

  public void logMessage(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "message";
    }
  }

  public void logSerial(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "serial";
    }
  }

  public void logWarning(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "warning";
    }
  }

  public void logError(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "error";
    }
  }

  public void logSetup(String msg){
    this.logSpecial(msg, "setup");
  }
  
  public void logResponse(String msg) {
	  this.logSpecial(msg,  "response");
  }
  
  public void logHelp(String msg) {
	  this.logSpecial(msg, "help");
  }

  public void logSpecial(String msg, String id){
    if(msg.length() > 0 && id.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = id;
    }
  }





  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
    if(abs(count) > 18) {
    	this.autoScrollTickBox.setValue(false);
    }
    if(this.scrollBar.isAtBottom()){
      this.autoScrollTickBox.setValue(true);
    }
  }

  public void viewResizeTriggered(){
    //this.messageWidth = (this.dim.x > 400) ? 400 : this.dim.x;
	  this.messageViewHeight = this.dim.y - 80;
	  this.scrollBar.resize(this.dim.x - 10, this.messageViewHeight/2, this.messageViewHeight);
    this.commandInput.resize(10, this.dim.y - 55, this.dim.x - 20);
    this.autoScrollTickBox.resize(15, this.dim.y - 20);
    this.autoScrollLabel.resize(30, this.dim.y - 20, 200);
    this.clearMessagesButton.resize(this.dim.x - 65, this.dim.y - 20, 50);
    for(ConsoleMessageElement e : this.messages){
      e.setScrollContainer(0, this.messageViewHeight);
    };
    //this.scrollBar.setCurrentPosition(0, this.messageViewHeight);
    //this.autoScrollTickBox.setValue(true);
    this.arrangeMessages();
  }

  public void show(){

    while(this.messagesToLog.size() > 0){
      this.addMessage(this.messagesToLog.get(0)[0], this.messagesToLog.get(0)[1]);
      this.messagesToLog.remove(0);
    }

    while(this.messagesToRemove.size() > 0){
      this.removeMessage(this.messagesToRemove.get(0));
      this.messagesToRemove.remove(0);
      this.arrangeMessages();
    }

    this.calculatedMessageHeight = 0;
    for(int i = 0; i < this.messages.size(); ++i){
      this.messages.get(i).resize((this.dim.x - 10)/2 - (this.messageWidth - 20)/2, this.calculatedMessageHeight + 5 + this.messages.get(i).dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + this.messages.get(i).dim.y;

      this.messages.get(i).setId(i);
    }


    stroke(0);
    strokeWeight(1);
    noFill();

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    line((this.dim.x - 10)/2 - (this.messageWidth - 20)/6 - 15, 0, (this.dim.x - 10)/2 - (this.messageWidth - 20)/6 - 15, this.messageViewHeight);
    line(0, this.messageViewHeight, this.dim.x, this.messageViewHeight);
    noStroke();
    fill(255);
    rect(0, this.messageViewHeight, this.dim.x, this.dim.y - this.messageViewHeight);

    for(Element e : this.elements){
      e.show();
    }

    for(ConsoleMessageElement e : this.messages){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }

  public void mousePressed(){
    this.elementFilter.clear();
    for(int i = 0; i < this.elements.size(); ++i){
      if(this.elements.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    int biggest = -1;
    int index = -1;
    for(int i : this.elementFilter){
      if(this.elements.get(i).layer > biggest){
        biggest = this.elements.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.elements.get(index).mousePressed();
    }
    for(int i = 0; i < this.elements.size(); ++i){
      if(i != index){
        this.elements.get(i).deselect();
      }
    }

    // message elements

    this.elementFilter.clear();
    for(int i = 0; i < this.messages.size(); ++i){
      if(this.messages.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    biggest = -1;
    index = -1;
    for(int i : this.elementFilter){
      if(this.messages.get(i).layer > biggest){
        biggest = this.messages.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.messages.get(index).mousePressed();
    }
    for(int i = 0; i < this.messages.size(); ++i){
      if(i != index){
        this.messages.get(i).deselect();
      }
    }
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }

    for(ConsoleMessageElement e : this.messages){
      e.mouseReleased();
    }
  }

  public void mouseWheel(float count){
    if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y && mouseY <= this.pos.y + this.dim.y){
      this.mouseScrolled(count);
    }
    for(Element e : this.elements){
      if(e.mousePressIsWithinBorder()){
        e.mouseWheel(count);
      }
    }
  }

  public void keyPressed(char k, int c){
    for(Element e : this.elements){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(Element e : this.elements){
      if(e.selected){
        e.keyTyped(k);
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(Element e : this.elements){
      if(e.selected){
        e.keyReleased();
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyReleased();
      }
    }
  }
}
// Elements.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public final float defaultNewElementHeight = 30;
public final float defaultNewElementAdditiveHeight = 20;
public final int elementColor_Blue = color(56, 132, 255);

public enum ElementOrient {
  HORIZONTAL,
  VERTICAL
}



public interface Element_Interface {
  public void select();
  public void deselect();
  public void resize(float x, float y, float w, float h);
  public void show();
  public void drawSelectionOutline();
  public boolean mousePressIsWithinBorder();
  public void mousePressed();
  public void mouseReleased();
  public void mouseWheel(float count);
  public void mouseScrolled(float count);
  public void keyPressed(char k, int c);
  public void keyTyped(char k);
  public void keyReleased();

  public void clickEvent();
}

public class Element implements Element_Interface {
  public AppController appController;
  public ViewController viewController;
  public PVector pos;
  public PVector dim;
  public boolean selected;
  public boolean mouseHeld;
  public boolean disabled;
  public int layer;

  public Element(AppController a, ViewController v, float x, float y, float w, float h){
    this.appController = a;
    this.viewController = v;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.selected = false;
    this.mouseHeld = false;
    this.disabled = false;
    this.layer = 0;
  }

  public void select(){
    this.selected = true;
  }

  public void deselect(){
    this.selected = false;
  }

  public void disable(){
    this.disabled = true;
    this.deselect();
  }

  public void enable(){
    this.disabled = false;
  }

  public void drawSelectionOutline(){
    stroke(0, 0, 255, 100);
    strokeWeight(1);
    noFill();
    rectMode(CORNER);
    rect(this.pos.x-2, this.pos.y - this.dim.y/2 - 2, this.dim.x+4, this.dim.y+4);
  }

  public void resize(float x, float y, float w, float h){
    this.pos.set(x, y);
    this.dim.set(w, h);
  }

  public boolean mousePressIsWithinBorder(){
    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2){
      // User clicked element
      return true;
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
      }
    }
  }

  public void mouseReleased(){
    this.mouseHeld = false;
  }

  public void mouseWheel(float count){}

  public void mouseScrolled(float count){}

  public void keyPressed(char k, int c){

  }

  public void keyTyped(char k){

  }

  public void keyReleased(){

  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    fill(255);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    if(this.selected){
      this.drawSelectionOutline();
    }
  }

  public void clickEvent(){}
}

//-----------------------------------------------------------------------------------------------------------------//

public class ButtonElement extends Element {
  public float defaultHeight;
  public String text;
  public int strokeColor;
  public boolean suggested;

  public ButtonElement(AppController a, ViewController v, float x, float y, float w, String t){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.text = t;
    this.strokeColor = color(56, 132, 255);
    this.suggested = false;
  }

  public ButtonElement(AppController a, ViewController v, float x, float y, float w, float h, String t){
    super(a, v, x, y, w, h);
    this.defaultHeight = h;
    this.text = t;
    this.strokeColor = color(56, 132, 255);
    this.suggested = false;
  }

  public void setStrokeColor(int c){
    this.strokeColor = c;
  }

  public void setSuggested(boolean s){
    this.suggested = s;
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
  }

  public void resize(float x, float y, float w, float h){
    this.pos.set(x, y);
    this.dim.set(w, h);
  }

  public void show(){
    if(this.disabled){
      stroke(150);
      fill(255);
    }else{
      stroke(this.strokeColor);
      if(this.mouseHeld){
        fill(200);
      }else{
        if(this.suggested){
          fill(this.strokeColor, 50);
        }else{
          fill(255);
        }
      }
    }
    strokeWeight(2);

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    if(this.disabled){
      fill(150);
      if(this.selected){
        this.selected = false;
      }
    }else{
      fill(0);
    }

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Regular"));
    text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class UtilityButtonElement extends ButtonElement {
  public UtilityButtonElement(AppController a, ViewController v, float x, float y){
    super(a, v, x, y, 20, 20, "...");
  }

  public void resize(float x, float y){
    this.pos.set(x, y);
    this.dim.set(this.defaultHeight, this.defaultHeight);
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class TextButtonElement extends ButtonElement {
  public TextButtonElement(AppController a, ViewController v, float x, float y, float w, String t){
    super(a, v, x, y, w, 20, t);
  }

  public void show(){
    stroke(56, 132, 255);
    fill(56, 132, 255);

    if(this.mouseHeld){
      stroke(0);
      fill(0);
    }

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Regular"));
    text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);

    //if(this.selected){
    //  stroke(0, 100);
    //  strokeWeight(1);
    //  line(this.pos.x + this.dim.x/2 - textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8, this.pos.x + this.dim.x/2 + textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8);
    //}
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class TickBoxElement extends Element {
  public boolean ticked;

  public TickBoxElement(AppController a, ViewController v, float x, float y, float size){
    super(a, v, x, y, size, size);

    this.ticked = false;

  }

  public TickBoxElement(AppController a, ViewController v, float x, float y){
    this(a, v, x, y, 20);
  }

  public void setValue(boolean t){
    this.ticked = t;
  }

  public boolean getValue(){
    return this.ticked;
  }

  public void resize(float x, float y, float size){
    this.pos.set(x, y);
    this.dim.set(size, size);
  }

  public void resize(float x, float y){
    this.resize(x, y, this.dim.x);
  }

  public void show(){
    stroke(56, 132, 255);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.ticked){
      stroke(0);
      strokeWeight(2);
      //line(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8);
      //line(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + 1.2*this.dim.x, this.pos.y - this.dim.y/2);
      //(this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8, this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + 1.2*this.dim.x, this.pos.y - this.dim.y/2 );
      noFill();
      beginShape();
        vertex(this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8);
        vertex(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4);
        vertex(this.pos.x + 1.2f*this.dim.x, this.pos.y - this.dim.y/2);
      endShape();
  }

    if(this.selected){
      this.drawSelectionOutline();
    }
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.ticked = !this.ticked;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class BooleanElement extends TickBoxElement {
  public BooleanElement(AppController a, ViewController v, float x, float y){
    super(a, v, x, y, 0);
    this.dim.x = 35;
    this.dim.y = 20;
  }

  public void show(){
    noStroke();
    fill((this.mouseHeld ? 150 : 200));
    ellipse(this.pos.x + this.dim.y/2, this.pos.y, this.dim.y, this.dim.y);
    ellipse(this.pos.x + this.dim.x - this.dim.y/2, this.pos.y, this.dim.y, this.dim.y);
    rectMode(CORNER);
    rect(this.pos.x + this.dim.y/2, this.pos.y - this.dim.y/2, this.dim.x - this.dim.y, this.dim.y);
    fill(elementColor_Blue);
    ellipse(this.pos.x + (this.ticked ? (this.dim.x - this.dim.y/2) : (this.dim.y/2)), this.pos.y, this.dim.y, this.dim.y);
  }
}




//-----------------------------------------------------------------------------------------------------------------//


public class TextElement extends ButtonElement {
  public int alignment;

  public TextElement(AppController a, ViewController v, float x, float y, float w, String t, int align){
    super(a, v, x, y, w, 20, t);
    this.alignment = align;
  }

  public void setText(String t){
    this.text = t;
  }

  public void show(){
    stroke(0);
    fill(0);

    textFont(fonts.get("SF").get("Regular"));

    if(this.alignment == CENTER){
      textAlign(CENTER);
      text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);
    }else if(this.alignment == LEFT){
      textAlign(LEFT);
      text(this.text, this.pos.x + 10, this.pos.y + 6);
    }else if(this.alignment == RIGHT){
      textAlign(RIGHT);
      text(this.text, this.pos.x + this.dim.x - 10, this.pos.y + 6);
    }

    //if(this.selected){
    //  stroke(0, 100);
    //  strokeWeight(1);
    //  line(this.pos.x + this.dim.x/2 - textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8, this.pos.x + this.dim.x/2 + textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8);
    //}
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class LineInputElement extends Element {
  public float defaultHeight;
  public String text;
  public String displayText;
  public boolean backspaceStillPressed;
  public int backspaceCount;
  public PFont stdFont;
  public boolean contentsHidden;

  public LineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.text = "";
    this.displayText = "";
    this.backspaceCount = 0;
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.contentsHidden = false;
  }

  public LineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
    this.defaultHeight = h;
    this.text = "";
    this.displayText = "";
    this.backspaceCount = 0;
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.contentsHidden = false;
  }

  public void hideContents(boolean a){
    this.contentsHidden = a;
    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    while(textWidth(this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
    }
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
  }

  public void reset(){
    this.text = "";
    this.displayText = "";
  }

  public void setValue(String s){
    for(int i = 0; i < s.length(); ++i){
      this.keyTyped(s.charAt(i));
    }
  }

  public String getValue(){
    return this.text;
  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    text(this.displayText + (this.selected ? "_" : ""), this.pos.x + 10, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }

    //update text input
    if(this.backspaceStillPressed){
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }
  }

  public void enterEvent(){}

  public void keyPressed(char k, int c){
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }
  }

  public void keyReleased(){
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
  }

  public void backspaceTriggered(){
    if(ALT_PRESSED){
      this.reset();
    }else if(this.text.length() > 0){
      this.text = this.text.substring(0, this.text.length() - 1);
      if(this.contentsHidden){
        this.displayText = "";
        for(int i = 0; i < this.text.length(); ++i){
          this.displayText += "*";
        }
      }else{
        this.displayText = this.text;
      }

      textFont(this.stdFont);
      while(textWidth(this.displayText) > this.dim.x - 20){
        this.displayText = this.displayText.substring(1, this.displayText.length());
      }
    }
  }

  public void keyTyped(char k){
    this.text += k;
    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    while(textWidth(this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//
public class NumberLineInputElement extends LineInputElement {
  public boolean sign;
  public boolean signed;
  public boolean displaySign;
  public boolean numbersHidden;

  public NumberLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
    this.sign = true;
    this.signed = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public NumberLineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
    this.sign = true;
    this.signed = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public void setSigned(boolean s){
    this.signed = s;
  }

  public void reset(){
    this.text = "";
    this.displayText = "";
    this.sign = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public float getNumber(){
    return (this.sign ? 1.0f : -1.0f) * PApplet.parseFloat(this.text);
  }

  public void show(){

    if(this.getNumber() == 0 || this.text.isEmpty()){
      this.displaySign = false;
    }else{
      this.displaySign = true;
    }

    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    text((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText + (this.selected ? "_" : ""), this.pos.x + 10, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }

    //update text input
    if(this.backspaceStillPressed){
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }
  }

  public void backspaceTriggered(){
    if(ALT_PRESSED){
      this.reset();
    }else if(this.text.length() > 0){
      this.text = this.text.substring(0, this.text.length() - 1);
      if(this.contentsHidden){
        this.displayText = "";
        for(int i = 0; i < this.text.length(); ++i){
          this.displayText += "*";
        }
      }else{
        this.displayText = this.text;
      }

      textFont(this.stdFont);
      this.numbersHidden = false;
      while(textWidth((this.numbersHidden ? "<" : "") + (this.displaySign ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
        this.displayText = this.displayText.substring(1, this.displayText.length());
        this.numbersHidden = true;
      }
    }
  }

  public void keyTyped(char k){

    if(k >= '0' && k <= '9'){
      this.text += k;
    }else if(k == '.'){
      if(!this.text.contains(".")){
        if(this.text.length() == 0){
          this.text += "0";
        }
        this.text += ".";
      }
    }else if(k == '-' && this.signed){
      this.sign = !this.sign;
    }

    if(this.text.length() > 1){
      while(this.text.charAt(0) == '0' && this.text.length() > 1){
        if(!(this.text.charAt(1) == '.')){
          this.text = this.text.substring(1, this.text.length());
        }else{
          break;
        }
      }
    }


    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    this.numbersHidden = false;
    while(textWidth((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
      this.numbersHidden = true;
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//
public class IntegerLineInputElement extends NumberLineInputElement {
  public IntegerLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
  }

  public IntegerLineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
  }

  public int getInteger(){
    return PApplet.parseInt((this.sign ? 1.0f : -1.0f) * PApplet.parseFloat(this.text));
  }

  public void keyTyped(char k){

    if(k >= '0' && k <= '9'){
      this.text += k;
    }else if(k == '-' && this.signed){
      this.sign = !this.sign;
    }

    if(this.text.length() > 1){
      while(this.text.charAt(0) == '0' && this.text.length() > 1){
        if(!(this.text.charAt(1) == '.')){
          this.text = this.text.substring(1, this.text.length());
        }else{
          break;
        }
      }
    }


    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    this.numbersHidden = false;
    while(textWidth((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
      this.numbersHidden = true;
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class NewLineInputElement extends Element {
  public int cursorPos;
  public boolean cursorBlinkOn;
  public float cursorBlinkCount;
  public int beginTextDisplay, endTextDisplay;
  public ArrayList<Character> text;
  public String displayText;
  public String placeHolder;
  public boolean backspaceStillPressed;
  public int backspaceCount;
  public boolean arrowStillPressed;
  public int arrowCount;
  public int arrowFrequencyCount;
  public boolean arrowDirection;
  public PFont stdFont;



  public NewLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, defaultNewElementHeight);
    this.cursorPos = 0;
    this.cursorBlinkOn = true;
    this.cursorBlinkCount = 0;
    this.beginTextDisplay = 0;
    this.endTextDisplay = 0;
    this.text = new ArrayList<Character>();
    this.displayText = "";
    this.placeHolder = "Type here";
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
    this.arrowStillPressed = false;
    this.arrowCount = 0;
    this.arrowFrequencyCount = 0;
    this.arrowDirection = false;
    this.stdFont = fonts.get("SF").get("Light");
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, defaultNewElementHeight);
    this.arrangeString();
  }

  public void setPlaceholder(String s){
    this.placeHolder = s;
  }

  public String getValue(){
    String output = "";
    for(char i : this.text){
      output += i;
    }
    return output;
  }

  public void setValue(String v){
    this.text.clear();
    for(int i = 0; i < v.length(); ++i){
      this.text.add(v.charAt(i));
    }
    this.cursorPos = this.text.size();
    this.arrangeString();
    this.contentEdited();
  }

  public void reset(){
    this.cursorPos = 0;
    this.text.clear();
    this.arrangeString();
    this.contentEdited();
  }

  public void setTextFont(PFont f){
    this.stdFont = f;
  }

  public void show(){

    if(this.mousePressIsWithinBorder()){
      SET_MOUSEPOINTER_TEXT = true;
    }

    // stroke(0);
    // strokeWeight(1);
    // line(this.pos.x, this.pos.y + this.dim.y/2, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2);

    // SHOWING CONTENTS
    textFont(this.stdFont);
    textAlign(LEFT);

    if(this.displayText.length() > 0){
      stroke(0);
      fill(0);
      text(this.displayText, this.pos.x + 2, this.pos.y + 6);
    }else{
      stroke(180);
      fill(180);
      text(this.placeHolder, this.pos.x + 2, this.pos.y + 6);
    }

    if(this.selected && this.cursorBlinkOn){
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y - this.dim.y/2.3f, this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y + this.dim.y/2.3f);
    }






    if(this.selected){
      this.cursorBlinkCount += 1/frameRate;
      if(this.cursorBlinkCount > 0.5f){
        this.cursorBlinkOn = !this.cursorBlinkOn;
        this.cursorBlinkCount = 0;
      }
    }else{
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
    }

    //update text input
    if(this.backspaceStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }

    if(this.arrowStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.arrowCount++;
      this.arrowFrequencyCount++;
    }
    if(this.arrowCount >= frameRate/2){
      if(this.arrowFrequencyCount > frameRate/10){
        if(this.arrowDirection == false){
          // LEFT
          if(this.cursorPos > 0){
            this.cursorPos--;
            if(this.cursorPos < this.beginTextDisplay){
              // cursor out of field <--
              this.beginTextDisplay = this.cursorPos;
              this.arrangeString();
            }

          }
        }else{
          // RIGHT
          if(this.cursorPos < this.text.size()){
            this.cursorPos++;
            this.arrangeString();
          }
        }
        this.arrowFrequencyCount = 0;
      }
    }
  }

  public void enterEvent(){}

  public void arrangeString(){
    // constructing displayText
    this.displayText = "";
    for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
      this.displayText += this.text.get(i);
    }

    textFont(this.stdFont);
    this.endTextDisplay = this.text.size();
    while(textWidth(this.displayText) > this.dim.x - 4){
      if(this.endTextDisplay > this.cursorPos){
        this.displayText = this.displayText.substring(0, this.displayText.length() - 1);
        this.endTextDisplay--;
      }else{
        // cursor left visible field
        this.beginTextDisplay++;
        this.displayText = "";
        for(int i = this.beginTextDisplay; i < this.cursorPos; ++i){
          this.displayText += this.text.get(i);
        }
      }
    }
  }

  public void contentEdited(){}

  public void keyPressed(char k, int c){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }else if(c == LEFT){
      if(this.cursorPos > 0){
        this.cursorPos--;
        if(this.cursorPos < this.beginTextDisplay){
          // cursor out of field <--
          this.beginTextDisplay = this.cursorPos;
          this.arrangeString();
        }

      }
      this.arrowStillPressed = true;
      this.arrowDirection = false;
    }else if(c == RIGHT){
      if(this.cursorPos < this.text.size()){
        this.cursorPos++;
        this.arrangeString();
      }
      this.arrowStillPressed = true;
      this.arrowDirection = true;
    }
    this.contentEdited();
  }

  public void keyReleased(){
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
    this.arrowStillPressed = false;
    this.arrowCount = 0;
    this.arrowFrequencyCount = 0;
  }

  public void backspaceTriggered(){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    // handle backspace event
    if(this.cursorPos > 0){
      this.text.remove(this.cursorPos - 1);
      this.cursorPos--;
      if(this.cursorPos < this.beginTextDisplay){
        // cursor out of field <--
        this.beginTextDisplay = this.cursorPos;
      }
      this.arrangeString();
      textFont(this.stdFont);
      if(textWidth(this.displayText) < this.dim.x - 4){
        this.beginTextDisplay = 0;
        this.arrangeString();
      }
    }
    this.contentEdited();
  }

  public void keyTyped(char k){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(this.cursorPos == this.text.size()){
      // cursor is at end of string
      this.text.add(k);
      this.cursorPos++;
      this.displayText = "";
      for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
        this.displayText += this.text.get(i);
      }
      textFont(this.stdFont);
      while(textWidth(this.displayText) > this.dim.x - 4){
        // cursor went out of visible field.
        this.beginTextDisplay++;
        this.displayText = "";
        for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
          this.displayText += this.text.get(i);
        }
      }
    }else{
      // cursor is not at end of string
      this.text.add(this.cursorPos, k);
      this.cursorPos++;

      this.arrangeString();
    }
    this.contentEdited();
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();

        //this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2

        textFont(this.stdFont);
        int closest = 0;
        float smallestDistance = width;
        for(int i = 0; i <= this.displayText.length(); ++i){
          if(abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2)) < smallestDistance){
            closest = i;
            smallestDistance = abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2));
          }
        }
        this.cursorPos = this.beginTextDisplay + closest;
      }
    }
  }



  


}

//-----------------------------------------------------------------------------------------------------------------//

public class SmartSelectionElement extends NewLineInputElement {
  public ArrayList<String> options;
  public ArrayList<String> optionFilter;
  public String filterBuffer;
  public PFont boldFont;
  public float dimY_options;
  public boolean superStrict;

  public SmartSelectionElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
    this.options = new ArrayList<String>();
    // for(int i = 1400; i < 2400; ++i){
    //   this.options.add("/dev/cu.usbmodem" + str(i));
    // }
    this.optionFilter = new ArrayList<String>();
    this.filterBuffer = "";
    this.boldFont = fonts.get("SF").get("Bold");
    this.dimY_options = this.dim.y;
    this.superStrict = false;
  }

  public void setStrict(boolean s){
    this.superStrict = s;
  }

  public void addOption(String s){
    this.options.add(s);
  }

  public void select(){
    this.selected = true;
    this.layer = 1;
    this.contentEdited();
  }

  public void deselect(){
    this.selected = false;
    this.layer = 0;
    this.dimY_options = this.dim.y;
  }

  public void show(){

    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2){
      // user hovers element
      SET_MOUSEPOINTER_TEXT = true;
    }


    // stroke(0);
    // strokeWeight(1);
    // line(this.pos.x, this.pos.y + this.dim.y/2, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2);

    if(this.selected){
      translate(0, 0, 1);
      stroke(0);
      strokeWeight(1);
      fill(255);
      rectMode(CORNER);
      rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);
    }

    // SHOWING CONTENTS
    // if(this.optionFilter.size() > 0 || this.displayText.length() == 0){
    //   textFont(this.stdFont);
    // }else{
    //   textFont(this.boldFont);
    // }
    textFont(this.stdFont);
    textAlign(LEFT);

    if(this.selected && this.displayText.length() > 0 && this.optionFilter.size() > 0){
      fill(180);
      text(this.optionFilter.get(0), this.pos.x + 2, this.pos.y + 6);
    }

    if(this.displayText.length() > 0){
      stroke(0);
      fill(0);
      text(this.displayText, this.pos.x + 2, this.pos.y + 6);
    }else{
      stroke(180);
      fill(180);
      text(this.placeHolder, this.pos.x + 2, this.pos.y + 6);
    }

    if(this.selected && this.cursorBlinkOn){
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y - this.dim.y/2.3f, this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y + this.dim.y/2.3f);
    }






    if(this.selected){
      this.cursorBlinkCount += 1/frameRate;
      if(this.cursorBlinkCount > 0.5f){
        this.cursorBlinkOn = !this.cursorBlinkOn;
        this.cursorBlinkCount = 0;
      }
    }else{
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
    }

    //update text input
    if(this.backspaceStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }

    if(this.arrowStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.arrowCount++;
      this.arrowFrequencyCount++;
    }
    if(this.arrowCount >= frameRate/2){
      if(this.arrowFrequencyCount > frameRate/10){
        if(this.arrowDirection == false){
          // LEFT
          if(this.cursorPos > 0){
            this.cursorPos--;
            if(this.cursorPos < this.beginTextDisplay){
              // cursor out of field <--
              this.beginTextDisplay = this.cursorPos;
              this.arrangeString();
            }

          }
        }else{
          // RIGHT
          if(this.cursorPos < this.text.size()){
            this.cursorPos++;
            this.arrangeString();
          }
        }
        this.arrowFrequencyCount = 0;
      }
    }

    //
    // PART BELONGING TO SMART SELECTION ELEMENT
    //

    if(this.selected){
      float y = 0;
      stroke(0);
      strokeWeight(1);
      rectMode(CORNER);
      textFont(this.stdFont);
      textAlign(LEFT);
      for(String s : this.optionFilter){
        if(this.viewController.pos.y + this.pos.y + this.dim.y/2 + y <= height){
          noStroke();
          fill(255);
          rect(this.pos.x, this.pos.y + this.dim.y/2 + y, this.dim.x, defaultNewElementAdditiveHeight);

          stroke(0);
          line(this.pos.x, this.pos.y + this.dim.y/2 + y, this.pos.x, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight);
          line(this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight);

          fill(0);
          text(s, this.pos.x + 2, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight/2 + 6);
        }
        y += defaultNewElementAdditiveHeight;
      }
      line(this.pos.x, this.pos.y + this.dim.y/2 + y, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y);
      this.dimY_options = this.dim.y + y;
      translate(0, 0, -1);
    }else{
      this.dimY_options = this.dim.y;
    }





  }

  public void contentEdited(){
    //if(this.text.size() > 0){
      // user has made request for suggestion
      String userInput = "";
      for(char c : this.text){
        userInput += c;
      }

      this.optionFilter.clear();
      for(String s : this.options){
        if(s.length() >= userInput.length()){
          this.optionFilter.add(s);
        }
      }
      //printArray(this.optionFilter);
      if(userInput.length() > 0){
        for(int i = this.optionFilter.size() - 1; i >= 0; --i){
          if(!(userInput.equals(this.optionFilter.get(i).substring(0, userInput.length())))){
            this.optionFilter.remove(i);
          }
        }
      }

      if(this.superStrict){
        if(this.optionFilter.size() == 0){
          this.cursorPos = this.text.size();
          this.backspaceTriggered();
        }
      }

      //printArray(this.optionFilter);
    //}
  }

  public void keyPressed(char k, int c){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }else if(c == LEFT){
      if(this.cursorPos > 0){
        this.cursorPos--;
        if(this.cursorPos < this.beginTextDisplay){
          // cursor out of field <--
          this.beginTextDisplay = this.cursorPos;
          this.arrangeString();
        }

      }
      this.arrowStillPressed = true;
      this.arrowDirection = false;
    }else if(c == RIGHT){
      if(this.cursorPos < this.text.size()){
        this.cursorPos++;
        this.arrangeString();
      }else if(this.cursorPos == this.text.size() && this.optionFilter.size() > 0 && this.text.size() > 0){
        this.text.clear();
        for(int j = 0; j < this.optionFilter.get(0).length(); ++j){
          this.text.add(this.optionFilter.get(0).charAt(j));
        }
        this.cursorPos = this.text.size();
        this.arrangeString();
        this.contentEdited();
      }
      this.arrowStillPressed = true;
      this.arrowDirection = true;
    }
    this.contentEdited();
  }

  public boolean mousePressIsWithinBorder(){
    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y - this.dim.y/2 + this.dimY_options){
      // User clicked element
      return true;
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled && ( mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2 )){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();

        //this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2

        textFont(this.stdFont);
        int closest = 0;
        float smallestDistance = width;
        for(int i = 0; i <= this.displayText.length(); ++i){
          if(abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2)) < smallestDistance){
            closest = i;
            smallestDistance = abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2));
          }
        }
        this.cursorPos = this.beginTextDisplay + closest;
      }else if(!this.disabled){
        // option pressed
        int selected = 0;
        for(int i = 0; i < this.optionFilter.size(); ++i){
          if(mouseY >= this.viewController.pos.y + this.pos.y + this.dim.y/2 + defaultNewElementAdditiveHeight * i &&
             mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2 + defaultNewElementAdditiveHeight * (i+1)){
            selected = i;
            this.text.clear();
            for(int j = 0; j < this.optionFilter.get(i).length(); ++j){
              this.text.add(this.optionFilter.get(i).charAt(j));
            }
            this.cursorPos = this.text.size();
            this.arrangeString();
            this.contentEdited();
            this.deselect();
            break;
          }
        }
      }
    }
  }





}




//-----------------------------------------------------------------------------------------------------------------//
public class SelectionElement extends Element {
  public ArrayList<String> options;
  public int sel;
  public float defaultHeight;
  public boolean opened;
  public PFont stdFont;

  public SelectionElement(AppController a, ViewController v, float x, float y, String[] list){
    super(a, v, x, y, 100, 30);
    this.defaultHeight = 30;
    this.options = new ArrayList<String>();
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.sel = -1;
    textFont(this.stdFont);
    for(String s : list){
      this.options.add(s);
      if(textWidth(s) + 35 > this.dim.x - 20){
        this.dim.x = textWidth(s) + 35;
      }
    }
  }

  public SelectionElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.options = new ArrayList<String>();
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.sel = -1;
  }

  public void select(){
    this.selected = true;
    this.layer = 1;
  }

  public void deselect(){
    this.selected = false;
    this.opened = false;
    this.layer = 0;
  }

  public String getValue(){
    if(this.sel >= 0 && this.sel < this.options.size()){
      return this.options.get(this.sel);
    }
    return "";
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
    textFont(this.stdFont);
    for(String s : this.options){
      if(textWidth(s) + 35 > this.dim.x - 20){
        this.dim.x = textWidth(s) + 35;
      }
    }
  }

  public void addOption(String s){
    textFont(this.stdFont);
    this.options.add(s);
    if((textWidth(s) + 35) > (this.dim.x - 20)){
      this.dim.x = textWidth(s) + 35;
    }
  }

  public void removeOption(String s){
    for(int i = 0; i < this.options.size(); ++i){
      if(this.options.get(i) == s){
        this.options.remove(i);
        break;
      }
    }
  }

  public boolean mousePressIsWithinBorder(){
    if(this.opened){
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.options.size() * this.dim.y + this.dim.y/2){
        // User clicked element or an option
        return true;
      }
      return false;
    }else{
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y/2){
        // User clicked element
        return true;
      }
      return false;
    }
  }

  public void mousePressed(){
    if(this.opened){
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.options.size() * this.dim.y + this.dim.y/2){
        // User clicked element or an option
        this.clickEvent();
        this.selectOptionClickEvent();
        this.mouseHeld = true;
        this.select();
      }else{
        this.deselect();
        this.opened = false;
      }
    }else{
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y/2){
        // User clicked element
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
        this.opened = true;
      }
    }
  }

  private void selectOptionClickEvent(){
    for(int i = 0; i < this.options.size(); ++i){
      if(mouseY >= this.pos.y + this.dim.y * (i+1) - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y * (i+1) + this.dim.y/2){
        // An option has been pressed!
        this.deselect();
        this.opened = false;
        this.sel = i;
        break;
      }
    }
  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    if(this.sel >= 0 && this.sel < this.options.size()){
      text(this.options.get(this.sel), this.pos.x + 10, this.pos.y + 6);
    }else{
      this.sel = -1;
    }

    if(this.opened){
      translate(0, 0, 2);

      this.sel = -1;

      stroke(0);
      strokeWeight(1);
      rectMode(CORNER);
      textAlign(LEFT);
      textFont(this.stdFont);
      for(int i = 0; i < this.options.size(); ++i){
        fill(255);
        rect(this.pos.x, this.pos.y + this.dim.y * (i+1) - this.dim.y/2, this.dim.x, this.dim.y);
        fill(0);
        text(this.options.get(i), this.pos.x + 10, this.pos.y + this.dim.y * (i+1) + 6);
      }

      translate(0, 0, -2);
    }else{
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + this.dim.x - 10 - 5, this.pos.y + 3, this.pos.x + this.dim.x - 10 - 5 - 5, this.pos.y - 3);
      line(this.pos.x + this.dim.x - 10 - 5, this.pos.y + 3, this.pos.x + this.dim.x - 10, this.pos.y - 3);
    }
  }


}

//-----------------------------------------------------------------------------------------------------------------//

public class StepperCounterElement extends Element {

  public StepperCounterElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
  }

}

//-----------------------------------------------------------------------------------------------------------------//

public class DateSelectionElement extends Element {
  public float defaultHeight;
  public String parts;
  public ArrayList<Element> elements;
  public SelectionElement day;
  public SelectionElement month;
  public SelectionElement year;

  public DateSelectionElement(AppController a, ViewController v, float x, float y, String parts){
    super(a, v, x, y, 200, 30);
    this.defaultHeight = 30;
    this.parts = parts;
    this.elements = new ArrayList<Element>();
    this.day = new SelectionElement(this.appController, this.viewController, this.pos.x, this.pos.y, 50);
    for(int i = 1; i <= 31; ++i){
      this.day.addOption(str(i));
    }

    this.elements.add(this.day);
  }

  public void mousePressed(){
    for(Element e : this.elements){
      e.deselect();
      e.mousePressed();
    }
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }
  }

  public void show(){
    for(Element e : this.elements){
      e.show();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class SpinWheelLoadingElement extends Element {
  public float counter;
  public float step;
  public int amountOfLines;
  public boolean shown;

  public SpinWheelLoadingElement(AppController a, ViewController v, float x, float y, float s){
    super(a, v, x, y, s, s);
    this.counter = 0;
    this.step = 0;
    this.amountOfLines = PApplet.parseInt(s);
    this.shown = true;
  }

  public SpinWheelLoadingElement(AppController a, ViewController v, float x, float y, float s, int n){
    super(a, v, x, y, s, s);
    this.counter = 0;
    this.step = 0;
    this.amountOfLines = n;
    this.shown = true;
  }

  public void resize(float x, float y, float s){
    this.pos.set(x, y);
    this.dim.set(s, s);
  }

  public void toggleHide(){
    this.shown = !this.shown;
  }

  public void hide(boolean f){
    this.shown = !f;
  }

  public void show(){
    if(this.shown){
      this.counter += 30/frameRate;
      if(this.counter >= 1){
        this.counter = 0;
        this.step += TWO_PI/PApplet.parseFloat(this.amountOfLines);
        if(this.step >= TWO_PI){
          this.step = 0;
          this.counter += 30/frameRate;
        }
      }

      translate(this.pos.x, this.pos.y);

      rotate(this.step);

      strokeWeight(this.dim.x/5);

      for(int i = 0; i < this.amountOfLines; ++i){
        stroke(255 - map(PApplet.parseFloat(i), 0, PApplet.parseFloat(this.amountOfLines)-1, 0, 255));
        line(
          this.dim.x/3 * cos(i/PApplet.parseFloat(this.amountOfLines) * TWO_PI),
          this.dim.x/3 * sin(i/PApplet.parseFloat(this.amountOfLines) * TWO_PI),
          this.dim.x * cos(i/PApplet.parseFloat(this.amountOfLines) * TWO_PI),
          this.dim.x * sin(i/PApplet.parseFloat(this.amountOfLines) * TWO_PI)
        );
      }


      //stroke(200);
      //strokeWeight(2);
      //fill(200);
      //for(float i = 0; i <= 2*PI; i += (2/float(this.amountOfLines))*PI){
      //  line(
      //    this.dim.x/3*cos(i),
      //    this.dim.x/3*sin(i),
      //    this.dim.x*cos(i),
      //    this.dim.x*sin(i)
      //  );
      //}

      //stroke(0);
      //fill(0);
      //line(
      //  this.dim.x/3*cos(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x/3*sin(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x*cos(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x*sin(this.step * ((2/float(this.amountOfLines))*PI))
      //);

      //if(this.step > 0){
      //  stroke(100);
      //  fill(100);
      //  line(
      //    this.dim.x/3*cos((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x/3*sin((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*cos((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*sin((this.step-1) * ((2/float(this.amountOfLines))*PI))
      //  );
      //}else{
      //  stroke(100);
      //  fill(100);
      //  line(
      //    this.dim.x/3*cos(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x/3*sin(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*cos(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*sin(this.amountOfLines * ((2/float(this.amountOfLines))*PI))
      //  );
      //}

      rotate(-this.step);

      translate(-this.pos.x, -this.pos.y);
    }
  }
}


//-----------------------------------------------------------------------------------------------------------------//

public class VerticalScrollElement extends Element {
  public float min; // Minimum rangeMin
  public float max; // Maximum rangeMax
  public float rangeMin; // Current rangeMin
  public float rangeMax; // Current rangeMax
  public boolean isDragged;
  public float dragHeight;


  public VerticalScrollElement(AppController a, ViewController v, float x, float y, float h, float min, float max){
    super(a, v, x, y, 10, h);

    if(max > min){
      this.min = min;
      this.max = max;
      this.rangeMin = this.min;
      this.rangeMax = this.max;
    }

    this.isDragged = false;
    this.dragHeight = this.rangeMax - this.rangeMin;
  }

  public void resize(float x, float y, float h){
	  this.pos.set(x, y);
	  this.dim.y = h;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.isDragged = true;
      this.dragHeight = this.rangeMax - this.rangeMin;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }

  public void mouseReleased(){
    this.isDragged = false;
    this.mouseHeld = false;
  }

  public void setExtremes(float min, float max){
    if(max > min){
      this.min = min;
      this.max = max;
    }
  }

  public void setCurrentPosition(float minVisible, float maxVisible){
    if(maxVisible > minVisible){
      this.rangeMin = minVisible;
      this.rangeMax = maxVisible;
    }
  }

  public float getMinimumValue(){
    return this.rangeMin;
  }
  
  public float getMaximumValue() {
    return this.rangeMax;
  }

  public boolean isAtTop(){
    return (this.rangeMin == this.min);
  }

  public boolean isAtBottom(){
    return (this.rangeMax == this.max);
  }

  public void addScroll(float count){
    this.dragHeight = this.rangeMax - this.rangeMin;
    this.rangeMin += count;
    this.rangeMax = this.rangeMin + this.dragHeight;
    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }
  }

  public void show(){
    noStroke();
    fill(230);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.mouseHeld){
      stroke(0);
      strokeWeight(1);
      fill(0);
    }else{
      noStroke();
      fill(200);
    }
    rect(this.pos.x, this.pos.y - this.dim.y/2 + (this.rangeMin/(this.max - this.min))*this.dim.y, this.dim.x, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.y);

    if(this.isDragged){
      this.rangeMin = this.min + ((mouseY - (this.viewController.pos.y + this.pos.y - this.dim.y/2))/this.dim.y)*(this.max - this.min) - this.dragHeight/2;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }

    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }

  }


}


//-----------------------------------------------------------------------------------------------------------------//

public class HorizontalScrollElement extends Element {
  public float min; // Minimum rangeMin
  public float max; // Maximum rangeMax
  public float rangeMin; // Current rangeMin
  public float rangeMax; // Current rangeMax
  public boolean isDragged;
  public float dragHeight;


  public HorizontalScrollElement(AppController a, ViewController v, float x, float y, float w, float min, float max){
    super(a, v, x, y, w, 10);

    if(max > min){
      this.min = min;
      this.max = max;
      this.rangeMin = this.min;
      this.rangeMax = this.max;
    }

    this.isDragged = false;
    this.dragHeight = this.rangeMax - this.rangeMin;
  }

  public void resize(float x, float y, float w){
	  this.pos.set(x, y);
	  this.dim.x = w;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.isDragged = true;
      this.dragHeight = this.rangeMax - this.rangeMin;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }

  public void mouseReleased(){
    this.isDragged = false;
    this.mouseHeld = false;
  }

  public void setExtremes(float min, float max){
    if(max > min){
      this.min = min;
      this.max = max;
    }
  }

  public void setCurrentPosition(float minVisible, float maxVisible){
    if(maxVisible > minVisible){
      this.rangeMin = minVisible;
      this.rangeMax = maxVisible;
    }
  }

  public float getMinimumValue(){
    return this.rangeMin;
  }
  
  public float getMaximumValue() {
	  return this.rangeMax;
  }

  public void addScroll(float count){
    this.dragHeight = this.rangeMax - this.rangeMin;
    this.rangeMin += count;
    this.rangeMax = this.rangeMin + this.dragHeight;
    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }
  }

  public void show(){
    noStroke();
    fill(230);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.mouseHeld){
      stroke(0);
      strokeWeight(1);
      fill(0);
    }else{
      noStroke();
      fill(200);
    }
    //rect(this.pos.x, this.pos.y - this.dim.y/2 + (this.rangeMin/(this.max - this.min))*this.dim.y, this.dim.x, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.y);
    rect(this.pos.x + (this.rangeMin/(this.max - this.min))*this.dim.x, this.pos.y - this.dim.y/2, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.x, this.dim.y);

    if(this.isDragged){
      this.rangeMin = this.min + ((mouseX - (this.viewController.pos.x + this.pos.x))/this.dim.x)*(this.max - this.min) - this.dragHeight/2;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }

    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }

  }


}


// ------------------------------------------------------------------------------- //


public class SliderElement extends Element {
	public boolean vertical;
	
	public Range range;
	public float currentPos;
	
	public SliderElement(AppController a, ViewController v, float x, float y, float size, String orient, Range r) {
		super(a, v, x, y, 0, 0);
		
		this.vertical = false;
		if(orient == "vertical") {
			this.vertical = true;
		}
		
		if(this.vertical) {
			this.resize(x, y, 10, size);
		}else {
			this.resize(x, y, size, 10);
		}
		
		// Determined the orientation of the slider
		
		this.range = r;
		
		this.currentPos = this.range.min;
	}
	
	
}



// -------------------------------------------------------------------------------------------------------------------- //

public class SmartSliderElement extends Element {
  public ElementOrient orientation;
  public Range range;
  public float lowerValue;
  public float upperValue;
  public float ballSize;
  public boolean grub;
  public boolean leftGrub;

  public SmartSliderElement(AppController a, ViewController v, float x, float y, ElementOrient o, float size, float min, float max){
    super(a, v, x, y, (o == ElementOrient.HORIZONTAL ? size : 10), (o == ElementOrient.HORIZONTAL ? 10 : size));
    this.orientation = o;
    this.range = new Range(min, max);
    this.lowerValue = this.range.min;
    this.upperValue = this.range.max;
    this.ballSize = 20;
  }

  public Range getValue(){
    return new Range(this.lowerValue, this.upperValue);
  }

  public void setValue(float min, float max){
    if((max > min) && (min >= this.range.min) && (max <= this.range.max)){
      this.lowerValue = min;
      this.upperValue = max;
    }
  }

  public void resize(float x, float y, float size){
    this.pos.set(x, y);
    if(this.orientation == ElementOrient.HORIZONTAL){
      this.dim.x = size;
    }else if(this.orientation == ElementOrient.VERTICAL){
      this.dim.y = size;
    }
  }

  public void show(){
    if(this.orientation == ElementOrient.HORIZONTAL){
      stroke(0);
      strokeWeight(2);
      line(this.pos.x, this.pos.y, this.pos.x + this.dim.x, this.pos.y);
      noStroke();
      fill(elementColor_Blue);
      translate(0, 0, 1);
      ellipse(this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y, this.ballSize, this.ballSize);
      ellipse(this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y, this.ballSize, this.ballSize);
      translate(0, 0, -1);

      if(this.grub){
        textFont(fonts.get("SF").get("Regular 6"));
        textAlign(CENTER);
        fill(0);
        if(this.leftGrub){
          this.lowerValue = map(mouseX - this.viewController.pos.x - this.pos.x, 0, this.dim.x, this.range.min, this.range.max);
          if(this.lowerValue < this.range.min){
            this.lowerValue = this.range.min;
          }
          if(this.lowerValue >= this.upperValue - (this.range.max - this.range.min)/1000){
            this.lowerValue = this.upperValue - (this.range.max - this.range.min)/1000;
          }
        }else{
          this.upperValue = map(mouseX - this.viewController.pos.x - this.pos.x, 0, this.dim.x, this.range.min, this.range.max);
          if(this.upperValue > this.range.max){
            this.upperValue = this.range.max;
          }
          if(this.upperValue <= this.lowerValue + (this.range.max - this.range.min)/1000){
            this.upperValue = this.lowerValue + (this.range.max - this.range.min)/1000;
          }
        }
        text(this.lowerValue, this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y + this.ballSize);
        text(this.upperValue, this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y + this.ballSize);
      }


    }else if(this.orientation == ElementOrient.VERTICAL){
      stroke(0);
      strokeWeight(2);
      line(this.pos.x, this.pos.y, this.pos.x, this.pos.y + this.dim.y);
      noStroke();
      fill(elementColor_Blue);
      ellipse(this.pos.x, this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y), this.ballSize, this.ballSize);
      ellipse(this.pos.x, this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y), this.ballSize, this.ballSize);

      if(this.grub){
        textFont(fonts.get("SF").get("Regular 6"));
        textAlign(LEFT);
        fill(0);
        if(this.leftGrub){
          this.lowerValue = map(mouseY - this.viewController.pos.y - this.pos.y, 0, this.dim.y, this.range.min, this.range.max);
          if(this.lowerValue < this.range.min){
            this.lowerValue = this.range.min;
          }
          if(this.lowerValue >= this.upperValue - (this.range.max - this.range.min)/1000){
            this.lowerValue = this.upperValue - (this.range.max - this.range.min)/1000;
          }
        }else{
          this.upperValue = map(mouseY - this.viewController.pos.y - this.pos.y, 0, this.dim.y, this.range.min, this.range.max);
          if(this.upperValue > this.range.max){
            this.upperValue = this.range.max;
          }
          if(this.upperValue <= this.lowerValue + (this.range.max - this.range.min)/1000){
            this.upperValue = this.lowerValue + (this.range.max - this.range.min)/1000;
          }
        }
        text(this.lowerValue, this.pos.x + this.ballSize, this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y));
        text(this.upperValue, this.pos.x + this.ballSize, this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y));
      }
    }
  }

  public boolean mousePressIsWithinBorder(){
    if(this.orientation == ElementOrient.HORIZONTAL){
      if(mouseX >= this.viewController.pos.x + this.pos.x - this.ballSize/2 &&
        mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x + this.ballSize/2 &&
        mouseY >= this.viewController.pos.y + this.pos.y - this.ballSize/2 &&
        mouseY <= this.viewController.pos.y + this.pos.y + this.ballSize/2){
        // User clicked element
        return true;
      }
    }else if(this.orientation == ElementOrient.VERTICAL){
      if(mouseX >= this.viewController.pos.x + this.pos.x - this.ballSize/2 &&
        mouseX <= this.viewController.pos.x + this.pos.x + this.ballSize/2 &&
        mouseY >= this.viewController.pos.y + this.pos.y - this.ballSize/2 &&
        mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y + this.ballSize/2){
        // User clicked element
        return true;
      }
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
        if(this.orientation == ElementOrient.HORIZONTAL){
          if(mouseX >= this.viewController.pos.x + this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x) - this.ballSize/2 &&
              mouseX <= this.viewController.pos.x + this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = true;
          }else if(mouseX >= this.viewController.pos.x + this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x) - this.ballSize/2 &&
              mouseX <= this.viewController.pos.x + this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = false;
          }
        }else if(this.orientation == ElementOrient.VERTICAL){
          if(mouseY >= this.viewController.pos.y + this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y) - this.ballSize/2 &&
              mouseY <= this.viewController.pos.y + this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = true;
          }else if(mouseY >= this.viewController.pos.y + this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y) - this.ballSize/2 &&
              mouseY <= this.viewController.pos.y + this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = false;
          }
        }
      }
    }
  }

  public void mouseReleased(){
    this.mouseHeld = false;
    this.grub = false;
  }


}
// FileSelectorMethods.pde
// Processing 3.4
// Rens Dur (Project Bèta)


//-----------------------------------------------------------------------------------------------------------------//
// AppController -> (void)SetupView_ask_folder_MissionData -> [callback](void)SetupView_selected_folder_MissionData -> AppController_Interface

/*init*/public void SetupView_ask_folder_MissionData(){
  selectFolder("Select to store the mission data.", "SetupView_selected_folder_MissionData");
}

/*callback*/public void SetupView_selected_folder_MissionData(File selected){
	if(selected != null) {
		appController.SetupView_selected_folder_MissionData(selected);
	}
}
// MathFunctions.pde
// Processing 3.4
// Rens Dur (Project Bèta)

final public float e = 2.718281828f;


final public float log10(float a){
  return log(a)/log(10);
}

final public float log20(float a){
  return log(a)/log(20);
}

final public float log50(float a){
  return log(a)/log(50);
}

final public float logBase(float a, float g){
  return log(a)/log(g);
}

final public float ln(float a){
  return log(a)/log(e);
}


final public double log10(double a){
  return (double)log10((float)a);
}

final public double log20(double a){
  return (double)log20((float)a);
}

final public double log50(double a){
  return (double)log50((float)a);
}

final public double logBase(double a, double g){
  return (double)logBase((float)a, (float)g);
}

final public double ln(double a){
  return (double)ln((float)a);
}
class Parachute{
  PVector pos;
  float Width;
  float Height;
  float CenterDepth;
  float Depth;
  float depthStep;
  float segWidth;
  float StringLength;
  int nSegments;
  
  int primaryColor;
  int secondaryColor;
  
  float alpha;
  float beta;
  
  PVector frontLeft;
  PVector frontRight;
  PVector backRight;
  PVector backLeft;
  
  Parachute(float x, float y, float z, float w, float h, float cd, float od, float sl, int nseg, int p, int s){
    pos = new PVector(x,y,z);
    Width = w;
    Height = h;
    Depth = cd;
    CenterDepth = cd;
    StringLength = sl;
    if(nseg > 0){
      nSegments = nseg;
    }else{
      nSegments = 1;
    }
    segWidth = Width/nSegments;
    depthStep = (cd-od)/((nSegments-1)/2);
    
    alpha = sin((0.5f*segWidth)/StringLength)*2;
    beta = sin((0.5f*Depth)/StringLength)*2;
    
    primaryColor = p;
    secondaryColor = s;
    
    frontLeft = new PVector(0,0,0);
    frontRight = new PVector(0,0,0);
    backRight = new PVector(0,0,0);
    backLeft = new PVector(0,0,0);
  }
  
  public void show(){
    pushMatrix();
    translate(pos.x,pos.y,pos.z);
    //rotateX(map(mouseY,0,height,0,-2*PI));
    //rotateY(map(mouseX,0,width,0,2*PI));
    stroke(0);
    strokeWeight(1);
    fill(primaryColor);
    
    Depth = CenterDepth;
    beta = sin((0.5f*Depth)/StringLength)*2;
    
    // 1
    line(0,0,0,-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    line(0,0,0,StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    line(0,0,0,StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    line(0,0,0,-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    beginShape();
    vertex(-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    vertex(StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    vertex(StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    vertex(-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    endShape(CLOSE);
    
    frontLeft.set(-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    frontRight.set(StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),StringLength*(sin(0.5f*beta)));
    backRight.set(StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    backLeft.set(-StringLength*sin(0.5f*alpha),-StringLength*cos(0.5f*alpha),-StringLength*(sin(0.5f*beta)));
    
    String colorSet = "primary";
    
    for(int i = 1; i <= (nSegments-1)/2; ++i){
      Depth -= depthStep;
      beta = sin((0.5f*Depth)/StringLength)*2;
      
      if(colorSet == "primary"){
        fill(secondaryColor);
        colorSet = "secondary";
      }else if(colorSet == "secondary"){
        fill(primaryColor);
        colorSet = "primary";
      }
      
      line(0,0,0,-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      line(0,0,0,-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
      beginShape();
      vertex(-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      vertex(frontLeft.x,frontLeft.y,frontLeft.z);
      vertex(backLeft.x,backLeft.y,backLeft.z);
      vertex(-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
      endShape(CLOSE);
      
      frontLeft.set(-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      backLeft.set(-StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
      
      line(0,0,0,StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      line(0,0,0,StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
      beginShape();
      vertex(frontRight.x,frontRight.y,frontRight.z);
      vertex(StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      vertex(StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
      vertex(backRight.x,backRight.y,backRight.z);
      endShape(CLOSE);
      
      frontRight.set(StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),StringLength*(sin(0.5f*beta)));
      backRight.set(StringLength*sin((i+0.5f)*alpha),-StringLength*cos((i+0.5f)*alpha),-StringLength*(sin(0.5f*beta)));
    }
    
    
    
    
    
    //// 1
    //line(0,0,0,-30,-147,20);
    //line(0,0,0,30,-147,20);
    //line(0,0,0,30,-147,-20);
    //line(0,0,0,-30,-147,-20);
    //beginShape();
    //vertex(-30,-147,20);
    //vertex(30,-147,20);
    //vertex(30,-147,-20);
    //vertex(-30,-147,-20);
    //endShape(CLOSE);
    
    ////2
    //line(0,0,0,-84,-124,20);
    //line(0,0,0,-84,-124,-20);
    //beginShape();
    //vertex(-84,-124,20);
    //vertex(-30,-147,20);
    //vertex(-30,-147,-20);
    //vertex(-84,-124,-20);
    //endShape(CLOSE);
    
    ////2
    //line(0,0,0,84,-124,20);
    //line(0,0,0,84,-124,-20);
    //beginShape();
    //vertex(30,-147,20);
    //vertex(84,-124,20);
    //vertex(84,-124,-20);
    //vertex(30,-147,-20);
    //endShape(CLOSE);
    
    popMatrix();
  }
}
// SerialController.java
// Processing 3.4
// Rens Dur (Project Bèta)
// Alpha MCS software package






// TASK: improve, this is just sufficient.

public static class SerialController {
	public static Serial serialCom = null;
	public static boolean isOpen = false;
	public static ArrayList<String> received = new ArrayList<String>();
	
	public static String[] getAvailablePorts() {
		return Serial.list();
	}
	
	public static boolean open(PApplet javaEnvironment, String portName, int baudRate) {
		try{
			if(isOpen && !(serialCom == null)) {
				serialCom.stop();
				serialCom = null;
				isOpen = false;
			}
			if(portName.length() > 0 && baudRate >= 300) {
				serialCom = new Serial(javaEnvironment, portName, baudRate);
				isOpen = true;
        		return true;
			}else{
		        return false;
		      }
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean close() {
		try{
			if(isOpen) {
				serialCom.stop();
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean send(String s) {
		try{
			if(isOpen) {
				serialCom.write(s);
			}
			return true;
		}catch(Exception e){
			return false;
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
// SetupView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class SetupView extends ViewController {
  public TextButtonElement backButton;
  public ButtonElement continueButton;

  // Main elements
  public TextElement serialConnectionLabel;
  public TextElement missionFolderLabel;
  public TextElement missionIdentifierLabel;
  public TextElement consoleLogFileTBLabel;
  public TextElement csvDataFileTBLabel;

  public SmartSelectionElement serialPortSelect;
  public SmartSelectionElement serialBaudSelect;

  public UtilityButtonElement dataOutputFolderButton;
  public TextElement dataOutputFolderName;

  public NewLineInputElement missionIdentifierInput;

  public BooleanElement consoleLogFileTickBox;
  public BooleanElement csvDataFileTickBox;

  public SmartSliderElement testSlider;

  // Data
  String absoluteMissionPath;




  public SetupView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);



    this.backButton = new TextButtonElement(this.appController, this, this.dim.x/2 - 70, this.dim.y - 40, 60, "Back"){
      public void clickEvent(){
        this.appController.displayStartupScheme();
      }
    };

    this.continueButton = new ButtonElement(this.appController, this, this.dim.x/2 + 10, this.dim.y - 40, 100, "Continue"){
      public void clickEvent(){
        try{
          this.appController.runMissionSetup();
        }catch(IOException e){
          println("Something went wrong when trying to write to file.");
        }catch(Exception e){
          println("Something unidentifiable went wrong.");
        }
      }
    };

    // Main elements
    this.serialConnectionLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 200, 200, "Serial connection:", RIGHT);
    this.missionFolderLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 280, 200, "Mission folder:", RIGHT);
    this.missionIdentifierLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 320, 200, "Mission identifier:", RIGHT);

    this.serialPortSelect = new SmartSelectionElement(this.appController, this, this.dim.x/2 + 10, 200, 300);
    this.serialPortSelect.setPlaceholder("Serial port");
    this.serialPortSelect.setStrict(true);
    this.serialBaudSelect = new SmartSelectionElement(this.appController, this, this.dim.x/2 + 10, 240, 150);
    this.serialBaudSelect.setPlaceholder("Baud rate");
    this.serialBaudSelect.setStrict(true);

    this.dataOutputFolderButton = new UtilityButtonElement(this.appController, this, this.dim.x/2 + 10, 280){
      public void clickEvent(){
        SetupView_ask_folder_MissionData();
      }
    };
    this.dataOutputFolderName = new TextElement(this.appController, this, this.dim.x/2 + 30, 280, 500, "", LEFT);

    this.missionIdentifierInput = new NewLineInputElement(this.appController, this, this.dim.x/2 + 10, 320, 200);

    this.consoleLogFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 400, 200, "Create console-log file", RIGHT);
    this.consoleLogFileTickBox = new BooleanElement(this.appController, this, this.dim.x/2 + 10, 400);


    this.csvDataFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 440, 200, "Create CSV data-output file", RIGHT);
    this.csvDataFileTickBox = new BooleanElement(this.appController, this, this.dim.x/2 + 10, 440);
    
    this.testSlider = new SmartSliderElement(this.appController, this, 100, 100, ElementOrient.VERTICAL, 200, 0, 100);

    this.elements.add(this.backButton);
    this.elements.add(this.continueButton);

    this.elements.add(this.serialConnectionLabel);
    this.elements.add(this.missionFolderLabel);
    this.elements.add(this.missionIdentifierLabel);

    this.elements.add(this.serialPortSelect);
    this.elements.add(this.serialBaudSelect);

    this.elements.add(this.dataOutputFolderButton);
    this.elements.add(this.dataOutputFolderName);

    this.elements.add(this.missionIdentifierInput);

    this.elements.add(this.consoleLogFileTickBox);
    this.elements.add(this.consoleLogFileTBLabel);
    this.elements.add(this.csvDataFileTickBox);
    this.elements.add(this.csvDataFileTBLabel);

    //this.elements.add(this.testSlider);

    // Setting default values
    for(String s : SerialController.getAvailablePorts()){
      this.serialPortSelect.addOption(s);
    }

    this.serialBaudSelect.addOption("300");
    this.serialBaudSelect.addOption("1200");
    this.serialBaudSelect.addOption("2400");
    this.serialBaudSelect.addOption("4800");
    this.serialBaudSelect.addOption("9600");
    this.serialBaudSelect.addOption("19200");
    this.serialBaudSelect.addOption("38400");
    this.serialBaudSelect.addOption("57600");
    this.serialBaudSelect.addOption("74880");
    this.serialBaudSelect.addOption("115200");
    this.serialBaudSelect.addOption("230400");
    this.serialBaudSelect.addOption("250000");
    this.serialBaudSelect.addOption("500000");
    this.serialBaudSelect.addOption("1000000");
    this.serialBaudSelect.addOption("2000000");

    this.missionIdentifierInput.setValue("PB_Flight01");

    this.consoleLogFileTickBox.setValue(true);


    // Setting default data

    this.absoluteMissionPath = "";

  }

  public String getSelectedSerialPort(){
    return this.serialPortSelect.getValue();
  }

  public int getSelectedSerialBaud(){
    return PApplet.parseInt(this.serialBaudSelect.getValue());
  }

  public String getSelectedMissionPath(){
    return this.absoluteMissionPath;
  }

  public String getSelectedMissionIdentifier(){
    return this.missionIdentifierInput.getValue();
  }

  public boolean getSelectedDoConsoleLogFile(){
    return this.consoleLogFileTickBox.getValue();
  }

  public boolean getSelectedDoCSVDataFile(){
    return this.csvDataFileTickBox.getValue();
  }



  public void viewResizeTriggered(){
    this.backButton.resize(this.dim.x/2 - 70, this.dim.y - 40, 60);
    this.continueButton.resize(this.dim.x/2 + 10, this.dim.y - 40, 100);

    this.serialConnectionLabel.resize(this.dim.x/2 - 210, 200, 200);
    this.missionFolderLabel.resize(this.dim.x/2 - 210, 280, 200);
    this.missionIdentifierLabel.resize(this.dim.x/2 - 210, 320, 200);

    this.serialPortSelect.resize(this.dim.x/2 + 10, 200, 300);
    this.serialBaudSelect.resize(this.dim.x/2 + 10, 240, 150);

    this.dataOutputFolderButton.resize(this.dim.x/2 + 10, 280);
    this.dataOutputFolderName.resize(this.dim.x/2 + 30, 280, 500);

    this.missionIdentifierInput.resize(this.dim.x/2 + 10, 320, 200);

    this.consoleLogFileTickBox.resize(this.dim.x/2 - 100, 400);
    this.consoleLogFileTBLabel.resize(this.dim.x/2 - 80, 400, 300);

    this.csvDataFileTickBox.resize(this.dim.x/2 - 100, 440);
    this.csvDataFileTBLabel.resize(this.dim.x/2 - 80, 440, 300);
  }

  public void show(){
    // view UPDATE part
    if(this.serialPortSelect.getValue() == "" ||
      this.serialBaudSelect.getValue() == "" ||
      this.missionIdentifierInput.getValue().length() < 1 ||
      this.absoluteMissionPath == ""){

      this.continueButton.disabled = true;
    }else{
      this.continueButton.disabled = false;
    }


    stroke(255);
    strokeWeight(1);
    fill(255);

    rectMode(CORNER);

    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    fill(0);

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Thin 20"));
    text("Session setup", this.dim.x/2, 50);

    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }

  // --------------- APPCONTROLLER DATA FILL ----------------- //
  public void Response_selected_folder_MissionData(File selected){
    this.absoluteMissionPath = selected.getAbsolutePath();
    this.dataOutputFolderName.setText(selected.getName());
  }
}
// StartupView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class StartupView extends ViewController {
  public SpaceAnimation spaceAnimation;
  public TextButtonElement button;
  public ButtonElement shortCut;

  public StartupView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);
    this.spaceAnimation = new SpaceAnimation();
    this.button = new TextButtonElement(this.appController, this, this.dim.x/2 - 30, this.dim.y/2 + 100, 60, "Start"){
      public void clickEvent(){
        this.appController.displaySetupScheme();
      }
    };

    this.shortCut = new ButtonElement(this.appController, this, this.dim.x/2 - 50, this.dim.y/2 + 130, 100, "Short Cut"){
      public void clickEvent(){
        try{
          this.appController.runMissionSetup();
        }catch(IOException e){
          println("Something went wrong when trying to write to file.");
        }catch(Exception e){
          println("Something unidentifiable went wrong.");
        }
      }
    };

    this.elements.add(this.button);
    //this.elements.add(this.shortCut);
  }

  public void viewResizeTriggered(){
    this.button.resize(this.dim.x/2-50, this.dim.y/2 + 100, 100);
    this.shortCut.resize(this.dim.x/2 - 50, this.dim.y/2 + 130, 100);
  }

  public void show(){
    stroke(255);
    strokeWeight(1);
    //fill(255);
    noFill();

    rectMode(CORNER);

    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    this.spaceAnimation.show();

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    translate(0, 0, 100);

    fill(0);

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Black 20"));
    text("Alpha", this.dim.x/2, this.dim.y/2 - this.dim.y/10);

    textFont(fonts.get("Lora").get("Regular"));
    text("CanSat Mission Control Software by Rens Dur\n(Project Bèta)", this.dim.x/2, this.dim.y/2 - this.dim.y/10 + 30);

    translate(0, 0, -100);

    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }
}
// ViewController.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public interface ViewController_Interface {
  public void toggleVisibility();
  public void resize(float x, float y, float w, float h);
  public void viewResizeTriggered();
  public void show();
  public void mousePressed();
  public void mouseReleased();
  public void mouseWheel(float count);
  public void mouseScrolled(float count);
  public void keyPressed(char k, int c);
  public void keyTyped(char k);
  public void keyReleased();
}

public class ViewController implements ViewController_Interface {
  public AppController appController;
  public PVector pos;
  public PVector dim;
  public boolean visible;
  public boolean userInteractionEnabled;
  public ArrayList<Element> elements;
  public ArrayList<Integer> elementFilter;

  public ViewController(AppController a, float x, float y, float w, float h){
    this.appController = a;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.visible = true;
    this.userInteractionEnabled = true;
    this.elements = new ArrayList<Element>();
    this.elementFilter = new ArrayList<Integer>();
  }

  public void universalMethod(String func){}

  public void viewResizeTriggered(){}

  public void blockInteraction(){}

  public void resize(float x, float y, float w, float h){
    this.pos.set(x, y);
    this.dim.set(w, h);
    this.viewResizeTriggered();
  }

  public void toggleVisibility(){
    this.visible = !this.visible;
  }

  public void show(){
    stroke(0);
    strokeWeight(1);
    fill(255);

    rectMode(CORNER);

    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);
  }

  public void mousePressed(){
    this.elementFilter.clear();
    for(int i = 0; i < this.elements.size(); ++i){
      if(this.elements.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    int biggest = -1;
    int index = -1;
    for(int i : this.elementFilter){
      if(this.elements.get(i).layer > biggest){
        biggest = this.elements.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.elements.get(index).mousePressed();
    }
    for(int i = 0; i < this.elements.size(); ++i){
      if(i != index){
        this.elements.get(i).deselect();
      }
    }
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }
  }

  public void mouseWheel(float count){
    if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y && mouseY <= this.pos.y + this.dim.y){
      this.mouseScrolled(count);
    }
    for(Element e : this.elements){
      if(e.mousePressIsWithinBorder()){
        e.mouseWheel(count);
      }
    }
  }

  public void mouseScrolled(float count){}

  public void keyPressed(char k, int c){
    for(Element e : this.elements){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(Element e : this.elements){
      if(e.selected){
        e.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(Element e : this.elements){
      if(e.selected){
        e.keyReleased();
      }
    }
  }
}
// ViewSelectorView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ViewSelectorView extends ViewController {

  public ArrayList<ButtonElement> viewButtons;

  public HorizontalScrollElement scrollBar;
  public float viewWidth;

  public ButtonElement exitButton;
  public ButtonElement setupButton;
  public ButtonElement forceDeployButton;

  public ButtonElement missionInfoButton;
  public ButtonElement overviewButton;
  public ButtonElement consoleButton;
  public ButtonElement BabyCanInfo;
  public ButtonElement MotherCanInfo;
  public ButtonElement ControlButtons;
  public ButtonElement FlightPath;
  public ButtonElement MeasuredData;

  public String currentViewIdentifier;


  public ViewSelectorView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.currentViewIdentifier = "";

    this.viewButtons = new ArrayList<ButtonElement>();

    this.viewWidth = this.dim.x;
    this.scrollBar = new HorizontalScrollElement(this.appController, this, 0, this.dim.y - 5, this.dim.x, 0, this.dim.x);
    this.scrollBar.setCurrentPosition(0, this.viewWidth);

    this.exitButton = new ButtonElement(this.appController, this, 5, 15, 60, 20, "Exit"){
      public void clickEvent(){
        this.appController.exitApplication();
      }
    };
    this.exitButton.setStrokeColor(color(255, 0, 0));
    this.exitButton.setSuggested(true);

    this.setupButton = new ButtonElement(this.appController, this, 70, 15, 60, 20, "Setup"){

    };
    this.setupButton.setStrokeColor(color(255, 233, 0));
    this.setupButton.setSuggested(true);

    this.forceDeployButton = new ButtonElement(this.appController, this, 150, 15, 140, 20, "Force Deploy View"){
      public void clickEvent(){
        this.appController.switchViewToForceDeploy();
      }
    };

    this.missionInfoButton = new ButtonElement(this.appController, this, 0, 0, 120, "Mission info"){
      public void clickEvent(){
        this.appController.switchViewToMissionInfo();
        this.disable();
      }
    };
    
    this.BabyCanInfo = new ButtonElement(this.appController, this, 0, 0, 130, "BabyCan info") {
    	public void clickEvent() {
    		this.appController.switchViewToBabyCanInfo();
    		this.disable();
    	}
    };

    this.MotherCanInfo = new ButtonElement(this.appController, this, 0, 0, 120, "MotherCan info") {
    	public void clickEvent() {
    		this.appController.switchViewToMotherCanInfo();
    		this.disable();
    	}
    };

    this.ControlButtons = new ButtonElement(this.appController, this, 0, 0, 120, "Control buttons"){
      public void clickEvent(){
        this.appController.switchViewToControlButtons();
        this.disable();
      }
    };
    
    this.FlightPath = new ButtonElement(this.appController, this, 0, 0, 90, "Flight path") {
    	public void clickEvent() {
    		this.appController.switchViewToFlightPath();
    		this.disable();
    	}
    };
    
    this.MeasuredData = new ButtonElement(this.appController, this, 0, 0, 120, "Measured data"){
        public void clickEvent(){
            this.appController.switchViewToDataCharts();
            this.disable();
          }
        };

    this.overviewButton = new ButtonElement(this.appController, this, 0, 0, 90, "Overview"){
      public void clickEvent(){
        this.appController.switchViewToOverview();
        this.disable();
      }
    };

    this.consoleButton = new ButtonElement(this.appController, this, 0, 0, 80, "Console"){
      public void clickEvent(){
        this.appController.switchViewToConsole();
        this.disable();
      }
    };

    this.elements.add(this.exitButton);
    this.elements.add(this.setupButton);
    this.elements.add(this.scrollBar);
    this.viewButtons.add(this.missionInfoButton);
    this.viewButtons.add(this.overviewButton);
    this.viewButtons.add(this.consoleButton);
    this.viewButtons.add(this.MotherCanInfo);
    this.viewButtons.add(this.BabyCanInfo);
    this.viewButtons.add(this.ControlButtons);
    this.viewButtons.add(this.FlightPath);
    this.viewButtons.add(this.MeasuredData);
    
    float b = 5;
    for(ButtonElement e : this.viewButtons) {
    	e.resize(b, 50, e.dim.x);
    	b += e.dim.x + 5;
    }
    if(b > this.viewWidth) {
    	this.viewWidth = b;
    	this.scrollBar.setExtremes(0, this.viewWidth);
    	this.scrollBar.setCurrentPosition(0, this.dim.x);
    }
  }

  public void universalMethod(String func){
    if(func.equals("AddForceDeployButton")){
      this.elements.add(this.forceDeployButton);
    }
  }

  public void viewResizeTriggered(){
    this.exitButton.resize(5, 15, 60, 20);
    this.scrollBar.resize(0, this.dim.y - 5, this.dim.x);

    this.viewWidth = this.dim.x;
    float b = 5;
    for(ButtonElement e : this.viewButtons) {
    	e.resize(b, 50, e.dim.x);
    	b += e.dim.x + 5;
    }
    if(b > this.viewWidth) {
    	this.viewWidth = b;
    }
	this.scrollBar.setExtremes(0, this.viewWidth);
    this.scrollBar.setCurrentPosition(0, this.dim.x);
  }

  public void enableAllButtons(){
    for(ButtonElement e : this.viewButtons) {
    	e.enable();
    }
  }

  public void disableOverviewButton(){
    this.overviewButton.disable();
  }

  public void disableConsoleButton(){
    this.consoleButton.disable();
  }

  public void show(){


    noStroke();
    fill(255);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    stroke(150);
    strokeWeight(1);
    line(this.pos.x, this.pos.y + 30, this.pos.x + this.dim.x, this.pos.y + 30);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    fill(0);
    textFont(fonts.get("SF").get("Regular"));
    textAlign(RIGHT);
    text(str(hour()) + ":" + (str(minute()).length() < 2 ? "0" + str(minute()) : str(minute())) + ":" + (str(second()).length() < 2 ? "0" + str(second()) : str(second())), this.dim.x - 5, 20);
    //text(CALENDAR.get(Calendar.DAY_OF_WEEK), this.dim.x - 75, 20);


    for(Element e : this.elements){
      e.show();
    }

    translate(-this.scrollBar.getMinimumValue(), 0);
    for(ButtonElement e : this.viewButtons){
      e.show();
    }
    translate(this.scrollBar.getMinimumValue(), 0);

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

    stroke(0);
    strokeWeight(2);
    line(this.pos.x, this.pos.y + this.dim.y, this.pos.x + this.dim.x, this.pos.y + this.dim.y);
  }

  public void mousePressed(){
    this.elementFilter.clear();
    for(int i = 0; i < this.elements.size(); ++i){
      if(this.elements.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    int biggest = -1;
    int index = -1;
    for(int i : this.elementFilter){
      if(this.elements.get(i).layer > biggest){
        biggest = this.elements.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.elements.get(index).mousePressed();
    }
    for(int i = 0; i < this.elements.size(); ++i){
      if(i != index){
        this.elements.get(i).deselect();
      }
    }

    // viewButton elements

    mouseX += this.scrollBar.getMinimumValue();

    this.elementFilter.clear();
    for(int i = 0; i < this.viewButtons.size(); ++i){
      if(this.viewButtons.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    biggest = -1;
    index = -1;
    for(int i : this.elementFilter){
      if(this.viewButtons.get(i).layer > biggest){
        biggest = this.viewButtons.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.viewButtons.get(index).mousePressed();
    }
    for(int i = 0; i < this.viewButtons.size(); ++i){
      if(i != index){
        this.viewButtons.get(i).deselect();
      }
    }

    mouseX -= this.scrollBar.getMinimumValue();
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }

    for(ButtonElement e : this.viewButtons){
      e.mouseReleased();
    }
  }

  public void mouseWheel(float count){
    if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y && mouseY <= this.pos.y + this.dim.y){
      this.mouseScrolled(count);
    }
    for(Element e : this.elements){
      if(e.mousePressIsWithinBorder()){
        e.mouseWheel(count);
      }
    }
  }

  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
  }

  public void keyPressed(char k, int c){
    for(Element e : this.elements){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(Element e : this.elements){
      if(e.selected){
        e.keyTyped(k);
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(Element e : this.elements){
      if(e.selected){
        e.keyReleased();
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyReleased();
      }
    }
  }
}
// View_BabyCanInfo.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_BabyCanInfo extends ViewController {


  public View_BabyCanInfo(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    fill(0);
    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("BabyCan information", this.dim.x/2, 50);

    textFont(fonts.get("SF").get("Thin 15"));
    text("Bèta", this.dim.x/4, 80);

    textFont(fonts.get("SF").get("Bold"));
    textAlign(RIGHT);
    text("Boot state:", this.dim.x/4 - 5, 120);
    text("Battery:", this.dim.x/4 - 5, 140);
    text("Radio connection:", this.dim.x/4 - 5, 160);
    text("Flight-mode enabled:", this.dim.x/4 - 5, 180);
    text("GPS:", this.dim.x/4 - 5, 220);
    text("Measurements:", this.dim.x/4 - 5, 260);
    
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text((DataSetDeposit.beta_bootState == 0 ? "Not booting" : (DataSetDeposit.beta_bootState == 1 ? "Booting" : (DataSetDeposit.beta_bootState == 2 ? "Active" : "..."))), this.dim.x/4 + 5, 120);
    text(str(DataSetDeposit.beta_batteryVoltage) + " volts", this.dim.x/4 + 5, 140);

    text(str((float)DataSetDeposit.groundStation_RSSI), this.dim.x/4 + 5, 160);

    text((DataSetDeposit.beta_flightMode == 0 ? "Non-flight-mode" : (DataSetDeposit.beta_flightMode == 1 ? "Flight-mode" : "Landed-mode")), this.dim.x/4 + 5, 180);

    
    text((DataSetDeposit.beta_GPSFix ? "Fixed" : "Not fixed") + ", " + str(DataSetDeposit.beta_GPSSatellites) + " satellites", this.dim.x/4 + 5, 220);
    
    text(str(DataSetDeposit.beta_pointsMeasured) + " datapoints", this.dim.x/4 + 5, 260);

    stroke(0);
    strokeWeight(2);
    line(this.dim.x/2, 60, this.dim.x/2, 280);


    textFont(fonts.get("SF").get("Thin 15"));
    textAlign(CENTER);
    text("Rho", 3*this.dim.x/4, 80);

    textFont(fonts.get("SF").get("Bold"));
    textAlign(RIGHT);
    text("Boot state:", 3*this.dim.x/4 - 5, 120);
    text("Battery:", 3*this.dim.x/4 - 5, 140);
    text("Radio connection:", 3*this.dim.x/4 - 5, 160);
    text("Flight-mode enabled:", 3*this.dim.x/4 - 5, 180);
    text("GPS:", 3*this.dim.x/4 - 5, 220);
    text("Measurements:", 3*this.dim.x/4 - 5, 260);
    
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text((DataSetDeposit.rho_bootState == 0 ? "Not booting" : (DataSetDeposit.rho_bootState == 1 ? "Booting" : (DataSetDeposit.rho_bootState == 2 ? "Active" : "..."))), 3*this.dim.x/4 + 5, 120);
    text(str(DataSetDeposit.rho_batteryVoltage) + " volts", 3*this.dim.x/4 + 5, 140);

    text(str((float)DataSetDeposit.groundStation_RSSI), 3*this.dim.x/4 + 5, 160);

    text((DataSetDeposit.rho_flightMode == 0 ? "Non-flight-mode" : (DataSetDeposit.rho_flightMode == 1 ? "Flight-mode" : "Landed-mode")), 3*this.dim.x/4 + 5, 180);

    
    text((DataSetDeposit.rho_GPSFix ? "Fixed" : "Not fixed") + ", " + str(DataSetDeposit.rho_GPSSatellites) + " satellites", 3*this.dim.x/4 + 5, 220);
    
    text(str(DataSetDeposit.rho_pointsMeasured) + " datapoints", 3*this.dim.x/4 + 5, 260);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
// View_ControlButtons.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_ControlButtons extends ViewController {

  public ButtonElement openPinsButton;
  public ButtonElement closePinsButton;
  public ButtonElement openRingButton;
  public ButtonElement closeRingButton;

  public ButtonElement flightMode;
  public ButtonElement sendAllData;
  public ButtonElement clearData;

  public ButtonElement pushData;


  public View_ControlButtons(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.openPinsButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 - 80, 100, "Open pins"){
      public void clickEvent(){
        SerialController.send("[OPP]");
      }
    };
    this.closePinsButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 - 30, 100, "Close pins"){
      public void clickEvent(){
        SerialController.send("[CLP]");
      }
    };
    this.openRingButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 + 30, 100, "Open ring"){
      public void clickEvent(){
        SerialController.send("[OPR]");
      }
    };
    this.closeRingButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 + 80, 100, "Close ring"){
      public void clickEvent(){
        SerialController.send("[CLR]");
      }
    };

    this.flightMode = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2 - 80, 100, "Flight mode"){
      public void clickEvent(){
        SerialController.send("[FLIGHT_MODE]");
      }
    };

    this.sendAllData = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2, 100, "Send all data"){
      public void clickEvent(){
        SerialController.send("[SAD]");
      }
    };

    this.clearData = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2 + 50, 100, "Clear FRAM"){
      public void clickEvent(){
        SerialController.send("[CLF]");
      }
    };

    this.pushData = new ButtonElement(this.appController, this, this.dim.x/2, this.dim.y - 200, 100, "Push data"){
      public void clickEvent(){
        //DataDecoder.addData("{SGS:2;F:LOG,[GCS] Delta started on @RHchannel 2;}{SGR:2;}{SGF:2;F:LOG,[GCS] Frequency set to: 868.00MHz;}{SGP:2;}{CAN:3;SBT:1;}{CAN:2;RS:-42;}{CAN:3;SBT:2;SMR:2;SMF:2;SMB:2;SMC:0;SMS:2;SMG:2;SMR:2;SFM:0;SDP:0;F:LOG,ALTITUDE_CORRECTION@-62.07;}{CAN:3;F:LOG,Flightmode unlocked;}{CAN:2;RS:-41;}{CAN:2;RS:-27;}{CAN:2;RS:-21;}{CAN:2;RS:-12;}{CAN:2;RS:-22;}{CAN:2;RS:-15;}{CAN:2;RS:-21;}{CAN:2;RS:-23;}{CAN:2;RS:-13;}{CAN:2;RS:-36;}{CAN:2;RS:-37;}{CAN:2;RS:-13;}{CAN:2;RS:-44;}{CAN:2;RS:-43;}{CAN:2;RS:-12;}{CAN:2;RS:-56;}{CAN:2;RS:-55;}{CAN:2;RS:-13;}{CAN:2;RS:-22;}{CAN:2;RS:-15;}{CAN:2;RS:-21;}{CAN:2;RS:-35;}{CAN:2;RS:-13;}{CAN:2;RS:-27;}{CAN:2;RS:-28;}{CAN:2;RS:-13;}{CAN:2;RS:-49;}{CAN:2;RS:-51;}{CAN:2;RS:-13;}{CAN:2;RS:-27;}{CAN:2;RS:-22;}{CAN:2;RS:-16;}{CAN:2;RS:-28;}{CAN:2;RS:-16;}{CAN:2;RS:-22;}{CAN:2;RS:-58;}{CAN:2;RS:-61;}{CAN:3;SMU:1;}{CAN:2;RS:-65;}{CAN:3;GT:114042;TS:153;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100501.87;AT:25.8;AL:4.60;HM:37.38;AX:-1.69;AY:0.90;AZ:10.66;GX:-0.26;GY:0.31;GZ:0.32;CX:89.46;CY:-26.95;CZ:-90.81;OC:0.00;O2:0.00;BV:4.06;}{CAN:2;RS:-71;}{CAN:3;GT:115307;TS:1658;GA:51.9783;GO:4.5368;GH:0.00;GS:0.43;GV:33.41;G3:1;GN:0;AP:100499.17;AT:25.8;AL:4.83;HM:37.31;AX:-1.74;AY:1.12;AZ:9.80;GX:0.47;GY:0.38;GZ:0.08;CX:100.03;CY:-25.05;CZ:-91.98;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-63;}{CAN:2;RS:-69;}{CAN:3;GT:115309;TS:3171;GA:51.9783;GO:4.5368;GH:-4.20;GS:0.29;GV:67.23;G3:1;GN:9;AP:100493.41;AT:25.7;AL:5.31;HM:37.25;AX:-1.05;AY:1.15;AZ:8.87;GX:-0.56;GY:-0.02;GZ:-1.19;CX:94.31;CY:-18.66;CZ:-94.81;OC:0.00;O2:0.00;BV:4.05;}{CAN:3;GT:115311;TS:4689;GA:51.9783;GO:4.5368;GH:-4.20;GS:0.42;GV:55.22;G3:1;GN:9;AP:100487.09;AT:25.7;AL:5.84;HM:37.14;AX:-1.09;AY:0.67;AZ:8.22;GX:-0.17;GY:0.28;GZ:-0.51;CX:92.06;CY:-27.82;CZ:-87.64;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-65;}{CAN:3;GT:115312;TS:6202;GA:51.9783;GO:4.5368;GH:-4.30;GS:0.59;GV:99.94;G3:1;GN:9;AP:100478.59;AT:25.8;AL:6.56;HM:37.04;AX:-1.25;AY:0.85;AZ:9.29;GX:-0.27;GY:0.33;GZ:0.22;CX:98.82;CY:-30.76;CZ:-90.14;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-63;}{CAN:2;RS:-76;}{CAN:2;RS:-11;}{CAN:3;GT:115314;TS:7715;GA:51.9783;GO:4.5368;GH:-4.50;GS:0.43;GV:73.26;G3:1;GN:9;AP:100479.43;AT:25.7;AL:6.49;HM:36.96;AX:-1.36;AY:0.74;AZ:8.13;GX:-0.02;GY:1.12;GZ:-0.76;CX:87.55;CY:-34.38;CZ:-94.65;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-71;}{CAN:2;RS:-74;}{CAN:2;RS:-9;}{CAN:3;GT:115315;TS:9228;GA:51.9783;GO:4.5368;GH:-4.60;GS:1.05;GV:70.25;G3:1;GN:9;AP:100475.00;AT:25.7;AL:6.86;HM:36.87;AX:-0.99;AY:0.82;AZ:8.40;GX:0.19;GY:0.85;GZ:0.02;CX:84.60;CY:-20.04;CZ:-101.49;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-67;}{CAN:3;GT:115317;TS:10741;GA:51.9783;GO:4.5368;GH:-5.00;GS:1.16;GV:70.37;G3:1;GN:9;AP:100477.94;AT:25.7;AL:6.61;HM:36.76;AX:-1.00;AY:0.78;AZ:8.86;GX:-0.37;GY:-0.19;GZ:-1.07;CX:81.14;CY:-23.50;CZ:-100.15;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-57;}{CAN:2;RS:-77;}{CAN:3;GT:115318;TS:12259;GA:51.9783;GO:4.5368;GH:-5.20;GS:0.95;GV:97.87;G3:1;GN:9;AP:100469.97;AT:25.5;AL:7.28;HM:36.64;AX:-0.47;AY:0.60;AZ:10.12;GX:0.27;GY:-0.09;GZ:0.17;CX:85.82;CY:-37.15;CZ:-91.64;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-58;}{CAN:3;GT:115320;TS:13777;GA:51.9783;GO:4.5368;GH:-5.40;GS:0.17;GV:285.04;G3:1;GN:9;AP:100465.09;AT:25.5;AL:7.69;HM:36.55;AX:-1.02;AY:0.67;AZ:10.08;GX:0.97;GY:0.87;GZ:-0.10;CX:85.12;CY:-38.18;CZ:-89.97;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-60;}{CAN:3;GT:115321;TS:15295;GA:51.9783;GO:4.5368;GH:-5.40;GS:0.22;GV:190.44;G3:1;GN:9;AP:100460.19;AT:25.4;AL:8.10;HM:36.46;AX:-1.27;AY:0.60;AZ:10.05;GX:-0.30;GY:-0.32;GZ:-0.21;CX:75.24;CY:-36.28;CZ:-89.14;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-61;}{CAN:3;GT:115323;TS:16819;GA:51.9783;GO:4.5368;GH:-5.40;GS:0.69;GV:216.03;G3:1;GN:9;AP:100455.62;AT:25.3;AL:8.48;HM:36.42;AX:-0.97;AY:0.95;AZ:7.92;GX:0.29;GY:-0.03;GZ:-0.45;CX:76.45;CY:-46.13;CZ:-97.65;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-64;}{CAN:3;GT:115324;TS:18337;GA:51.9783;GO:4.5368;GH:-5.40;GS:0.46;GV:229.73;G3:1;GN:9;AP:100459.06;AT:25.3;AL:8.19;HM:36.42;AX:-1.17;AY:0.93;AZ:9.65;GX:0.85;GY:-1.21;GZ:-0.22;CX:113.73;CY:-38.70;CZ:-92.81;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-58;}{CAN:3;GT:115326;TS:19855;GA:51.9783;GO:4.5368;GH:-5.40;GS:1.71;GV:291.03;G3:1;GN:9;AP:100447.95;AT:25.4;AL:9.12;HM:36.40;AX:-0.79;AY:1.50;AZ:9.59;GX:0.04;GY:0.85;GZ:0.32;CX:99.51;CY:-35.94;CZ:-91.14;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-63;}{CAN:2;RS:-52;}{CAN:2;RS:-11;}{CAN:2;RS:-58;}{CAN:2;RS:-13;}{CAN:2;RS:-66;}{CAN:2;RS:-13;}{CAN:2;RS:-67;}{CAN:2;RS:-13;}{CAN:2;RS:-90;}{CAN:2;RS:-8;}{CAN:2;RS:-72;}{CAN:2;RS:-12;}{CAN:2;RS:-90;}{CAN:3;GT:115335;TS:28995;GA:51.9783;GO:4.5368;GH:-6.30;GS:1.33;GV:39.18;G3:1;GN:9;AP:100423.17;AT:25.5;AL:11.20;HM:36.36;AX:-0.82;AY:0.93;AZ:8.24;GX:-0.22;GY:0.51;GZ:-0.46;CX:89.46;CY:-21.42;CZ:-92.48;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-67;}{CAN:2;RS:-84;}{CAN:2;RS:-11;}{CAN:3;GT:115336;TS:30518;GA:51.9783;GO:4.5368;GH:-6.30;GS:1.08;GV:52.09;G3:1;GN:9;AP:100420.89;AT:25.5;AL:11.39;HM:36.35;AX:-0.85;AY:0.83;AZ:10.06;GX:0.63;GY:0.51;GZ:0.38;CX:98.65;CY:-17.80;CZ:-92.64;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-61;}{CAN:2;RS:-80;}{CAN:2;RS:-11;}{CAN:3;GT:115338;TS:32043;GA:51.9783;GO:4.5368;GH:-6.40;GS:0.50;GV:59.07;G3:1;GN:9;AP:100412.23;AT:25.6;AL:12.12;HM:36.36;AX:-0.93;AY:1.06;AZ:9.76;GX:0.21;GY:0.35;GZ:-0.42;CX:88.94;CY:-16.07;CZ:-89.64;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-65;}{CAN:2;RS:-81;}{CAN:3;GT:115339;TS:33566;GA:51.9783;GO:4.5368;GH:-6.30;GS:0.46;GV:68.99;G3:1;GN:9;AP:100409.81;AT:25.6;AL:12.32;HM:36.37;AX:-0.76;AY:0.76;AZ:7.27;GX:0.06;GY:0.70;GZ:-1.12;CX:83.22;CY:-23.15;CZ:-91.47;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-63;}{CAN:2;RS:-84;}{CAN:3;GT:115341;TS:35090;GA:51.9783;GO:4.5368;GH:-6.30;GS:0.81;GV:163.73;G3:1;GN:9;AP:100411.19;AT:25.6;AL:12.21;HM:36.39;AX:-1.06;AY:0.41;AZ:9.40;GX:1.40;GY:-1.51;GZ:0.53;CX:92.75;CY:-44.40;CZ:-88.30;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-64;}{CAN:2;RS:-80;}{CAN:2;RS:-10;}{CAN:3;GT:115342;TS:36614;GA:51.9783;GO:4.5368;GH:-6.20;GS:0.80;GV:178.99;G3:1;GN:9;AP:100413.14;AT:25.6;AL:12.04;HM:36.38;AX:-1.69;AY:0.83;AZ:9.84;GX:-0.48;GY:1.10;GZ:-0.97;CX:86.16;CY:-41.99;CZ:-84.63;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-71;}{CAN:2;RS:-12;}{CAN:3;GT:115344;TS:38139;GA:51.9783;GO:4.5368;GH:-6.10;GS:0.62;GV:184.83;G3:1;GN:9;AP:100405.31;AT:25.6;AL:12.70;HM:36.41;AX:-0.91;AY:1.25;AZ:10.00;GX:0.06;GY:-0.36;GZ:-0.07;CX:108.87;CY:-26.61;CZ:-85.80;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-73;}{CAN:2;RS:-86;}{CAN:2;RS:-12;}{CAN:3;GT:115346;TS:39668;GA:51.9783;GO:4.5368;GH:-5.80;GS:0.62;GV:201.59;G3:1;GN:9;AP:100398.19;AT:25.6;AL:13.30;HM:36.43;AX:-1.11;AY:0.94;AZ:9.63;GX:-0.90;GY:-1.22;GZ:0.41;CX:105.06;CY:-39.39;CZ:-89.81;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-65;}{CAN:2;RS:-99;}{CAN:3;GT:115347;TS:41198;GA:51.9783;GO:4.5368;GH:-5.50;GS:0.83;GV:217.00;G3:1;GN:9;AP:100392.44;AT:25.7;AL:13.78;HM:36.45;AX:-1.10;AY:0.55;AZ:8.00;GX:0.12;GY:-0.18;GZ:0.16;CX:103.67;CY:-34.56;CZ:-91.47;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-66;}{CAN:2;RS:-95;}{CAN:2;RS:-13;}{CAN:3;GT:115349;TS:42722;GA:51.9783;GO:4.5368;GH:-4.90;GS:0.49;GV:248.58;G3:1;GN:9;AP:100384.16;AT:25.7;AL:14.47;HM:36.47;AX:-1.12;AY:0.55;AZ:8.59;GX:-0.72;GY:1.32;GZ:-1.38;CX:98.13;CY:-35.25;CZ:-92.48;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-67;}{CAN:2;RS:-96;}{CAN:2;RS:-13;}{CAN:3;GT:115350;TS:44246;GA:51.9783;GO:4.5368;GH:-4.70;GS:0.17;GV:250.53;G3:1;GN:9;AP:100380.76;AT:25.7;AL:14.76;HM:36.48;AX:-1.04;AY:0.90;AZ:9.25;GX:0.74;GY:-1.16;GZ:0.59;CX:100.03;CY:-27.82;CZ:-88.97;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-65;}{CAN:2;RS:-105;}{CAN:2;RS:-13;}{CAN:3;SDP:2;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;GT:115352;TS:45770;GA:51.9783;GO:4.5368;GH:-4.10;GS:0.15;GV:329.41;G3:1;GN:9;AP:100372.12;AT:25.8;AL:15.48;HM:36.48;AX:-1.47;AY:0.48;AZ:10.00;GX:-0.96;GY:0.29;GZ:-0.51;CX:98.65;CY:-35.07;CZ:-79.62;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:2;RS:-92;}{CAN:2;RS:-11;}{CAN:2;RS:-83;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-73;}{CAN:2;RS:-9;}{CAN:2;RS:-73;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-67;}{CAN:2;RS:-8;}{CAN:2;RS:-67;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-71;}{CAN:2;RS:-9;}{CAN:2;RS:-72;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;GT:115359;TS:53397;GA:51.9783;GO:4.5368;GH:-1.90;GS:0.79;GV:270.34;G3:1;GN:9;AP:100360.99;AT:25.8;AL:16.42;HM:36.46;AX:-1.19;AY:0.97;AZ:9.00;GX:-0.08;GY:-0.49;GZ:0.17;CX:82.87;CY:-39.05;CZ:-94.15;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115401;TS:54922;GA:51.9783;GO:4.5368;GH:-1.30;GS:0.29;GV:262.48;G3:1;GN:9;AP:100365.45;AT:25.9;AL:16.04;HM:36.45;AX:-0.84;AY:0.98;AZ:9.44;GX:0.07;GY:-0.10;GZ:-0.09;CX:83.39;CY:-37.49;CZ:-94.31;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;GT:115402;TS:56447;GA:51.9783;GO:4.5368;GH:-1.10;GS:0.11;GV:327.79;G3:1;GN:9;AP:100366.83;AT:25.9;AL:15.93;HM:36.43;AX:-1.19;AY:0.97;AZ:9.34;GX:0.31;GY:0.50;GZ:-0.64;CX:88.76;CY:-45.27;CZ:-90.14;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:2;RS:-9;}{CAN:2;RS:-71;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:2;RS:-8;}{CAN:2;RS:-72;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115407;TS:61022;GA:51.9783;GO:4.5368;GH:-0.90;GS:0.07;GV:239.09;G3:1;GN:9;AP:100370.59;AT:26.0;AL:15.61;HM:36.36;AX:-0.97;AY:1.08;AZ:8.85;GX:-0.15;GY:0.08;GZ:0.05;CX:88.42;CY:-45.61;CZ:-89.81;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-10;}{CAN:2;RS:-75;}{CAN:2;RS:-12;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-72;}{CAN:2;RS:-9;}{CAN:2;RS:-72;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:3;GT:115412;TS:65591;GA:51.9783;GO:4.5368;GH:-0.80;GS:0.05;GV:37.21;G3:1;GN:9;AP:100368.95;AT:26.1;AL:15.75;HM:36.20;AX:-0.87;AY:0.78;AZ:8.84;GX:-0.13;GY:0.05;GZ:-0.17;CX:88.94;CY:-45.79;CZ:-92.31;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115413;TS:67114;GA:51.9783;GO:4.5368;GH:-0.70;GS:0.10;GV:60.53;G3:1;GN:9;AP:100365.78;AT:26.2;AL:16.02;HM:36.16;AX:-0.86;AY:0.56;AZ:8.53;GX:0.10;GY:-0.13;GZ:0.32;CX:84.95;CY:-49.76;CZ:-101.16;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;GT:115415;TS:68637;GA:51.9783;GO:4.5368;GH:-0.40;GS:0.47;GV:115.20;G3:1;GN:9;AP:100362.64;AT:25.9;AL:16.28;HM:36.05;AX:-0.99;AY:0.95;AZ:9.14;GX:0.02;GY:-0.03;GZ:1.30;CX:93.62;CY:-45.61;CZ:-95.15;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;GT:115416;TS:70160;GA:51.9783;GO:4.5368;GH:-0.30;GS:0.73;GV:77.82;G3:1;GN:9;AP:100364.11;AT:25.9;AL:16.16;HM:35.99;AX:-1.37;AY:0.49;AZ:10.12;GX:0.32;GY:-0.44;GZ:-0.74;CX:94.48;CY:-22.98;CZ:-95.98;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-66;}{CAN:2;RS:-11;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;GT:115418;TS:71685;GA:51.9783;GO:4.5368;GH:0.00;GS:0.94;GV:77.49;G3:1;GN:9;AP:100367.96;AT:25.9;AL:15.83;HM:35.95;AX:-2.35;AY:0.61;AZ:9.65;GX:-0.55;GY:-1.26;GZ:1.97;CX:98.13;CY:-25.92;CZ:-91.14;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;GT:115419;TS:73208;GA:51.9783;GO:4.5368;GH:0.40;GS:0.78;GV:83.81;G3:1;GN:9;AP:100363.84;AT:25.8;AL:16.18;HM:35.91;AX:-1.32;AY:1.04;AZ:10.07;GX:-0.14;GY:0.00;GZ:0.25;CX:89.28;CY:-49.59;CZ:-87.64;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:2;RS:-7;}{CAN:2;RS:-64;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:115422;TS:76254;GA:51.9783;GO:4.5368;GH:0.90;GS:0.85;GV:205.62;G3:1;GN:9;AP:100355.02;AT:25.8;AL:16.92;HM:35.87;AX:-1.21;AY:0.80;AZ:8.44;GX:-0.41;GY:0.58;GZ:0.70;CX:79.23;CY:-37.15;CZ:-85.30;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115424;TS:77777;GA:51.9783;GO:4.5368;GH:1.20;GS:1.19;GV:199.58;G3:1;GN:9;AP:100351.40;AT:25.9;AL:17.22;HM:35.87;AX:-1.29;AY:0.49;AZ:10.10;GX:0.14;GY:-0.98;GZ:-3.03;CX:85.12;CY:-42.33;CZ:-91.64;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:2;RS:-68;}{CAN:2;RS:-8;}{CAN:2;RS:-66;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115427;TS:80827;GA:51.9783;GO:4.5368;GH:1.90;GS:0.89;GV:157.19;G3:1;GN:8;AP:100351.30;AT:26.0;AL:17.23;HM:35.88;AX:-1.21;AY:0.90;AZ:9.36;GX:-0.46;GY:0.13;GZ:1.13;CX:83.22;CY:-67.04;CZ:-88.47;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;GT:115428;TS:82350;GA:51.9783;GO:4.5368;GH:2.30;GS:0.48;GV:150.61;G3:1;GN:7;AP:100354.38;AT:26.0;AL:16.97;HM:35.88;AX:-1.17;AY:0.79;AZ:8.94;GX:0.48;GY:-0.49;GZ:-0.21;CX:69.00;CY:-54.94;CZ:-94.15;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;GT:115430;TS:83875;GA:51.9783;GO:4.5368;GH:3.10;GS:0.16;GV:187.27;G3:1;GN:8;AP:100359.66;AT:26.1;AL:16.53;HM:35.87;AX:-0.94;AY:0.89;AZ:9.07;GX:0.32;GY:-0.13;GZ:0.16;CX:88.07;CY:-63.24;CZ:-94.15;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:2;RS:-70;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;GT:115431;TS:85398;GA:51.9783;GO:4.5368;GH:3.40;GS:0.35;GV:212.12;G3:1;GN:8;AP:100354.15;AT:26.2;AL:16.99;HM:35.86;AX:-1.11;AY:0.84;AZ:9.06;GX:-0.33;GY:0.52;GZ:-0.15;CX:76.97;CY:-62.55;CZ:-92.48;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:2;RS:-74;}{CAN:2;RS:-15;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;GT:115433;TS:86921;GA:51.9783;GO:4.5368;GH:4.00;GS:0.20;GV:250.87;G3:1;GN:8;AP:100350.48;AT:26.2;AL:17.30;HM:35.85;AX:-1.12;AY:0.99;AZ:9.00;GX:-0.15;GY:0.13;GZ:-0.15;CX:78.19;CY:-63.76;CZ:-90.31;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;GT:115434;TS:88444;GA:51.9783;GO:4.5368;GH:4.50;GS:0.21;GV:305.58;G3:1;GN:8;AP:100352.64;AT:26.3;AL:17.12;HM:35.82;AX:-1.02;AY:0.76;AZ:9.13;GX:0.30;GY:-0.34;GZ:0.22;CX:79.92;CY:-63.41;CZ:-93.98;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;GT:115436;TS:89968;GA:51.9783;GO:4.5367;GH:5.30;GS:0.18;GV:298.30;G3:1;GN:8;AP:100351.62;AT:26.4;AL:17.20;HM:35.82;AX:-0.96;AY:1.07;AZ:9.32;GX:0.00;GY:-0.03;GZ:-0.03;CX:83.56;CY:-62.55;CZ:-95.48;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-74;}{CAN:2;RS:-9;}{CAN:2;RS:-69;}{CAN:2;RS:-11;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:2;RS:-68;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;GT:115440;TS:94541;GA:51.9783;GO:4.5367;GH:7.00;GS:0.25;GV:305.70;G3:1;GN:8;AP:100352.45;AT:26.6;AL:17.14;HM:35.71;AX:-1.09;AY:0.90;AZ:9.15;GX:0.36;GY:-0.15;GZ:0.11;CX:73.85;CY:-59.78;CZ:-95.15;OC:0.00;O2:0.00;BV:4.05;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-68;}{CAN:2;RS:-8;}{CAN:2;RS:-64;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:2;RS:-10;}{CAN:2;RS:-67;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:115445;TS:99107;GA:51.9783;GO:4.5368;GH:8.60;GS:0.80;GV:348.29;G3:1;GN:9;AP:100336.86;AT:26.6;AL:18.44;HM:35.51;AX:-0.66;AY:1.52;AZ:9.71;GX:-1.31;GY:0.17;GZ:1.49;CX:105.75;CY:-42.16;CZ:-89.14;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:2;RS:-60;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:2;RS:-69;}{CAN:2;RS:-9;}{CAN:2;RS:-75;}{CAN:2;RS:-15;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:2;RS:-9;}{CAN:2;RS:-72;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;GT:115450;TS:103685;GA:51.9783;GO:4.5368;GH:10.30;GS:0.18;GV:225.40;G3:1;GN:9;AP:100315.09;AT:26.7;AL:20.27;HM:35.36;AX:-1.16;AY:1.25;AZ:10.15;GX:-0.87;GY:-0.50;GZ:-0.44;CX:109.57;CY:-46.65;CZ:-92.81;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:2;RS:-83;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:2;RS:-84;}{CAN:2;RS:-10;}{CAN:2;RS:-79;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:115453;TS:106745;GA:51.9783;GO:4.5368;GH:11.20;GS:0.58;GV:30.85;G3:1;GN:9;AP:100306.14;AT:26.7;AL:21.02;HM:35.29;AX:-0.78;AY:0.82;AZ:9.06;GX:0.42;GY:-0.62;GZ:0.52;CX:120.66;CY:-27.64;CZ:-99.15;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-109;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;GT:115454;TS:108270;GA:51.9783;GO:4.5368;GH:11.50;GS:0.46;GV:14.33;G3:1;GN:9;AP:100299.92;AT:26.7;AL:21.54;HM:35.23;AX:-0.59;AY:1.05;AZ:8.00;GX:0.06;GY:0.30;GZ:-0.24;CX:112.86;CY:-34.38;CZ:-103.99;OC:0.00;O2:0.00;BV:4.03;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:2;RS:-82;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-98;}{CAN:3;GT:115456;TS:109800;GA:51.9783;GO:4.5368;GH:11.90;GS:0.74;GV:52.64;G3:1;GN:9;AP:100302.83;AT:26.8;AL:21.30;HM:35.14;AX:-1.12;AY:0.71;AZ:8.32;GX:0.12;GY:0.43;GZ:-1.42;CX:98.13;CY:-25.23;CZ:-104.49;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;GT:115457;TS:111325;GA:51.9783;GO:4.5368;GH:12.20;GS:1.05;GV:82.40;G3:1;GN:9;AP:100303.91;AT:26.7;AL:21.21;HM:35.04;AX:-0.99;AY:0.70;AZ:8.96;GX:0.50;GY:-0.02;GZ:-0.11;CX:96.57;CY:-30.93;CZ:-102.66;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;GT:115459;TS:112854;GA:51.9783;GO:4.5368;GH:12.70;GS:1.68;GV:121.61;G3:1;GN:9;AP:100304.34;AT:26.7;AL:21.17;HM:34.96;AX:-1.68;AY:0.20;AZ:10.11;GX:-0.14;GY:-0.42;GZ:-0.29;CX:82.18;CY:-44.92;CZ:-104.49;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-76;}{CAN:2;RS:-77;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:115500;TS:114384;GA:51.9783;GO:4.5368;GH:13.00;GS:1.80;GV:157.73;G3:1;GN:9;AP:100305.28;AT:26.5;AL:21.09;HM:34.84;AX:-0.97;AY:0.91;AZ:9.71;GX:0.32;GY:0.65;GZ:-0.36;CX:97.43;CY:-44.92;CZ:-110.84;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;GT:115502;TS:115914;GA:51.9783;GO:4.5368;GH:13.50;GS:1.92;GV:189.14;G3:1;GN:9;AP:100307.72;AT:26.5;AL:20.89;HM:34.75;AX:-1.11;AY:0.84;AZ:8.65;GX:0.06;GY:-0.04;GZ:0.08;CX:52.36;CY:-61.86;CZ:-129.53;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;GT:115503;TS:117443;GA:51.9783;GO:4.5368;GH:13.70;GS:0.54;GV:168.80;G3:1;GN:9;AP:100303.82;AT:26.5;AL:21.22;HM:34.69;AX:-1.01;AY:0.69;AZ:9.56;GX:0.05;GY:0.44;GZ:-0.10;CX:63.45;CY:-56.67;CZ:-110.17;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-67;}{CAN:2;RS:-66;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:115505;TS:118973;GA:51.9783;GO:4.5368;GH:14.10;GS:0.22;GV:180.08;G3:1;GN:9;AP:100299.66;AT:26.6;AL:21.57;HM:34.65;AX:-1.20;AY:0.87;AZ:9.27;GX:-0.25;GY:-0.21;GZ:-0.73;CX:71.95;CY:-62.72;CZ:-107.67;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;GT:115506;TS:120503;GA:51.9783;GO:4.5368;GH:14.20;GS:0.46;GV:229.73;G3:1;GN:9;AP:100299.61;AT:26.7;AL:21.57;HM:34.62;AX:-1.15;AY:0.93;AZ:9.71;GX:-0.25;GY:0.44;GZ:-0.29;CX:91.36;CY:-75.16;CZ:-101.32;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;GT:115508;TS:122033;GA:51.9783;GO:4.5368;GH:14.50;GS:0.36;GV:221.42;G3:1;GN:9;AP:100305.95;AT:26.8;AL:21.04;HM:34.55;AX:-1.09;AY:0.77;AZ:9.66;GX:0.36;GY:0.17;GZ:0.68;CX:77.50;CY:-64.79;CZ:-104.33;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:2;RS:-68;}{CAN:2;RS:-8;}{CAN:2;RS:-68;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-8;}{CAN:2;RS:-67;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:2;RS:-29;}{CAN:2;RS:-77;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:2;RS:-69;}{CAN:2;RS:-10;}{CAN:3;GT:115514;TS:128140;GA:51.9783;GO:4.5368;GH:14.80;GS:0.04;GV:242.48;G3:1;GN:9;AP:100319.05;AT:27.0;AL:19.94;HM:34.33;AX:-1.13;AY:0.98;AZ:9.65;GX:-0.02;GY:-0.04;GZ:-1.07;CX:69.35;CY:-54.94;CZ:-102.49;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:2;RS:-70;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:2;RS:-65;}{CAN:2;RS:-8;}{CAN:2;RS:-65;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:2;RS:-63;}{CAN:2;RS:-11;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;GT:115519;TS:132724;GA:51.9783;GO:4.5368;GH:15.10;GS:0.23;GV:344.71;G3:1;GN:9;AP:100324.19;AT:27.1;AL:19.51;HM:34.12;AX:-1.10;AY:1.04;AZ:8.39;GX:-0.14;GY:-0.04;GZ:0.31;CX:98.47;CY:-66.00;CZ:-91.81;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;GT:115520;TS:134253;GA:51.9783;GO:4.5368;GH:15.20;GS:0.21;GV:358.60;G3:1;GN:9;AP:100322.05;AT:27.2;AL:19.69;HM:34.06;AX:-1.03;AY:0.58;AZ:7.93;GX:0.13;GY:-0.13;GZ:0.17;CX:88.59;CY:-61.34;CZ:-90.31;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:115522;TS:135777;GA:51.9783;GO:4.5368;GH:15.20;GS:0.14;GV:58.50;G3:1;GN:9;AP:100325.77;AT:27.1;AL:19.37;HM:33.97;AX:-0.89;AY:0.72;AZ:7.11;GX:-0.34;GY:0.34;GZ:0.12;CX:83.74;CY:-52.70;CZ:-93.64;OC:0.00;O2:0.00;BV:4.03;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-73;}{CAN:2;RS:-11;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:115523;TS:137301;GA:51.9783;GO:4.5368;GH:15.30;GS:0.08;GV:69.89;G3:1;GN:9;AP:100328.77;AT:27.1;AL:19.12;HM:33.88;AX:-0.79;AY:0.83;AZ:8.53;GX:-0.04;GY:-0.17;GZ:0.33;CX:71.60;CY:-44.75;CZ:-98.65;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:2;RS:-12;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;GT:115525;TS:138826;GA:51.9783;GO:4.5368;GH:15.60;GS:0.16;GV:127.57;G3:1;GN:9;AP:100326.41;AT:27.0;AL:19.32;HM:33.80;AX:-0.90;AY:0.67;AZ:9.20;GX:-0.10;GY:0.06;GZ:0.00;CX:73.51;CY:-38.36;CZ:-101.82;OC:0.00;O2:0.00;BV:4.04;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:115526;TS:140355;GA:51.9783;GO:4.5368;GH:15.70;GS:0.07;GV:158.30;G3:1;GN:9;AP:100332.83;AT:27.0;AL:18.78;HM:33.71;AX:-0.55;AY:1.12;AZ:8.63;GX:-1.02;GY:0.53;GZ:-0.44;CX:72.29;CY:-35.77;CZ:-101.66;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;GT:115528;TS:141885;GA:51.9783;GO:4.5368;GH:15.90;GS:0.03;GV:213.18;G3:1;GN:9;AP:100338.10;AT:26.9;AL:18.34;HM:33.68;AX:-1.06;AY:1.24;AZ:11.23;GX:0.32;GY:-0.50;GZ:0.51;CX:73.85;CY:-32.14;CZ:-106.16;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;GT:115529;TS:143415;GA:51.9783;GO:4.5368;GH:16.00;GS:0.38;GV:224.45;G3:1;GN:9;AP:100349.37;AT:26.9;AL:17.39;HM:33.62;AX:-1.03;AY:1.13;AZ:9.09;GX:0.06;GY:-0.26;GZ:0.15;CX:68.31;CY:-22.12;CZ:-102.16;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:115531;TS:144945;GA:51.9783;GO:4.5368;GH:15.90;GS:0.85;GV:245.19;G3:1;GN:10;AP:100358.70;AT:27.0;AL:16.61;HM:33.65;AX:-0.75;AY:0.49;AZ:7.22;GX:0.20;GY:-0.15;GZ:0.61;CX:79.40;CY:-14.51;CZ:-97.15;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:115532;TS:146475;GA:51.9783;GO:4.5368;GH:16.00;GS:0.18;GV:258.86;G3:1;GN:10;AP:100364.45;AT:27.1;AL:16.13;HM:33.66;AX:-1.28;AY:0.59;AZ:9.72;GX:0.08;GY:-0.29;GZ:0.94;CX:101.77;CY:-9.85;CZ:-95.65;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:115534;TS:148004;GA:51.9783;GO:4.5368;GH:16.10;GS:0.67;GV:195.39;G3:1;GN:10;AP:100367.56;AT:27.1;AL:15.87;HM:33.64;AX:-0.97;AY:1.13;AZ:10.51;GX:-0.20;GY:0.56;GZ:1.36;CX:117.89;CY:-36.63;CZ:-92.81;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:115536;TS:149534;GA:51.9783;GO:4.5368;GH:16.30;GS:0.64;GV:210.88;G3:1;GN:10;AP:100375.41;AT:27.0;AL:15.21;HM:33.62;AX:-0.98;AY:0.67;AZ:8.11;GX:-0.50;GY:0.16;GZ:2.41;CX:76.11;CY:-30.24;CZ:-99.99;OC:0.00;O2:0.00;BV:4.03;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;GT:115537;TS:151064;GA:51.9783;GO:4.5368;GH:14.90;GS:1.78;GV:55.44;G3:1;GN:10;AP:100401.83;AT:27.0;AL:12.99;HM:33.58;AX:-0.94;AY:0.32;AZ:8.16;GX:0.10;GY:0.56;GZ:2.96;CX:105.58;CY:-45.44;CZ:-95.98;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:2;RS:-63;}{CAN:2;RS:-8;}{CAN:2;RS:-59;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:2;RS:-72;}{CAN:2;RS:-8;}{CAN:2;RS:-66;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:2;RS:-7;}{CAN:2;RS:-58;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-63;}{CAN:2;RS:-13;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;GT:115545;TS:158707;GA:51.9783;GO:4.5367;GH:13.10;GS:0.66;GV:241.79;G3:1;GN:10;AP:100495.71;AT:27.0;AL:5.12;HM:33.43;AX:-1.07;AY:0.83;AZ:8.21;GX:-0.56;GY:0.36;GZ:4.35;CX:100.73;CY:-42.33;CZ:-108.00;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;GT:115546;TS:160237;GA:51.9783;GO:4.5367;GH:12.70;GS:0.62;GV:234.56;G3:1;GN:10;AP:100512.55;AT:27.0;AL:3.71;HM:33.43;AX:-0.91;AY:0.43;AZ:8.23;GX:-0.16;GY:0.91;GZ:4.35;CX:88.42;CY:-15.20;CZ:-104.83;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;GT:115548;TS:161766;GA:51.9783;GO:4.5367;GH:11.60;GS:0.99;GV:215.22;G3:1;GN:10;AP:100543.77;AT:26.7;AL:1.09;HM:33.44;AX:0.57;AY:9.97;AZ:14.76;GX:1.81;GY:0.70;GZ:2.63;CX:97.26;CY:-26.09;CZ:-110.34;OC:0.00;O2:0.00;BV:4.03;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;GT:115549;TS:163291;GA:51.9782;GO:4.5367;GH:11.30;GS:0.85;GV:205.73;G3:1;GN:10;AP:100547.51;AT:26.9;AL:0.78;HM:33.55;AX:0.44;AY:6.79;AZ:7.14;GX:-0.45;GY:-0.13;GZ:-0.21;CX:95.53;CY:-67.90;CZ:-95.98;OC:0.00;O2:0.00;BV:4.01;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;GT:115551;TS:164827;GA:51.9782;GO:4.5367;GH:10.50;GS:0.35;GV:133.33;G3:1;GN:10;AP:100546.41;AT:27.0;AL:0.87;HM:33.65;AX:1.63;AY:5.01;AZ:8.36;GX:-0.59;GY:-0.39;GZ:0.38;CX:95.53;CY:-50.28;CZ:-109.00;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;GT:115552;TS:166357;GA:51.9782;GO:4.5367;GH:10.40;GS:0.04;GV:195.14;G3:1;GN:10;AP:100545.67;AT:27.0;AL:0.93;HM:33.79;AX:-0.49;AY:4.18;AZ:8.50;GX:0.30;GY:0.16;GZ:0.26;CX:103.50;CY:-51.66;CZ:-106.33;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;GT:115554;TS:167887;GA:51.9783;GO:4.5367;GH:10.20;GS:0.37;GV:339.49;G3:1;GN:10;AP:100544.70;AT:27.0;AL:1.02;HM:33.92;AX:0.11;AY:4.03;AZ:7.14;GX:-0.03;GY:-0.04;GZ:0.12;CX:100.03;CY:-53.04;CZ:-102.66;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;GT:115555;TS:169423;GA:51.9783;GO:4.5367;GH:10.00;GS:0.69;GV:35.64;G3:1;GN:10;AP:100541.77;AT:27.0;AL:1.26;HM:33.97;AX:0.55;AY:-1.04;AZ:9.87;GX:1.45;GY:-0.32;GZ:-0.05;CX:100.21;CY:-28.68;CZ:-111.17;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-61;}{CAN:2;RS:-58;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;GT:115557;TS:170959;GA:51.9783;GO:4.5368;GH:9.60;GS:0.25;GV:353.72;G3:1;GN:10;AP:100542.50;AT:27.0;AL:1.20;HM:34.04;AX:-1.89;AY:3.09;AZ:8.44;GX:-0.15;GY:-0.34;GZ:-0.37;CX:110.26;CY:-47.00;CZ:-107.17;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:2;RS:-69;}{CAN:2;RS:-79;}{CAN:2;RS:-74;}{CAN:2;RS:-11;}{CAN:2;RS:-81;}{CAN:2;RS:-9;}{CAN:2;RS:-84;}{CAN:2;RS:-10;}{CAN:2;RS:-86;}{CAN:2;RS:-12;}{CAN:2;RS:-98;}{CAN:2;RS:-88;}{CAN:2;RS:-13;}{CAN:2;RS:-102;}{CAN:2;RS:-13;}{CAN:2;RS:-89;}{CAN:2;RS:-13;}{CAN:2;RS:-101;}{CAN:2;RS:-13;}{CAN:2;RS:-93;}{CAN:2;RS:-13;}{CAN:2;RS:-117;}{CAN:2;RS:-15;}{CAN:2;RS:-87;}{CAN:2;RS:-13;}{CAN:2;RS:-99;}{CAN:2;RS:-13;}{CAN:2;RS:-75;}{CAN:2;RS:-11;}{CAN:2;RS:-77;}{CAN:2;RS:-13;}{CAN:2;RS:-97;}{CAN:2;RS:-13;}{CAN:2;RS:-88;}{CAN:2;RS:-13;}{CAN:2;RS:-98;}{CAN:2;RS:-13;}{CAN:2;RS:-86;}{CAN:2;RS:-13;}{CAN:2;RS:-84;}{CAN:2;RS:-13;}{CAN:2;RS:-80;}{CAN:2;RS:-13;}{CAN:2;RS:-89;}{CAN:2;RS:-13;}{CAN:2;RS:-76;}{CAN:2;RS:-13;}{CAN:2;RS:-92;}{CAN:2;RS:-13;}{CAN:2;RS:-87;}{CAN:2;RS:-13;}{CAN:2;RS:-67;}{CAN:2;RS:-13;}{CAN:2;RS:-83;}{CAN:2;RS:-13;}{CAN:2;RS:-87;}{CAN:2;RS:-13;}{CAN:2;RS:-82;}{CAN:2;RS:-13;}{CAN:2;RS:-86;}{CAN:2;RS:-12;}{CAN:2;RS:-83;}{CAN:2;RS:-13;}{CAN:2;RS:-83;}{CAN:2;RS:-13;}{CAN:2;RS:-90;}{CAN:2;RS:-12;}{CAN:2;RS:-89;}{CAN:2;RS:-13;}{CAN:2;RS:-84;}{CAN:2;RS:-13;}{CAN:2;RS:-90;}{CAN:2;RS:-13;}{CAN:2;RS:-79;}{CAN:2;RS:-12;}{CAN:2;RS:-91;}{CAN:2;RS:-13;}{CAN:2;RS:-82;}{CAN:2;RS:-13;}{CAN:2;RS:-92;}{CAN:2;RS:-13;}{CAN:2;RS:-80;}{CAN:2;RS:-13;}{CAN:2;RS:-90;}{CAN:2;RS:-13;}{CAN:2;RS:-80;}{CAN:2;RS:-13;}{CAN:2;RS:-92;}{CAN:2;RS:-13;}{CAN:2;RS:-81;}{CAN:2;RS:-13;}{CAN:2;RS:-76;}{CAN:2;RS:-12;}{CAN:2;RS:-87;}{CAN:2;RS:-12;}{CAN:2;RS:-76;}{CAN:2;RS:-13;}{CAN:2;RS:-93;}{CAN:2;RS:-12;}{CAN:2;RS:-74;}{CAN:2;RS:-91;}{CAN:2;RS:-73;}{CAN:2;RS:-103;}{CAN:2;RS:-12;}{CAN:2;RS:-76;}{CAN:2;RS:-13;}{CAN:2;RS:-96;}{CAN:2;RS:-13;}{CAN:2;RS:-88;}{CAN:2;RS:-13;}{CAN:2;RS:-86;}{CAN:2;RS:-87;}{CAN:2;RS:-12;}{CAN:2;RS:-88;}{CAN:2;RS:-86;}{CAN:2;RS:-13;}{CAN:2;RS:-87;}{CAN:2;RS:-13;}{CAN:2;RS:-74;}{CAN:2;RS:-12;}{CAN:2;RS:-89;}{CAN:2;RS:-13;}{CAN:2;RS:-75;}{CAN:2;RS:-12;}{CAN:2;RS:-85;}{CAN:2;RS:-13;}{CAN:2;RS:-59;}{CAN:2;RS:-13;}{CAN:2;RS:-88;}{CAN:2;RS:-13;}{CAN:2;RS:-68;}{CAN:2;RS:-13;}{CAN:2;RS:-86;}{CAN:2;RS:-13;}{CAN:2;RS:-75;}{CAN:2;RS:-13;}{CAN:2;RS:-90;}{CAN:2;RS:-13;}{CAN:2;RS:-71;}{CAN:2;RS:-12;}{CAN:2;RS:-109;}{CAN:2;RS:-13;}{CAN:2;RS:-76;}{CAN:2;RS:-13;}{CAN:2;RS:-89;}{CAN:2;RS:-81;}{CAN:2;RS:-13;}{CAN:2;RS:-89;}{CAN:2;RS:-13;}{CAN:2;RS:-75;}{CAN:2;RS:-13;}{CAN:2;RS:-96;}{CAN:2;RS:-13;}{CAN:2;RS:-76;}{CAN:2;RS:-11;}{CAN:2;RS:-89;}{CAN:2;RS:-9;}{CAN:2;RS:-87;}{CAN:2;RS:-11;}{CAN:2;RS:-95;}{CAN:2;RS:-11;}{CAN:2;RS:-79;}{CAN:2;RS:-13;}{CAN:2;RS:-87;}{CAN:2;RS:-49;}{CAN:2;RS:-76;}{CAN:2;RS:-47;}{CAN:2;RS:-90;}{CAN:2;RS:-13;}{CAN:2;RS:-84;}{CAN:2;RS:-13;}{CAN:2;RS:-91;}{CAN:2;RS:-13;}{CAN:2;RS:-95;}{CAN:2;RS:-13;}{CAN:2;RS:-99;}{CAN:2;RS:-11;}{CAN:2;RS:-95;}{CAN:2;RS:-11;}{CAN:2;RS:-88;}{CAN:2;RS:-11;}{CAN:2;RS:-97;}{CAN:2;RS:-10;}{CAN:2;RS:-83;}{CAN:2;RS:-44;}{CAN:2;RS:-85;}{CAN:2;RS:-4;}{CAN:2;RS:-83;}{CAN:2;RS:-49;}{CAN:2;RS:-81;}{CAN:2;RS:-87;}{CAN:2;RS:-17;}{CAN:2;RS:-90;}{CAN:2;RS:-7;}{CAN:2;RS:-86;}{CAN:2;RS:1;}{CAN:2;RS:-84;}{CAN:2;RS:-10;}{CAN:2;RS:-82;}{CAN:2;RS:-45;}{CAN:2;RS:-81;}{CAN:2;RS:-47;}{CAN:2;RS:-72;}{CAN:2;RS:-47;}{CAN:2;RS:-72;}{CAN:2;RS:-45;}{CAN:2;RS:-44;}{CAN:2;RS:-69;}{CAN:2;RS:-44;}{CAN:2;RS:-77;}{CAN:2;RS:-48;}{CAN:2;RS:-43;}{CAN:2;RS:-77;}{CAN:2;RS:-47;}{CAN:2;RS:-75;}{CAN:2;RS:-77;}{CAN:2;RS:-45;}{CAN:2;RS:-75;}{CAN:2;RS:-77;}{CAN:2;RS:-45;}{CAN:2;RS:-69;}{CAN:2;RS:-45;}{CAN:2;RS:-73;}{CAN:2;RS:-45;}{CAN:2;RS:-77;}{CAN:2;RS:-43;}{CAN:2;RS:-85;}{CAN:2;RS:-44;}{CAN:2;RS:-76;}{CAN:2;RS:-44;}{CAN:2;RS:-68;}{CAN:2;RS:-44;}{CAN:2;RS:-61;}{CAN:2;RS:-44;}{CAN:2;RS:-58;}{CAN:2;RS:-43;}{CAN:2;RS:-58;}{CAN:2;RS:-45;}{CAN:2;RS:-84;}{CAN:2;RS:-45;}{CAN:2;RS:-84;}{CAN:2;RS:-88;}{CAN:2;RS:-43;}{CAN:2;RS:-83;}{CAN:2;RS:-43;}{CAN:3;SBT:1;}{CAN:2;RS:-64;}{CAN:2;RS:-88;}{CAN:2;RS:-43;}{CAN:2;RS:-99;}{CAN:2;RS:-92;}{CAN:2;RS:-44;}{CAN:2;RS:-100;}{CAN:2;RS:-43;}{CAN:2;RS:-52;}{CAN:2;RS:-43;}{CAN:2;RS:-88;}{CAN:2;RS:-45;}{CAN:2;RS:-81;}{CAN:2;RS:-45;}{CAN:2;RS:-82;}{CAN:2;RS:-45;}{CAN:2;RS:-77;}{CAN:2;RS:-45;}{CAN:2;RS:-89;}{CAN:2;RS:-45;}{CAN:2;RS:-80;}{CAN:2;RS:-21;}{CAN:2;RS:-89;}{CAN:2;RS:-20;}{CAN:2;RS:-71;}{CAN:2;RS:-20;}{CAN:2;RS:-82;}{CAN:2;RS:-19;}{CAN:2;RS:-90;}{CAN:2;RS:-20;}{CAN:2;RS:-84;}{CAN:2;RS:-20;}{CAN:2;RS:-72;}{CAN:2;RS:-19;}{CAN:2;RS:-79;}{CAN:2;RS:-20;}{CAN:2;RS:-76;}{CAN:2;RS:-19;}{CAN:2;RS:-87;}{CAN:2;RS:-74;}{CAN:2;RS:-36;}{CAN:2;RS:-16;}{CAN:2;RS:-77;}{CAN:2;RS:-20;}{CAN:2;RS:-76;}{CAN:2;RS:-19;}{CAN:2;RS:-45;}{CAN:2;RS:-19;}{CAN:2;RS:-70;}{CAN:2;RS:-19;}{CAN:2;RS:-100;}{CAN:2;RS:-19;}{CAN:2;RS:-55;}{CAN:2;RS:-19;}{CAN:2;RS:-98;}{CAN:2;RS:-19;}{CAN:2;RS:-45;}{CAN:2;RS:-19;}{CAN:2;RS:-95;}{CAN:2;RS:-19;}{CAN:2;RS:-45;}{CAN:2;RS:-19;}{CAN:2;RS:-96;}{CAN:2;RS:-19;}{CAN:2;RS:-47;}{CAN:2;RS:-19;}{CAN:2;RS:-98;}{CAN:2;RS:-19;}{CAN:2;RS:-54;}{CAN:2;RS:-19;}{CAN:2;RS:-90;}{CAN:2;RS:-19;}{CAN:2;RS:-60;}{CAN:2;RS:-19;}{CAN:2;RS:-85;}{CAN:2;RS:-19;}{CAN:2;RS:-55;}{CAN:2;RS:-19;}{CAN:2;RS:-90;}{CAN:2;RS:-19;}{CAN:2;RS:-67;}{CAN:2;RS:-19;}{CAN:2;RS:-83;}{CAN:2;RS:-19;}{CAN:2;RS:-60;}{CAN:2;RS:-19;}{CAN:2;RS:-83;}{CAN:2;RS:-57;}{CAN:2;RS:-28;}{CAN:2;RS:-26;}{CAN:2;RS:-13;}{CAN:2;RS:-54;}{CAN:2;RS:-19;}{CAN:2;RS:-88;}{CAN:2;RS:-19;}{CAN:2;RS:-54;}{CAN:2;RS:-19;}{CAN:2;RS:-91;}{CAN:2;RS:-19;}{CAN:2;RS:-59;}{CAN:2;RS:-19;}{CAN:2;RS:-63;}{CAN:2;RS:-85;}{CAN:2;RS:-29;}{CAN:2;RS:-28;}{CAN:2;RS:-29;}{CAN:2;RS:-19;}{CAN:2;RS:-65;}{CAN:2;RS:-19;}{CAN:2;RS:-96;}{CAN:2;RS:-19;}{CAN:2;RS:-65;}{CAN:2;RS:-19;}{CAN:2;RS:-81;}{CAN:2;RS:-19;}{CAN:2;RS:-60;}{CAN:2;RS:-19;}{CAN:2;RS:-84;}{CAN:2;RS:-19;}{CAN:2;RS:-76;}{CAN:2;RS:-19;}{CAN:2;RS:-83;}{CAN:2;RS:-19;}{CAN:2;RS:-80;}{CAN:2;RS:-19;}{CAN:2;RS:-90;}{CAN:2;RS:-19;}{CAN:2;RS:-88;}{CAN:2;RS:-19;}{CAN:2;RS:-89;}{CAN:2;RS:-19;}{CAN:2;RS:-84;}{CAN:2;RS:-20;}{CAN:2;RS:-87;}{CAN:2;RS:-19;}{CAN:2;RS:-80;}{CAN:2;RS:-19;}{CAN:2;RS:-86;}{CAN:2;RS:-20;}{CAN:2;RS:-77;}{CAN:2;RS:-17;}{CAN:2;RS:-88;}{CAN:2;RS:-17;}{CAN:2;RS:-77;}{CAN:2;RS:-17;}{CAN:2;RS:-100;}{CAN:2;RS:-17;}{CAN:2;RS:-85;}{CAN:2;RS:-17;}{CAN:2;RS:-107;}{CAN:2;RS:-17;}{CAN:2;RS:-86;}{CAN:2;RS:-17;}{CAN:2;RS:-95;}{CAN:2;RS:-17;}{CAN:2;RS:-97;}{CAN:2;RS:-17;}{CAN:2;RS:-106;}{CAN:2;RS:-93;}{CAN:2;RS:-17;}{CAN:2;RS:-104;}{CAN:2;RS:-18;}{CAN:2;RS:-95;}{CAN:2;RS:-17;}{CAN:2;RS:-104;}{CAN:2;RS:-18;}{CAN:2;RS:-99;}{CAN:2;RS:-18;}{CAN:2;RS:-106;}{CAN:2;RS:-17;}{CAN:2;RS:-102;}{CAN:2;RS:-18;}{CAN:2;RS:-111;}{CAN:2;RS:-112;}{CAN:2;RS:-45;}{CAN:2;RS:-113;}{CAN:2;RS:-45;}{CAN:2;RS:-68;}{CAN:2;RS:-103;}{CAN:2;RS:-45;}{CAN:2;RS:-112;}{CAN:2;RS:-45;}{CAN:2;RS:-98;}{CAN:2;RS:-45;}{CAN:2;RS:-101;}{CAN:2;RS:-45;}{CAN:2;RS:-100;}{CAN:2;RS:-45;}{CAN:2;RS:-101;}{CAN:2;RS:-45;}{CAN:3;SBT:2;SMR:2;SMF:2;SMB:2;SMC:0;SMS:2;SMG:2;SMR:2;SFM:0;SDP:0;F:LOG,ALTITUDE_CORRECTION@-61.82;}{CAN:2;RS:-68;}{CAN:2;RS:-102;}{CAN:2;RS:-47;}{CAN:2;RS:-101;}{CAN:2;RS:-45;}{CAN:2;RS:-101;}{CAN:2;RS:-45;}{CAN:2;RS:-100;}{CAN:2;RS:-47;}{CAN:2;RS:-103;}{CAN:2;RS:-19;}{CAN:2;RS:-20;}{CAN:2;RS:-101;}{CAN:2;RS:-45;}{CAN:2;RS:-100;}{CAN:2;RS:-19;}{CAN:3;F:LOG,Flightmode unlocked;}{CAN:2;RS:-68;}{CAN:2;RS:-99;}{CAN:2;RS:-20;}{CAN:2;RS:-99;}{CAN:2;RS:-20;}{CAN:2;RS:-99;}{CAN:2;RS:-15;}{CAN:2;RS:-98;}{CAN:2;RS:-16;}{CAN:2;RS:-98;}{CAN:2;RS:-16;}{CAN:2;RS:-99;}{CAN:2;RS:-102;}{CAN:2;RS:-16;}{CAN:2;RS:-49;}{CAN:2;RS:-102;}{CAN:2;RS:-53;}{CAN:2;RS:-101;}{CAN:2;RS:-49;}{CAN:2;RS:-100;}{CAN:2;RS:-49;}{CAN:2;RS:-52;}{CAN:2;RS:-51;}{CAN:2;RS:-108;}{CAN:2;RS:-49;}{CAN:2;RS:-112;}{CAN:2;RS:-48;}{CAN:2;RS:-112;}{CAN:2;RS:-48;}{CAN:2;RS:-114;}{CAN:2;RS:-51;}{CAN:2;RS:-98;}{CAN:2;RS:-114;}{CAN:2;RS:-101;}{CAN:2;RS:-51;}{CAN:2;RS:-115;}{CAN:2;RS:-49;}{CAN:2;RS:-101;}{CAN:2;RS:-49;}{CAN:2;RS:-114;}{CAN:2;RS:-49;}{CAN:2;RS:-102;}{CAN:2;RS:-48;}{CAN:2;RS:-112;}{CAN:2;RS:-49;}{CAN:2;RS:-105;}{CAN:2;RS:-48;}{CAN:2;RS:-107;}{CAN:2;RS:-49;}{CAN:2;RS:-102;}{CAN:2;RS:-48;}{CAN:2;RS:-116;}{CAN:2;RS:-49;}{CAN:2;RS:-97;}{CAN:2;RS:-47;}{CAN:2;RS:-109;}{CAN:2;RS:-105;}{CAN:2;RS:-49;}{CAN:2;RS:-113;}{CAN:2;RS:-50;}{CAN:2;RS:-105;}{CAN:2;RS:-51;}{CAN:2;RS:-112;}{CAN:2;RS:-50;}{CAN:2;RS:-102;}{CAN:2;RS:-47;}{CAN:2;RS:-107;}{CAN:2;RS:-16;}{CAN:2;RS:-102;}{CAN:2;RS:-18;}{CAN:2;RS:-108;}{CAN:2;RS:-15;}{CAN:2;RS:-98;}{CAN:2;RS:-12;}{CAN:2;RS:-103;}{CAN:2;RS:-13;}{CAN:2;RS:-95;}{CAN:2;RS:-15;}{CAN:2;RS:-113;}{CAN:2;RS:-15;}{CAN:2;RS:-90;}{CAN:2;RS:-16;}{CAN:2;RS:-104;}{CAN:2;RS:-15;}{CAN:2;RS:-90;}{CAN:2;RS:-15;}{CAN:2;RS:-113;}{CAN:2;RS:-15;}{CAN:2;RS:-87;}{CAN:2;RS:-15;}{CAN:2;RS:-107;}{CAN:2;RS:-15;}{CAN:2;RS:-83;}{CAN:2;RS:-13;}{CAN:2;RS:-106;}{CAN:2;RS:-12;}{CAN:2;RS:-87;}{CAN:2;RS:-13;}{CAN:2;RS:-104;}{CAN:2;RS:-9;}{CAN:2;RS:-89;}{CAN:2;RS:-9;}{CAN:2;RS:-99;}{CAN:2;RS:-11;}{CAN:2;RS:-86;}{CAN:2;RS:-15;}{CAN:2;RS:-93;}{CAN:2;RS:-4;}{CAN:2;RS:-84;}{CAN:2;RS:-2;}{CAN:2;RS:-106;}{CAN:2;RS:-2;}{CAN:2;RS:-93;}{CAN:2;RS:0;}{CAN:2;RS:-102;}{CAN:2;RS:0;}{CAN:2;RS:-84;}{CAN:2;RS:0;}{CAN:2;RS:-101;}{CAN:2;RS:0;}{CAN:2;RS:-85;}{CAN:2;RS:-1;}{CAN:2;RS:-96;}{CAN:2;RS:0;}{CAN:2;RS:-87;}{CAN:2;RS:-1;}{CAN:2;RS:-100;}{CAN:2;RS:0;}{CAN:2;RS:-82;}{CAN:2;RS:0;}{CAN:2;RS:-92;}{CAN:2;RS:0;}{CAN:2;RS:-80;}{CAN:2;RS:0;}{CAN:2;RS:-96;}{CAN:2;RS:0;}{CAN:2;RS:-80;}{CAN:2;RS:0;}{CAN:2;RS:-93;}{CAN:2;RS:-82;}{CAN:2;RS:0;}{CAN:2;RS:-95;}{CAN:2;RS:0;}{CAN:2;RS:-84;}{CAN:2;RS:-2;}{CAN:2;RS:-89;}{CAN:2;RS:-3;}{CAN:2;RS:-84;}{CAN:2;RS:-3;}{CAN:2;RS:-93;}{CAN:2;RS:-4;}{CAN:2;RS:-87;}{CAN:2;RS:-3;}{CAN:2;RS:-101;}{CAN:2;RS:-3;}{CAN:2;RS:-82;}{CAN:2;RS:-3;}{CAN:2;RS:-93;}{CAN:2;RS:-3;}{CAN:2;RS:-83;}{CAN:2;RS:-3;}{CAN:2;RS:-59;}{CAN:2;RS:-58;}{CAN:2;RS:-89;}{CAN:2;RS:-104;}{CAN:2;RS:-92;}{CAN:2;RS:-3;}{CAN:2;RS:-96;}{CAN:2;RS:-4;}{CAN:2;RS:-96;}{CAN:2;RS:-3;}{CAN:3;GT:120012;TS:153;GA:51.9783;GO:4.0005;GH:-3.70;GS:0.01;GV:264.33;G3:1;GN:10;AP:100523.79;AT:26.7;AL:3.02;HM:39.19;AX:-0.53;AY:0.98;AZ:7.36;GX:-0.21;GY:0.16;GZ:-0.85;CX:86.34;CY:-33.17;CZ:-90.81;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-56;}{CAN:2;RS:-96;}{CAN:2;RS:-3;}{CAN:2;RS:-105;}{CAN:2;RS:-3;}{CAN:3;GT:120200;TS:1671;GA:51.9783;GO:4.5368;GH:-3.70;GS:0.02;GV:265.62;G3:1;GN:10;AP:100519.31;AT:26.7;AL:3.40;HM:38.99;AX:-0.52;AY:0.73;AZ:2.49;GX:-0.01;GY:-0.44;GZ:-0.82;CX:93.27;CY:-44.92;CZ:-92.48;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:2;RS:-90;}{CAN:2;RS:-4;}{CAN:3;GT:120202;TS:3189;GA:51.9783;GO:4.5368;GH:2.40;GS:0.14;GV:99.65;G3:1;GN:10;AP:100519.12;AT:26.8;AL:3.41;HM:38.84;AX:-1.80;AY:1.37;AZ:12.73;GX:0.19;GY:-0.41;GZ:-0.81;CX:116.33;CY:-34.38;CZ:-93.31;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-64;}{CAN:2;RS:-95;}{CAN:2;RS:-4;}{CAN:3;GT:120204;TS:4709;GA:51.9783;GO:4.5368;GH:2.80;GS:0.02;GV:41.50;G3:1;GN:10;AP:100512.89;AT:26.9;AL:3.94;HM:38.64;AX:-0.99;AY:1.08;AZ:7.66;GX:-0.11;GY:0.04;GZ:-1.13;CX:104.02;CY:-8.64;CZ:-99.15;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-55;}{CAN:2;RS:-93;}{CAN:2;RS:-3;}{CAN:3;GT:120205;TS:6222;GA:51.9783;GO:4.5368;GH:3.10;GS:0.18;GV:355.93;G3:1;GN:10;AP:100515.16;AT:26.9;AL:3.75;HM:38.47;AX:-0.87;AY:0.93;AZ:9.61;GX:-0.71;GY:0.65;GZ:-1.56;CX:72.99;CY:-25.05;CZ:-104.33;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-56;}{CAN:2;RS:-97;}{CAN:2;RS:-3;}{CAN:3;GT:120207;TS:7740;GA:51.9783;GO:4.5368;GH:3.60;GS:0.29;GV:347.19;G3:1;GN:10;AP:100509.94;AT:26.9;AL:4.18;HM:38.28;AX:-1.32;AY:1.59;AZ:13.85;GX:-0.49;GY:-0.43;GZ:-1.20;CX:91.19;CY:-55.98;CZ:-102.83;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-52;}{CAN:2;RS:-92;}{CAN:2;RS:-3;}{CAN:3;GT:120208;TS:9265;GA:51.9783;GO:4.5368;GH:3.90;GS:0.09;GV:88.41;G3:1;GN:10;AP:100511.42;AT:27.0;AL:4.06;HM:38.07;AX:-0.69;AY:0.80;AZ:4.93;GX:-0.33;GY:-0.83;GZ:-0.99;CX:116.68;CY:-30.24;CZ:-105.33;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-54;}{CAN:2;RS:-90;}{CAN:2;RS:-3;}{CAN:3;GT:120210;TS:10783;GA:51.9783;GO:4.5368;GH:4.20;GS:0.14;GV:107.47;G3:1;GN:10;AP:100503.77;AT:26.9;AL:4.70;HM:37.84;AX:-1.66;AY:1.09;AZ:12.97;GX:1.18;GY:-1.15;GZ:-0.40;CX:95.18;CY:-14.00;CZ:-109.00;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-66;}{CAN:2;RS:-106;}{CAN:2;RS:-4;}{CAN:2;RS:-58;}{CAN:2;RS:-3;}{CAN:2;RS:-53;}{CAN:2;RS:-2;}{CAN:2;RS:-61;}{CAN:2;RS:-1;}{CAN:2;RS:-60;}{CAN:2;RS:-2;}{CAN:2;RS:-54;}{CAN:2;RS:-3;}{CAN:2;RS:-57;}{CAN:2;RS:-3;}{CAN:2;RS:-83;}{CAN:2;RS:-2;}{CAN:3;GT:120220;TS:21423;GA:51.9783;GO:4.5368;GH:5.80;GS:0.12;GV:241.61;G3:1;GN:9;AP:100483.17;AT:27.0;AL:6.43;HM:36.42;AX:-0.34;AY:0.58;AZ:4.83;GX:-0.01;GY:-0.01;GZ:-0.37;CX:101.94;CY:-24.53;CZ:-106.83;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:2;RS:-86;}{CAN:2;RS:-3;}{CAN:3;GT:120222;TS:22947;GA:51.9783;GO:4.5368;GH:5.80;GS:0.35;GV:329.80;G3:1;GN:10;AP:100478.59;AT:27.0;AL:6.81;HM:36.26;AX:-0.69;AY:0.32;AZ:3.99;GX:0.14;GY:-0.94;GZ:-0.01;CX:92.58;CY:-26.26;CZ:-105.83;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-63;}{CAN:2;RS:-83;}{CAN:2;RS:-3;}{CAN:3;GT:120223;TS:24465;GA:51.9783;GO:4.5368;GH:5.90;GS:0.16;GV:60.64;G3:1;GN:10;AP:100479.80;AT:27.0;AL:6.71;HM:36.15;AX:-1.11;AY:0.29;AZ:13.47;GX:-0.67;GY:0.99;GZ:-0.84;CX:89.28;CY:-29.89;CZ:-102.66;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-56;}{CAN:2;RS:-82;}{CAN:2;RS:-3;}{CAN:3;GT:120225;TS:25983;GA:51.9783;GO:4.5368;GH:6.00;GS:0.35;GV:60.30;G3:1;GN:10;AP:100480.98;AT:26.8;AL:6.61;HM:36.02;AX:-0.86;AY:1.10;AZ:11.14;GX:0.98;GY:-0.97;GZ:0.10;CX:92.06;CY:-30.58;CZ:-100.66;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-67;}{CAN:2;RS:-86;}{CAN:2;RS:-3;}{CAN:3;GT:120226;TS:27501;GA:51.9783;GO:4.5368;GH:6.10;GS:0.33;GV:55.79;G3:1;GN:10;AP:100474.37;AT:27.0;AL:7.17;HM:35.95;AX:-0.81;AY:0.29;AZ:6.88;GX:1.47;GY:0.68;GZ:-0.31;CX:87.90;CY:-35.42;CZ:-94.65;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-56;}{CAN:2;RS:-91;}{CAN:2;RS:-3;}{CAN:3;GT:120228;TS:29014;GA:51.9783;GO:4.5368;GH:6.30;GS:0.27;GV:332.16;G3:1;GN:10;AP:100476.59;AT:27.1;AL:6.98;HM:35.84;AX:-0.59;AY:1.23;AZ:12.23;GX:-0.39;GY:-0.24;GZ:-0.56;CX:95.35;CY:-44.58;CZ:-90.47;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-66;}{CAN:2;RS:-81;}{CAN:2;RS:-2;}{CAN:3;GT:120229;TS:30539;GA:51.9783;GO:4.5368;GH:6.50;GS:0.19;GV:2.06;G3:1;GN:10;AP:100471.18;AT:27.1;AL:7.43;HM:35.72;AX:-1.59;AY:0.68;AZ:11.45;GX:-0.51;GY:1.67;GZ:-1.04;CX:98.65;CY:-47.51;CZ:-91.98;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-66;}{CAN:3;GT:120231;TS:32057;GA:51.9783;GO:4.5368;GH:6.80;GS:0.06;GV:272.15;G3:1;GN:9;AP:100465.80;AT:27.1;AL:7.88;HM:35.61;AX:-0.14;AY:0.64;AZ:3.07;GX:-0.39;GY:0.41;GZ:-0.69;CX:118.41;CY:-37.84;CZ:-90.97;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-55;}{CAN:2;RS:-82;}{CAN:2;RS:-2;}{CAN:3;GT:120232;TS:33575;GA:51.9783;GO:4.5368;GH:6.90;GS:0.30;GV:88.79;G3:1;GN:10;AP:100462.45;AT:27.1;AL:8.16;HM:35.48;AX:-1.25;AY:1.57;AZ:12.45;GX:0.75;GY:-0.08;GZ:-0.43;CX:122.05;CY:-16.93;CZ:-96.15;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-59;}{CAN:3;GT:120234;TS:35095;GA:51.9783;GO:4.5368;GH:7.20;GS:0.16;GV:24.20;G3:1;GN:10;AP:100460.11;AT:27.2;AL:8.36;HM:35.37;AX:-0.21;AY:0.09;AZ:7.22;GX:1.39;GY:0.91;GZ:-0.72;CX:103.85;CY:-3.97;CZ:-111.00;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-65;}{CAN:2;RS:-87;}{CAN:2;RS:-2;}{CAN:3;GT:120235;TS:36613;GA:51.9783;GO:4.5368;GH:7.20;GS:0.06;GV:312.47;G3:1;GN:10;AP:100452.94;AT:27.1;AL:8.96;HM:35.27;AX:-0.20;AY:0.86;AZ:6.04;GX:0.46;GY:-0.19;GZ:-0.69;CX:83.56;CY:-22.12;CZ:-109.84;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:2;RS:-84;}{CAN:2;RS:-2;}{CAN:3;GT:120237;TS:38131;GA:51.9783;GO:4.5368;GH:7.40;GS:0.20;GV:331.66;G3:1;GN:10;AP:100450.73;AT:27.0;AL:9.15;HM:35.13;AX:-1.19;AY:1.06;AZ:13.48;GX:0.54;GY:-0.11;GZ:-0.90;CX:87.38;CY:-44.92;CZ:-102.49;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-58;}{CAN:3;GT:120239;TS:39656;GA:51.9783;GO:4.5368;GH:7.50;GS:0.15;GV:338.34;G3:1;GN:10;AP:100448.81;AT:27.1;AL:9.31;HM:35.08;AX:-2.02;AY:2.65;AZ:15.78;GX:-1.43;GY:-1.42;GZ:-1.05;CX:110.43;CY:-41.29;CZ:-101.66;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-54;}{CAN:3;GT:120240;TS:41181;GA:51.9783;GO:4.5368;GH:7.60;GS:1.09;GV:39.17;G3:1;GN:10;AP:100444.99;AT:27.1;AL:9.63;HM:34.97;AX:-1.18;AY:1.37;AZ:15.47;GX:-1.73;GY:-0.95;GZ:-0.94;CX:105.06;CY:-21.42;CZ:-105.50;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-56;}{CAN:3;GT:120242;TS:42706;GA:51.9783;GO:4.5368;GH:6.80;GS:0.13;GV:136.09;G3:1;GN:10;AP:100438.42;AT:27.1;AL:10.18;HM:34.89;AX:-1.62;AY:1.56;AZ:11.90;GX:0.07;GY:0.72;GZ:-0.78;CX:94.14;CY:-22.29;CZ:-105.66;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-66;}{CAN:3;GT:120243;TS:44236;GA:51.9783;GO:4.5368;GH:7.20;GS:0.25;GV:177.08;G3:1;GN:10;AP:100439.21;AT:27.1;AL:10.11;HM:34.81;AX:-2.03;AY:1.27;AZ:11.04;GX:-1.58;GY:0.09;GZ:-1.20;CX:87.90;CY:-29.89;CZ:-103.66;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-52;}{CAN:3;GT:120245;TS:45766;GA:51.9783;GO:4.5368;GH:7.60;GS:0.11;GV:231.82;G3:1;GN:10;AP:100436.80;AT:27.1;AL:10.32;HM:34.70;AX:-1.99;AY:1.81;AZ:10.85;GX:-4.38;GY:-1.65;GZ:-2.13;CX:92.75;CY:-46.82;CZ:-96.65;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-59;}{CAN:2;RS:-4;}{CAN:2;RS:-53;}{CAN:2;RS:-4;}{CAN:2;RS:-57;}{CAN:2;RS:-3;}{CAN:2;RS:-70;}{CAN:2;RS:-4;}{CAN:3;GT:120252;TS:53405;GA:51.9783;GO:4.5368;GH:8.70;GS:0.14;GV:135.42;G3:1;GN:10;AP:100423.49;AT:27.3;AL:11.43;HM:34.26;AX:-0.63;AY:0.53;AZ:3.51;GX:0.13;GY:0.25;GZ:-1.87;CX:86.68;CY:-41.81;CZ:-89.47;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-66;}{CAN:3;GT:120254;TS:54930;GA:51.9783;GO:4.5368;GH:9.00;GS:0.57;GV:52.74;G3:1;GN:10;AP:100419.75;AT:27.3;AL:11.75;HM:34.17;AX:-0.90;AY:0.79;AZ:11.32;GX:-1.40;GY:0.09;GZ:-2.11;CX:112.34;CY:-22.81;CZ:-89.14;OC:0.00;O2:0.00;BV:4.01;}{CAN:3;GT:120255;TS:56460;GA:51.9783;GO:4.5368;GH:9.10;GS:0.45;GV:79.18;G3:1;GN:10;AP:100414.02;AT:27.4;AL:12.23;HM:34.08;AX:-0.35;AY:0.76;AZ:5.53;GX:0.53;GY:0.14;GZ:-1.43;CX:75.76;CY:-27.47;CZ:-92.98;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-56;}{CAN:3;GT:120257;TS:57983;GA:51.9783;GO:4.5368;GH:9.30;GS:0.10;GV:72.43;G3:1;GN:10;AP:100412.44;AT:27.5;AL:12.36;HM:33.99;AX:-1.01;AY:1.37;AZ:8.18;GX:-0.23;GY:0.45;GZ:-1.62;CX:104.02;CY:-48.03;CZ:-92.48;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-64;}{CAN:3;GT:120258;TS:59506;GA:51.9783;GO:4.5368;GH:9.50;GS:0.42;GV:332.66;G3:1;GN:10;AP:100411.46;AT:27.5;AL:12.44;HM:33.90;AX:-0.22;AY:0.67;AZ:2.77;GX:0.02;GY:0.53;GZ:-1.35;CX:111.13;CY:-17.45;CZ:-96.65;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-61;}{CAN:3;GT:120300;TS:61031;GA:51.9783;GO:4.5368;GH:10.00;GS:0.42;GV:62.81;G3:1;GN:10;AP:100411.37;AT:27.6;AL:12.45;HM:33.82;AX:-1.22;AY:0.09;AZ:10.99;GX:1.54;GY:0.42;GZ:-0.83;CX:83.74;CY:-17.11;CZ:-98.99;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:3;GT:120301;TS:62556;GA:51.9783;GO:4.5368;GH:10.40;GS:0.32;GV:53.19;G3:1;GN:10;AP:100412.98;AT:27.6;AL:12.31;HM:33.74;AX:-0.47;AY:0.23;AZ:4.61;GX:0.10;GY:-0.32;GZ:-1.03;CX:87.72;CY:-44.92;CZ:-97.15;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-77;}{CAN:3;GT:120303;TS:64081;GA:51.9783;GO:4.5368;GH:11.00;GS:0.44;GV:32.17;G3:1;GN:10;AP:100410.12;AT:27.7;AL:12.55;HM:33.68;AX:-0.06;AY:2.49;AZ:15.35;GX:-0.36;GY:2.20;GZ:-1.79;CX:105.93;CY:-42.33;CZ:-95.65;OC:0.00;O2:0.00;BV:4.01;}{CAN:3;GT:120304;TS:65611;GA:51.9783;GO:4.5368;GH:11.20;GS:0.53;GV:350.07;G3:1;GN:10;AP:100407.57;AT:27.7;AL:12.77;HM:33.62;AX:-0.41;AY:0.11;AZ:3.56;GX:-1.54;GY:0.43;GZ:-1.38;CX:110.61;CY:-21.08;CZ:-99.15;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-61;}{CAN:2;RS:-97;}{CAN:2;RS:6;}{CAN:3;GT:120306;TS:67141;GA:51.9783;GO:4.5368;GH:11.70;GS:0.29;GV:6.47;G3:1;GN:10;AP:100403.48;AT:27.7;AL:13.11;HM:33.56;AX:-0.65;AY:0.14;AZ:2.47;GX:0.35;GY:1.10;GZ:-1.36;CX:83.74;CY:-20.56;CZ:-98.32;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-64;}{CAN:2;RS:-88;}{CAN:2;RS:-4;}{CAN:3;GT:120308;TS:68664;GA:51.9783;GO:4.5368;GH:12.30;GS:0.19;GV:44.06;G3:1;GN:10;AP:100401.91;AT:27.8;AL:13.24;HM:33.49;AX:-1.38;AY:1.47;AZ:12.06;GX:0.95;GY:-1.21;GZ:-0.25;CX:82.87;CY:-34.56;CZ:-96.48;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:2;RS:-92;}{CAN:2;RS:0;}{CAN:3;GT:120309;TS:70194;GA:51.9783;GO:4.5368;GH:12.50;GS:0.41;GV:23.49;G3:1;GN:10;AP:100398.61;AT:27.8;AL:13.52;HM:33.47;AX:-0.10;AY:2.19;AZ:10.41;GX:-1.40;GY:3.57;GZ:-2.29;CX:87.90;CY:-48.55;CZ:-94.98;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-61;}{CAN:3;GT:120311;TS:71724;GA:51.9783;GO:4.5368;GH:12.90;GS:0.26;GV:208.90;G3:1;GN:10;AP:100398.05;AT:27.9;AL:13.57;HM:33.42;AX:-1.09;AY:0.63;AZ:17.08;GX:2.50;GY:0.87;GZ:-0.61;CX:111.65;CY:-27.30;CZ:-96.15;OC:0.00;O2:0.00;BV:4.02;}{CAN:2;RS:-58;}{CAN:2;RS:-61;}{CAN:2;RS:1;}{CAN:2;RS:-55;}{CAN:2;RS:3;}{CAN:2;RS:-61;}{CAN:2;RS:3;}{CAN:2;RS:-58;}{CAN:2;RS:4;}{CAN:2;RS:-59;}{CAN:2;RS:-2;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:2;RS:-64;}{CAN:2;RS:6;}{CAN:2;RS:-59;}{CAN:2;RS:-1;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:2;RS:-61;}{CAN:2;RS:9;}{CAN:2;RS:-65;}{CAN:2;RS:6;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-69;}{CAN:2;RS:6;}{CAN:3;GT:120321;TS:82424;GA:51.9783;GO:4.5368;GH:16.30;GS:0.37;GV:49.23;G3:1;GN:10;AP:100374.08;AT:27.8;AL:15.58;HM:32.99;AX:-0.78;AY:0.90;AZ:8.97;GX:-0.53;GY:1.23;GZ:4.35;CX:99.17;CY:-33.87;CZ:-62.76;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120323;TS:83949;GA:51.9783;GO:4.5368;GH:17.10;GS:0.05;GV:105.42;G3:1;GN:10;AP:100376.77;AT:27.8;AL:15.35;HM:32.92;AX:-0.99;AY:1.39;AZ:10.20;GX:-0.58;GY:0.68;GZ:2.73;CX:95.18;CY:-27.82;CZ:-63.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;GT:120324;TS:85479;GA:51.9783;GO:4.5368;GH:17.40;GS:0.40;GV:151.36;G3:1;GN:10;AP:100374.70;AT:27.8;AL:15.52;HM:32.86;AX:-1.21;AY:0.68;AZ:9.19;GX:-0.25;GY:-0.02;GZ:0.63;CX:84.78;CY:-67.56;CZ:-60.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;GT:120326;TS:87009;GA:51.9783;GO:4.5368;GH:17.80;GS:0.30;GV:245.72;G3:1;GN:10;AP:100373.69;AT:27.8;AL:15.36;HM:32.81;AX:-0.80;AY:0.94;AZ:8.96;GX:0.16;GY:0.24;GZ:-1.46;CX:91.71;CY:-64.79;CZ:-60.59;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120327;TS:88534;GA:51.9783;GO:4.5368;GH:18.10;GS:0.13;GV:49.65;G3:1;GN:10;AP:100378.38;AT:27.8;AL:15.22;HM:32.75;AX:-0.96;AY:0.79;AZ:9.82;GX:0.44;GY:-0.43;GZ:-3.15;CX:69.17;CY:-38.18;CZ:-63.26;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120329;TS:90059;GA:51.9783;GO:4.5368;GH:18.40;GS:0.71;GV:21.74;G3:1;GN:10;AP:100377.23;AT:27.7;AL:15.31;HM:32.69;AX:-1.06;AY:0.52;AZ:8.44;GX:0.20;GY:-0.63;GZ:-3.91;CX:82.52;CY:-27.64;CZ:-65.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;GT:120330;TS:91584;GA:51.9783;GO:4.5368;GH:18.40;GS:0.83;GV:27.74;G3:1;GN:10;AP:100376.25;AT:27.7;AL:15.39;HM:32.62;AX:-0.75;AY:0.59;AZ:9.21;GX:0.04;GY:-0.24;GZ:-3.95;CX:91.19;CY:-28.34;CZ:-65.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;GT:120332;TS:93109;GA:51.9783;GO:4.5368;GH:18.30;GS:0.49;GV:202.30;G3:1;GN:10;AP:100369.99;AT:27.7;AL:15.92;HM:32.59;AX:-0.79;AY:0.82;AZ:9.24;GX:0.23;GY:0.12;GZ:-3.09;CX:104.89;CY:-38.18;CZ:-64.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:120334;TS:94639;GA:51.9783;GO:4.5368;GH:19.20;GS:1.56;GV:190.13;G3:1;GN:10;AP:100379.53;AT:27.6;AL:15.12;HM:32.52;AX:-0.85;AY:1.40;AZ:9.48;GX:0.45;GY:-0.04;GZ:-1.81;CX:79.92;CY:-64.79;CZ:-60.93;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120335;TS:96169;GA:51.9783;GO:4.5368;GH:19.80;GS:0.34;GV:65.11;G3:1;GN:10;AP:100376.25;AT:27.6;AL:15.39;HM:32.49;AX:-1.08;AY:0.82;AZ:9.92;GX:0.21;GY:0.28;GZ:-0.07;CX:104.02;CY:-53.91;CZ:-61.43;OC:0.00;O2:0.00;BV:4.01;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:120337;TS:97694;GA:51.9783;GO:4.5368;GH:18.50;GS:0.21;GV:113.31;G3:1;GN:10;AP:100375.58;AT:27.5;AL:15.45;HM:32.45;AX:-0.78;AY:0.97;AZ:9.17;GX:-0.56;GY:0.26;GZ:1.80;CX:79.40;CY:-64.62;CZ:-62.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:2;RS:-5;}{CAN:2;RS:-66;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:120340;TS:100744;GA:51.9783;GO:4.5368;GH:16.80;GS:1.63;GV:48.58;G3:1;GN:10;AP:100376.26;AT:27.4;AL:15.39;HM:32.34;AX:-0.68;AY:1.13;AZ:9.11;GX:-0.34;GY:0.58;GZ:2.20;CX:76.63;CY:-61.16;CZ:-63.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;GT:120341;TS:102269;GA:51.9783;GO:4.5368;GH:14.40;GS:1.52;GV:39.60;G3:1;GN:10;AP:100375.44;AT:27.2;AL:15.46;HM:32.26;AX:-0.79;AY:0.68;AZ:9.02;GX:-0.11;GY:-0.15;GZ:1.32;CX:93.79;CY:-29.55;CZ:-64.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:2;RS:-58;}{CAN:2;RS:-4;}{CAN:2;RS:-61;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;GT:120344;TS:105329;GA:51.9783;GO:4.5368;GH:12.10;GS:0.53;GV:18.72;G3:1;GN:10;AP:100371.38;AT:27.2;AL:15.80;HM:32.28;AX:-0.69;AY:1.37;AZ:8.71;GX:-0.38;GY:0.43;GZ:0.53;CX:96.74;CY:-59.44;CZ:-62.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:120346;TS:106854;GA:51.9783;GO:4.5368;GH:10.50;GS:0.38;GV:259.55;G3:1;GN:10;AP:100392.14;AT:27.1;AL:14.06;HM:32.31;AX:-0.72;AY:0.98;AZ:7.01;GX:0.27;GY:0.19;GZ:0.49;CX:85.64;CY:-66.69;CZ:-61.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:120347;TS:108379;GA:51.9783;GO:4.5368;GH:9.40;GS:1.05;GV:89.50;G3:1;GN:10;AP:100402.08;AT:27.1;AL:13.23;HM:32.32;AX:-0.58;AY:0.69;AZ:6.22;GX:-0.53;GY:-0.22;GZ:0.97;CX:67.96;CY:-58.75;CZ:-62.43;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;GT:120349;TS:109902;GA:51.9783;GO:4.5368;GH:8.70;GS:0.66;GV:60.09;G3:1;GN:10;AP:100411.31;AT:27.0;AL:12.45;HM:32.32;AX:-1.05;AY:0.56;AZ:9.46;GX:-0.13;GY:-0.19;GZ:1.14;CX:76.80;CY:-35.07;CZ:-64.60;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;GT:120350;TS:111427;GA:51.9783;GO:4.5368;GH:8.60;GS:1.01;GV:30.29;G3:1;GN:10;AP:100410.92;AT:27.1;AL:12.49;HM:32.35;AX:-0.86;AY:0.79;AZ:9.33;GX:0.47;GY:0.44;GZ:1.40;CX:101.07;CY:-39.57;CZ:-67.94;OC:0.00;O2:0.00;BV:4.00;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;GT:120352;TS:112950;GA:51.9783;GO:4.5368;GH:8.30;GS:0.51;GV:270.18;G3:1;GN:10;AP:100406.93;AT:27.2;AL:12.82;HM:32.39;AX:-1.01;AY:0.88;AZ:9.16;GX:0.19;GY:-0.10;GZ:1.41;CX:77.32;CY:-68.08;CZ:-61.76;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:120353;TS:114475;GA:51.9783;GO:4.5368;GH:8.10;GS:0.82;GV:225.85;G3:1;GN:10;AP:100411.12;AT:27.2;AL:12.47;HM:32.41;AX:-1.30;AY:0.98;AZ:10.21;GX:0.05;GY:0.03;GZ:0.84;CX:69.69;CY:-33.87;CZ:-64.10;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:120355;TS:116000;GA:51.9783;GO:4.5368;GH:8.30;GS:1.11;GV:49.92;G3:1;GN:10;AP:100417.35;AT:27.1;AL:11.95;HM:32.41;AX:0.32;AY:-12.21;AZ:11.63;GX:1.33;GY:-0.03;GZ:0.63;CX:81.66;CY:-43.02;CZ:-54.25;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;GT:120356;TS:117530;GA:51.9783;GO:4.5368;GH:8.40;GS:0.69;GV:39.47;G3:1;GN:10;AP:100400.87;AT:27.2;AL:13.33;HM:32.43;AX:-0.94;AY:0.70;AZ:8.09;GX:-0.46;GY:-0.20;GZ:-0.30;CX:81.83;CY:-30.41;CZ:-66.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;GT:120358;TS:119060;GA:51.9783;GO:4.5368;GH:8.60;GS:0.82;GV:239.90;G3:1;GN:10;AP:100408.88;AT:27.3;AL:12.66;HM:32.48;AX:-1.01;AY:1.23;AZ:11.09;GX:0.09;GY:-0.01;GZ:-0.38;CX:74.20;CY:-32.14;CZ:-63.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120400;TS:120590;GA:51.9783;GO:4.5368;GH:9.10;GS:0.18;GV:278.37;G3:1;GN:10;AP:100415.73;AT:27.4;AL:12.08;HM:32.52;AX:-1.14;AY:0.65;AZ:9.16;GX:0.13;GY:0.13;GZ:-0.25;CX:67.44;CY:-41.64;CZ:-62.93;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:120401;TS:122115;GA:51.9783;GO:4.5368;GH:9.30;GS:1.00;GV:76.08;G3:1;GN:10;AP:100424.08;AT:27.4;AL:11.38;HM:32.55;AX:-0.91;AY:1.11;AZ:10.76;GX:-0.25;GY:-0.08;GZ:-0.11;CX:75.76;CY:-74.47;CZ:-49.91;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;GT:120403;TS:123644;GA:51.9783;GO:4.5368;GH:9.60;GS:0.62;GV:68.23;G3:1;GN:10;AP:100430.73;AT:27.5;AL:10.82;HM:32.57;AX:-0.90;AY:0.67;AZ:8.09;GX:-0.10;GY:-0.12;GZ:0.46;CX:78.54;CY:-45.79;CZ:-57.59;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:2;RS:-56;}{CAN:2;RS:-5;}{CAN:2;RS:-59;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;GT:120406;TS:126694;GA:51.9783;GO:4.5368;GH:10.30;GS:0.20;GV:304.21;G3:1;GN:10;AP:100439.18;AT:27.4;AL:10.12;HM:32.56;AX:-1.06;AY:0.83;AZ:8.31;GX:0.14;GY:0.02;GZ:1.39;CX:99.69;CY:-45.10;CZ:-67.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;GT:120407;TS:128219;GA:51.9783;GO:4.5368;GH:10.50;GS:1.16;GV:5.39;G3:1;GN:10;AP:100456.20;AT:27.4;AL:8.69;HM:32.56;AX:-1.19;AY:0.80;AZ:12.16;GX:0.18;GY:0.66;GZ:1.96;CX:75.24;CY:-57.71;CZ:-70.44;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:2;RS:-56;}{CAN:2;RS:-4;}{CAN:2;RS:-63;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:120410;TS:131265;GA:51.9783;GO:4.5368;GH:9.90;GS:0.46;GV:61.62;G3:1;GN:10;AP:100464.08;AT:27.6;AL:8.03;HM:32.61;AX:-1.27;AY:0.75;AZ:9.91;GX:-0.12;GY:-0.28;GZ:2.86;CX:55.30;CY:-41.64;CZ:-60.59;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120412;TS:132788;GA:51.9783;GO:4.5368;GH:10.70;GS:0.18;GV:9.25;G3:1;GN:10;AP:100467.84;AT:27.6;AL:7.71;HM:32.62;AX:-0.91;AY:0.76;AZ:9.68;GX:-0.32;GY:0.25;GZ:2.81;CX:84.43;CY:-63.07;CZ:-55.59;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;GT:120413;TS:134311;GA:51.9783;GO:4.5368;GH:10.80;GS:0.59;GV:251.98;G3:1;GN:10;AP:100481.05;AT:27.6;AL:6.61;HM:32.61;AX:-1.04;AY:0.63;AZ:9.88;GX:-0.13;GY:0.30;GZ:2.85;CX:95.70;CY:-44.92;CZ:-66.77;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;GT:120415;TS:135834;GA:51.9783;GO:4.5368;GH:11.30;GS:0.27;GV:199.32;G3:1;GN:10;AP:100487.77;AT:27.6;AL:6.04;HM:32.59;AX:-1.01;AY:0.75;AZ:10.28;GX:-0.08;GY:0.00;GZ:2.68;CX:79.92;CY:-40.26;CZ:-70.94;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120416;TS:137364;GA:51.9783;GO:4.5368;GH:11.30;GS:0.51;GV:179.42;G3:1;GN:10;AP:100500.74;AT:27.6;AL:4.95;HM:32.59;AX:-0.88;AY:0.55;AZ:8.78;GX:-0.16;GY:0.50;GZ:2.59;CX:87.03;CY:-64.27;CZ:-71.11;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:120418;TS:138888;GA:51.9783;GO:4.5368;GH:11.50;GS:0.03;GV:200.59;G3:1;GN:10;AP:100507.27;AT:27.6;AL:4.41;HM:32.59;AX:-0.88;AY:0.68;AZ:8.34;GX:-0.10;GY:0.19;GZ:2.47;CX:105.75;CY:-33.17;CZ:-76.12;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:3;GT:120419;TS:140418;GA:51.9783;GO:4.5368;GH:11.50;GS:0.06;GV:337.77;G3:1;GN:10;AP:100505.21;AT:27.8;AL:4.58;HM:32.59;AX:-0.77;AY:0.68;AZ:8.18;GX:-0.54;GY:0.22;GZ:2.28;CX:61.72;CY:-42.85;CZ:-81.46;OC:0.00;O2:0.00;BV:4.00;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;GT:120421;TS:141943;GA:51.9783;GO:4.5368;GH:11.80;GS:0.34;GV:270.53;G3:1;GN:10;AP:100516.95;AT:27.8;AL:3.60;HM:32.59;AX:-0.78;AY:0.67;AZ:8.18;GX:-0.32;GY:0.02;GZ:2.02;CX:105.23;CY:-53.73;CZ:-61.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;GT:120422;TS:143473;GA:51.9783;GO:4.5368;GH:11.90;GS:0.25;GV:222.57;G3:1;GN:10;AP:100516.37;AT:27.9;AL:3.65;HM:32.63;AX:-0.64;AY:0.85;AZ:7.36;GX:-0.15;GY:0.27;GZ:1.71;CX:77.15;CY:-49.59;CZ:-64.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-54;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;GT:120424;TS:145003;GA:51.9783;GO:4.5368;GH:12.10;GS:0.26;GV:248.20;G3:1;GN:10;AP:100529.21;AT:27.9;AL:2.57;HM:32.63;AX:-0.86;AY:1.01;AZ:8.39;GX:0.12;GY:0.37;GZ:1.47;CX:89.80;CY:-42.50;CZ:-66.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:120425;TS:146534;GA:51.9783;GO:4.5368;GH:12.10;GS:0.48;GV:215.01;G3:1;GN:10;AP:100528.78;AT:28.0;AL:2.60;HM:32.65;AX:-0.87;AY:0.78;AZ:9.12;GX:0.04;GY:0.30;GZ:1.29;CX:92.75;CY:-46.48;CZ:-65.27;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:2;RS:-4;}{CAN:2;RS:-68;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:3;GT:120429;TS:149590;GA:51.9783;GO:4.5367;GH:12.10;GS:0.16;GV:289.50;G3:1;GN:10;AP:100538.25;AT:28.0;AL:1.81;HM:32.61;AX:-1.03;AY:0.80;AZ:8.52;GX:-0.03;GY:0.06;GZ:0.90;CX:82.00;CY:-56.50;CZ:-68.61;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-54;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:2;RS:-75;}{CAN:2;RS:-6;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:120432;TS:152652;GA:51.9783;GO:4.5367;GH:11.50;GS:0.25;GV:265.74;G3:1;GN:10;AP:100546.48;AT:28.1;AL:1.12;HM:32.56;AX:-7.98;AY:5.86;AZ:1.56;GX:2.09;GY:-0.81;GZ:-4.38;CX:107.14;CY:-86.74;CZ:-52.08;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:2;RS:-59;}{CAN:2;RS:-7;}{CAN:3;GT:120433;TS:154188;GA:51.9783;GO:4.5367;GH:11.40;GS:0.97;GV:195.73;G3:1;GN:10;AP:100555.30;AT:28.1;AL:0.38;HM:32.55;AX:-1.89;AY:-10.61;AZ:1.32;GX:0.56;GY:-0.16;GZ:-0.08;CX:106.45;CY:5.87;CZ:-40.73;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:2;RS:-60;}{CAN:2;RS:-4;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;GT:120436;TS:157254;GA:51.9783;GO:4.5368;GH:11.30;GS:1.77;GV:197.06;G3:1;GN:10;AP:100547.20;AT:28.3;AL:1.06;HM:32.62;AX:-11.73;AY:-5.05;AZ:2.92;GX:0.19;GY:0.35;GZ:1.42;CX:108.01;CY:-1.56;CZ:-45.57;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-66;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-54;}{CAN:3;GT:120438;TS:158790;GA:51.9782;GO:4.5368;GH:11.20;GS:2.48;GV:181.89;G3:1;GN:10;AP:100552.77;AT:28.4;AL:0.60;HM:32.70;AX:-6.95;AY:-3.83;AZ:2.42;GX:0.05;GY:-0.69;GZ:-0.08;CX:112.52;CY:-6.39;CZ:-42.90;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;GT:120439;TS:160326;GA:51.9782;GO:4.5368;GH:11.20;GS:1.74;GV:175.81;G3:1;GN:10;AP:100549.95;AT:28.4;AL:0.83;HM:32.83;AX:-6.85;AY:-4.41;AZ:4.99;GX:-0.35;GY:-0.44;GZ:-0.03;CX:121.70;CY:-13.82;CZ:-37.72;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-54;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;GT:120441;TS:161857;GA:51.9782;GO:4.5368;GH:11.20;GS:1.05;GV:171.66;G3:1;GN:10;AP:100546.17;AT:28.5;AL:1.15;HM:32.97;AX:-6.11;AY:-4.36;AZ:8.88;GX:0.64;GY:-1.58;GZ:-0.58;CX:110.95;CY:-11.40;CZ:-49.41;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-54;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:3;GT:120442;TS:163387;GA:51.9782;GO:4.5368;GH:11.20;GS:1.59;GV:171.25;G3:1;GN:10;AP:100549.36;AT:28.5;AL:0.88;HM:33.20;AX:-3.05;AY:-5.31;AZ:9.24;GX:-0.44;GY:-0.07;GZ:0.63;CX:81.48;CY:-7.95;CZ:-59.43;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;GT:120444;TS:164923;GA:51.9782;GO:4.5368;GH:11.20;GS:1.51;GV:174.84;G3:1;GN:10;AP:100555.36;AT:28.5;AL:0.38;HM:33.31;AX:-3.58;AY:-6.41;AZ:5.35;GX:0.20;GY:0.26;GZ:0.71;CX:91.19;CY:-1.38;CZ:-46.07;OC:0.00;O2:0.00;BV:3.98;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;F:ASK;}{CAN:2;RS:-38;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-42;}{CAN:3;F:ASK;}{CAN:2;RS:-44;}{CAN:3;GT:120445;TS:166454;GA:51.9782;GO:4.5368;GH:11.20;GS:1.10;GV:180.73;G3:1;GN:10;AP:100552.33;AT:28.6;AL:0.63;HM:33.49;AX:-5.47;AY:-5.29;AZ:6.09;GX:0.01;GY:-0.01;GZ:0.04;CX:101.77;CY:-4.67;CZ:-46.24;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-45;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-45;}{CAN:3;GT:120447;TS:167990;GA:51.9782;GO:4.5368;GH:11.10;GS:0.01;GV:40.83;G3:1;GN:10;AP:100557.59;AT:28.7;AL:0.19;HM:33.64;AX:-4.65;AY:-5.55;AZ:6.89;GX:0.08;GY:-0.19;GZ:-0.08;CX:100.90;CY:-4.49;CZ:-46.40;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-44;}{CAN:3;F:ASK;}{CAN:2;RS:-39;}{CAN:3;F:ASK;}{CAN:2;RS:-40;}{CAN:3;F:ASK;}{CAN:2;RS:-45;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;F:ASK;}{CAN:2;RS:-48;}{CAN:2;RS:-50;}{CAN:2;RS:-4;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;GT:120450;TS:171062;GA:51.9782;GO:4.5368;GH:11.20;GS:0.04;GV:120.35;G3:1;GN:10;AP:100555.01;AT:28.8;AL:0.41;HM:33.72;AX:-2.58;AY:-5.27;AZ:7.16;GX:-0.00;GY:-0.02;GZ:0.01;CX:94.83;CY:-4.67;CZ:-50.58;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:2;RS:-51;}{CAN:2;RS:-3;}{CAN:2;RS:-52;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;GT:120453;TS:174128;GA:51.9782;GO:4.5368;GH:11.10;GS:0.02;GV:115.54;G3:1;GN:9;AP:100542.36;AT:28.8;AL:1.47;HM:33.70;AX:-2.42;AY:-4.66;AZ:8.02;GX:0.05;GY:0.25;GZ:0.04;CX:93.96;CY:-6.57;CZ:-55.42;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:2;RS:-51;}{CAN:2;RS:-3;}{CAN:2;RS:-49;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;GT:120456;TS:177194;GA:51.9782;GO:4.5368;GH:10.90;GS:0.12;GV:76.50;G3:1;GN:10;AP:100535.80;AT:28.7;AL:2.02;HM:33.55;AX:0.30;AY:-5.10;AZ:7.79;GX:0.08;GY:0.08;GZ:-0.10;CX:83.04;CY:-4.67;CZ:-54.25;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;F:ASK;}{CAN:2;RS:-45;}{CAN:2;RS:-39;}{CAN:2;RS:-4;}{CAN:3;GT:120458;TS:178723;GA:51.9782;GO:4.5368;GH:10.80;GS:0.56;GV:243.37;G3:1;GN:10;AP:100544.80;AT:28.7;AL:1.26;HM:33.48;AX:-4.84;AY:-3.77;AZ:6.67;GX:-0.21;GY:0.36;GZ:0.05;CX:113.56;CY:-13.65;CZ:-50.24;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-42;}{CAN:3;F:ASK;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:2;RS:-44;}{CAN:3;F:ASK;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-40;}{CAN:3;GT:120459;TS:180259;GA:51.9782;GO:4.5368;GH:10.80;GS:0.19;GV:284.15;G3:1;GN:10;AP:100552.32;AT:28.8;AL:0.63;HM:33.39;AX:-5.87;AY:-3.59;AZ:6.35;GX:-0.32;GY:0.38;GZ:-0.02;CX:116.85;CY:-17.28;CZ:-47.41;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;GT:120501;TS:181795;GA:51.9782;GO:4.5368;GH:10.80;GS:0.36;GV:19.11;G3:1;GN:10;AP:100556.47;AT:28.9;AL:0.29;HM:33.29;AX:-7.49;AY:-4.32;AZ:4.78;GX:-0.14;GY:-0.05;GZ:0.13;CX:119.80;CY:-13.30;CZ:-41.23;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;GT:120502;TS:183331;GA:51.9782;GO:4.5368;GH:10.80;GS:0.47;GV:67.19;G3:1;GN:9;AP:100547.13;AT:29.0;AL:1.07;HM:33.23;AX:-8.46;AY:-6.81;AZ:0.35;GX:-0.45;GY:-1.19;GZ:-0.11;CX:122.05;CY:-15.20;CZ:-10.68;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-45;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-50;}{CAN:3;F:ASK;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:2;RS:-42;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;GT:120504;TS:184867;GA:51.9782;GO:4.5368;GH:10.80;GS:0.31;GV:242.27;G3:1;GN:10;AP:100543.90;AT:28.8;AL:1.34;HM:33.11;AX:-7.86;AY:-4.90;AZ:3.42;GX:0.97;GY:0.52;GZ:-0.47;CX:129.33;CY:-26.61;CZ:-27.38;OC:0.00;O2:0.00;BV:3.99;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-44;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;GT:120505;TS:186403;GA:51.9782;GO:4.5368;GH:10.90;GS:0.41;GV:57.35;G3:1;GN:10;AP:100550.45;AT:28.8;AL:0.79;HM:33.00;AX:-5.24;AY:-1.84;AZ:6.47;GX:-0.27;GY:0.31;GZ:-0.07;CX:129.85;CY:-37.15;CZ:-42.90;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-40;}{CAN:3;F:ASK;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:2;RS:-38;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-44;}{CAN:3;F:ASK;}{CAN:2;RS:-45;}{CAN:3;GT:120507;TS:187939;GA:51.9782;GO:4.5368;GH:11.10;GS:0.03;GV:340.09;G3:1;GN:10;AP:100540.94;AT:28.8;AL:1.59;HM:32.92;AX:-6.35;AY:-0.47;AZ:7.02;GX:0.11;GY:0.09;GZ:0.25;CX:125.52;CY:-46.65;CZ:-50.08;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-54;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-10;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-64;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-64;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-64;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-64;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-8;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;SBT:1;}{CAN:2;RS:-69;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-56;}{CAN:2;RS:-5;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-64;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120703;TS:153;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100525.17;AT:26.4;AL:3.05;HM:37.29;AX:-0.70;AY:1.01;AZ:7.49;GX:0.47;GY:-0.39;GZ:-0.97;CX:87.72;CY:-58.75;CZ:-68.11;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-51;}{CAN:3;GT:120716;TS:1658;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100521.28;AT:26.4;AL:3.37;HM:37.16;AX:-0.86;AY:0.46;AZ:7.93;GX:0.25;GY:-0.06;GZ:-1.15;CX:96.39;CY:-45.96;CZ:-64.77;OC:0.00;O2:0.00;BV:4.01;}{CAN:2;RS:-52;}{CAN:3;GT:120717;TS:3169;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100524.46;AT:26.5;AL:3.10;HM:37.02;AX:-1.62;AY:1.21;AZ:10.33;GX:0.48;GY:0.13;GZ:-1.07;CX:85.47;CY:-41.29;CZ:-62.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-53;}{CAN:3;GT:120720;TS:6191;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100522.08;AT:26.6;AL:3.30;HM:36.68;AX:-0.82;AY:0.73;AZ:6.39;GX:0.02;GY:-0.32;GZ:-0.89;CX:78.36;CY:-53.56;CZ:-63.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-51;}{CAN:3;GT:120722;TS:7702;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100520.97;AT:26.6;AL:3.40;HM:36.46;AX:-0.97;AY:1.21;AZ:10.72;GX:0.42;GY:0.21;GZ:-0.87;CX:86.16;CY:-60.65;CZ:-62.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-48;}{CAN:2;RS:-55;}{CAN:2;RS:-5;}{CAN:2;RS:-69;}{CAN:2;RS:-9;}{CAN:2;RS:-57;}{CAN:2;RS:-5;}{CAN:2;RS:-64;}{CAN:2;RS:-9;}{CAN:2;RS:-68;}{CAN:2;RS:-10;}{CAN:2;RS:-55;}{CAN:2;RS:-66;}{CAN:2;RS:-9;}{CAN:2;RS:-57;}{CAN:2;RS:-72;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;GT:120731;TS:16772;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100495.64;AT:26.7;AL:5.52;HM:35.32;AX:-0.94;AY:0.66;AZ:6.67;GX:-0.64;GY:-0.26;GZ:0.14;CX:102.29;CY:-48.38;CZ:-68.77;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-60;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120732;TS:18284;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100502.20;AT:26.8;AL:4.97;HM:35.17;AX:-1.41;AY:1.21;AZ:9.81;GX:-0.07;GY:-0.19;GZ:0.42;CX:98.30;CY:-56.50;CZ:-67.94;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-56;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120734;TS:19794;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100494.87;AT:26.8;AL:5.58;HM:35.03;AX:-1.87;AY:0.75;AZ:11.80;GX:0.16;GY:-0.03;GZ:0.57;CX:85.12;CY:-61.68;CZ:-70.61;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-60;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120735;TS:21306;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100491.48;AT:26.5;AL:5.87;HM:34.87;AX:-1.06;AY:0.82;AZ:9.97;GX:0.23;GY:0.30;GZ:0.12;CX:76.28;CY:-58.05;CZ:-71.11;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-68;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120737;TS:22811;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100485.38;AT:26.4;AL:6.38;HM:34.77;AX:-0.48;AY:1.18;AZ:8.99;GX:-0.25;GY:0.27;GZ:-0.54;CX:76.63;CY:-58.75;CZ:-70.44;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-61;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120738;TS:24323;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100495.03;AT:26.5;AL:5.57;HM:34.69;AX:-0.68;AY:0.91;AZ:8.19;GX:0.61;GY:0.06;GZ:-0.80;CX:87.20;CY:-63.41;CZ:-67.60;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-71;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120740;TS:25835;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100485.77;AT:26.6;AL:6.35;HM:34.65;AX:-0.55;AY:0.50;AZ:7.45;GX:0.46;GY:-0.52;GZ:-1.17;CX:102.11;CY:-49.24;CZ:-69.94;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-66;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120741;TS:27347;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100489.99;AT:26.6;AL:5.99;HM:34.61;AX:-0.72;AY:0.45;AZ:8.81;GX:0.84;GY:-0.26;GZ:-0.91;CX:87.38;CY:-33.52;CZ:-72.45;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-61;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;GT:120743;TS:28859;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100484.29;AT:26.5;AL:6.47;HM:34.56;AX:-0.49;AY:0.74;AZ:9.47;GX:0.52;GY:-0.26;GZ:-1.16;CX:75.07;CY:-48.55;CZ:-70.61;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-63;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;GT:120744;TS:30370;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100479.52;AT:26.3;AL:6.87;HM:34.52;AX:-0.90;AY:1.71;AZ:10.55;GX:-0.21;GY:0.16;GZ:-1.71;CX:92.40;CY:-62.72;CZ:-66.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-63;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120746;TS:31882;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100476.91;AT:26.4;AL:7.09;HM:34.52;AX:-1.34;AY:1.34;AZ:12.23;GX:0.17;GY:-0.47;GZ:-1.30;CX:92.40;CY:-34.38;CZ:-68.61;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-71;}{CAN:3;GT:120747;TS:33394;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100484.86;AT:26.4;AL:6.42;HM:34.48;AX:-0.77;AY:0.84;AZ:11.44;GX:0.26;GY:-0.53;GZ:-0.75;CX:79.06;CY:-41.12;CZ:-70.44;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-51;}{CAN:2;RS:-22;}{CAN:3;GT:120750;TS:36417;GA:0.0000;GO:0.0000;GH:0.00;GS:0.00;GV:0.00;G3:0;GN:0;AP:100470.66;AT:26.2;AL:7.61;HM:34.42;AX:-0.60;AY:0.77;AZ:7.56;GX:0.49;GY:0.29;GZ:-1.38;CX:95.18;CY:-60.65;CZ:-65.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-52;}{CAN:2;RS:-10;}{CAN:2;RS:-53;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-59;}{CAN:2;RS:-9;}{CAN:2;RS:-57;}{CAN:2;RS:-9;}{CAN:2;RS:-58;}{CAN:2;RS:-9;}{CAN:2;RS:-59;}{CAN:2;RS:-56;}{CAN:2;RS:-9;}{CAN:2;RS:-61;}{CAN:2;RS:-4;}{CAN:2;RS:-63;}{CAN:2;RS:-10;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120804;TS:50024;GA:51.9780;GO:4.5368;GH:-2.40;GS:0.23;GV:21.71;G3:1;GN:4;AP:100455.62;AT:26.7;AL:8.87;HM:34.57;AX:-0.46;AY:0.85;AZ:10.24;GX:-0.25;GY:-0.34;GZ:0.03;CX:78.71;CY:-62.89;CZ:-65.77;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-59;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120806;TS:51542;GA:51.9781;GO:4.5368;GH:3.70;GS:1.83;GV:159.25;G3:1;GN:4;AP:100451.78;AT:26.6;AL:9.20;HM:34.52;AX:-0.81;AY:0.35;AZ:7.94;GX:-0.05;GY:-0.88;GZ:0.25;CX:70.56;CY:-58.23;CZ:-67.60;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-56;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120807;TS:53060;GA:51.9781;GO:4.5368;GH:5.80;GS:1.63;GV:185.99;G3:1;GN:4;AP:100452.12;AT:26.5;AL:9.17;HM:34.46;AX:-0.70;AY:1.03;AZ:10.29;GX:0.14;GY:-0.20;GZ:-0.07;CX:71.77;CY:-54.25;CZ:-68.11;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120809;TS:54579;GA:51.9781;GO:4.5367;GH:9.00;GS:0.99;GV:193.52;G3:1;GN:4;AP:100448.30;AT:26.4;AL:9.49;HM:34.42;AX:-0.77;AY:0.96;AZ:11.90;GX:0.14;GY:-0.30;GZ:-0.29;CX:73.51;CY:-56.67;CZ:-66.77;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;GT:120810;TS:56097;GA:51.9781;GO:4.5367;GH:9.70;GS:0.33;GV:207.31;G3:1;GN:4;AP:100443.11;AT:26.3;AL:9.92;HM:34.34;AX:-0.54;AY:1.40;AZ:11.20;GX:0.40;GY:0.59;GZ:-0.49;CX:77.67;CY:-61.51;CZ:-65.77;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120812;TS:57615;GA:51.9781;GO:4.5367;GH:7.90;GS:0.33;GV:199.37;G3:1;GN:4;AP:100442.01;AT:26.3;AL:10.02;HM:34.31;AX:-0.96;AY:0.98;AZ:9.95;GX:0.15;GY:-0.28;GZ:0.17;CX:84.26;CY:-62.55;CZ:-66.10;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-72;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120813;TS:59138;GA:51.9781;GO:4.5367;GH:9.20;GS:1.50;GV:43.60;G3:1;GN:4;AP:100442.05;AT:26.2;AL:10.01;HM:34.29;AX:-0.39;AY:0.66;AZ:9.51;GX:0.10;GY:-0.25;GZ:0.28;CX:78.88;CY:-60.99;CZ:-68.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-64;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;GT:120815;TS:60656;GA:51.9782;GO:4.5368;GH:6.80;GS:2.42;GV:84.99;G3:1;GN:4;AP:100440.73;AT:26.1;AL:10.12;HM:34.23;AX:-0.64;AY:0.92;AZ:8.95;GX:-0.11;GY:0.72;GZ:-0.58;CX:76.80;CY:-60.65;CZ:-65.94;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-61;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:2;RS:-4;}{CAN:3;GT:120821;TS:66749;GA:51.9781;GO:4.5368;GH:3.20;GS:1.03;GV:116.43;G3:1;GN:4;AP:100439.61;AT:26.2;AL:10.22;HM:34.25;AX:-1.16;AY:0.84;AZ:10.13;GX:0.38;GY:0.07;GZ:-1.13;CX:82.52;CY:-40.78;CZ:-66.77;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-52;}{CAN:3;GT:120822;TS:68272;GA:51.9782;GO:4.5368;GH:3.20;GS:0.21;GV:259.02;G3:1;GN:4;AP:100430.20;AT:26.1;AL:11.01;HM:34.22;AX:-0.71;AY:0.74;AZ:9.73;GX:0.50;GY:-0.13;GZ:-1.55;CX:84.43;CY:-52.70;CZ:-63.26;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-51;}{CAN:2;RS:-59;}{CAN:2;RS:-9;}{CAN:2;RS:-58;}{CAN:2;RS:-10;}{CAN:2;RS:-54;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:2;RS:-60;}{CAN:2;RS:-3;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120832;TS:77414;GA:51.9781;GO:4.5368;GH:8.00;GS:0.16;GV:286.53;G3:1;GN:4;AP:100422.77;AT:26.5;AL:11.63;HM:34.12;AX:-0.64;AY:0.90;AZ:7.68;GX:-0.23;GY:0.19;GZ:-1.69;CX:88.76;CY:-32.14;CZ:-61.43;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-57;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120833;TS:78937;GA:51.9781;GO:4.5368;GH:8.20;GS:0.12;GV:246.07;G3:1;GN:4;AP:100415.61;AT:26.6;AL:12.23;HM:34.07;AX:-1.49;AY:0.97;AZ:10.25;GX:-0.04;GY:-0.81;GZ:-1.09;CX:70.73;CY:-51.83;CZ:-61.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120835;TS:80466;GA:51.9781;GO:4.5368;GH:8.40;GS:0.24;GV:43.13;G3:1;GN:4;AP:100416.80;AT:26.6;AL:12.13;HM:34.01;AX:-0.87;AY:0.76;AZ:7.37;GX:0.45;GY:0.29;GZ:-0.94;CX:87.90;CY:-66.87;CZ:-59.59;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-67;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120836;TS:81984;GA:51.9781;GO:4.5368;GH:8.40;GS:0.26;GV:48.74;G3:1;GN:4;AP:100412.48;AT:26.7;AL:12.49;HM:33.96;AX:-1.29;AY:1.05;AZ:9.91;GX:0.05;GY:0.42;GZ:-0.87;CX:104.19;CY:-53.73;CZ:-62.26;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-64;}{CAN:2;RS:-61;}{CAN:2;RS:-10;}{CAN:3;GT:120838;TS:83507;GA:51.9781;GO:4.5368;GH:8.00;GS:0.32;GV:38.64;G3:1;GN:4;AP:100412.04;AT:26.7;AL:12.53;HM:33.88;AX:-1.35;AY:1.11;AZ:13.76;GX:-0.28;GY:0.64;GZ:-0.67;CX:105.58;CY:-44.40;CZ:-61.93;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-65;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120839;TS:85031;GA:51.9781;GO:4.5368;GH:8.00;GS:0.11;GV:114.58;G3:1;GN:4;AP:100405.96;AT:26.7;AL:13.04;HM:33.81;AX:-0.49;AY:0.72;AZ:8.04;GX:0.50;GY:0.19;GZ:-0.25;CX:104.89;CY:-35.42;CZ:-60.26;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-77;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120841;TS:86554;GA:51.9781;GO:4.5368;GH:8.30;GS:0.26;GV:62.96;G3:1;GN:4;AP:100406.79;AT:26.8;AL:12.97;HM:33.75;AX:-0.70;AY:0.86;AZ:10.51;GX:-0.36;GY:-0.05;GZ:-0.49;CX:101.42;CY:-33.69;CZ:-65.27;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-57;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:120842;TS:88083;GA:51.9781;GO:4.5368;GH:8.70;GS:0.29;GV:88.08;G3:1;GN:4;AP:100398.84;AT:26.8;AL:13.64;HM:33.68;AX:-0.85;AY:0.76;AZ:7.34;GX:0.37;GY:-0.28;GZ:-0.61;CX:89.46;CY:-28.34;CZ:-65.43;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-68;}{CAN:2;RS:-61;}{CAN:3;GT:120844;TS:89606;GA:51.9781;GO:4.5368;GH:9.10;GS:0.06;GV:28.49;G3:1;GN:4;AP:100396.66;AT:26.8;AL:13.82;HM:33.62;AX:-0.94;AY:0.81;AZ:8.10;GX:0.29;GY:-0.02;GZ:-1.13;CX:70.73;CY:-37.67;CZ:-66.10;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-53;}{CAN:2;RS:-23;}{CAN:3;GT:120847;TS:92654;GA:51.9781;GO:4.5368;GH:10.30;GS:1.52;GV:184.34;G3:1;GN:4;AP:100395.78;AT:26.8;AL:13.89;HM:33.50;AX:-1.25;AY:1.24;AZ:13.68;GX:0.15;GY:0.15;GZ:-1.89;CX:102.11;CY:-36.11;CZ:-64.93;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-51;}{CAN:2;RS:-9;}{CAN:3;GT:120850;TS:95709;GA:51.9781;GO:4.5368;GH:11.80;GS:1.10;GV:157.95;G3:1;GN:5;AP:100390.83;AT:26.8;AL:14.31;HM:33.43;AX:-0.87;AY:0.83;AZ:9.83;GX:0.14;GY:0.16;GZ:-2.33;CX:103.15;CY:-40.95;CZ:-64.60;OC:0.00;O2:0.00;BV:4.00;}{CAN:2;RS:-52;}{CAN:2;RS:-54;}{CAN:2;RS:-67;}{CAN:2;RS:-5;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:2;RS:-65;}{CAN:2;RS:-5;}{CAN:2;RS:-71;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-66;}{CAN:2;RS:-5;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:2;RS:-55;}{CAN:2;RS:-61;}{CAN:2;RS:-65;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:2;RS:-4;}{CAN:3;GT:120858;TS:103348;GA:51.9782;GO:4.5370;GH:7.90;GS:3.98;GV:135.05;G3:1;GN:5;AP:100364.98;AT:26.5;AL:16.48;HM:33.38;AX:-0.55;AY:0.50;AZ:8.75;GX:0.06;GY:-0.19;GZ:-1.41;CX:74.89;CY:-36.63;CZ:-65.43;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-56;}{CAN:3;GT:120859;TS:104871;GA:51.9782;GO:4.5369;GH:8.90;GS:0.31;GV:135.05;G3:1;GN:5;AP:100366.27;AT:26.3;AL:16.37;HM:33.39;AX:-0.65;AY:0.72;AZ:9.51;GX:-0.03;GY:-0.27;GZ:-0.92;CX:72.29;CY:-60.99;CZ:-62.60;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-5;}{CAN:2;RS:-77;}{CAN:2;RS:-9;}{CAN:3;GT:120901;TS:106401;GA:51.9782;GO:4.5369;GH:7.80;GS:1.80;GV:105.55;G3:1;GN:4;AP:100375.48;AT:26.3;AL:15.60;HM:33.46;AX:-0.88;AY:1.37;AZ:8.37;GX:-0.44;GY:0.17;GZ:-0.47;CX:84.78;CY:-66.87;CZ:-59.26;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;GT:120902;TS:107924;GA:51.9782;GO:4.5369;GH:7.50;GS:2.41;GV:87.17;G3:1;GN:5;AP:100365.84;AT:26.3;AL:16.40;HM:33.52;AX:-1.61;AY:0.93;AZ:12.26;GX:0.72;GY:0.39;GZ:0.04;CX:85.99;CY:-65.31;CZ:-60.76;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-56;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;GT:120904;TS:109448;GA:51.9782;GO:4.5370;GH:4.50;GS:4.25;GV:175.59;G3:1;GN:5;AP:100366.38;AT:26.3;AL:16.36;HM:33.59;AX:-1.52;AY:0.04;AZ:12.46;GX:-0.26;GY:0.08;GZ:-0.24;CX:83.74;CY:-70.67;CZ:-58.59;OC:0.00;O2:0.00;BV:3.99;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:2;RS:-4;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;GT:120905;TS:110978;GA:51.9782;GO:4.5369;GH:7.30;GS:1.31;GV:168.58;G3:1;GN:5;AP:100362.31;AT:26.5;AL:16.70;HM:33.66;AX:-0.64;AY:1.27;AZ:8.47;GX:0.21;GY:0.64;GZ:-0.51;CX:85.99;CY:-67.73;CZ:-57.42;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:2;RS:-55;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;GT:120907;TS:112502;GA:51.9782;GO:4.5369;GH:9.80;GS:0.95;GV:327.88;G3:1;GN:5;AP:100360.91;AT:26.6;AL:16.82;HM:33.72;AX:-0.85;AY:1.04;AZ:11.20;GX:0.39;GY:0.20;GZ:-0.77;CX:95.01;CY:-56.67;CZ:-52.08;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-4;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:120908;TS:114027;GA:51.9782;GO:4.5369;GH:10.40;GS:1.28;GV:324.02;G3:1;GN:5;AP:100363.22;AT:26.6;AL:16.63;HM:33.75;AX:-0.92;AY:0.95;AZ:7.48;GX:0.37;GY:0.14;GZ:-1.21;CX:107.66;CY:-33.35;CZ:-57.92;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;GT:120910;TS:115557;GA:51.9783;GO:4.5369;GH:12.60;GS:1.04;GV:284.79;G3:1;GN:5;AP:100360.28;AT:26.7;AL:16.87;HM:33.78;AX:-1.23;AY:1.63;AZ:12.61;GX:-0.41;GY:0.14;GZ:-1.88;CX:69.00;CY:-31.79;CZ:-60.09;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-62;}{CAN:2;RS:-4;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-4;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:120913;TS:118617;GA:51.9783;GO:4.5369;GH:15.60;GS:0.42;GV:297.52;G3:1;GN:5;AP:100354.97;AT:26.7;AL:17.32;HM:33.76;AX:-1.36;AY:1.52;AZ:10.61;GX:0.29;GY:-0.05;GZ:-1.97;CX:89.46;CY:-20.04;CZ:-61.43;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-52;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:2;RS:-5;}{CAN:2;RS:-63;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-4;}{CAN:2;RS:-67;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:2;RS:-61;}{CAN:2;RS:-5;}{CAN:2;RS:-67;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:2;RS:-4;}{CAN:2;RS:-66;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;GT:120920;TS:126262;GA:51.9783;GO:4.5368;GH:19.70;GS:0.55;GV:324.72;G3:1;GN:5;AP:100338.55;AT:27.1;AL:18.70;HM:33.55;AX:-0.76;AY:0.48;AZ:9.49;GX:0.10;GY:0.26;GZ:-0.01;CX:53.92;CY:-53.73;CZ:-67.60;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:2;RS:-65;}{CAN:2;RS:-4;}{CAN:2;RS:-66;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-54;}{CAN:2;RS:-5;}{CAN:3;GT:120924;TS:129312;GA:51.9783;GO:4.5368;GH:21.50;GS:0.41;GV:324.72;G3:1;GN:5;AP:100331.53;AT:26.9;AL:19.28;HM:33.38;AX:-1.53;AY:0.67;AZ:10.08;GX:0.74;GY:-0.20;GZ:0.26;CX:79.75;CY:-15.90;CZ:-69.11;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;GT:120925;TS:130842;GA:51.9783;GO:4.5368;GH:21.80;GS:0.35;GV:324.72;G3:1;GN:5;AP:100331.08;AT:26.7;AL:19.32;HM:33.29;AX:-1.47;AY:0.60;AZ:13.27;GX:0.84;GY:-0.45;GZ:-0.74;CX:68.48;CY:-30.58;CZ:-70.61;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:2;RS:-67;}{CAN:2;RS:-9;}{CAN:3;GT:120927;TS:132372;GA:51.9783;GO:4.5368;GH:22.50;GS:0.12;GV:324.72;G3:1;GN:5;AP:100326.14;AT:26.6;AL:19.74;HM:33.23;AX:-0.20;AY:0.08;AZ:7.98;GX:-0.09;GY:-0.23;GZ:-1.28;CX:61.55;CY:-58.92;CZ:-67.60;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;GT:120928;TS:133901;GA:51.9783;GO:4.5368;GH:22.60;GS:0.28;GV:324.72;G3:1;GN:5;AP:100322.05;AT:26.6;AL:20.08;HM:33.23;AX:-0.68;AY:1.06;AZ:5.57;GX:0.33;GY:0.16;GZ:-1.98;CX:115.98;CY:-49.24;CZ:-63.93;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120930;TS:135431;GA:51.9782;GO:4.5368;GH:22.50;GS:1.55;GV:209.38;G3:1;GN:5;AP:100323.89;AT:26.5;AL:19.93;HM:33.23;AX:-0.76;AY:0.57;AZ:7.22;GX:-0.35;GY:0.37;GZ:-2.19;CX:58.77;CY:-43.71;CZ:-65.60;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:2;RS:-4;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;GT:120931;TS:136960;GA:51.9783;GO:4.5368;GH:23.50;GS:1.85;GV:50.39;G3:1;GN:5;AP:100318.00;AT:26.4;AL:20.42;HM:33.25;AX:-1.19;AY:1.28;AZ:8.46;GX:0.35;GY:-0.56;GZ:-1.18;CX:107.49;CY:-63.24;CZ:-58.42;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:3;GT:120933;TS:138489;GA:51.9782;GO:4.5368;GH:24.00;GS:0.18;GV:29.71;G3:1;GN:5;AP:100322.12;AT:26.5;AL:20.07;HM:33.30;AX:-1.53;AY:0.58;AZ:11.79;GX:0.13;GY:-0.14;GZ:-0.52;CX:122.40;CY:-46.31;CZ:-57.09;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:2;RS:-4;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:120934;TS:140019;GA:51.9782;GO:4.5368;GH:24.50;GS:0.22;GV:29.71;G3:1;GN:5;AP:100319.47;AT:26.6;AL:20.30;HM:33.36;AX:-1.07;AY:0.90;AZ:8.63;GX:0.08;GY:0.24;GZ:-1.10;CX:113.04;CY:-19.01;CZ:-65.77;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:3;GT:120936;TS:141542;GA:51.9782;GO:4.5368;GH:24.90;GS:0.64;GV:109.90;G3:1;GN:5;AP:100305.83;AT:26.6;AL:21.44;HM:33.41;AX:-0.96;AY:1.10;AZ:9.66;GX:-0.28;GY:0.32;GZ:-2.69;CX:53.22;CY:-53.73;CZ:-70.28;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:3;F:ASK;}{CAN:2;RS:-61;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-4;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;GT:120939;TS:144602;GA:51.9782;GO:4.5368;GH:25.60;GS:0.89;GV:131.12;G3:1;GN:5;AP:100300.34;AT:26.7;AL:21.90;HM:33.47;AX:-0.26;AY:0.76;AZ:3.81;GX:-0.26;GY:0.01;GZ:-4.14;CX:59.46;CY:-19.18;CZ:-70.94;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-53;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-63;}{CAN:3;F:ASK;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:2;RS:-5;}{CAN:2;RS:-73;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-5;}{CAN:2;RS:-69;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-56;}{CAN:2;RS:-71;}{CAN:2;RS:-5;}{CAN:2;RS:-70;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:2;RS:-57;}{CAN:2;RS:-5;}{CAN:2;RS:-70;}{CAN:2;RS:-5;}{CAN:2;RS:-71;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:2;RS:-75;}{CAN:2;RS:-6;}{CAN:2;RS:-76;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-56;}{CAN:2;RS:-6;}{CAN:2;RS:-75;}{CAN:2;RS:-6;}{CAN:2;RS:-75;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:2;RS:-74;}{CAN:3;GT:120950;TS:155291;GA:51.9782;GO:4.5368;GH:27.50;GS:0.06;GV:198.49;G3:1;GN:5;AP:100287.68;AT:27.0;AL:22.97;HM:34.33;AX:1.04;AY:4.40;AZ:8.33;GX:0.08;GY:0.06;GZ:-0.13;CX:46.81;CY:-79.82;CZ:-102.83;OC:0.00;O2:0.00;BV:3.98;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:2;RS:-10;}{CAN:3;GT:120951;TS:156816;GA:51.9782;GO:4.5368;GH:27.60;GS:0.04;GV:198.49;G3:1;GN:5;AP:100287.46;AT:26.9;AL:22.98;HM:34.59;AX:1.16;AY:4.50;AZ:7.98;GX:0.08;GY:0.10;GZ:-0.21;CX:47.85;CY:-79.82;CZ:-103.49;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-57;}{CAN:2;RS:-6;}{CAN:3;GT:120953;TS:158341;GA:51.9782;GO:4.5368;GH:27.70;GS:0.57;GV:198.49;G3:1;GN:5;AP:100286.36;AT:26.8;AL:23.08;HM:34.80;AX:-6.50;AY:4.11;AZ:7.59;GX:0.82;GY:0.17;GZ:-2.59;CX:110.09;CY:-86.22;CZ:-74.62;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:3;GT:120954;TS:159871;GA:51.9782;GO:4.5368;GH:27.80;GS:2.15;GV:335.66;G3:1;GN:5;AP:100298.05;AT:26.9;AL:22.10;HM:34.94;AX:-4.61;AY:-8.70;AZ:1.64;GX:0.05;GY:0.64;GZ:0.32;CX:117.72;CY:1.21;CZ:-16.53;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:2;RS:-57;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;GT:120956;TS:161395;GA:51.9783;GO:4.5368;GH:28.10;GS:1.83;GV:23.68;G3:1;GN:6;AP:100310.75;AT:26.9;AL:21.03;HM:34.98;AX:-3.41;AY:-7.70;AZ:0.06;GX:0.90;GY:1.41;GZ:0.24;CX:100.03;CY:4.67;CZ:-28.21;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;GT:120957;TS:162918;GA:51.9783;GO:4.5368;GH:28.40;GS:1.41;GV:5.48;G3:1;GN:6;AP:100306.50;AT:27.0;AL:21.39;HM:35.01;AX:-6.60;AY:5.56;AZ:4.69;GX:-0.03;GY:-0.91;GZ:-1.34;CX:127.94;CY:-54.25;CZ:-46.07;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;GT:120959;TS:164447;GA:51.9783;GO:4.5368;GH:28.50;GS:0.86;GV:330.28;G3:1;GN:6;AP:100286.92;AT:26.9;AL:23.03;HM:34.93;AX:0.82;AY:-9.54;AZ:-0.33;GX:0.04;GY:0.40;GZ:1.60;CX:65.19;CY:-1.38;CZ:-36.72;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:2;RS:-58;}{CAN:2;RS:-6;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;GT:121000;TS:165970;GA:51.9783;GO:4.5368;GH:28.80;GS:1.02;GV:312.94;G3:1;GN:6;AP:100301.25;AT:26.9;AL:21.83;HM:34.85;AX:-7.69;AY:3.17;AZ:4.55;GX:0.39;GY:-0.09;GZ:0.35;CX:120.49;CY:-40.26;CZ:-58.26;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;GT:121002;TS:167500;GA:51.9783;GO:4.5368;GH:29.10;GS:0.50;GV:292.03;G3:1;GN:6;AP:100294.70;AT:27.0;AL:22.38;HM:34.82;AX:-5.30;AY:-5.46;AZ:6.16;GX:0.57;GY:0.11;GZ:-0.96;CX:102.46;CY:-24.02;CZ:-67.60;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:2;RS:-91;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:2;RS:-56;}{CAN:2;RS:-6;}{CAN:2;RS:-83;}{CAN:2;RS:-7;}{CAN:2;RS:-87;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-84;}{CAN:2;RS:-6;}{CAN:2;RS:-84;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-7;}{CAN:2;RS:-84;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:2;RS:-59;}{CAN:2;RS:-7;}{CAN:2;RS:-92;}{CAN:2;RS:-9;}{CAN:2;RS:-92;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-91;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:3;GT:121016;TS:181251;GA:51.9783;GO:4.5368;GH:30.10;GS:0.07;GV:292.03;G3:1;GN:6;AP:100300.28;AT:27.0;AL:21.91;HM:34.18;AX:-5.68;AY:-7.82;AZ:2.43;GX:0.24;GY:0.15;GZ:-0.07;CX:102.63;CY:-9.68;CZ:-57.09;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-100;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;GT:121017;TS:182780;GA:51.9783;GO:4.5368;GH:30.30;GS:0.29;GV:292.03;G3:1;GN:6;AP:100293.34;AT:27.0;AL:22.49;HM:34.12;AX:-5.74;AY:5.59;AZ:5.99;GX:0.13;GY:-0.26;GZ:-1.25;CX:100.38;CY:-65.83;CZ:-67.60;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-59;}{CAN:2;RS:-7;}{CAN:3;GT:121019;TS:184310;GA:51.9783;GO:4.5368;GH:30.40;GS:0.28;GV:292.03;G3:1;GN:6;AP:100295.31;AT:27.0;AL:22.32;HM:34.10;AX:-1.04;AY:0.71;AZ:10.53;GX:-0.87;GY:1.97;GZ:0.63;CX:66.40;CY:-53.39;CZ:-70.94;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:121020;TS:185840;GA:51.9783;GO:4.5368;GH:30.90;GS:0.46;GV:292.03;G3:1;GN:6;AP:100293.50;AT:27.0;AL:22.48;HM:34.10;AX:-0.49;AY:3.78;AZ:8.57;GX:0.27;GY:0.17;GZ:-0.18;CX:82.87;CY:-45.27;CZ:-75.78;OC:0.00;O2:0.00;BV:3.98;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;GT:121022;TS:187365;GA:51.9783;GO:4.5368;GH:31.00;GS:0.27;GV:292.03;G3:1;GN:6;AP:100293.90;AT:27.0;AL:22.44;HM:34.06;AX:-1.26;AY:1.05;AZ:11.27;GX:-0.20;GY:-1.36;GZ:-0.42;CX:85.64;CY:-34.21;CZ:-73.78;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-103;}{CAN:3;F:ASK;}{CAN:2;RS:-91;}{CAN:3;GT:121023;TS:188895;GA:51.9783;GO:4.5368;GH:31.00;GS:0.29;GV:292.03;G3:1;GN:6;AP:100295.48;AT:27.0;AL:22.31;HM:34.04;AX:-0.78;AY:1.12;AZ:9.39;GX:0.60;GY:0.03;GZ:-0.29;CX:75.24;CY:-32.83;CZ:-73.45;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-101;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;GT:121025;TS:190418;GA:51.9783;GO:4.5368;GH:30.80;GS:0.07;GV:292.03;G3:1;GN:6;AP:100294.62;AT:27.1;AL:22.38;HM:34.01;AX:-0.97;AY:0.81;AZ:9.12;GX:0.47;GY:0.24;GZ:-0.33;CX:71.43;CY:-40.78;CZ:-72.45;OC:0.00;O2:0.00;BV:3.99;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;GT:121026;TS:191943;GA:51.9783;GO:4.5368;GH:30.80;GS:0.12;GV:292.03;G3:1;GN:6;AP:100296.55;AT:27.1;AL:22.22;HM:33.94;AX:-1.13;AY:1.12;AZ:9.28;GX:-0.10;GY:0.58;GZ:-1.12;CX:71.08;CY:-51.14;CZ:-73.78;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:3;GT:121028;TS:193473;GA:51.9783;GO:4.5368;GH:30.70;GS:0.16;GV:292.03;G3:1;GN:6;AP:100290.59;AT:27.1;AL:22.72;HM:33.91;AX:5.41;AY:0.08;AZ:7.42;GX:1.02;GY:0.12;GZ:-0.54;CX:52.01;CY:-58.05;CZ:-58.76;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-86;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-97;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-103;}{CAN:2;RS:-104;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-103;}{CAN:3;F:ASK;}{CAN:2;RS:-99;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-91;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-97;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:2;RS:-59;}{CAN:2;RS:-8;}{CAN:3;F:ASK;}{CAN:2;RS:-98;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:2;RS:-59;}{CAN:2;RS:-8;}{CAN:2;RS:-89;}{CAN:2;RS:-8;}{CAN:2;RS:-90;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-97;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:2;RS:-7;}{CAN:2;RS:-89;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-89;}{CAN:2;RS:-8;}{CAN:2;RS:-89;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-103;}{CAN:2;RS:-59;}{CAN:2;RS:-7;}{CAN:2;RS:-91;}{CAN:2;RS:-7;}{CAN:2;RS:-90;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:3;GT:121043;TS:208723;GA:51.9783;GO:4.5367;GH:30.80;GS:0.46;GV:228.08;G3:1;GN:7;AP:100292.23;AT:27.4;AL:22.58;HM:35.27;AX:1.26;AY:-0.97;AZ:7.25;GX:0.40;GY:-1.31;GZ:0.86;CX:57.21;CY:-21.42;CZ:-67.44;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-58;}{CAN:2;RS:-9;}{CAN:3;GT:121045;TS:210248;GA:51.9783;GO:4.5367;GH:30.30;GS:0.68;GV:210.26;G3:1;GN:7;AP:100304.48;AT:27.4;AL:21.55;HM:35.45;AX:3.86;AY:-0.74;AZ:7.95;GX:-0.22;GY:0.43;GZ:-1.03;CX:48.89;CY:-28.34;CZ:-60.09;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:2;RS:-9;}{CAN:3;GT:121046;TS:211778;GA:51.9783;GO:4.5367;GH:29.80;GS:1.00;GV:199.44;G3:1;GN:7;AP:100307.21;AT:27.4;AL:21.33;HM:35.61;AX:2.83;AY:-1.12;AZ:8.28;GX:-0.35;GY:-1.05;GZ:0.39;CX:61.37;CY:-32.83;CZ:-62.43;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;GT:121048;TS:213308;GA:51.9783;GO:4.5367;GH:29.10;GS:1.17;GV:85.93;G3:1;GN:7;AP:100312.94;AT:27.4;AL:20.84;HM:35.73;AX:4.24;AY:0.64;AZ:9.74;GX:0.56;GY:-0.23;GZ:0.71;CX:69.52;CY:-38.18;CZ:-74.62;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:2;RS:-59;}{CAN:2;RS:-7;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;GT:121049;TS:214832;GA:51.9783;GO:4.5367;GH:28.90;GS:0.82;GV:84.46;G3:1;GN:7;AP:100328.84;AT:27.2;AL:19.51;HM:35.77;AX:3.65;AY:-0.04;AZ:6.58;GX:0.03;GY:-0.22;GZ:-0.99;CX:75.07;CY:-25.40;CZ:-67.94;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;GT:121051;TS:216355;GA:51.9783;GO:4.5368;GH:28.50;GS:0.89;GV:111.12;G3:1;GN:7;AP:100335.20;AT:27.2;AL:18.98;HM:35.84;AX:4.46;AY:-1.12;AZ:7.42;GX:-0.29;GY:-0.39;GZ:0.45;CX:73.51;CY:-31.45;CZ:-61.76;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-91;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:2;RS:-58;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-96;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:121052;TS:217885;GA:51.9783;GO:4.5368;GH:28.50;GS:0.92;GV:143.56;G3:1;GN:7;AP:100344.33;AT:27.2;AL:18.21;HM:35.88;AX:2.63;AY:1.12;AZ:8.51;GX:0.35;GY:-0.19;GZ:0.94;CX:80.96;CY:-35.77;CZ:-61.93;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:2;RS:-97;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:3;GT:121054;TS:219409;GA:51.9783;GO:4.5368;GH:28.40;GS:0.43;GV:143.56;G3:1;GN:7;AP:100336.80;AT:27.3;AL:18.84;HM:35.88;AX:4.55;AY:1.16;AZ:9.60;GX:-0.24;GY:-0.20;GZ:-0.76;CX:76.28;CY:-61.16;CZ:-62.76;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-81;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-97;}{CAN:3;GT:121055;TS:220939;GA:51.9783;GO:4.5368;GH:28.30;GS:1.21;GV:103.80;G3:1;GN:7;AP:100346.98;AT:27.3;AL:17.99;HM:35.86;AX:3.67;AY:0.23;AZ:7.25;GX:0.74;GY:-0.16;GZ:0.37;CX:77.32;CY:-55.29;CZ:-58.42;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-72;}{CAN:2;RS:-83;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:2;RS:-76;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-82;}{CAN:2;RS:-7;}{CAN:2;RS:-81;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:2;RS:-57;}{CAN:2;RS:-7;}{CAN:2;RS:-81;}{CAN:2;RS:-6;}{CAN:2;RS:-84;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:2;RS:-74;}{CAN:2;RS:-6;}{CAN:2;RS:-77;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-79;}{CAN:2;RS:-6;}{CAN:2;RS:-76;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;GT:121109;TS:234659;GA:51.9782;GO:4.5367;GH:29.40;GS:0.62;GV:186.42;G3:1;GN:7;AP:100389.88;AT:27.0;AL:14.39;HM:35.39;AX:3.08;AY:1.64;AZ:7.32;GX:0.07;GY:-0.26;GZ:0.15;CX:66.23;CY:-44.23;CZ:-59.43;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-93;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:121110;TS:236184;GA:51.9782;GO:4.5367;GH:29.30;GS:0.67;GV:130.78;G3:1;GN:7;AP:100390.36;AT:26.9;AL:14.35;HM:35.44;AX:5.70;AY:0.25;AZ:10.08;GX:-0.14;GY:0.14;GZ:0.21;CX:61.03;CY:-34.21;CZ:-57.42;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:2;RS:-6;}{CAN:3;GT:121112;TS:237709;GA:51.9782;GO:4.5367;GH:29.40;GS:0.78;GV:128.00;G3:1;GN:7;AP:100398.55;AT:26.9;AL:13.66;HM:35.52;AX:4.77;AY:1.00;AZ:9.00;GX:-0.17;GY:0.95;GZ:0.45;CX:62.59;CY:-36.80;CZ:-55.59;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;GT:121114;TS:239234;GA:51.9782;GO:4.5367;GH:29.20;GS:0.80;GV:161.22;G3:1;GN:7;AP:100409.30;AT:26.9;AL:12.76;HM:35.61;AX:2.66;AY:0.44;AZ:8.98;GX:-0.46;GY:-0.46;GZ:-0.39;CX:71.08;CY:-39.74;CZ:-57.76;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:2;RS:-58;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-91;}{CAN:3;GT:121115;TS:240764;GA:51.9782;GO:4.5367;GH:29.30;GS:0.48;GV:144.23;G3:1;GN:7;AP:100406.64;AT:26.9;AL:12.98;HM:35.70;AX:4.07;AY:1.71;AZ:10.91;GX:1.82;GY:0.31;GZ:0.76;CX:86.16;CY:-36.80;CZ:-59.26;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:2;RS:-80;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;GT:121117;TS:242289;GA:51.9783;GO:4.5367;GH:29.50;GS:2.38;GV:354.35;G3:1;GN:7;AP:100409.81;AT:27.0;AL:12.72;HM:35.84;AX:4.01;AY:-0.97;AZ:12.46;GX:-0.40;GY:0.53;GZ:-0.05;CX:82.35;CY:-54.77;CZ:-60.93;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:2;RS:-60;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;GT:121118;TS:243819;GA:51.9783;GO:4.5367;GH:29.60;GS:2.81;GV:344.33;G3:1;GN:7;AP:100408.31;AT:27.0;AL:12.84;HM:35.97;AX:2.23;AY:0.17;AZ:9.38;GX:0.17;GY:0.23;GZ:0.70;CX:70.04;CY:-62.20;CZ:-59.76;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:2;RS:-57;}{CAN:2;RS:-7;}{CAN:3;F:ASK;}{CAN:2;RS:-104;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;GT:121120;TS:245342;GA:51.9783;GO:4.5367;GH:29.50;GS:0.73;GV:306.13;G3:1;GN:7;AP:100413.72;AT:27.1;AL:12.39;HM:36.14;AX:2.87;AY:-0.04;AZ:8.57;GX:-0.66;GY:-0.39;GZ:-0.53;CX:64.49;CY:-61.16;CZ:-56.09;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;GT:121121;TS:246872;GA:51.9783;GO:4.5367;GH:29.20;GS:0.93;GV:293.64;G3:1;GN:7;AP:100424.69;AT:27.1;AL:11.47;HM:36.33;AX:2.70;AY:0.35;AZ:5.66;GX:-0.06;GY:0.33;GZ:-0.59;CX:66.92;CY:-57.36;CZ:-55.42;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-77;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:2;RS:-85;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-88;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-89;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:2;RS:-57;}{CAN:2;RS:-76;}{CAN:2;RS:-6;}{CAN:2;RS:-76;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:2;RS:-57;}{CAN:2;RS:-7;}{CAN:2;RS:-80;}{CAN:2;RS:-7;}{CAN:2;RS:-100;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-100;}{CAN:3;F:ASK;}{CAN:2;RS:-87;}{CAN:2;RS:-91;}{CAN:2;RS:-8;}{CAN:2;RS:-84;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-90;}{CAN:2;RS:-58;}{CAN:2;RS:-7;}{CAN:2;RS:-89;}{CAN:2;RS:-8;}{CAN:2;RS:-88;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-95;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:2;RS:-57;}{CAN:2;RS:-8;}{CAN:2;RS:-89;}{CAN:2;RS:-7;}{CAN:2;RS:-80;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-97;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:121136;TS:262124;GA:51.9783;GO:4.5368;GH:25.90;GS:3.94;GV:313.04;G3:1;GN:7;AP:100468.06;AT:27.0;AL:7.83;HM:36.98;AX:4.13;AY:-1.44;AZ:5.75;GX:-0.42;GY:0.41;GZ:-1.19;CX:75.07;CY:-35.42;CZ:-60.26;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-92;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;GT:121138;TS:263647;GA:51.9783;GO:4.5367;GH:23.50;GS:2.63;GV:335.63;G3:1;GN:7;AP:100472.70;AT:27.0;AL:7.44;HM:36.83;AX:4.12;AY:0.26;AZ:8.51;GX:0.71;GY:0.99;GZ:0.04;CX:92.23;CY:-28.68;CZ:-63.43;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:2;RS:-57;}{CAN:2;RS:-7;}{CAN:3;GT:121139;TS:265165;GA:51.9783;GO:4.5367;GH:22.30;GS:0.82;GV:138.25;G3:1;GN:7;AP:100469.69;AT:27.0;AL:7.70;HM:36.69;AX:2.52;AY:-1.00;AZ:5.86;GX:-0.17;GY:-0.25;GZ:0.20;CX:74.20;CY:-58.40;CZ:-65.77;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;GT:121141;TS:266689;GA:51.9783;GO:4.5367;GH:20.80;GS:0.46;GV:233.54;G3:1;GN:7;AP:100461.15;AT:27.0;AL:8.41;HM:36.58;AX:2.50;AY:0.07;AZ:8.73;GX:0.34;GY:0.60;GZ:0.33;CX:75.59;CY:-56.33;CZ:-65.10;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-66;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-56;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;GT:121143;TS:268207;GA:51.9783;GO:4.5367;GH:21.00;GS:0.71;GV:258.84;G3:1;GN:7;AP:100467.01;AT:27.0;AL:7.92;HM:36.56;AX:3.17;AY:1.71;AZ:9.15;GX:-0.17;GY:-0.01;GZ:-0.46;CX:60.68;CY:-51.83;CZ:-60.76;OC:0.00;O2:0.00;BV:3.97;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-79;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-81;}{CAN:3;GT:121144;TS:269730;GA:51.9783;GO:4.5367;GH:20.70;GS:0.31;GV:258.84;G3:1;GN:7;AP:100476.22;AT:27.0;AL:7.15;HM:36.56;AX:4.41;AY:0.40;AZ:12.54;GX:0.13;GY:0.55;GZ:-0.49;CX:63.28;CY:-47.17;CZ:-57.59;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-82;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;GT:121146;TS:271255;GA:51.9783;GO:4.5367;GH:20.00;GS:0.54;GV:258.84;G3:1;GN:7;AP:100487.66;AT:27.1;AL:6.19;HM:36.59;AX:6.21;AY:-0.93;AZ:11.96;GX:0.68;GY:1.86;GZ:-0.69;CX:74.20;CY:-49.07;CZ:-56.75;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:2;RS:-56;}{CAN:2;RS:-6;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:2;RS:-71;}{CAN:3;GT:121147;TS:272780;GA:51.9783;GO:4.5367;GH:19.60;GS:0.78;GV:258.41;G3:1;GN:7;AP:100497.33;AT:27.1;AL:5.38;HM:36.68;AX:2.07;AY:-1.25;AZ:9.46;GX:0.30;GY:-0.00;GZ:0.65;CX:60.51;CY:-53.39;CZ:-57.59;OC:0.00;O2:0.00;BV:3.96;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;GT:121149;TS:274303;GA:51.9783;GO:4.5367;GH:19.70;GS:1.10;GV:220.19;G3:1;GN:7;AP:100497.30;AT:27.1;AL:5.38;HM:36.71;AX:2.38;AY:-0.47;AZ:6.60;GX:-0.21;GY:-0.25;GZ:-0.62;CX:59.64;CY:-45.96;CZ:-56.42;OC:0.00;O2:0.00;BV:3.98;}{CAN:2;RS:-71;}{CAN:2;RS:-27;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-70;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:2;RS:-69;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-68;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-86;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-85;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:2;RS:-77;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-56;}{CAN:2;RS:-6;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-83;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:3;F:ASK;}{CAN:2;RS:-69;}{CAN:2;RS:-56;}{CAN:2;RS:-5;}{CAN:2;RS:-68;}{CAN:2;RS:-5;}{CAN:2;RS:-71;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-72;}{CAN:2;RS:-71;}{CAN:2;RS:-6;}{CAN:2;RS:-72;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:2;RS:-56;}{CAN:2;RS:-6;}{CAN:2;RS:-75;}{CAN:2;RS:-6;}{CAN:2;RS:-74;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-80;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-76;}{CAN:2;RS:-57;}{CAN:2;RS:-6;}{CAN:2;RS:-75;}{CAN:2;RS:-7;}{CAN:2;RS:-76;}{CAN:2;RS:-10;}{CAN:3;F:ASK;}{CAN:2;RS:-79;}{CAN:3;F:ASK;}{CAN:2;RS:-84;}{CAN:3;GT:121204;TS:289574;GA:51.9783;GO:4.5368;GH:18.20;GS:1.31;GV:190.81;G3:1;GN:7;AP:100541.77;AT:27.0;AL:1.65;HM:36.26;AX:3.35;AY:-0.61;AZ:10.11;GX:-0.33;GY:0.98;GZ:-0.74;CX:61.20;CY:-43.71;CZ:-74.95;OC:0.00;O2:0.00;BV:3.95;}{CAN:2;RS:-74;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-75;}{CAN:2;RS:-56;}{CAN:2;RS:-9;}{CAN:3;GT:121205;TS:291110;GA:51.9783;GO:4.5367;GH:18.20;GS:1.55;GV:233.39;G3:1;GN:7;AP:100540.53;AT:27.0;AL:1.76;HM:36.18;AX:0.69;AY:5.10;AZ:8.47;GX:-0.74;GY:0.35;GZ:0.07;CX:62.41;CY:-59.44;CZ:-70.44;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-75;}{CAN:3;F:ASK;}{CAN:2;RS:-73;}{CAN:3;F:ASK;}{CAN:2;RS:-71;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;GT:121207;TS:292635;GA:51.9783;GO:4.5367;GH:18.20;GS:2.13;GV:196.84;G3:1;GN:7;AP:100542.69;AT:27.0;AL:1.58;HM:36.13;AX:1.18;AY:-1.32;AZ:11.91;GX:0.24;GY:0.80;GZ:-0.27;CX:56.17;CY:-30.41;CZ:-59.76;OC:0.00;O2:0.00;BV:3.95;}{CAN:2;RS:-65;}{CAN:3;F:ASK;}{CAN:2;RS:-67;}{CAN:3;F:ASK;}{CAN:2;RS:-64;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-60;}{CAN:3;GT:121209;TS:294166;GA:51.9782;GO:4.5367;GH:18.10;GS:3.05;GV:181.57;G3:1;GN:7;AP:100539.54;AT:27.0;AL:1.84;HM:36.01;AX:3.23;AY:0.73;AZ:8.86;GX:-0.38;GY:1.01;GZ:-0.05;CX:53.05;CY:-33.17;CZ:-55.75;OC:0.00;O2:0.00;BV:3.96;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-51;}{CAN:3;F:ASK;}{CAN:2;RS:-57;}{CAN:3;F:ASK;}{CAN:2;RS:-53;}{CAN:2;RS:-54;}{CAN:3;GT:121210;TS:295691;GA:51.9782;GO:4.5367;GH:17.90;GS:2.53;GV:200.91;G3:1;GN:7;AP:100536.40;AT:27.0;AL:2.10;HM:35.93;AX:2.40;AY:-1.06;AZ:9.72;GX:-0.27;GY:0.70;GZ:-0.72;CX:53.22;CY:-35.07;CZ:-60.59;OC:0.00;O2:0.00;BV:3.96;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;F:ASK;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:2;RS:-44;}{CAN:3;F:ASK;}{CAN:2;RS:-47;}{CAN:3;GT:121212;TS:297222;GA:51.9782;GO:4.5367;GH:17.70;GS:2.29;GV:203.74;G3:1;GN:7;AP:100542.92;AT:26.9;AL:1.56;HM:35.84;AX:0.14;AY:-3.09;AZ:8.84;GX:0.28;GY:-0.03;GZ:-0.43;CX:63.45;CY:-40.09;CZ:-65.10;OC:0.00;O2:0.00;BV:3.95;}{CAN:2;RS:-41;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-42;}{CAN:3;F:ASK;}{CAN:2;RS:-38;}{CAN:3;GT:121213;TS:298747;GA:51.9782;GO:4.5367;GH:17.40;GS:1.33;GV:190.25;G3:1;GN:7;AP:100555.11;AT:26.9;AL:0.54;HM:35.81;AX:-4.66;AY:3.51;AZ:8.43;GX:-0.27;GY:1.08;GZ:0.93;CX:86.51;CY:-56.85;CZ:-72.28;OC:0.00;O2:0.00;BV:3.95;}{CAN:2;RS:-43;}{CAN:3;F:ASK;}{CAN:2;RS:-40;}{CAN:3;F:ASK;}{CAN:2;RS:-39;}{CAN:3;F:ASK;}{CAN:2;RS:-42;}{CAN:3;F:ASK;}{CAN:2;RS:-40;}{CAN:3;F:ASK;}{CAN:2;RS:-39;}{CAN:3;GT:121215;TS:300272;GA:51.9782;GO:4.5367;GH:17.00;GS:0.32;GV:191.18;G3:1;GN:7;AP:100548.40;AT:27.0;AL:1.10;HM:35.79;AX:-5.05;AY:2.26;AZ:6.43;GX:-0.88;GY:0.63;GZ:0.68;CX:94.14;CY:-55.81;CZ:-70.28;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-41;}{CAN:3;F:ASK;}{CAN:2;RS:-38;}{CAN:3;F:ASK;}{CAN:2;RS:-40;}{CAN:3;F:ASK;}{CAN:2;RS:-48;}{CAN:3;F:ASK;}{CAN:2;RS:-49;}{CAN:3;F:ASK;}{CAN:2;RS:-41;}{CAN:3;GT:121216;TS:301797;GA:51.9782;GO:4.5367;GH:17.00;GS:0.34;GV:191.18;G3:1;GN:7;AP:100553.89;AT:27.0;AL:0.64;HM:35.79;AX:-3.79;AY:2.47;AZ:6.88;GX:-1.86;GY:-1.86;GZ:0.52;CX:94.66;CY:-57.02;CZ:-67.77;OC:0.00;O2:0.00;BV:3.95;}{CAN:2;RS:-19;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;GT:121218;TS:303322;GA:51.9782;GO:4.5367;GH:16.60;GS:0.21;GV:191.18;G3:1;GN:7;AP:100558.71;AT:26.9;AL:0.24;HM:35.72;AX:-0.89;AY:1.63;AZ:9.13;GX:0.12;GY:0.16;GZ:-0.17;CX:69.69;CY:-47.00;CZ:-69.77;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-17;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;GT:121219;TS:304847;GA:51.9782;GO:4.5367;GH:16.60;GS:0.21;GV:191.18;G3:1;GN:7;AP:100554.06;AT:26.9;AL:0.62;HM:35.64;AX:-0.81;AY:1.59;AZ:9.23;GX:0.07;GY:0.07;GZ:-0.16;CX:67.79;CY:-45.79;CZ:-69.27;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-17;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;GT:121221;TS:306378;GA:51.9782;GO:4.5367;GH:16.20;GS:0.09;GV:191.18;G3:1;GN:7;AP:100555.49;AT:26.9;AL:0.50;HM:35.55;AX:-0.81;AY:1.60;AZ:9.19;GX:0.09;GY:0.09;GZ:-0.16;CX:68.83;CY:-47.51;CZ:-70.61;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-17;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:3;GT:121222;TS:307903;GA:51.9782;GO:4.5367;GH:16.00;GS:0.05;GV:191.18;G3:1;GN:7;AP:100553.83;AT:26.9;AL:0.64;HM:35.47;AX:-0.84;AY:1.61;AZ:9.19;GX:0.08;GY:0.08;GZ:-0.16;CX:70.39;CY:-47.00;CZ:-70.11;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-18;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:2;RS:-21;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:2;RS:-4;}{CAN:3;GT:121225;TS:310951;GA:51.9782;GO:4.5367;GH:15.50;GS:0.00;GV:191.18;G3:1;GN:7;AP:100553.22;AT:27.0;AL:0.70;HM:35.30;AX:-0.79;AY:1.59;AZ:9.22;GX:0.07;GY:0.06;GZ:-0.15;CX:69.87;CY:-48.90;CZ:-70.61;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:3;GT:121227;TS:312480;GA:51.9782;GO:4.5367;GH:15.30;GS:0.00;GV:191.18;G3:1;GN:7;AP:100550.71;AT:27.0;AL:0.91;HM:35.26;AX:-0.82;AY:1.61;AZ:9.14;GX:0.10;GY:0.11;GZ:-0.16;CX:68.31;CY:-46.31;CZ:-69.77;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-23;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-23;}{CAN:2;RS:-22;}{CAN:2;RS:-9;}{CAN:3;F:ASK;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-16;}{CAN:3;F:ASK;}{CAN:3;GT:121230;TS:315528;GA:51.9782;GO:4.5367;GH:14.90;GS:0.02;GV:191.18;G3:1;GN:7;AP:100554.92;AT:27.0;AL:0.55;HM:35.27;AX:-0.89;AY:1.67;AZ:9.18;GX:0.10;GY:0.12;GZ:-0.17;CX:69.17;CY:-48.21;CZ:-69.61;OC:0.00;O2:0.00;BV:3.96;}{CAN:2;RS:-22;}{CAN:3;F:ASK;}{CAN:2;RS:-21;}{CAN:3;F:ASK;}{CAN:2;RS:-25;}{CAN:3;F:ASK;}{CAN:2;RS:-39;}{CAN:3;GT:121231;TS:317053;GA:51.9782;GO:4.5367;GH:14.80;GS:0.19;GV:162.96;G3:1;GN:7;AP:100562.62;AT:26.9;AL:-0.09;HM:35.32;AX:-7.23;AY:5.07;AZ:3.65;GX:-0.63;GY:1.47;GZ:1.62;CX:123.96;CY:-44.75;CZ:-51.25;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-26;}{CAN:3;F:ASK;}{CAN:2;RS:-25;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-18;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-24;}{CAN:3;GT:121233;TS:318589;GA:51.9782;GO:4.5367;GH:14.70;GS:0.30;GV:162.96;G3:1;GN:7;AP:100562.76;AT:27.0;AL:-0.10;HM:35.43;AX:-9.25;AY:3.76;AZ:1.29;GX:-0.43;GY:0.72;GZ:-0.06;CX:130.55;CY:-43.37;CZ:-29.55;OC:0.00;O2:0.00;BV:3.94;}{CAN:2;RS:-32;}{CAN:3;F:ASK;}{CAN:3;F:ASK;}{CAN:2;RS:-39;}{CAN:2;RS:-32;}{CAN:2;RS:-4;}{CAN:3;F:ASK;}{CAN:2;RS:-33;}{CAN:3;F:ASK;}{CAN:2;RS:-37;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-60;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-58;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-9;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-58;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-9;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-59;}{CAN:2;RS:-10;}{CAN:2;RS:-60;}{CAN:2;RS:-10;}{CAN:2;RS:-60;}{CAN:2;RS:-9;}{CAN:2;RS:-60;}{CAN:2;RS:-10;}{CAN:2;RS:-61;}{CAN:2;RS:-9;}");
      }
    };

    this.elements.add(this.openPinsButton);
    this.elements.add(this.closePinsButton);
    this.elements.add(this.openRingButton);
    this.elements.add(this.closeRingButton);
    this.elements.add(this.flightMode);
    this.elements.add(this.sendAllData);
    this.elements.add(this.clearData);
    //this.elements.add(this.pushData);
  }

  public void viewResizeTriggered(){
    this.openPinsButton.resize(this.dim.x/4, this.dim.y/2 - 75, 100);
    this.closePinsButton.resize(this.dim.x/4, this.dim.y/2 - 25, 100);
    this.openRingButton.resize(this.dim.x/4, this.dim.y/2 + 25, 100);
    this.closeRingButton.resize(this.dim.x/4, this.dim.y/2 + 75, 100);

    this.flightMode.resize(3*this.dim.x/4, this.dim.y/2 - 80, 100);
    this.sendAllData.resize(3*this.dim.x/4, this.dim.y/2, 100);
    this.clearData.resize(3*this.dim.x/4, this.dim.y/2 + 50, 100);
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 15"));
    textAlign(CENTER);
    fill(0);
    text("These actions are performed without applying\nany safety check.", this.dim.x/2, 50);

    stroke(0);
    strokeWeight(1);
    line(this.dim.x/4 - 25, this.dim.y/2, this.dim.x/4 + 125, this.dim.y/2);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
//import javafx.scene.chart.Chart;

// View_DataCharts.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_DataCharts extends ViewController {
	public VerticalScrollElement scrollBar;
	public ArrayList<Chart> charts;
  public float chartHeight;

  public ButtonElement selectMuDataButton;
  public ButtonElement selectBetaDataButton;
  public ButtonElement selectRhoDataButton;
  public float selectButtonWidth;

  public Chart chart_acceleration;
  public Chart chart_gyroscope;
  public Chart chart_compass;
  public Chart chart_airpressure;
  public Chart chart_airtemperature;
  public Chart chart_altitude;
  public Chart chart_humidity;
  public Chart chart_TVOC;
  public Chart chart_ECO2;
  // public Chart chart_GPSsatellites;
  // public Chart chart_batteryvoltage;

  public SmartSliderElement horizontalSlider;



  public View_DataCharts(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);
    
    this.scrollBar = new VerticalScrollElement(this.appController, this, this.dim.x - 10, this.dim.y/2, this.dim.y, 0, this.dim.y);
    
    this.charts = new ArrayList<Chart>();
    this.chartHeight = 500;

    this.selectButtonWidth = 200;
    this.selectBetaDataButton = new ButtonElement(this.appController, this, this.dim.x/2 - this.selectButtonWidth * 1.5f - 5, 20, this.selectButtonWidth, "Bèta data"){
      public void clickEvent(){
        this.viewController.universalMethod("setDataBeta");
      }
    };
    this.selectMuDataButton = new ButtonElement(this.appController, this, this.dim.x/2 - this.selectButtonWidth/2, 20, this.selectButtonWidth, "Mu data"){
      public void clickEvent(){
        this.viewController.universalMethod("setDataMu");
      }
    };
    this.selectRhoDataButton = new ButtonElement(this.appController, this, this.dim.x/2 + this.selectButtonWidth/2 + 5, 20, this.selectButtonWidth, "Rho data"){
      public void clickEvent(){
        this.viewController.universalMethod("setDataRho");
      }
    };


    this.horizontalSlider = new SmartSliderElement(this.appController, this, 100, 50, ElementOrient.HORIZONTAL, this.dim.x - 210, 0, 1000);
    this.horizontalSlider.setValue(0, 100);

    // creating charts

    this.chart_acceleration =     new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(-15, 15),     "Acceleration",     "Time",     "s",    "Acceleration",               "m/s/s");
    this.chart_gyroscope =        new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(-10, 10),     "Gyroscope",        "Time",     "s",    "Angular velocity",           "rads/s");
    this.chart_compass =          new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(-100, 100),     "Compass",          "Time",     "s",    "Magnetic field strength",    "uT");
    this.chart_airpressure =      new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 1000000),   "Air pressure",     "Time",     "s",    "Pressure",                   "Pa");
    this.chart_airtemperature =   new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(-10, 30),     "Air temperature",  "Time",     "s",    "Temperature",                "degC");
    this.chart_altitude =         new Chart(100, 0, 0, 700, new ChartRange(0, 100), new ChartRange(-10, 10),   "Altitude",         "Time",     "s",    "Altitude",                   "m");
    this.chart_humidity =         new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 100),       "Humidity",         "Time",     "s",    "Humidity",                   "?");
    this.chart_TVOC =             new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 2000),       "TVOC",             "Time",     "s",    "TVOC",                       "?");
    this.chart_ECO2 =             new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 2000),       "ECO2",             "Time",     "s",    "ECO2",                       "?");
    // this.chart_GPSsatellites =    new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 10),       "GPS: satellites",  "Time",     "s",    "Satellites",                 "Units");
    // this.chart_batteryvoltage =   new Chart(100, 0, 0, this.chartHeight, new ChartRange(0, 100), new ChartRange(0, 7),        "Battery voltage",  "Time",     "s",    "Voltage",                    "Volt");
    

    this.charts.add(this.chart_acceleration);
    this.charts.add(this.chart_gyroscope);
    this.charts.add(this.chart_compass);
    this.charts.add(this.chart_airpressure);
    this.charts.add(this.chart_airtemperature);
    this.charts.add(this.chart_altitude);
    this.charts.add(this.chart_humidity);
    this.charts.add(this.chart_TVOC);
    this.charts.add(this.chart_ECO2);
    // this.charts.add(this.chart_GPSsatellites);
    // this.charts.add(this.chart_batteryvoltage);

    for(Chart c : this.charts){
      //c.autoScroll = true;
    }
    
    this.viewResizeTriggered();
    
    this.elements.add(this.scrollBar);
    this.elements.add(this.selectMuDataButton);
    this.elements.add(this.selectBetaDataButton);
    this.elements.add(this.selectRhoDataButton);
    this.elements.add(this.horizontalSlider);
  }

  public void universalMethod(String func){
    if(func.equals("setDataMu")){
      for(Chart c : this.charts){
        c.clear();
      }
      this.chart_acceleration.addDataSet(DataSetDeposit.mu_accX);
      this.chart_acceleration.addDataSet(DataSetDeposit.mu_accY);
      this.chart_acceleration.addDataSet(DataSetDeposit.mu_accZ);
      this.chart_gyroscope.addDataSet(DataSetDeposit.mu_gyroX);
      this.chart_gyroscope.addDataSet(DataSetDeposit.mu_gyroY);
      this.chart_gyroscope.addDataSet(DataSetDeposit.mu_gyroZ);
      this.chart_compass.addDataSet(DataSetDeposit.mu_compassX);
      this.chart_compass.addDataSet(DataSetDeposit.mu_compassY);
      this.chart_compass.addDataSet(DataSetDeposit.mu_compassZ);
      this.chart_airpressure.addDataSet(DataSetDeposit.mu_airPres);
      this.chart_airtemperature.addDataSet(DataSetDeposit.mu_airTemp);
      this.chart_altitude.addDataSet(DataSetDeposit.mu_altitude);
      this.chart_humidity.addDataSet(DataSetDeposit.mu_humidity);
      this.chart_TVOC.addDataSet(DataSetDeposit.mu_TVOC);
      this.chart_ECO2.addDataSet(DataSetDeposit.mu_ECO2);

    }else if(func.equals("setDataBeta")){
      for(Chart c : this.charts){
        c.clear();
      }
      this.chart_acceleration.addDataSet(DataSetDeposit.beta_accX);
      this.chart_acceleration.addDataSet(DataSetDeposit.beta_accY);
      this.chart_acceleration.addDataSet(DataSetDeposit.beta_accZ);
      this.chart_gyroscope.addDataSet(DataSetDeposit.beta_gyroX);
      this.chart_gyroscope.addDataSet(DataSetDeposit.beta_gyroY);
      this.chart_gyroscope.addDataSet(DataSetDeposit.beta_gyroZ);
      this.chart_compass.addDataSet(DataSetDeposit.beta_compassX);
      this.chart_compass.addDataSet(DataSetDeposit.beta_compassY);
      this.chart_compass.addDataSet(DataSetDeposit.beta_compassZ);
      this.chart_airpressure.addDataSet(DataSetDeposit.beta_airPres);
      this.chart_airtemperature.addDataSet(DataSetDeposit.beta_airTemp);
      this.chart_altitude.addDataSet(DataSetDeposit.beta_altitude);
      this.chart_humidity.addDataSet(DataSetDeposit.beta_humidity);
      this.chart_TVOC.addDataSet(DataSetDeposit.beta_TVOC);
      this.chart_ECO2.addDataSet(DataSetDeposit.beta_ECO2);

    }else if(func.equals("setDataRho")){
      for(Chart c : this.charts){
        c.clear();
      }
      this.chart_acceleration.addDataSet(DataSetDeposit.rho_accX);
      this.chart_acceleration.addDataSet(DataSetDeposit.rho_accY);
      this.chart_acceleration.addDataSet(DataSetDeposit.rho_accZ);
      this.chart_gyroscope.addDataSet(DataSetDeposit.rho_gyroX);
      this.chart_gyroscope.addDataSet(DataSetDeposit.rho_gyroY);
      this.chart_gyroscope.addDataSet(DataSetDeposit.rho_gyroZ);
      this.chart_compass.addDataSet(DataSetDeposit.rho_compassX);
      this.chart_compass.addDataSet(DataSetDeposit.rho_compassY);
      this.chart_compass.addDataSet(DataSetDeposit.rho_compassZ);
      this.chart_airpressure.addDataSet(DataSetDeposit.rho_airPres);
      this.chart_airtemperature.addDataSet(DataSetDeposit.rho_airTemp);
      this.chart_altitude.addDataSet(DataSetDeposit.rho_altitude);
      this.chart_humidity.addDataSet(DataSetDeposit.rho_humidity);
      this.chart_TVOC.addDataSet(DataSetDeposit.rho_TVOC);
      this.chart_ECO2.addDataSet(DataSetDeposit.rho_ECO2);
    }
  }
  
  public void viewResizeTriggered(){
	  this.scrollBar.resize(this.dim.x - 10, this.dim.y/2, this.dim.y);
    this.selectBetaDataButton.resize(this.dim.x/2 - this.selectButtonWidth * 1.5f - 5, 20, this.selectButtonWidth);
    this.selectMuDataButton.resize(this.dim.x/2 - this.selectButtonWidth/2, 20, this.selectButtonWidth);
    this.selectRhoDataButton.resize(this.dim.x/2 + this.selectButtonWidth/2 + 5, 20, this.selectButtonWidth);
    this.horizontalSlider.resize(100, 50, this.dim.x - 210);
	  
	  float y = 120;
	  for(int i = 0; i < this.charts.size(); ++i) {
		  this.charts.get(i).resize(100, y + this.charts.get(i).dim.y/2, this.dim.x - 210, this.charts.get(i).dim.y);
		  y += this.charts.get(i).dim.y + 200;
	  }
	  y -= 50;
	  if(y > this.dim.y) {
		  this.scrollBar.setExtremes(0, y);
		  this.scrollBar.setCurrentPosition(0, this.dim.y);
	  }else {
		  this.scrollBar.setExtremes(0, this.dim.y);
		  this.scrollBar.setCurrentPosition(0, this.dim.y);
	  }
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    //this.scrollBar.show();

    for(Chart c : this.charts){
      c.setXRange(new ChartRange(this.horizontalSlider.getValue().min, this.horizontalSlider.getValue().max));
    }
    
    translate(0, -this.scrollBar.getMinimumValue(), -1);

    for(Chart c : this.charts) {
    	c.show();
    }
    
    translate(0, this.scrollBar.getMinimumValue(), 1);

    noStroke();
    fill(255);
    rectMode(CORNER);
    rect(0, 0, this.dim.x - 10, 80);
    stroke(0);
    strokeWeight(1);
    line(0, 80, this.dim.x - 10, 80);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }
  
  public void mouseScrolled(float count){
    int selectedGraph = -1;
    if(ALT_PRESSED){
      for(int i = 0; i < this.charts.size(); ++i){
        if((mouseX >= this.charts.get(i).pos.x) && (mouseX <= this.charts.get(i).pos.x + this.charts.get(i).dim.x) && 
          ((mouseY + this.scrollBar.getMinimumValue()) >= this.charts.get(i).pos.y - this.charts.get(i).dim.y/2) && ((mouseY + this.scrollBar.getMinimumValue()) <= (this.charts.get(i).pos.y + this.charts.get(i).dim.y/2))){
            selectedGraph = i;
        }
      }
    }
    if(selectedGraph >= 0){
      this.charts.get(selectedGraph).addScroll(count);
    }else{
      this.scrollBar.addScroll(count);
    }
  }



}
// View_FlightPath.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_FlightPath extends ViewController {
	
	public PImage rocket_image;
	public PVector rocket_pos;
	public float rocket_time;
	public float rocket_angle;


  public View_FlightPath(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.rocket_image = loadImage("rocket.png");
    this.rocket_pos = new PVector(this.dim.x/2 - 425, this.dim.y - 50);
    this.rocket_time = 0;
    this.rocket_angle = 0;
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    fill(0);

    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("Flight Path", this.dim.x/2, this.dim.y - 200);
    
    stroke(56, 132, 255);
    strokeWeight(5);
    noFill();
    
    ellipse(this.dim.x/2, this.dim.y + 100, 800, 1200);
    
    
    
//    stroke(0);
//    strokeWeight(1);
//    noFill();
//    
//    beginShape();
//    for(float i = 0; i <= this.rocket_time; i += 0.0001) {
//    	vertex(this.dim.x/2 - 450*cos(i), this.dim.y - 550*sin(i));
//    }
//    endShape();
    
    this.rocket_pos.set(this.dim.x/2 - 450*cos(this.rocket_time), this.dim.y - 50 - 550*sin(this.rocket_time));
    this.rocket_angle = this.rocket_time;
    translate(this.rocket_pos.x, this.rocket_pos.y);
    rotateZ(this.rocket_angle);
    imageMode(CENTER);
    image(this.rocket_image, 0, 0, 50, 100);
    rotateZ(-this.rocket_angle);
    translate(-this.rocket_pos.x, -this.rocket_pos.y);
    
    this.rocket_time += 0.005f;

    if(this.rocket_time > PI) {
    	this.rocket_time = PI;
    }else if(this.rocket_time < 0) {
    	this.rocket_time = 0;
    }


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
// View_ForceDeploy.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_ForceDeploy extends ViewController {
    public ButtonElement denyButton;
    public ButtonElement confirmButton;
    public boolean onceShown;
    public boolean onceClosed;


  public View_ForceDeploy(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.denyButton = new ButtonElement(this.appController, this, this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150, "Deny");
    this.denyButton.setStrokeColor(color(255, 0, 0));

    this.confirmButton = new ButtonElement(this.appController, this, 2*this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150, "Confirm"){
        public void clickEvent(){
            this.appController.sendForceBabyCanDeploy();
        }
    };
    this.confirmButton.setStrokeColor(color(0, 255, 0));
    this.confirmButton.setSuggested(true);

    this.elements.add(this.denyButton);
    this.elements.add(this.confirmButton);

    this.onceShown = false;
    this.onceClosed = false;
  }

  public void viewResizeTriggered(){
    this.denyButton.resize(this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150);
    this.confirmButton.resize(2*this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150);
  }

  public void blockInteraction(){
    if(this.onceShown && !this.onceClosed){
        this.onceClosed = true;
    }
  }

  public void show(){

    if(!this.onceShown){
        this.appController.viewSelectorMethod("AddForceDeployButton");
        this.onceShown = true;
    }

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 20"));
    textAlign(CENTER);
    fill(0);
    text("FORCE DEPLOY REQUESTED", width/2, 250);
    textFont(fonts.get("Lora").get("Regular"));
    text("The MotherCan hasn't been able to determine the safety of the deploy.\nYou need to give manual permission for deploying the BabyCans.", this.dim.x/2, 275);

    if(!this.onceClosed){
        stroke(0);
        strokeWeight(2);
        fill(240);
        rectMode(CENTER);
        rect(220, 50, 300, 100);
        textFont(fonts.get("SF").get("Regular"));
        fill(0);
        text("From now on, you can open this view by\nclicking 'Force Deploy View'.\n\nThis message will be displayed once.", 220, 25);
    }


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
// View_MissionInfo.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_MissionInfo extends ViewController {


  public View_MissionInfo(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    fill(0);

    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("Mission Information", this.dim.x/2, 50);
    
    textFont(fonts.get("SF").get("Bold"));
    textAlign(RIGHT);
    text("Serial port:", this.dim.x/2 - 5, 120);
    text("Serial baud-rate:", this.dim.x/2 - 5, 140);
    text("Mission folder:", this.dim.x/2 - 5, 160);
    text("Mission identifier:", this.dim.x/2 - 5, 180);
    text("Create console log file:", this.dim.x/2 - 5, 200);
    text("Create csv file:", this.dim.x/2 - 5, 220);
    
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text(MissionSettings.getSerialPort(), this.dim.x/2 + 5, 120);
    text(MissionSettings.getSerialBaudRate(), this.dim.x/2 + 5, 140);
    text(MissionSettings.getOutputFolderPath(), this.dim.x/2 + 5, 160);
    text(MissionSettings.getMissionIdentifier(), this.dim.x/2 + 5, 180);
    text((MissionSettings.getCreateConsoleLogFile() ? "YES" : "NO"), this.dim.x/2 + 5, 200);
    text((MissionSettings.getCreateCSVDataOutputFile() ? "YES" : "NO"), this.dim.x/2 + 5, 220);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
// View_MissionStart.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_MissionStart extends ViewController {


  public View_MissionStart(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);


  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    fill(0);

    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("Mission: " + MissionSettings.getMissionIdentifier(), this.dim.x/2, this.dim.y/3);

    textFont(fonts.get("Lora").get("Regular"));
    text("The mission has successfully been setup.\nYou can control the mission by switching between views, using the toolbar above.\nAll the obtained data will be stored in the folder you selected.", this.dim.x/2, this.dim.y/3 + 40);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
// View_MotherCanInfo.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_MotherCanInfo extends ViewController {


  public View_MotherCanInfo(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    
    fill(0);
    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("MotherCan information", this.dim.x/2, 50);
    
    textFont(fonts.get("SF").get("Bold"));
    textAlign(RIGHT);
    text("Boot state:", this.dim.x/2 - 5, 100);
    text("Battery:", this.dim.x/2 - 5, 120);
    text("Radio connection:", this.dim.x/2 - 5, 140);
    text("Mode:", this.dim.x/2 - 5, 160);
    text("GPS:", this.dim.x/2 - 5, 200);
    text("Measurements:", this.dim.x/2 - 5, 240);
    text("BabyCans deployed:", this.dim.x/2 - 5, 280);
    
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text((DataSetDeposit.mu_bootState == 0 ? "Not booting" : (DataSetDeposit.mu_bootState == 1 ? "Booting" : (DataSetDeposit.mu_bootState == 2 ? "Active" : "..."))), this.dim.x/2 + 5, 100);
    text(str(DataSetDeposit.mu_batteryVoltage) + " volts", this.dim.x/2 + 5, 120);

    text(str((float)DataSetDeposit.groundStation_RSSI), this.dim.x/2 + 5, 140);

    text((DataSetDeposit.mu_flightMode == 0 ? "Non-flight-mode" : (DataSetDeposit.mu_flightMode == 1 ? "Flight-mode" : "Landed-mode")), this.dim.x/2 + 5, 160);

    
    text((DataSetDeposit.mu_GPSFix ? "Fixed" : "Not fixed") + ", " + str(DataSetDeposit.mu_GPSSatellites) + " satellites", this.dim.x/2 + 5, 200);
    
    text(str(DataSetDeposit.mu_pointsMeasured) + " datapoints", this.dim.x/2 + 5, 240);

    text((DataSetDeposit.mu_babyCansDeployed ? "YES" : "NO"), this.dim.x/2 + 5, 280);

    
    
    


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}

// View_UniversalText.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_UniversalText extends ViewController {
    public String title;
    public String subtitle;


  public View_UniversalText(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.title = "";
    this.subtitle = "";
  }

  public void setMessage(String t, String s){
    this.title = t;
    this.subtitle = s;
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 20"));
    textAlign(CENTER);
    fill(0);
    text(this.title, this.dim.x/2, 250);
    stroke(0);
    strokeWeight(2);
    line(this.dim.x/2 - textWidth(this.title)/2, 270, this.dim.x/2 + textWidth(this.title)/2, 270);
    textFont(fonts.get("Lora").get("Regular"));
    text(this.subtitle, this.dim.x/2, 300);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Alpha" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
