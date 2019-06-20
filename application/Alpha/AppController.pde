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
  private View_ForceDeploy view_forceDeploy;
  private View_ControlButtons view_controlButtons;
  private View_UniversalText view_universalText;
  private View_Overview view_overview;

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

	this.view_overview = new View_Overview(this, 500, 80, width - 500, height - 80);
	this.view_overview.visible = false;

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
	this.viewControllers.add(this.view_overview);
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

    //if (frameRate < 45) {
    //  strokeWeight(1);
    //  fill(0);
    //  textAlign(LEFT);
    //  textFont(fonts.get("SF").get("Regular"));
    //  text(int(frameRate), 10, 20);
    //}
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
	this.view_overview.resize(500, 80, width - 500, height - 80);
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
    this.viewSelectorView.FlightPath.disable();
    this.view_FlightPath.visible = true;
    this.viewSelectorView.currentViewIdentifier = "flightPath";
  }

  public void switchViewToOverview() {
    this.blockInteraction();
    this.viewSelectorView.enableAllButtons();
    this.viewSelectorView.visible = true;
    this.overviewConsoleView.resize(0, 80, 500, height - 80);
    this.overviewConsoleView.visible = true;
	this.view_overview.visible = true;
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
    for(int i = 0; i < 50; ++i){
      SerialController.send("[DEP]");
    }
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
