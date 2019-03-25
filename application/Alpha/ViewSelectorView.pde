// ViewSelectorView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ViewSelectorView extends ViewController {

  public ArrayList<ButtonElement> viewButtons;

  public HorizontalScrollElement scrollBar;
  public float viewWidth;

  public ButtonElement exitButton;
  public ButtonElement setupButton;
  public ButtonElement forceDeployButton;

  public ButtonElement missionInfoButton;
  public ButtonElement overviewButton;
  public ButtonElement consoleButton;
  public ButtonElement BabyCanInfo;
  public ButtonElement MotherCanInfo;
  public ButtonElement FlightPath;
  public ButtonElement MeasuredData;

  public String currentViewIdentifier;


  public ViewSelectorView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.currentViewIdentifier = "";

    this.viewButtons = new ArrayList<ButtonElement>();

    this.viewWidth = this.dim.x;
    this.scrollBar = new HorizontalScrollElement(this.appController, this, 0, this.dim.y - 5, this.dim.x, 0, this.dim.x);
    this.scrollBar.setCurrentPosition(0, this.viewWidth);

    this.exitButton = new ButtonElement(this.appController, this, 5, 15, 60, 20, "Exit"){
      public void clickEvent(){
        this.appController.exitApplication();
      }
    };
    this.exitButton.setStrokeColor(color(255, 0, 0));
    this.exitButton.setSuggested(true);

    this.setupButton = new ButtonElement(this.appController, this, 70, 15, 60, 20, "Setup"){

    };
    this.setupButton.setStrokeColor(color(255, 233, 0));
    this.setupButton.setSuggested(true);

    this.forceDeployButton = new ButtonElement(this.appController, this, 150, 15, 140, 20, "Force Deploy View"){
      public void clickEvent(){
        this.appController.switchViewToForceDeploy();
      }
    };

    this.missionInfoButton = new ButtonElement(this.appController, this, 0, 0, 120, "Mission info"){
      public void clickEvent(){
        this.appController.switchViewToMissionInfo();
        this.disable();
      }
    };
    
    this.BabyCanInfo = new ButtonElement(this.appController, this, 0, 0, 130, "BabyCan info") {
    	public void clickEvent() {
    		this.appController.switchViewToBabyCanInfo();
    		this.disable();
    	}
    };

    this.MotherCanInfo = new ButtonElement(this.appController, this, 0, 0, 120, "MotherCan info") {
    	public void clickEvent() {
    		this.appController.switchViewToMotherCanInfo();
    		this.disable();
    	}
    };
    
    this.FlightPath = new ButtonElement(this.appController, this, 0, 0, 90, "Flight path") {
    	public void clickEvent() {
    		this.appController.switchViewToFlightPath();
    		this.disable();
    	}
    };
    
    this.MeasuredData = new ButtonElement(this.appController, this, 0, 0, 120, "Measured data"){
        public void clickEvent(){
            this.appController.switchViewToDataCharts();
            this.disable();
          }
        };

    this.overviewButton = new ButtonElement(this.appController, this, 0, 0, 90, "Overview"){
      public void clickEvent(){
        this.appController.switchViewToOverview();
        this.disable();
      }
    };

    this.consoleButton = new ButtonElement(this.appController, this, 0, 0, 80, "Console"){
      public void clickEvent(){
        this.appController.switchViewToConsole();
        this.disable();
      }
    };

    this.elements.add(this.exitButton);
    this.elements.add(this.setupButton);
    this.elements.add(this.scrollBar);
    this.viewButtons.add(this.missionInfoButton);
    this.viewButtons.add(this.overviewButton);
    this.viewButtons.add(this.consoleButton);
    this.viewButtons.add(this.MotherCanInfo);
    this.viewButtons.add(this.BabyCanInfo);
    this.viewButtons.add(this.FlightPath);
    this.viewButtons.add(this.MeasuredData);
    
    float b = 5;
    for(ButtonElement e : this.viewButtons) {
    	e.resize(b, 50, e.dim.x);
    	b += e.dim.x + 5;
    }
    if(b > this.viewWidth) {
    	this.viewWidth = b;
    	this.scrollBar.setExtremes(0, this.viewWidth);
    	this.scrollBar.setCurrentPosition(0, this.dim.x);
    }
  }

  public void universalMethod(String func){
    if(func.equals("AddForceDeployButton")){
      this.elements.add(this.forceDeployButton);
    }
  }

  public void viewResizeTriggered(){
    this.exitButton.resize(5, 15, 60, 20);
    this.scrollBar.resize(0, this.dim.y - 5, this.dim.x);

    this.viewWidth = this.dim.x;
    float b = 5;
    for(ButtonElement e : this.viewButtons) {
    	e.resize(b, 50, e.dim.x);
    	b += e.dim.x + 5;
    }
    if(b > this.viewWidth) {
    	this.viewWidth = b;
    }
	this.scrollBar.setExtremes(0, this.viewWidth);
    this.scrollBar.setCurrentPosition(0, this.dim.x);
  }

  public void enableAllButtons(){
    for(ButtonElement e : this.viewButtons) {
    	e.enable();
    }
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
    line(this.pos.x, this.pos.y + 30, this.pos.x + this.dim.x, this.pos.y + 30);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    fill(0);
    textFont(fonts.get("SF").get("Regular"));
    textAlign(RIGHT);
    text(str(hour()) + ":" + (str(minute()).length() < 2 ? "0" + str(minute()) : str(minute())) + ":" + (str(second()).length() < 2 ? "0" + str(second()) : str(second())), this.dim.x - 5, 20);
    //text(CALENDAR.get(Calendar.DAY_OF_WEEK), this.dim.x - 75, 20);


    for(Element e : this.elements){
      e.show();
    }

    translate(-this.scrollBar.getMinimumValue(), 0);
    for(ButtonElement e : this.viewButtons){
      e.show();
    }
    translate(this.scrollBar.getMinimumValue(), 0);

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

    stroke(0);
    strokeWeight(2);
    line(this.pos.x, this.pos.y + this.dim.y, this.pos.x + this.dim.x, this.pos.y + this.dim.y);
  }

  public void mousePressed(){
    this.elementFilter.clear();
    for(int i = 0; i < this.elements.size(); ++i){
      if(this.elements.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    int biggest = -1;
    int index = -1;
    for(int i : this.elementFilter){
      if(this.elements.get(i).layer > biggest){
        biggest = this.elements.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.elements.get(index).mousePressed();
    }
    for(int i = 0; i < this.elements.size(); ++i){
      if(i != index){
        this.elements.get(i).deselect();
      }
    }

    // viewButton elements

    mouseX += this.scrollBar.getMinimumValue();

    this.elementFilter.clear();
    for(int i = 0; i < this.viewButtons.size(); ++i){
      if(this.viewButtons.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    biggest = -1;
    index = -1;
    for(int i : this.elementFilter){
      if(this.viewButtons.get(i).layer > biggest){
        biggest = this.viewButtons.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.viewButtons.get(index).mousePressed();
    }
    for(int i = 0; i < this.viewButtons.size(); ++i){
      if(i != index){
        this.viewButtons.get(i).deselect();
      }
    }

    mouseX -= this.scrollBar.getMinimumValue();
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }

    for(ButtonElement e : this.viewButtons){
      e.mouseReleased();
    }
  }

  public void mouseWheel(float count){
    if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y && mouseY <= this.pos.y + this.dim.y){
      this.mouseScrolled(count);
    }
    for(Element e : this.elements){
      if(e.mousePressIsWithinBorder()){
        e.mouseWheel(count);
      }
    }
  }

  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
  }

  public void keyPressed(char k, int c){
    for(Element e : this.elements){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(Element e : this.elements){
      if(e.selected){
        e.keyTyped(k);
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(Element e : this.elements){
      if(e.selected){
        e.keyReleased();
      }
    }
    for(ButtonElement e : this.viewButtons){
      if(e.selected){
        e.keyReleased();
      }
    }
  }
}
