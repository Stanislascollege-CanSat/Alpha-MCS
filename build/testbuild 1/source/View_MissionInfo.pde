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
