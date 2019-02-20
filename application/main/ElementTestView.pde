// ElementTestView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ElementTestView extends ViewController {

  public ButtonElement backButton;

  public Chart testChart;


  public ElementTestView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);


    this.backButton = new ButtonElement(this.appController, this, 35, this.dim.y - 50, 120, "Back"){
      public void clickEvent(){
        this.appController.closeElementTestView();
      }
    };

    this.testChart = new Chart(100, 300, this.dim.x - 200, 400, new ChartRange(0, 20), new ChartRange(0, 10), "Velocity", "time", "s", "velocity", "m/s");


    this.elements.add(this.backButton);
  }

  public void viewResizeTriggered(){
    this.backButton.resize(35, this.dim.y - 50, 120);
  }

  public void show(){
    this.testChart.xRange.max /= 1.005;

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
    text("Element Testing View", this.dim.x/2, 50);

    this.testChart.show();

    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }
}
