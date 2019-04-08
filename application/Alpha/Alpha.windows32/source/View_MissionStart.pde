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
