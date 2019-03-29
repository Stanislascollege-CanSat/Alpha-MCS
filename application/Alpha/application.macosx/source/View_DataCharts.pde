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
    this.selectBetaDataButton = new ButtonElement(this.appController, this, this.dim.x/2 - this.selectButtonWidth * 1.5 - 5, 20, this.selectButtonWidth, "Bèta data"){
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
    this.selectBetaDataButton.resize(this.dim.x/2 - this.selectButtonWidth * 1.5 - 5, 20, this.selectButtonWidth);
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
