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
