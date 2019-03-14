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
