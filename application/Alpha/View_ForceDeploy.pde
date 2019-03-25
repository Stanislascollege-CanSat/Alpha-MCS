// View_ForceDeploy.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_ForceDeploy extends ViewController {
    public ButtonElement denyButton;
    public ButtonElement confirmButton;
    public boolean onceShown;
    public boolean onceClosed;


  public View_ForceDeploy(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.denyButton = new ButtonElement(this.appController, this, this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150, "Deny");
    this.denyButton.setStrokeColor(color(255, 0, 0));

    this.confirmButton = new ButtonElement(this.appController, this, 2*this.dim.x/3 - 100, this.dim.y/2 + 100, 200, 150, "Confirm"){
        public void clickEvent(){
            this.appController.displayUniversalMessage("BABYCANS DEPLOYED", "The BabyCans have successfully been deployed.");
        }
    };
    this.confirmButton.setStrokeColor(color(0, 255, 0));
    this.confirmButton.setSuggested(true);

    this.elements.add(this.denyButton);
    this.elements.add(this.confirmButton);

    this.onceShown = false;
    this.onceClosed = false;
  }

  public void blockInteraction(){
    if(this.onceShown && !this.onceClosed){
        this.onceClosed = true;
    }
  }

  public void show(){

    if(!this.onceShown){
        this.appController.viewSelectorMethod("AddForceDeployButton");
        this.onceShown = true;
    }

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 20"));
    textAlign(CENTER);
    fill(0);
    text("FORCE DEPLOY REQUESTED", width/2, 250);
    textFont(fonts.get("Lora").get("Regular"));
    text("The MotherCan hasn't been able to determine the safety of the deploy.\nYou need to give manual permission for deploying the BabyCans.", this.dim.x/2, 275);

    if(!this.onceClosed){
        stroke(0);
        strokeWeight(2);
        fill(240);
        rectMode(CENTER);
        rect(220, 50, 300, 100);
        textFont(fonts.get("SF").get("Regular"));
        fill(0);
        text("From now on, you can open this view by\nclicking 'Force Deploy View'.\n\nThis message will be displayed once.", 220, 25);
    }


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
