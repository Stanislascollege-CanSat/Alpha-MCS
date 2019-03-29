// View_ControlButtons.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_ControlButtons extends ViewController {

  public ButtonElement openPinsButton;
  public ButtonElement closePinsButton;
  public ButtonElement openRingButton;
  public ButtonElement closeRingButton;

  public ButtonElement flightMode;
  public ButtonElement sendAllData;
  public ButtonElement clearData;


  public View_ControlButtons(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.openPinsButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 - 80, 100, "Open pins"){
      public void clickEvent(){
        SerialController.send("[OPP]");
      }
    };
    this.closePinsButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 - 30, 100, "Close pins"){
      public void clickEvent(){
        SerialController.send("[CLP]");
      }
    };
    this.openRingButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 + 30, 100, "Open ring"){
      public void clickEvent(){
        SerialController.send("[OPR]");
      }
    };
    this.closeRingButton = new ButtonElement(this.appController, this, this.dim.x/4, this.dim.y/2 + 80, 100, "Close ring"){
      public void clickEvent(){
        SerialController.send("[CLR]");
      }
    };

    this.flightMode = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2 - 80, 100, "Flight mode"){
      public void clickEvent(){
        SerialController.send("[FLIGHT_MODE]");
      }
    };

    this.sendAllData = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2, 100, "Send all data"){
      public void clickEvent(){
        SerialController.send("[SAD]");
      }
    };

    this.clearData = new ButtonElement(this.appController, this, 3*this.dim.x/4, this.dim.y/2 + 50, 100, "Clear FRAM"){
      public void clickEvent(){
        SerialController.send("[CLF]");
      }
    };

    this.elements.add(this.openPinsButton);
    this.elements.add(this.closePinsButton);
    this.elements.add(this.openRingButton);
    this.elements.add(this.closeRingButton);
    this.elements.add(this.flightMode);
    this.elements.add(this.sendAllData);
    this.elements.add(this.clearData);
  }

  public void viewResizeTriggered(){
    this.openPinsButton.resize(this.dim.x/4, this.dim.y/2 - 75, 100);
    this.closePinsButton.resize(this.dim.x/4, this.dim.y/2 - 25, 100);
    this.openRingButton.resize(this.dim.x/4, this.dim.y/2 + 25, 100);
    this.closeRingButton.resize(this.dim.x/4, this.dim.y/2 + 75, 100);

    this.flightMode.resize(3*this.dim.x/4, this.dim.y/2 - 80, 100);
    this.sendAllData.resize(3*this.dim.x/4, this.dim.y/2, 100);
    this.clearData.resize(3*this.dim.x/4, this.dim.y/2 + 50, 100);
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 15"));
    textAlign(CENTER);
    fill(0);
    text("These actions are performed without applying\nany safety check.", this.dim.x/2, 50);

    stroke(0);
    strokeWeight(1);
    line(this.dim.x/4 - 25, this.dim.y/2, this.dim.x/4 + 125, this.dim.y/2);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
