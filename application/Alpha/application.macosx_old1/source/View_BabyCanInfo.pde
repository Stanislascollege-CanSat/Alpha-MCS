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
