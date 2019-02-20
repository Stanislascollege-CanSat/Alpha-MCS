// ConsoleView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class ConsoleView extends ViewController {
  public VerticalScrollElement scrollBar;
  public ArrayList<ConsoleMessageElement> messages;

  public ArrayList<ConsoleMessageElement> messagesToLog;

  public TickBoxElement autoScrollTickBox;
  public TextElement autoScrollLabel;

  public LineInputElement commandInput;

  public float calculatedMessageHeight;
  public float messageViewHeight;

  public ConsoleView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.messageViewHeight = this.dim.y - 80;

    this.scrollBar = new VerticalScrollElement(this.appController, this, this.pos.x + this.dim.x - 10, this.pos.y + this.messageViewHeight/2, this.messageViewHeight, 0, this.messageViewHeight);


    this.messages = new ArrayList<ConsoleMessageElement>();

    this.messagesToLog = new ArrayList<ConsoleMessageElement>();

    this.autoScrollTickBox = new TickBoxElement(this.appController, this, this.pos.x + 15, this.pos.y + this.dim.y - 20);
    this.autoScrollTickBox.setValue(true);

    this.autoScrollLabel = new TextElement(this.appController, this, this.pos.x + 30, this.pos.y + this.dim.y - 20, 200, "Autoscroll", LEFT);

    this.commandInput = new LineInputElement(this.appController, this, this.pos.x + 10, this.pos.y + this.dim.y - 55, this.dim.x - 20){
      public void clickEvent(){
        this.appController.logMessage("This is supposed to be a very long message, to test the capabilities of the console system.");
        this.reset();
      }
    };

    this.calculatedMessageHeight = 0;

    this.elements.add(this.scrollBar);
    this.elements.add(this.autoScrollTickBox);
    this.elements.add(this.autoScrollLabel);
    this.elements.add(this.commandInput);

    // for(int i = 0; i < 100; ++i){
    //   this.addMessage("This is a message");
    // }


  }

  private void arrangeMessages(){
    this.calculatedMessageHeight = 0;
    for(ConsoleMessageElement e : this.messages){
      e.resize(this.pos.x + 5, this.calculatedMessageHeight + 5 + e.dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + e.dim.y;
    }
    this.calculatedMessageHeight += 5;
    if(this.calculatedMessageHeight > this.messageViewHeight){
      // Now messages start to fall outside of the visible field
      this.scrollBar.setExtremes(0, this.calculatedMessageHeight);
      if(this.autoScrollTickBox.getValue()){
        this.scrollBar.setCurrentPosition(this.calculatedMessageHeight - this.messageViewHeight, this.calculatedMessageHeight);
      }else{

      }
    }

  }

  public void addMessage(String msg){
    this.messages.add(new ConsoleMessageElement(this.appController, this, this.pos.x + 5, 0, this.dim.x - 20, hour(), minute(), second(), msg));
    this.messages.get(this.messages.size() - 1).setScrollContainer(this.pos.y, this.pos.y + this.messageViewHeight);
    this.elements.add(this.messages.get(this.messages.size() - 1));
    this.arrangeMessages();
  }



  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
    this.autoScrollTickBox.setValue(false);
  }

  public void viewResizeTriggered(){
	this.messageViewHeight = this.dim.y - 80;
	this.scrollBar.resize(this.pos.x + this.dim.x - 10, this.pos.y + this.messageViewHeight/2, this.messageViewHeight);
  }

  public void show(){

    this.calculatedMessageHeight = 0;
    for(ConsoleMessageElement e : this.messages){
      e.resize(this.pos.x + 5, this.calculatedMessageHeight + 5 + e.dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + e.dim.y;
    }


    stroke(0);
    noFill();

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    line(this.pos.x + 100, this.pos.y, this.pos.x + 100, this.pos.y + this.messageViewHeight);
    line(this.pos.x, this.pos.y + this.messageViewHeight, this.pos.x + this.dim.x, this.pos.y + this.messageViewHeight);
    noStroke();
    fill(255);
    rect(this.pos.x, this.pos.y + this.messageViewHeight, this.dim.x, this.dim.y - this.messageViewHeight);

    for(Element e : this.elements){
        e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }
}
