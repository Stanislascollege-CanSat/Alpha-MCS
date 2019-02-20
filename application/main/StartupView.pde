// StartupView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class StartupView extends ViewController {
  public SpaceAnimation spaceAnimation;
  public TextButtonElement button;

  public ButtonElement elementTestButton;

  public StartupView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);
    this.spaceAnimation = new SpaceAnimation();
    this.button = new TextButtonElement(this.appController, this, this.dim.x/2 - 30, this.dim.y/2 + 100, 60, "Start"){
      public void clickEvent(){
        this.appController.displaySetupScheme();
      }
    };

    this.elementTestButton = new ButtonElement(this.appController, this, this.dim.x - 155, this.dim.y - 50, 120, "Test Elements"){
      public void clickEvent(){
        this.appController.openElementTestView();
      }
    };

    this.elements.add(this.button);
    this.elements.add(this.elementTestButton);
  }

  public void viewResizeTriggered(){
    this.button.resize(this.dim.x/2-50, this.dim.y/2 + 100, 100);
    this.elementTestButton.resize(this.dim.x - 155, this.dim.y - 50, 120);
  }

  public void show(){
    stroke(255);
    strokeWeight(1);
    //fill(255);
    noFill();

    rectMode(CORNER);

    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    this.spaceAnimation.show();

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    translate(0, 0, 100);

    fill(0);

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Black 20"));
    text("Alpha", this.dim.x/2, this.dim.y/2 - this.dim.y/10);

    textFont(fonts.get("Lora").get("Regular"));
    text("CanSat Mission Control Software by Rens Dur\n(Project Bèta)", this.dim.x/2, this.dim.y/2 - this.dim.y/10 + 30);

    translate(0, 0, -100);

    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }
}
