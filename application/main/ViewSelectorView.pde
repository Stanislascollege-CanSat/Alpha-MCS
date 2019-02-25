// ViewSelectorView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ViewSelectorView extends ViewController {

  public ButtonElement exitButton;

  public ButtonElement overviewButton;
  public ButtonElement consoleButton;

  public String currentViewIdentifier;


  public ViewSelectorView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.exitButton = new ButtonElement(this.appController, this, 10, 25, 60, "Exit"){
      public void clickEvent(){
        this.appController.exitApplication();
      }
    };
    this.exitButton.setStrokeColor(color(255, 0, 0));
    this.exitButton.setSuggested(true);

    this.overviewButton = new ButtonElement(this.appController, this, 90, 25, 100, "Overview"){
      public void clickEvent(){
        this.appController.switchViewToOverview();
      }
    };

    this.consoleButton = new ButtonElement(this.appController, this, 200, 25, 100, "Console"){
      public void clickEvent(){
        this.appController.switchViewToConsole();
      }
    };

    this.elements.add(this.exitButton);
    this.elements.add(this.overviewButton);
    this.elements.add(this.consoleButton);
  }

  public void viewResizeTriggered(){
    this.exitButton.resize(10, 25, 60);
    this.overviewButton.resize(90, 25, 100);
    this.consoleButton.resize(200, 25, 100);
  }

  public void enableAllButtons(){
    this.overviewButton.enable();
    this.consoleButton.enable();
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
    line(this.pos.x + 80, this.pos.y, this.pos.x + 80, this.pos.y + this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

    stroke(0);
    strokeWeight(2);
    line(this.pos.x, this.pos.y + this.dim.y, this.pos.x + this.dim.x, this.pos.y + this.dim.y);
  }
}
