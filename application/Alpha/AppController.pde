// AppController.pde
// Processing 3.4
// Rens Dur (Project Bèta)

import java.io.FileWriter;
import java.lang.Math;

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

  private ConsoleView overviewConsoleView;
  
  private FileWriter serial_receive_file;
  private boolean serial_receive_file_opened;

  public AppController(PApplet environment){


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
    this.viewControllers.add(this.overviewConsoleView);
    
    DataDecoder.init();
  }

  public void show(){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.show();
      }
    }
    
    if(SerialController.available()) {
    	for(String s : SerialController.getReceived()) {
    		//this.overviewConsoleView.logSerial(s);
    		DataDecoder.addData(s);
    		if(this.serial_receive_file_opened) {
				try{	
					this.serial_receive_file.write(s);
				}catch(IOException e){
					//doewatleuks.
				}catch(Exception e){
					// doe wat leuks.
				}
    		}
    	}
    }
    
    DataDecoder.update();
    
    strokeWeight(1);
    fill(0);
    textAlign(LEFT);
    textFont(fonts.get("SF").get("Regular"));
    text(int(frameRate), 10, 20);
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
    this.view_DataCharts.resize(0, 80, width, height - 80);
    this.view_BabyCanInfo.resize(0, 80, width, height - 80);
    this.view_MotherCanInfo.resize(0, 80, width, height - 80);
  }

  public void addView(ViewController v){
    this.viewControllers.add(v);
  }

  public void exitApplication(){
	SerialController.close();
	if(this.serial_receive_file_opened) {
		try {
			this.serial_receive_file.close();
		}catch(IOException e) {
			//doewatleuks
		}catch(Exception e) {
			//doewatleuks
		}
	}
	
	try {
		FileWriter decoded_data_mother = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_MotherCan.txt");
		decoded_data_mother.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
		decoded_data_mother.write("--> Measured " + DataDecoder.getDecodedMotherData().size() + " data points.\n");
		for(MeasuredDataPoint p : DataDecoder.getDecodedMotherData()) {
			decoded_data_mother.write("\n\n--\n\n");
			for(String k : p.getMap().keySet()) {
				decoded_data_mother.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
			}
			println("\n\n");
		}
		decoded_data_mother.close();
		
		FileWriter decoded_data_beta = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_BetaCan.txt");
		decoded_data_beta.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
		decoded_data_beta.write("--> Measured " + DataDecoder.getDecodedBetaData().size() + " data points.\n");
		for(MeasuredDataPoint p : DataDecoder.getDecodedBetaData()) {
			decoded_data_beta.write("\n\n--\n\n");
			for(String k : p.getMap().keySet()) {
				decoded_data_beta.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
			}
		}
		decoded_data_beta.close();
		
		FileWriter decoded_data_rho = new FileWriter(MissionSettings.getOutputFolderPath() + "/DECODED_DATA_RhoCan.txt");
		decoded_data_rho.write("@MISSION: " + MissionSettings.getMissionIdentifier() + "\n");
		decoded_data_rho.write("--> Measured " + DataDecoder.getDecodedRhoData().size() + " data points.\n");
		for(MeasuredDataPoint p : DataDecoder.getDecodedRhoData()) {
			decoded_data_rho.write("\n\n--\n\n");
			for(String k : p.getMap().keySet()) {
				decoded_data_rho.write(">>" + k + ": " + Double.toString(p.getMap().get(k)) + "\n");
			}
		}
		decoded_data_rho.close();
	}catch(IOException e) {
		
	}catch(Exception e) {
		
	}
	
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
		int(this.setupView.getSelectedSerialBaud()),
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
    
//    SerialController.open(this.mainJavaEnvironment, MissionSettings.getSerialPort(), MissionSettings.getSerialBaudRate());
//    this.serial_receive_file = new FileWriter(MissionSettings.getOutputFolderPath() + "/serial_receive.txt");
//    this.serial_receive_file_opened = true;	


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
	  	case "send":
	  		if(args.length > 0) {
	  			for(String s : args) {
	  				SerialController.send(s);
	  			}
	  			this.overviewConsoleView.logResponse("Done.");
	  		}else {
	  			this.overviewConsoleView.logSpecial("send <text>", "syntax_error");
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
	  				case "send":
	  					this.overviewConsoleView.logHelp("-> send <text>");
	  					break;
	  				default:
	  					this.overviewConsoleView.logSpecial("Command '" + args[0] + "' not found.", "syntax_error");
	  			}
	  		}else {
	  			this.overviewConsoleView.logSpecial("help <command_name>\nType 'list' for list of commands.", "syntax_error");
	  		}
	  		break;
	  	case "list":
	  		this.overviewConsoleView.logResponse("List of commands:\n\nlog\nprint\nclear\nexit\ngetMissionSetting\nsend");
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
  
  public void switchViewToDataCharts(){
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_DataCharts.visible = true;
    this.viewSelectorView.currentViewIdentifier = "dataCharts";
  }
  
  public void switchViewToBabyCanInfo(){
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_BabyCanInfo.visible = true;
    this.viewSelectorView.currentViewIdentifier = "babyCanInfo";
  }
  
  public void switchViewToMotherCanInfo(){
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.view_MotherCanInfo.visible = true;
    this.viewSelectorView.currentViewIdentifier = "motherCanInfo";
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
