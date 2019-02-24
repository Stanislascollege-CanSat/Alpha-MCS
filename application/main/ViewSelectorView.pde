// ViewSelectorView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ViewSelectorView extends ViewController {

  public ButtonElement exitButton;

  public ButtonElement overviewButton;
  public ButtonElement exampleButton;


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

    this.exampleButton = new ButtonElement(this.appController, this, 200, 25, 100, "Other"){
      public void clickEvent(){
        this.appController.switchViewToExample();
      }
    };

    this.elements.add(this.exitButton);
    this.elements.add(this.overviewButton);
    this.elements.add(this.exampleButton);
  }

  public void viewResizeTriggered(){
    this.exitButton.resize(10, 25, 100);
    this.overviewButton.resize(90, 25, 100);
    this.exampleButton.resize(200, 25, 100);
  }

  public void enableAllButtons(){
    this.overviewButton.disabled = false;
    this.exampleButton.disabled = false;
  }

  public void disableOverviewButton(){
    this.overviewButton.disabled = true;
  }

  public void disableExampleButton(){
    this.exampleButton.disabled = true;
  }

  public void show(){
    // noStroke();
    // fill(0);
    // rectMode(CORNER);
    // rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

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
