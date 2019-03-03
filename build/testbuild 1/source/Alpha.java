import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Map; 
import processing.opengl.PGL; 
import processing.opengl.PJOGL; 
import static javax.swing.JOptionPane.*; 
import java.io.FileWriter; 
import java.lang.Math; 
import java.math.BigDecimal; 
import java.util.*; 
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





PImage appIcon;



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
AppController appController;
//StartupView startupView;

// PImage mouse_pointer_img;

public void settings(){
  size(1000, 700, P3D);
  //fullScreen(P3D);
  pixelDensity(displayDensity());
  //smooth(8);
  PJOGL.setIcon("1024x1024.png");
}

public void setup(){
  surface.setTitle("Alpha");
  surface.setResizable(true);
//  appIcon = loadImage("icon1000.png");
//  surface.setIcon(appIcon);
  background(200);

  // Frame components
  w = width;
  h = height;
  minWidth = 600;
  minHeight = 500;

  // Cursor settings
  // mouse_pointer_img = loadImage("MousePointer.png");
  // cursor(mouse_pointer_img, 2, 13);

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


  // Application components
  appController = new AppController(this);

  //println(MAX_INT);

}

public void draw(){
  if(!completedStartup){
    //showMessageDialog(null, "This application is still being developed. Some functions might not work.", "Work in progress", WARNING_MESSAGE);
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

  private SerialController serialController;


  private StartupView startupView;
  private SetupView setupView;

  // ------- MISSION VIEWS --------- //
  private ViewSelectorView viewSelectorView;

  private View_MissionStart view_MissionStart;
  private View_MissionInfo view_MissionInfo;
  private View_FlightPath view_FlightPath;

  private ConsoleView overviewConsoleView;


  private DataSet testDataSet;
  private DataSet testDerivDataSet;

  public AppController(PApplet environment){
    this.testDataSet = new DataSet("Velocity");

    for(double i = -10; i <= 10; i += 0.01f){
      this.testDataSet.addDataPoint(new DataPoint(i, 5*Math.sin(i)));
    }

    this.testDerivDataSet = this.testDataSet.calcDerivative();


    this.mainJavaEnvironment = environment;

    this.viewControllers = new ArrayList<ViewController>();

    this.serialController = new SerialController(this, this.mainJavaEnvironment);

    this.startupView = new StartupView(this, 0, 0, width, height);

    this.setupView = new SetupView(this, 0, 0, width, height);
    this.setupView.visible = false;

    this.viewSelectorView = new ViewSelectorView(this, 0, 0, width, 80);
    this.viewSelectorView.visible = false;

    this.view_MissionStart = new View_MissionStart(this, 0, 80, width, height - 80);
    this.view_MissionStart.visible = false;

    this.view_MissionInfo = new View_MissionInfo(this, 0, 80, width, height - 80);
    this.view_MissionInfo.visible = false;

    this.view_MissionInfo.testChart.addDataSet(this.testDataSet);
    this.view_MissionInfo.testChart.addDataSet(this.testDerivDataSet);
    this.view_MissionInfo.testChart.addDataSet(this.testDerivDataSet.calcDerivative());
    this.view_MissionInfo.testChart.addDataSet(this.testDerivDataSet.calcDerivative().calcDerivative());
    
    this.view_FlightPath = new View_FlightPath(this, 0, 80, width, height - 80);
    this.view_FlightPath.visible = false;

    this.overviewConsoleView = new ConsoleView(this, 0, 80, 500, height - 80);
    this.overviewConsoleView.visible = false;

    this.viewControllers.add(this.startupView);
    this.viewControllers.add(this.setupView);
    this.viewControllers.add(this.viewSelectorView);
    this.viewControllers.add(this.view_MissionStart);
    this.viewControllers.add(this.view_MissionInfo);
    this.viewControllers.add(this.view_FlightPath);
    this.viewControllers.add(this.overviewConsoleView);
  }

  public void show(){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.show();
      }
    }
    strokeWeight(1);
    fill(0);
    textAlign(LEFT);
    textFont(fonts.get("SF").get("Regular"));
    text(PApplet.parseInt(frameRate), 10, 20);
  }

  public void resize(){
    this.startupView.resize(0, 0, width, height);
    this.setupView.resize(0, 0, width, height);
    this.viewSelectorView.resize(0, 0, width, 80);
    this.view_MissionStart.resize(0, 80, width, height - 80);
    this.view_MissionInfo.resize(0, 80, width, height - 80);
    this.view_FlightPath.resize(0, 80, width, height - 80);
    if(this.viewSelectorView.currentViewIdentifier.equals("overview")){
      this.overviewConsoleView.resize(0, 80, 500, height - 80);
    }else if(this.viewSelectorView.currentViewIdentifier.equals("console")){
      this.overviewConsoleView.resize(0, 80, width, height - 80);
    }
  }

  public void addView(ViewController v){
    this.viewControllers.add(v);
  }

  public void exitApplication(){
    exit();
  }

  public void mousePressed(){
    for(ViewController v : this.viewControllers){
      if(v.visible && v.userInteractionEnabled){
        v.mousePressed();
      }
    }
  }

  public void mouseReleased(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = true;
      if(v.visible){
        v.mouseReleased();
      }
    }
  }

  public void mouseWheel(float count){
    for(ViewController v : this.viewControllers){
      if(v.visible && v.userInteractionEnabled){
        v.mouseWheel(count);
      }
    }
  }

  public void keyPressed(char k, int c){
    if(k == '1' && CTRL_PRESSED){
      this.exitApplication();
    }
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyReleased();
      }
    }
  }

  private void blockInteraction(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = false;
    }
    for(ViewController v : this.viewControllers){
      v.visible = false;
    }
  }






  public void displaySetupScheme(){
    this.blockInteraction();
    this.setupView.visible = true;
  }

  public void displayStartupScheme(){
    this.blockInteraction();
    this.startupView.visible = true;
  }

  public void runMissionSetup() throws IOException{
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

    

    this.overviewConsoleView.logSetup("Serial port:\n    " + this.setupView.getSelectedSerialPort());
    this.overviewConsoleView.logSetup("Serial baud-rate:\n    " + this.setupView.getSelectedSerialBaud());
    this.overviewConsoleView.logSetup("Mission folder:\n    " + this.setupView.getSelectedMissionPath());
    this.overviewConsoleView.logSetup("Mission identifier:\n    " + this.setupView.getSelectedMissionIdentifier());
    this.overviewConsoleView.logSetup("Create console log file:\n    " + (this.setupView.getSelectedDoConsoleLogFile() ? "YES" : "NO"));
    this.overviewConsoleView.logSetup("Create CSV data output file:\n    " + (this.setupView.getSelectedDoCSVDataFile() ? "YES" : "NO"));


    this.blockInteraction();
    this.viewSelectorView.visible = true;
    this.view_MissionStart.visible = true;
  }

  // ------------------ CONSOLE COMMANDS --------------------------- //

  public String[] parseCommand(String input){
    String[] output = input.trim().split("\\s+");
    return output;
  }

  public void runCommand(String command, String[] args){
	  if(ActionRequest.anyRequestOpen() && !(command.equals("confirm") || command.equals("deny"))) {
		  ActionRequest.denyAll();
		  this.overviewConsoleView.logResponse("All requests denied.");
	  }
	  switch(command) {
	  	case "log":
	      if(args.length > 0){
	        String msg = "";
	        for(int i = 1; i < args.length; ++i){
	          msg += args[i];
	          msg += " ";
	        }
	        if(args[0].equals("msg")){
	          this.overviewConsoleView.logMessage(msg);
	        }else if(args[0].equals("wrn")){
	          this.overviewConsoleView.logWarning(msg);
	        }else if(args[0].equals("err")){
	          this.overviewConsoleView.logError(msg);
	        }else{
	          String argslist = "";
	          for(int i = 0; i < args.length; ++i){
	            argslist += "\n[" + str(i) + "]->" + args[i];
	          }
	          this.overviewConsoleView.logSpecial("Command: '" + command + "'\nArguments:" + argslist + "\n\nShould be:\n" + command + " <msg,wrn,err> <text>", "syntax_error");
	          argslist = null;
	        }
	        msg = null;
	      }else{
	        String argslist = "";
	        for(int i = 0; i < args.length; ++i){
	          argslist += "\n[" + str(i) + "]->" + args[i];
	        }
	        this.overviewConsoleView.logSpecial("Command: '" + command + "'\nArguments:" + argslist + "\n\nShould be:\n" + command + " <msg,wrn,err> <text>", "syntax_error");
	        argslist = null;
	      }
	      break;
	  	case "print":
	      if(args.length > 0){
	        String msg = "";
	        for(int i = 0; i < args.length; ++i){
	          msg += args[i];
	          msg += " ";
	        }
	        this.overviewConsoleView.logMessage(msg);
	      }else{
	        String argslist = "";
	        for(int i = 0; i < args.length; ++i){
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
		    if(args.length == 1){
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
	  	case "help":
	  		if(args.length == 1) {
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
	  				default:
	  					this.overviewConsoleView.logSpecial("Command '" + args[0] + "' not found.", "syntax_error");
	  			}
	  		}else {
	  			this.overviewConsoleView.logSpecial("help <command_name>\nType 'list' for list of commands.", "syntax_error");
	  		}
	  		break;
	  	case "list":
	  		this.overviewConsoleView.logResponse("List of commands:\n\nlog\nprint\nclear\nexit\ngetMissionSetting");
	  		break;
	  	case "confirm":
	  		if(ActionRequest.clearConsole) {
	  			this.overviewConsoleView.clearMessages();
	  		}
	  		if(ActionRequest.exitAlpha) {
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

  public void deleteMessageFromConsole(int id){
    this.overviewConsoleView.deleteMessage(id);
  }

  public void clearConsoleMessages(){
    this.overviewConsoleView.clearMessages();
  }

  // ------------------ VIEW SWITCH METHODS ------------------------ //

  public void switchViewToMissionInfo(){
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

  public void switchViewToOverview(){
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.overviewConsoleView.resize(0, 80, 500, height - 80);
    this.overviewConsoleView.visible = true;
    this.viewSelectorView.currentViewIdentifier = "overview";
  }

  public void switchViewToConsole(){
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.overviewConsoleView.resize(0, 80, width, height - 80);
    this.overviewConsoleView.visible = true;
    this.viewSelectorView.currentViewIdentifier = "console";
  }

  // ------------------ DIALOG WINDOWS ----------------------------- //





  // ------------------ FILE SELECTION METHODS --------------------- //
  public void SetupView_ask_folder_MissionData(){
    SetupView_ask_folder_MissionData();
  }

  public void SetupView_selected_folder_MissionData(File selected){
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
  }

  public void addDataSet(DataSet a){
    this.dataSets.add(a);
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

  public LineInputElement commandInput;

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

    this.commandInput = new LineInputElement(this.appController, this, 10, this.dim.y - 55, this.dim.x - 20){
      public void enterEvent(){
        String[] parse = this.appController.parseCommand(this.getValue());
        if(parse.length > 0){
          this.appController.runCommand(parse[0], Arrays.copyOfRange(parse, 1, parse.length));
        }
        this.reset();
      }
    };

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
    this.autoScrollTickBox.setValue(false);
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
// FileSelectorMethods.pde
// Processing 3.4
// Rens Dur (Project Bèta)


//-----------------------------------------------------------------------------------------------------------------//
// AppController -> (void)SetupView_ask_folder_MissionData -> [callback](void)SetupView_selected_folder_MissionData -> AppController_Interface

/*init*/public void SetupView_ask_folder_MissionData(){
  selectFolder("Select to store the mission data.", "SetupView_selected_folder_MissionData");
}

/*callback*/public void SetupView_selected_folder_MissionData(File selected){
  appController.SetupView_selected_folder_MissionData(selected);
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


public interface SerialController_Interface {

}

public class SerialController implements SerialController_Interface {
  public AppController appController;
  private PApplet mainJavaEnvironment;
  private Serial com;
  private boolean serialSetupDone;
  private int[] possibleBaudRates;

  public SerialController(AppController a, PApplet environment){
    this.appController = a;
    this.mainJavaEnvironment = environment;
  }

  public String[] getPortsList(){
    return Serial.list();
  }

  public void openConnection(String port, int baudrate){
    if(this.serialSetupDone){
      this.stopConnection();
    }
    this.com = null;
    this.com = new Serial(this.mainJavaEnvironment, port, baudrate);
    this.serialSetupDone = true;
  }

  public void stopConnection(){
    if(this.serialSetupDone){
      this.com.stop();
      this.serialSetupDone = false;
    }
  }

  public void write(String msg){
    if(this.serialSetupDone && this.com.available() == 1){
      this.com.write(msg);
    }
  }

  public boolean opened(){
    return this.serialSetupDone;
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

  public SelectionElement serialPortSelect;
  public SelectionElement serialBaudSelect;

  public UtilityButtonElement dataOutputFolderButton;
  public TextElement dataOutputFolderName;

  public LineInputElement missionIdentifierInput;

  public TickBoxElement consoleLogFileTickBox;
  public TickBoxElement csvDataFileTickBox;

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

    this.serialPortSelect = new SelectionElement(this.appController, this, this.dim.x/2 + 10, 200, 100);
    this.serialBaudSelect = new SelectionElement(this.appController, this, this.dim.x/2 + 10, 240, 100);

    this.dataOutputFolderButton = new UtilityButtonElement(this.appController, this, this.dim.x/2 + 10, 280){
      public void clickEvent(){
        SetupView_ask_folder_MissionData();
      }
    };
    this.dataOutputFolderName = new TextElement(this.appController, this, this.dim.x/2 + 30, 280, 500, "", LEFT);

    this.missionIdentifierInput = new LineInputElement(this.appController, this, this.dim.x/2 + 10, 320, 200);

    this.consoleLogFileTickBox = new TickBoxElement(this.appController, this, this.dim.x/2 - 100, 400);
    this.consoleLogFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 80, 400, 300, "Create console-log file", LEFT);

    this.csvDataFileTickBox = new TickBoxElement(this.appController, this, this.dim.x/2 - 100, 440);
    this.csvDataFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 80, 440, 300, "Create CSV data-output file", LEFT);

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

    // Setting default values
    for(String s : this.appController.serialController.getPortsList()){
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

    this.serialPortSelect.resize(this.dim.x/2 + 10, 200, 100);
    this.serialBaudSelect.resize(this.dim.x/2 + 10, 240, 100);

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
    this.elements.add(this.shortCut);
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

  public void viewResizeTriggered(){}

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

  public ButtonElement missionInfoButton;
  public ButtonElement overviewButton;
  public ButtonElement consoleButton;
  public ButtonElement BabyCan1Info;
  public ButtonElement BabyCan2Info;
  public ButtonElement MotherCanInfo;
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

    this.missionInfoButton = new ButtonElement(this.appController, this, 0, 0, 120, "Mission info"){
      public void clickEvent(){
        this.appController.switchViewToMissionInfo();
        this.disable();
      }
    };
    
    this.BabyCan1Info = new ButtonElement(this.appController, this, 0, 0, 130, "BabyCan 1 info");
    
    this.BabyCan2Info = new ButtonElement(this.appController, this, 0, 0, 130, "BabyCan 2 info");
    
    this.MotherCanInfo = new ButtonElement(this.appController, this, 0, 0, 120, "MotherCan info");
    
    this.FlightPath = new ButtonElement(this.appController, this, 0, 0, 90, "Flight path") {
    	public void clickEvent() {
    		this.appController.switchViewToFlightPath();
    		this.disable();
    	}
    };
    
    this.MeasuredData = new ButtonElement(this.appController, this, 0, 0, 120, "Measured data");

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
    this.elements.add(this.scrollBar);
    this.viewButtons.add(this.missionInfoButton);
    this.viewButtons.add(this.overviewButton);
    this.viewButtons.add(this.consoleButton);
    //this.viewButtons.add(this.MotherCanInfo);
    this.viewButtons.add(this.BabyCan1Info);
    this.viewButtons.add(this.BabyCan2Info);
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
// View_MissionInfo.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_MissionInfo extends ViewController {

  public Chart testChart;


  public View_MissionInfo(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.testChart = new Chart(100, this.dim.y/2, 800, 500, new ChartRange(-10, 10), new ChartRange(-10, 10), "Derivative test", "x", "units", "y", "units");


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


    for(Element e : this.elements){
      e.show();
    }

    this.testChart.show();

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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Alpha" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
